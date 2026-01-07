import java.util.*;

class RelativeDistance {

    private final Map<String, Set<String>> graph;

    RelativeDistance(Map<String, List<String>> familyTree) {
        graph = new HashMap<>();

        for (Map.Entry<String, List<String>> entry : familyTree.entrySet()) {
            String parent = entry.getKey();
            List<String> children = entry.getValue();

            graph.putIfAbsent(parent, new HashSet<>());

            for (String child : children) {
                graph.putIfAbsent(child, new HashSet<>());

                graph.get(parent).add(child);
                graph.get(child).add(parent);
            }

            for (int i = 0; i < children.size(); i++) {
                for (int j = i + 1; j < children.size(); j++) {
                    String child1 = children.get(i);
                    String child2 = children.get(j);
                    graph.get(child1).add(child2);
                    graph.get(child2).add(child1);
                }
            }
        }
    }

    int degreeOfSeparation(String personA, String personB) {
        if (!graph.containsKey(personA) || !graph.containsKey(personB)) return -1;
        if (personA.equals(personB)) return 0;

        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> distance = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.add(personA);
        visited.add(personA);
        distance.put(personA, 0);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currDistance = distance.get(current);

            for (String neighbor : graph.getOrDefault(current, Collections.emptySet())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    distance.put(neighbor, currDistance + 1);
                    queue.add(neighbor);

                    if (neighbor.equals(personB)) return currDistance + 1;
                }
            }
        }

        return -1;
    }
}
