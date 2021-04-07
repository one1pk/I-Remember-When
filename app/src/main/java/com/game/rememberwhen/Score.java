package com.game.rememberwhen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.game.rememberwhen.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class Score {
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

    public Score(List<Player> playerList) {
        for (int i = 0; i < playerList.size(); i++) {
            FirebaseFirestore db=FirebaseFirestore.getInstance();
            final CollectionReference collection= db.collection("/rooms");
            if (playerList.get(i).getScore() == 500) {
                playersListText.setText("Winner found");

            }

            if (playerList.get(i).getStatus().equals("storyteller")) {
                if (answer.equals("truth")) {
                    switch (respond) {
                        case "truth":
                            db.collection("/rooms").document("name").update(
                                    "score", player.getScore()+ playerList.size()*10
                            );
                            break;

                        case "MakeItUp":
                            player.setScore(player.getScore() - 10);
                            break;


                    }
                } else {
                    switch (respond) {
                        case "truth":
                            db.collection("/rooms").document("name").update(
                                    "score", player.getScore()+ playerList.size()*20
                            );
                            break;

                        case "MakeItUp":
                            db.collection("/rooms").document("name").update(
                                    "score", player.getScore()+ playerList.size()-20
                            );
                            break;

                    }
                }

            } else {
                if (respond.equals(""))
                    db.collection("/rooms").document("name").update(
                            "score", player.getScore()+ playerList.size()-10
                    );
                else if (answer.equals("truth")) {
                    switch (respond) {
                        case "truth":
                            db.collection("/rooms").document("name").update(
                                    "score", player.getScore()+ playerList.size()+10
                            );
                            break;

                        case "MakeItUp":
                            db.collection("/rooms").document("name").update(
                                    "score", player.getScore()+ playerList.size()-10
                            );
                            break;

                    }
                } else {
                    switch (respond) {
                        case "truth":
                            db.collection("/rooms").document("name").update(
                                    "score", player.getScore()+ playerList.size()-5
                            );
                            break;

                        case "MakeItUp":
                            db.collection("/rooms").document("name").update(
                                    "score", player.getScore()+ playerList.size()+10
                            );
                            break;
                    }
                }
            }
        }
    }



}
