package com.alfimenkov.dao;

import com.alfimenkov.entity.Airplane;
import com.alfimenkov.entity.Cargo;
import com.alfimenkov.entity.Passenger;
import org.jetbrains.annotations.VisibleForTesting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AirLineDao implements IAirlineDao {

    private Database db;
    private static final Logger LOGGER = LoggerFactory.getLogger(AirLineDao.class);

    public AirLineDao() {

        this.db = Database.getInstance();
    }

    @VisibleForTesting
    public AirLineDao (Database database) {
        this.db = database;
    }

    @Override
    public int getTotalCapacity() {

        int result = db.getPlanes().stream()
                .filter(x -> x.getClass() == Passenger.class)
                .reduce(0,
                        (x, y) -> {
                            return x + ((Passenger) y).getCapacity();
                        },
                        (x, y) -> x + y);
        LOGGER.debug("Total  capacity of Airline which has {} planes -- {}", db.getPlanes().size(),result);
        return result;
    }

    @Override
    public double getTotalCarryingCapacity() {

        double result = db.getPlanes().stream()
                .filter(x -> x.getClass() == Cargo.class)
                .reduce(0.0,
                        (x, y) -> {
                            return x + ((Cargo) y).getCarryingCapacity();
                        },
                        (x, y) -> x + y);
        LOGGER.debug("Total carrying capacity of Airline which has {} planes -- {}", db.getPlanes().size(),result);
        return result;
    }

    @Override
    public ArrayList<Airplane> sortPlanesByDistance() {

        return db.getPlanes().stream().
                sorted(Airplane::compareTo).
                collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<Airplane> getPlanesByConsumptionRange(double boundA, double boundB) {

        LOGGER.debug("Get planes by consumption range, lower bound: {}, higher bound {}", boundA, boundB);
        ArrayList planes =  db.getPlanes().stream().
                filter(x -> x.getConsumption() > boundA && x.getConsumption() < boundB).
                collect(Collectors.toCollection(ArrayList::new));
        LOGGER.debug("Returns collection of planes(size - {}): {}", planes.size(), planes.toString());
        return planes;
    }

    @Override
    public List<Airplane> getAll() {
        return db.getPlanes();
    }

    @Override
    public Airplane getPlaneByName(String name) {

        LOGGER.debug("Try to find plane with name {}", name);
        for (Airplane airplane : db.getPlanes()) {

            if(airplane.getName().equals(name)) {

                LOGGER.debug("Returns airplane: {}", airplane);
                return airplane;
            }
            else{

                LOGGER.debug("Returns null");
                return null;
            }
        }
        return null;
    }

    @Override
    public void deletePlane(String name) {

        LOGGER.debug("Deleting plane with name{}", name);
        db.delete(getPlaneByName(name));
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
