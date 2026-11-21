class Solution {
    //Optimal Approach
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int res[] = new int[n];
        int sum=0;

        if(k > 0){
            for(int i=1;i<=k;i++){
               sum += code[i%n];
            }
            res[0] = sum;

            for(int i=1;i<n;i++){
                sum -= code[i % n];
                sum += code[(i + k)%n];
                res[i] = sum;
            }
        }
        else {
            for(int i = -1; i >= k; i--) {
                sum += code[(i + n) % n];
            }

            for(int i = 0; i < n; i++) {
                res[i] = sum;

                sum -= code[((i + k) % n + n) % n];
                sum += code[i];
            }
        }
        

        return res;
    }
}