/* ManageNewRoomActivity acts as a game room lobby. Host stays while player joins
 Player list gets updated while user joins using Pub-Sub events on Firebase*/


package com.game.rememberwhen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManageNewRoomActivity extends AppCompatActivity {
    Player player;
    Room room;
    List playerList = new ArrayList<Player>();
    TextView playersListText;
    RecyclerView players_list_view; // Player details view dynamic creation using simple player_list_item.xml

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_new_room);
        Bundle b = getIntent().getExtras();

        //list of players
        players_list_view = (RecyclerView) findViewById(R.id.playersListView);
        players_list_view.setLayoutManager(new LinearLayoutManager(this));
        players_list_view.addItemDecoration(new DividerItemDecoration(players_list_view.getContext(), DividerItemDecoration.VERTICAL));
        final PlayersAdapter adapter = new PlayersAdapter(playerList);
        players_list_view.setAdapter(adapter);
        if (b.get("player") == null) {
            // For new player being transferred from JoinRoomActivity
            if (b.get("users") != null) {
                room = new Room(Integer.parseInt(b.get("roomId").toString()), false);
                FirebaseFirestore fStore = FirebaseFirestore.getInstance();
                CollectionReference collection = fStore.collection("/rooms");
                collection.document(String.valueOf(b.get("roomId"))).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    //AddSnapShotListner creats Publisher-Subscriber connection to given /rooms/ROOMID path and updates when new data inserted
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Toast.makeText(ManageNewRoomActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        if (value.exists()) {
                            final HashMap<String, Object> roomData = (HashMap<String, Object>) value.getData();
                            System.out.println("+++Value " + roomData.values());
                            ArrayList<Player> playerArrayList = (ArrayList<Player>) value.toObject(PlayerDocument.class).users;
                            // Converts the Firestore data into arrayList
                            playerList.addAll(playerArrayList);
                            adapter.notifyDataSetChanged(); // By Default adapters work on assigned dataset so when we query firebase it takes some time so we have to tell adapter to update and display new data
                            playersListText = findViewById(R.id.playersList);
                            if (playerList.size() == 6) {
                                playersListText.setText("Players are ready: ");
                            } else {
                                playersListText.setText("Players Being Added: ");
                            }

                        }

                    }
                });

            }
        } else {
            player = new Gson().fromJson(b.get("player").toString(), Player.class);
            room = new Room(Integer.parseInt(b.get("roomId").toString()), false);
        }

        adapter.notifyDataSetChanged();
        // Get the intent that started this activity
        Intent intent = getIntent();

        // Create room and return generated code
//        Room newRoom = new Room();
//        int generatedRoomID = newRoom.getRoomId();

        // Cast RoomID to string and send to screen
        try {
            String roomCode = String.valueOf(room.getRoomId());
            TextView displayRoomID = findViewById(R.id.textViewDisplayRoomID);
            displayRoomID.setText(roomCode);
        } catch (Exception e) {
            e.printStackTrace();
            TextView displayRoomID = findViewById(R.id.textViewDisplayRoomID);
            displayRoomID.setText("roomCode not Found");
        }

    }

    // functions for buttons
    public void readyUp(View view) {
        Toast.makeText(getApplicationContext(), "Room not Ready (TODO)", Toast.LENGTH_SHORT).show();
        //TODO go to game page
    }
    public void openRules(View view) {
        Intent intent = new Intent(this, RulesActivity.class);
        startActivity(intent);
    }

    // Adapters are used to bind dynamic list of data with a static re-usable List or any kind of Custom List views such as player_list_item.xml into
    // Recycler or other view. Requires View Holder,
    class PlayersAdapter extends RecyclerView.Adapter<PlayersViewHolder> {
        private final List<Player> users;

        public PlayersAdapter(List<Player> playerList) {
            super();
            this.users = playerList;
        }

        @NonNull
        @Override
        public PlayersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PlayersViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PlayersViewHolder holder, int position) {
            holder.bind(this.users.get(position));
        }

        @Override
        public int getItemCount() {
            return this.users.size();
        }
    }

    class PlayersViewHolder extends RecyclerView.ViewHolder {
        private final TextView playerName;
        private final TextView playerScore;
        private ImageView playerAvatar;

        public PlayersViewHolder(ViewGroup container) {
            super(LayoutInflater.from(ManageNewRoomActivity.this).inflate(R.layout.player_list_item, container, false));
            playerName = (TextView) itemView.findViewById(R.id.playerNameText);
            playerScore = (TextView) itemView.findViewById(R.id.PlayerScoreText);
//            playerAvatar =(ImageView)itemView.findViewById(R.id.playerImageView);

        }

        public void bind(Player playerBinder) {
            playerName.setText(playerBinder.getName());
            playerScore.setText("Score: " + playerBinder.getScore());
        }
    }
}
