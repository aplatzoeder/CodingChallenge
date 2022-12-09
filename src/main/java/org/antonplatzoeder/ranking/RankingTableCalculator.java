package org.antonplatzoeder.ranking;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RankingTableCalculator {
    public static void main(String[] args) {
        System.out.println("Working Directory: " + System.getProperty("user.dir"));
        System.out.printf("Input: %s%n", args[0]);

        try {
            File input = new File(args[0]);
            Scanner myReader = new Scanner(input);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }
}