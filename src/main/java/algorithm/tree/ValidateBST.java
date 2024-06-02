package algorithm.tree;

import java.util.Stack;

public class ValidateBST {

    public static void main(String[] args) {
        System.out.println(isValidBST(Util.giveMeTree()));
    }

    public static boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Integer previous = null;
        while(!stack.empty() || root!=null){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(previous != null && previous >= root.val){
                return false;
            }
            previous = root.val;
            root = root.right;
        }
        return true;
    }


}
