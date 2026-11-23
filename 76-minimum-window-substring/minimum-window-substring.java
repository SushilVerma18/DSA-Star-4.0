class Solution {
    public String minWindow(String s, String t) {
        int[] windowFreq = new int[256];
        int[] targetFreq = new int[256];

        for(char ch : t.toCharArray()){
            targetFreq[ch]++;
        }

        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int startIndex = 0;

        for(int right = 0; right < s.length();right++){
            windowFreq[s.charAt(right)]++;

            while(containsAll(windowFreq,targetFreq)){
                int currLen = right - left +1;
                if(currLen < minLen){
                    minLen = currLen;
                    startIndex = left;
                }  
                windowFreq[s.charAt(left)]--;
                left++; 
            } 
           
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(startIndex,startIndex + minLen);
        
    }

    public boolean containsAll(int[] windowFreq,int[] targetFreq){
        for(int i = 0 ; i<256;i++){
            if(windowFreq[i]<targetFreq[i]){
                return false;
            }
        }

        return true;
    }
}