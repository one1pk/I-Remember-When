package com.game.rememberwhen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.game.myfirstapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;

// Main Entry point / Activity for Application
public class MainActivity extends AppCompatActivity {
    Player player;
    Bundle b; // Data Transfer utility between two android activities

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = getIntent().getExtras(); // Getting default bundle
    }

    // On Click CreateRoom Method
    public void createRoom(View view) {
        // Go to CreateRoom Activity
        EditText usernameText = findViewById(R.id.editTextTextPersonName);
        String userName = usernameText.getText().toString();
        player = new Player(userName, 0); // Initial Score 0
        final Room room = new Room();
        player.setRoomId(room.getRoomId());
        CollectionReference fireStore = this.initFireStore().collection("/rooms"); // FireStore root node collection reference

        DocumentReference path = fireStore.document(String.valueOf(room.getRoomId())); // Creating Document node with RoomId
        // It looks like this
        // /rooms/RandomRoomId

        ArrayList arrayList = new ArrayList<Player>(); // [{PlayerObject},{PlayerObject}]
        HashMap hm = new HashMap<String, ArrayList<Player>>(); // Firestore works on Key-Value so need HashMap
        // to map {"users":[{PlayerObject},{PlayerObject}]}
        hm.put("users", arrayList);
        arrayList.add(player);
        fireStore.document(String.valueOf(room.getRoomId())).set(hm) // Saving new room with host Player
                .addOnSuccessListener(new OnSuccessListener<Void>() { // Async Wait for Firebase to respond
                    @Override
                    public void onSuccess(Void v) {
                        System.out.println("Room Created with id " + room.getRoomId());
                        Bundle b = new Bundle();
                        b.putSerializable("player", new Gson().toJson(player));
                        b.putString("roomId", String.valueOf(room.getRoomId()));
                        Intent intent = new Intent(MainActivity.this, CreateRoomActivity.class);
                        intent.putExtras(b);
                        startActivity(intent); // Transition to CreateRoomActivity.class/layout
                    }
                });
//            .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        result.error("deleteError", e.getLocalizedMessage(), null);
//                    }
//                });
//            }

    }

    // Join Room Button Click
    public void joinRoom(View view) {
//        Toast.makeText(getApplicationContext(),"No Rooms to Join (TODO)", Toast.LENGTH_SHORT).show();
        //TODO create joinRoom Activity
        EditText usernameText = findViewById(R.id.editTextTextPersonName);
        String userName = usernameText.getText().toString();
        if (b == null) {
            b = new Bundle();
        }
        b.putString("userName", userName);

        Intent intent = new Intent(this, JoinRoomActivity.class);
        intent.putExtras(b);
        startActivity(intent); // Passing Username to JoinRoomActivity.class
    }

    public FirebaseFirestore initFireStore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        return db;
    }

}