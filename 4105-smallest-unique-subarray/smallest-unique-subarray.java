
import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int N = n + 1;

        // Step 1: Build suffix array
        int[] s = new int[N];

        for (int i = 0; i < n; i++) {
            s[i] = nums[i];
        }

        s[n] = 0;

        int[] p = new int[N];
        int[] c = new int[N];

        int[] cnt = new int[Math.max(100001, N)];

        for (int x : s) {
            cnt[x]++;
        }

        for (int i = 1; i < 100001; i++) {
            cnt[i] += cnt[i - 1];
        }

        for (int i = N - 1; i >= 0; i--) {
            p[--cnt[s[i]]] = i;
        }

        int classes = 1;
        c[p[0]] = 0;

        for (int i = 1; i < N; i++) {
            if (s[p[i]] != s[p[i - 1]]) {
                classes++;
            }

            c[p[i]] = classes - 1;
        }

        int[] pn = new int[N];
        int[] cn = new int[N];

        // Doubling algorithm
        for (int len = 1; len < N; len <<= 1) {

            for (int i = 0; i < N; i++) {
                pn[i] = p[i] - len;

                if (pn[i] < 0) {
                    pn[i] += N;
                }
            }

            Arrays.fill(cnt, 0, classes, 0);

            for (int i = 0; i < N; i++) {
                cnt[c[pn[i]]]++;
            }

            for (int i = 1; i < classes; i++) {
                cnt[i] += cnt[i - 1];
            }

            for (int i = N - 1; i >= 0; i--) {
                int x = pn[i];
                p[--cnt[c[x]]] = x;
            }

            cn[p[0]] = 0;
            int newClasses = 1;

            for (int i = 1; i < N; i++) {
                int cur = p[i];
                int prev = p[i - 1];

                int mid = (cur + len) % N;
                int midPrev = (prev + len) % N;

                if (c[cur] != c[prev] ||
                    c[mid] != c[midPrev]) {
                    newClasses++;
                }

                cn[cur] = newClasses - 1;
            }

            int[] temp = c;
            c = cn;
            cn = temp;

            classes = newClasses;

            if (classes == N) {
                break;
            }
        }

        // Step 2: Remove sentinel
        int[] sa = new int[n];

        for (int i = 1; i < N; i++) {
            sa[i - 1] = p[i];
        }

        // Step 3: Build inverse suffix array
        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }

        // Step 4: Kasai's LCP algorithm
        int[] lcp = new int[n];
        int h = 0;

        for (int i = 0; i < n; i++) {
            int r = rank[i];

            if (r == 0) {
                h = 0;
                continue;
            }

            int j = sa[r - 1];

            while (i + h < n &&
                   j + h < n &&
                   nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[r] = h;

            if (h > 0) {
                h--;
            }
        }

        // Step 5: Find minimum unique subarray length
        int ans = n;

        for (int r = 0; r < n; r++) {
            int longest = 0;

            if (r > 0) {
                longest = Math.max(longest, lcp[r]);
            }

            if (r < n - 1) {
                longest = Math.max(longest, lcp[r + 1]);
            }

            int required = longest + 1;

            // Ensure the subarray fits
            if (required <= n - sa[r]) {
                ans = Math.min(ans, required);
            }
        }

        return ans;
    }
}
