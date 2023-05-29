public class StrassenMatrixMultiplication {

    public static int[][] strassenMultiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];

        // Base case: if the matrices are 1x1
        if (n == 1) {
            result[0][0] = A[0][0] * B[0][0];
        } else {
            // Divide the matrices into smaller submatrices
            int[][] A11 = new int[n / 2][n / 2];
            int[][] A12 = new int[n / 2][n / 2];
            int[][] A21 = new int[n / 2][n / 2];
            int[][] A22 = new int[n / 2][n / 2];
            int[][] B11 = new int[n / 2][n / 2];
            int[][] B12 = new int[n / 2][n / 2];
            int[][] B21 = new int[n / 2][n / 2];
            int[][] B22 = new int[n / 2][n / 2];

            // Splitting input matrices into submatrices
            splitMatrix(A, A11, 0, 0);
            splitMatrix(A, A12, 0, n / 2);
            splitMatrix(A, A21, n / 2, 0);
            splitMatrix(A, A22, n / 2, n / 2);
            splitMatrix(B, B11, 0, 0);
            splitMatrix(B, B12, 0, n / 2);
            splitMatrix(B, B21, n / 2, 0);
            splitMatrix(B, B22, n / 2, n / 2);

            // Recursive steps
            int[][] M1 = strassenMultiply(addMatrices(A11, A22), addMatrices(B11, B22));
            int[][] M2 = strassenMultiply(addMatrices(A21, A22), B11);
            int[][] M3 = strassenMultiply(A11, subtractMatrices(B12, B22));
            int[][] M4 = strassenMultiply(A22, subtractMatrices(B21, B11));
            int[][] M5 = strassenMultiply(addMatrices(A11, A12), B22);
            int[][] M6 = strassenMultiply(subtractMatrices(A21, A11), addMatrices(B11, B12));
            int[][] M7 = strassenMultiply(subtractMatrices(A12, A22), addMatrices(B21, B22));

            // Calculate the submatrices of the result matrix
            int[][] C11 = addMatrices(subtractMatrices(addMatrices(M1, M4), M5), M7);
            int[][] C12 = addMatrices(M3, M5);
            int[][] C21 = addMatrices(M2, M4);
            int[][] C22 = addMatrices(subtractMatrices(addMatrices(M1, M3), M2), M6);

            // Combine the submatrices to get the final result
            combineMatrices(C11, result, 0, 0);
            combineMatrices(C12, result, 0, n / 2);
            combineMatrices(C21, result, n / 2, 0);
            combineMatrices(C22, result, n / 2, n / 2);
        }

        return result;
    }

    // Helper method to split a matrix into submatrices
    private static void splitMatrix(int[][] parent, int[][] child, int startRow, int startCol) {
        for (int i = 0; i < child.length; i++) {
            for (int j = 0; j < child.length; j++) {
                child[i][j] = parent[i + startRow][j + startCol];
            }
        }
    }

    // Helper method to combine submatrices into a parent matrix
    private static void combineMatrices(int[][] child, int[][] parent, int startRow, int startCol) {
        for (int i = 0; i < child.length; i++) {
            for (int j = 0; j < child.length; j++) {
                parent[i + startRow][j + startCol] = child[i][j];
            }
        }
    }

    // Helper method to add two matrices
    private static int[][] addMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }

        return result;
    }

    // Helper method to subtract two matrices
    private static int[][] subtractMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }

        return result;
    }

    // Helper method to display a matrix
    private static void displayMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] A = {
                {1, 2},
                {3, 4}
        };

        int[][] B = {
                {5, 6},
                {7, 8}
        };

        int[][] result = strassenMultiply(A, B);
        displayMatrix(result);
    }
}
