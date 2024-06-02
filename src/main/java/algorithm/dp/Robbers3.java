package algorithm.dp;

import datastructure.TreeNode;

public class Robbers3 {

    static class Sum{
        int s1,s2;
    }
    public int rob(TreeNode root) {
        Sum sum = dfs(root);
        return Math.max(sum.s1, sum.s2);
    }

    public Sum dfs(TreeNode node){
        if(node == null)
            return new Sum();
        Sum lsum = dfs(node.left);
        Sum rsum = dfs(node.right);
        var sum = new Sum();
        sum.s1 = Math.max(lsum.s1, lsum.s1) + Math.max(rsum.s1, rsum.s2);
        sum.s2 = node.val + lsum.s1 + rsum.s1;
        return sum;
    }

    public static void main(String[] args) {
        Robbers3 r = new Robbers3();
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(1,t1, null);
        TreeNode t3 = new TreeNode(2);
        TreeNode t4 = new TreeNode(4,t2, t3);
        System.out.println(r.rob(t4));
    }
}