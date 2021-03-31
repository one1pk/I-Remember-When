package com.game.rememberwhen;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Score {
    TextView playersListText;
    RecyclerView players_list_view;
    Player player;
    //here answer means if storyteller telols truth or lie
    String answer;
    public String getAnswer() { return answer; }

    //the respond that gets from listeners
    String respond;
    public String getRespond() { return respond; }

    public Score(List<Player> playerList) {
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).score == 500) {
                  playersListText.setText("Winner found");
            }

            if (playerList.get(i).getStatus() == "storyTeller") {
                if (answer.equals("truth")) {
                    switch (respond) {
                        case "truth":
                            player.setScore(player.getScore() + playerList.size() * 10);
                            break;

                        case "MakeItUp":
                            player.setScore(player.getScore() - 10);
                            break;


                    }
                } else {
                    switch (respond) {
                        case "truth":
                            player.setScore(player.getScore() + playerList.size() * 20);
                            break;

                        case "MakeItUp":
                            player.setScore(player.getScore() - 20);
                            break;

                    }
                }

            } else {
                if (respond.equals(""))
                    player.setScore(player.getScore() - 10);
                else if (answer.equals("truth")) {
                    switch (respond) {
                        case "truth":
                            player.setScore(player.getScore() + 10);
                            break;

                        case "MakeItUp":
                            player.setScore(player.getScore() - 10);
                            break;

                    }
                } else {
                    switch (respond) {
                        case "truth":
                            player.setScore(player.getScore() - 5);
                            break;

                        case "MakeItUp":
                            player.setScore(player.getScore() + 10);
                            break;
                    }
                }
            }
        }
    }

   }


