// https://www.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1
class Solution {
    static int knapsack(int W, int val[], int wt[]) {
        // code here
        int n = val.length;
        int[][] dp = new int[n][W + 1];
        for(int i = wt[0]; i <= W; i++)
            dp[0][i] = val[0];
        for(int i = 1; i < n; i++) {
            for(int j = 0; j <= W; j++) {
                if(wt[i] > j) {
                    dp[i][j] = dp[i-1][j];
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-wt[i]] + val[i]);
                }
            }
        }
        return dp[n-1][W];
    }
    
    static void print(int[][] dp) {
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
    }
}
