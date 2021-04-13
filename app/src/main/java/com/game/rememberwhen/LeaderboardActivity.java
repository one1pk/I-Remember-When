package com.game.rememberwhen;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class LeaderboardActivity extends AppCompatActivity {
    private ArrayList<Player> players;
    private ArrayList<Player> players2;
    private TextView mytxt;
    private Button nextBtn;
    private Player player;

    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);
        b = getIntent().getExtras();
        player = (Player) b.getSerializable("player");
        players2 = (ArrayList<Player>)b.getSerializable("allPlayers");

        nextBtn = (Button) findViewById(R.id.nextRoundbutton);
        mytxt = findViewById(R.id.mytext);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("/rooms").document(b.get("roomId").toString());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot docSnap = task.getResult();
                    players = ((ArrayList<Player>) docSnap.toObject(PlayerDocument.class).users);
                    Collections.sort(players, Collections.reverseOrder());
                    showList();
                }
            }
        });

        final Intent intentHost = new Intent(this, StorytellerActivity.class);
        final Intent intentRest = new Intent(this, ListenerActivity.class);
        View.OnClickListener nextRoundActivity = new View.OnClickListener() {
            public void onClick(View view) {
                TurnSwitching.TurnSwitch(b, players, player);
                intentHost.putExtras(b);
                startActivity(intentHost);
                if (player.getStatus().equals("storyteller")) {
                    intentHost.putExtras(b);
                    startActivity(intentHost);
                }
                else {
                    intentRest.putExtras(b);
                    startActivity(intentRest);
                }
            }
        };

        nextBtn.setOnClickListener(nextRoundActivity);
    }

    private void showList() {
        for (Player p: players)
        {
            mytxt.append(p.toString()+ "\n\n");
        }
        for (Player p: players2)
        {
            mytxt.append(p.toString()+ "\n\n");
        }
    }

}
