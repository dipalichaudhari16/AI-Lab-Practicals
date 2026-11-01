import java.util.*;

public class AStar8Puzzle {
    static final String GOAL = "123456780";

    static class Node {
        String state;
        int g, h;
        Node parent;

        Node(String state, int g, Node parent) {
            this.state = state;
            this.g = g;
            this.h = manhattan(state);
            this.parent = parent;
        }

        int f() { return g + h; }
    }

    static int manhattan(String state) {
        int distance = 0;
        for (int i = 0; i < 9; i++) {
            char tile = state.charAt(i);
            if (tile == '0') continue;
            int value = tile - '1';
            int goalRow = value / 3, goalCol = value % 3;
            distance += Math.abs(i / 3 - goalRow) + Math.abs(i % 3 - goalCol);
        }
        return distance;
    }

    static boolean isSolvable(String state) {
        int inversions = 0;
        for (int i = 0; i < 9; i++) {
            if (state.charAt(i) == '0') continue;
            for (int j = i + 1; j < 9; j++) {
                if (state.charAt(j) != '0' && state.charAt(i) > state.charAt(j)) {
                    inversions++;
                }
            }
        }
        return inversions % 2 == 0;
    }

    static void printBoard(String state) {
        for (int i = 0; i < 9; i++) {
            System.out.print((state.charAt(i) == '0' ? " " : state.charAt(i)) + " ");
            if (i % 3 == 2) System.out.println();
        }
        System.out.println();
    }

    static void solve(String start) {
        PriorityQueue<Node> open = new PriorityQueue<>(
            Comparator.comparingInt(Node::f).thenComparingInt(n -> n.h)
        );
        Map<String, Integer> bestCost = new HashMap<>();
        open.add(new Node(start, 0, null));
        bestCost.put(start, 0);

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!open.isEmpty()) {
            Node current = open.poll();
            if (current.g != bestCost.get(current.state)) continue;

            if (current.state.equals(GOAL)) {
                List<String> path = new ArrayList<>();
                for (Node n = current; n != null; n = n.parent) path.add(n.state);
                Collections.reverse(path);
                System.out.println("Solution found in " + (path.size() - 1) + " moves:");
                for (String s : path) printBoard(s);
                return;
            }

            int zero = current.state.indexOf('0');
            int row = zero / 3, col = zero % 3;

            for (int k = 0; k < 4; k++) {
                int nr = row + dr[k], nc = col + dc[k];
                if (nr < 0 || nr >= 3 || nc < 0 || nc >= 3) continue;
                int next = nr * 3 + nc;
                char[] a = current.state.toCharArray();
                a[zero] = a[next];
                a[next] = '0';
                String nextState = new String(a);
                int newCost = current.g + 1;

                if (newCost < bestCost.getOrDefault(nextState, Integer.MAX_VALUE)) {
                    bestCost.put(nextState, newCost);
                    open.add(new Node(nextState, newCost, current));
                }
            }
        }
        System.out.println("No solution found.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the 8-puzzle row by row (use 0 for blank).");
        System.out.println("Example: 1 2 3  4 0 6  7 5 8");
        StringBuilder start = new StringBuilder();
        boolean[] seen = new boolean[9];

        for (int i = 0; i < 9; i++) {
            int x = sc.nextInt();
            if (x < 0 || x > 8 || seen[x]) {
                System.out.println("Invalid input. Enter each number from 0 to 8 exactly once.");
                sc.close();
                return;
            }
            seen[x] = true;
            start.append(x);
        }

        if (!isSolvable(start.toString())) {
            System.out.println("This puzzle configuration is not solvable.");
        } else {
            solve(start.toString());
        }
        sc.close();
    }
}
