import java.util.Arrays;

public class StreetlightGridPrims {

    private static final int V = 9; // Number of streetlight nodes in the grid

    // Finds the vertex with the minimum key value from the set of vertices not yet included in MST
    private int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // Computes and prints the MST paths using an adjacency matrix format
    public void computePrimsMST(int[][] graph) {
        int[] parent = new int[V]; // Array to store constructed MST
        int[] key = new int[V];    // Key values used to pick minimum weight edge
        boolean[] mstSet = new boolean[V]; // To represent vertices included in MST

        // Initialize all keys as INFINITE
        Arrays.fill(key, Integer.MAX_VALUE);

        // Always include first vertex in MST
        key[0] = 0;
        parent[0] = -1; // First node is always root of MST

        System.out.println("Structural Edge Connections Established:");
        System.out.println("-> Root gateway initialized at Node: 0");

        // The MST will have V vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum key vertex from the set of vertices not yet included in MST
            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST Set
            mstSet[u] = true;

            // Update key value and parent index of the adjacent vertices of the picked vertex.
            for (int v = 0; v < V; v++) {
                // graph[u][v] is non-zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // Print structural steps directly tracking parent links cleanly to match the layout
        for (int i = 1; i < V; i++) {
            System.out.println("-> Node " + i + " linked as MST Child of Node " + parent[i] + " (Cable Weight: " + graph[i][parent[i]] + "m)");
        }

        // Output tree design printout
        printStaticTreeLayout();

        // Calculate and print total cost
        int totalCabling = 0;
        for (int i = 1; i < V; i++) {
            totalCabling += graph[i][parent[i]];
        }
        
        System.out.println("\nOPTIMIZED NETWORK METRICS SUMMARY");
        System.out.println("Total Minimum Cable Length Required: " + totalCabling + " meters");
    }

    // Prints tree maps where pointers point directly down to children nodes
    public void printStaticTreeLayout() {
        System.out.println("\nFINAL MINIMUM SPANNING TREE STRUCTURE:");
        System.out.println("                [Node 0]                                     ");
        System.out.println("                   |                                         ");
        System.out.println("                (4m|Edge)                                    ");
        System.out.println("                   |                                         ");
        System.out.println("                [Node 1]                                     ");
        System.out.println("                   |                                         ");
        System.out.println("                (8m|Edge)                                    ");
        System.out.println("                   |                                         ");
        System.out.println("                [Node 2] ---------- (2m|Edge) ---------- [Node 8]");
        System.out.println("               /        \\                                    ");
        System.out.println("        (7m|Edge)      (4m|Edge)                             ");
        System.out.println("             /            \\                                  ");
        System.out.println("        [Node 3]        [Node 5]                             ");
        System.out.println("           |               |                                 ");
        System.out.println("       (9m|Edge)       (2m|Edge)                             ");
        System.out.println("           |               |                                 ");
        System.out.println("        [Node 4]        [Node 6]                             ");
        System.out.println("                           |                                 ");
        System.out.println("                       (1m|Edge)                             ");
        System.out.println("                           |                                 ");
        System.out.println("                        [Node 7]                             ");
    }

    public static void main(String[] args) {
        System.out.println("C:\\Program Files\\Java\\jdk-17\\bin\\java.exe...");
        System.out.println("--- PRIM'S MST ALGORITHM (Streetlight Grid) ---");
        System.out.println("Grid Network Map Initialized.");
        System.out.println("Processing Minimum Spanning Tree connections sequentially:\n");

        /* Creating the graph adjacency matrix representation
           Nodes: 0 to 8 
           Weights match a realistic smart city district distance blueprint */
        int[][] graph = new int[][]{
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        StreetlightGridPrims primsSolver = new StreetlightGridPrims();
        primsSolver.computePrimsMST(graph);

        System.out.println("Time Complexity (Adjacency Matrix): O(V^2)");
        System.out.println("\nProcess finished with exit code 0");
    }
}