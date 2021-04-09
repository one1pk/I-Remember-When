package com.game.rememberwhen;
import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class TurnSwitching extends AppCompatActivity {
    TextView playersListText;
    Player player;
    int count=1,maxRound=10;
    public void TurnSwitch(List<Player> playerList){
        //call this class when one round pass
        //find the storyteller and make it listener, assign storyteller status to the first next person
        for(int i = 0; i< playerList.size(); i++) {
            if(playerList.get(i).getStatus().equals("storyteller")) {
                playerList.get(i).setStatus("listener");
                playerList.get(i + 1).setStatus("storyteller");
            }
        }
        final Intent intentHost = new Intent(this, StorytellerActivity.class);
        final Intent intentRest = new Intent(this, ListenerActivity.class);
        // ? not sure how to send players in the diff activity class ?
        if (player.getStatus().equals("storyteller")) {
            startActivity(intentHost);
        }
        else {
            startActivity(intentRest);
        }
        count+=1;
        if(count==maxRound)
        {
            playersListText.setText("max round reaches");
            new LeaderboardActivity(playerList);
        }

    }
}
