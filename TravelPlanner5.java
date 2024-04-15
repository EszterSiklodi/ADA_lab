import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.io.InputStreamReader;

class City implements Comparable<City> {
    int id;
    int cost;

    public City(int id, int cost) {
        this.id = id;
        this.cost = cost;
    }

    @Override
    public int compareTo(City other) {
        return Integer.compare(cost, other.cost);
    }
}

class Edge {
    int destination;
    int price;

    public Edge(int destination, int price) {
        this.destination = destination;
        this.price = price;
    }
}

public class TravelPlanner5 {
    private static final int INF = Integer.MAX_VALUE;

    private int numCities;
    private List<List<Edge>> graph;

    public TravelPlanner5(int numCities) {
        this.numCities = numCities;
        this.graph = new ArrayList<>(numCities);
        for (int i = 0; i < numCities; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public void addConnection(int city1, int city2, int price) {
        graph.get(city1).add(new Edge(city2, price));
    }

    public List<Integer> findShortestPath(int source, int destination) {
        int[] dist = new int[numCities];
        int[] prev = new int[numCities];
        Arrays.fill(dist, INF);
        Arrays.fill(prev, -1);

        PriorityQueue<City> pq = new PriorityQueue<>();
        pq.offer(new City(source, 0));
        dist[source] = 0;

        while (!pq.isEmpty()) {
            City current = pq.poll();
            if (current.id == destination) {
                break;  // Reached the destination city
            }

            if (current.cost > dist[current.id]) {
                continue;  // Skip outdated/costlier paths
            }

            for (Edge edge : graph.get(current.id)) {
                int newCost = current.cost + edge.price;
                if (newCost < dist[edge.destination]) {
                    dist[edge.destination] = newCost;
                    prev[edge.destination] = current.id;
                    pq.offer(new City(edge.destination, newCost));
                }
            }
        }

        // Reconstruct the shortest path
        List<Integer> path = new ArrayList<>();
        int currentCity = destination;
        while (currentCity != -1) {
            path.add(0, currentCity);
            currentCity = prev[currentCity];
        }

        return path;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("g_dir_weight_positive_15.txt"));  // Replace "graph.txt" with your input file path

        int numCities = Integer.parseInt(reader.readLine());
        int numConnections = Integer.parseInt(reader.readLine());

        TravelPlanner5 planner = new TravelPlanner5(numCities);

        for (int i = 0; i < numConnections; i++) {
            String[] parts = reader.readLine().split(" ");
            int city1 = Integer.parseInt(parts[0]);
            int city2 = Integer.parseInt(parts[1]);
            int price = Integer.parseInt(parts[2]);
            planner.addConnection(city1, city2, price);
        }

        reader.close();

        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the source city: ");
        int source = Integer.parseInt(inputReader.readLine());
        System.out.print("Enter the destination city: ");
        int destination = Integer.parseInt(inputReader.readLine());
        inputReader.close();

        List<Integer> shortestPath = planner.findShortestPath(source, destination);

        if (shortestPath.isEmpty()) {
            System.out.println("No path found from source to destination.");
            } else {
            System.out.println("Cheapest travel route:");
            for (int city : shortestPath) {
            System.out.print(city + " ");
            }
            System.out.println();
            System.out.println("Total price: " + (shortestPath.size() - 1));
     }
    }
 }