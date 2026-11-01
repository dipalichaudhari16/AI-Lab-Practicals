import java.util.Scanner;

public class MinimaxTicTacToe {
    static char[] board = {'1','2','3','4','5','6','7','8','9'};

    static void printBoard() {
        System.out.println();
        for (int i = 0; i < 9; i += 3) {
            System.out.println(" " + board[i] + " | " + board[i+1] + " | " + board[i+2]);
            if (i < 6) System.out.println("---+---+---");
        }
        System.out.println();
    }

    static char winner() {
        int[][] lines = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
        };
        for (int[] line : lines) {
            if (board[line[0]] == board[line[1]] &&
                board[line[1]] == board[line[2]]) return board[line[0]];
        }
        return ' ';
    }

    static boolean isFull() {
        for (char c : board) if (c != 'X' && c != 'O') return false;
        return true;
    }

    static int minimax(boolean maximizing) {
        char result = winner();
        if (result == 'O') return 10;
        if (result == 'X') return -10;
        if (isFull()) return 0;

        if (maximizing) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] != 'X' && board[i] != 'O') {
                    char old = board[i];
                    board[i] = 'O';
                    best = Math.max(best, minimax(false));
                    board[i] = old;
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] != 'X' && board[i] != 'O') {
                    char old = board[i];
                    board[i] = 'X';
                    best = Math.min(best, minimax(true));
                    board[i] = old;
                }
            }
            return best;
        }
    }

    static int bestMove() {
        int bestValue = Integer.MIN_VALUE;
        int move = -1;
        for (int i = 0; i < 9; i++) {
            if (board[i] != 'X' && board[i] != 'O') {
                char old = board[i];
                board[i] = 'O';
                int value = minimax(false);
                board[i] = old;
                if (value > bestValue) {
                    bestValue = value;
                    move = i;
                }
            }
        }
        return move;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Tic-Tac-Toe using Minimax Algorithm");
        System.out.println("You are X. Computer is O. Select a cell from 1 to 9.");
        printBoard();

        while (winner() == ' ' && !isFull()) {
            System.out.print("Enter your move (1-9): ");
            if (!sc.hasNextInt()) {
                System.out.println("Please enter a number.");
                sc.next();
                continue;
            }
            int move = sc.nextInt() - 1;
            if (move < 0 || move >= 9 || board[move] == 'X' || board[move] == 'O') {
                System.out.println("Invalid or occupied cell. Try again.");
                continue;
            }

            board[move] = 'X';
            if (winner() != ' ' || isFull()) break;

            int computerMove = bestMove();
            board[computerMove] = 'O';
            System.out.println("Computer chooses cell " + (computerMove + 1));
            printBoard();
        }

        printBoard();
        char result = winner();
        if (result == 'X') System.out.println("You win!");
        else if (result == 'O') System.out.println("Computer wins!");
        else System.out.println("Match is a draw.");
        sc.close();
    }
}
