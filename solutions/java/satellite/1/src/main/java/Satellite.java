import java.util.*;

public class Satellite {

    public Tree treeFromTraversals(List<Character> preorder, List<Character> inorder) {
        if (preorder.size() != inorder.size()) {
            throw new IllegalArgumentException("traversals must have the same length");
        }

        Set<Character> preorderSet = new HashSet<>(preorder);
        Set<Character> inorderSet = new HashSet<>(inorder);

        if (preorderSet.size() != preorder.size() || inorderSet.size() != inorder.size()) {
            throw new IllegalArgumentException("traversals must contain unique items");
        }

        if (!preorderSet.equals(inorderSet)) {
            throw new IllegalArgumentException("traversals must have the same elements");
        }

        Node root = build(preorder, inorder);
        return new Tree(root);
    }

    private Node build(List<Character> preorder, List<Character> inorder) {
        if (preorder.isEmpty()) {
            return null;
        }

        char rootVal = preorder.get(0);
        int rootIndex = inorder.indexOf(rootVal);

        List<Character> leftInorder = inorder.subList(0, rootIndex);
        List<Character> rightInorder = inorder.subList(rootIndex + 1, inorder.size());

        List<Character> leftPreorder = preorder.subList(1, 1 + leftInorder.size());
        List<Character> rightPreorder = preorder.subList(1 + leftInorder.size(), preorder.size());

        Node root = new Node(rootVal);
        root.left = build(leftPreorder, leftInorder);
        root.right = build(rightPreorder, rightInorder);

        return root;
    }
}
