class Solution {
    public boolean judgeSquareSum(int c) {
        int left = 0;
        int right = (int) Math.sqrt(c);

        while(left<=right){
            if(((long) left * left + (long) right * right) < c ){
                left++;
            }
            else if(((long) left * left + (long) right * right) > c ) {
                right--;
            }
            else{
                return true;
            }
        }

        return false;
    }
}