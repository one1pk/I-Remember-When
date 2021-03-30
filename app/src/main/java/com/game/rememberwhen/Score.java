package com.game.rememberwhen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Score {
    Player player;
    //here answer means if storyteller telols truth or lie
    String answer;
    public String getAnswer() { return answer; }

    //the respond that gets from listeners
    String respond;
    public String getRespond() { return respond; }

    //assign the score for all players at one round
    //should be call at end of each cycle
    public Score(List<Player> playerList) {
        for (int i = 0; i < playerList.size(); i++) {
            if(playerList.get(i).score==500)
            {
                //assign code for saying winner is found
            }
            if (playerList.get(i).getStatus() == "storyTeller") {
                if (answer.equals("truth")) {
                       switch (respond) {
                            case "truth":
                            player.setScore(player.getScore()+ playerList.size() * 10); break;

                            case "MakeItUp":
                            // what happen?
                            break;


                        }
                }
                 else{
                        switch (respond) {
                            case "truth":
                            player.setScore(player.getScore()+ playerList.size() * 20); break;

                            case "MakeItUp":
                            player.setScore(player.getScore()-20); break;

                        }
                 }

            }
            else{
                if(respond.equals(""))
                    player.setScore(player.getScore()- 10);
                else if (answer.equals("truth")) {
                        switch (respond) {
                            case "truth":
                            player.setScore(player.getScore()+ 10); break;

                            case "MakeItUp":
                            player.setScore(player.getScore()- 10); break;

                        }
                }
                else{
                     switch (respond) {
                           case "truth":
                            player.setScore(player.getScore()-5); break;

                            case "MakeItUp":
                            player.setScore(player.getScore()+10); break;
                     }
                }
            }
        }

   }
}

