import java.util.Scanner;

public class EightQueens {
    static final int N = 8;
    static int solutions = 0;

    static boolean isSafe(int[][] board, int row, int col) {
        for (int i = 0; i < col; i++)
            if (board[row][i] == 1) return false;

        for (int r = row, c = col; r >= 0 && c >= 0; r--, c--)
            if (board[r][c] == 1) return false;

        for (int r = row, c = col; r < N && c >= 0; r++, c--)
            if (board[r][c] == 1) return false;

        return true;
    }

    static boolean solve(int[][] board, int col) {
        if (col == N) {
            solutions++;
            return true;
        }

        boolean found = false;
        for (int row = 0; row < N; row++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1;
                if (solve(board, col + 1)) found = true;
                board[row][col] = 0;
            }
        }
        return found;
    }

    static void printOneSolution(int[][] board, int col) {
        if (col == N) return;
        for (int row = 0; row < N; row++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1;
                if (hasCompletion(board, col + 1)) return;
                board[row][col] = 0;
            }
        }
    }

    static boolean hasCompletion(int[][] board, int col) {
        if (col == N) return true;
        for (int row = 0; row < N; row++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1;
                if (hasCompletion(board, col + 1)) return true;
                board[row][col] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] board = new int[N][N];
        solve(board, 0);
        printOneSolution(board, 0);

        System.out.println("One solution to the 8-Queens problem:");
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++)
                System.out.print((board[r][c] == 1 ? "Q " : ". "));
            System.out.println();
        }
        System.out.println("Total solutions = " + solutions);
    }
}
