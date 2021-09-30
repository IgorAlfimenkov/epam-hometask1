package com.alfimenkov.dao;

import com.alfimenkov.entity.Airline;
import com.alfimenkov.entity.Airplane;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Database {

    public static String filename;
    private Airline airline;
    static Database instance;

    public Database(String filename) {

        this.filename = filename;
        airline = new Airline();
        read();
    }


    public Airline getAirline() {
        return airline;
    }

    public List<Airplane> getPlanes(){

        return airline.getAirplanes();
    }

    public void save() {

        ObjectMapper mapper = new ObjectMapper();

        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        // convert  object to JSON file
        try {
            writer.writeValue(Paths.get(filename).toFile(), airline.getAirplanes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read() {

        ArrayList<Airplane> airplanes;

        try {

            ObjectMapper mapper = new ObjectMapper();

            airplanes = mapper.readValue(Paths.get(this.filename).toFile(), new TypeReference<ArrayList<Airplane>>() {
            });

            airline.setAirplanes(airplanes);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static Database getInstance() {

        if (instance == null) return new Database(filename);
        return instance;
    }

}
