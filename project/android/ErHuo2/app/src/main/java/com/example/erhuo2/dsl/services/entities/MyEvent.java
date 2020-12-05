package com.example.erhuo2.dsl.services.entities;

public class MyEvent {
    private int prePosition;
    private int aftPosition;

    public MyEvent(int prePosition, int aftPosition) {
        this.prePosition = prePosition;
        this.aftPosition = aftPosition;
    }

    public MyEvent() {
    }

    public int getPrePosition() {
        return prePosition;
    }

    public void setPrePosition(int prePosition) {
        this.prePosition = prePosition;
    }

    public int getAftPosition() {
        return aftPosition;
    }

    public void setAftPosition(int aftPosition) {
        this.aftPosition = aftPosition;
    }
}
