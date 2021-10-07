package com.alfimenkov.dao;

import com.alfimenkov.entity.Airplane;

import java.util.ArrayList;
import java.util.List;

public interface IAirlineDao {

     int getTotalCapacity();
     double getTotalCarryingCapacity();
     List<Airplane> sortPlanesByDistance();
     ArrayList<Airplane> getPlanesByConsumptionRange(double boundA, double boundB);
     List<Airplane> getAll();
     void addPlane(Airplane airplane);
     void saveToFile();
}
