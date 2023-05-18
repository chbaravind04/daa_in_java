public class public class StrassenMatrixMultiplication {
  
    public static int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        
        // Base case: If the matrices are 1x1
        if (n == 1) {
            result[0][0] = A[0][0] * B[0][0];
        } else {
            // Split the matrices into quarters
            int[][] a11 = new int[n/2][n/2];
            int[][] a12 = new int[n/2][n/2];
            int[][] a21 = new int[n/2][n/2];
            int[][] a22 = new int[n/2][n/2];
            int[][] b11 = new int[n/2][n/2];
            int[][] b12 = new int[n/2][n/2];
            int[][] b21 = new int[n/2][n/2];
            int[][] b22 = new int[n/2][n/2];
            
            // Divide matrix A into quarters
            splitMatrix(A, a11, 0, 0);
            splitMatrix(A, a12, 0, n/2);
            splitMatrix(A, a21, n/2, 0);
            splitMatrix(A, a22, n/2, n/2);
            
            // Divide matrix B into quarters
            splitMatrix(B, b11, 0, 0);
            splitMatrix(B, b12, 0, n/2);
            splitMatrix(B, b21, n/2, 0);
            splitMatrix(B, b22, n/2, n/2);
            
            // Recursive calls to multiply sub-matrices
            int[][] p1 = multiply(addMatrices(a11, a22), addMatrices(b11, b22));
            int[][] p2 = multiply(addMatrices(a21, a22), b11);
            int[][] p3 = multiply(a11, subtractMatrices(b12, b22));
            int[][] p4 = multiply(a22, subtractMatrices(b21, b11));
            int[][] p5 = multiply(addMatrices(a11, a12), b22);
            int[][] p6 = multiply(subtractMatrices(a21, a11), addMatrices(b11, b12));
            int[][] p7 = multiply(subtractMatrices(a12, a22), addMatrices(b21, b22));
            
            // Calculate the sub-matrices of the result matrix
            int[][] c11 = addMatrices(subtractMatrices(addMatrices(p1, p4), p5), p7);
            int[][] c12 = addMatrices(p3, p5);
            int[][] c21 = addMatrices(p2, p4);
            int[][] c22 = addMatrices(subtractMatrices(addMatrices(p1, p3), p2), p6);
            
            // Merge the sub-matrices into the result matrix
            mergeMatrices(c11, result, 0, 0);
            mergeMatrices(c12, result, 0, n/2);
            mergeMatrices(c21, result, n/2, 0);
            mergeMatrices(c22, result, n/2, n/2);
        }
        
        return result;
    }
    
    // Helper method to split a matrix into quarters
    private static void splitMatrix(int[][] matrix, int[][] subMatrix, int startRow, int startCol) {
        for (int i = 0; i < subMatrix.length; i++) {
            for (int j = 0; j < subMatrix.length; j++) {
                subMatrix[i][j] = matrix[i + startRow][j + startCol];
            }
        }
    }
    
    // Helper method to merge a sub-matrix into a matrix
    private static void mergeMatrices(int[][] subMatrix, int[][] matrix, int startRow, int startCol) {
        for (int i = 0; i < subMatrix.length; i++) {
            for (int j = 0; j < subMatrix.length; j++) {
                matrix[i + startRow][j + startCol] = subMatrix[i][j];
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
        int n = matrix.length;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
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
        
        int[][] result = multiply(A, B);
        
        System.out.println("Result:");
        displayMatrix(result);
    }
}
 {
  
    public static int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        
        // Base case: If the matrices are 1x1
        if (n == 1) {
            result[0][0] = A[0][0] * B[0][0];
        } else {
            // Split the matrices into quarters
            int[][] a11 = new int[n/2][n/2];
            int[][] a12 = new int[n/2][n/2];
            int[][] a21 = new int[n/2][n/2];
            int[][] a22 = new int[n/2][n/2];
            int[][] b11 = new int[n/2][n/2];
            int[][] b12 = new int[n/2][n/2];
            int[][] b21 = new int[n/2][n/2];
            int[][] b22 = new int[n/2][n/2];
            
            // Divide matrix A into quarters
            splitMatrix(A, a11, 0, 0);
            splitMatrix(A, a12, 0, n/2);
            splitMatrix(A, a21, n/2, 0);
            splitMatrix(A, a22, n/2, n/2);
            
            // Divide matrix B into quarters
            splitMatrix(B, b11, 0, 0);
            splitMatrix(B, b12, 0, n/2);
            splitMatrix(B, b21, n/2, 0);
            splitMatrix(B, b22, n/2, n/2);
            
            // Recursive calls to multiply sub-matrices
            int[][] p1 = multiply(addMatrices(a11, a22), addMatrices(b11, b22));
            int[][] p2 = multiply(addMatrices(a21, a22), b11);
            int[][] p3 = multiply(a11, subtractMatrices(b12, b22));
            int[][] p4 = multiply(a22, subtractMatrices(b21, b11));
            int[][] p5 = multiply(addMatrices(a11, a12), b22);
            int[][] p6 = multiply(subtractMatrices(a21, a11), addMatrices(b11, b12));
            int[][] p7 = multiply(subtractMatrices(a12, a22), addMatrices(b21, b22));
            
            // Calculate the sub-matrices of the result matrix
            int[][] c11 = addMatrices(subtractMatrices(addMatrices(p1, p4), p5), p7);
            int[][] c12 = addMatrices(p3, p5);
            int[][] c21 = addMatrices(p2, p4);
            int[][] c22 = addMatrices(subtractMatrices(addMatrices(p1, p3), p2), p6);
            
            // Merge the sub-matrices into the result matrix
            mergeMatrices(c11, result, 0, 0);
            mergeMatrices(c12, result, 0, n/2);
            mergeMatrices(c21, result, n/2, 0);
            mergeMatrices(c22, result, n/2, n/2);
        }
        
        return result;
    }
    
    // Helper method to split a matrix into quarters
    private static void splitMatrix(int[][] matrix, int[][] subMatrix, int startRow, int startCol) {
        for (int i = 0; i < subMatrix.length; i++) {
            for (int j = 0; j < subMatrix.length; j++) {
                subMatrix[i][j] = matrix[i + startRow][j + startCol];
            }
        }
    }
    
    // Helper method to merge a sub-matrix into a matrix
    private static void mergeMatrices(int[][] subMatrix, int[][] matrix, int startRow, int startCol) {
        for (int i = 0; i < subMatrix.length; i++) {
            for (int j = 0; j < subMatrix.length; j++) {
                matrix[i + startRow][j + startCol] = subMatrix[i][j];
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
        int n = matrix.length;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
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
        
        int[][] result = multiply(A, B);
        
        System.out.println("Result:");
        displayMatrix(result);
    }
}
