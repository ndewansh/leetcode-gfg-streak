class Solution {
    public int longestPalindromeSubseq(String s) {
        char[] a = s.toCharArray();
        int n = a.length;
        String[][] dp = new String[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                dp[i][j] = "";
            }
        }
        for(int i = 0; i < n; i++) {
            dp[i][i] = ""+a[i];
        }                

        for(int i = 0; i < n-1; i++) {
            if(a[i] == a[i+1]) {
                dp[i][i+1] = "" + a[i] + a[i+1];
            }
            else {
                dp[i][i+1] = dp[i][i];
            }
        }
        
        for(int i = 2; i < n; i++) {
            for(int j = 0; j + i < n; j++) {
                int l = j;
                int r = j+i;
                if(a[l] == a[r]) {
                    dp[l][r] = a[l] + dp[l+1][r-1] + a[r];
                }
                else {
                    String s1 = dp[l+1][r];
                    String s2 = dp[l][r-1];
                    // System.out.println("s1 = "+s1+" , s2 = "+s2);
                    if(s1.length() > s2.length()) {
                        dp[l][r] = s1;
                    }
                    else {
                        dp[l][r] = s2;
                    }
                }
            }
        }
        // print(dp, n);
        return dp[0][n-1].length();
    }

    public void print(String[][] dp, int n) {
        for(int i = 0; i < n ; i++) {
            for(int j = 0; j < n; j++) {
                System.out.println("LPS from "+i+", "+j+" = "+dp[i][j]);
            }
            System.out.println();
        }
    }
}




/*
WITH ONLY INTEGERS

class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        char[] a = s.toCharArray();
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++){
            dp[i][i] = 1;
        }
        for(int i = 1; i < n; i++){
            if(a[i-1] == a[i]){
                dp[i-1][i] = 2;
            }
            else {
                dp[i-1][i] = 1;
            }
        }
        for(int i = 2; i < n; i++){
            for(int j = 0; j <= n - i - 1; j++){
                if(a[j] == a[j+i]){
                    dp[j][j+i] = dp[j+1][j+i-1] + 2;
                }
                else {
                    dp[j][j+i] = Math.max(dp[j][j+i-1], dp[j+1][j+i]);
                }
            }
        }
        // for(int i = 0; i < n; i++){
        //     for(int j = 0; j < n; j++)
        //         System.out.print(dp[i][j]+" ");
        //     System.out.println();
        // }
        return dp[0][n-1];
    }
}



*/
