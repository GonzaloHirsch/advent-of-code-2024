package day_8;

import java.util.*;

public class Solution {
    // Part 1.
    public int getTotalAntinodes(char[][] data, boolean allowAnyPosition) {
        // Get a list of all matching letters.
        Map<Character, int[][]> coordinates = this.computeCharacterPositions(data);

        // Compute all the pairs and place antinodes.
        return coordinates.values().stream().filter(coords -> coords.length > 1)
                .map(coords -> this.computeAntinodes(data, coords, allowAnyPosition)).reduce(Integer::sum)
                .orElse(0);
    }

    private Map<Character, int[][]> computeCharacterPositions(char[][] data) {
        // Create a list of all coordinates per item.
        Map<Character, List<int[]>> coords = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i][j] == '.') continue;    // Skip empty spots.
                List<int[]> positions = coords.getOrDefault(data[i][j], new ArrayList<>());
                positions.add(new int[]{i, j});
                coords.put(data[i][j], positions);
            }
        }

        // Convert that to a different map.
        return coords.entrySet()
                .stream()
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue().toArray(new int[0][0])))
                .reduce(new HashMap<>(), (map, entry) -> {
                    map.put(entry.getKey(), entry.getValue());
                    return map;
                }, (m1, m2) -> {
                    m1.putAll(m2);
                    return m1;
                });
    }

    private int computeAntinodes(char[][] data, int[][] positions, boolean allowAnyPosition) {
        int total = 0;

        // Iterate all the pairs of nodes.
        for (int i = 0, x, y, a, b, diffX, diffY, newX, newY; i < positions.length; i++) {
            x = positions[i][0]; y = positions[i][1];
            for (int j = i + 1; j < positions.length; j++) {
                a = positions[j][0]; b = positions[j][1];

                // Compute the difference in each axis;
                diffX = a - x; diffY = b - y;

                // Compute the new positions until we are out of bounds.
                int index = allowAnyPosition ? 0 : 1;
                do {
                    newX = x - (index * diffX); newY = y - (index * diffY);
                    if (isWithinBounds(data, newX, newY) && data[newX][newY] != '#') {
                        data[newX][newY] = '#';
                        total++;
                    }
                    index++;
                } while (isWithinBounds(data, newX, newY) && index < (allowAnyPosition ? data.length : 1));
                index = allowAnyPosition ? 0 : 1;
                do {
                    newX = a + (index * diffX); newY = b + (index * diffY);
                    if (isWithinBounds(data, newX, newY) && data[newX][newY] != '#') {
                        data[newX][newY] = '#';
                        total++;
                    }
                    index++;
                } while (isWithinBounds(data, newX, newY) && index < (allowAnyPosition ? data.length : 1));
            }
        }

        return total;
    }

    private boolean isWithinBounds(char[][] data, int x, int y) {
        return x >= 0 && y >= 0 && x < data.length && y < data[0].length;
    }
}
