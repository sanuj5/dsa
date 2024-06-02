package algorithm.tree;

import java.util.Stack;

public class PathSum {

    class SumNode{
        Integer sum;
        TreeNode treeNode;

        public SumNode(Integer sum, TreeNode treeNode) {
            this.sum = sum;
            this.treeNode = treeNode;
        }
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        Stack<SumNode> stack = new Stack<>();
        SumNode prev = new SumNode(0, null);
        while(!stack.empty() || root != null){
            while(root != null){
                SumNode tempNode = new SumNode(root.val + prev.sum, root);
                stack.push(tempNode);
                prev = tempNode;
                root = root.left;
            }
            SumNode node = stack.pop();
            if(node.treeNode.left == null && node.treeNode.right ==null && node.sum == targetSum){
                return true;
            }
            prev = node;
            root = node.treeNode.right;
        }
        return false;
    }

    public boolean hasPathSumRecursive(TreeNode root, int targetSum) {
        if(root == null){
            return false;
        }
        if(root.left == null && root.right == null && root.val == targetSum){
            return true;
        }
        return hasPathSumRecursive(root.left, targetSum - root.val) ||
                hasPathSumRecursive(root.right, targetSum - root.val);
    }

    public static void main(String[] args) {
        PathSum p = new PathSum();
        System.out.println(p.hasPathSum(Util.giveMeTree(), 30));
        System.out.println(p.hasPathSumRecursive(Util.giveMeTree(), 27));
    }
}
