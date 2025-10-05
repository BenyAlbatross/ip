package octoplush.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public char tag() {
        return 'T';
    }
}
