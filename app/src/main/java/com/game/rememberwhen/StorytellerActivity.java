package com.game.rememberwhen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class StorytellerActivity extends AppCompatActivity {
    private FirebaseDatabase database;

    private Prompt prompt;

    private TextView promptTextView;

    private int promptCounter = 0;

    private ArrayList<Prompt> dataset = new ArrayList<Prompt>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the intent that started this activity
        Intent intent = getIntent();
        Bundle b = getIntent().getExtras();
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
//                startTimer();
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

    private void showPrompt() {
        promptTextView = findViewById(R.id.textViewPrompt); // put in loadUI method?
        promptTextView.setText(dataset.get(promptCounter).prompt);
    }

    // TODO: Round timer and EndRound functionality
    /*
    private void startTimer() {
        cTimer = new CountDownTimer(timeLeft*1000, 1000) {
            public void onTick(long millisUntilFinished) {
                timeLeft = (int)(millisUntilFinished / 1000);
                timerTextView.setText("Time Left: " + String.valueOf(timeLeft));
            }
            public void onFinish() {
                endRound();
            }
        };
        cTimer.start();
    }

    private void cancelTimer() {
        if(cTimer!=null)
            cTimer.cancel();
    }

    private void endRound() {
        cancelTimer();

        Intent intent = new Intent(this, EndGameActivity.class);
        intent.putExtra("time_left", timeLeft);
        int finalScore = score + (timeLeft * 10);
        intent.putExtra("score", finalScore);
        startActivity(intent);

        finish();
    }
     */

}
