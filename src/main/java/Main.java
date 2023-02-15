import task2.Dijkstra;
import task2.Graph;
import task2.Node;

public class Main {

  public static void main(String[] args) {
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

    Graph graph = new Graph();

    graph.addNode(nodeA);
    graph.addNode(nodeB);
    graph.addNode(nodeC);
    graph.addNode(nodeD);
    graph.addNode(nodeE);
    graph.addNode(nodeF);

    Dijkstra.calculateShortestPathFromSource(nodeA);
    System.out.println(nodeA.getShortestPath());
    System.out.println(nodeB.getShortestPath());
    System.out.println(nodeC.getShortestPath());
    System.out.println(nodeD.getShortestPath());
    System.out.println(nodeE.getShortestPath());
    System.out.println(nodeF.getShortestPath());
  }
}
