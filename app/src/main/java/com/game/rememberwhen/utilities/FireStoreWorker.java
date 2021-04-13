// Class to remove the player from the room and update FireStore
package com.game.rememberwhen.utilities;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.game.rememberwhen.Player;
import com.game.rememberwhen.PlayerDocument;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class FireStoreWorker {
    FirebaseFirestore instance = null;

    public FireStoreWorker() {
    }

    public FirebaseFirestore init() {
        if (this.instance == null) {
            this.instance = FirebaseFirestore.getInstance();
        }
        return this.instance;
    }

    public void playerQuit(String roomID, Player player) {
        init()
                .collection("/rooms")
                .document(roomID)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
              //      @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        HashMap<String, Object> data = (HashMap<String, Object>) documentSnapshot.getData();
                        ArrayList<Player> playerList = (ArrayList<Player>) documentSnapshot.toObject(PlayerDocument.class).users;
                        System.out.println(playerList.size());
                        int indexToRemove = 0;
                        for (Player p : playerList) {
                            if (p.getToken().equals(player.getToken())) {
                                System.out.println("REMOVE PLAYER");
                                System.out.println(player.toString());
                                indexToRemove = playerList.indexOf(p);
                            }
                        }
                        playerList.remove(indexToRemove);
                        data.clear();
                        data.put("users", playerList);
                        System.out.println(playerList.size());
                        init()
                                .collection("/rooms")
                                .document(roomID)
                                .set(data)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                    // TODO: SEND FCM MESSAGE to Notify other Players
                                        //FINISH THIS ACTIVITY;

                                    }
                                });

                    }
                });

    }
}
