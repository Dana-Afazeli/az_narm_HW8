package app;

import adapter.jgrapht.JGraphTAdapter;
import graph.Graph;
import traversal.BFS;
import traversal.DFS;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // The ONLY line that changed in the application logic.
        // We are now instantiating our new JGraphTAdapter instead of the old one.
        // The rest of the code works seamlessly because it programs to the `Graph` interface.
        Graph<Integer> graph = new JGraphTAdapter<>();

        // --- The rest of the code is UNCHANGED ---
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);
        graph.addEdge(3, 7);
        graph.addEdge(4, 8);
        graph.addEdge(5, 9);

        System.out.println("--- Running traversals on JGraphT implementation ---");

        BFS<Integer> bfs = new BFS<>(graph);
        List<Integer> bfsOrder = bfs.traverse(1);
        System.out.println("BFS order starting at 1: " + bfsOrder);

        DFS<Integer> dfs = new DFS<>(graph);
        List<Integer> dfsOrder = dfs.traverse(1);
        System.out.println("DFS order starting at 1: " + dfsOrder);
    }
}