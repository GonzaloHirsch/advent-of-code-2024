package day_2;

import java.util.*;

public class Solution {

    public int getTotalSafeReports(List<Integer[]> reports, int tolerance) {
        return (int) reports.stream().map(report -> this.isReportSafe(report, tolerance)).filter(isSafe -> isSafe).count();
    }

    private boolean isReportSafe(Integer[] report, int tolerance) {
        return this.isReportSafe(report, 1, 0, tolerance) || this.isReportSafe(report, -1, 0, tolerance);
    }

    private boolean isReportSafe(Integer[] report, int direction, int errorCount, int limit) {
        // We went over the limit.
        if (errorCount > limit) return false;

        // Get the first item.
        int prev = Integer.MIN_VALUE, next, diff, start;
        for (start = 0; start < report.length && prev == Integer.MIN_VALUE; start++) prev = report[start];

        for (int i = start; i < report.length; i++) {
            // Skip if we flag the value
            next = report[i];
            if (next == Integer.MIN_VALUE) continue;
            diff = next - prev;

            // In this case, it's an error that we can dampen.
            if (Math.abs(diff) <= 0 || Math.abs(diff) > 3 || diff * direction < 0) {
                // Expand cases and restart after removing values.
                report[i] = Integer.MIN_VALUE;
                if (isReportSafe(report, direction, errorCount + 1, limit)) return true;
                report[i] = next;
                report[i - 1] = Integer.MIN_VALUE;
                if (isReportSafe(report, direction, errorCount + 1, limit)) return true;
                report[i - 1] = prev;
                return false;
            } else {
                prev = next;
            }
        }
        return true;
    }
}
