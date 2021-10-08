package com.alfimenkov.dao;

import com.alfimenkov.entity.Airline;
import com.alfimenkov.entity.Airplane;

import java.util.List;

public class Database {

    public static String filename;
    private Airline airline;
    static Database instance;

    public Database() {

        this.filename = "data.json";
        airline = JsonLoader.readFromJson(filename);
    }

    public Database(String filename) {

        this.filename = filename;
        airline = JsonLoader.readFromJson(filename);
    }


    public Airline getAirline() {
        return airline;
    }

    public List<Airplane> getPlanes(){

        return airline.getAirplanes();
    }

    protected void save() {

        JsonLoader.writeToJson(filename, airline);
    }

    protected static Database getInstance() {

        if (instance == null) return new Database(filename);
        return instance;
    }

    protected void add(Airplane airplane) {

        getPlanes().add(airplane);
    }

}
