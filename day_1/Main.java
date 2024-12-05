package day_1;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Parse.
        List<int[]> data = Input.readInput("./day_1/input.txt");

        // Solve.
        Solution solution = new Solution();
        int response = solution.getTotalListDistance(data.get(0), data.get(1));
        System.out.println("Solution is " + response);
        assert 1530215 == response;

        response = solution.getSimilarityScore(data.get(0), data.get(1));
        System.out.println("Similarity score is " + response);
        assert 26800609 == response;
    }
}
