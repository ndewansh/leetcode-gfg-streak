// https://leetcode.com/problems/recover-binary-search-tree/
// https://www.geeksforgeeks.org/problems/fixed-two-nodes-of-a-bst/1
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    TreeNode prev;
    TreeNode first;
    TreeNode mid;
    TreeNode last;
    public void recoverTree(TreeNode root) {
        inorder(root);
        if(last == null) {
            last = mid;
        }
        int temp = first.val;
        first.val = last.val;
        last.val = temp;
    }

    public void inorder(TreeNode temp) {
        if(temp == null) return;
        inorder(temp.left);
        if(prev != null && temp.val < prev.val) {
            // violation.
            if(first == null) {
                // first violation.
                first = prev;
                mid = temp;                
            }
            else {
                // already a violation, swap the nodes.
                last = temp;
            }
        }
        prev = temp;
        inorder(temp.right);
    }
}
