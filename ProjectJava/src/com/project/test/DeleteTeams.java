package com.project.test;

import com.project.util.Constants;
import java.io.*;
import java.util.Scanner;

/**
 * @author Gary Wheeler
 * @return 
 * DeleteTeams test class for deleting data from Csv files
 */

public class DeleteTeams
{
    public static void deleteteams() throws IOException
    {
        Scanner strInput = new Scanner(System.in);
        Scanner intInput = new Scanner(System.in);


        String name, record;


        File tempDB = new File(Constants.TEAMTEMP_DATAFILE);
        File db = new File(Constants.TEAM_DATAFILE);


        BufferedReader br = new BufferedReader(new FileReader(db));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));

        name = strInput.nextLine();


        while ((record = br.readLine()) != null)
        {


            if (record.contains(name))
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
