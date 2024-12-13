package day_13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    @SuppressWarnings("CallToPrintStackTrace")
    public static List<Machine> readInput(String filename) {
        // Parse the input.
        List<Machine> input = new ArrayList<>();
        try {
            File myObj = new File(filename);
            try (Scanner myReader = new Scanner(myObj)) {
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if (data.isEmpty()) continue;
                    String bbStr = myReader.nextLine(), prizeStr = myReader.nextLine();
                    Machine m = new Machine(parseButton(data, 3),parseButton(bbStr, 1),parsePrize(prizeStr));
                    input.add(m);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return input;
    }

    private static Machine.Button parseButton(String s, int cost) {
        String[] coords = s.split(": ")[1].replaceAll("[XY]\\+", "").split(", ");
        return new Machine.Button(Long.parseLong(coords[0]), Long.parseLong(coords[1]), cost);
    }

    private static Machine.Prize parsePrize(String s) {
        String[] coords = s.split(": ")[1].replaceAll("[XY]=", "").split(", ");
        return new Machine.Prize(Long.parseLong(coords[0]), Long.parseLong(coords[1]));
    }

    public static class Machine {
        public static class Button {
            public long x;
            public long y;
            public int cost;

            public Button(long x, long y, int cost) {
                this.x = x;
                this.y = y;
                this.cost = cost;
            }
        }
        public static class Prize {
            public long x;
            public long y;

            public Prize(long x, long y) {
                this.x = x;
                this.y = y;
            }
        }
        public Button buttonA, buttonB;
        public Prize prize;
        public Machine(Button A, Button B, Prize prize) {
            this.buttonA = A;
            this.buttonB = B;
            this.prize = prize;
        }
    }
}
