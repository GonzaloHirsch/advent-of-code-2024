package day_9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    @SuppressWarnings("CallToPrintStackTrace")
    public static char[] readInput(String filename) {
        // Parse the input.
        char[] input = null;
        try {
            File myObj = new File(filename);
            try (Scanner myReader = new Scanner(myObj)) {
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    input = data.toCharArray();
                    for (int i = 0; i < input.length; i++) input[i] = (char)(input[i] - '0');
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return input;
    }
}
