package day_10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    @SuppressWarnings("CallToPrintStackTrace")
    public static int[][] readInput(String filename) {
        // Parse the input.
        List<int[]> list = new ArrayList<>();
        try {
            File myObj = new File(filename);
            try (Scanner myReader = new Scanner(myObj)) {
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    char[] chars = data.toCharArray();
                    int[] arr = new int[data.length()];
                    for (int i = 0; i < arr.length; i++) arr[i] = chars[i] - '0';
                    list.add(arr);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        int[][] input = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) input[i] = list.get(i);
        return input;
    }
}
