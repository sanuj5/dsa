package algorithm.tree;

public class Util {

    public static TreeNode addLeft(TreeNode root, int val){
        TreeNode node = new TreeNode(val);
        root.left = node;
        return node;
    }
    public static TreeNode addRight(TreeNode root, int val){
        TreeNode node = new TreeNode(val);
        root.right = node;
        return node;
    }


    /**
     *           10
     *       8        20
     *     2   9
     *   1
     */
    public static TreeNode giveMeTree() {
        TreeNode root = new TreeNode(10);
        TreeNode left1 = Util.addLeft(root, 8);
        TreeNode right1 = Util.addRight(root, 20);
        TreeNode left1left2 = Util.addLeft(left1, 2);
        TreeNode left1Right2 = Util.addRight(left1, 9);
        TreeNode left2left3 = Util.addLeft(left1left2, 1);
        return root;
    }
}
