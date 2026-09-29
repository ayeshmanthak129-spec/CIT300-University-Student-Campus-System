import java.util.LinkedList;

/**
 * ActionStack implements a Stack data structure adhering to the LIFO (Last In, First Out) principle.
 * 
 * LIFO (Last In, First Out):
 * The last action pushed onto the stack is the first one to be popped/undone.
 * This is used in the Campus Management System to maintain an action history and support undo operations.
 */
public class ActionStack {

    // Internal storage using LinkedList as a LIFO stack
    private final LinkedList<String> stack;

    // Constructor
    public ActionStack() {
        this.stack = new LinkedList<>();
    }

    // Push: Add an action onto the top of the stack (LIFO)
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

    // Pop: Remove and return the most recent action from the top of the stack (LIFO)
    public String pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty. No actions to undo/remove.");
            return null;
        }

        String action = stack.pop();
        System.out.println("Action popped (LIFO): " + action);
        return action;
    }

    // Undo: Helper alias for pop()
    public String undo() {
        return pop();
    }

    // Peek: View the most recent action at the top of the stack without removing it
    public String peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty. No actions available.");
            return null;
        }

        return stack.peek();
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    // Return the number of actions currently in the stack
    public int size() {
        return stack.size();
    }

    // Display all actions from top (most recent) to bottom (oldest)
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

    // Display actions (alias for displayStack)
    public void displayActions() {
        displayStack();
    }

    // Clear all actions from the stack
    public void clear() {
        stack.clear();
        System.out.println("Action stack cleared.");
    }
}
