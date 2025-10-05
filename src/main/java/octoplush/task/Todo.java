package octoplush.task;

/**
 * Represents a simple todo task without a specific time.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public char tag() {
        return 'T';
    }
}
