package com.project.menu;

import com.project.io.FileProcessing;
import com.project.models.Driver;
import com.project.models.Team;
import com.project.util.Constants;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Gary Wheeler
 * Represents a Menu for Drivers (MenuDrivers) to be created, viewed, updated & deleted.
 * MenuTeams class extends the functionality of the Menu class
 * 
 */

public class MenuDrivers extends Menu
{

    /**
     * Constructors
     */
    public MenuDrivers() {
        super();
    }

    public MenuDrivers(String title)  {
        super(title);
    }

    public MenuDrivers(String title, int headerWidth)  {
        super(title, headerWidth);
    }

    public MenuDrivers(String title, String file, int headerWidth)  {
        super(title, file, headerWidth);;
    }

    /**
     * Polymorphism - override Menu body for this subclass to display driver details table
     */
    @Override
    public void displayBody() {
        this.displayBody(Driver.getDriversList().size(), Driver.getHeaders(), Driver.getColMaxWidths());
        this.displaySubMenu();
    }

   /**
     * Create SubMenu to allow CRUD operations to be carried out on data
     */

    public void displaySubMenu() {
        int selection = 6;
        String repeat;
        Scanner input = new Scanner(System.in);

        do
        {
            System.out.println("\n\nView Driver Submenu Options (Y/N)?");
            String strOpt = input.next();

            if(strOpt.equalsIgnoreCase("Y")){

                super.setTitle("F1 Driver - Submenu Options"); // Inherits setTitle from Superclass
                super.displayHeader();

                System.out.print("\n");
                System.out.println("1) Create New Driver:");
                System.out.println("2) View Drivers Team");
                System.out.println("3) Update Driver Name:");
                System.out.println("4) Update Driver Points:");
                System.out.println("5) Delete Driver:");
                System.out.println("6) Return to Main Menu");
                System.out.println("7) Exit Application");
                System.out.println("\nPlease enter selection:");
                selection = input.nextInt();

                Driver driver = null;
                int intInput;
                String strInput;

                switch(selection) {
                    case 1:
                        System.out.println("\nEnter New Driver Details in the following format:");
                        System.out.println(String.join(", ", Driver.getHeaders()));
                        input.nextLine();
                        strInput = input.nextLine();
                        List<String> row = Arrays.stream(strInput.split("\\s*,\\s*")).collect(Collectors.toList());

                        if(row.size() == Driver.getHeaders().size()){
                            new Driver(row);
                            FileProcessing.writeToCsv(Constants.DRIVER_DATAFILE);
                            System.out.println("Driver successfully added");
                        }
                        else
                            System.out.printf("\nDriver not created: All %s column values must be entered", Driver.getHeaders().size());
                        break;
                    case 2:
                        try {
                            System.out.println("\nEnter Driver ID to view team (e.g 1 = Lewis Hamilton, 2 = Valtteri Bottas, etc):");
                            intInput = input.nextInt();
                            driver = Driver.getDriverById(intInput);
                            if(driver == null)
                                System.out.println("No Drivers match this ID");
                            else{
                                System.out.printf("\nTeam for Driver No.%s (%s):\n", intInput, driver.getDriverName());
                                System.out.printf("- %s\n", driver.getDriverTeam());
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter an integer value for Driver ID");
                            input.nextLine();
                        }
                        break;
                    case 3:
                        try {
                            System.out.println("\nEnter Driver ID to update:");
                            intInput = input.nextInt();
                            driver = Driver.getDriverById(intInput);
                            if(driver == null)
                                System.out.println("No Drivers match this ID");
                            else{
                                System.out.printf("\nEnter new Name for %s:\n", driver.getDriverName());
                                strInput = input.next();
                                driver.setDriverName(strInput);
                                FileProcessing.writeToCsv(Constants.DRIVER_DATAFILE);
                                System.out.println("Name successfully updated");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter an integer value for Driver ID");
                            input.nextLine();
                        }
                        break;
                    case 4:
                        try {
                            System.out.println("\nEnter Driver ID to update:");
                            intInput = input.nextInt();
                            driver = Driver.getDriverById(intInput);
                            if(driver == null)
                                System.out.println("No Drivers match this ID");
                            else{
                                System.out.println("\nEnter new Points:");
                                intInput = input.nextInt();
                                driver.setPoints(intInput);
                                FileProcessing.writeToCsv(Constants.DRIVER_DATAFILE);
                                System.out.println("Points successfully updated");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter an integer value for Driver ID");
                            input.nextLine();
                        }
                        break;
                    case 5:
                        try {
                            System.out.println("\nEnter Driver ID to delete:");
                            intInput = input.nextInt();
                            List<Driver> drivers = Driver.getDriversList();
                            boolean matchFound = false;
                            for (int i = 0; i < drivers.size(); i++)
                            {
                                if(drivers.get(i).getDriverId() == intInput){
                                    String name = drivers.get(i).getDriverName();
                                    drivers.remove(i);
                                    System.out.println(name + " deleted");
                                    matchFound = true;
                                }
                            }
                            if(matchFound)
                                FileProcessing.writeToCsv(Constants.DRIVER_DATAFILE);
                            else
                                System.out.println("Driver ID does not exist. No records deleted.");
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter an integer value for Driver ID");
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
