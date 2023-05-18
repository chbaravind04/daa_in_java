public class OptimalBinarySearchTree {
    public static float optimalBST(float[] keys, float[] freq) {
        int n = keys.length;
        float[][] cost = new float[n + 1][n + 1];

        for (int i = 0; i < n; i++) {
            cost[i][i] = freq[i];
        }

        for (int L = 2; L <= n; L++) {
            for (int i = 0; i <= n - L + 1; i++) {
                int j = i + L - 1;
                cost[i][j] = Float.MAX_VALUE;
                float sum = getSum(freq, i, j);

                for (int k = i; k <= j; k++) {
                    float temp = sum + (k - 1 < i ? 0 : cost[i][k - 1]) +
                                 (k + 1 > j ? 0 : cost[k + 1][j]);
                    if (temp < cost[i][j]) {
                        cost[i][j] = temp;
                    }
                }
            }
        }

        return cost[0][n - 1];
    }

    private static float getSum(float[] freq, int i, int j) {
        float sum = 0;
        for (int k = i; k <= j; k++) {
            sum += freq[k];
        }
        return sum;
    }

    public static void main(String[] args) {
        float[] keys = {10, 12, 20};
        float[] freq = {0.34f, 0.33f, 0.33f};

        float minCost = optimalBST(keys, freq);
        System.out.println("Minimum cost: " + minCost);
    }
}
