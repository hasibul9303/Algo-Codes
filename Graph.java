import java.util.*;
public class Graph {

    private LinkedList<Integer> adj[];
    
    public Graph(int v) {
        adj = new LinkedList[v];
        for(int i=0; i<v; i++) {
            adj[i] = new LinkedList<Integer>();
        }
    }
    
    public void addEdge(int src, int dest) {
        adj[src].add(dest);
        adj[dest].add(src);
    }

    public int bfs(int src, int dest) {
        boolean vis[] = new boolean[adj.length];
        int parent[] = new int[adj.length];

        Queue<Integer> q = new LinkedList<>();

        q.add(src);
        vis[src] = true;
        parent[src] = -1;
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            System.out.print(cur + "->");
            if(cur == dest)
                break;
            for(int neighbor: adj[cur]) {
                if(!vis[neighbor]) {
                    vis[neighbor] = true;
                    parent[neighbor] = cur;
                    q.add(neighbor);
                }
            }
        }

        int cur = dest;
        int distance = 0;
        System.out.println("\n Traversal path from source to destination");
        while(parent[cur]!=-1) {
            System.out.print(cur + " -> ");
            cur = parent[cur];
            distance++;
        }
        return distance;
    }

    public void dfsUtil(int v) {
        boolean vis[] = new boolean[adj.length];
        dfs(v, vis);
    }

    public void dfs(int v, boolean vis[]) {
        vis[v] = true;
        System.out.print(v + " -> ");

        for(int neighbor:adj[v]) {
            if(!vis[neighbor]) {
                dfs(neighbor, vis);
            }
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of vertices: ");
        int v = sc.nextInt();
        System.out.println("Enter the number of edges: ");
        int e = sc.nextInt();

        Graph graph = new Graph(v);

        System.out.println("Enter " + e + " edges: ");
        for(int i=0; i<e; i++) {
            int src = sc.nextInt();
            int dest= sc.nextInt();

            graph.addEdge(src, dest);
        }

        System.out.println("Enter source: ");
        int src = sc.nextInt();
        // System.out.println("Enter destination:");
        // int dest = sc.nextInt();

        // System.out.println("Following is Breadth First Traversal " + "(starting from vertex " + src + ")");
        // int distance = graph.bfs(src, dest);
        // System.out.println(" \n Minimum distance is " + distance);

        graph.dfsUtil(v);


    }
}
