package day_8;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Parse.
        String input = "day_8/input.txt";
        char[][] data = Input.readInput(input);
        Solution solution = new Solution();
        int response = solution.getTotalAntinodes(data, false);
        System.out.println("Total antinodes " + response);     // 256

        data = Input.readInput(input);
        response = solution.getTotalAntinodes(data, true);
        System.out.println("Total antinodes with harmonics " + response);     // 1005
    }
}
