package university.graph;

import java.util.*;

/**
 * Member 4: Graph Data Structure Implementation
 * Represents campus locations and connections (roads/paths)
 * using an Adjacency List (LinkedHashMap of LinkedHashSets).
 */
public class Graph {

    // Adjacency List: Maps each location to a set of connected neighbouring locations
    private final Map<String, Set<String>> adjacencyList;

    /**
     * Default Constructor
     */
    public Graph() {
        this.adjacencyList = new LinkedHashMap<>();
    }

    /**
     * Add a new campus location (vertex)
     * @param location Name of the location
     * @return true if added successfully, false otherwise
     */
    public boolean addLocation(String location) {
        if (location == null || location.trim().isEmpty()) {
            System.out.println("Location name cannot be empty.");
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

    /**
     * Remove a campus location (vertex) and all associated connections
     * @param location Name of the location to remove
     * @return true if removed successfully, false otherwise
     */
    public boolean removeLocation(String location) {
        if (location == null || location.trim().isEmpty()) {
            System.out.println("Location name cannot be empty.");
            return false;
        }

        location = location.trim();

        if (!adjacencyList.containsKey(location)) {
            System.out.println("Location not found: " + location);
            return false;
        }

        // Remove this location from all adjacent neighbours' connection sets
        for (Set<String> neighbours : adjacencyList.values()) {
            neighbours.remove(location);
        }

        // Remove the vertex from the adjacency list
        adjacencyList.remove(location);
        System.out.println("Location removed successfully: " + location);
        return true;
    }

    /**
     * Add a bidirectional connection (undirected edge) between two locations
     * @param source Source location name
     * @param destination Destination location name
     * @return true if connection was added, false otherwise
     */
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

        if (source.equalsIgnoreCase(destination)) {
            System.out.println("A location cannot connect to itself.");
            return false;
        }

        if (adjacencyList.get(source).contains(destination)) {
            System.out.println("Connection already exists between " + source + " and " + destination + ".");
            return false;
        }

        // Undirected graph: add edge in both directions
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);

        System.out.println("Connection added successfully: " + source + " <-> " + destination);
        return true;
    }

    /**
     * Remove a bidirectional connection between two locations
     * @param source Source location name
     * @param destination Destination location name
     * @return true if connection was removed, false otherwise
     */
    public boolean removeConnection(String source, String destination) {
        if (source == null || destination == null ||
                source.trim().isEmpty() || destination.trim().isEmpty()) {
            System.out.println("Location names cannot be empty.");
            return false;
        }

        source = source.trim();
        destination = destination.trim();

        if (!adjacencyList.containsKey(source) || !adjacencyList.containsKey(destination)) {
            System.out.println("One or both locations do not exist.");
            return false;
        }

        if (!adjacencyList.get(source).contains(destination)) {
            System.out.println("Connection does not exist between " + source + " and " + destination + ".");
            return false;
        }

        // Undirected graph: remove edge in both directions
        adjacencyList.get(source).remove(destination);
        adjacencyList.get(destination).remove(source);

        System.out.println("Connection removed successfully: " + source + " <-> " + destination);
        return true;
    }

    /**
     * Check if a location exists in the graph
     * @param location Name of location to check
     * @return true if location exists, false otherwise
     */
    public boolean containsLocation(String location) {
        if (location == null || location.trim().isEmpty()) {
            return false;
        }
        return adjacencyList.containsKey(location.trim());
    }

    /**
     * Retrieve a set of all campus location names
     * @return Set containing all location names
     */
    public Set<String> getLocations() {
        return Collections.unmodifiableSet(adjacencyList.keySet());
    }

    /**
     * Retrieve all neighbours connected to a specific location
     * @param location Name of the location
     * @return Set of connected location names, or empty set if none/not found
     */
    public Set<String> getNeighbours(String location) {
        if (location == null || !containsLocation(location)) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(adjacencyList.get(location.trim()));
    }

    /**
     * Display all campus connections (Adjacency List view)
     */
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

    /**
     * Display all campus locations
     */
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

    /**
     * Breadth-First Search (BFS) Traversal
     * Traverses level-by-level starting from the specified location using a Queue (FIFO).
     * @param startLocation Starting vertex for BFS
     */
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

        System.out.println("\n===== BFS TRAVERSAL (Starting at: " + startLocation + ") =====");

        boolean first = true;
        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (!first) {
                System.out.print(" -> ");
            }
            System.out.print(current);
            first = false;

            for (String neighbour : adjacencyList.get(current)) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.offer(neighbour);
                }
            }
        }
        System.out.println();
    }

    /**
     * Depth-First Search (DFS) Traversal
     * Traverses branch-by-branch starting from the specified location using recursion.
     * @param startLocation Starting vertex for DFS
     */
    public void dfs(String startLocation) {
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
        System.out.println("\n===== DFS TRAVERSAL (Starting at: " + startLocation + ") =====");
        dfsHelper(startLocation, visited, true);
        System.out.println();
    }

    /**
     * Recursive helper for Depth-First Search (DFS)
     */
    private void dfsHelper(String current, Set<String> visited, boolean isFirst) {
        visited.add(current);

        if (!isFirst) {
            System.out.print(" -> ");
        }
        System.out.print(current);

        for (String neighbour : adjacencyList.get(current)) {
            if (!visited.contains(neighbour)) {
                dfsHelper(neighbour, visited, false);
            }
        }
    }
}
