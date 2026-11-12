class Solution {
    public void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public void reverseString(char[] s) {
        int l = 0;
        int r = s.length-1;

        while(l <= r){
            swap(s,l,r);
            l++;
            r--;
        }
    }
}