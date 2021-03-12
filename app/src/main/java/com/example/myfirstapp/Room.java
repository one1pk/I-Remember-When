package com.example.myfirstapp;

import java.security.SecureRandom;

public class Room {
    private final int roomId;
    private int hostPlayerId;

    public Room() {
        SecureRandom randomPin = new SecureRandom();
        roomId = randomPin.nextInt(8999) + 1000;
    }

    public Room(int roomId) {
        //TODO: Why are we incrementing this?
        roomId = roomId >= 9999 ? 1000 : roomId + 1;
        this.roomId = roomId;
    }

    public int getRoomId() {
        return this.roomId;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Room && this.roomId == ((Room) other).getRoomId();
    }
}
