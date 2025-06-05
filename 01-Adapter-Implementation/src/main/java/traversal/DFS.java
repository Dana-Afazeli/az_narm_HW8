// ------------------------------------------------------------
// File: 01-Adapter-Implementation/src/main/java/traversal/DFS.java
// ------------------------------------------------------------

package traversal;

import graph.Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A generic DFS (Depth‐First Search) implementation that works over any {@link Graph<T>}.
 *
 * @param <T> the type of the vertex labels
 */
public class DFS<T> {

    /** The graph on which DFS will run. */
    private final Graph<T> graph;

    /**
     * Construct a DFS traverser on the given graph.
     *
     * @param graph a {@link Graph<T>} implementation (e.g., {@link adapter.jgrapht.JGraphTAdapter})
     * @throws IllegalArgumentException if {@code graph} is null
     */
    public DFS(Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph instance cannot be null");
        }
        this.graph = graph;
    }

    /**
     * Perform DFS beginning from {@code startVertex}. If {@code startVertex} is not
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
            return order; // empty result if start not in graph
        }

        Set<T> visited = new HashSet<>();
        Deque<T> stack = new ArrayDeque<>();

        stack.push(startVertex);

        while (!stack.isEmpty()) {
            T current = stack.pop();
            if (!visited.contains(current)) {
                visited.add(current);
                order.add(current);

                // Push neighbors onto the stack. The exact order of iteration
                // is whatever getNeighbors(...) returns. If you want a consistent
                // left‐to‐right or sorted order, you could sort here.
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
