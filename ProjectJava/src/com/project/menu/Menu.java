package com.project.menu;

import com.project.io.*;
import com.project.models.*;
import com.project.util.Constants;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author Gary Wheeler
 * This class represents a Menu that is being used in this program
 * This Menu can be used in any class within the program
 * It implements MenuInterface and has to use the methods within that class
 */

public class Menu implements MenuInterface
{
    /**
     * Attributes
     * Encapsulation -> private fields/attributes are only accessible by getters (accessors) and setters (mutators)
     */
    private String title;
    private String file;
    private int headerWidth;
    private List<Integer> columnWidths;
    private List<String> headings;

    /**
     * Constructors
     */
    public Menu() {
        this("", "",0);
    }

    public Menu(String title)  {
        this(title, "", 0);
    }

    public Menu(String title, int headerWidth)  {
        this(title, "", headerWidth);
    }

    public Menu(String title, String file, int headerWidth)  {
        this.title = title;
        this.file = file;
        this.headerWidth = headerWidth;
        this.initMenu();
    }

    /**
     * Getters
     * @return getTitle
     */
    public String getTitle() {
        return this.title;
    }

    public int getHeaderWidth() {
        return this.headerWidth;
    }

    /**
     * Setters
     * @param newTitle
     */
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public void setHeaderWidth(int newHeaderWidth) {
        this.headerWidth = newHeaderWidth;
    }

    /**
     * This static method loads the CSV files for use within this class
     */

    public static void loadCSV() // Static, only available to this class
    {
        FileProcessing.getFileContent(Constants.DRIVER_DATAFILE);
        FileProcessing.getFileContent(Constants.TEAM_DATAFILE);
    }

    /**
     * This method, initMenu, initialises the Menu display for use within the program
     * @return Nothing.
     */

    @Override
    public void initMenu() 
    {
        this.displayHeader();
        this.displayBody();
    }

    /**
     * This method, displayHeader, sets up the header display for use within the program
     */

    @Override
    public void displayHeader()
    {
        StringBuilder sb = new StringBuilder(Constants.NEWLINE);  // sb = new StringBuilder object with a return
        int lPadding = ((this.headerWidth - this.title.length()) / 2) - 2; // LHS padding is header width - title length divided by 2 minus 2 
        int rPadding = this.headerWidth - this.title.length() - lPadding - 2;// RHS padding is LHS - 2

        for (int i = 0; i < this.headerWidth; i++) sb.append(Constants.MENU_HEADER_BORDER); // Print header LHS Stars
        sb.append("\n").append(Constants.MENU_HEADER_BORDER);
        for (int i = 0; i < lPadding; i++) sb.append(" "); // Create White Space
        sb.append(this.title);
        for (int i = 0; i < rPadding; i++) sb.append(" "); // Create White Space
        sb.append(Constants.MENU_HEADER_BORDER).append("\n");
        for (int i = 0; i < this.headerWidth; i++) sb.append(Constants.MENU_HEADER_BORDER); // Print header RHS Stars

        System.out.println(sb);
    }

    /**
     * This method, displayBody, sets up the body display for use within the program
     * @return Nothing.
     */

    @Override
    public void displayBody() {
        int selection = 0;
        String repeat;
        Scanner input = new Scanner(System.in);

        do
        {
            System.out.println("\n1) Show F1 Teams Summary:");
            System.out.println("2) Show F1 Drivers Summary");
            System.out.println("3) Exit Application");
            System.out.println("\nPlease enter selection:");
            selection = input.nextInt();

            switch(selection) {
                case 1:
                    new MenuTeams("F1 Teams Summary", Constants.TEAM_DATAFILE, 153);
                    break;
                case 2:
                    new MenuDrivers("F1 Drivers Summary", Constants.DRIVER_DATAFILE,107);
                    break;
                case 3:
                    System.out.println("\nApplication has been exited");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid selection - Please Try Again");
            }
        } while (selection < 1 || selection > 3);
    }

    /**
     * Overload displayBody method signature to allow subclasses display tables
     */

    @Override
    public void displayBody(int listSize, List<String> headers, List<Integer> colMaxWidths){
        this.printRows(headers, colMaxWidths, true, true);
        for (int i = 0; i < listSize; i++)
        {
            List<String> rowList;
            if(this.getClass().getSimpleName().equalsIgnoreCase("MenuDrivers"))
                rowList = Driver.getDriversList().get(i).getDriverDetailsList();
            else
                rowList = Team.getTeamsList().get(i).getTeamDetailsList();
            this.printRows(rowList, colMaxWidths, false, i == listSize - 1 ? true : false);
        }
    }

    /**
     * This method, printRows, sets up cell padding, ellipsis, table/border separators etc for use within the program
     * @return Nothing.
     */

    @Override
    public void printRows(List<String> list, List<Integer> colMaxWidths, boolean topBorder, boolean bottomBorder){
        StringBuilder sbRow = new StringBuilder();

        for (int i = 0; i < list.size(); i++){
            String cellVal = list.get(i);

            if(cellVal.length() > (Constants.MAX_COLUMN_WIDTH - 3)){
                cellVal = cellVal.substring(0,(Constants.MAX_COLUMN_WIDTH - 3)) + "...";
            }

            int padding = colMaxWidths.get(i) - cellVal.length();
            sbRow.append(Constants.TABLE_BODY_SEPARATOR).append(Constants.TABLE_BODY_PADDING);

            if(Pattern.matches("([0-9]*)", cellVal) || Pattern.matches("([0-9]*)\\.([0-9]*)", cellVal))
            {
                for (int j = 0; j <= padding; j++) sbRow.append(Constants.TABLE_BODY_PADDING);
                sbRow.append(cellVal);
            }else{
                sbRow.append(cellVal);
                for (int j = 0; j <= padding; j++) sbRow.append(Constants.TABLE_BODY_PADDING);
            }
        }
        sbRow.append(Constants.TABLE_BODY_SEPARATOR);
        String borderFormat = sbRow.toString()
                .replaceAll("[^" + Constants.TABLE_BODY_SEPARATOR + "]", Constants.TABLE_HEADER_BORDER)
                .replaceAll("[|]", Constants.TABLE_HEADER_SEPARATOR);

        if(topBorder) System.out.printf("%s", borderFormat);
        System.out.printf("\n%s", sbRow);
        if(bottomBorder) System.out.printf("\n%s", borderFormat);
    }

}
