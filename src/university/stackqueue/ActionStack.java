package university.stackqueue;

import java.util.LinkedList;

/**
 * ActionStack implements a Stack data structure adhering to the LIFO (Last In, First Out) principle.
 * 
 * LIFO (Last In, First Out):
 * The last action pushed onto the stack is the first one to be popped/undone.
 * In the University Student & Campus Management System, this is used to maintain an
 * audit trail of administrative/campus modifications and to support Undo operations
 * (e.g., reverting added/removed campus locations, connections, or student updates).
 */
public class ActionStack {

    // Internal storage using LinkedList as a LIFO stack (Deque operations)
    private final LinkedList<String> stack;

    /**
     * Initializes an empty ActionStack.
     */
    public ActionStack() {
        this.stack = new LinkedList<>();
    }

    /**
     * Push: Adds an action onto the top of the stack (LIFO).
     *
     * @param action Description of the action performed.
     * @return true if successfully recorded, false otherwise.
     */
    public boolean push(String action) {
        if (action == null || action.trim().isEmpty()) {
            System.out.println("Action cannot be empty.");
            return false;
        }

        action = action.trim();
        stack.push(action);
        System.out.println("Action recorded: " + action);
        return true;
    }

    /**
     * Pop: Removes and returns the most recent action from the top of the stack (LIFO).
     *
     * @return The most recent action, or null if the stack is empty.
     */
    public String pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty. No actions to undo/remove.");
            return null;
        }

        String action = stack.pop();
        System.out.println("Action popped (LIFO): " + action);
        return action;
    }

    /**
     * Undo: Semantic alias for pop() to reverse the latest action.
     *
     * @return The reverted action, or null if the stack is empty.
     */
    public String undo() {
        return pop();
    }

    /**
     * Peek: Inspects the most recent action at the top of the stack without removing it.
     *
     * @return The top action, or null if the stack is empty.
     */
    public String peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty. No actions available.");
            return null;
        }

        return stack.peek();
    }

    /**
     * Checks if the stack has no actions.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * Returns the number of actions currently stored in the stack.
     *
     * @return Count of actions.
     */
    public int size() {
        return stack.size();
    }

    /**
     * Displays all actions from top (most recent) to bottom (oldest).
     */
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("No actions in the stack.");
            return;
        }

        System.out.println("\n===== ACTION STACK (LIFO - Top to Bottom) =====");
        int index = 1;
        for (String action : stack) {
            System.out.println(index + ". " + action + (index == 1 ? " [TOP / Most Recent]" : ""));
            index++;
        }
    }

    /**
     * Alias for displayStack().
     */
    public void displayActions() {
        displayStack();
    }

    /**
     * Clears all actions from the stack.
     */
    public void clear() {
        stack.clear();
        System.out.println("Action stack cleared.");
    }

    /**
     * Demonstration and testing entry point for ActionStack.
     */
    public static void main(String[] args) {
        System.out.println("=== Testing ActionStack (LIFO) ===");
        ActionStack stack = new ActionStack();

        // Testing push
        stack.push("Added Location: Library");
        stack.push("Added Location: Science Block");
        stack.push("Added Connection: Library <-> Science Block");

        // Display stack
        stack.displayStack();

        // Testing peek
        System.out.println("\nPeek Top Action: " + stack.peek());

        // Testing undo / pop (LIFO order)
        System.out.println("\n--- Performing Undo Operations ---");
        stack.undo();
        stack.displayStack();

        stack.pop();
        stack.displayStack();

        stack.undo();
        stack.displayStack();

        // Test empty pop
        stack.pop();
    }
}
