package com.game.rememberwhen;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;

public class StorytellerActivity extends AppCompatActivity {
    private FirebaseDatabase database;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        setContentView(R.layout.activity_storyteller);

        //loadDataset();
        /*
        string FirebaseFirestore.getInstance().collection("/rooms").document(String.valueOf(b.get("roomId"))).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

            }
        });
        */

        // When user chooses lie/truth, screen view changes for storyteller to tell story
        View.OnClickListener truthListener = new View.OnClickListener() {
            public void onClick(View view) {
                setContentView(R.layout.activity_storyteller_talk);
                startTimer(); // begin timer on display
                // TODO [DELARAM] change score status
            }
        };
        View.OnClickListener lieListener = new View.OnClickListener() {
            public void onClick(View view) {
                setContentView(R.layout.activity_storyteller_talk);
                startTimer(); // begin timer on display
                // TODO [DELARAM] change score status
            }
        };

        lieButton.setOnClickListener(lieListener);
        truthButton.setOnClickListener(truthListener);

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
        cTimer.start();
    }

    private void endStoryTime(View view) {
        // TODO switch to Deliberation Activity
    }

    // function called when 'Rules' button pressed (onClick in .xml)
    public void openRules(View view) {
        Intent intent = new Intent(this, RulesActivity.class);
        startActivity(intent);
    }
}
