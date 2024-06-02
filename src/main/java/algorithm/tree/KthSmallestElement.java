package algorithm.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 */
public class KthSmallestElement {

    public static void main(String[] args) {
        System.out.println(kthSmallest(prepareBst(), 3));

    }

    public static int kthSmallest(TreeNode root, int k) {
        LinkedList<Integer> allElements = new LinkedList<>();
        inoderTraversal(allElements,root,k);
        System.out.println(allElements);
//        return -1;
        return allElements.getLast();
//        Integer result = 0;
//        smallest(root,0,k,result);
//        return result;
    }

    private static void inoderTraversal(List<Integer> allElements, TreeNode root, int k) {
        if(root==null){
            return;
        }
        inoderTraversal(allElements,root.left, k);
        if(allElements.size()<k) {
            allElements.add(root.val);
        }
        inoderTraversal(allElements,root.right, k);
    }

    private static void smallest(TreeNode root,int currentCounter, int k, Integer result){
        if(root == null){
            return ;
        }
        smallest(root.left,currentCounter,k,result);
        currentCounter++;
        System.out.println("Current Counter "+ currentCounter);
        if(currentCounter == k){
            result = root.val;
        }
        smallest(root.right,currentCounter,k,result);
    }

    private static TreeNode prepareBst() {
        TreeNode root = new TreeNode(5);
        TreeNode left1 = Util.addLeft(root, 3);
        TreeNode right1 = Util.addRight(root, 6);
        TreeNode left1left2 = Util.addLeft(left1, 2);
        TreeNode left1Right2 = Util.addRight(left1, 4);
        TreeNode left2left3 = Util.addLeft(left1left2, 1);
        return root;
    }

}
