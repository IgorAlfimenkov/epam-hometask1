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

public class Loader {

    protected static Airline readFromJson(String filename) {

        Airline airline = new Airline();
        ArrayList<Airplane> airplanes;

        try {

            ObjectMapper mapper = new ObjectMapper();

            airplanes = mapper.readValue(Paths.get(filename).toFile(), new TypeReference<ArrayList<Airplane>>() {
            });

            airline.setAirplanes(airplanes);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return airline;
    }

    protected static void writeToJson (String filename, Airline airline) {

        ObjectMapper mapper = new ObjectMapper();

        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        try {
            writer.writeValue(Paths.get(filename).toFile(), airline.getAirplanes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
