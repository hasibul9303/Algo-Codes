import java.util.Arrays;
import java.util.Scanner;

/**
 * Implementation of Prim's Algorithm to find the Minimum Spanning Tree of a
 * graph.
 */
class Prim_Algo {

    /**
     * Finds the Minimum Spanning Tree using Prim's Algorithm.
     *
     * @param G the adjacency matrix representation of the graph
     * @param V the number of vertices in the graph
     */
    public void Prim(int G[][], int V) {
        int INF = 9999999;
        int no_edge; // number of edges

        boolean[] selected = new boolean[V];
        Arrays.fill(selected, false);
        no_edge = 0;

        selected[0] = true;

        System.out.println("Edge : Weight");

        while (no_edge < V - 1) {
            int min = INF;
            int x = 0; // row number
            int y = 0; // col number

            for (int i = 0; i < V; i++) {
                if (selected[i]) {
                    for (int j = 0; j < V; j++) {
                        if (!selected[j] && G[i][j] != 0) {
                            if (min > G[i][j]) {
                                min = G[i][j];
                                x = i;
                                y = j;
                            }
                        }
                    }
                }
            }

            System.out.println(x + " - " + y + " :  " + G[x][y]);
            selected[y] = true;
            no_edge++;
        }
    }

    /**
     * Main method to execute the Prim's Algorithm.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Prim_Algo g = new Prim_Algo();

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();

        int[][] G = new int[V][V];

        System.out.println("Enter the adjacency matrix:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                G[i][j] = scanner.nextInt();
            }
        }

        g.Prim(G, V);
    }
}
