package algorithm.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal
 */
public class ZigzagTraversal {

    public static void main(String[] args) {
        System.out.println(zigzagLevelOrder(Util.giveMeTree()));
    }

    private static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> out = new LinkedList<>();
        if(root == null) {
            return out;
        }
        char directionStartFrom = 'R';
        Stack<TreeNode> rStack = new Stack<>();
        Stack<TreeNode> lStack = new Stack<>();
        lStack.push(root);
        while(!rStack.isEmpty() || !lStack.isEmpty()){
            LinkedList<Integer> list = new LinkedList<>();
            if(directionStartFrom =='R'){
                while(!lStack.isEmpty()){
                    TreeNode node = lStack.pop();
                    if(node.left!=null) rStack.push(node.left);
                    if(node.right!=null) rStack.push(node.right);
                    list.add(node.val);
                }
                directionStartFrom = 'L';
            }
            else{
                while(!rStack.isEmpty()){
                    TreeNode node = rStack.pop();
                    if(node.right!=null) lStack.push(node.right);
                    if(node.left!=null) lStack.push(node.left);
                    list.add(node.val);
                }
                directionStartFrom = 'R';
            }
            out.add(list);
        }

        return out;
    }
}
