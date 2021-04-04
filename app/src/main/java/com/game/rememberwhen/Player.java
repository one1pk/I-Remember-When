package com.game.rememberwhen;

// POJO / Model Class for Player Entity
public class Player {
    private String name;
    private int roomId;
    private int score;
    private String status;

    // Default Constructor
    public Player() {
    }

    // Overridden constructor
    public Player(String name, int score, String status) {
        this.name = name;
        this.score = score;
        this.status = status;
    }

    public boolean equals(Player p1) {
        return (name.equals(p1.getName())) && (roomId == p1.getRoomId()) && (score == p1.getScore());
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

    public void setStatus(String status) {
        this.status=status;
    }

    public String getStatus() {
        return this.status;
    }

}

