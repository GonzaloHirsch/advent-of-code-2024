package day_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Input {

    @SuppressWarnings("CallToPrintStackTrace")
    public static String readInput(String filename) {
        // Parse the input.
        StringBuilder sb = new StringBuilder();
        try {
            File myObj = new File(filename);
            try (Scanner myReader = new Scanner(myObj)) {
                int lineCount = 0;
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    sb.append(data);
                    if (lineCount++ > 0) sb.append("\n");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return sb.toString();
    }
}
