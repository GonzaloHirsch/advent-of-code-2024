package day_7;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // Parse.
        List<long[]> data = Input.readInput("day_7/input.txt");

        // Solve.
        Solution solution = new Solution();
        long response = solution.getPossibleEquations(data);
        System.out.println("Total sum " + response);     // 1298103531759

        response = solution.getPossibleEquationsWithExtension(data);
        System.out.println("Total sum with extension " + response);     // 140575048428831
    }
}
