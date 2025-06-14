package adapter.jgrapht;

import graph.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Collection;
import java.util.stream.Collectors;

// This is the Adapter class for the JGraphT library.
// It implements our application-specific `Graph` interface.
// It holds an instance of the JGraphT graph and translates method calls.
public class JGraphTAdapter<T> implements Graph<T> {

    // The adaptee: the third-party library's graph object.
    private final DefaultDirectedGraph<T, DefaultEdge> jgraphtGraph;

    public JGraphTAdapter() {
        this.jgraphtGraph = new DefaultDirectedGraph<>(DefaultEdge.class);
    }

    @Override
    public void addVertex(T label) {
        if (label == null) {
            throw new IllegalArgumentException("Vertex label cannot be null");
        }
        // The adapter ensures vertices are added before creating an edge.
        if (!jgraphtGraph.containsVertex(label)) {
            jgraphtGraph.addVertex(label);
        }
    }

    @Override
    public void addEdge(T from, T to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Edge endpoints cannot be null");
        }
        // Ensure vertices exist before adding an edge.
        addVertex(from);
        addVertex(to);
        // Translate our `addEdge` call to JGraphT's `addEdge` method.
        jgraphtGraph.addEdge(from, to);
    }

    @Override
    public Collection<T> getNeighbors(T label) {
        if (label == null) {
            throw new IllegalArgumentException("Vertex label cannot be null");
        }
        if (!jgraphtGraph.containsVertex(label)) {
            return java.util.Collections.emptyList();
        }
        // JGraphT provides outgoing edges, we need to map them to target vertices.
        // This is part of the translation logic of the adapter.
        return jgraphtGraph
                .outgoingEdgesOf(label)
                .stream()
                .map(jgraphtGraph::getEdgeTarget)
                .collect(Collectors.toList());
    }

    @Override
    public boolean containsVertex(T label) {
        if (label == null) {
            throw new IllegalArgumentException("Vertex label cannot be null");
        }
        return jgraphtGraph.containsVertex(label);
    }

    @Override
    public Collection<T> getAllVertices() {
        return jgraphtGraph.vertexSet();
    }
}