package day_6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

public class Input {
    @SuppressWarnings("CallToPrintStackTrace")
    public static char[][] readInput(String filename) {
        // Parse the input.
        List<char[]> l = new ArrayList<>();
        try {
            File myObj = new File(filename);
            try (Scanner myReader = new Scanner(myObj)) {
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    l.add(data.toCharArray());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        char[][] input = new char[l.size()][];
        for (int i = 0; i < l.size(); i++) input[i] = l.get(i);
        return input;
    }
}
