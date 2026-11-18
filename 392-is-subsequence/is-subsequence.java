class Solution {
    public boolean isSubsequence(String s, String t) {
        int i = 0;
        int j = 0;

        while(i < s.length() && j < t.length()){
            if(s.charAt(i)==t.charAt(j)){
                i++;
            }
            j++;
        }

        if(i != s.length()) return false;

        return true;

    }
}
        //--------------------BRUTE FORCE -------------------------
        // int prevIndex = -1;
        // for(int i =0;i<s.length();i++){
        //     boolean sequence = false;
        //     for(int j = 0;j<t.length();j++){
        //         if(s.charAt(i)==t.charAt(j) && prevIndex < j){
        //             prevIndex = j;
        //             sequence = true;
        //             break;
        //         }
        //     }

        //     if(!sequence) return false;
        // }

        // return true;