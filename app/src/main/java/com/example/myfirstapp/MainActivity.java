package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
            //open up CreateRoomActivity
            public void createRoom (View view) {
                Intent intent = new Intent(this, CreateRoomActivity.class);
                startActivity(intent);
            }

            //open up JoinRoomActivity
            public void joinRoom (View view){
                Intent intent = new Intent(this, JoinRoomActivity.class);
                startActivity(intent);
                //TODO if no rooms available uncomment toast
                // Toast.makeText(getApplicationContext(),"No Rooms to Join", Toast.LENGTH_SHORT).show();
            }

}