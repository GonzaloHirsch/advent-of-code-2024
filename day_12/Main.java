package day_12;

public class Main {

    public static void main(String[] args) {
        // Parse.
        String input = "day_12/small_input.txt";
        char[][] data = Input.readInput(input);
        Solution solution = new Solution();
        long response = solution.getFenceCost(data);
        System.out.println("Fence cost " + response);     // 1456082

        data = Input.readInput(input);
        //response = solution.getChecksumWithFragmentation(data, true);
        System.out.println("Checksum with better fragmentation " + response);     // 1272555053673
    }
}
