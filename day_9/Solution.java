package day_9;

import java.util.*;

public class Solution {
    // Part 1.
    public long getChecksum(char[] data, boolean verbose) {
        // Iterate all the file/block combinations.
        long total = 0;
        for (int i = 0, j = data.length - 1, position = 0; i < data.length; i++) {
            if (data[i] == 0) continue;
            // Advance the normal blocks.
            // Position is incrementing, and the ID is the index/2 (cause from the left).
            for (; i % 2 == 0 && data[i] > 0; data[i]--) {
                total += (long) (position++) * (i / 2);
                if (verbose) System.out.print((int) (i / 2));
            }
            // Advance the right-hand side blocks.
            // Consume however many empty spaces are.
            while (i % 2 == 1 && data[i] > 0 && j >= 0) {
                // We ran out of blocks. Update the index.
                while (j >= 0 && data[j] <= 0) {
                    j -= 2;
                }
                if (j < 0) break;
                // Update the total;
                total += (long) (position++) * (j / 2);
                if (verbose) System.out.print((int) (j / 2));
                // Decrement the indexes.
                data[i]--;
                data[j]--;
            }
        }
        return total;
    }

    // Part 2.
    private static class DoubleListNode {
        public DoubleListNode prev, next;
        public int type;    // 0 for empty, 1 for file.
        public int size;    // Size of the file or space.
        public int id;    // ID of the file.

        public DoubleListNode(int type, int size, int id) {
            this.type = type;
            this.size = size;
            this.id = id;
        }

        public DoubleListNode addNext(DoubleListNode next) {
            if (this.next != null) this.next.prev = next;
            next.next = this.next;
            this.next = next;
            next.prev = this;
            return next;
        }

        public DoubleListNode removeSelf() {
            if (this.prev != null) this.prev.next = this.next;
            if (this.next != null) this.next.prev = this.prev;
            return this;
        }
    }
    public long getChecksumWithCompleteFragmentation(char[] data, boolean verbose) {
        long total = 0;
        for (int i = 0, position = 0, k; i < data.length; i++) {
            // Compute the move if it is a file index.
            if (i % 2 == 0) {
                total += (i / 2) * position * getFactor(data[i]);
                position += data[i];
                if (verbose) for (int p = 0; p < data[i]; p++) System.out.print(i / 2);
                data[i] = 0;
            }
            // Compute the move for a full file in an empty space index.
            while (i % 2 == 1 && data[i] > 0) {
                // Look for any file that might fit.
                k = data.length - 1;
                while (k > 0 && k > i && (data[i] < data[k] || data[k] == 0)) k -= 2;
                // If we didn't find a match, just skip it.
                if (data[i] < data[k] || k <= i) {
                    if (verbose) for (int p = 0; p < data[i]; p++) System.out.print(".");
                    break;
                }
                // Put the file in the place.
                total += (k / 2) * position * getFactor(data[k]);
                position += data[k];
                if (verbose) for (int p = 0; p < data[k]; p++) System.out.print(k / 2);
                data[i] -= data[k];
                data[k] = 0;
            }
        }
        return total;
    }

    public long getChecksumWithFragmentation(char[] data, boolean verbose) {
        // Prepare the list.
        DoubleListNode[] sides = this.buildList(data);
        if (verbose) this.debugLists(sides);

        // Iterate to fix the list.
        DoubleListNode head = sides[0], tail;
        long total = 0, position = 0;
        while (head != null) {
            // Handle a file.
            if (head.type == 1) {
                total += (head.id) * position * getFactor(head.size);
                position += head.size;
                if (verbose) for (int p = 0; p < head.size; p++) System.out.print(head.id);
            }
            // Handle an empty space
            if (head.type == 0) {
                // Iterate until we can remove the space.
                // CANNOT REMOVE NODES BECAUSE FUCK EVERYTHING UP
                while (head != null && head.size > 0) {
                    tail = sides[1];
                    // Iterate the tail until we find a suitable one.
                    while (tail != null && tail != head && tail.size > head.size) {
                        while (tail != null && tail.type == 0) tail = tail.prev;
                    }
                    // Could not find one with enough space.
                    if (tail == null || tail == head || tail.type == 0) {
                        if (verbose) for (int p = 0; p < head.size; p++) System.out.print(".");
                        break;
                    }
                    // Found one, so we can now put it in the list.
                    tail.removeSelf();
                    if (head.prev != null) head.prev.addNext(tail);
                    // Update size.
                    if (tail.next != null) tail.next.size -= tail.size;
                    if (verbose) for (int p = 0; p < tail.size; p++) System.out.print(tail.id);
                    // If we ran out of space, remove the node.
                    if (tail.next != null && tail.next.size == 0) tail.next.removeSelf();
                    head = tail.next;
                }
            }
            if (head != null) head = head.next;
        }

        return total;
    }

    private DoubleListNode[] buildList(char[] data) {
        DoubleListNode dummy = new DoubleListNode(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE), tail = dummy, node;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 0) continue;
            // Create node.
            if (i % 2 == 0) node = new DoubleListNode(1, (int)data[i], i/2);
            else node = new DoubleListNode(0, (int)data[i], Integer.MIN_VALUE);
            // Add it.
            tail = tail.addNext(node);
        }
        dummy.next.prev = null;
        return new DoubleListNode[]{dummy.next, tail};
    }

    private void debugLists(DoubleListNode[] sides){
        DoubleListNode front = sides[0], back = sides[1];
        while (front != null) {
            System.out.print(front.size);
            front = front.next;
        }
        System.out.println();
        String s = "";
        while (back != null) {
            s = back.size + s;
            back = back.prev;
        }
        System.out.println(s);
    }

    public long getFactor(int n) {
        return ((long) n * (n - 1)) / 2;
    }
}
