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

        System.out.println( search(node, 9));
        System.out.println( search(node, 10));
    }
}
