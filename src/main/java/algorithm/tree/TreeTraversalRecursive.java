package algorithm.tree;

import java.util.LinkedList;
import java.util.List;

public class TreeTraversalRecursive {
    public static void main(String[] args) {
        traversal(Util.giveMeTree());
    }

    public static void traversal(TreeNode root) {
        List<Integer> inOrderList = new LinkedList<>();
        List<Integer> preOrderList = new LinkedList<>();
        List<Integer> postOrderList = new LinkedList<>();
        inOrder(root, inOrderList);
        preOrder(root, preOrderList);
        postOrder(root, postOrderList);
        System.out.println("In Order: " + inOrderList);
        System.out.println("Pre Order: " + preOrderList);
        System.out.println("Post Order: " + postOrderList);
        System.out.println("Height: " + height(root));
    }

    public static void inOrder(TreeNode root, List<Integer> list)  {
        if(root == null){
            return;
        }
        if(root.left != null){
            inOrder(root.left, list);
        }
        list.add(root.val);
        inOrder(root.right,list);
    }
    public static void preOrder(TreeNode root, List<Integer> list)  {
        if(root == null){
            return;
        }
        list.add(root.val);
        if(root.left != null){
            preOrder(root.left, list);
        }
        preOrder(root.right,list);
    }
    public static void postOrder(TreeNode root, List<Integer> list)  {
        if(root == null){
            return;
        }
        if(root.left != null){
            postOrder(root.left, list);
        }
        postOrder(root.right,list);
        list.add(root.val);
    }

    public static int height(TreeNode root){
        if(root == null){
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
    }
}
