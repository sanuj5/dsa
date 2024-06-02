package algorithm.tree;

import datastructure.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KthElementFromNode {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int currentDistance = -1;
        while(!queue.isEmpty()){
            currentDistance++;
            int length = queue.size();
            while(length-- > 0){
                TreeNode node = queue.poll();
                if(node.val == target.val){
                    queue = new LinkedList<>();
                    break;
                }
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
        }
        System.out.println(currentDistance);
        return null;
    }

    public static void main(String[] args) {
        KthElementFromNode k = new KthElementFromNode();
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(1,t1, null);
        TreeNode t3 = new TreeNode(2);
        TreeNode t4 = new TreeNode(4,t2, t3);
        k.distanceK(t4,t3,2);
    }
}
