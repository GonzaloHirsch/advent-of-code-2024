package day_9;

public class Main {

    public static void main(String[] args) {
        // Parse.
        String input = "day_9/base_input.txt";
        char[] data = Input.readInput(input);
        Solution solution = new Solution();
        long response = solution.getChecksum(data, false);
        System.out.println("Checksum " + response);     // 6421128769094

        data = Input.readInput(input);
        response = solution.getChecksumWithFragmentation(data, true);
        System.out.println("Checksum with better fragmentation " + response);     // 1272555053673
    }
}
