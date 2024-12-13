package day_13;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Parse.
        String input = "day_13/input.txt";
        List<Input.Machine> machines = Input.readInput(input);
        Solution solution = new Solution();
        long response = solution.getMinimumTokensToWin(machines, 100);
        System.out.println("Minimum token count " + response);     // 33427

        // Add the new diff.
        machines.forEach(machine -> {
            machine.prize.x += 10000000000000L;
            machine.prize.y += 10000000000000L;
        });
        response = solution.getMinimumTokensToWin(machines, 1000000000000000L);
        System.out.println("Minimum token count with new coords " + response);     // 1545092988928
    }
}
