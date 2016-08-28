package java_data_structures;

import java.util.*;

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
        traverse(root.getRight());
        traverse(root.getLeft());

    }

    public static void inOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }

        try {

            Stack<TreeNode> stack = new ArrayStack<>(10);
            TreeNode temp = root;

            while (!stack.isEmpty() || temp != null) {

                if (temp != null) {
                    stack.push(temp);
                    temp = temp.getLeft();
                    continue;
                }

                System.out.printf(stack.top().getData() + " ");
                temp = stack.pop().getRight();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static TreeNode findMin(TreeNode root) {
        if (root == null) {
            return null;
        }

        while (root.getLeft() != null) {
            root = root.getLeft();
        }

        return root;
    }

    public static boolean search(TreeNode root, int d) {

        if (root == null) {
            return false;
        }

        if ((Integer) root.getData() == d) {
            return true;
        } else {
            if ((Integer) root.getData() < d) {
                return search(root.getRight(), d);
            } else {
                return search(root.getLeft(), d);
            }
        }
    }

    public static TreeNode inOrderSuccessor(TreeNode root, int d) {

        if (root == null) {
            return null;
        }

        TreeNode successor = null;

        while (root != null) {
            if ((Integer) root.getData() < d) {
                root = root.getRight();
            } else if ((Integer) root.getData() > d) {
                successor = root;
                root = root.getLeft();
            } else {
                if (root.getRight() != null) {
                    successor = findMin(root.getRight());
                }
                break;
            }
        }
        return successor;
    }

    private static boolean is_bst_rec(TreeNode root, int minValue, int maxValue) {
        if (root == null) {
            return true;
        }

        if (((Integer) root.getData() < minValue) || ((Integer) root.getData() > maxValue)) {
            return false;
        }

        return is_bst_rec(root.getLeft(), minValue, (Integer) root.getData()) &&
                is_bst_rec(root.getRight(), (Integer) root.getData(), maxValue);
    }

    public static boolean is_Valid_BST(TreeNode root) {
        return is_bst_rec(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static void printNodesWithChildren(TreeNode root) {
        if (root == null) {
            return;
        }

        try {
            TreeNode temp = root;
            Stack<TreeNode> stack = new ArrayStack<>(10);

            while (!stack.isEmpty() || temp != null) {
                if (temp != null) {
                    stack.push(temp);
                    temp = temp.getLeft();
                    continue;
                } else {
                    temp = stack.pop();
                    if (temp != null && (temp.getLeft() != null || temp.getRight() != null)) {
                        System.out.print(temp.getData() + " ");
                    }
                    if (temp.getLeft() != null) {
                        System.out.print(temp.getLeft().getData() + " ");
                    }
                    if (temp.getRight() != null) {
                        System.out.println(temp.getRight().getData());
                    }
                    temp = temp.getRight();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void level_order_traversal(TreeNode root) {
        if (root == null) {
            return;
        }

        java.util.List<java.util.Queue<TreeNode>> queues = new ArrayList<>();

        queues.add(new ArrayDeque<TreeNode>());
        queues.add(new ArrayDeque<TreeNode>());

        java.util.Queue<TreeNode> current_queue = queues.get(0);
        java.util.Queue<TreeNode> next_queue = queues.get(1);

        current_queue.add(root);
        int level_number = 0;

        while (!current_queue.isEmpty()) {
            TreeNode temp = current_queue.poll();
            System.out.print(temp.getData() + ", ");

            if (temp.getLeft() != null) {
                next_queue.add(temp.getLeft());
            }

            if (temp.getRight() != null) {
                next_queue.add(temp.getRight());
            }

            if (current_queue.isEmpty()) {
                System.out.println();
                ++level_number;
                current_queue = queues.get(level_number % 2);
                next_queue = queues.get((level_number + 1) % 2);
            }
        }
        System.out.println();

    }

    boolean matchTree(TreeNode r1, TreeNode r2) {
        if (r1 == null && r2 == null) {
            return true; // nothing left in the subtree
        } else if (r1 == null || r2 == null) {
            return false; // exactly tree is empty, therefore trees don 't match
        } else if (r1.data != r2.data) {
            return false; // data doesn ' t match
        } else {
            return matchTree(r1.left, r2.left) && matchTree(r1.right, r2.right);
        }
    }

    public static void main(String[] args) {
        TreeNode<Integer> node = new TreeNode<>(5);
        node.setLeft(new TreeNode(3));
        node.setRight(new TreeNode(8));
        /*
                     a
                 b          c
              d    e     f    g
         */

        TreeNode<Integer> left = node.getLeft();
        TreeNode<Integer> right = node.getRight();

        left.setLeft(new TreeNode(1));
        left.setRight(new TreeNode(4));

        right.setLeft(new TreeNode(7));
        right.setRight(new TreeNode(10));

        traverse(node);

        inOrderSuccessor(node, 5);

        System.out.println(search(node, 9));
        System.out.println(search(node, 10));
    }
}
