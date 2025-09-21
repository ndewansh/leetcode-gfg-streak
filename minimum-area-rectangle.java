// https://leetcode.com/problems/minimum-area-rectangle/description/
class Solution {
    public int minAreaRect(int[][] a) {
        int n = a.length;
        Set<Integer> keys = new HashSet<>(); 
        for(int i = 0; i < n ;i++) {
            int pair = pair(a[i][0], a[i][1]);
            keys.add(pair);
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            for(int j = i+1; j < n; j++) {
                int x1 = a[i][0];
                int y1 = a[i][1];
                int x2 = a[j][0];
                int y2 = a[j][1];
                if(x1 == x2 || y1 == y2) continue;
                // diagonals.
                // x1, y1 = 1, 2
                // x2, y2 = 5, 3
                // for rect to form, we should have
                // x1, y2 = 1, 3 and x2, y1 = 5, 2
                int k1 = pair(x1, y2);
                int k2 = pair(x2, y1);
                if(keys.contains(k1) && keys.contains(k2)) {
                    // it is a rect.
                    int area = Math.abs(x1 - x2) * Math.abs(y1 - y2);
                    min = Math.min(area, min);
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0: min;
    }

    // Cantor Pairing
    public static int pair(int x, int y) {
        return ((x + y) * (x + y + 1)) / 2 + y;
    }
}
