package traversal;

import adapter.jgrapht.JGraphTAdapter;
import graph.Graph;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BFSTest {

    @Test
    void testSimpleBFS() {
        Graph<Integer> g = new JGraphTAdapter<>();
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);

        BFS<Integer> bfs = new BFS<>(g);
        List<Integer> order = bfs.traverse(1);
        assertEquals(4, order.size());
        assertTrue(order.contains(1) && order.contains(4));
        assertEquals(1, order.get(0));
    }
}
