package day_11;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Solution {
    public static class Stone {
        public long n;
        public static Map<Long, Map<Integer, Long>> memo = new ConcurrentHashMap<>();

        public Stone(long n) {
            this.n = n;
        }

        public long simulate(long n, int limit, int steps) {
            if (limit == 0) return 1;
            // Verify if the number is there. If it does, it SHOULD have the key.
            if (!Stone.memo.containsKey(n)) Stone.memo.put(n, new ConcurrentHashMap<>());
            else if (Stone.memo.get(n).containsKey(limit)) return Stone.memo.get(n).get(limit);

            long value;
            int digits = (int) (Math.log10(n) + 1);
            // Rule #1
            if (n == 0) value = this.simulate(1, limit - 1, steps + 1);
            // Rule #2
            else if (digits % 2 == 0) {
                long pow = (long) Math.pow(10, (int) (digits / 2.0));
                value = this.simulate(n / pow, limit - 1, steps + 1) + this.simulate(n % pow, limit - 1, steps + 1);
            }
            // Rule #3
            else {
                value = this.simulate(n * 2024, limit - 1, steps + 1);
            }

            // Add how many stones we generate in the limit steps we have left.
            // E.g. having a 0, in X steps, we get Y.
            Stone.memo.get(n).put(limit, value);
            return value;
        }
    }

    public long computeStoneMutations(List<Integer> l, int steps) {
        Stone.memo = new ConcurrentHashMap<>();
        List<Stone> stones = l.stream().map(Stone::new).toList();
        return stones.parallelStream().map(stone -> stone.simulate(stone.n, steps, 0)).reduce(Long::sum).orElse(0L);
    }

}
