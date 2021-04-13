package com.game.rememberwhen;

import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.reflect.Array.getLength;

public class Score {

    private static final String TAG = "Score";
    private static String answer;
    private static String response;
    // private final Bundle savedInstanceState = new Bundle();
    TextView playersListText;
    RecyclerView players_list_view;
    private static Player player;
    //here answer means if storyteller tells truth or lie

    public Score() {
    }

    public static void updateScore(String room, Player player1) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("/rooms").document(room);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot docSnap = task.getResult();
                    if (docSnap.exists()) {
                        ArrayList<Player> players = ((ArrayList<Player>) docSnap.toObject(PlayerDocument.class).users);
                        int numPlayers = players.size();
                        for (int i = 0; i < numPlayers; i++) {
                            if (players.get(i).getStatus().equals("storyteller")) {
                                answer = players.get(i).getResponse();
                            }
                        }
                        for (int i = 0; i < numPlayers; i++) {
                            if (players.get(i).equals(player1)) {
                                player = players.get(i);
                                if (player.getStatus().equals("storyteller")) {
                                    // check responses of each player to calc score of storyteller
                                    for (int j = 0; j < numPlayers - 1; j++) {
                                        // modular addition makes sure to check all players before and after storyteller
                                        response = players.get((j + i) % (numPlayers - 1)).getResponse();
                                        if (answer.equals("truth")) {
                                            switch (response) {
                                                case "truth":

                                                    docRef.update(
                                                            "score", player.getScore() + 10
                                                    );
                                                    docRef.update(
                                                            "score dif", 10
                                                    );
                                                    break;

                                                case "MakeItUp":
                                                    docRef.update(
                                                            "score", player.getScore() - 10
                                                    );
                                                    docRef.update(
                                                            "score dif", -10
                                                    );

                                                    break;


                                            }
                                        } else {
                                            switch (response) {
                                                case "truth":
                                                    docRef.update(
                                                            "score", player.getScore() + 20
                                                    );
                                                    docRef.update(
                                                            "score dif", 20
                                                    );
                                                    break;

                                                case "MakeItUp":
                                                    docRef.update(
                                                            "score", player.getScore() - 20
                                                    );
                                                    docRef.update(
                                                            "score dif", -20
                                                    );
                                                    break;

                                            }
                                        }
                                    }

                                } else {
                                    response = player.getResponse();
                                    if (response == null) {}
                                    else if (response.equals("")) {
                                        docRef.update(
                                                "score", player.getScore() - 10
                                        );
                                        docRef.update(
                                                "score dif", -10
                                        );

                                    } else if (answer.equals("truth")) {
                                        switch (response) {
                                            case "truth":
                                                docRef.update(
                                                        "score", player.getScore() + 10
                                                );
                                                docRef.update(
                                                        "score dif", +10
                                                );
                                                break;

                                            case "MakeItUp":
                                                docRef.update(
                                                        "score", player.getScore() - 10
                                                );
                                                docRef.update(
                                                        "score dif", -10
                                                );
                                                break;

                                        }
                                    } else {
                                        switch (response) {
                                            case "truth":
                                                docRef.update(
                                                        "score", player.getScore() - 5
                                                );
                                                docRef.update(
                                                        "score dif", -5
                                                );
                                                break;

                                            case "MakeItUp":
                                                docRef.update(
                                                        "score", player.getScore() + 10
                                                );
                                                docRef.update(
                                                        "score dif", +10
                                                );
                                                break;
                                        }
                                    }
                                }
                            }

                        }
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });


    }
}
/*
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
                if (getRespond().equals("")) {
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
        new LeaderboardActivity(playerList);

    }

 */

