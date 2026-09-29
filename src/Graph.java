import java.util.*;

public class Graph {

    // Adjacency List
    private final Map<String, Set<String>> adjacencyList;

    // Constructor
    public Graph() {
        adjacencyList = new LinkedHashMap<>();
    }

    // Add a campus location
    public boolean addLocation(String location) {

        if (location == null || location.trim().isEmpty()) {
            System.out.println("Location cannot be empty.");
            return false;
        }

        location = location.trim();

        if (adjacencyList.containsKey(location)) {
            System.out.println("Location already exists: " + location);
            return false;
        }

        adjacencyList.put(location, new LinkedHashSet<>());

        System.out.println("Location added successfully: " + location);
        return true;
    }

    // Remove a campus location
    public boolean removeLocation(String location) {

        if (location == null || location.trim().isEmpty()) {
            System.out.println("Location cannot be empty.");
            return false;
        }

        location = location.trim();

        if (!adjacencyList.containsKey(location)) {
            System.out.println("Location not found: " + location);
            return false;
        }

        // Remove this location from all neighbour lists
        for (Set<String> neighbours : adjacencyList.values()) {
            neighbours.remove(location);
        }

        // Remove the location
        adjacencyList.remove(location);

        System.out.println("Location removed successfully: " + location);
        return true;
    }

    // Add a campus connection / road
    public boolean addConnection(String source, String destination) {

        if (source == null || destination == null ||
                source.trim().isEmpty() || destination.trim().isEmpty()) {

            System.out.println("Location names cannot be empty.");
            return false;
        }

        source = source.trim();
        destination = destination.trim();

        if (!adjacencyList.containsKey(source)) {
            System.out.println("Source location does not exist: " + source);
            return false;
        }

        if (!adjacencyList.containsKey(destination)) {
            System.out.println("Destination location does not exist: " + destination);
            return false;
        }

        if (source.equals(destination)) {
            System.out.println("A location cannot connect to itself.");
            return false;
        }

        if (adjacencyList.get(source).contains(destination)) {
            System.out.println("Connection already exists.");
            return false;
        }

        // Undirected connection
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);

        System.out.println("Connection added successfully.");
        return true;
    }

    // Remove a campus connection / road
    public boolean removeConnection(String source, String destination) {

        if (source == null || destination == null ||
                source.trim().isEmpty() || destination.trim().isEmpty()) {

            System.out.println("Location names cannot be empty.");
            return false;
        }

        source = source.trim();
        destination = destination.trim();

        if (!adjacencyList.containsKey(source) ||
                !adjacencyList.containsKey(destination)) {

            System.out.println("One or both locations do not exist.");
            return false;
        }

        if (!adjacencyList.get(source).contains(destination)) {
            System.out.println("Connection does not exist.");
            return false;
        }

        adjacencyList.get(source).remove(destination);
        adjacencyList.get(destination).remove(source);

        System.out.println("Connection removed successfully.");
        return true;
    }

    // Display all campus connections
    public void displayConnections() {

        if (adjacencyList.isEmpty()) {
            System.out.println("No campus locations available.");
            return;
        }

        System.out.println("\n===== CAMPUS CONNECTIONS =====");

        for (Map.Entry<String, Set<String>> entry : adjacencyList.entrySet()) {

            System.out.print(entry.getKey() + " -> ");

            if (entry.getValue().isEmpty()) {
                System.out.println("No connections");
            } else {
                System.out.println(String.join(", ", entry.getValue()));
            }
        }
    }

    // Breadth First Search (BFS)
    public void bfs(String startLocation) {

        if (startLocation == null || startLocation.trim().isEmpty()) {
            System.out.println("Start location cannot be empty.");
            return;
        }

        startLocation = startLocation.trim();

        if (!adjacencyList.containsKey(startLocation)) {
            System.out.println("Start location not found: " + startLocation);
            return;
        }

        Set<String> visited = new LinkedHashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(startLocation);
        queue.offer(startLocation);

        System.out.println("\n===== BFS TRAVERSAL =====");

        while (!queue.isEmpty()) {

            String current = queue.poll();

            System.out.print(current + " ");

            for (String neighbour : adjacencyList.get(current)) {

                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.offer(neighbour);
                }
            }
        }

        System.out.println();
    }

    // Display all campus locations
    public void displayLocations() {

        if (adjacencyList.isEmpty()) {
            System.out.println("No campus locations available.");
            return;
        }

        System.out.println("\n===== CAMPUS LOCATIONS =====");

        for (String location : adjacencyList.keySet()) {
            System.out.println("- " + location);
        }
    }
}
