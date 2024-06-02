package datastructure;

import java.util.LinkedList;
import java.util.Queue;

public class BST<T extends Comparable<T>> {


    public Iterable<T> keys() {
        Queue<T> queue = new LinkedList<>();
        inorder(root, queue);
        return queue;
    }

    public int levelSum() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int maxSum = 0;
        while (!queue.isEmpty()) {
            int sum = 0;
            int counter = queue.size();
            while (counter-- > 0) {
                Node currentNode = queue.remove();
                sum = sum + (Integer) currentNode.value;
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    private void inorder(Node node, Queue<T> queue) {
        if (node == null) {
            return;
        }
        inorder(node.left, queue);
        queue.add(node.value);
        inorder(node.right, queue);
    }

//    public boolean isSumTree(){
//        return isSumTree(root);
//    }
//    private boolean isSumTree(Node node){
//        return node.value = isSumTree(node.left) + no
//    }

    class Node {
        T value;
        Node right;
        Node left;

        Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", right=" + right +
                    ", left=" + left +
                    '}';
        }
    }

    private Node root;

    public void put(T value) {
        root = put(root, value);
    }

    public int height() {
        return getHeight(root);
    }

    private int getHeight(Node node) {
        if (node == null) return 0;
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    private Node put(Node node, T value) {
        if (node == null) return new Node(value);
        int comp = value.compareTo(node.value);
        if (comp < 0) {
            node.left = put(node.left, value);
        } else if (comp > 0) {
            node.right = put(node.right, value);
        }
        return node;
    }

    public T get(T value) {
        Node x = root;
        while (x != null) {
            int comp = value.compareTo(x.value);
            if (comp < 0) {
                x = x.left;
            } else if (comp > 0) {
                x = x.right;
            } else {
                return x.value;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        BST<Integer> st = new BST<>();
        st.put(8);
        st.put(10);
        st.put(12);
        st.put(9);
        st.put(5);
        st.put(3);
        st.put(7);
        st.put(100);
        st.put(200);
//        System.out.println(st.root);
        for (Integer a : st.keys()) {
            System.out.println(a);
        }
        System.out.println(st.height());
        System.out.println(st.levelSum());
    }
}
