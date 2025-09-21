// https://www.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1
class Solution {
    static int knapsack(int W, int val[], int wt[]) {
        // code here
        int n = val.length;
        int[] prev = new int[W+1];
        int[] cur = new int[W+1];
    
        for(int i = wt[0]; i <= W; i++) {
            cur[i] = val[0];
            prev[i] = val[0];
        }
            
        for(int i = 1; i < n; i++) {
            for(int j = 0; j <= W; j++) {
                if(wt[i] > j) {
                    cur[j] = prev[j];
                }
                else {
                    cur[j] = Math.max(prev[j], prev[j-wt[i]] + val[i]);
                }
            }
            prev = cur.clone();
        }
        return cur[W];
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
