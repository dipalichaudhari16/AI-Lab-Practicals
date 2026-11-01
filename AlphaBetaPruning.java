import java.util.Scanner;

public class AlphaBetaPruning {
    static int visitedLeaves = 0;

    static int minimax(int[] scores, int depth, int nodeIndex,
                       boolean maximizing, int alpha, int beta) {
        if (depth == 3) {
            visitedLeaves++;
            return scores[nodeIndex];
        }

        if (maximizing) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 2; i++) {
                int value = minimax(scores, depth + 1, nodeIndex * 2 + i,
                                    false, alpha, beta);
                best = Math.max(best, value);
                alpha = Math.max(alpha, best);
                if (beta <= alpha) break;
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 2; i++) {
                int value = minimax(scores, depth + 1, nodeIndex * 2 + i,
                                    true, alpha, beta);
                best = Math.min(best, value);
                beta = Math.min(beta, best);
                if (beta <= alpha) break;
            }
            return best;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] scores = new int[8];

        System.out.println("Enter 8 leaf values of the game tree:");
        for (int i = 0; i < 8; i++) scores[i] = sc.nextInt();

        int result = minimax(scores, 0, 0, true,
                             Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println("Best value using Alpha-Beta Pruning = " + result);
        System.out.println("Leaf nodes evaluated = " + visitedLeaves);
        System.out.println("Some leaf nodes may be skipped due to pruning.");
        sc.close();
    }
}
