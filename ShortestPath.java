import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.io.InputStreamReader;

class ShortestPath {
    static final int V = 5;
    int minDistance(int[] dist, boolean[] sptSet) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    void printSolution(int[] dist, int[] parent, int src, int dest) {
        System.out.println("Shortest path from " + src + " to " + dest + ":");
        System.out.print(dest + " ");

        int node = parent[dest];
        while (node != src) {
            System.out.print(node + " ");
            node = parent[node];
        }

        System.out.print(src + "\n");

        System.out.println("Distance: " + dist[dest]);
    }

    void dijkstra(int[][] graph, int src, int dest) {
        int[] dist = new int[V];
        int[] parent = new int[V];
        boolean[] sptSet = new boolean[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[src] = 0;
        parent[src] = -1;

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);

            sptSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    parent[v] = u;
                }
            }
        }

        printSolution(dist, parent, src, dest);
    }

     public static void main(String[] args) {
        int[][] graph = new int[][] {
                { 0, 6, 0, 7, 0 },
                { 0, 0, 5, 8, 4 },
                { 0, 2, 0, 0, 0 },
                { 0, 0, 3, 0, 9 },
                { 2, 0, 7, 0, 0 }
        };

        int source = 0;
        int destination = 3;

        ShortestPath t = new ShortestPath();
        t.dijkstra(graph, source, destination);
    }
}

