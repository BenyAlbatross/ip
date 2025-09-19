import java.util.Scanner;

public class Octoplush {
    private static final String SEP = "    ____________________________________________________________";
    private static final String IND = "     ";

    // --- Level-5: custom exception type(s) ---
    private static class DukeException extends RuntimeException {
        DukeException(String message) { super(message); }
    }

    private static abstract class Task {
        protected final String description;
        protected boolean done;

        Task(String description) {
            this.description = description;
            this.done = false;
        }

        void mark() { this.done = true; }
        void unmark() { this.done = false; }

        protected abstract char tag();

        /** Extra trailing info e.g. (by: ...), (from: ... to: ...). Return "" if none. */
        protected String extra() { return ""; }

        @Override
        public String toString() {
            return "[" + tag() + "][" + (done ? "X" : " ") + "] " + description + extra();
        }
    }

    private static class Todo extends Task {
        Todo(String description) { super(description); }
        @Override protected char tag() { return 'T'; }
    }

    private static class Deadline extends Task {
        private final String by;
        Deadline(String description, String by) {
            super(description);
            this.by = by;
        }
        @Override protected char tag() { return 'D'; }
        @Override protected String extra() { return " (by: " + by + ")"; }
    }

    private static class Event extends Task {
        private final String from, to;
        Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }
        @Override protected char tag() { return 'E'; }
        @Override protected String extra() { return " (from: " + from + " to: " + to + ")"; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int count = 0;

        // Greeting
        System.out.println(SEP);
        System.out.println(IND + "Hello! I'm Octoplush");
        System.out.println(IND + "What can I do for you?");
        System.out.println(SEP);

        while (true) {
            String line = sc.nextLine().trim();

            // allow empty input to render an empty block (unchanged aesthetic)
            if (line.isEmpty()) {
                System.out.println(SEP);
                System.out.println(IND);
                System.out.println(SEP);
                continue;
            }

            if (line.equals("bye")) {
                System.out.println(SEP);
                System.out.println(IND + "Bye. Hope to see you again soon!");
                System.out.println(SEP);
                break;
            }

            try {
                if (line.equals("list")) {
                    System.out.println(SEP);
                    System.out.println(IND + "Here are the tasks in your list:");
                    for (int i = 0; i < count; i++) {
                        // NOTE: no space after the dot, to match sample output
                        System.out.println(IND + (i + 1) + "." + tasks[i]);
                    }
                    System.out.println(SEP);

                } else if (line.startsWith("mark ")) {
                    int idx = parseIndexOrThrow(line.substring(5), count, "mark");
                    tasks[idx - 1].mark();
                    System.out.println(SEP);
                    System.out.println(IND + "Nice! I've marked this task as done:");
                    System.out.println(IND + "  " + tasks[idx - 1]);
                    System.out.println(SEP);

                } else if (line.startsWith("unmark ")) {
                    int idx = parseIndexOrThrow(line.substring(7), count, "unmark");
                    tasks[idx - 1].unmark();
                    System.out.println(SEP);
                    System.out.println(IND + "OK, I've marked this task as not done yet:");
                    System.out.println(IND + "  " + tasks[idx - 1]);
                    System.out.println(SEP);

                } else if (line.startsWith("todo")) {
                    // Allow "todo" and "todo ..." — but empty description is an error
                    String desc = (line.length() >= 4 ? line.substring(4) : "").trim();
                    requireNonEmpty(desc, "The description of a todo cannot be empty.");
                    ensureCapacity(count, tasks.length);
                    tasks[count++] = new Todo(desc);
                    printAdded(tasks[count - 1], count);

                } else if (line.startsWith("deadline")) {
                    // Format: deadline <desc> /by <when>
                    String rest = (line.length() >= 8 ? line.substring(8) : "").trim();
                    int byIdx = rest.indexOf("/by ");
                    if (byIdx < 0) {
                        throw new DukeException("Invalid deadline format. Use: deadline <desc> /by <when>");
                    }
                    String desc = rest.substring(0, byIdx).trim();
                    String by = rest.substring(byIdx + 4).trim();
                    requireNonEmpty(desc, "The description of a deadline cannot be empty.");
                    requireNonEmpty(by, "The '/by' time for a deadline cannot be empty.");
                    ensureCapacity(count, tasks.length);
                    tasks[count++] = new Deadline(desc, by);
                    printAdded(tasks[count - 1], count);

                } else if (line.startsWith("event")) {
                    // Format: event <desc> /from <start> /to <end>
                    String rest = (line.length() >= 5 ? line.substring(5) : "").trim();
                    int fromIdx = rest.indexOf("/from ");
                    int toIdx = rest.indexOf("/to ");
                    if (fromIdx < 0 || toIdx < 0 || toIdx <= fromIdx) {
                        throw new DukeException(
                                "Invalid event format. Use: event <desc> /from <start> /to <end>"
                        );
                    }
                    String desc = rest.substring(0, fromIdx).trim();
                    String from = rest.substring(fromIdx + 6, toIdx).trim();
                    String to = rest.substring(toIdx + 4).trim();
                    requireNonEmpty(desc, "The description of an event cannot be empty.");
                    requireNonEmpty(from, "The '/from' time for an event cannot be empty.");
                    requireNonEmpty(to, "The '/to' time for an event cannot be empty.");
                    ensureCapacity(count, tasks.length);
                    tasks[count++] = new Event(desc, from, to);
                    printAdded(tasks[count - 1], count);

                } else {
                    // Level-5: unknown command -> error block (no silent fallback)
                    throw new DukeException("Sorry, I don’t recognise that command. Try: list, todo, deadline, event, mark, unmark, bye.");
                }

            } catch (DukeException ex) {
                printError(ex.getMessage());
            }
        }

        sc.close();
    }

    private static void printAdded(Task t, int count) {
        System.out.println(SEP);
        System.out.println(IND + "Got it. I've added this task:");
        System.out.println(IND + "  " + t);
        System.out.println(IND + "Now you have " + count + " tasks in the list.");
        System.out.println(SEP);
    }

    private static void printError(String msg) {
        System.out.println(SEP);
        System.out.println(IND + msg);
        System.out.println(SEP);
    }

    // --- helpers that THROW DukeException with specific messages ---

    private static void requireNonEmpty(String s, String messageIfEmpty) {
        if (s == null || s.isEmpty()) {
            throw new DukeException(messageIfEmpty);
        }
    }

    private static void ensureCapacity(int count, int capacity) {
        if (count >= capacity) {
            throw new DukeException("Your task list is full. Please remove some tasks before adding more.");
        }
    }

    private static int parseIndexOrThrow(String s, int currentCount, String cmdName) {
        String trimmed = (s == null ? "" : s.trim());
        if (trimmed.isEmpty()) {
            throw new DukeException("You must specify a task number to " + cmdName + ".");
        }
        try {
            int idx = Integer.parseInt(trimmed);
            if (idx < 1 || idx > currentCount) {
                throw new DukeException("Invalid task number: " + trimmed + ". It should be between 1 and " + currentCount + ".");
            }
            return idx;
        } catch (NumberFormatException e) {
            throw new DukeException("Task number must be an integer for '" + cmdName + "'.");
        }
    }
}