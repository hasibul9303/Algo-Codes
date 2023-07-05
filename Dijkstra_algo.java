// Importing the necessary classes
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// A class to represent a node in the graph
class Node implements Comparable<Node> {
    // The node id
    public int id;
    // The distance from the source node
    public int distance;

    // Constructor to initialize the node
    public Node(int id, int distance) {
        this.id = id;
        this.distance = distance;
    }

    // A method to compare two nodes based on their distance
    public int compareTo(Node other) {
        return this.distance - other.distance;
    }
}

// A class to represent a weighted edge in the graph
class Edge {
    // The source node of the edge
    public int source;
    // The destination node of the edge
    public int destination;
    // The weight of the edge
    public int weight;

    // Constructor to initialize the edge
    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

// A class to represent a graph using adjacency list

class Dijkstra_Algo {
    // The number of nodes in the graph
    public int nodes;
    // The adjacency list to store the edges
    public List<List<Edge>> adjList;

   /**
     * Constructor to initialize the graph with a given number of nodes.
     * @param nodes The number of nodes in the graph.
     * @throws IllegalArgumentException If the number of nodes is negative or zero.
     */
    public Dijkstra_Algo(int nodes) {
        this.nodes = nodes;
        adjList = new ArrayList<>();
        // Initialize the adjacency list for each node
        for (int i = 0; i < nodes; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    /** A method to add an edge to the graph with a given source, destination and weight.
     * @param source The id of the source node of the edge.
     * @param destination The id of the destination node of the edge.
     * @param weight The weight of the edge.
     * @throws IllegalArgumentException If the source or destination node is invalid or out of range, or if the weight is negative.
     */
    public void addEdge(int source, int destination, int weight) {
        // Add the edge to the source node's list
        adjList.get(source).add(new Edge(source, destination, weight));
        // Add the reverse edge to the destination node's list (for undirected graph)
        adjList.get(destination).add(new Edge(destination, source, weight));
    }

    /**
     * A method to implement Djikstra Algorithm to find the shortest path from a source node to all other nodes in the graph.
     * @param source The id of the source node.
     * @throws IllegalArgumentException If the source node is invalid or out of range.
     */
    public void djikstra(int source) {
        // A boolean array to mark visited nodes
        boolean[] visited = new boolean[nodes];
        // A priority queue to store the nodes with their distances
        PriorityQueue<Node> queue = new PriorityQueue<>();
        // An array to store the parent of each node in the shortest path tree
        int[] parent = new int[nodes];
        // An array to store the distance of each node from the source node
        int[] distance = new int[nodes];

        // Initialize all distances as infinity and all parents as -1
        for (int i = 0; i < nodes; i++) {
            distance[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }

        // Initialize the source node's distance as 0 and add it to the queue
        distance[source] = 0;
        queue.add(new Node(source, 0));

        // Loop until the queue is empty or all nodes are visited
        while (!queue.isEmpty() && !allVisited(visited)) {
            // Poll the node with the minimum distance from the queue and mark it as visited
            Node node = queue.poll();
            visited[node.id] = true;

            // Loop through all the adjacent edges of the node
            for (Edge edge : adjList.get(node.id)) {
                // If the destination node is not visited and its distance can be updated
                if (!visited[edge.destination] && distance[node.id] + edge.weight < distance[edge.destination]) {
                    // Update its distance and parent and add it to the queue
                    distance[edge.destination] = distance[node.id] + edge.weight;
                    parent[edge.destination] = node.id;
                    queue.add(new Node(edge.destination, distance[edge.destination]));
                }
            }
        }

        // Print the shortest path tree and distances from the source node
        printShortestPathTree(source, parent, distance);
    }

    /**
     * A helper method to check if all nodes are visited in the graph.
     * @param visited A boolean array to indicate the visited status of each node.
     * @return True if all nodes are visited, false otherwise.
     */
    public boolean allVisited(boolean[] visited) {
        for (boolean v : visited) {
            if (!v) return false;
        }
        return true;
    }

    /**
     * A helper method to print the shortest path tree and distances from the source node in the graph.
     * @param source The id of the source node.
     * @param parent An array to store the parent of each node in the shortest path tree.
     * @param distance An array to store the distance of each node from the source node.
     */
    public void printShortestPathTree(int source, int[] parent, int[] distance) {
        System.out.println("Distances from node " + source + ":");
        for (int i = 0; i < nodes; i++) {
            System.out.println("Node " + i + ": " + distance[i]);
        }
    }
    public static void main(String[] args) {
        // Creating a Scanner object to read the input
        Scanner sc = new Scanner(System.in);

        // Reading the number of nodes in the graph
        System.out.println("Enter the number of nodes in the graph:");
        int nodes = sc.nextInt();

        // Creating a graph with the given number of nodes
        Dijkstra_Algo graph = new Dijkstra_Algo(nodes);

        // Reading the number of edges in the graph
        System.out.println("Enter the number of edges in the graph:");
        int edges = sc.nextInt();

        // Reading the edges and adding them to the graph
        
        for (int i = 0; i < edges; i++) {
            System.out.print("Enter the source, destination, and weight of edge " + (i + 1) + ": ");
            int source = sc.nextInt();
            int destination = sc.nextInt();
            int weight = sc.nextInt();
            graph.addEdge(source, destination, weight);
        }

        // Reading the source node for the algorithm
        System.out.println("Enter the source node for Djikstra Algorithm:");
        int source = sc.nextInt();

        // Running the algorithm from the source node
        graph.djikstra(source);

        // Closing the scanner
        sc.close();
    }
}