import java.util.LinkedList;
import java.util.Queue;

/**
 * ServiceQueue implements a Queue data structure adhering to the FIFO (First In, First Out) principle.
 * 
 * FIFO (First In, First Out):
 * The first student service request enqueued is the first one to be dequeued and served.
 * This is used in the Campus Management System for student service desk requests, advising queues, and ticketing.
 */
public class ServiceQueue {

    // Internal storage using Queue (LinkedList implementation)
    private final Queue<String> queue;

    // Constructor
    public ServiceQueue() {
        this.queue = new LinkedList<>();
    }

    // Enqueue: Add a student service request to the back of the queue (FIFO)
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

    // Add request: Alias for enqueue()
    public boolean addRequest(String request) {
        return enqueue(request);
    }

    // Dequeue: Remove and return the next student service request from the front of the queue (FIFO)
    public String dequeue() {
        if (isEmpty()) {
            System.out.println("Service queue is empty. No pending requests to serve.");
            return null;
        }

        String request = queue.poll();
        System.out.println("Serving request (FIFO): " + request);
        return request;
    }

    // Serve next: Alias for dequeue()
    public String serveNext() {
        return dequeue();
    }

    // Peek: View the next service request at the front of the queue without removing it
    public String peek() {
        if (isEmpty()) {
            System.out.println("Service queue is empty. No pending requests.");
            return null;
        }

        return queue.peek();
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // Return the number of pending requests in the queue
    public int size() {
        return queue.size();
    }

    // Display all pending service requests from front (first in line) to rear (last in line)
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

    // Display requests: Alias for displayQueue
    public void displayRequests() {
        displayQueue();
    }

    // Clear all requests from the queue
    public void clear() {
        queue.clear();
        System.out.println("Service queue cleared.");
    }
}
