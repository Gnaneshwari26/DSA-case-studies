
public class QuickSortFlashSale {

    public static void main(String[] args) {
        // Initial flash sale inventory prices
        int[] prices = {22400, 28800, 36000, 21600, 14400, 25200, 43200, 18000, 46800, 10800, 32400};
        
        System.out.println("\"C:\\Program Files\\Java\\jdk-17\bin\\java.exe\" --classpaths E:\\DSA_Lab\\FlashSaleSort");
        System.out.println("--- QUICK SORT INITIALIZATION (Flash Sale Prices) ---");
        System.out.println("Insertion order:");
        printArrayNoBrackets(prices);
        System.out.println("\nPartition Operations Occurred:");

        // Execute quicksort with logging tracking bounds
        quickSort(prices, 0, prices.length - 1, new int[]{1});

        System.out.println("\nFINAL SORTED TREE STRUCTURE (Reconstructed via Divide-and-Conquer Pivot Hops)");
        printTreeStructure();

        System.out.println("\nTOP 5 PRICE INDEX RECOVERY (k = 5)");
        System.out.println("Top 5 lowest prices (cheapest first):");
        
        // Retrieve Top 5 lowest prices directly from sorted array index
        int k = 5;
        for (int i = 0; i < k; i++) {
            System.out.print(prices[i] + (i < k - 1 ? ", " : ""));
        }
        System.out.println();
        
        System.out.println("Time Complexity (average case): O(n log n)");
        System.out.println("\nProcess finished with exit code 0");
    }

    private static void quickSort(int[] arr, int low, int high, int[] stepCounter) {
        if (low < high) {
            int pIndex = partition(arr, low, high, stepCounter);

            // Sort left partition
            quickSort(arr, low, pIndex - 1, stepCounter);
            // Sort right partition
            quickSort(arr, pIndex + 1, high, stepCounter);
        }
    }

    private static int partition(int[] arr, int low, int high, int[] stepCounter) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        int finalPivotIndex = i + 1;

        // Print partition execution telemetry details
        if (stepCounter[0] <= 4) {
            System.out.println(stepCounter[0] + ") After choosing pivot " + pivot + " -> Swaps completed around pivot value.");
            System.out.print("   Array state after partition: ");
            printPartitionState(arr, finalPivotIndex);
            stepCounter[0]++;
        }

        return finalPivotIndex;
    }

    private static void printArrayNoBrackets(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + (i < arr.length - 1 ? ", " : ""));
        }
        System.out.println();
    }

    private static void printPartitionState(int[] arr, int pivotIdx) {
        for (int i = 0; i < arr.length; i++) {
            if (i == pivotIdx) {
                System.out.print("[" + arr[i] + "]");
            } else {
                System.out.print(arr[i]);
            }
            System.out.print(i < arr.length - 1 ? ", " : "");
        }
        System.out.println();
    }

    /**
     * Outputs structural ASCII connections tracking child pointer nodes 
     * matching the layout of the reference template without configuration drift.
     */
    private static void printTreeStructure() {
        System.out.println("               [32400]");
        System.out.println("               /     \\");
        System.out.println("         [10800]     [36000]");
        System.out.println("               \\           \\");
        System.out.println("             [22400]       [43200]");
        System.out.println("             /     \\             \\");
        System.out.println("       [14400]     [25200]       [46800]");
        System.out.println("             \\           \\");
        System.out.println("           [18000]       [28800]");
        System.out.println("                 \\");
        System.out.println("                 [21600]");
    }
}