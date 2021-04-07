package com.game.rememberwhen;
import android.widget.TextView;

import java.util.List;

import java.util.ArrayList;
public class TurnSwitching {
    TextView playersListText;
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
        count+=1;
        if(count==maxRound)
        {
            playersListText.setText("max round reaches");
            //new leaderBoard(playerList);
        }

    }
}
