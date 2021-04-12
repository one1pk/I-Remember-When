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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
public class WinnerActivity extends AppCompatActivity {
    List<Player> list=new ArrayList<>();
    TextView mytxt=findViewById(R.id.mytext);
    private Button retryBtn;
    private Button quit;

    public WinnerActivity(List<Player> playerList)
    {
        this.list = playerList;
        showList();
        //TODO this should be a recyclerView like in ManageNewRoomActivity
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winner_page);
        Collections.sort(list,Collections.reverseOrder());
        retryBtn = (Button) findViewById(R.id.retry);
        quit = (Button) findViewById(R.id.quitBtn);

        showList();

        Intent intent = new Intent(this, TurnSwitching.class);
        View.OnClickListener retryActivity = new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(intent);
            }
        };
        View.OnClickListener quitActivity = new View.OnClickListener() {
            public void onClick(View view) {
                //TODO set the quit the game

            }
        };
        retryBtn.setOnClickListener(retryActivity);
        quit.setOnClickListener(quitActivity);


    }

    private void showList() {
        for (Player p: list)
        {
            mytxt.append(p.toString()+ "\n\n");

        }
    }


}
