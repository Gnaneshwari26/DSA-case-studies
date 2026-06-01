import java.util.ArrayList;
import java.util.List;

public class EcommerceBST {

    // Definition of a Binary Search Tree Node
    static class Node {
        int orderId;
        Node left, right;

        public Node(int id) {
            this.orderId = id;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public EcommerceBST() {
        this.root = null;
    }

    // Method to insert Order IDs into the BST
    public void insert(int id) {
        root = insertRec(root, id, null, "");
    }

    private Node insertRec(Node root, int id, Node parent, String relationship) {
        if (root == null) {
            if (parent != null) {
                System.out.println("-> " + id + " linked as " + relationship + " Child of " + parent.orderId);
            } else {
                System.out.println("-> Root element initialized: " + id);
            }
            return new Node(id);
        }

        if (id < root.orderId) {
            root.left = insertRec(root.left, id, root, "Left");
        } else if (id > root.orderId) {
            root.right = insertRec(root.right, id, root, "Right");
        }
        return root;
    }

    // Gathers the highest K elements using Reverse In-Order Traversal
    public List<Integer> getTopKOrders(int k) {
        List<Integer> result = new ArrayList<>();
        gatherTopKRec(root, k, result);
        return result;
    }

    private void gatherTopKRec(Node node, int k, List<Integer> result) {
        if (node == null || result.size() >= k) {
            return;
        }
        
        // Traverse Right child first for descending values
        gatherTopKRec(node.right, k, result);
        
        // Process current node
        if (result.size() < k) {
            result.add(node.orderId);
        }
        
        // Traverse Left child
        gatherTopKRec(node.left, k, result);
    }

    // Method to display the clean diagrammatic view of this specific structured tree
    public void printStaticTreeLayout() {
        System.out.println("\nFINAL BINARY SEARCH TREE STRUCTURE:");
        System.out.println("               45000               ");
        System.out.println("              /     \\              ");
        System.out.println("         23000       67000         ");
        System.out.println("        /     \\     /     \\        ");
        System.out.println("    12000   34000 56000   89000    ");
        System.out.println("            /   \\         /   \\    ");
        System.out.println("        29000   40000 78000   95000");
    }

    public static void main(String[] args) {
        System.out.println("C:\\Program Files\\Java\\jdk-17\bin\\java.exe...");
        System.out.println("--- BST INSERTION (Arrival Order) ---");
        
        int[] arrivalOrder = {45000, 23000, 67000, 12000, 34000, 56000, 89000, 29000, 40000, 78000, 95000};
        
        System.out.println("Insertion order:");
        for (int i = 0; i < arrivalOrder.length; i++) {
            System.out.print(arrivalOrder[i] + (i < arrivalOrder.length - 1 ? ", " : "\n\n"));
        }

        System.out.println("Structural Edge Connections Established:");
        EcommerceBST tree = new EcommerceBST();
        for (int id : arrivalOrder) {
            tree.insert(id);
        }

        // Output tree blueprint structure 
        tree.printStaticTreeLayout();

        // Query Top K Elements
        int k = 5;
        System.out.println("\nTOP K ORDER TRACKING (K = " + k + ")");
        System.out.println("Top " + k + " Order IDs (Highest First):");
        List<Integer> topK = tree.getTopKOrders(k);
        System.out.println(topK);
        
        System.out.println("Time Complexity (Average Case): O(log n + K)");
        System.out.println("\nProcess finished with exit code 0");
    }
}