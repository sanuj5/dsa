package algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Symmetric {

    public static void main(String[] args) {
        Symmetric s = new Symmetric();
        s.isSymmetric(null);
    }

    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int counter = queue.size();
            List<TreeNode> list = new ArrayList<>();
            while(counter-->0){
                TreeNode currentNode = queue.remove();
                if(currentNode.left!=null){
                    queue.add(currentNode.left);
                }
                if(currentNode.right!=null){
                    queue.add(currentNode.right);
                }
                list.add(currentNode);
            }
            if (!checkSymmetry(list)){
                return false;
            }
        }
        return  true;
    }

    private boolean checkSymmetry(List<TreeNode> list) {
        int i=0;
        int j=list.size()-1;
        while(i<j){
            if(!(list.get(i++).val == list.get(j--).val)){
                return false;
            }
        }
        return true;
    }
}
