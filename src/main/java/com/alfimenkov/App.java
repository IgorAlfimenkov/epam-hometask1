package com.alfimenkov;

import com.alfimenkov.dao.Database;
import com.alfimenkov.entity.Airplane;
import com.alfimenkov.entity.Cargo;
import com.alfimenkov.entity.Passenger;
import com.alfimenkov.service.Service;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

      Database db = new Database("data.json");
      Service service = new Service();
      Scanner in = new Scanner(System.in);
      int choice = 0;
      String input = "";

      while(!input.equals("9")) {

          System.out.println("Show all airplanes in airline -- 1");
          System.out.println("Calculate total capacity of airline -- 2");
          System.out.println("Calculate total carrying capacity of airline -- 3");
          System.out.println("Show planes by fuel consumption range -- 4");
          System.out.println("Sort planes by distance -- 5");
          System.out.println("Add new airplane -- 6");
          System.out.println("Delete airplane -- 7");
          System.out.println("Exit -- 8");

          input = in.next();

          try {
              choice = Integer.parseInt(input);
          } catch (Exception e) {
              System.out.println("Invalid input! Try again.");
          }

          switch (choice){

              case 1:
                  System.out.println(service.getAll());
                  break;
              case 2:
                  System.out.println(service.getTotalCapacity());
                  break;
              case 3:
                  System.out.println(service.getTotalCarryingCapacity());
                  break;
              case 4:
                  System.out.println("Enter lower bound: ");
                  double boundA = in.nextDouble();
                  System.out.println("Enter higher bound: ");
                  double boundB = in.nextDouble();
                  System.out.println(service.getPlanesByConsumptionRange(boundA, boundB));
                  break;
              case 5:
                  System.out.println(service.sortPlanesByDistance());
                  break;
              case 6:

                  System.out.println("Choose type of plane: \n" +
                          "Passenger -- 1\n" +
                          "Cargo -- 2");
                  int type = in.nextInt();

                  System.out.println("Enter name of plane:");
                  in.nextLine();
                  String name = in.nextLine();

                  System.out.println("Enter distance of flight:");
                  Double distance = Double.parseDouble(in.next());

                  System.out.println("Enter fuel consumption of plane:");
                  Double consumption = Double.parseDouble(in.next());

                  if(type == 1){

                      System.out.println("Enter capacity:");
                      int capacity = in.nextInt();
                      Airplane passenger = new Passenger(name, distance, consumption, capacity);
                      service.addPlane(passenger);
                  }
                  else if(type == 2) {

                      System.out.println("Enter carrying capacity:");
                      double carryingCapacity = Double.parseDouble(in.next());
                      Airplane cargo = new Cargo(name, distance, consumption, carryingCapacity);
                      service.addPlane(cargo);
                  }
                  break;
              case 7:
                  System.out.println("Enter name of plane: ");
                  in.nextLine();
                  String planeName = in.nextLine();
                  service.deletePlane(planeName);
                  break;
              case 8:
                  service.saveToFile();
                  input = "9";
                  break;
              default:
                  System.out.println("Invalid input, try again!");
                  break;
              }
          }
      }
    }

