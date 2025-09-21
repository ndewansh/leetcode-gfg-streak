// https://leetcode.com/problems/last-stone-weight-ii/description/
class Solution {
    public boolean canPartition(int[] a) {
        int sum = Arrays.stream(a).sum();
        if(sum % 2 != 0) return false;
        int half = sum / 2;
        int n = a.length;

        int[][] dp = new int[n][half+1];
        for(int i = 0; i < n; i++)
            dp[i][0] = 1; // any index with target = 0 is a valid state. 
        for(int i = n-2; i >= 0; i--) {
            for(int j = 0; j <= half; j++) {
                if(j < a[i]) {
                    dp[i][j] = dp[i+1][j];
                }
                else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i+1][j-a[i]]);
                }
            }
        }
        return dp[0][half] == 1;
    }

    public boolean canTargetHalf(int i, int[] a, int k) {
        if(k == 0) return true;
        if(i == 0) {
            return a[i] == k;
        }
        return canTargetHalf(i-1, a, k) || canTargetHalf(i-1, a, k - a[i]);
    }
}
