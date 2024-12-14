package day_14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    @SuppressWarnings("CallToPrintStackTrace")
    public static List<Robot> readInput(String filename) {
        // Parse the input.
        List<Robot> input = new ArrayList<>();
        try {
            File myObj = new File(filename);
            try (Scanner myReader = new Scanner(myObj)) {
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    input.add(parseRobot(data));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return input;
    }

    private static Robot parseRobot(String s) {
        String[] pv = s.split(" ");
        return new Robot(parseVector(pv[0].replaceAll("p=", "")), parseVector(pv[1].replaceAll("v=", "")));
    }

    private static Vector parseVector(String s) {
        String[] coords = s.split(",");
        return new Vector(Integer.parseInt(coords[0]),Integer.parseInt(coords[1]));
    }

    public static class Vector {
        public int x, y;

        public Vector(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static class Robot {
        public Vector p, v;

        public Robot(Vector p, Vector v) {
            this.p = p;
            this.v = v;
        }
    }
}
