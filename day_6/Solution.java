package day_6;

import java.util.*;

public class Solution {

    public final static int[][] DIRECTIONS = new int[][]{
            new int[]{-1, 0},new int[]{0, 1},new int[]{1, 0},new int[]{0, -1}
    };

    // Part 1.
    public int getUniqueGuardPositions(char[][] map) {
        // Find initial position.
        Map.Entry<Integer, Integer> position = this.getInitialPosition(map);
        map[position.getKey()][position.getValue()] = '.';

        // Simulate the movement and store the positions.
        Set<Integer> cells = this.simulateMovement(map, position.getKey(), position.getValue());

        map[position.getKey()][position.getValue()] = '^';

        return cells.size();
    }

    public Map.Entry<Integer, Integer> getInitialPosition(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) if (map[i][j] == '^') return new AbstractMap.SimpleEntry<>(i, j);
        }
        throw new IllegalArgumentException("No start position");
    }

    public Set<Integer> simulateMovement(char[][] map, int x, int y) {
        // Where we store the directions.
        Set<Integer> positions = new HashSet<>();

        // Current direction we are facing.
        int[] dir = DIRECTIONS[0];

        // Will simulate until we leave the bounds.
        int nextX, nextY, dirIndex = 0;
        while (true) {
            // Add the current position to the list of positions.
            positions.add(this.encodePosition(x, y));

            // Compute next position.
            nextX = x + dir[0]; nextY = y + dir[1];

            // If the next one is already out of bounds, ignore it and leave to finish.
            if (!this.isWithinBounds(map, nextX, nextY)) break;

            // If we are going to crash to something, we need to change directions.
            if (!this.isSafePosition(map, nextX, nextY)) {
                // Update direction.
                dirIndex = (dirIndex + 1) % DIRECTIONS.length;
                dir = DIRECTIONS[dirIndex];
            } else {
                // Update current position.
                x = nextX; y = nextY;
            }
        }

        return positions;
    }

    public boolean isWithinBounds(char[][] map, int x, int y) {
        return x >= 0 && x < map.length && y >= 0 && y < map[0].length;
    }

    public boolean isSafePosition(char[][] map, int x, int y) {
        return map[x][y] != '#';
    }

    private int encodePosition(int x, int y) {
        return x * 10000 + y;
    }

    private int encodeVertexCombination(int x, int y, int a, int b) {
        return (x * 1000 + y) * 1000000 + a * 1000 + b;
    }

    private Map.Entry<Integer, Integer> decodePosition(int p) {
        return new AbstractMap.SimpleEntry<>(p / 10000, p % 10000);
    }

    // Part 2
    public int getUniqueObstaclePositions(char[][] map) {
        // Find initial position.
        Map.Entry<Integer, Integer> position = this.getInitialPosition(map);
        map[position.getKey()][position.getValue()] = '.';

        // Simulate the movement and store the positions.
        Set<Integer> cells = this.simulateMovement(map, position.getKey(), position.getValue());

        // Remove the initial position because it's not a valid one.
        cells.remove(this.encodePosition(position.getKey(), position.getValue()));
        long total = cells.stream().map(cell -> {
            Map.Entry<Integer, Integer> c = this.decodePosition(cell);
            int x = c.getKey(), y = c.getValue();
            map[x][y] = '#';
            boolean isLoop = this.simulateMovementWithNewObstacles(map, position.getKey(), position.getValue());
            map[x][y] = '.';
            return isLoop;
        }).filter(isLoop -> isLoop).count();

        map[position.getKey()][position.getValue()] = '^';

        return (int) total;
    }

    public boolean simulateMovementWithNewObstacles(char[][] map, int x, int y) {
        // Where we store the directions.
        Set<Long> vertexConnections = new HashSet<>();

        // Current direction we are facing.
        int[] dir = DIRECTIONS[0];

        // Will simulate until we leave the bounds.
        int nextX, nextY, vX, vY, dirIndex = 0;
        long encoding;
        while (true) {
            // Store the initial vertex.
            vX = x; vY = y;

            // Complete a sequence in a vertex.
            while (true) {
                // Compute next position.
                nextX = x + dir[0]; nextY = y + dir[1];

                // If the next one is already out of bounds, ignore it and leave to finish.
                if (!this.isWithinBounds(map, nextX, nextY)) return false;

                // If it is not safe, it means we reached an obstacle.
                if (!this.isSafePosition(map, nextX, nextY)) {
                    // Encode the vertex and check if it matches.
                    // Not the next one, because that may be an obstacle.
                    encoding = this.encodeVertexCombination(vX, vY, x, y);
                    if (vertexConnections.contains(encoding)) return true;
                    vertexConnections.add(encoding);

                    // We need to break so that we handle changing directions.
                    // Update direction.
                    dirIndex = (dirIndex + 1) % DIRECTIONS.length;
                    dir = DIRECTIONS[dirIndex];
                    break;
                }

                // Update current position.
                x = nextX; y = nextY;
            }
        }
    }
}
