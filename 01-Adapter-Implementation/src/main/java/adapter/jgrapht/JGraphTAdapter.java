package adapter.jgrapht;

import graph.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Collection;
import java.util.stream.Collectors;

public class JGraphTAdapter<T> implements Graph<T> {

    private final DefaultDirectedGraph<T, DefaultEdge> jgraphtGraph;

    public JGraphTAdapter() {
        this.jgraphtGraph = new DefaultDirectedGraph<>(DefaultEdge.class);
    }

    @Override
    public void addVertex(T label) {
        if (label == null) {
            throw new IllegalArgumentException("Vertex label cannot be null");
        }
        if (!jgraphtGraph.containsVertex(label)) {
            jgraphtGraph.addVertex(label);
        }
    }

    @Override
    public void addEdge(T from, T to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Edge endpoints cannot be null");
        }
        addVertex(from);
        addVertex(to);
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
