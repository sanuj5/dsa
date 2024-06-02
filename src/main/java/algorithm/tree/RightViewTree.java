package algorithm.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/
 * Do level order traversal and add last level element to list
 */
public class RightViewTree {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if(root == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int count = queue.size();
            while(count-- > 0){
                TreeNode node = queue.poll();
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
                if(count == 0){
                    list.add(node.val);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        RightViewTree t = new RightViewTree();
        System.out.println(t.rightSideView(Util.giveMeTree()));
    }
}
