package com.project.menu;

import com.project.io.FileProcessing;
import com.project.models.Driver;
import com.project.models.Team;
import com.project.util.Constants;
import java.util.*;
import java.util.stream.Collectors;

public class MenuTeams extends Menu
{

    /**
     * Constructors
     */
    public MenuTeams() {
        super();
    }

    public MenuTeams(String title) {
        super(title);
    }

    public MenuTeams(String title, int headerWidth) {
        super(title, headerWidth);
    }

    public MenuTeams(String title, String file, int headerWidth) {
        super(title, file, headerWidth);
    }

    /**
     * Polymorphism - override Menu body for this subclass to display Teams Summary table
     */
    @Override
    public void displayBody() {
        this.displayBody(Team.getTeamsList().size(), Team.getHeaders(), Team.getColMaxWidths());
        this.displaySubMenu();
    }


    public void displaySubMenu() {
        int selection = 6;
        String repeat;
        Scanner input = new Scanner(System.in);

        do
        {
            System.out.println("\n\nView Team Submenu Options (Y/N)?");
            String strOpt = input.next();

            if(strOpt.equalsIgnoreCase("Y")){

                super.setTitle("F1 Team - Submenu Options");
                super.displayHeader();

                System.out.print("\n");
                System.out.println("1) Create New Team:");
                System.out.println("2) View Team Drivers");
                System.out.println("3) Update Team Name:");
                System.out.println("4) Update Team Points:");
                System.out.println("5) Delete Team:");
                System.out.println("6) Return to Main Menu");
                System.out.println("7) Exit Application");
                System.out.println("\nPlease enter selection:");
                selection = input.nextInt();

                Team team = null;
                int intInput;
                String strInput;

                switch(selection) {
                    case 1:
                        System.out.println("\nEnter New Team Details in the following format:");
                        System.out.println(String.join(", ", Team.getHeaders()));
                        input.nextLine();

                        strInput = input.nextLine();
                        List<String> row = Arrays.stream(strInput.split("\\s*,\\s*")).collect(Collectors.toList());

                        if(row.size() == Team.getHeaders().size()){
                            new Team(row);
                            FileProcessing.writeToCsv(Constants.TEAM_DATAFILE);
                            System.out.println("Team successfully added");
                        }
                        else
                            System.out.printf("\nTeam not created: All %s column values must be entered\n", Team.getHeaders().size());
                        break;
                    case 2:
                        try {
                            System.out.println("\nEnter Team ID to view drivers (e.g 1 = Alfa Romeo, 2 = Ferrari, etc):");
                            intInput = input.nextInt();
                            team = Team.getTeamById(intInput);

                            if(team == null)
                                System.out.println("No Teams match this ID");
                            else{
                                System.out.printf("\nDrivers for Team No.%s (%s):", intInput, team.getTeamName());
                                for (int i = 0; i < team.getTeamDrivers().size(); i++)
                                    System.out.printf("\n%s) %s", i+1, team.getTeamDrivers().get(i).getDriverName());
                                System.out.println();
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter an integer value for Team ID");
                            input.nextLine();
                        }
                        break;
                    case 3:
                        try {
                            System.out.println("\nEnter Team ID to update:");
                            intInput = input.nextInt();
                            team = Team.getTeamById(intInput);

                            if(team == null)
                                System.out.println("No Teams match this ID");
                            else{
                                System.out.printf("\nEnter new Name for %s:\n", team.getTeamName());
                                strInput = input.next();
                                team.setTeamName(strInput);
                                FileProcessing.writeToCsv(Constants.TEAM_DATAFILE);
                                System.out.println("Name successfully updated");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter an integer value for Team ID");
                            input.nextLine();
                        }
                        break;
                    case 4:
                        try {
                            System.out.println("\nEnter Team ID to update:");
                            intInput = input.nextInt();
                            team = Team.getTeamById(intInput);

                            if(team == null)
                                System.out.println("No Teams match this ID");
                            else{
                                System.out.println("\nEnter new Points:");
                                intInput = input.nextInt();
                                team.setPoints(intInput);
                                FileProcessing.writeToCsv(Constants.TEAM_DATAFILE);
                                System.out.println("Points successfully updated");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter an integer value for Team ID");
                            input.nextLine();
                        }
                        break;
                    case 5:
                        try {
                            System.out.println("\nEnter Team ID to delete:");
                            intInput = input.nextInt();
                            List<Team> teams = Team.getTeamsList();
                            boolean matchFound = false;
                            for (int i = 0; i < teams.size(); i++)
                            {
                                if(teams.get(i).getTeamId() == intInput){
                                    String teamName = teams.get(i).getTeamName();
                                    teams.remove(i);
                                    System.out.println(teamName + " deleted");
                                    matchFound = true;
                                }
                            }
                            if(matchFound)
                                FileProcessing.writeToCsv(Constants.TEAM_DATAFILE);
                            else
                                System.out.println("Team ID does not exist. No records deleted.");
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter an integer value for Team ID");
                            input.nextLine();
                        }
                        break;
                    case 6:
                        System.out.println("\nReturning to Main Menu...");
                        super.setTitle(Constants.MAIN_TITLE);
                        super.displayHeader();
                        super.displayBody();
                        break;
                    case 7:
                        System.out.println("\n" + Constants.EXIT_STATEMENT);
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid selection - Please Try Again");
                }
            }

            System.out.println("\nReturn to Main Menu? (Y/N)");
            repeat = input.next();

            if(repeat.equalsIgnoreCase("y")){
                super.setTitle(Constants.MAIN_TITLE);
                super.displayHeader();
                super.displayBody();
            }
            else
                System.out.println("\n" + Constants.EXIT_STATEMENT);

        } while (selection < 1 || selection > 8);

    }

}

