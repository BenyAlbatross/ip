package octoplush.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * Creates a new deadline task.
     *
     * @param description The description of the task.
     * @param by The deadline for the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the deadline time for this task.
     *
     * @return The deadline LocalDateTime.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Gets the deadline as a formatted string for storage.
     *
     * @return The deadline in yyyy-MM-dd HHmm format.
     */
    public String getByString() {
        return by.format(INPUT_FORMAT);
    }

    @Override
    public char tag() {
        return 'D';
    }

    @Override
    protected String extra() {
        return " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }
}