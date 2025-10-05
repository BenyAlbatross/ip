package octoplush.task;

public abstract class Task {
    protected final String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public boolean isDone() {
        return done;
    }

    public String getDescription() {
        return description;
    }

    public abstract char tag();

    protected String extra() {
        return "";
    }

    @Override
    public String toString() {
        return "[" + tag() + "][" + (done ? "X" : " ") + "] " + description + extra();
    }
}
