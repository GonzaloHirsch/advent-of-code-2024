package day_3;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Parse.
        String data = Input.readInput("./day_3/input.txt");

        // Solve.
        Solution solution = new Solution();
        long response = solution.getValueFromCorruptedMemory(data);
        System.out.println("Mul value is " + response);
        assert 174103751 == response;

        response = solution.getValueFromCorruptedMemoryWithExtraInstructions(data);
        System.out.println("Mul value with extra is " + response);
        assert 100411201 == response;
    }
}
