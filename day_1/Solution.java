package day_1;

import java.util.*;

public class Solution {

    public int getTotalListDistance(int[] listA, int[] listB) {
        // Sort both lists.
        Arrays.sort(listA);
        Arrays.sort(listB);

        // Compare per index.
        int value = 0;
        for (int i = 0; i < listA.length; i++) {
            value += (Math.abs(listA[i] - listB[i]));
        }

        return value;
    }

    public int getSimilarityScore(int[] listA, int[] listB) {
        // Count items in B.
        Map<Integer, Integer> countBItems = new HashMap<>();
        for (int item : listB) countBItems.put(item, countBItems.getOrDefault(item, 0) + 1);

        // Convert to set so we go through each once.
        Set<Integer> setA = new HashSet<>();
        for (int item : listA) setA.add(item);

        // Process the set.
        return setA.stream().map(item -> countBItems.getOrDefault(item, 0) * item).reduce(Integer::sum).orElse(0);
    }
}
