// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    private int solve(TreeNode root, boolean canRob) {
        if (root == null) {
            return 0; // nothing can be stolen
        }

        int leftProfit = 0, rightProfit = 0, profit = 0, maxProfit = 0;
        if (canRob == true) {
            // find the maximum profit if I include this
            leftProfit = solve(root.left, false); // cannot rob next connected node
            rightProfit = solve(root.right, false); // cannot rob next connected node
            profit = root.val + leftProfit + rightProfit;// add profits from both sides
            maxProfit = Math.max(maxProfit, profit);
        }

        else {
            // may be max Profit is not including this node
            leftProfit = solve(root.left, true); // can rob next connected node
            leftProfit = Math.max(leftProfit, solve(root.left, false)); // cannot rob next connected node
            rightProfit = solve(root.right, true); // can rob next connected node
            rightProfit = Math.max(rightProfit, solve(root.right, false)); // cannot rob next connected node
            profit = 0 + leftProfit + rightProfit; // no need to include root's value, only add profits from both sides
            maxProfit = Math.max(maxProfit, profit);

        }
        return maxProfit;
    }

    public int rob(TreeNode root) {
        // using recursion : to find all possible paths
        // TC : exponential(O(2^n)) where n is no. of nodes
        // SC : O(n) recursive stack space
        int includeRoot = solve(root, true); // rob root
        int excludeRoot = solve(root, false); // donot rob root
        return Math.max(includeRoot, excludeRoot); // can have 2 possibilities
    }
}

class MemoizedSolution {
    private int[] solve(TreeNode root){
        int[] options = new int[2];
        // base case: root is null: no profits 
        if(root == null)
            return options; // by default {0,0}
        
        int[] leftNodeChoices = solve(root.left);// find max profits from left subtree
        int[] rightNodeChoices = solve(root.right); // find max profits from right subtree

        // use the formula learned
        // robbCurrNodeProfit = root.val + leftNodeSkippedProfit + rightNodeSkippedProfit
        // skipCurrNodeProfit = 0 + max(leftNoderobbProfit, leftNodeskipProfit) + max(rightNoderobbProfit, rightNodeskipProfit)

        // robb curr node
        options[0] =  root.val + leftNodeChoices[1] + rightNodeChoices[1];
        // skip curr node
        options[1] = 0 + Math.max(leftNodeChoices[0], leftNodeChoices[1]) + Math.max(rightNodeChoices[0],rightNodeChoices[1]);

        return options;
    }
    public int rob(TreeNode root) {
        // using recursion + memoization
        // TC : O(n) -> only visiting each node once
        // SC : O(n) recursive stack space
        int[] options = solve(root);// stores {robbProfit,skipProfit}
        return Math.max(options[0], options[1]); // return robbing root or not robbing is giving me max profit
    }
}