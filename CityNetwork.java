/*
Earliest moment when cities are connected

A country has N cities, each having a unique ID value from 0 to (N - 1). Local authorities are planning to build M roads connecting the cities, but works are planned to last M months. Each month, only one road can be built.
The schedule of planned works is given in the form of triplets representing city1 city2 planned month.
Find the earliest time moment (earliest month) when all the cities will be connected by a network, or print that there will be no such moment. The cities are connected by a network if for any pair of cities there is a path between them.
The input is read from a text file in the form: 
The first line contains the value of N 
The second line contains the value of M 
The next M lines contain triplets of 3 numbers: number1 number2 number3, where number1 and number2 are city IDs (0..N-1) and number3 is a month (1..M). 

Hint: use Disjoint Sets

Use input data from files given here
 */

 import java.io.*;
 import java.util.*;
 
 public class CityNetwork {
     public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new File("30Kint_3.txt"));

         String[] line = scan.readLine().split("30Kint_3.txt ");
         int n = Integer.parseInt(line[0]);
         int m = Integer.parseInt(line[1]);
 
         DisjointSet dsu = new DisjointSet(n);
         int numConnectedComponents = n; // initially, each vertex is in its own component
 
         // read in the edges and sort them by their planned month
         List<Edge> edges = new ArrayList<>();
         for (int i = 0; i < m; i++) {
             line = scan.readLine().split("30Kint_3.txt");
             int u = Integer.parseInt(line[0]);
             int v = Integer.parseInt(line[1]);
             int month = Integer.parseInt(line[2]);
             edges.add(new Edge(u, v, month));
         }
         Collections.sort(edges);
 
         int earliestMonth = -1;
         for (Edge e : edges) {
             int u = e.u;
             int v = e.v;
             int month = e.month;
             int root1 = dsu.find(u);
             int root2 = dsu.find(v);
             if (root1 != root2) { // the vertices are in different components
                 dsu.union(root1, root2);
                 numConnectedComponents--;
                 if (numConnectedComponents == 1) { // all vertices are now in the same component
                     earliestMonth = month;
                     break;
                 }
             }
         }
 
         if (earliestMonth == -1) {
             System.out.println("There is no moment when all cities will be connected.");
         } else {
             System.out.println("The earliest moment when all cities will be connected is month " + earliestMonth + ".");
         }
     }
 
     static class Edge implements Comparable<Edge> {
         int u, v, month;
 
         public Edge(int u, int v, int month) {
             this.u = u;
             this.v = v;
             this.month = month;
         }
 
         public int compareTo(Edge other) {
             return Integer.compare(month, other.month);
         }
     }
 
     static class DisjointSet {
         int[] parent;
         int[] rank;
 
         public DisjointSet(int n) {
             parent = new int[n];
             rank = new int[n];
             for (int i = 0; i < n; i++) {
                 parent[i] = i;
                 rank[i] = 0;
             }
         }
 
         public int find(int x) {
             if (parent[x] != x) {
                 parent[x] = find(parent[x]);
             }
             return parent[x];
         }
 
         public void union(int x, int y) {
             int root1 = find(x);
             int root2 = find(y);
             if (root1 == root2) return;
             if (rank[root1] < rank[root2]) {
                 parent[root1] = root2;
             } else if (rank[root1] > rank[root2]) {
                 parent[root2] = root1;
             } else {
                 parent[root1] = root2;
                 rank[root2]++;
             }
         }
     }
 }
 