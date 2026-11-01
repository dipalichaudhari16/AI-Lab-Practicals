import java.util.Scanner;

public class SudokuSolver {
    static boolean isSafe(int[][] grid, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == num || grid[i][col] == num) return false;
        }

        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (grid[r][c] == num) return false;
            }
        }
        return true;
    }

    static boolean solve(int[][] grid) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grid[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isSafe(grid, row, col, num)) {
                            grid[row][col] = num;
                            if (solve(grid)) return true;
                            grid[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    static void printGrid(int[][] grid) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                System.out.print(grid[r][c] + " ");
                if (c == 2 || c == 5) System.out.print("| ");
            }
            System.out.println();
            if (r == 2 || r == 5) System.out.println("---------------------");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] grid = new int[9][9];

        System.out.println("Enter Sudoku values row by row (use 0 for empty cells):");
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                grid[r][c] = sc.nextInt();
                if (grid[r][c] < 0 || grid[r][c] > 9) {
                    System.out.println("Values must be between 0 and 9.");
                    sc.close();
                    return;
                }
            }
        }

        System.out.println("\nInput Sudoku:");
        printGrid(grid);

        if (solve(grid)) {
            System.out.println("\nSolved Sudoku:");
            printGrid(grid);
        } else {
            System.out.println("No solution exists for this Sudoku.");
        }
        sc.close();
    }
}
