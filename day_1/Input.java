package day_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {

    @SuppressWarnings("CallToPrintStackTrace")
    public static List<int[]> readInput(String filename) {
        int lines = Input.getLineCount(filename);

        // Create the base lists.
        List<int[]> list = new ArrayList<>();
        int[] listA = new int[lines], listB = new int[lines];

        // Parse the input.
        try {
            File myObj = new File(filename);
            try (Scanner myReader = new Scanner(myObj)) {
                int index = 0;
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] rowValues = data.split("   ");
                    listA[index] = Integer.parseInt(rowValues[0]);
                    listB[index++] = Integer.parseInt(rowValues[1]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        list.add(listA);
        list.add(listB);
        return list;
    }

    public static int getLineCount(String file) {
        int lines;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            lines = 0;
            while (reader.readLine() != null) {
                lines++;
            }
        } catch (IOException ex) {
            return 0;
        }
        return lines;
    }
}
