class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int ans = 0;
        int cs = 0;

        for (int num : nums) {
            cs += num;

            int rem = ((cs % k) + k) % k;

            if (map.containsKey(rem)) {
                ans += map.get(rem);
            }

            map.put(rem, map.getOrDefault(rem, 0) + 1);
        }

        return ans;
    }
}