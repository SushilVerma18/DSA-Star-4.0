package medium;

public class P12_HollowIncTri {
    static void main(String[] args) {
        for(int i=1;i<=5;i++)
        {
            int s =0;
            for(int j = 1; j<=i; j++)
            {
                if(i==5 || i==1) System.out.print("*");
                else {
                    if (j == 1) {
                        System.out.print("*");
                    }
                    if (s < i - 2) {
                        System.out.print(" ");
                        s++;
                    }
                }
            }
           if (i!=1 && i!=5)  System.out.print("*");
            System.out.println();
        }
    }
}
