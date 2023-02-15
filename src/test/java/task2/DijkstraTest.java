package task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class DijkstraTest {
    private Graph graph;
    private Dijkstra dijkstra;
    private static Graph expected;
    private final List<List<Node>> checkUnsettled = Arrays.asList(
            List.of(new Node("A", 0)),
            List.of(new Node("C", 15), new Node("B", 10)),
            List.of(new Node("C", 15), new Node("D", 22), new Node("F", 25)),
            List.of(new Node("D", 22), new Node("F", 25), new Node("E", 25)),
            List.of(new Node("F", 23), new Node("E", 24)),
            List.of(new Node("E", 24))
    );

    @BeforeAll
    static void init() {
        expected = new Graph();
        expected.addAll(Set.of(
                new Node("A", 0),
                new Node("B", 10),
                new Node("C", 15),
                new Node("D", 22),
                new Node("E", 24),
                new Node("F", 23)
        ));
    }

    @BeforeEach
    public void initGraph() {
        graph = new Graph();
        dijkstra = new Dijkstra();
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        nodeA.addDestination(nodeB, 10);
        nodeA.addDestination(nodeC, 15);
        nodeB.addDestination(nodeD, 12);
        nodeB.addDestination(nodeF, 15);
        nodeC.addDestination(nodeE, 10);
        nodeD.addDestination(nodeE, 2);
        nodeD.addDestination(nodeF, 1);
        nodeF.addDestination(nodeE, 5);

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);
    }

    @Test
    public void testOnFinalDistanceDependsA() {
        dijkstra.calculateShortestPathFromSource(graph.findNode("A").orElse(null));
        graph.getNodes().forEach(node ->
                    Assertions.assertTrue(
                            node.equals(Objects.requireNonNull(expected.findNode(node.getName()).orElse(null)))
                    )
        );
    }

    @Test
    public void testOnIteration() {
        Node source = graph.findNode("A").orElse(null);
        Assertions.assertNotNull(source);

        source.setDistance(0);
        dijkstra.getUnsettledNodes().add(source);
        Node currentNode = dijkstra.getLowestDistanceNode();

        int counter = 0;
        while (dijkstra.unsettledNodes.size() != 0) {
            final List<Node> unsettled = checkUnsettled.get(counter);
            dijkstra.getUnsettledNodes().forEach(node -> {
                Assertions.assertTrue(unsettled.contains(node));
            });
            Assertions.assertEquals(unsettled.size(), dijkstra.getUnsettledNodes().size());
            currentNode = dijkstra.iteration(currentNode);
            Assertions.assertTrue(expected.getNodes().containsAll(dijkstra.getSettledNodes()));
            Assertions.assertEquals(dijkstra.settledNodes.size(), ++counter);
        }
    }
}
