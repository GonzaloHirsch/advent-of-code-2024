package day_2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Input {

    @SuppressWarnings("CallToPrintStackTrace")
    public static List<Integer[]> readInput(String filename) {
        // Create the base lists.
        List<Integer[]> list = new ArrayList<>();

        // Parse the input.
        try {
            File myObj = new File(filename);
            try (Scanner myReader = new Scanner(myObj)) {
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    List<Integer> parsedData = Arrays.stream(data.split(" ")).map(Integer::parseInt).toList();
                    list.add(parsedData.toArray(new Integer[0]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return list;
    }
}
