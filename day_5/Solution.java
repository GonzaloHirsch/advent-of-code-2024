package day_5;

import java.util.*;

public class Solution {

    // Part 1.
    public int computeMiddleOfUpdates(Input.ProblemInput data) {
        // Verify ok lists.
        List<List<Integer>> validLists = this.getUpdatesWithStatus(data.pageUpdates, data.pairs, true);

        // Add middles.
        return this.getSumOfMiddles(validLists);
    }

    private Map<Integer, Set<Integer>> precomputeRequirements(List<Map.Entry<Integer, Integer>> pairs, Set<Integer> numbers) {
        return pairs.stream()
                .filter(pair -> numbers.contains(pair.getKey()) && numbers.contains(pair.getValue()))
                .reduce(
                        new HashMap<>(),
                        (hashMap, pair) -> {
                            Set<Integer> s = hashMap.getOrDefault(pair.getValue(), new HashSet<>());
                            s.add(pair.getKey());
                            hashMap.put(pair.getValue(), s);
                            return hashMap;
                        },
                        (map1, map2) -> {
                            map2.forEach((key, value) -> {
                                Set<Integer> s = map1.getOrDefault(key, value);
                                s.addAll(value);
                                map1.put(key, s);
                            });
                            return map1;
                        }
                );
    }

    private boolean isValidList(List<Integer> updates, List<Map.Entry<Integer, Integer>> rules) {
        // Computes the requirements.
        Map<Integer, Set<Integer>> requirements = this.precomputeRequirements(rules, new HashSet<>(updates));

        for (int page : updates) {
            // Verify if it's ok to update this one.
            if (requirements.containsKey(page) && requirements.get(page).size() > 0) return false;
            // Update the met requirements.
            requirements.forEach((key, value) -> value.remove(page));
        }

        return true;
    }

    private List<List<Integer>> getUpdatesWithStatus(List<List<Integer>> updates, List<Map.Entry<Integer, Integer>> rules, boolean isValid) {
        return updates.stream().filter(l -> this.isValidList(l, rules) == isValid).toList();
    }

    private int getSumOfMiddles(List<List<Integer>> list) {
        return list.stream().map(l -> l.get(l.size() / 2)).reduce(Integer::sum).orElse(0);
    }

    // Part 2.
    public int computeMiddleOfUpdatesWithReordering(Input.ProblemInput data) {
        // Get lists with the right status.
        List<List<Integer>> invalidLists = this.getUpdatesWithStatus(data.pageUpdates, data.pairs, false);

        invalidLists = this.sortInvalidLists(invalidLists, data.pairs);

        // Add up middles.
        return this.getSumOfMiddles(invalidLists);
    }

    private List<List<Integer>> sortInvalidLists(List<List<Integer>> invalidLists, List<Map.Entry<Integer, Integer>> rules) {
        // Compute the rules into a ruleset for comparisons.
        Map<Integer, Integer> ordering = rules.stream().reduce(
                new HashMap<>(),
                (hashMap, pair) -> {
                    hashMap.put(pair.getKey() * 10000 + pair.getValue(), -1);
                    hashMap.put(pair.getValue() * 10000 + pair.getKey(), 1);
                    return hashMap;
                },
                (map1, map2) -> {
                    map1.putAll(map2);
                    return map1;
                }
        );

        // Sort using the rules laid out.
        return invalidLists.stream().map(l -> {
            List<Integer> newList = new ArrayList<>(l);
            newList.sort((a, b) -> ordering.getOrDefault(a * 10000 + b, 1));
            return newList;
        }).toList();
    }
}
