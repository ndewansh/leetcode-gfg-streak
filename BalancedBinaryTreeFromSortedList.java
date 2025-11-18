// https://leetcode.com/problems/balance-a-binary-search-tree/

// Create Balanced Binary Tree From a BST
// First do inorder and get the list
// Now construct the Balanced Binary Search Tree using the BST

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
    List<TreeNode> list;
    public TreeNode balanceBST(TreeNode root) {
        list = new ArrayList<>();
        inorder(root);
        return createBalancedBSTFromSortedList(0, list.size() - 1);
    }    

    private void inorder(TreeNode root) {
        if(root == null) return;
        inorder(root.left);
        list.add(root);
        inorder(root.right);        
    }

    // create a tree from the sorted array
    private TreeNode createBalancedBSTFromSortedList(int l, int r) {
        if(l > r) return null;
        int mid = (l + r ) / 2;
        TreeNode root = list.get(mid);
        root.left = createBalancedBSTFromSortedList(l, mid - 1);
        root.right = createBalancedBSTFromSortedList(mid + 1, r);
        return root;
    }
}
