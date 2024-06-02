package algorithm.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/recover-binary-search-tree/
 * Hint: Keep three pointers while doing inorder traversal.
 * Store first and second for first match, last for second match
 * if last pointer null swap first & second, else swap first & last
 */
public class RecoverBST {
    public static void main(String[] args) {
        TreeNode treeNode = Util.giveMeTree();
        System.out.println(inorderTraversal(treeNode));
        recoverTree(treeNode);
        System.out.println(inorderTraversal(treeNode));
    }

    public static void recoverTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;
        TreeNode previous = null;
        TreeNode swap1=null,swap2=null,swap3=null;
        while(!stack.isEmpty() || temp!=null){
            while(temp!=null){
                stack.push(temp);
                temp = temp.left;
            }
            temp = stack.pop();
            if(previous != null && previous.val > temp.val){
                if(swap1 == null ){
                    swap1 = previous;
                    swap2 = temp;
                }
                else{
                    swap3 = temp;
                }
            }
            previous = temp;
            temp = temp.right;
        }
        int tempSwap = swap1.val;
        if(swap3!=null){
            swap1.val=swap3.val;
            swap3.val=tempSwap;
        }
        else{
            swap1.val=swap2.val;
            swap2.val=tempSwap;
        }
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new LinkedList<>();
        while(!stack.isEmpty() || root != null){
            while(root!=null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }
}
