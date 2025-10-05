package octoplush.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event, which is a task that occurs during a specific time period.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * Creates a new event task.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the start time of this event.
     *
     * @return The start time LocalDateTime.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Gets the end time of this event.
     *
     * @return The end time LocalDateTime.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Gets the start time as a formatted string for storage.
     *
     * @return The start time in yyyy-MM-dd HHmm format.
     */
    public String getFromString() {
        return from.format(INPUT_FORMAT);
    }

    /**
     * Gets the end time as a formatted string for storage.
     *
     * @return The end time in yyyy-MM-dd HHmm format.
     */
    public String getToString() {
        return to.format(INPUT_FORMAT);
    }

    @Override
    public char tag() {
        return 'E';
    }

    @Override
    protected String extra() {
        return " (from: " + from.format(OUTPUT_FORMAT) + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }
}
