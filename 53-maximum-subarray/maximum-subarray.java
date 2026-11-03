class Solution {
    public int maxSubArray(int[] nums) {
        
        int n = nums.length;
        int maxSum = nums[0];
        int cs = nums[0];

        for(int i=1;i<n;i++){
            cs = Math.max(nums[i],cs + nums[i]);
            maxSum = Math.max(cs,maxSum);
        }

        return maxSum;
    }
}