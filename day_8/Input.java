package day_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    @SuppressWarnings("CallToPrintStackTrace")
    public static char[][] readInput(String filename) {
        // Parse the input.
        List<char[]> list = new ArrayList<>();
        try {
            File myObj = new File(filename);
            try (Scanner myReader = new Scanner(myObj)) {
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    list.add(data.toCharArray());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        char[][] input = new char[list.size()][];
        for (int i = 0; i < input.length; i++) input[i] = list.get(i);
        return input;
    }
}
