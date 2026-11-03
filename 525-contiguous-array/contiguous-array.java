class Solution {
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        int cs = 0;
        int maxLen = 0;

        for(int i =0;i<n;i++){
            if(nums[i]==0) cs--;
            else cs++;

            if(map.containsKey(cs)){
                maxLen = Math.max(maxLen,i-map.get(cs));
            }
            else{
                map.put(cs,i);
            }
        }

        return maxLen;
    }
}