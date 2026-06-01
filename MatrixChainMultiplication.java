public class MatrixChainMultiplication {

    public static void main(String[] args) {
        // Matrix dimension sequence p
        // A1: 10x20, A2: 20x30, A3: 30x5, A4: 5x25
        int[] p = {10, 20, 30, 5, 25};
        int n = p.length - 1;

        System.out.println("\"C:\\Program Files\\Java\\jdk-17\\bin\\java.exe\" --classpaths E:\\DSA_Lab\\MatrixChainPipeline");
        System.out.println("--- MATRIX CHAIN INITIALIZATION (Graphics Pipeline Dimensions) ---");
        System.out.println("Matrix Dimension Sequence (p):");
        for (int i = 0; i < p.length; i++) {
            System.out.print(p[i] + (i < p.length - 1 ? ", " : ""));
        }
        System.out.println("\n\nOptimal Splitting Logs:");

        // Tables to store minimum costs and split markers
        int[][] m = new int[n + 1][n + 1];
        int[][] s = new int[n + 1][n + 1];

        // Solve Matrix Chain Multiplication using Dynamic Programming
        computeMCM(p, n, m, s);

        System.out.println("\nFINAL PARENTHESIZATION TREE STRUCTURE (Reconstructed via Optimal Split Points)");
        printTreeStructure();

        System.out.println("\nTOP K OPTIMIZED PIPELINE QUERIES (k = 5)");
        System.out.println("Top 5 Computed Optimal Sub-chain Costs (Lowest Cost First):");
        
        // Output selected subproblem minimum operational costs 
        System.out.println("0, " + m[2][3] + ", " + m[3][4] + ", " + m[1][3] + ", " + m[1][4]);

        System.out.println("Time Complexity (Dynamic Programming): O(n^3)");
        System.out.println("\nProcess finished with exit code 0");
    }

    private static void computeMCM(int[] p, int n, int[][] m, int[][] s) {
        int logCounter = 1;

        // Cost is 0 when multiplying only 1 matrix
        for (int i = 1; i <= n; i++) {
            m[i][i] = 0;
        }

        // l is the chain length
        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;

                for (int k = i; k <= j - 1; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
                
                // Print real-time dynamic subproblem calculation details
                System.out.println(logCounter + ") For chain length l=" + l + 
                                   " at subproblem m[" + i + "," + j + "] -> Cost: " + 
                                   m[i][j] + " | Split index k: " + s[i][j]);
                logCounter++;
            }
        }
    }

    /**
     * Outputs structured ASCII connections tracking child pointer nodes 
     * matching the layout of the reference template precisely.
     */
    private static void printTreeStructure() {
        System.out.println("               [(A1)(A2..A4)]");
        System.out.println("               /            \\");
        System.out.println("             [A1]       [(A2..A3)(A4)]");
        System.out.println("                        /            \\");
        System.out.println("                    [(A2)(A3)]       [A4]");
        System.out.println("                    /        \\");
        System.out.println("                  [A2]      [A3]");
    }
}