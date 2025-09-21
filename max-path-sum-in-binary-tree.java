// https://leetcode.com/problems/binary-tree-maximum-path-sum/
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
    int maxPathSum;
    public int maxPathSum(TreeNode root) {
        maxPathSum = Integer.MIN_VALUE;
        pathSum(root);
        return maxPathSum;
    }

    public int pathSum(TreeNode node) {
        if(node == null) return 0;
        int leftSum = pathSum(node.left);
        int rightSum = pathSum(node.right);
        leftSum = leftSum < 0 ? 0 : leftSum;
        rightSum = rightSum < 0 ? 0 : rightSum;
        int sum = leftSum + node.val + rightSum;
        maxPathSum = Math.max(maxPathSum, sum);
        return Math.max(leftSum, rightSum) + node.val;
    }
}
