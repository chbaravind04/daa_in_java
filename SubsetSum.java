public class SubsetSum {
    public static boolean isSubsetSum(int[] set, int sum) {
        int n = set.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];

        // If the sum is 0, an empty subset can always be formed
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // If the set is empty, no subset can be formed
        for (int j = 1; j <= sum; j++) {
            dp[0][j] = false;
        }

        // Fill the dynamic programming table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (set[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - set[i - 1]];
                }
            }
        }

        return dp[n][sum];
    }

    public static void main(String[] args) {
        int[] set = {3, 34, 4, 12, 5, 2};
        int sum = 9;

        boolean subsetExists = isSubsetSum(set, sum);
        if (subsetExists) {
            System.out.println("Subset with sum " + sum + " exists.");
        } else {
            System.out.println("Subset with sum " + sum + " does not exist.");
        }
    }
}
