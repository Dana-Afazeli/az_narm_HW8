package traversal;

import adapter.jgrapht.JGraphTAdapter;
import graph.Graph;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

// The test code also programs to the interface.
// It can test any graph implementation, demonstrating flexibility.
class BFSTest {

    @Test
    void testBfsOnJGraphTAdapter() {
        // Test using the JGraphTAdapter
        Graph<Integer> g = new JGraphTAdapter<>();
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);

        BFS<Integer> bfs = new BFS<>(g);
        List<Integer> order = bfs.traverse(1);

        // Assertions remain the same
        assertEquals(4, order.size());
        assertEquals(List.of(1, 2, 3, 4), order);
        assertEquals(1, order.get(0));
    }
}