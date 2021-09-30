package com.alfimenkov.entity;

public class Cargo extends Airplane {

    private double carryingCapacity;

    public Cargo(String type, String name, double distance, double consumption, double carryingCapacity) {
        super(type, name, distance, consumption);
        this.carryingCapacity = carryingCapacity;
    }

    public Cargo() {

    }

    public double getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(double carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "carryingCapacity=" + carryingCapacity +
                '}';
    }
}
