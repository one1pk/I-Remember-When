package com.game.rememberwhen;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TurnSwitching {

    int count = 1, maxRound = 10;

    public TurnSwitching() {
    }

    public static void TurnSwitch(Bundle b, ArrayList<Player> players, Player player) {
        //call this class when one round pass
        //find the storyteller and make it listener, assign storyteller status to the first next person
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getStatus().equals("storyteller")) {
                players.get(i).setStatus("listener");
                players.get((i + 1) % players.size() - 1).setStatus("storyteller");
                i++;
            } else {
                players.get(i).setStatus("listener");
            }
        }
    }
}

/*        count+=1;
        if(count==maxRound)
        {
            //playersListText.setText("max round reaches");
            // new LeaderBoard(playerList);
            new WinnerActivity(playerList);
        }*/


