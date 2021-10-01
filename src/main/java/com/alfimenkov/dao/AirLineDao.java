package com.alfimenkov.dao;

import com.alfimenkov.entity.Airplane;
import com.alfimenkov.entity.Cargo;
import com.alfimenkov.entity.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AirLineDao implements IAirlineDao {

    static Database db = Database.getInstance();

    @Override
    public int getTotalCapacity() {

        return db.getPlanes().stream()
                .filter(x -> x.getClass() == Passenger.class)
                .reduce(0,
                        (x, y) -> {
                            return x + ((Passenger) y).getCapacity();
                        },
                        (x, y) -> x + y);
    }

    @Override
    public double getTotalCarryingCapacity() {
        return db.getPlanes().stream()
                .filter(x -> x.getClass() == Cargo.class)
                .reduce(0.0,
                        (x, y) -> {
                            return x + ((Cargo) y).getCarryingCapacity();
                        },
                        (x, y) -> x + y);
    }

    @Override
    public List<Airplane> sortPlanesByDistance() {

        return db.getPlanes().stream().
                sorted(Airplane::compareTo).
                collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Airplane> getPlanesByConsumptionRange(double boundA, double boundB) {
        return db.getPlanes().stream().
                filter(x -> x.getConsumption() > boundA && x.getConsumption() < boundB).
                collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<Airplane> getAll() {
        return db.getPlanes();
    }

    @Override
    public void addPlane(Airplane airplane) {

        db.add(airplane);
    }

    @Override
    public void saveToFile() {

        db.save();
    }
}
