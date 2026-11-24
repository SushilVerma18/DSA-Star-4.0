class Solution {
    public int maxProduct(int[] nums) {
        int largest = Integer.MIN_VALUE;
        int secondLarg = Integer.MIN_VALUE;
        
        for(int i =0;i<nums.length;i++){
            if(nums[i] >= largest){
                secondLarg = largest;
                largest = nums[i];
            }
            else if(nums[i] > secondLarg){
                secondLarg = nums[i];
            }
        }

        return ((largest - 1) * (secondLarg - 1));
    }
}