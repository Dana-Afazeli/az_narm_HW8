// ------------------------------------------------------------
// File: 01-Adapter-Implementation/src/main/java/traversal/BFS.java
// ------------------------------------------------------------

package traversal;

import graph.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * A generic BFS (Breadth‐First Search) implementation that works over any {@link Graph<T>}.
 *
 * @param <T> the type of the vertex labels
 */
public class BFS<T> {

    /** The graph on which BFS will run. */
    private final Graph<T> graph;

    /**
     * Construct a BFS traverser on the given graph.
     *
     * @param graph a {@link Graph<T>} implementation (e.g., {@link adapter.jgrapht.JGraphTAdapter})
     * @throws IllegalArgumentException if {@code graph} is null
     */
    public BFS(Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph instance cannot be null");
        }
        this.graph = graph;
    }

    /**
     * Perform BFS beginning from {@code startVertex}. If {@code startVertex} is not
     * in the graph, returns an empty list.
     *
     * @param startVertex the starting vertex label
     * @return a {@link List} of vertex labels in the order they were visited
     * @throws IllegalArgumentException if {@code startVertex} is null
     */
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
