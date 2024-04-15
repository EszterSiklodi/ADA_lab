import java.util.*;

public class CityNetwork1 {

    static class Edge implements Comparable<Edge> {
        int city1;
        int city2;
        int month;

        Edge(int city1, int city2, int month) {
            this.city1 = city1;
            this.city2 = city2;
            this.month = month;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(month, other.month);
        }
    }

    static int[] parent;
    static int[] size;

    static int find(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return;
        }
        if (size[rootX] < size[rootY]) {
            int temp = rootX;
            rootX = rootY;
            rootY = temp;
        }
        parent[rootY] = rootX;
        size[rootX] += size[rootY];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            int city1 = scanner.nextInt();
            int city2 = scanner.nextInt();
            int month = scanner.nextInt() - 1;
            pq.offer(new Edge(city1, city2, month));
        }

        int count = 0;
        int lastMonth = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int city1 = edge.city1;
            int city2 = edge.city2;
            int month = edge.month;
            if (find(city1) != find(city2)) {
                union(city1, city2);
                count++;
                lastMonth = month;
            }
        }

        if (count == n - 1) {
            System.out.println(lastMonth + 1);
        } else {
            System.out.println("no such moment");
        }
    }
}

    

