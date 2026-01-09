import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> root;

    public void insert(T value) {
        root = insertRec(root, value);
    }

    private Node<T> insertRec(Node<T> current, T value) {
        if (current == null) {
            return new Node<>(value);
        }
        if (value.compareTo(current.data) <= 0) {
            current.left = insertRec(current.left, value);
        } else {
            current.right = insertRec(current.right, value);
        }
        return current;
    }

    public List<T> getAsSortedList() {
        List<T> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }

    private void inOrderTraversal(Node<T> node, List<T> result) {
        if (node == null) return;
        inOrderTraversal(node.left, result);
        result.add(node.data);
        inOrderTraversal(node.right, result);
    }

    public List<T> getAsLevelOrderList() {
        List<T> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<T> current = queue.poll();
            result.add(current.data);
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }

        return result;
    }

    public Node<T> getRoot() {
        return root;
    }

    static class Node<T> {
        private final T data;
        private Node<T> left;
        private Node<T> right;

        Node(T data) {
            this.data = data;
        }

        Node<T> getLeft() {
            return left;
        }

        Node<T> getRight() {
            return right;
        }

        T getData() {
            return data;
        }
    }
}
