package day_7;

import java.util.List;
import java.util.Map;

public class Solution {
    // Part 1.
    public long getPossibleEquations(List<long[]> data) {
        return data.parallelStream()
                .map(arr -> this.computeEquationPossibility(arr[0], arr, 2, arr[1]))
                .reduce((long) 0, Long::sum);
    }

    private long computeEquationPossibility(long target, long[] nums, int index, long partial) {
        // Verify if we made it to the end.
        if (partial == target && index == nums.length) return target;

        // Not possible already.
        if (partial > target || index >= nums.length) return 0;

        return Math.max(this.computeEquationPossibility(target, nums, index + 1, partial + nums[index]),
                this.computeEquationPossibility(target, nums, index + 1, partial * nums[index]));
    }

    // Part 2

    public long getPossibleEquationsWithExtension(List<long[]> data) {
        return data.parallelStream()
                .map(arr -> this.computeEquationPossibilityWithExtension(arr[0], arr, 2, arr[1]))
                .reduce((long) 0, Long::sum);
    }

    private long computeEquationPossibilityWithExtension(long target, long[] nums, int index, long partial) {
        // Verify if we made it to the end.
        if (partial == target && index == nums.length) return target;

        // Not possible already.
        if (partial > target || index >= nums.length) return 0;

        return Math.max(
                Math.max(
                        this.computeEquationPossibilityWithExtension(target, nums, index + 1, partial + nums[index]),
                        this.computeEquationPossibilityWithExtension(target, nums, index + 1, partial * nums[index])
                ),
                this.computeEquationPossibilityWithExtension(target, nums, index + 1, Long.parseLong(partial + String.valueOf(nums[index])))
            );
    }
}
