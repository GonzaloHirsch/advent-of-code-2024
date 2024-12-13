package day_12;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    // Part 1.
    public long getFenceCost(char[][] data) {
        long total = 0;
        boolean[][] visited = new boolean[data.length][data[0].length];

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (visited[i][j]) continue;
                int[] measurements = new int[2];
                this.dfsForType(data, visited, i, j, data[i][j], measurements);
                total += ((long) measurements[0] * measurements[1]);
            }
        }
        return total;
    }

    private void dfsForType(char[][] data, boolean[][] visited, int x, int y, char type, int[] measurements) {
        if (!this.isWithinBounds(data, x, y) || data[x][y] != type || visited[x][y]) return;
        visited[x][y] = true;
        // Adding to area.
        measurements[0]++;
        // Adding to perimeter.
        measurements[1] += (4 - this.getNeighborsOfType(data, x, y, type));
        // Expand to the neighbors.
        this.dfsForType(data, visited, x - 1, y, type, measurements);
        this.dfsForType(data, visited, x, y - 1, type, measurements);
        this.dfsForType(data, visited, x + 1, y, type, measurements);
        this.dfsForType(data, visited, x, y + 1, type, measurements);
    }

    private int getNeighborsOfType(char[][] data, int x, int y, char type) {
        int total = 0;
        total += this.isWithinBounds(data, x - 1, y) && data[x - 1][y] == type ? 1 : 0;
        total += this.isWithinBounds(data, x, y - 1) && data[x][y - 1] == type ? 1 : 0;
        total += this.isWithinBounds(data, x + 1, y) && data[x + 1][y] == type ? 1 : 0;
        total += this.isWithinBounds(data, x, y + 1) && data[x][y + 1] == type ? 1 : 0;
        return total;
    }

    private boolean isWithinBounds(char[][] data, int x, int y) {
        return x >= 0 && y >= 0 && x < data.length && y < data[0].length;
    }

    // Part 2.
    public long getFenceCostWithDiscount(char[][] data) {
        long total = 0;
        int[][] ids = new int[data.length][data[0].length];
        Map<Integer, int[]> dimensions = new HashMap<>();

        // One pass to compute IDs and the area.
        for (int i = 0, id = 1; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (ids[i][j] <= 0) continue;
                int[] measurements = new int[1];
                this.dfsForTypeWithId(data, ids, i, j, id, data[i][j], measurements);
                dimensions.put(id, new int[]{measurements[0], 0});
                id++;
            }
        }

        // One horizontal pass to compute those sides.
        for (int i = 0, id; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                id = ids[i][j];
                if (!this.isWithinBounds(data, i - 1, j) || data[i-1][j] != data[i][j]) dimensions.get(id)[1]++;
                if (!this.isWithinBounds(data, i + 1, j) || data[i+1][j] != data[i][j]) dimensions.get(id)[1]++;
            }
        }

        // One vertical pass.
        for (int i = 0, id = 1; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (ids[i][j] <= 0) continue;
                int[] measurements = new int[1];
                this.dfsForTypeWithId(data, ids, i, j, id, data[i][j], measurements);
                dimensions.put(id, new int[]{measurements[0], 0});
                id++;
            }
        }

        // Another pass for the total.
        return total;
    }

    private void dfsForTypeWithId(char[][] data, int[][] ids, int x, int y, int id, char type, int[] measurements) {
        if (!this.isWithinBounds(data, x, y) || data[x][y] != type || ids[x][y] > 0) return;
        ids[x][y] = id;
        // Adding to area.
        measurements[0]++;
        // Expand to the neighbors.
        this.dfsForTypeWithId(data, ids, x - 1, y, id, type, measurements);
        this.dfsForTypeWithId(data, ids, x, y - 1, id, type, measurements);
        this.dfsForTypeWithId(data, ids, x + 1, y, id, type, measurements);
        this.dfsForTypeWithId(data, ids, x, y + 1, id, type, measurements);
    }
}
