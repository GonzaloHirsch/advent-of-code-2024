package day_6;

public class Main {

    public static void main(String[] args) {
        // Parse.
        char[][] data = Input.readInput("day_6/input.txt");

        // Solve.
        Solution solution = new Solution();
        int response = solution.getUniqueGuardPositions(data);
        System.out.println("Unique positions " + response);     // 4722

        response = solution.getUniqueObstaclePositions(data);
        System.out.println("Unique positions for obstacles " + response);     // 1602
    }
}
