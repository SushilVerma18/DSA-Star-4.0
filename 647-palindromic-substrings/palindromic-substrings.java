class Solution {
    public static int checkPalindrome(String s, int left , int right){
        int count = 0;

        while(left >= 0 && right < s.length() && s.charAt(left)==s.charAt(right)){
            left--;
            right++;
            count++;

        }
        return count;
    }

    public int countSubstrings(String s) {
        int count = 0;
        for(int i = 0;i < s.length();i++ ){
            //odd
            count += checkPalindrome(s,i,i);

            //even
            count += checkPalindrome(s,i,i+1);
        }
        return count;
    }
}