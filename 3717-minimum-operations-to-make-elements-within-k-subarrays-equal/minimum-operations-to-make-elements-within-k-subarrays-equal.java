
import java.util.*;

class Solution {

    int n;
    int[] nums;
    long[] coords;
    long[] bitCount, bitSum;
    long totalCount, totalSum;

    void update(long[] bit, int i, long val) {
        while (i < bit.length) {
            bit[i] += val;
            i += i & -i;
        }
    }

    long query(long[] bit, int i) {
        long sum = 0;
        while (i > 0) {
            sum += bit[i];
            i -= i & -i;
        }
        return sum;
    }

    int kth(long k) {
        int idx = 0;
        int step = Integer.highestOneBit(coords.length);

        for (; step > 0; step >>= 1) {
            int next = idx + step;
            if (next < bitCount.length && bitCount[next] < k) {
                idx = next;
                k -= bitCount[next];
            }
        }
        return idx + 1;
    }

    void add(int value, int delta) {
        int idx = Arrays.binarySearch(coords, value) + 1;

        update(bitCount, idx, delta);
        update(bitSum, idx, (long) value * delta);

        totalCount += delta;
        totalSum += (long) value * delta;
    }

    long getCost() {
        int mid = kth((totalCount + 1) / 2);
        long median = coords[mid - 1];

        long leftCount = query(bitCount, mid);
        long leftSum = query(bitSum, mid);

        long rightCount = totalCount - leftCount;
        long rightSum = totalSum - leftSum;

        return median * leftCount - leftSum
                + rightSum - median * rightCount;
    }

    public long minOperations(int[] nums, int x, int k) {
        this.n = nums.length;
        this.nums = nums;

        coords = Arrays.stream(nums)
                .distinct()
                .sorted()
                .asLongStream()
                .toArray();

        bitCount = new long[coords.length + 1];
        bitSum = new long[coords.length + 1];

        long[] cost = new long[n - x + 1];

        // Calculate minimum cost for every window of size x.
        for (int i = 0; i < x; i++) {
            add(nums[i], 1);
        }

        cost[0] = getCost();

        for (int i = x; i < n; i++) {
            add(nums[i - x], -1);
            add(nums[i], 1);

            cost[i - x + 1] = getCost();
        }

        // DP: minimum cost to select j non-overlapping windows.
        long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[k + 1][n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(dp[j], INF);
        }

        for (int j = 1; j <= k; j++) {
            for (int i = 1; i <= n; i++) {

                // Do not select a window ending at i - 1.
                dp[j][i] = dp[j][i - 1];

                // Select window [i-x, i-1].
                if (i >= x) {
                    long prev = dp[j - 1][i - x];

                    if (prev != INF) {
                        dp[j][i] = Math.min(
                                dp[j][i],
                                prev + cost[i - x]
                        );
                    }
                }
            }
        }

        return dp[k][n];
    }
}
