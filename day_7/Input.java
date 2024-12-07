package day_7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Input {
    @SuppressWarnings("CallToPrintStackTrace")
    public static List<long[]> readInput(String filename) {
        // Parse the input.
        List<long[]> input = new ArrayList<>();
        try {
            File myObj = new File(filename);
            try (Scanner myReader = new Scanner(myObj)) {
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] parts = data.split(": ");
                    long n = Long.parseLong(parts[0]);
                    parts = parts[1].split(" ");
                    long[] nums = new long[parts.length + 1];
                    for (int i = 0; i < parts.length; i++) nums[i + 1] = Long.parseLong(parts[i]);
                    nums[0] = n;
                    input.add(nums);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return input;
    }
}
