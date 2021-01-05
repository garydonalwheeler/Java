package com.project.test;

import com.project.util.Constants;
import java.io.*;
import java.util.Scanner;

/**
 * @author Gary Wheeler
 * @return nothing
 * DeleteDrivers test class for deleting data from Csv files
 */

public class DeleteDrivers
{
    public static void deletedrivers() throws IOException
    {
        Scanner strInput = new Scanner(System.in);
        Scanner intInput = new Scanner(System.in);


        String teamName, record;


        File tempDB = new File(Constants.DRIVERTEMP_DATAFILE);
        File db = new File(Constants.DRIVER_DATAFILE);


        BufferedReader br = new BufferedReader(new FileReader(db));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));


//        System.out.println("Delete Driver Record\n");
//
//        System.out.println("Enter the Driver ID to confirm: ");
        teamName = strInput.nextLine();


        while ((record = br.readLine()) != null)
        {


            if (record.contains(teamName))
                continue;

            bw.write(record);
            bw.flush();
            bw.newLine();

        }

        br.close();
        bw.close();

        db.delete();

        tempDB.renameTo(db);
    }
}
