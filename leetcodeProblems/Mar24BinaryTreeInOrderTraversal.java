import java.util.*;
import java.util.Stack;
class IterativeSolution {
    List<Integer> inOrderList = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();

    public List<Integer> inorderTraversal(TreeNode root) {
	// using stack DS
        if(root == null) return inOrderList;
        TreeNode node = root;
        while(true){
            if(node != null){
                stack.push(node);
                node = node.left;
            }
            else{
                if(stack.isEmpty()) break;
                node = stack.pop();
                inOrderList.add(node.val);
                node = node.right;
            }
        }
        return inOrderList;
    }
}
class MorrisTraversalSolution {
    public List<Integer> inorderTraversal(TreeNode root) {
        // using morris traversal
        // takes O(1) constant space
        List<Integer> inOrderList = new ArrayList<>();
        TreeNode curr = root;
        while(curr != null){
            if(curr.left == null){
                inOrderList.add(curr.val);
                curr = curr.right;//go to right
            }
            else{
                TreeNode prev = curr.left;
                while(prev.right != null && prev.right != curr){
                    // go to extreme right of the subtree
                    // and the node should not equals to root(curr)
                    prev = prev.right;
                }
                if(prev.right == null){
                    // no thread linking to the root(curr)
                    // make a thread
                    prev.right = curr;
                    curr = curr.left;//do rest traversal
                }
                else{
                    // prev.right is a curr
                    prev.right = null;//cut the thread
                    // add curr to list: as whole left subtree traversal is over
                    inOrderList.add(curr.val);
                    curr = curr.right;//traverse on right subtree
                }
            }
        }
        return inOrderList;
    }
}
