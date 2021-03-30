package com.game.rememberwhen;
import java.util.List;

import java.util.ArrayList;
public class TurnSwitching {
    public void TurnSwitch(List<Player> playerList){
        //call this class when one round pass
        //find the storyteller and make it listener, assigne storyteller status to the first next person
        for(int i = 0; playerList.size() < i; i++) {
            if(playerList.get(i).getStatus()=="storyTeller") {
                playerList.get(i).setStatus("listener");
                playerList.get(i + 1).setStatus("listener");
            }
        }

    }
}

