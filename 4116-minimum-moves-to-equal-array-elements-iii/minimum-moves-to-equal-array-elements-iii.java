class Solution {
    public int minMoves(int[] nums) {
        int max = Integer.MIN_VALUE;
        int ans = 0;

        for(int num : nums){
           if(max < num){
            max = num;
           }
        }

        for(int num : nums){
            ans += max - num;
        }

        return ans;
    }
}