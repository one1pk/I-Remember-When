package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class JoinRoomActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_room);

        //get the intent that started this activity
        Intent intent = getIntent();
    }

    //check the code give by user is valid, connect user to open room
    public void findRoom(View view){
        //TODO create and connect to game waiting room
        Toast.makeText(getApplicationContext(),"No Rooms Available", Toast.LENGTH_SHORT).show();
        /*lines below will get code from user, check if it is valid and send user to the matching room */

        /* Intent intent = new Intent(this, WaitingRoom.class);
        EditText editText = (EditText) findViewById(R.id.JoiningRoomID);
        String userEnteredID = editText.getText().toString();
        isRoomIDValid(userEnteredID);

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        */
    }

    /*check user entered roomID against available roomIDs*/
    //TODO change isRoomIDValid to bool and implement
    /*public void isRoomIDValid (String uRoomID){
        //TODO logic to check if userEnteredID is valid
        return;
    }*/
}