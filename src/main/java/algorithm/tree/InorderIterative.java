package algorithm.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class InorderIterative {
    public static void main(String[] args) {
        System.out.println(inorderTraversal(Util.giveMeTree()));
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
