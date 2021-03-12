package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CreateRoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);

        //get the intent that started this activity
        Intent intent = getIntent();

        //Create room and return generated code
        Room newRoom = new Room();
        int generatedRoomID = newRoom.getRoomId();

        //cast RoomID to string and send to screen
        String roomCode = String.valueOf(generatedRoomID);
        TextView displayRoomID = findViewById(R.id.textViewDisplayRoomID);
        displayRoomID.setText(roomCode);
    }
    public void readyUp (View view){
        Toast.makeText(getApplicationContext(),"Room not Ready (TODO)", Toast.LENGTH_SHORT).show();
        //TODO create Game Beginning activity - rules?
    }
}