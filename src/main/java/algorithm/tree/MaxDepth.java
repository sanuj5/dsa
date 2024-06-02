package algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

public class MaxDepth {

    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        int currentCount = 0;
        int levelCount = 1;
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
            currentCount++;
            if(currentCount == levelCount){
                currentCount = 0;
                levelCount = queue.size();
                depth++;
            }
        }
        return depth;
    }
}
