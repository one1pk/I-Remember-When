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
public class LeaderBoard extends AppCompatActivity {
    List<Player> list=new ArrayList<>();
    TextView mytext=findViewById(R.id.mytext);
    TextView winnerText=findViewById(R.id.winnerTextView);
    TextView maxRound=findViewById(R.id.maxRTextView);
    String a;

    public LeaderBoard(List<Player> playerList)
    {
        this.list = playerList;
        showList();
    }
    public LeaderBoard(List<Player> playerList,String a)
    {
        this.list = playerList;
        this.a=a;
        showList();
    }
    public LeaderBoard(List<Player> playerList,String a,int b)
    {
        this.list = playerList;
        this.a=a;
        showList();
    }


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
            mytext.append(p.toString()+ "\n\n");
        }
        if(a=="winner")
            winnerText.setText("winner is found");
        else if(a=="maxRound")
            winnerText.setText("Max Round is reached");

    }
    public void nextRound(View view) {
        Intent intent = new Intent(this, TurnSwitching.class);
        startActivity(intent);
    }

}
