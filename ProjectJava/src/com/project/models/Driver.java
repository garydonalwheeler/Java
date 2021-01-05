package com.project.models;

import java.util.*;

 /**
  * This is the Driver method. This creates and list all drivers
  * @author Gary Wheeler
  */

public class Driver
{
    /**
     * Attributes
     */
    private int id;
    private String name;
    private Country country;
    private String team;
    private String car;
    private String engine;
    private Tyre tyres;
    private int points;

    private static List<Driver> driversList = new ArrayList<>(); //  list of all driver objects created - all drivers for all teams
    private static List<String> headers = new ArrayList<>();
    private static List<Integer> colMaxWidths = new ArrayList<>();
    private List<Driver> teamDriversList;

    /**
     * Constructors
     * @param cols
     */
    public Driver(List<String> cols)
    {
        this(Integer.parseInt(cols.get(0)), cols.get(1), Country.valueOf(cols.get(2)), cols.get(3), cols.get(4), cols.get(5), Tyre.valueOf(cols.get(6)), Integer.parseInt(cols.get(7)));
    }

    public Driver(int id, String name, Country country, String team, String car, String engine, Tyre tyres, int points)
    {
        this.id = id;
        this.name = name;
        this.country = country;
        this.team = team;
        this.car = car;
        this.engine = engine;
        this.tyres = tyres;
        this.points = points;
        driversList.add(this); // keep track of all objects created
    }

    /**
     * Getters
     * @return getDriverId
     */
    public int getDriverId()
    {
        return this.id;
    }

    public String getDriverName()
    {
        return this.name;
    }

    public Country getDriverCountry()
    {
        return this.country;
    }

    public String getDriverTeam()
    {
        return this.team;
    }

    public String getDriverCar()
    {
        return this.car;
    }

    public String getDriverEngine()
    {
        return this.engine;
    }

    public Tyre getDriverTyres()
    {
        return this.tyres;
    }

    public int getDriverPoints()
    {
        return this.points;
    }

    public static List<Driver> getDriversList()
    {
        return Driver.driversList;
    }

    public static List<String> getHeaders()
    {
        return Driver.headers;
    }

    public static List<Integer> getColMaxWidths()
    {
        return Driver.colMaxWidths;
    }

    public static Driver getDriverById(int id) {
        List<Driver> allDriversList = Driver.driversList;
        Driver matchingDriver = null;

        for (int i = 0; i < allDriversList.size(); i++)
        {
            if(id == allDriversList.get(i).getDriverId()){
                matchingDriver = allDriversList.get(i);
            }
        }
        return matchingDriver;
    }

    // Each object getter stored as a string arraylist
    public List<String> getDriverDetailsList()
    {
        List<String> driverDetailsList = new ArrayList<>(Arrays.asList(
                String.valueOf(this.getDriverId()),
                String.valueOf(this.getDriverName()),
                String.valueOf(this.getDriverCountry()),
                String.valueOf(this.getDriverTeam()),
                String.valueOf(this.getDriverCar()),
                String.valueOf(this.getDriverEngine()),
                String.valueOf(this.getDriverTyres()),
                String.valueOf(this.getDriverPoints())
        ));
        return driverDetailsList;
    }


    /**
     * Setters
     * @param newId
     */
    public void setDriverId(int newId)
    {
        this.id = newId;
    }

    public void setDriverName(String newName)
    {
        this.name = newName;
    }

    public void setDriverCountry(String newCountry)
    {
        this.country = Country.valueOf(newCountry);
    }

    public static void setDriversList(List<Driver> driversList)
    {
        Driver.driversList = driversList;
    }

    public static void setHeaders(List<String> headers)
    {
        Driver.headers = headers;
    }

    public static void setColMaxWidths(List<Integer> colMaxWidths)
    {
        Driver.colMaxWidths = colMaxWidths;
    }

    public List<Driver> getTeamDrivers() {
        return this.teamDriversList;
    }

    public String getDriverTeams() {
        return this.team;
    }

    public void setPoints(int points) {
        this.points = points;
    }



}