package com.project.io;

import com.project.models.*;
import com.project.menu.*;
import com.project.util.Constants;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.*;

/**
 * @author Gary Wheeler
 * FileProcessing class for importing & processing data from csv files
 */

public class FileProcessing
{
    /**
     * Attributes
     */
    private String fileDir;
    private String fileName;
    private String filePath;

    /**
     * Constructors
     */
    public FileProcessing()
    {
        this("teams.csv");
    }

    public FileProcessing(String fileName)
    {
        this(System.getProperty("user.dir"), fileName);
    }

    public FileProcessing(String fileDir, String fileName)
    {
        this.fileDir = fileDir;
        this.fileName = fileName;
        this.setFilePath(this.fileDir + "\\" + this.fileName);
    }

    /**
     * Getters
     * @return getfileDir
     */
    public String getfileDir()
    {
        return this.fileDir;
    }

    public String getFileName()
    {
        return this.fileName;
    }

    public String getFilePath()
    {
        return this.filePath;
    }

    /**
     * Setters
     * @param fileDir
     */
    public void setfileDir(String fileDir)
    {
        this.fileDir = fileDir;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    /**
     * Check if a file exists
     *
     * @param filePath full file path to file
     * @return return true or false if exists
     */
    public boolean fileExists(String filePath)
    {
        File f = new File(filePath);
        if (f.isFile())
            return true;
        System.out.printf("\nFile '%s' does not exist.\n", filePath);
        return false;
    }


    /**
     * Get content from CSV/text file
     *
     * @param file file name
     */
    public static void getFileContent(String file) // Get content from relevant CSV file
    {
        List<Integer> colMaxWidths = new ArrayList<>();
        Scanner scanner = null, dataScanner;
        int rowIdx = 0;

        try // Try & Catch Exception for file not found
        {
            scanner = new Scanner(new File(file));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        while (scanner.hasNextLine())
        {
            dataScanner = new Scanner(scanner.nextLine()).useDelimiter(",");
            List<String> row = new ArrayList<>();
            int colIdx = 0;
            while (dataScanner.hasNext())
            {
                String data = dataScanner.next();
                row.add(data);
                int colWidth = (data.length() > (Constants.MAX_COLUMN_WIDTH - 3)) ? Constants.MAX_COLUMN_WIDTH : data.length();

                if(rowIdx == 0)
                    colMaxWidths.add(colIdx, colWidth);
                else{
                    if(colWidth > colMaxWidths.get(colIdx))
                        colMaxWidths.set(colIdx, colWidth);
                }
                colIdx++;
            }

            if(file == Constants.DRIVER_DATAFILE) //If file found is drivers.csv setup table for Drivers
            {
                if(rowIdx == 0) // If row is 0 then it is a header row so set headers
                    Driver.setHeaders(row);
                else
                    new Driver(row); // If row is not 0 then it is a driver row so fill with driver data
                Driver.setColMaxWidths(colMaxWidths);
            }
            else {                     // If file found is not drivers.csv then setup table for Teams
                if(rowIdx == 0)        //  If row is 0 then it is a header row so set headers
                    Team.setHeaders(row);
                else{
                    // All drivers were added first before teams. Now check which ones drive for this team with lambda filter below.
                    List<Driver> allDrivers = Driver.getDriversList();
                    Team team = new Team(row);

                    List<Driver> teamDriverList = allDrivers.stream() // List drivers as single stream
                            .filter(d -> d.getDriverTeam().equals(team.getTeamName())).collect(Collectors.toList()); // Get the Team that the Driver drives for

                    team.setTeamDrivers(teamDriverList); //
                }
                Team.setColMaxWidths(colMaxWidths);
            }
            rowIdx++;
        }
        scanner.close();
    }


    public static void writeToCsv(String fileName) {
        // Ternary operators (more concise than if/else)
        int rowCount = (fileName == Constants.DRIVER_DATAFILE) ? Driver.getDriversList().size() : Team.getTeamsList().size();
        List<String> headers = (fileName == Constants.DRIVER_DATAFILE) ? Driver.getHeaders() : Team.getHeaders();

        try {
            FileWriter csvWriter = new FileWriter(fileName);
            csvWriter.append(String.join(",", headers));
            csvWriter.append("\n");

            for (int i = 0; i < rowCount; i++) {
                List<String> row = (fileName == Constants.DRIVER_DATAFILE) ? Driver.getDriversList().get(i).getDriverDetailsList() : Team.getTeamsList().get(i).getTeamDetailsList();
                for (int j = 0; j < row.size(); j++)
                    csvWriter.append(row.get(j)).append(",");
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}