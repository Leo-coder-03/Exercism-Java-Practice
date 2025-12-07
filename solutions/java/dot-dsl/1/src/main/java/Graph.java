import java.util.*;

public class Graph {

    private final Map<String, String> attributes;
    private final Collection<Node> nodes = new ArrayList<>();
    private final Collection<Edge> edges = new ArrayList<>();

    // Empty constructor
    public Graph() {
        this.attributes = new HashMap<>();
    }

    // Constructor with attributes
    public Graph(Map<String, String> attributes) {
        this.attributes = new HashMap<>(attributes);
    }

    // Return nodes
    public Collection<Node> getNodes() {
        return nodes;
    }

    // Return edges
    public Collection<Edge> getEdges() {
        return edges;
    }

    // Add node with no attributes
    public Graph node(String name) {
        nodes.add(new Node(name));
        return this;
    }

    // Add node with attributes
    public Graph node(String name, Map<String, String> attributes) {
        nodes.add(new Node(name, attributes));
        return this;
    }

    // Add edge with no attributes
    public Graph edge(String start, String end) {
        edges.add(new Edge(start, end));
        return this;
    }

    // Add edge with attributes
    public Graph edge(String start, String end, Map<String, String> attributes) {
        edges.add(new Edge(start, end, attributes));
        return this;
    }

    // Return attributes
    public Map<String, String> getAttributes() {
        return attributes;
    }
}
