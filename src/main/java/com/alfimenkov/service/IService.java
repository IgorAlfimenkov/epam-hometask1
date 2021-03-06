package com.alfimenkov.service;

import com.alfimenkov.entity.Airplane;

import java.util.List;

public interface IService {

    int getTotalCapacity();
    double getTotalCarryingCapacity();
    List<Airplane> sortPlanesByDistance();
    List<Airplane> getPlanesByConsumptionRange(double boundA, double boundB);
    List<Airplane> getAll();
    void deletePlane(String name);
    void addPlane(Airplane airplane);
    void saveToFile();
}
