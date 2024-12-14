package day_14;

import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    // Part 1.
    public void simulateRobot(Input.Robot robot, int steps, int limitX, int limitY) {
        for (int i = 0; i < steps; i++) this.simulateRobotStep(robot, limitX, limitY);
    }

    public void simulateRobotStep(Input.Robot robot, int limitX, int limitY) {
        robot.p.x = (robot.p.x + robot.v.x) % limitX;
        if (robot.p.x < 0) robot.p.x += limitX;
        robot.p.y = (robot.p.y + robot.v.y) % limitY;
        if (robot.p.y < 0) robot.p.y += limitY;
    }

    public int getQuadrant(int x, int y, int midX, int midY) {
        if (x == midX || y == midY) return 0;
        if (x < midX && y < midY) return 1;
        if (x > midX && y < midY) return 2;
        if (x > midX) return 3;
        return 4;
    }

    public long getSafetyFactor(List<Input.Robot> robots, int steps, int limitX, int limitY) {
        // Pre-compute some limits.
        int midX = limitX / 2, midY = limitY / 2;

        // Simulate all of them.
        robots.parallelStream().forEach(robot -> simulateRobot(robot, steps, limitX, limitY));

        // Compute quadrants for each.
        int[] robotCounts = robots.stream().map(robot -> getQuadrant(robot.p.x, robot.p.y, midX, midY))
                .reduce(new int[4], (arr, val) -> {
                    if (val == 0) return arr;
                    arr[val - 1]++;
                    return arr;
                }, (arr1, arr2) -> {
                    for (int i = 0; i < arr1.length; i++) arr1[i] += arr2[i];
                    return arr1;
                });

        int value = 1;
        for (int n : robotCounts) value *= n;

        return value;
    }

    // Part 2.
    // Look for density in robots.
    public long findTree(List<Input.Robot> robots, int steps, int limitX, int limitY) {
        // Simulate all of them.
        int totalSteps = 0;
        boolean found = false;
        while (!found && totalSteps < steps) {
            // Convert to matrix.
            boolean[][] mat = new boolean[limitY][limitX];

            // Run 1 step and add to mat.
            robots.parallelStream().forEach(robot -> {
                simulateRobot(robot, 1, limitX, limitY);
                mat[robot.p.y][robot.p.x] = true;
            });

            // Compute density.
            found = foundDenseArea(mat, 0.9, 5);

            totalSteps++;
        }

        return totalSteps;
    }

    public boolean foundDenseArea(boolean[][] mat, double densityLimit, int windowSize) {
        for (int i = 0, w2 = windowSize * windowSize; i < mat.length - windowSize; i++) {
            for (int j = 0; j < mat[0].length - windowSize; j++) {
                int total = 0;
                for (int a = 0; a < windowSize; a++) for (int b = 0; b < windowSize; b++) total += (mat[i + a][j + b] ? 1 : 0);
                if ((double)total / w2 >= densityLimit) return true;
            }
        }
        return false;
    }
}
