
import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {

        // Available servers: smallest weight, then smallest index
        PriorityQueue<int[]> free = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
                return Integer.compare(a[1], b[1]);
            }
        );

        // Busy servers: earliest free time, then weight, then index
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) return Long.compare(a[0], b[0]);
                if (a[1] != b[1]) return Long.compare(a[1], b[1]);
                return Long.compare(a[2], b[2]);
            }
        );

        int n = servers.length;
        int m = tasks.length;

        // Initially all servers are free
        for (int i = 0; i < n; i++) {
            free.offer(new int[]{servers[i], i});
        }

        int[] ans = new int[m];
        long time = 0;

        for (int i = 0; i < m; i++) {

            // Current time cannot be earlier than task arrival
            time = Math.max(time, i);

            // Release all servers that have completed their tasks
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                long[] server = busy.poll();

                free.offer(new int[]{
                    (int) server[1],
                    (int) server[2]
                });
            }

            // If no server is free, move time forward
            if (free.isEmpty()) {
                time = busy.peek()[0];

                // Release all servers available at this time
                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    long[] server = busy.poll();

                    free.offer(new int[]{
                        (int) server[1],
                        (int) server[2]
                    });
                }
            }

            // Select the best available server
            int[] server = free.poll();

            int weight = server[0];
            int index = server[1];

            ans[i] = index;

            // Server becomes free after completing this task
            busy.offer(new long[]{
                time + tasks[i],
                weight,
                index
            });
        }

        return ans;
    }
}
