
// package com.project.test;

// import com.project.menu.Menu;
// import com.project.io.FileProcessing;
// import com.project.models.Driver;
// import com.project.models.Team;
// import com.project.util.Constants;
// import java.io.IOException;
// import java.util.*;
// import java.util.stream.Collectors;
// import java.io.*;

// public class CSVDrivers
// {

//     public static void main(String[] args) throws IOException
//     {
//         Scanner scanner = new Scanner(new File("data/csv/Drivers.csv"));
//         Scanner dataScanner = null;
//         int index = 0;
//         List<com.netbeans.Drivers> driversList = new ArrayList<>(); //Pull data into ArrayList & list in numerical order with ID

//         while (scanner.hasNextLine())
//         {
//             dataScanner = new Scanner(scanner.nextLine());
//             Scanner scanners = dataScanner.useDelimiter(",");
//             Drivers drivers;
//             drivers = new Drivers();

//             while (dataScanner.hasNext())
//             {
//                 String data = dataScanner.next();
//                 if (index == 0)
//                     drivers.setId(Integer.parseInt(data));
//                 else if (index == 1)
//                     drivers.setName(data);
//                 else if (index == 2)
//                     drivers.setNationality(data);
//                 else if (index == 3)
//                     drivers.setTeam(data);
//                 else
//                     System.out.println("Invalid data::" + data);
//                 index++;
//             }
//             index = 0;
//             driversList.add(drivers);
//         }

//         scanner.close();

//         System.out.println(driversList);

//     }

// }
