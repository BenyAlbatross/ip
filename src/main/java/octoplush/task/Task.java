package octoplush.task;

/**
 * Represents a task in the Octoplush application.
 * All specific task types inherit from this abstract class.
 */
public abstract class Task {
    protected final String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Marks this task as done.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Marks this task as not done.
     */
    public void unmark() {
        this.done = false;
    }

    /**
     * Checks if this task is marked as done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Gets the description of this task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the single-character tag representing this task type.
     *
     * @return The task type tag (e.g., 'T' for Todo, 'D' for Deadline).
     */
    public abstract char tag();

    protected String extra() {
        return "";
    }

    @Override
    public String toString() {
        return "[" + tag() + "][" + (done ? "X" : " ") + "] " + description + extra();
    }
}
