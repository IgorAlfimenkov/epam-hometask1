package com.alfimenkov.entity;

public abstract class Airplane implements Comparable<Airplane>{

    private String type;
    private String name;
    private double distance;
    private double consumption;

    public Airplane() {

    }

    public Airplane(String type, String name, double distance, double consumption) {
        this.type = type;
        this.name = name;
        this.distance = distance;
        this.consumption = consumption;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    @Override
    public String toString() {
        return "entity.Airplane{" +
                "name='" + name + '\'' +
                ", distance=" + distance +
                ", consumption=" + consumption +
                '}';
    }

    @Override
    public int compareTo(Airplane airplane) {
        if (this.distance > airplane.distance) return 1;

        else if (this.distance < airplane.distance) return -1;

        else return 0;

    }
}
