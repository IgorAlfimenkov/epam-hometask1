package com.alfimenkov.entity;

import java.util.ArrayList;
import java.util.Objects;

public class Airline {

    private ArrayList <Airplane> airplanes;

    public Airline(ArrayList<Airplane> airplanes) {
        this.airplanes = airplanes;
    }

    public Airline() {

    }

    public ArrayList<Airplane> getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(ArrayList<Airplane> airplanes) {
        this.airplanes = airplanes;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "airplanes=" + airplanes +
                '}';
    }
}
