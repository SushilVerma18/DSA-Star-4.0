class Solution {
    public boolean checkString(String s) {
        int b = -1;
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i)=='b'){
                b = i;
                break;
            }
        }

        if(b ==-1 || b==s.length()-1) return true;

        for(int i = b+1;i<s.length();i++){
            if(s.charAt(i)=='a') return false;
        }

        return true;
    }
}