package day_5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

public class Input {

    public static class ProblemInput {
        public List<Map.Entry<Integer, Integer>> pairs = new ArrayList<>();
        public List<List<Integer>> pageUpdates = new ArrayList<>();
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static ProblemInput readInput(String filename) {
        // Parse the input.
        ProblemInput input = new ProblemInput();
        try {
            File myObj = new File(filename);
            try (Scanner myReader = new Scanner(myObj)) {
                // Process pairs.
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if (data.isEmpty()) break;
                    String[] pair = data.split("\\|");
                    input.pairs.add(new AbstractMap.SimpleEntry<>(Integer.parseInt(pair[0]),Integer.parseInt(pair[1])));
                }
                // Process updates.
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if (data.isEmpty()) break;
                    input.pageUpdates.add(Stream.of(data.split(",")).map(Integer::parseInt).toList());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return input;
    }
}
