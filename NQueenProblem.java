public class NQueenProblem {
    public static boolean isSafe(int[][] board, int row, int col, int N) {
        // Check if there is a queen in the same column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        // Check if there is a queen in the upper left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check if there is a queen in the upper right diagonal
        for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    public static boolean solveNQueen(int[][] board, int row, int N) {
        if (row == N) {
            // All queens have been placed successfully
            return true;
        }

        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col, N)) {
                // Place the queen
                board[row][col] = 1;

                // Recursively place the remaining queens
                if (solveNQueen(board, row + 1, N)) {
                    return true;
                }

                // Backtrack and remove the queen
                board[row][col] = 0;
            }
        }

        return false;
    }

    public static void printBoard(int[][] board, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int N = 4; // Number of queens and board size
        int[][] board = new int[N][N];

        if (solveNQueen(board, 0, N)) {
            System.out.println("Solution:");
            printBoard(board, N);
        } else {
            System.out.println("No solution exists for N = " + N);
        }
    }
}
