package com.project;

import com.project.menu.Menu;
import com.project.util.Constants;
import java.io.IOException;


 /**
  * This is the Main method which makes use of Menu method.
  * @author Gary Wheeler
  * @param args Unused.
  */

public class Main {
    public static void main(String[] args) throws IOException
    {
        Menu.loadCSV();
        new Menu(Constants.MAIN_TITLE, 80);
    }
}