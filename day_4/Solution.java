package day_4;

import java.util.List;
import java.util.regex.Pattern;

public class Solution {
    // First Part
    private static final int[][] DIRECTIONS = new int[][]{
            new int[]{0,1},
            new int[]{0,-1},
            new int[]{1,0},
            new int[]{-1,0},
            new int[]{1,1},
            new int[]{-1,-1},
            new int[]{1,-1},
            new int[]{-1,1},
    };
    public int findXmasTotals(char[][] data) {
        int total = 0;
        char[] target = "XMAS".toCharArray();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                // Skip if not a valid starting char.
                if (data[i][j] != 'X') continue;
                total += this.dfsForXmas(data, i, j, target);
            }
        }
        return total;
    }

    private int dfsForXmas(char[][] data, int i, int j, char[] target) {
        int total = 0;
        for (int[] dir : DIRECTIONS) {
            total += (this.isValidXmas(data, i, j, dir, target)) ? 1 : 0;
        }
        return total;
    }

    private boolean isValidXmas(char[][] data, int i, int j, int[] dir, char[] target){
        for (char c : target) {
            // Out of bounds.
            if (i < 0 || j < 0 || i >= data.length || j >= data[0].length) return false;
            // Verify the letters.
            if (data[i][j] != c) return false;
            // Update counters.
            i += dir[0];
            j += dir[1];
        }
        return true;
    }

    // Second part
    private static final int[][] DIRECTIONS_FOR_X = new int[][]{
            new int[]{1,1},
            new int[]{-1,-1},
            new int[]{1,-1},
            new int[]{-1,1},
    };
    public int findTotalMasInX(char[][] data) {
        int total = 0;
        char[] target = "MAS".toCharArray();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                // Skip if not a valid starting char.
                if (data[i][j] != 'A') continue;
                total += this.dfsForMasX(data, i, j, target);
            }
        }
        return total;
    }

    private int dfsForMasX(char[][] data, int i, int j, char[] target) {
        int total = 0;
        for (int[] dir : DIRECTIONS_FOR_X) {
            total += (this.isValidMasX(data, i, j, dir, target)) ? 1 : 0;
        }
        return total >= 2 ? 1 : 0;
    }

    private boolean isValidMasX(char[][] data, int i, int j, int[] dir, char[] target){
        i -= dir[0]; j -= dir[1];
        for (char c : target) {
            // Out of bounds.
            if (i < 0 || j < 0 || i >= data.length || j >= data[0].length) return false;
            // Verify the letters.
            if (data[i][j] != c) return false;
            // Update counters.
            i += dir[0];
            j += dir[1];
        }
        return true;
    }
}
