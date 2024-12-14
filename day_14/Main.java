package day_14;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        tryCase("base_input.txt", 100, 11, 7);
        tryCase("input.txt", 100, 101, 103);
        tryCasePart2("input.txt", 10000, 101, 103);
    }

    public static void tryCase(String file, int steps, int limitX, int limitY) {
        List<Input.Robot> robots = Input.readInput("day_14/" + file);
        Solution solution = new Solution();
        long response = solution.getSafetyFactor(robots, steps, limitX, limitY);
        System.out.println("Safety factor after " + steps + " steps (" + limitX + " , " + limitY + ") --> " + response);
    }

    public static void tryCasePart2(String file, int steps, int limitX, int limitY) {
        List<Input.Robot> robots = Input.readInput("day_14/" + file);
        Solution solution = new Solution();
        long response = solution.findTree(robots, steps, limitX, limitY);
        System.out.println("Tree found after " + steps + " steps (" + limitX + " , " + limitY + ") --> " + response);
    }
}
