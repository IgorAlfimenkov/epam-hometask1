package com.alfimenkov.service;

import com.alfimenkov.dao.AirLineDao;
import com.alfimenkov.dao.IAirlineDao;
import com.alfimenkov.entity.Airline;
import com.alfimenkov.entity.Airplane;
import com.alfimenkov.entity.Cargo;
import com.alfimenkov.entity.Passenger;
import org.jetbrains.annotations.VisibleForTesting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Service implements IService {

    static IAirlineDao airLineDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(Service.class);

    public Service() {

        airLineDao = new AirLineDao();
    }

    @VisibleForTesting
    public Service(AirLineDao dao) {

        airLineDao = dao;
    }

    @Override
    public int getTotalCapacity() {

        int result = airLineDao.getTotalCapacity();
        LOGGER.info("SERVICE: Total capacity: {}", result);
        return result;
    }

    @Override
    public double getTotalCarryingCapacity() {

        double result = airLineDao.getTotalCarryingCapacity();
        LOGGER.info("SERVICE: Total carrying capacity: {}", result);
        return result;
    }

    @Override
    public List<Airplane> sortPlanesByDistance() {
        return airLineDao.sortPlanesByDistance();
    }

    @Override
    public ArrayList<Airplane> getPlanesByConsumptionRange(double boundA, double boundB) {
        LOGGER.info("SERVICE: Get planes by consumption (bounds: lower bound - {}, higher bound - {}): ", boundA, boundB);
        ArrayList<Airplane> airplanes = airLineDao.getPlanesByConsumptionRange(boundA,boundB);
        return airplanes;
    }

    @Override
    public List<Airplane> getAll() {
        return airLineDao.getAll();
    }

    @Override
    public void deletePlane(String name) {

        airLineDao.deletePlane(name);
    }

    @Override
    public void addPlane(Airplane airplane) {
        airLineDao.addPlane(airplane);
    }

    @Override
    public void saveToFile() {
        airLineDao.saveToFile();
    }

}
