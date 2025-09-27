class Solution {
    public int countSubstrings(String s) {
        boolean[][] ans = findAllPalindromes(s);
        int count = 0;
        for(int i = 0; i < ans.length; i++) {
            for(int j = 0; j < ans[i].length; j++) {
                if(ans[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean[][] findAllPalindromes(String s) {
        char[] a= s.toCharArray();
        int n = a.length;
        boolean[][] ans = new boolean[n][n];

        for(int i = 0; i < n; i++) {
            ans[i][i] = true;
        }
        
        for(int i = 0; i < n-1; i++) {
            if(a[i] == a[i+1]) {
                ans[i][i+1] = true;
            }
        }

        for(int i = 2; i < n; i++) {
            for(int j = 0; j+i < n; j++) {
                int l = j;
                int r = j+i;
                if(a[l] == a[r]) {
                    ans[l][r] = ans[l+1][r-1];
                }
            }
        }

        return ans;
    }
}
