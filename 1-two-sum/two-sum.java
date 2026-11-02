class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        int n = nums.length;

        for(int i =0;i<n;i++){ 
            int res = target - nums[i];
            if(map.containsKey(res)){
                return new int[] {i,map.get(res)};
            }

            map.put(nums[i],i);
            
        }
        return new int[] {-1,-1};
    }
}