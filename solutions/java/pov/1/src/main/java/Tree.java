import java.util.*;

class Tree {
    private final String label;
    private final List<Tree> children;

    public Tree(String label) {
        this(label, new ArrayList<>());
    }

    public Tree(String label, List<Tree> children) {
        this.label = label;
        this.children = children;
    }

    public static Tree of(String label) {
        return new Tree(label);
    }

    public static Tree of(String label, List<Tree> children) {
        return new Tree(label, children);
    }

    public String label() {
        return label;
    }

    public List<Tree> children() {
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tree)) return false;
        Tree other = (Tree) o;
        return label.equals(other.label)
            && children.size() == other.children.size()
            && children.containsAll(other.children)
            && other.children.containsAll(children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, children);
    }

    @Override
    public String toString() {
        return "Tree{" + label + ", " + children + "}";
    }


    public Tree fromPov(String target) {
        List<String> path = new ArrayList<>();
        if (!findPathToLabel(this, target, path)) {
            throw new UnsupportedOperationException("Tree could not be reoriented");
        }

        return reRoot(this, path);
    }

    private boolean findPathToLabel(Tree node, String target, List<String> buffer) {
        if (node.label.equals(target)) {
            buffer.add(target);
            return true;
        }

        for (Tree c : node.children) {
            if (findPathToLabel(c, target, buffer)) {
                buffer.add(node.label);
                return true;
            }
        }
        return false;
    }

    private Tree reRoot(Tree root, List<String> path) {
        Collections.reverse(path); 

        Tree current = root;
        Tree newRoot = null;
        Tree parent = null;

        for (int i = 0; i < path.size(); i++) {
            String label = path.get(i);

            if (i > 0) {
                String next = path.get(i);
                for (Tree c : current.children) {
                    if (c.label.equals(next)) {
                        current = c;
                        break;
                    }
                }
            }

            List<Tree> newChildren = new ArrayList<>();

            for (Tree c : current.children) {
                if (!path.contains(c.label)) {  
                    newChildren.add(c.deepCopy());
                }
            }

            if (parent != null) {
                newChildren.add(parent);
            }

            parent = new Tree(label, newChildren);
            if (i == path.size() - 1) {
                newRoot = parent;
            }
        }
        return newRoot;
    }

    private Tree deepCopy() {
        List<Tree> copied = new ArrayList<>();
        for (Tree c : children) {
            copied.add(c.deepCopy());
        }
        return new Tree(label, copied);
    }


    public List<String> pathTo(String fromNode, String toNode) {
        Tree rerooted;
        try {
            rerooted = fromPov(fromNode);
        } catch (UnsupportedOperationException e) {
            throw new UnsupportedOperationException("No path found");
        }

        List<String> path = new ArrayList<>();
        if (!dfsFind(rerooted, toNode, path)) {
            throw new UnsupportedOperationException("No path found");
        }
        return path;
    }

    private boolean dfsFind(Tree node, String target, List<String> path) {
        path.add(node.label);

        if (node.label.equals(target)) {
            return true;
        }

        for (Tree c : node.children) {
            if (dfsFind(c, target, path)) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }
}
