package com.game.rememberwhen;

import android.content.Intent;
<<<<<<< app/src/main/java/com/game/rememberwhen/StorytellerActivity.java
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.game.rememberwhen.listeners.PlayerListener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class StorytellerActivity extends AppCompatActivity implements PlayerListener {
    private FirebaseDatabase database;
    private Player player;
    private ArrayList<Player> getSelectedUsers = new ArrayList<>();

    static String prompt;

    private TextView promptTextView;
    private TextView timerTextView;
    private Button lieButton;
    private Button truthButton;
    private Button buttonDone;
    private Button buttonMoreTime;

    private int promptCounter = -1;
    private CountDownTimer cTimer = null;
    private int timeLeft = 120;

    private ArrayList<Prompt> dataset = new ArrayList<Prompt>();

    private final int REQUEST_CODE_BATTERY_OPTIMIZATIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        player = (Player) b.getSerializable("player");
        getSelectedUsers.addAll((ArrayList<Player>) b.getSerializable("selectedUsersLIST"));
        System.out.println(getSelectedUsers.toString());
        
        setContentView(R.layout.activity_storyteller);
        truthButton = (Button) findViewById(R.id.truthButton);
        lieButton = (Button) findViewById(R.id.lieButton);
        timerTextView = (TextView) findViewById(R.id.timer); 

        database = FirebaseDatabase.getInstance();

        loadDataset();
        
        setContentView(R.layout.activity_storyteller);

        loadDataset();

        // When user chooses lie/truth, screen view changes for storyteller to tell story
        View.OnClickListener truthListener = new View.OnClickListener() {
            public void onClick(View view) {
                setContentView(R.layout.activity_storyteller_talk);
                StorytellerActivity.this.onMultipleUsersAction(true);
                startTimer(); // begin timer on display
                // TODO [DELARAM] change score status
            }
        };
        View.OnClickListener lieListener = new View.OnClickListener() {
            public void onClick(View view) {
                StorytellerActivity.this.onMultipleUsersAction(true);
                setContentView(R.layout.activity_storyteller_talk);
                startTimer(); // begin timer on display
                // TODO [DELARAM] change score status
            }
        };

        lieButton.setOnClickListener(lieListener);
        truthButton.setOnClickListener(truthListener);

    }

    private void loadDataset() {
        DatabaseReference myDBRef = database.getReference().child("db").child("prompts");
        // Read from database
        myDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataset.size() != 0) {
                    dataset.clear();
                }
                for (DataSnapshot promptSnapshot : dataSnapshot.getChildren()) {
                    Prompt prompt = promptSnapshot.getValue(Prompt.class);
                    dataset.add(prompt);
                }
                randomizePrompts();
                showPrompt();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("FIREBASE", "Failed to read value.", error.toException());
            }
        });
        }

    // Randomize order of prompts for each new game room
    private void randomizePrompts() {
        Collections.shuffle(dataset);
    }

    // increment prompt number and display new prompt
    private void showPrompt() {
        promptTextView = findViewById(R.id.textViewPrompt); // put in loadUI method?
        prompt = dataset.get(++promptCounter).prompt;
        promptTextView.setText(prompt);
    }

    //enable listener activity to retrieve prompt
    static String getPrompt(){
        return prompt;
    }

    public void skipPrompt(View view) {
        prompt = dataset.get(++promptCounter).prompt;
        promptTextView.setText(prompt);
    }

    private void startTimer() {
        cTimer.start();
        cTimer = new CountDownTimer(timeLeft*1000, 1000) {
            // update timer every second
            public void onTick(long millisUntilFinished) {
                timeLeft = (int)(millisUntilFinished / 1000);
                timerTextView.setText("Time Left: " + timeLeft);
                // display option for More Time when time left is under 10 seconds
                if(timeLeft <= 10) {
                    buttonMoreTime.setVisibility(View.VISIBLE);
                }
            }
            // end storytelling phase once timer runs out
            public void onFinish() {
                endStoryTime(buttonDone);
            }
        };
    }

    private void endStoryTime(View view) {
        // TODO switch to Deliberation Activity
        cTimer.cancel();
    }

    // function called when 'Rules' button pressed (onClick in .xml)
    public void openRules(View view) {
        Intent intent = new Intent(this, RulesActivity.class);
        startActivity(intent);
    }

    @Override
    public void initiateVideoMeeting(Player user) {
        final boolean isValidToken = user.token != null && !user.token.trim().isEmpty();

        if (!isValidToken) {
            Toast.makeText(this, user.getName() + " " + user.getStatus() + " is not available for meeting", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(getApplicationContext(), OutgoingInvitationActivity.class);
            intent.putExtra("user", new Gson().toJson(player, Player.class));
            intent.putExtra("type", "video");
            startActivity(intent);
        }
    }

    @Override
    public void initiateAudioMeeting(Player user) {
        final boolean isValidToken = user.token != null && !user.token.trim().isEmpty();

        if (!isValidToken) {
            Toast.makeText(this, user.getName() + " " + user.getStatus() + " is not available for meeting", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(getApplicationContext(), OutgoingInvitationActivity.class);
            intent.putExtra("user", new Gson().toJson(player, Player.class));
            intent.putExtra("type", "audio");
            startActivity(intent);
        }
    }

    @Override
    public void onMultipleUsersAction(Boolean isMultipleUsersSelected) {
        if (isMultipleUsersSelected) {
//            imageConference.setVisibility(View.VISIBLE);
//            imageConference.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), OutgoingInvitationActivity.class);
            intent.putExtra("user", new Gson().toJson(player, Player.class));
            Bundle bundle = intent.getExtras();
            bundle.putSerializable("selectedUsersLIST", (Serializable) getSelectedUsers);
            bundle.putString("type", "video");
            bundle.putString("token", player.getToken());
            bundle.putBoolean("isMultiple", true);
            bundle.putSerializable("user", (Serializable) player);

            intent.putExtras(bundle);
            intent.putExtra("selectedUsers", new Gson().toJson(getSelectedUsers));
            intent.putExtra("type", "video");
            intent.putExtra("isMultiple", true);
            startActivity(intent);


//            });
        } else {
//            imageConference.setVisibility(View.GONE);
        }
    }

    private void checkForBatteryOptimizations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
            if (!powerManager.isIgnoringBatteryOptimizations(getPackageName())) {
                new AlertDialog.Builder(StorytellerActivity.this)
                        .setTitle("Waring")
                        .setMessage("Battery optimization is enabled. It can interrupt running background services")
                        .setPositiveButton("Disable", (dialog, which) -> {
                            Intent intent = new Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
                            startActivityForResult(intent, REQUEST_CODE_BATTERY_OPTIMIZATIONS);
                        })
                        .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                        .show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_BATTERY_OPTIMIZATIONS) {
            checkForBatteryOptimizations();
        }
    }

}
