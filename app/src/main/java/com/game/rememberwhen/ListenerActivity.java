package com.game.rememberwhen;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ListenerActivity<prompt> extends AppCompatActivity {

    private FirebaseFirestore db;
    private DocumentReference docRef;


    private Bundle b;
    private ViewFlipper flipper;

    private Player player;

    private TextView prompt;
    private TextView timer;
    private Button voteTrue;
    private Button voteFalse;
    private Button voteNow;

    private CountDownTimer cTimer = null;
    private int timeLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = getIntent().getExtras();
        player = (Player) b.getSerializable("player");
        setContentView(R.layout.listener_flipper);

        flipper = (ViewFlipper) findViewById(R.id.listenerFlipper);
        LayoutInflater factory = LayoutInflater.from(this);
        View firstView = factory.inflate(R.layout.deliberation_listener, null);
        flipper.addView(firstView);

        loadUI();

        voteNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipper.showNext();
                setBtnListeners();
                startTimer();
            }
        });

        db = FirebaseFirestore.getInstance();
        docRef = db.collection("/rooms").document(b.get("roomId").toString());
    }

    //TODO import current prompt, storyteller, and when done telling the story from StorytellerActivity.java
    private void loadUI() {
        //display prompt
        prompt = findViewById(R.id.displayPrompt);
        prompt.setText(StorytellerActivity.getPrompt()); //TODO SET IN FIREBASE OR SEND FCM DATA MESSAGE TO ALL USERS StoryTeller.Device can not share that here
        timer = findViewById(R.id.timerTextView);

        voteNow = findViewById(R.id.voteBtn);
        voteTrue = findViewById(R.id.listenerVoteTrue);
        voteFalse = findViewById(R.id.listenerVoteFalse);
    }

    public void setBtnListeners() {

        voteTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cTimer.cancel();
                // new Score("truth",0);
                player.setResponse("truth");
                docRef.update("response", "truth");
                Score.updateScore(b.get("roomId").toString(), player);

                Intent intentLeaderboard = new Intent(ListenerActivity.this, LeaderboardActivity.class);
                intentLeaderboard.putExtras(b);
                startActivity(intentLeaderboard);
            }
        });

        voteFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cTimer.cancel();
                // new Score("makeItUp",0);
                player.setResponse("MakeItUp");
                docRef.update("response", "MakeItUp");
                Score.updateScore(b.get("roomId").toString(), player);

                Intent intentLeaderboard = new Intent(ListenerActivity.this, LeaderboardActivity.class);
                intentLeaderboard.putExtras(b);
                startActivity(intentLeaderboard);
            }
        });
    }

    private void startTimer() {
        timeLeft = 120;
        cTimer = new CountDownTimer(timeLeft*1000, 1000) {
            // update timer every second
            public void onTick(long millisUntilFinished) {
                timeLeft = (int)(millisUntilFinished / 1000);
                timer.setText(String.valueOf(timeLeft));
            }
            // end storytelling phase once timer runs out
            public void onFinish() {
                player.setResponse("");
                docRef.update("response", "");
                Score.updateScore(b.get("roomId").toString(), player);

                Intent intentLeaderboard = new Intent(ListenerActivity.this, LeaderboardActivity.class);
                intentLeaderboard.putExtras(b);
                startActivity(intentLeaderboard);
            }
        };
        cTimer.start();
    }

    public void openRules(View view) {
        Intent intent = new Intent(this, RulesActivity.class);
        startActivity(intent);
    }

}