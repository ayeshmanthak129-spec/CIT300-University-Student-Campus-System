package university.graph;

import java.util.*;

public class CampusGraph {

    private final Map<String, List<String>> adjacencyList
            = new LinkedHashMap<>();

    // Add a campus location
    public boolean addLocation(String location) {

        if (location == null || location.isBlank()) {
            return false;
        }

        if (adjacencyList.containsKey(location)) {
            return false;
        }

        adjacencyList.put(location, new ArrayList<>());

        return true;
    }

    // Remove a campus location
    public boolean removeLocation(String location) {

        if (!adjacencyList.containsKey(location)) {
            return false;
        }

        adjacencyList.remove(location);

        for (List<String> neighbours : adjacencyList.values()) {
            neighbours.remove(location);
        }

        return true;
    }

    // Add connection between two locations
    public boolean addConnection(String from, String to) {

        if (!adjacencyList.containsKey(from)
                || !adjacencyList.containsKey(to)
                || from.equals(to)) {

            return false;
        }

        if (adjacencyList.get(from).contains(to)) {
            return false;
        }

        adjacencyList.get(from).add(to);
        adjacencyList.get(to).add(from);

        return true;
    }

    // Remove connection
    public boolean removeConnection(String from, String to) {

        if (!adjacencyList.containsKey(from)
                || !adjacencyList.containsKey(to)) {

            return false;
        }

        boolean removed1 =
                adjacencyList.get(from).remove(to);

        boolean removed2 =
                adjacencyList.get(to).remove(from);

        return removed1 && removed2;
    }

    // Display all connections
    public void displayConnections() {

        if (adjacencyList.isEmpty()) {
            System.out.println("No campus locations found.");
            return;
        }

        System.out.println("--- Campus Network ---");

        for (Map.Entry<String, List<String>> entry
                : adjacencyList.entrySet()) {

            System.out.println(
                    entry.getKey() + " -> " + entry.getValue()
            );
        }
    }

    // Breadth First Search
    public void bfs(String start) {

        if (!adjacencyList.containsKey(start)) {
            System.out.println("Starting location not found.");
            return;
        }

        Set<String> visited = new LinkedHashSet<>();

        Queue<String> queue = new LinkedList<>();

        visited.add(start);
        queue.offer(start);

        System.out.print("BFS Traversal: ");

        while (!queue.isEmpty()) {

            String current = queue.poll();

            System.out.print(current + " ");

            for (String neighbour :
                    adjacencyList.get(current)) {

                if (!visited.contains(neighbour)) {

                    visited.add(neighbour);

                    queue.offer(neighbour);
                }
            }
        }

        System.out.println();
    }
}