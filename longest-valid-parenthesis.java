// https://leetcode.com/problems/longest-valid-parentheses/
class Solution {
    public int longestValidParentheses(String s) {
        int len = s.length();
        Stack<Integer> stack = new Stack<Integer>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int longestValidPar = 0;
        for(int i = 0; i < len; i++) {
            char par = s.charAt(i);
            if(par == '(') {
                stack.push(i);
                continue;
            }
            if(!stack.isEmpty()) {
                // found a valid pair.
                int openParIndex = stack.pop();
                int validPar = i - openParIndex + 1;
                // check for the continous matching pair.
                if(map.containsKey(openParIndex - 1)) 
                    validPar += map.get(openParIndex-1);                                
                map.put(i, validPar);
                longestValidPar = Math.max(longestValidPar, validPar);
            }                        
        }
        return longestValidPar;
    }

}
