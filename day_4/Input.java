package day_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {

    @SuppressWarnings("CallToPrintStackTrace")
    public static char[][] readInput(String filename) {
        // Parse the input.
        List<String> l = new ArrayList<>();
        int cols = 1;
        try {
            File myObj = new File(filename);
            try (Scanner myReader = new Scanner(myObj)) {
                int lineCount = 0;
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    l.add(data);
                    cols = data.length();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Process to generate an array.
        char[][] input = new char[l.size()][cols];
        for (int i = 0; i < l.size(); i++) {
            input[i] = l.get(i).toCharArray();
        }

        return input;
    }
}
