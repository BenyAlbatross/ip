package octoplush.task;

public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

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
