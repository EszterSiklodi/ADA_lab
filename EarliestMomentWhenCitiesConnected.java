/*Earliest moment when cities are connected

A country has N cities, each having a unique ID value from 0 to (N - 1). Local authorities are planning 
to build M roads connecting the cities, but works are planned to last M months. Each month, only one road can be built.
The schedule of planned works is given in the form of triplets representing city1 city2 planned month.

Find the earliest time moment (earliest month) when all the cities will be connected by a network, or print that there
 will be no such moment. The cities are connected by a network if for any pair of cities there is a path between them.

The input is read from a text file in the form: 
The first line contains the value of N 
The second line contains the value of M 
The next M lines contain triplets of 3 numbers: number1 number2 number3, where number1 and number2 are city IDs (0..N-1) 
and number3 is a month (1..M). 

Hint: use Disjoint Sets

 */
import java.io.*;
import java.util.*;

public class EarliestMomentWhenCitiesConnected {
    static int[] parent;
    static int[] rank;
    
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("road_moments_5_10.txt"));
            int n = scanner.nextInt(); //nr of cities
            int m = scanner.nextInt();// nr of roads to built
            List<Edge> edges = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt(); //city1
                int v = scanner.nextInt();//city2
                int w = scanner.nextInt();//month
                edges.add(new Edge(u, v, w));
            }
            scanner.close();
            
            Collections.sort(edges, Comparator.comparingInt(e -> e.w));
            parent = new int[n];
            rank = new int[n];
            Arrays.fill(rank, 1);
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            
            int count = 0;
            int time = 0;
            for (Edge edge : edges) {
                int u = edge.u;
                int v = edge.v;
                int w = edge.w;
                int pu = find(u);
                int pv = find(v);
                if (pu != pv) {
                    union(pu, pv);
                    count++;
                    time = w;
                }
                if (count == n - 1) {
                    break;
                }
            }
            if (count == n - 1) {
                System.out.println("All cities are connected at time " + time);
            } else {
                System.out.println("Not all cities can be connected");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    static int find(int u) {
        if (parent[u] == u) {
            return u;
        }
        parent[u] = find(parent[u]);
        return parent[u];
    }
    
    static void union(int u, int v) {
        if (rank[u] > rank[v]) {
            parent[v] = u;
        } else if (rank[u] < rank[v]) {
            parent[u] = v;
        } else {
            parent[u] = v;
            rank[v]++;
        }
    }
    
    static class Edge {
        int u, v, w;
        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}
