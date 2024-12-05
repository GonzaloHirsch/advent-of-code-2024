package day_2;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Parse.
        List<Integer[]> data = Input.readInput("./day_2/input.txt");

        // Solve.
        Solution solution = new Solution();
        int response = solution.getTotalSafeReports(data, 0);
        System.out.println("Total safe reports is " + response);
        assert 660 == response;

        response = solution.getTotalSafeReports(data, 1);
        System.out.println("Total safe reports with dampener is " + response);
        assert 689 == response;
    }
}
