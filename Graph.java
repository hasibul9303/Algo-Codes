import java.util.*;

public class Graph {

    private LinkedList<Integer> adj[];

    /**
     * Constructor to initialize the graph with the given number of vertices.
     *
     * @param v the number of vertices in the graph
     */
    public Graph(int v) {
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<Integer>();
        }
    }

    /**
     * Adds an edge between two vertices in the graph.
     *
     * @param src  the source vertex of the edge
     * @param dest the destination vertex of the edge
     */
    public void addEdge(int src, int dest) {
        adj[src].add(dest);
        adj[dest].add(src);
    }

    /**
     * Performs a Breadth First Search (BFS) traversal starting from a given source
     * vertex.
     *
     * @param src the source vertex for the BFS traversal
     */
    public void bfs(int src) {
        // Initialize an array to keep track of visited vertices
        boolean vis[] = new boolean[adj.length];

        // Create a queue for BFS
        Queue<Integer> q = new LinkedList<>();

        // Mark the current node as visited and enqueue it
        q.add(src);
        vis[src] = true;

        while (!q.isEmpty()) {
            // Dequeue a vertex from the queue and print it
            int cur = q.poll();
            System.out.print(cur + "->");

            // Get all adjacent vertices of the dequeued vertex and process each of them
            for (int neighbor : adj[cur]) {
                if (!vis[neighbor]) {
                    // Mark the neighbor as visited and enqueue it
                    vis[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }
    }

    /**
     * Utility method for performing Depth First Search (DFS) traversal using
     * recursion.
     *
     * @param v the current vertex for DFS traversal
     */
    public void dfsUtil(int v) {
        // Initialize an array to keep track of visited vertices
        boolean vis[] = new boolean[adj.length];
        // Call the recursive helper function for DFS traversal
        dfsRecursive(v, vis);
    }

    /**
     * Recursive helper method for Depth First Search (DFS) traversal.
     *
     * @param v   the current vertex for DFS traversal
     * @param vis an array to keep track of visited vertices
     */
    public void dfsRecursive(int v, boolean vis[]) {
        // Mark the current node as visited and print it
        vis[v] = true;
        System.out.print(v + " -> ");

        // Recur for all the vertices adjacent to this vertex
        for (int neighbor : adj[v]) {
            if (!vis[neighbor]) {
                dfsRecursive(neighbor, vis);
            }
        }
    }

    /**
     * Performs Depth First Search (DFS) traversal using iteration.
     *
     * @param v the source vertex for the DFS traversal
     */
    public void dfsIterative(int v) {
        // Initialize an array to keep track of visited vertices
        boolean vis[] = new boolean[adj.length];
        // Create a stack for DFS
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        vis[v] = true;

        while (!stack.isEmpty()) {
            // Pop a vertex from the stack and print it
            int cur = stack.pop();
            System.out.print(cur + " -> ");

            // Get all adjacent vertices of the popped vertex and process each of them
            for (int neighbor : adj[cur]) {
                if (!vis[neighbor]) {
                    // Mark the neighbor as visited and push it to the stack
                    stack.push(neighbor);
                    vis[neighbor] = true;
                }
            }
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // Read the number of vertices and edges from the user
        System.out.println("Enter the number of vertices: ");
        int v = sc.nextInt();
        System.out.println("Enter the number of edges: ");
        int e = sc.nextInt();

        // Create a graph instance
        Graph graph = new Graph(v);

        // Read and add the edges to the graph
        System.out.println("Enter " + e + " edges: ");
        for (int i = 0; i < e; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();

            graph.addEdge(src, dest);
        }

        // Read the source vertex from the user
        System.out.println("Enter source: ");
        int src = sc.nextInt();
        boolean flag = false;

        while (!flag) {
            // Display the menu for selecting the traversal method
            System.out.println("\nEnter 1 to perform BFS");
            System.out.println("Enter 2 to perform DFS(Recursion)");
            System.out.println("Enter 3 to perform DFS(Iterative)");
            System.out.println("Enter 4 to Exit \n");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    graph.bfs(src);
                    break;

                case 2:
                    graph.dfsUtil(src);
                    break;

                case 3:
                    graph.dfsIterative(src);
                    break;

                case 4:
                    flag = true;
                    break;

                default:
                    System.out.println("Wrong Choice!!");
            }
        }
    }
}