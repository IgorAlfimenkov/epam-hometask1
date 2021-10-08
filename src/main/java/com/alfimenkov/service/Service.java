package com.alfimenkov.service;

import com.alfimenkov.dao.AirLineDao;
import com.alfimenkov.dao.IAirlineDao;
import com.alfimenkov.entity.Airline;
import com.alfimenkov.entity.Airplane;
import com.alfimenkov.entity.Cargo;
import com.alfimenkov.entity.Passenger;
import org.jetbrains.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;

public class Service implements IService {

    static IAirlineDao airLineDao;

    public Service() {

        airLineDao = new AirLineDao();
    }

    @VisibleForTesting
    public Service(AirLineDao dao) {

        airLineDao = dao;
    }

    @Override
    public int getTotalCapacity() {
        return airLineDao.getTotalCapacity();
    }

    @Override
    public double getTotalCarryingCapacity() {
        return airLineDao.getTotalCarryingCapacity();
    }

    @Override
    public List<Airplane> sortPlanesByDistance() {
        return airLineDao.sortPlanesByDistance();
    }

    @Override
    public ArrayList<Airplane> getPlanesByConsumptionRange(double boundA, double boundB) {
        return airLineDao.getPlanesByConsumptionRange(boundA,boundB);
    }

    @Override
    public List<Airplane> getAll() {
        return airLineDao.getAll();
    }

    @Override
    public void addPlane(Airplane airplane) {
        airLineDao.addPlane(airplane);
    }

    @Override
    public void saveToFile() {
        airLineDao.saveToFile();
    }

    public int getTotalCapacity(Airline airline) {
        return airline.getAirplanes().stream()
                .filter(x -> x.getClass() == Passenger.class)
                .reduce(0,
                        (x, y) -> {
                            return x + ((Passenger) y).getCapacity();
                        },
                        (x, y) -> x + y);
    }

    public double getTotalCarryingCapacity(Airline airline) {
        return airline.getAirplanes().stream()
                .filter(x -> x.getClass() == Cargo.class)
                .reduce(0.0,
                        (x, y) -> {
                            return x + ((Cargo) y).getCarryingCapacity();
                        },
                        (x, y) -> x + y);
    }
}
