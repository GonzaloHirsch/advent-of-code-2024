package day_11;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        tryCase("0_test.txt", 7); // 14
        tryCase("base_input.txt", 1); // 7
        tryCase("extended.txt", 6); // 22
        tryCase("extended.txt", 25); // 55312
        tryCase("input.txt", 25); // 204022
        tryCase("input.txt", 75); // 241651071960597
    }

    private static void tryCase(String file, int steps) {
        List<Integer> stones = Input.readInput("day_11/" + file);
        Solution solution = new Solution();
        long response = solution.computeStoneMutations(stones, steps);
        System.out.println("Total stones (" + steps + " steps) (" + file + ") --> " + response);
    }
}
