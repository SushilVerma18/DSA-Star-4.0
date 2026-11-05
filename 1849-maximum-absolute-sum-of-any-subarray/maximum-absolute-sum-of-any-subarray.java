class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int maxSum = Math.abs(nums[0]);
        int currentMax = nums[0];
        int currentMin = nums[0];

        for(int i =1;i<nums.length;i++){
            currentMax = Math.max(nums[i],nums[i]+currentMax);
            currentMin = Math.min(nums[i],nums[i]+currentMin);

            maxSum = Math.max(maxSum,Math.max(currentMax,-currentMin));
        }

        return maxSum;
    }
}