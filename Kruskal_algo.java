import java.util.*;

/**
 * Implementation of Kruskal's algorithm to find the Minimum Spanning Tree (MST)
 * of a graph.
 */
class Kruskal_algo {
    /**
     * Represents an edge in the graph.
     */
    class Edge implements Comparable<Edge> {
        int src, dest, weight;

        /**
         * Compares two edges based on their weights.
         * 
         * @param compareEdge The edge to compare.
         * @return Negative integer, zero, or positive integer if this edge's weight is
         *         less than, equal to, or greater than the compared edge's weight.
         */
        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    };

    /**
     * Represents a subset (a disjoint set) for the union-find operation.
     */
    class Subset {
        int parent, rank;
    };

    int vertices, edges;
    Edge edge[];

    /**
     * Constructs a KruskalAlgorithm object with the given number of vertices and
     * edges.
     * 
     * @param v The number of vertices in the graph.
     * @param e The number of edges in the graph.
     */
    Kruskal_algo(int v, int e) {
        vertices = v;
        edges = e;
        edge = new Edge[edges];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    /**
     * Finds the parent (representative) of a subset using path compression.
     * 
     * @param subsets The array of subsets.
     * @param i       The index of the subset to find the parent of.
     * @return The parent (representative) of the subset.
     */
    int find(Subset subsets[], int i) {
        // Path compression
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    /**
     * Performs the union operation on two subsets using rank-based merging.
     * 
     * @param subsets The array of subsets.
     * @param x       The root of the first subset.
     * @param y       The root of the second subset.
     */
    void union(Subset subsets[], int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        // Rank-based merging
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    /**
     * Applies Kruskal's algorithm to find the Minimum Spanning Tree (MST) of the
     * graph.
     */
    void kruskalAlgo() {
        Edge result[] = new Edge[vertices];
        int e = 0;
        int i = 0;
        for (i = 0; i < vertices; ++i)
            result[i] = new Edge();

        // Sorting the edges
        Arrays.sort(edge);
        Subset subsets[] = new Subset[vertices];
        for (i = 0; i < vertices; ++i)
            subsets[i] = new Subset();

        // Initialize subsets
        for (int v = 0; v < vertices; ++v) {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }
        i = 0;
        int totalWeight = 0; // Total weight of the MST
        while (e < vertices - 1) {
            Edge nextEdge = edge[i++];
            int x = find(subsets, nextEdge.src);
            int y = find(subsets, nextEdge.dest);
            if (x != y) {
                result[e++] = nextEdge;
                union(subsets, x, y);
                totalWeight += nextEdge.weight;
            }
        }
        // Printing the MST edges
        for (i = 0; i < e; ++i)
            System.out.println(result[i].src + " - " + result[i].dest + ": " + result[i].weight);

        System.out.println("Total weight of the MST: " + totalWeight);

        
        
    }

    /**
     * The main method where the program execution begins.
     * Reads the number of nodes, number of edges, and the edges of the graph from
     * the user.
     * Runs the Kruskal's algorithm to find the MST of the graph.
     * Prints the edges of the MST.
     * Closes the scanner.
     */
    public static void main(String[] args) {
        // Creating a Scanner object to read the input
        Scanner sc = new Scanner(System.in);

        // Reading the number of nodes in the graph
        System.out.println("Enter the number of nodes in the graph:");
        int nodes = sc.nextInt();

        // Reading the number of edges in the graph
        System.out.println("Enter the number of edges in the graph:");
        int edges = sc.nextInt();

        // Creating a graph with the given number of nodes and edges
        Kruskal_algo graph = new Kruskal_algo(nodes, edges);

        // Reading the edges and adding them to the graph
        for (int i = 0; i < edges; i++) {
            System.out.print("Enter the source, destination, and weight of edge " + (i + 1) + ": ");
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int weight = sc.nextInt();

            graph.edge[i].src = src;
            graph.edge[i].dest = dest;
            graph.edge[i].weight = weight;
        }

        // Running the algorithm
        graph.kruskalAlgo();

        // Closing the scanner
        sc.close();
    }
}
