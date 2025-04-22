package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/148652 lv2

public class AnalogousCantorSet {
    // pow5[i] = 5^i
    long[] pow5;
    // pow4[i] = 4^i (number of 1s in the i-th sequence)
    long[] pow4;

    public int solution(int n, long l, long r) {
        // Initialize powers of 5 and 4 up to n
        pow5 = new long[n + 1];
        pow4 = new long[n + 1];
        pow5[0] = 1;
        pow4[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow5[i] = pow5[i - 1] * 5;
            pow4[i] = pow4[i - 1] * 4;
        }

        // Calculate the number of 1s up to r and subtract the number of 1s up to l-1
        // This gives the count of 1s in the closed interval [l, r]
        long answer = countOnesUpTo(n, r) - countOnesUpTo(n, l - 1);
        return (int)answer;
    }

    /**
     * Calculates the number of '1's in the n-th pseudo-Cantor bit string
     * from index 1 up to the given index (inclusive).
     *
     * @param n The level of the pseudo-Cantor bit string (0-based).
     * @param index The 1-based end index (inclusive).
     * @return The total count of '1's up to the index.
     */
    private long countOnesUpTo(int n, long index) {
        // Base case: If index is 0 or less, there are no 1s.
        if (index <= 0) {
            return 0;
        }
        // Base case: The 0-th string is "1". If index >= 1, it contains one '1'.
        if (n == 0) {
            return 1;
        }

        long len = pow5[n - 1]; // Length of each of the 5 sub-segments
        long countInSub = pow4[n - 1]; // Number of 1s in a full sub-segment (n-1 level)

        // Determine which segment the index falls into (0-based)
        long segmentIndex = (index - 1) / len;
        // Determine the relative position within that segment (1-based)
        long remainderIndex = (index - 1) % len + 1;

        long count = 0;

        // Calculate the count of 1s from the full segments *before* the current segment
        // Segments 0, 1, 3, 4 are based on the (n-1)th string (countInSub ones each)
        // Segment 2 is all zeros ("00000")
        if (segmentIndex > 0) {
            // Add ones from segment 0
            count += countInSub;
        }
        if (segmentIndex > 1) {
            // Add ones from segment 1
            count += countInSub;
        }
        // Segment 2 (index 2) has no ones, so nothing is added if segmentIndex > 2
        if (segmentIndex > 3) {
            // Add ones from segment 3
            count += countInSub;
        }
        if (segmentIndex > 4) {
            // Add ones from segment 4
            count += countInSub;
        }

        // Add the count of 1s from the *current* segment (up to remainderIndex)
        // If the current segment is segment 2 (index 2), it's all zeros, so we add nothing.
        if (segmentIndex != 2) {
            // Recursively find the count within the corresponding (n-1)th string
            count += countOnesUpTo(n - 1, remainderIndex);
        }

        return count;
    }

    // 2	4	17	8
    @Test
    public void test1() {
        int n = 2;
        long l = 4;
        long r = 17;
        int result = solution(n, l, r);
        Assertions.assertThat(result).isEqualTo(8);
    }

    // 20   1 2
    @Test
    public void test2() {
        int n = 20;
        long l = 1;
        long r = 2;
        int result = solution(n, l, r);
        Assertions.assertThat(result).isEqualTo(2);
    }

    // 20   1 1
    @Test
    public void test3() {
        int n = 20;
        long l = 1;
        long r = 2;
        int result = solution(n, l, r);
        Assertions.assertThat(result).isEqualTo(2);
    }

    // 20   6, 7
    @Test
    public void test4() {
        int n = 20;
        long l = 11;
        long r = 12;
        int result = solution(n, l, r);
        Assertions.assertThat(result).isEqualTo(0);
    }
}