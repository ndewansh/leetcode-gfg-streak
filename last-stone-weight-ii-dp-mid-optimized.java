// https://leetcode.com/problems/last-stone-weight-ii/
class Solution {
    public int lastStoneWeightII(int[] a) {
        // creating two bags with stones with min diff stone.
        // spliting the array into two parts of min diff bags.
        int n = a.length;
        int sum = Arrays.stream(a).sum();
        /*
           s5 - (s1 - s2) - (s3 - s4) ..
           s5 - s1 + s2 - s3 + s4 ..
           x = Sk - Sl
           minimize x.
        */
        int[][] dp = new int[n][sum + 1];
        // dp[i][s1] = Math.min(dp[i+1][s1], dp[i+1][s1+a[i]]);
        for(int s1 = 0; s1 <= sum; s1++) {
            int s2 = sum - s1;
            dp[n-1][s1] = Math.abs(s2 - s1);
        }

        for(int i = n-2; i >= 0; i--) {
            for(int s1 = 0; s1 + a[i] <= sum; s1++) {
                dp[i][s1] = Math.min(dp[i+1][s1], dp[i+1][s1 + a[i]]);
            }
        }
        return dp[0][0];
    }
}
