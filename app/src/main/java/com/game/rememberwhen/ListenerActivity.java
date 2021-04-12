package com.game.rememberwhen;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ListenerActivity<prompt> extends AppCompatActivity {

    private TextView prompt;
    private Button voteTrue;
    private Button voteFalse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        setContentView(R.layout.activity_listener);

        loadUI();

    }

    public void voteNow(View view) {
        setContentView(R.layout.deliberation_listener);
        voteTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO [Delaram] update scores
                new Score("truth",0);
                Intent intentLeaderboard = new Intent(ListenerActivity.this, LeaderBoardActivity.class);
                startActivity(intentLeaderboard);
            }
        });

        voteFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO [Delaram] update scores
                new Score("makeItUp",0);
                Intent intentLeaderboard = new Intent(ListenerActivity.this, LeaderBoardActivity.class);
                startActivity(intentLeaderboard);
            }
        });
    }

    public void openRules(View view) {
        Intent intent = new Intent(this, RulesActivity.class);
        startActivity(intent);
    }

    //TODO note to everyone: removed functionality for prompt to be displayed as it is no longer necessary. does this method need to be here anymore?
    private void loadUI() {

        voteTrue = findViewById(R.id.listenerVoteTrue);
        voteFalse = findViewById(R.id.listenerVoteFalse);
    }


}