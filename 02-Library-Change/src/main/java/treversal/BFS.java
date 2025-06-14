package traversal;

import graph.Graph;

import java.util.*;

// This class is a client of the `Graph` interface.
// It remains COMPLETELY UNCHANGED because it has no knowledge
// of the underlying graph library (JUNG or JGraphT).
public class BFS<T> {

    private final Graph<T> graph;

    public BFS(Graph<T> graph) {
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
            return order; // empty
        }

        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.offer(startVertex);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            order.add(current);

            for (T neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return order;
    }
}