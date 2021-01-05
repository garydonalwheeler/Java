package com.project.menu;

import java.util.List;

/**
 * @author Gary Wheeler
 * This class represents a MenuInterface that is being used in this program
 * This MenuInterface can be used in any class within the program
 * It creates a contract whereby these methods need to be used in any class that implements this interface
 */

public interface MenuInterface {

    public void initMenu();

    public void displayHeader(); 

    public void displayBody();

    public void displayBody(int listSize, List<String> headers, List<Integer> colMaxWidths);

    public void printRows(List<String> list, List<Integer> colMaxWidths, boolean topBorder, boolean bottomBorder);

}