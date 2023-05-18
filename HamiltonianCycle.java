import java.util.*;

public class HamiltonianCycle {
    private static int[][] graph;
    private static int numVertices;
    private static int[] path;

    public static boolean isSafe(int v, int pos, int[] path) {
        if (graph[path[pos - 1]][v] == 0) {
            return false; // There is no edge between the previous vertex and the current vertex
        }

        for (int i = 0; i < pos; i++) {
            if (path[i] == v) {
                return false; // The current vertex is already in the path
            }
        }

        return true;
    }

    public static boolean hamiltonianCycleUtil(int pos) {
        if (pos == numVertices) {
            // Check if there is an edge between the last and first vertices
            return graph[path[pos - 1]][path[0]] == 1;
        }

        for (int v = 1; v < numVertices; v++) {
            if (isSafe(v, pos, path)) {
                path[pos] = v;
                if (hamiltonianCycleUtil(pos + 1)) {
                    return true;
                }
                path[pos] = -1; // Backtrack and remove the vertex from the path
            }
        }

        return false;
    }

    public static void printHamiltonianCycle(int[] path) {
        System.out.println("Hamiltonian Cycle:");
        for (int v : path) {
            System.out.print(v + " ");
        }
        System.out.println(path[0]); // Print the first vertex again to complete the cycle
    }

    public static boolean hamiltonianCycle(int[][] adjacencyMatrix) {
        numVertices = adjacencyMatrix.length;
        graph = adjacencyMatrix;
        path = new int[numVertices];

        Arrays.fill(path, -1); // Initialize the path array

        // Start with the first vertex
        path[0] = 0;

        if (hamiltonianCycleUtil(1)) {
            printHamiltonianCycle(path);
            return true;
        } else {
            System.out.println("No Hamiltonian Cycle exists.");
            return false;
        }
    }

    public static void main(String[] args) {
        int[][] adjacencyMatrix = {
            {0, 1, 0, 1, 0},
            {1, 0, 1, 1, 1},
            {0, 1, 0, 0, 1},
            {1, 1, 0, 0, 1},
            {0, 1, 1, 1, 0}
        };

        hamiltonianCycle(adjacencyMatrix);
    }
}
