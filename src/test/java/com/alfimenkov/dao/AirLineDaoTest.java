package com.alfimenkov.dao;


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


public class AirLineDaoTest {

    private static ArrayList<Airplane> list;
    private static Database database;


    private static AirLineDao airLineDao;

    @BeforeAll
    public static void init() {

        database = Mockito.mock(Database.class);
        airLineDao = new AirLineDao(database);
        list = new ArrayList<Airplane>(Arrays.asList(new Passenger("WWW", 1000, 7, 670),
                new Passenger("BBB", 100, 8, 330),
                new Cargo("CCC", 200000, 5.6, 4000),
                new Cargo("CCC2", 2000, 4.4, 40800)));

        Airline airline = new Airline();
        airline.setAirplanes(list);
        database.setAirline(airline);
    }


    @Test
    public void testTotalCarryingCapacityDao() {

        //GIVEN
        given(database.getPlanes()).willReturn(list);
        double expected = 44800;

        //WHEN
        double result = airLineDao.getTotalCarryingCapacity();

        //THEN
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testTotalCapacityDao() {
        //GIVEN
        given(database.getPlanes()).willReturn(list);
        double expected = 1000;

        //WHEN
        double result = airLineDao.getTotalCapacity();

        //THEN
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void testPlanesByConsumptionRange() {

        //GIVEN
        double lowerBound = 4;
        double higherBound = 5.7;
        given(database.getPlanes()).willReturn(list);
        ArrayList<Airplane> airplanes = new ArrayList<Airplane>();
        airplanes.add(list.get(2));
        airplanes.add(list.get(3));

        //WHEN
        ArrayList<Airplane> result = airLineDao.getPlanesByConsumptionRange(lowerBound,higherBound);

        //THEN
        Assertions.assertEquals(airplanes,result);
    }

}
