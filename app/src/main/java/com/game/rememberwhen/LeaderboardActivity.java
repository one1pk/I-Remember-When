package com.game.rememberwhen;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
public class LeaderboardActivity extends AppCompatActivity {
    List<Player> list=new ArrayList<>();
    TextView mytxt=findViewById(R.id.mytext);
    public LeaderboardActivity(List<Player> playerList)
    {
        this.list = playerList;

        // return playerList;
    }
       // Collections.sort(playerList, Collections.reverseOrder());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);
        Collections.sort(list,Collections.reverseOrder());
        showList();


    }

    private void showList() {
        for (Player p: list)
        {
            mytxt.append(p.toString()+ "\n\n");

        }
    }
    public void nextRound(View view) {
        Intent intent = new Intent(this, TurnSwitching.class);
        startActivity(intent);
    }

}
