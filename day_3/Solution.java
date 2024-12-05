package day_3;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Solution {
    private static final Pattern patternNumber = Pattern.compile("[1-9]\\d{0,2}");
    public long getValueFromCorruptedMemory(String memory) {
        Pattern patternMultiply = Pattern.compile("mul\\([1-9]\\d{0,2},[1-9]\\d{0,2}\\)");
        return patternMultiply.matcher(memory).results()
                .map(match -> match.group(0))
                .map(patternNumber::matcher)
                .map(matcher -> (long) matcher.results().map(result -> Integer.parseInt(result.group())).reduce(1, (accum, next) -> accum * next))
                .reduce(Long::sum).orElse((long) 0);
    }

    public long getValueFromCorruptedMemoryWithExtraInstructions(String memory) {
        // Attempt to get all the instances.
        Pattern patternMultiply = Pattern.compile("(mul\\([1-9]\\d{0,2},[1-9]\\d{0,2}\\)|do\\(\\)|don't\\(\\))");
        List<String> instances = patternMultiply.matcher(memory).results().map(match -> match.group(0)).toList();

        // Iterate and handle enable/disable.
        long value = 0;
        boolean enabled = true;
        for (String s : instances) {
            // Flags
            if (s.equals("do()")) enabled = true;
            else if (s.equals("don't()")) enabled = false;
            // Handle the mult.
            else if (enabled) {
                value += patternNumber.matcher(s).results().map(result -> Integer.parseInt(result.group())).reduce(1, (accum, next) -> accum * next);
            }
        }

        return value;
    }
}
