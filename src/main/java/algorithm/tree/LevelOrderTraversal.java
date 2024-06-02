package algorithm.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

    public static void main(String[] args) {
        System.out.println(levelOrder(Util.giveMeTree()));
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> out = new LinkedList<>();
        if(root == null) {
            return out;
        }
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        while(!queue.isEmpty()){
            int count = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            for(int i=0;i<count;i++){
                TreeNode node = queue.poll();
                if(node.left!= null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);
                list.add(node.val);
            }
            out.add(list);
        }
        return out;
    }
}
