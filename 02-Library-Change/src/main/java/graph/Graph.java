package graph;

import java.util.Collection;

// This interface is the core of the pattern.
// It decouples the client (traversal algorithms) from the implementation (graph libraries).
// It remains UNCHANGED during the library migration.
public interface Graph<T> {
    void addVertex(T label);
    void addEdge(T from, T to);
    Collection<T> getNeighbors(T label);
    boolean containsVertex(T label);
    Collection<T> getAllVertices();
}