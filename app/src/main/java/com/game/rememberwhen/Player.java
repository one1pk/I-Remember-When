package com.game.rememberwhen;

// POJO / Model Class for Player Entity
public class Player {
    private String name;
    private int roomId;
    private int score;

    // Default Constructor
    public Player() {
    }

    // Overridden constructor
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public boolean equals(Player p1) {
        if((name == p1.getName()) && (roomId == p1.getRoomId()) && (score == p1.getScore())) {
            return true;
        }
        else {
            return false;
        }
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

