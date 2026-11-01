import java.util.*;

public class BlocksWorldPlanner {
    // Each stack is written from bottom to top, for example [A, B] means B is on A.
    static String canonical(List<List<String>> state) {
        List<String> parts = new ArrayList<>();
        for (List<String> stack : state) {
            if (!stack.isEmpty()) parts.add(String.join("", stack));
        }
        Collections.sort(parts);
        return String.join("|", parts);
    }

    static List<List<String>> copyState(List<List<String>> state) {
        List<List<String>> copy = new ArrayList<>();
        for (List<String> stack : state) copy.add(new ArrayList<>(stack));
        return copy;
    }

    static class Node {
        List<List<String>> state;
        Node parent;
        String action;

        Node(List<List<String>> state, Node parent, String action) {
            this.state = state;
            this.parent = parent;
            this.action = action;
        }
    }

    static List<List<String>> readState(Scanner sc, String label, int count) {
        List<List<String>> state = new ArrayList<>();
        Set<String> used = new HashSet<>();
        System.out.println("Enter each " + label + " stack from bottom to top, without spaces.");
        System.out.println("Example: AB means A is at the bottom and B is on A. Use - for an empty stack.");

        for (int i = 0; i < count; i++) {
            String value = sc.next().toUpperCase();
            List<String> stack = new ArrayList<>();
            if (!value.equals("-")) {
                for (int j = 0; j < value.length(); j++) {
                    String block = String.valueOf(value.charAt(j));
                    if (!Character.isLetter(value.charAt(j)) || !used.add(block)) {
                        throw new IllegalArgumentException("Blocks must be letters and must not repeat.");
                    }
                    stack.add(block);
                }
            }
            if (!stack.isEmpty()) state.add(stack);
        }
        return state;
    }

    static void solve(List<List<String>> start, List<List<String>> goal) {
        String goalKey = canonical(goal);
        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Node root = new Node(start, null, "Start");
        queue.add(root);
        visited.add(canonical(start));

        while (!queue.isEmpty()) {
            Node current = queue.remove();
            if (canonical(current.state).equals(goalKey)) {
                List<String> plan = new ArrayList<>();
                for (Node n = current; n != null; n = n.parent) plan.add(n.action);
                Collections.reverse(plan);
                System.out.println("\nPlan:");
                for (int i = 0; i < plan.size(); i++)
                    System.out.println(i + ". " + plan.get(i));
                return;
            }

            for (int from = 0; from < current.state.size(); from++) {
                List<String> source = current.state.get(from);
                String block = source.get(source.size() - 1);

                // Move the top block onto the table (a new stack).
                List<List<String>> next = copyState(current.state);
                next.get(from).remove(next.get(from).size() - 1);
                if (next.get(from).isEmpty()) next.remove(from);
                next.add(new ArrayList<>(Collections.singletonList(block)));
                addState(queue, visited, current, next,
                         "Move " + block + " onto the table");

                // Move the top block onto the top of another stack.
                for (int to = 0; to < current.state.size(); to++) {
                    if (to == from) continue;
                    List<List<String>> moved = copyState(current.state);
                    String movingBlock = moved.get(from).remove(moved.get(from).size() - 1);
                    if (moved.get(from).isEmpty()) {
                        moved.remove(from);
                        int targetIndex = to > from ? to - 1 : to;
                        moved.get(targetIndex).add(movingBlock);
                    } else {
                        moved.get(to).add(movingBlock);
                    }
                    addState(queue, visited, current, moved,
                             "Move " + movingBlock + " onto " +
                             current.state.get(to).get(current.state.get(to).size() - 1));
                }
            }
        }
        System.out.println("No plan found. Check that the initial and goal states contain the same blocks.");
    }

    static void addState(Queue<Node> queue, Set<String> visited, Node parent,
                         List<List<String>> state, String action) {
        String key = canonical(state);
        if (visited.add(key)) queue.add(new Node(state, parent, action));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter number of stacks in initial state: ");
            int initialCount = sc.nextInt();
            List<List<String>> initial = readState(sc, "initial", initialCount);

            System.out.print("Enter number of stacks in goal state: ");
            int goalCount = sc.nextInt();
            List<List<String>> goal = readState(sc, "goal", goalCount);

            Set<String> initialBlocks = new HashSet<>();
            Set<String> goalBlocks = new HashSet<>();
            for (List<String> stack : initial) initialBlocks.addAll(stack);
            for (List<String> stack : goal) goalBlocks.addAll(stack);

            if (!initialBlocks.equals(goalBlocks)) {
                System.out.println("Error: Initial and goal states must contain exactly the same blocks.");
            } else {
                solve(initial, goal);
            }
        } catch (Exception ex) {
            System.out.println("Invalid input: " + ex.getMessage());
        }
        sc.close();
    }
}
