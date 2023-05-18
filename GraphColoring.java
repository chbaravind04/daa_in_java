import java.util.*;

public class GraphColoring {
    public static boolean isSafe(int v, int[][] graph, int[] colors, int c) {
        // Check if any adjacent vertex has the same color
        for (int i = 0; i < graph.length; i++) {
            if (graph[v][i] == 1 && colors[i] == c) {
                return false;
            }
        }
        return true;
    }

    public static boolean graphColorUtil(int[][] graph, int m, int[] colors, int v) {
        // All vertices have been assigned colors
        if (v == graph.length) {
            return true;
        }

        // Try different colors for the current vertex
        for (int c = 1; c <= m; c++) {
            if (isSafe(v, graph, colors, c)) {
                colors[v] = c;
                if (graphColorUtil(graph, m, colors, v + 1)) {
                    return true;
                }
                colors[v] = 0; // Backtrack and remove color assignment
            }
        }
        return false;
    }

    public static boolean graphColoring(int[][] graph, int m) {
        int[] colors = new int[graph.length];
        if (graphColorUtil(graph, m, colors, 0)) {
            System.out.println("Graph can be colored with " + m + " colors:");
            for (int i = 0; i < graph.length; i++) {
                System.out.println("Vertex " + i + " -> Color " + colors[i]);
            }
            return true;
        } else {
            System.out.println("Graph cannot be colored with " + m + " colors.");
            return false;
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 1, 1, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 1},
            {1, 0, 1, 0}
        };
        int m = 3; // Number of colors

        graphColoring(graph, m);
    }
}
