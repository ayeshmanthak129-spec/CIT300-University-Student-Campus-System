package university.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ServiceQueue implements a Queue data structure adhering to the FIFO (First In, First Out) principle.
 * 
 * FIFO (First In, First Out):
 * The first student service request enqueued is the first one to be dequeued and served.
 * In the University Student & Campus Management System, this is used for managing
 * Student Service Desk inquiries, Academic Advising queues, Financial Aid requests,
 * and Help Desk ticketing where requests must be processed in the exact order they arrive.
 */
public class ServiceQueue {

    // Internal storage using Queue interface backed by LinkedList (FIFO operations)
    private final Queue<String> queue;

    /**
     * Initializes an empty ServiceQueue.
     */
    public ServiceQueue() {
        this.queue = new LinkedList<>();
    }

    /**
     * Enqueue: Adds a student service request to the rear of the queue (FIFO).
     *
     * @param request The description or ticket of the service request.
     * @return true if successfully added, false otherwise.
     */
    public boolean enqueue(String request) {
        if (request == null || request.trim().isEmpty()) {
            System.out.println("Service request cannot be empty.");
            return false;
        }

        request = request.trim();
        queue.offer(request);
        System.out.println("Service request added to queue: " + request);
        return true;
    }

    /**
     * Add Request: Semantic alias for enqueue().
     *
     * @param request The service request to add.
     * @return true if successfully added, false otherwise.
     */
    public boolean addRequest(String request) {
        return enqueue(request);
    }

    /**
     * Dequeue: Removes and returns the next student service request from the front of the queue (FIFO).
     *
     * @return The next service request to be served, or null if the queue is empty.
     */
    public String dequeue() {
        if (isEmpty()) {
            System.out.println("Service queue is empty. No pending requests to serve.");
            return null;
        }

        String request = queue.poll();
        System.out.println("Serving request (FIFO): " + request);
        return request;
    }

    /**
     * Serve Next: Semantic alias for dequeue().
     *
     * @return The service request being served, or null if the queue is empty.
     */
    public String serveNext() {
        return dequeue();
    }

    /**
     * Peek: Inspects the next service request at the front of the queue without removing it.
     *
     * @return The front service request, or null if the queue is empty.
     */
    public String peek() {
        if (isEmpty()) {
            System.out.println("Service queue is empty. No pending requests.");
            return null;
        }

        return queue.peek();
    }

    /**
     * Checks if the service queue has no pending requests.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Returns the number of pending service requests in the queue.
     *
     * @return Number of pending requests.
     */
    public int size() {
        return queue.size();
    }

    /**
     * Displays all pending service requests from front (next to be served) to rear.
     */
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("No pending service requests in the queue.");
            return;
        }

        System.out.println("\n===== SERVICE QUEUE (FIFO - Front to Rear) =====");
        int position = 1;
        for (String request : queue) {
            System.out.println(position + ". " + request + (position == 1 ? " [FRONT / Next to be Served]" : ""));
            position++;
        }
    }

    /**
     * Alias for displayQueue().
     */
    public void displayRequests() {
        displayQueue();
    }

    /**
     * Clears all pending requests from the queue.
     */
    public void clear() {
        queue.clear();
        System.out.println("Service queue cleared.");
    }

    /**
     * Demonstration and testing entry point for ServiceQueue.
     */
    public static void main(String[] args) {
        System.out.println("=== Testing ServiceQueue (FIFO) ===");
        ServiceQueue queue = new ServiceQueue();

        // Testing enqueue
        queue.enqueue("Student 1001: Course Registration Assistance");
        queue.enqueue("Student 1002: Campus ID Card Replacement");
        queue.enqueue("Student 1003: Academic Transcript Request");

        // Display queue
        queue.displayQueue();

        // Testing peek
        System.out.println("\nNext in Line (Peek): " + queue.peek());

        // Testing serveNext / dequeue (FIFO order)
        System.out.println("\n--- Serving Requests in Order ---");
        queue.serveNext();
        queue.displayQueue();

        queue.dequeue();
        queue.displayQueue();

        queue.serveNext();
        queue.displayQueue();

        // Test empty dequeue
        queue.dequeue();
    }
}
