package day_5;

public class Main {

    public static void main(String[] args) {
        // Parse.
        Input.ProblemInput data = Input.readInput("day_5/input.txt");

        // Solve.
        Solution solution = new Solution();
        int response = solution.computeMiddleOfUpdates(data);
        System.out.println("Sum of middles " + response);

        // Solve.
        solution = new Solution();
        response = solution.computeMiddleOfUpdatesWithReordering(data);
        System.out.println("Sum of middles with errors " + response);
    }
}
