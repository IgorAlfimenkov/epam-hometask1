package com.alfimenkov.service;

import com.alfimenkov.dao.AirLineDao;
import com.alfimenkov.entity.Airplane;

import java.util.List;

public class Service implements IService {

    static AirLineDao airLineDao = new AirLineDao();

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
    public List<Airplane> getPlanesByConsumptionRange(double boundA, double boundB) {
        return airLineDao.getPlanesByConsumptionRange(boundA,boundB);
    }

    @Override
    public List<Airplane> getAll() {
        return airLineDao.getAll();
    }
}
