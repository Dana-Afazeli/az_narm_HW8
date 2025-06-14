package traversal;

import graph.Graph;

import java.util.*;

// This class is another client of the `Graph` interface.
// Like BFS, it remains COMPLETELY UNCHANGED after the library migration.
public class DFS<T> {

    private final Graph<T> graph;

    public DFS(Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph instance cannot be null");
        }
        this.graph = graph;
    }

    public List<T> traverse(T startVertex) {
        if (startVertex == null) {
            throw new IllegalArgumentException("Start vertex cannot be null");
        }
        List<T> order = new ArrayList<>();
        if (!graph.containsVertex(startVertex)) {
            return order;
        }

        Set<T> visited = new HashSet<>();
        Deque<T> stack = new ArrayDeque<>();

        stack.push(startVertex);

        while (!stack.isEmpty()) {
            T current = stack.pop();
            if (!visited.contains(current)) {
                visited.add(current);
                order.add(current);

                for (T neighbor : graph.getNeighbors(current)) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        return order;
    }
}