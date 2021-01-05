
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


// /**
//  * @author Gary Wheeler
//  * Test Class for testing functionality of program 
//  */
// public class IOTester {
//     public static void main(String[] args) {
   
//        try {
//             System.out.println("\nEnter Driver ID to view team (e.g 1 = Lewis Hamilton, 2 = Valtteri Bottas, etc):");
//             intInput = input.nextInt();
//             driver = Driver.getDriverById(intInput);
//             if(driver == null)
//             System.out.println("No Drivers match this ID");
//             else{
//                  System.out.printf("\nTeam for Driver No.%s (%s):\n", intInput, driver.getDriverName());
//                  System.out.printf("- %s\n", driver.getDriverTeam());
//                 }
//             } catch (InputMismatchException e) {
//               System.out.println("Please enter an integer value for Driver ID");
//               input.nextLine();
//             }
//     }
// }
