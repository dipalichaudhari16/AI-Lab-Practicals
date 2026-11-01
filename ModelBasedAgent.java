public class ModelBasedAgent {

    static final int ROWS = 5;
    static final int COLS = 5;

    static char[][] grid = {
        {'S', '.', '#', '.', '.'},
        {'.', '.', '#', '.', '#'},
        {'#', '.', '.', '.', '.'},
        {'.', '#', '#', '.', '#'},
        {'.', '.', '.', '.', 'G'}
    };

    // Internal state: remembers cells already visited
    static boolean[][] visited = new boolean[ROWS][COLS];

    // Directions: Up, Down, Left, Right
    static int[] rowMove = {-1, 1, 0, 0};
    static int[] colMove = {0, 0, -1, 1};
    static String[] directions = {"UP", "DOWN", "LEFT", "RIGHT"};

    static boolean isValid(int row, int col) {
        return row >= 0 && row < ROWS
                && col >= 0 && col < COLS
                && grid[row][col] != '#';
    }

    static boolean navigate(int row, int col) {
        if (grid[row][col] == 'G') {
            System.out.println("Goal reached at (" + row + "," + col + ")");
            return true;
        }

        visited[row][col] = true;

        for (int i = 0; i < 4; i++) {
            int newRow = row + rowMove[i];
            int newCol = col + colMove[i];

            if (isValid(newRow, newCol) && !visited[newRow][newCol]) {
                System.out.println("Move " + directions[i]
                        + " to (" + newRow + "," + newCol + ")");

                if (navigate(newRow, newCol)) {
                    return true;
                }

                System.out.println("Backtracking from ("
                        + newRow + "," + newCol + ")");
            }
        }

        return false;
    }

    static void displayGrid() {
        System.out.println("\nGrid Environment:");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void displayInternalState() {
        System.out.println("\nInternal State (Visited Cells):");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(visited[i][j] ? "V " : "- ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("MODEL-BASED GRID NAVIGATION AGENT");
        displayGrid();

        System.out.println("\nStarting navigation from (0,0)");
        boolean result = navigate(0, 0);

        if (result) {
            System.out.println("\nNavigation Successful!");
        } else {
            System.out.println("\nGoal cannot be reached!");
        }

        displayInternalState();
    }
}
