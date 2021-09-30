package com.alfimenkov.entity;

public class Passenger extends Airplane {

    private int capacity;

    public Passenger(String type, String name, double distance, double consumption, int capacity) {
        super("passenger", name, distance, consumption);
        this.capacity = capacity;
    }

    public Passenger() {

    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "capacity=" + capacity +
                '}';
    }
}
