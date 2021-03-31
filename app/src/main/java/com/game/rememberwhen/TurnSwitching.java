package com.game.rememberwhen;
import java.util.List;

import java.util.ArrayList;
public class TurnSwitching {
    public void TurnSwitch(List<Player> playerList){
        //call this class when one round pass
        //find the storyteller and make it listener, assign storyteller status to the first next person
        for(int i = 0; i< playerList.size(); i++) {
            if(playerList.get(i).getStatus().equals("storyTeller")) {
                playerList.get(i).setStatus("listener");
                playerList.get(i + 1).setStatus("storyteller");
            }
        }

    }
}
