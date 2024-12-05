package day_4;

public class Main {

    public static void main(String[] args) {
        // Parse.
        char[][] data = Input.readInput("./day_4/input.txt");

        // Solve.
        Solution solution = new Solution();
        int response = solution.findXmasTotals(data);
        System.out.println("Instances of XMAS " + response);
        assert 2507 == response;

        response = solution.findTotalMasInX(data);
        System.out.println("Instances of X-MAS " + response);
        assert 1969 == response;
    }
}
