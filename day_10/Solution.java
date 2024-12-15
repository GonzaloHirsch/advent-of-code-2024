package day_10;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    // Part 1.
    public long computeTrailScores(int[][] map, boolean distinctTrails) {
        long total = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] != 0) continue;
                total += this.dfsPath(map, i, j, -1, distinctTrails ? null : new HashSet<>());
            }
        }
        return total;
    }

    public long dfsPath(int[][] map, int x, int y, int prev, Set<Integer> endpoints) {
        // Verify out of bounds.
        if (x < 0 || y < 0 || x >= map.length || y >= map[0].length || map[x][y] - prev != 1) return 0;

        // Reached the end.
        if (map[x][y] == 9) {
            int encoded = this.encodePoint(x, y);
            if (endpoints != null) {
                if (endpoints.contains(encoded)) return 0;
                endpoints.add(encoded);
            }
            return 1;
        }

        return this.dfsPath(map, x + 1, y, map[x][y], endpoints) + this.dfsPath(map, x - 1, y, map[x][y],
                endpoints) + this.dfsPath(map, x, y + 1, map[x][y], endpoints) + this.dfsPath(map, x, y - 1, map[x][y],
                endpoints);
    }

    public int encodePoint(int x, int y) {
        return x * 100000 + y;
    }
}
