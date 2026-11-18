class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int maxLen = 0;
        int n = s.length();

        Set<Character> set = new HashSet<>();

        for(int right = 0;right < n;right++){
            char cc = s.charAt(right);
            while(set.contains(cc)){
                set.remove(s.charAt(left));
                left++;
            }
            set.add(cc);
            maxLen = Math.max(maxLen, right - left +1);
        }
        return maxLen;
    }
}