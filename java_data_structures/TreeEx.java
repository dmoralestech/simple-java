package java_data_structures;

/**
 * Created by darwinmorales on 23/07/2016.
 */
public class TreeEx<E> {

    public static class TreeNode<E> {

        E data;
        TreeNode left;
        TreeNode right;

        public TreeNode(E data) {
            this.data = data;
        }

        public TreeNode(E data, TreeNode left, TreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }

    public static void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.getData());
        traverse(root.getLeft());
        traverse(root.getRight());
    }

    public static void traverse2(TreeNode root) {
        if (root == null) {
            return;
        }

        try {
            Stack<TreeNode> stack = new ArrayStack<>(10);
            stack.push(root);
            TreeNode temp = root.getLeft();
            while (temp.getLeft() != null) {
                stack.push(temp);
                System.out.println(temp.getData());
                temp = temp.getLeft();
            }

            temp = stack.pop();
            while((temp) != null) {
                System.out.println(temp.getLeft().getData() + " " + temp.getRight().getData());
                temp = stack.pop();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        TreeNode<String> node = new TreeNode<>("a");
        node.setLeft(new TreeNode("b"));
        node.setRight(new TreeNode("c"));

        TreeNode<String> left = node.getLeft();
        TreeNode<String> right = node.getRight();

        left.setLeft(new TreeNode("d"));
        left.setRight(new TreeNode("e"));

        right.setLeft(new TreeNode("f"));
        right.setRight(new TreeNode("g"));

        traverse2(node);
    }
}
