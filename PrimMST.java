import java.util.*;

public class PrimMST {
    private static final int INF = Integer.MAX_VALUE;
    private int[] dist;
    private boolean[] visited;
    private int[] parent;
    private List<List<Edge>> graph;

    public PrimMST(List<List<Edge>> graph) {
        int n = graph.size();
        this.graph = graph;
        dist = new int[n];
        visited = new boolean[n];
        parent = new int[n];
        Arrays.fill(dist, INF);
        Arrays.fill(parent, -1);
    }

    public int findMST() {
        int n = graph.size();
        dist[0] = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(i -> dist[i]));
        pq.offer(0);

        int mstWeight = 0;
        while (!pq.isEmpty()) {
            int u = pq.poll();
            if (visited[u]) {
                continue;
            }
            visited[u] = true;
            mstWeight += dist[u];
            for (Edge e : graph.get(u)) {
                int v = e.to;
                if (!visited[v] && e.weight < dist[v]) {
                    dist[v] = e.weight;
                    parent[v] = u;
                    pq.offer(v);
                }
            }
        }
        return mstWeight;
    }

    public List<Integer> getMSTEdges() {
        List<Integer> edges = new ArrayList<>();
        for (int i = 1; i < graph.size(); i++) {
            if (parent[i] != -1) {
                edges.add(parent[i]);
                edges.add(i);
            }
        }
        return edges;
    }

    public static void main(String[] args) {
        int n = 5;
        List<List<Edge>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // Adding edges to the graph

       /*  graph.get(1).add(new Edge(2, 5));
        graph.get(1).add(new Edge(3, 6));
        graph.get(2).add(new Edge(3, 9));
        graph.get(3).add(new Edge(4, 4));
        */

         graph.get(0).add(new Edge(1, 2));
        graph.get(0).add(new Edge(3, 6));
        graph.get(1).add(new Edge(2, 3));
        graph.get(1).add(new Edge(3, 8));
        graph.get(1).add(new Edge(4, 5));
        graph.get(2).add(new Edge(4, 7));
        graph.get(3).add(new Edge(4, 9));

        PrimMST prim = new PrimMST(graph);
        int mstWeight = prim.findMST();
        System.out.println("Minimum Spanning Tree Weight: " + mstWeight);

        List<Integer> mstEdges = prim.getMSTEdges();
        System.out.print("Minimum Spanning Tree Edges: ");
        for (int i = 0; i < mstEdges.size(); i += 2) {
            System.out.print("(" + mstEdges.get(i) + "," + mstEdges.get(i + 1) + ") ");
        }
    }
}

class Edge {
    int to;
    int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}
