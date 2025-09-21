// 
class Solution {
    public boolean canPartition(int[] a) {
        int sum = Arrays.stream(a).sum();
        if(sum % 2 != 0) return false;
        int half = sum / 2;
        int n = a.length;
        int[] dp = new int[half+1];
        dp[0] = 1;
        for(int i = n-2; i >= 0; i--) {
            for(int j = half; j >= 0; j--) {
                if(j >= a[i])
                    dp[j] = Math.max(dp[j], dp[j-a[i]]);                    
            }
        }
        return dp[half] == 1;
    }
}
