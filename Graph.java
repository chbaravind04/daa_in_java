import java.util.*;

class Edge implements Comparable<Edge> {
    int src;
    int dest;
    int weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

class Graph {
    int V; // Number of vertices
    List<Edge> edges; // List of edges

    public Graph(int V) {
        this.V = V;
        edges = new ArrayList<>();
    }

    public void addEdge(int src, int dest, int weight) {
        Edge edge = new Edge(src, dest, weight);
        edges.add(edge);
    }

    // Find the parent of a vertex (uses path compression)
    private int find(int[] parent, int vertex) {
        if (parent[vertex] != vertex) {
            parent[vertex] = find(parent, parent[vertex]);
        }
        return parent[vertex];
    }

    // Union of two sets of vertices (uses union by rank)
    private void union(int[] parent, int[] rank, int x, int y) {
        int xRoot = find(parent, x);
        int yRoot = find(parent, y);

        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot]++;
        }
    }

    public void findMinimumSpanningTree() {
        List<Edge> result = new ArrayList<>();
        Collections.sort(edges); // Sort edges by weight

        int[] parent = new int[V];
        int[] rank = new int[V];

        // Initialize parent and rank arrays
        for (int i = 0; i < V; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        int edgeCount = 0;
        int index = 0;

        // Select V-1 edges for the minimum spanning tree
        while (edgeCount < V - 1) {
            Edge edge = edges.get(index++);
            int srcParent = find(parent, edge.src);
            int destParent = find(parent, edge.dest);

            // Include the edge only if it doesn't create a cycle
            if (srcParent != destParent) {
                result.add(edge);
                union(parent, rank, srcParent, destParent);
                edgeCount++;
            }
        }

        // Display the minimum spanning tree
        System.out.println("Minimum Spanning Tree:");
        for (Edge edge : result) {
            System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight);
        }
    }

    public static void main(String[] args) {
        int V = 4;
        Graph graph = new Graph(V);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);

        graph.findMinimumSpanningTree();
    }
}
