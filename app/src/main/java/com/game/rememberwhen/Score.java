package com.game.rememberwhen;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Score extends AppCompatActivity{

   // private final Bundle savedInstanceState = new Bundle();
    TextView playersListText;
    RecyclerView players_list_view;
    Player player;
    //here answer means if storyteller tells truth or lie
    String answer;
    public String getAnswer() { return answer; }

    //the respond that gets from listeners
    String respond;
    public String getRespond() { return respond; }

    //Intent intent = getIntent();
   // int diffScore;
    public Score(String a)
    {
        answer=a;
    }
    public Score(String a,int b)
    {
        respond=a;
    }

    public Score(List<Player> playerList){
        for (int i = 0; i < playerList.size(); i++) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            final CollectionReference collection = db.collection("/rooms");
            if (playerList.get(i).getScore() == 500) {
              new WinnerActivity(playerList);
            }

            if (playerList.get(i).getStatus().equals("storyteller")) {
                if (getAnswer().equals("truth")) {
                    switch (getRespond()) {
                        case "truth":

                            db.collection("/rooms").document("name").update(
                                    "score", player.getScore() + playerList.size() * 10
                            );
                            db.collection("/rooms").document("name").update(
                                    "score dif",  playerList.size() * 10
                            );
                            break;

                        case "MakeItUp":
                            db.collection("/rooms").document("name").update(
                                    "score", player.getScore() - 10
                            );
                            db.collection("/rooms").document("name").update(
                                    "score dif",   -10
                            );

                            break;


                    }
                } else {
                    switch (getRespond()) {
                        case "truth":
                            db.collection("/rooms").document("name").update(
                                    "score", player.getScore() + playerList.size() * 20
                            );
                            db.collection("/rooms").document("name").update(
                                    "score dif",   playerList.size() * 20
                            );
                            break;

                        case "MakeItUp":
                            db.collection("/rooms").document("name").update(
                                    "score", player.getScore()  - 20
                            );
                            db.collection("/rooms").document("name").update(
                                    "score dif",   -20
                            );
                            break;

                    }
                }

            } else {
                if (getAnswer().equals("")) {
                    db.collection("/rooms").document("name").update(
                            "score", player.getScore() - 10
                    );
                    db.collection("/rooms").document("name").update(
                            "score dif",   -10
                    );
                }
                else if (getAnswer().equals("truth")) {
                    switch (getRespond()) {
                        case "truth":
                            db.collection("/rooms").document("name").update(
                                    "score", player.getScore() + 10
                            );
                            db.collection("/rooms").document("name").update(
                                    "score dif",   +10
                            );
                            break;

                        case "MakeItUp":
                            db.collection("/rooms").document("name").update(
                                    "score", player.getScore()  - 10
                            );
                            db.collection("/rooms").document("name").update(
                                    "score dif",   -10
                            );
                            break;

                    }
                } else {
                    switch (getRespond()) {
                        case "truth":
                            db.collection("/rooms").document("name").update(
                                    "score", player.getScore()  -5
                            );
                            db.collection("/rooms").document("name").update(
                                    "score dif",   -5
                            );
                            break;

                        case "MakeItUp":
                            db.collection("/rooms").document("name").update(
                                    "score", player.getScore() + 10
                            );
                            db.collection("/rooms").document("name").update(
                                    "score dif",   +10
                            );
                            break;
                    }
                }
            }


        }
        new LeaderBoardActivity(playerList);

    }

}
