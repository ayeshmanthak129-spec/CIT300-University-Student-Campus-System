package university;

import java.util.Scanner;
import university.graph.CampusGraph;
import university.stackqueue.ActionStack;
import university.stackqueue.ServiceQueue;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        CampusGraph campusGraph = new CampusGraph();
        ActionStack actionStack = new ActionStack();
        ServiceQueue serviceQueue = new ServiceQueue();

        while (true) {

            System.out.println("\n===== CAMPUS ROUTE & SERVICE MANAGEMENT SYSTEM =====");
            System.out.println("--- Campus Route & Location Operations ---");
            System.out.println("1. Add Campus Location");
            System.out.println("2. Remove Campus Location");
            System.out.println("3. Add Campus Connection/Road");
            System.out.println("4. Remove Campus Connection/Road");
            System.out.println("5. Display Campus Connections");
            System.out.println("6. Display Campus Locations");
            System.out.println("7. Traverse Campus Locations using BFS");
            System.out.println("--- Action History & Undo (Stack - LIFO) ---");
            System.out.println("8. Undo Last Action (LIFO)");
            System.out.println("9. Display Action History / Stack (LIFO)");
            System.out.println("--- Student Service Desk (Queue - FIFO) ---");
            System.out.println("10. Add Student Service Request (FIFO)");
            System.out.println("11. Serve Next Student Request (FIFO)");
            System.out.println("12. Display Pending Service Requests (FIFO)");
            System.out.println("13. Exit");

            System.out.print("Enter your choice: ");

            String input = scanner.nextLine();

            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter campus location: ");
                    String location = scanner.nextLine();
                    if (campusGraph.addLocation(location)) {
                        actionStack.push("Added location: " + location.trim());
                    }
                }
                case 2 -> {
                    System.out.print("Enter location to remove: ");
                    String removeLocation = scanner.nextLine();
                    if (campusGraph.removeLocation(removeLocation)) {
                        actionStack.push("Removed location: " + removeLocation.trim());
                    }
                }
                case 3 -> {
                    System.out.print("Enter source location: ");
                    String source = scanner.nextLine();
                    System.out.print("Enter destination location: ");
                    String destination = scanner.nextLine();
                    if (campusGraph.addConnection(source, destination)) {
                        actionStack.push("Added road: " + source.trim() + " <-> " + destination.trim());
                    }
                }
                case 4 -> {
                    System.out.print("Enter source location: ");
                    String removeSource = scanner.nextLine();
                    System.out.print("Enter destination location: ");
                    String removeDestination = scanner.nextLine();
                    if (campusGraph.removeConnection(removeSource, removeDestination)) {
                        actionStack.push("Removed road: " + removeSource.trim() + " <-> " + removeDestination.trim());
                    }
                }
                case 5 -> campusGraph.displayConnections();
                case 6 -> campusGraph.displayLocations();
                case 7 -> {
                    System.out.print("Enter starting location for BFS: ");
                    String startLocation = scanner.nextLine();
                    campusGraph.bfs(startLocation);
                }
                case 8 -> undoLastAction(actionStack, campusGraph);
                case 9 -> actionStack.displayStack();
                case 10 -> {
                    System.out.print("Enter student service request: ");
                    String request = scanner.nextLine();
                    serviceQueue.enqueue(request);
                }
                case 11 -> serviceQueue.dequeue();
                case 12 -> serviceQueue.displayQueue();
                case 13 -> {
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please select 1-13.");
            }
        }
    }

    // Helper method to demonstrate LIFO Undo (Last In, First Out)
    private static void undoLastAction(ActionStack actionStack, CampusGraph campusGraph) {
        if (actionStack.isEmpty()) {
            System.out.println("No actions to undo. Stack is empty.");
            return;
        }

        System.out.println("\n--- Undoing Last Action (LIFO: Last In, First Out) ---");
        String lastAction = actionStack.pop();

        if (lastAction == null) return;

        if (lastAction.startsWith("Added location: ")) {
            String location = lastAction.substring("Added location: ".length());
            System.out.println("Reverting addition of location: " + location);
            campusGraph.removeLocation(location);
        } else if (lastAction.startsWith("Removed location: ")) {
            String location = lastAction.substring("Removed location: ".length());
            System.out.println("Reverting removal of location: " + location);
            campusGraph.addLocation(location);
        } else if (lastAction.startsWith("Added road: ")) {
            String conn = lastAction.substring("Added road: ".length());
            String[] parts = conn.split(" <-> ");
            if (parts.length == 2) {
                System.out.println("Reverting addition of road: " + parts[0] + " <-> " + parts[1]);
                campusGraph.removeConnection(parts[0], parts[1]);
            }
        } else if (lastAction.startsWith("Removed road: ")) {
            String conn = lastAction.substring("Removed road: ".length());
            String[] parts = conn.split(" <-> ");
            if (parts.length == 2) {
                System.out.println("Reverting removal of road: " + parts[0] + " <-> " + parts[1]);
                campusGraph.addConnection(parts[0], parts[1]);
            }
        }
    }
}
