package com.alfimenkov.service;

import com.alfimenkov.dao.AirLineDao;
import com.alfimenkov.dao.Database;
import com.alfimenkov.entity.Airline;
import com.alfimenkov.entity.Airplane;
import com.alfimenkov.entity.Cargo;
import com.alfimenkov.entity.Passenger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.Arrays;


public class ServiceTest {


    private static ArrayList<Airplane> list;
    private static Database database;
    private static  AirLineDao airLineDao;
    private static Service service;



    @BeforeAll
    public static void init() {

        database = Mockito.mock(Database.class);
        airLineDao = Mockito.mock(AirLineDao.class);
        service = new Service(airLineDao);
        list = new ArrayList<Airplane>(Arrays.asList(new Passenger("WWW", 1000, 7, 670),
                new Passenger("BBB", 100, 8, 330),
                new Cargo("CCC", 200000, 5.6, 4000),
                new Cargo("CCC2", 2000, 4.4, 40800)));

        Airline airline = new Airline();
        airline.setAirplanes(list);
        database.setAirline(airline);
    }

   // @AfterEach
    public void reset() {


    }

    @Test
    public void testGetTotalCapacity() {

        //GIVEN

        given(database.getPlanes()).willReturn(list);
        given(airLineDao.getTotalCapacity()).willReturn(1000);
        int expected = airLineDao.getTotalCapacity();

        //WHEN
        int res = service.getTotalCapacity();


        //THEN
        Assertions.assertEquals(expected,res);

    }

    @Test
    public void testGetTotalCarryingCapacity() {

        //GIVEN
        when(database.getPlanes()).thenReturn(list);
        when(airLineDao.getTotalCarryingCapacity()).thenReturn(44800.0);
        double expected = airLineDao.getTotalCarryingCapacity();

        //WHEN
        double result = service.getTotalCarryingCapacity();

        //THEN
        Assertions.assertEquals(expected,result);
    }

    @Test
    void testPlanesByConsumptionRange() {

        //GIVEN
        double lowerBound = 4;
        double higherBound = 5.7;
        ArrayList<Airplane> airplanes = new ArrayList<Airplane>();
        airplanes.add(list.get(2));
        airplanes.add(list.get(3));
        when(database.getPlanes()).thenReturn(list);
        when(airLineDao.getPlanesByConsumptionRange(lowerBound, higherBound))
                .thenReturn(airplanes);
        ArrayList<Airplane> expected = airLineDao.getPlanesByConsumptionRange(lowerBound,higherBound);

        //WHEN
        ArrayList<Airplane> result = service.getPlanesByConsumptionRange(lowerBound, higherBound);

        //THEN
        Assertions.assertEquals(expected,result);
    }
}
