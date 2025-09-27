// https://www.youtube.com/watch?v=WBgsABoClE0
class Solution {
    List<List<String>> ans;
    public List<List<String>> partition(String s) {
        // find all palindromes
        int n = s.length();
        ans = new ArrayList<>();
        partition(0, s, new Stack<>());
        return ans;
    }

    public void partition(int i, String s, Stack<String> cur) {
        if(i == s.length()) {
            ans.add(new ArrayList<>(cur));
        }
        for(int j = i; j < s.length(); j++) {
            // check palindrome from i..j and repeat for func(j+1, a);
            if(isPal(s, i, j)) {
                cur.push(s.substring(i, Math.min(s.length(), j+1)));
                partition(j+1, s, cur);
                cur.pop();
            }
        }
    } 

    public boolean isPal(String s, int from, int to) {
        char[] a = s.toCharArray();
        while(from < to) {
            if(a[from] == a[to]) {
                from++;
                to--;
            }
            else {
                return false;
            }
        }
        return true;
    }

    
}
