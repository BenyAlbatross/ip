package octoplush.task;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private final String by;

    /**
     * Creates a new deadline task.
     *
     * @param description The description of the task.
     * @param by The deadline for the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the deadline time for this task.
     *
     * @return The deadline string.
     */
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