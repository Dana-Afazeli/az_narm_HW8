package graph;

import java.util.Collection;

public interface Graph<T> {
    void addVertex(T label);
    void addEdge(T from, T to);
    Collection<T> getNeighbors(T label);
    boolean containsVertex(T label);
    Collection<T> getAllVertices();
}
