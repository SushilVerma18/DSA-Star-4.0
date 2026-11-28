class Solution {
    public void moveZeroes(int[] nums) {
        int zero = -1;

        for(int i = 0;i<nums.length;i++){
            if(nums[i] == 0){
                zero = i;
                break;
            }

        }
        if(zero == -1) return;

        for(int j = zero+1;j<nums.length;j++){

            if(nums[j] != 0){
                int temp = nums[zero];
                nums[zero] = nums[j];
                nums[j] = temp;
                zero++;
            }
        }

        
    }
}