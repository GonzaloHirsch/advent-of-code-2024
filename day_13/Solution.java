package day_13;

import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    // Part 1.
    public long getMinimumTokensToWin(List<Input.Machine> machines, long limit) {
        List<Long> l = machines.stream().map(machine -> this.findMinimumTokensAlgebraic(machine, limit)).collect(
                Collectors.toList());
        return machines.stream().map(machine -> this.findMinimumTokensAlgebraic(machine, limit)).reduce(Long::sum).orElse(0L);
    }

    private long findMinimumTokensAlgebraic(Input.Machine machine, long limit) {
        double Bx_By = machine.buttonB.x / (double) machine.buttonB.y;
        double _A = (machine.prize.x - (Bx_By * machine.prize.y))/(machine.buttonA.x - (Bx_By * machine.buttonA.y));
        double _B = (machine.prize.x - ((double)machine.buttonA.x * Math.round((double)_A))) / machine.buttonB.x;
        if (!this.compareFloatWithError(_A, Math.round((double)_A)) || !this.compareFloatWithError(_B, Math.round((double)_B))) return 0;  // Make sure it's integer clicks.
        long A = Math.round((double)_A), B = Math.round((double)_B);
        if (B < 0 || A < 0 || A > limit || B > limit) return 0;
        if (machine.prize.x != (machine.buttonA.x * A + machine.buttonB.x * B) || machine.prize.y != machine.buttonA.y * A + machine.buttonB.y * B) return 0;
        return machine.buttonA.cost * A + machine.buttonB.cost * B;
    }

    private boolean compareFloatWithError(double a, double b) {
        return Math.abs(a-b) < 1e-4;
    }
}
