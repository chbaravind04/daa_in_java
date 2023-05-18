import java.util.*;

public class TravelingSalespersonProblem {
    private static int[][] distance;
    private static int n;
    private static int[][] memo;

    public static int tsp(int mask, int pos) {
        if (mask == (1 << n) - 1) {
            return distance[pos][0];
        }

        if (memo[mask][pos] != -1) {
            return memo[mask][pos];
        }

        int ans = Integer.MAX_VALUE;

        for (int city = 0; city < n; city++) {
            if ((mask & (1 << city)) == 0) {
                int newMask = mask | (1 << city);
                int newDist = distance[pos][city] + tsp(newMask, city);
                ans = Math.min(ans, newDist);
            }
        }

        memo[mask][pos] = ans;
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read the number of cities
        n = sc.nextInt();

        // Initialize the distance matrix
        distance = new int[n][n];

        // Read the distance matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = sc.nextInt();
            }
        }

        // Initialize the memoization table
        memo = new int[1 << n][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Find the shortest route
        int shortestRoute = tsp(1, 0);
        System.out.println("Shortest route length: " + shortestRoute);
    }
}
