package day_10;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Part 1.
        tryCase("base_input.txt", false);   // 1
        tryCase("extended.txt", false);     // 36
        tryCase("input.txt", false);        // 760
        // Part 2.
        tryCase("simple_pt2.txt", true);    // 3
        tryCase("medium_pt2.txt", true);    // 13
        tryCase("hard_pt2.txt", true);      // 227
        tryCase("extended.txt", true);      // 81
        tryCase("input.txt", true);         // 1764
    }

    public static void tryCase(String file, boolean distinct) {
        int[][] map = Input.readInput("day_10/" + file);
        Solution solution = new Solution();
        long response = solution.computeTrailScores(map, distinct);
        System.out.println("Trail score from " + file + " --> " + response);
    }
}
