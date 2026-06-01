public class FloydWarshallCaseStudy {
    // Define a high value to simulate infinity without causing arithmetic overflow
    private static final int INF = 99999;

    public static void main(String[] args) {
        int n = 4;
        String[] hubs = {"New York (V1)", "London (V2)", "Tokyo (V3)", "Paris (V4)"};

        // Initialize cost matrix matching the case study parameters
        int[][] dist = {
            {0, 5, INF, 10},
            {INF, 0, 3, INF},
            {7, INF, 0, 1},
            {2, INF, INF, 0}
        };

        // Initialize the Predecessor Matrix tracking the child-parent paths
        int[][] parent = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || dist[i][j] == INF) {
                    parent[i][j] = -1; // NIL marker
                } else {
                    parent[i][j] = i;  // Direct connection parent is the origin node
                }
            }
        }

        System.out.println("=====================================================================");
        System.out.println("          FLOYD-WARSHALL GLOBAL LOGISTICS NETWORK OPTIMIZATION        ");
        System.out.println("=====================================================================");
        System.out.println("\n--- Initial State (Matrix D^(0) and Parent Matrix P^(0)) ---");
        printMatrices(dist, parent, n);

        // Run the 3-nested loop Floyd-Warshall relaxation pipeline
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        parent[i][j] = parent[k][j]; // Reassign structural tracking sequence
                    }
                }
            }
            System.out.println("\n--- After Iteration k = " + (k + 1) + " (Using " + hubs[k] + " as intermediate) ---");
            printMatrices(dist, parent, n);
        }

        System.out.println("\n=====================================================================");
        System.out.println("FINAL DETAILED ROUTING PATHS AND EDGES");
        System.out.println("=====================================================================");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    System.out.print("From " + hubs[i] + " to " + hubs[j] + ": ");
                    if (dist[i][j] == INF) {
                        System.out.println("No Path Exists");
                    } else {
                        System.out.print("Cost = " + dist[i][j] + " | Path: ");
                        // Reconstruct path to track consecutive steps accurately
                        printPath(i, j, parent);
                        System.out.println();
                    }
                }
            }
        }
    }

    /**
     * Recursively processes the tracking grid backwards to build the precise forward path chain.
     */
    private static void printPath(int i, int j, int[][] parent) {
        if (i == j) {
            System.out.print("V" + (i + 1));
            return;
        }
        printPath(i, parent[i][j], parent);
        System.out.print(" -> V" + (j + 1));
    }

    /**
     * Prints formatted distance and predecessor tables tracking step-by-step state data.
     */
    private static void printMatrices(int[][] dist, int[][] parent, int n) {
        System.out.println("Distance Matrix D:");
        System.out.print("     ");
        for (int i = 1; i <= n; i++) System.out.format("%-7s", "V" + i);
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print("V" + (i + 1) + "  [");
            for (int j = 0; j < n; j++) {
                if (dist[i][j] == INF) System.out.format("%-7s", "INF");
                else System.out.format("%-7d", dist[i][j]);
            }
            System.out.println("]");
        }

        System.out.println("\nPredecessor/Parent Matrix P (1-indexed labels):");
        System.out.print("     ");
        for (int i = 1; i <= n; i++) System.out.format("%-7s", "V" + i);
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print("V" + (i + 1) + "  [");
            for (int j = 0; j < n; j++) {
                if (parent[i][j] == -1) System.out.format("%-7s", "NIL");
                else System.out.format("%-7s", "V" + (parent[i][j] + 1));
            }
            System.out.println("]");
        }
        System.out.println("--------------------------------------------------");
    }
}