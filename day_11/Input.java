package day_11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Input {
    @SuppressWarnings("CallToPrintStackTrace")
    public static List<Integer> readInput(String filename) {
        // Parse the input.
        List<Integer> input = new ArrayList<>();
        try {
            File myObj = new File(filename);
            try (Scanner myReader = new Scanner(myObj)) {
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    return Arrays.stream(data.split(" ")).toList().stream().map(Integer::parseInt).collect(Collectors.toList());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return input;
    }
}
