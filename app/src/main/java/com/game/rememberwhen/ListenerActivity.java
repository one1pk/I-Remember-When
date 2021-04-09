package com.game.rememberwhen;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ListenerActivity<prompt> extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        setContentView(R.layout.activity_listener);

        loadUI();
    }

    public void openRules(View view) {
        Intent intent = new Intent(this, RulesActivity.class);
        startActivity(intent);
    }

    //TODO import current prompt, storyteller, and when done telling the story from StorytellerActivity.java
    private void loadUI() {
        //display prompt
        TextView Prompt = findViewById(R.id.displayPrompt);
        Prompt.setText(StorytellerActivity.getPrompt()); //TODO SET IN FIREBASE OR SEND FCM DATA MESSAGE TO ALL USERS StoryTeller.Device can not share that here
    }

}