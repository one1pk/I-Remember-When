package com.game.rememberwhen;

// POJO / Model Class for Player Entity
public class Player {
    public String name;
    public int roomId;
    public int score;

    // Default Constructor
    public Player() {
    }

    // Overridden constructor
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
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

