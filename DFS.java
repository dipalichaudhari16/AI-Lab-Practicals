import java.util.*;

public class DFS {
    static void dfs(int vertex, List<List<Integer>> graph, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        for (int neighbour : graph.get(vertex)) {
            if (!visited[neighbour]) {
                dfs(neighbour, graph, visited);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();
        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();

        if (n <= 0 || e < 0) {
            System.out.println("Invalid graph size.");
            sc.close();
            return;
        }

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        System.out.println("Enter each edge as: source destination (vertices 0 to " + (n - 1) + ")");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            if (u < 0 || u >= n || v < 0 || v >= n) {
                System.out.println("Invalid vertex number.");
                sc.close();
                return;
            }
            graph.get(u).add(v);
            graph.get(v).add(u); // Remove this line for a directed graph.
        }

        System.out.print("Enter starting vertex: ");
        int start = sc.nextInt();
        if (start < 0 || start >= n) {
            System.out.println("Invalid starting vertex.");
            sc.close();
            return;
        }

        System.out.print("DFS Traversal: ");
        dfs(start, graph, new boolean[n]);
        System.out.println();
        sc.close();
    }
}
