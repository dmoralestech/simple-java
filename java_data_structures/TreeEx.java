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
            while ((temp) != null) {
                System.out.println(temp.getLeft().getData() + " " + temp.getRight().getData());
                temp = stack.pop();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

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

    public TreeNode<E> inOrderSuccessor(TreeNode root, int d) {

        if (root == null) {
            return null;
        }

        TreeNode<E> successor = null;

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
        TreeNode<String> node = new TreeNode<>("a");
        node.setLeft(new TreeNode("b"));
        node.setRight(new TreeNode("c"));
        /*
                     a
                 b          c
              d    e     f    g
         */

        TreeNode<String> left = node.getLeft();
        TreeNode<String> right = node.getRight();

        left.setLeft(new TreeNode("d"));
        left.setRight(new TreeNode("e"));

        right.setLeft(new TreeNode("f"));
        right.setRight(new TreeNode("g"));

        printNodesWithChildren(node);

    }
}
