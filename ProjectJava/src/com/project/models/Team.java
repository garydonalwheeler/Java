package com.project.models;

import java.util.*;

 /**
  * This is the Team method. This creates and list all teams
  * @author Gary Wheeler
  */

public class Team {

    /**
     * Attributes
     */
    private int id;
    private String teamName;
    private String engine;
    private Country teamCountry;
    private int racesEntered;
    private int racesStarted;
    private int driversCount;
    private int totalEntries;
    private int wins;
    private double points;
    private int poles;
    private int fastestLap;
    private int podiums;

    private List<Driver> teamDriversList;  // List of driver objects for this team object only
    private static List<Team> teamsList = new ArrayList<>(); // List of all team objects created from this class
    private static List<String> headers = new ArrayList<>();
    private static List<Integer> colMaxWidths = new ArrayList<>();


    /**
     * Constructors
     * @param cols
     */
    public Team(List<String> cols) {
        this(
                Integer.parseInt(cols.get(0)),
                cols.get(1),
                cols.get(2),
                Country.valueOf(cols.get(3)),
                Integer.parseInt(cols.get(4)),
                Integer.parseInt(cols.get(5)),
                Integer.parseInt(cols.get(6)),
                Integer.parseInt(cols.get(7)),
                Integer.parseInt(cols.get(8)),
                Double.parseDouble(cols.get(9)), // setting each column to either string or integer or double
                Integer.parseInt(cols.get(10)),
                Integer.parseInt(cols.get(11)),
                Integer.parseInt(cols.get(12))
        );
    }


    public Team(int id, String teamName, String engine, Country teamCountry, int racesEntered, int racesStarted,
                int driversCount, int totalEntries, int wins, double points, int poles, int fastestLap, int podiums)
    {
        this.id = id;
        this.teamName = teamName;
        this.engine = engine;
        this.teamCountry = teamCountry;
        this.racesEntered = racesEntered;
        this.racesStarted = racesStarted;
        this.driversCount = driversCount;
        this.totalEntries = totalEntries;
        this.wins = wins;
        this.points = points;
        this.poles = poles;
        this.fastestLap = fastestLap;
        this.podiums = podiums;
        teamsList.add(this); // keep track of all objects created
    }

    /**
     * Getters
     * @return getTeamId
     */
    public int getTeamId() {
        return this.id;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public String getTeamEngine() {
        return this.engine;
    }

    public Country getTeamCountry() {
        return this.teamCountry;
    }

    public int getRacesEntered() {
        return this.racesEntered;
    }

    public int getRacesStarted() {
        return this.racesStarted;
    }

    public int getDriversCount() {
        return this.driversCount;
    }

    public int getTotalEntries() {
        return this.totalEntries;
    }

    public int getWins() {
        return this.wins;
    }

    public double getPoints() {
        return this.points;
    }

    public int getPoles() {
        return this.poles;
    }

    public int getFastestLap() {
        return this.fastestLap;
    }

    public int getPodiums() {
        return this.podiums;
    }

    public static List<Team> getTeamsList() {
        return Team.teamsList;
    }

    public List<Driver> getTeamDrivers() {
        return this.teamDriversList;
    }

    public static List<String> getHeaders() {
        return Team.headers;
    }

    public static List<Integer> getColMaxWidths() {
        return Team.colMaxWidths;
    }


    public static Team getTeamById(int id) {
        List<Team> allTeamsList = Team.teamsList;
        Team matchingTeam = null;

        for (int i = 0; i < allTeamsList.size(); i++)
        {
            if(id == allTeamsList.get(i).getTeamId()){
                matchingTeam = allTeamsList.get(i);
            }
        }
        return matchingTeam;
    }

    // Each object getter stored as a string arraylist
    public List<String> getTeamDetailsList() {
        List<String> teamDetailsList = new ArrayList<>(Arrays.asList(
                String.valueOf(this.getTeamId()),
                String.valueOf(this.getTeamName()),
                String.valueOf(this.getTeamEngine()),
                String.valueOf(this.getTeamCountry()),
                String.valueOf(this.getRacesEntered()),
                String.valueOf(this.getRacesStarted()),
                String.valueOf(this.getDriversCount()),
                String.valueOf(this.getTotalEntries()),
                String.valueOf(this.getWins()),
                String.valueOf(this.getPoints()),
                String.valueOf(this.getPoles()),
                String.valueOf(this.getFastestLap()),
                String.valueOf(this.getPodiums())
        ));
        return teamDetailsList;
    }

    /**
     * Setters
     * @param newId
     */
    public void setTeamId(int newId) {
        this.id = newId;
    }

    public void setTeamName(String newName) {
        this.teamName = newName;
    }

    public void setTeamEngine(String newEngine) {
        this.engine = newEngine;
    }

    public void setTeamCountry(String newCountry) {
        this.teamCountry = Country.valueOf(newCountry);
    }

    public void setRacesEntered(int racesEntered) {
        this.racesEntered = racesEntered;
    }

    public void setRacesStarted(int racesStarted) {
        this.racesStarted = racesStarted;
    }

    public void setDriversCount(int driversCount) {
        this.driversCount = driversCount;
    }

    public void setTotalEntries(int totalEntries) {
        this.totalEntries = totalEntries;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setPoles(int poles) {
        this.poles = poles;
    }

    public void setFastestLap(int fastestLap) {
        this.fastestLap = fastestLap;
    }

    public void setPodiums(int podiums) {
        this.podiums = podiums;
    }

    public static void setTeamsList(List<Team> teamsList) {
        Team.teamsList = teamsList;
    }

    public void setTeamDrivers(List<Driver> driverList) {
        this.teamDriversList = driverList;
    }

    public static void setHeaders(List<String> headers) {
        Team.headers = headers;
    }

    public static void setColMaxWidths(List<Integer> colMaxWidths) {
        Team.colMaxWidths = colMaxWidths;
    }

}




