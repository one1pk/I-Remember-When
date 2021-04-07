package com.game.rememberwhen;

// POJO / Model Class for Player Entity
public class Player implements Comparable<Player> {
    private String name;
    private int roomId;
    private int score;
    private int scoreDif;
    private String status;

    // Default Constructor
    public Player() {
    }

    // Overridden constructor
    public Player(String name, int score,int scoreDif, String status) {
        this.name = name;
        this.score = score;
        this.scoreDif=scoreDif;
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

    public int getScoreDif() {
        return scoreDif;
    }

    public void setScoreDif(int scoreDif) {
        this.scoreDif = scoreDif;
    }

    public void setStatus(String status) {
        this.status=status;
    }

    public String getStatus() {
        return this.status;
    }

    @Override
    public String toString(){
      if(scoreDif>0)
                return "name: "+ name+ " Score:"+score+ " get: "+scoreDif;
       else
                return "name: "+ name+ " Score:"+score+ " los: "+ -1* scoreDif;

    }
    @Override
    public int compareTo(Player o) {
        return ( this.getScore()< o.getScore() ? -1 :
                (this.getScore()==  o.getScore() ? 0 : 1));
    }
}

