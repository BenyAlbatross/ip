package octoplush.task;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public char tag() {
        return 'D';
    }

    @Override
    protected String extra() {
        return " (by: " + by + ")";
    }
}