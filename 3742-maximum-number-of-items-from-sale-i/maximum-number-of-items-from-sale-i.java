
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;
        int MAX = 1500;

        // Step 1: Frequency of each factor
        int[] freq = new int[MAX + 1];

        for (int[] item : items) {
            freq[item[0]]++;
        }

        // Step 2: Calculate free copies for each factor
        int[] free = new int[MAX + 1];

        for (int f = 1; f <= MAX; f++) {
            int count = 0;

            for (int j = f; j <= MAX; j += f) {
                count += freq[j];
            }

            free[f] = count - 1;
        }

        // Step 3: Group items by price
        ArrayList<Integer>[] groups = new ArrayList[MAX + 1];

        for (int i = 0; i < n; i++) {
            int price = items[i][1];

            if (groups[price] == null) {
                groups[price] = new ArrayList<>();
            }

            groups[price].add(1 + free[items[i][0]]);
        }

        // Step 4: 0/1 Knapsack DP
        int NEG = -1000000000;

        int[] dp = new int[budget + 1];
        Arrays.fill(dp, NEG);
        dp[0] = 0;

        int ans = 0;

        // Process prices from highest to lowest
        for (int p = MAX; p >= 1; p--) {

            if (groups[p] == null) {
                continue;
            }

            int[] cur = new int[budget + 1];
            Arrays.fill(cur, NEG);

            // Select at least one item with price p
            for (int value : groups[p]) {

                for (int cost = budget; cost >= p; cost--) {

                    // Select this item with higher-priced items
                    if (dp[cost - p] != NEG) {
                        cur[cost] = Math.max(
                            cur[cost],
                            dp[cost - p] + value
                        );
                    }

                    // Select this item with previously selected
                    // items of the same price group
                    if (cur[cost - p] != NEG) {
                        cur[cost] = Math.max(
                            cur[cost],
                            cur[cost - p] + value
                        );
                    }
                }
            }

            // Step 5: Buy additional copies at price p
            for (int cost = 0; cost <= budget; cost++) {

                if (cur[cost] != NEG) {

                    int extra = (budget - cost) / p;

                    ans = Math.max(
                        ans,
                        cur[cost] + extra
                    );

                    // Merge current group into DP
                    dp[cost] = Math.max(dp[cost], cur[cost]);
                }
            }
        }

        return ans;
    }
}
