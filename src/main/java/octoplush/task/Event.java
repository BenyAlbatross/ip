package octoplush.task;

/**
 * Represents an event, which is a task that occurs during a specific time period.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Creates a new event task.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the start time of this event.
     *
     * @return The start time string.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Gets the end time of this event.
     *
     * @return The end time string.
     */
    public String getTo() {
        return to;
    }

    @Override
    public char tag() {
        return 'E';
    }

    @Override
    protected String extra() {
        return " (from: " + from + " to: " + to + ")";
    }
}
