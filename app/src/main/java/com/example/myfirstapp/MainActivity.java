package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.irememeberwhen.CREATEROOM";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

            public void createRoom (View view) {
                //go to CreateRoom Activity
                Intent intent = new Intent(this, CreateRoomActivity.class);
                startActivity(intent);
            }

            public void joinRoom (View view){
                Toast.makeText(getApplicationContext(),"No Rooms to Join (TODO)", Toast.LENGTH_SHORT).show();
                //TODO create joinRoom Activity

                /* Intent intent = new Intent(this, JoinRoomActivity.class);
                startActivity(intent);*/
            }

}