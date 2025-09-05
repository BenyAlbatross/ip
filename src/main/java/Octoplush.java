import java.util.Scanner;

public class Octoplush {
    private static final String SEP = "    ____________________________________________________________";
    private static final String IND = "     ";

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

            if (line.equals("bye")) {
                System.out.println(SEP);
                System.out.println(IND + "Bye. Hope to see you again soon!");
                System.out.println(SEP);
                break;

            } else if (line.equals("list")) {
                System.out.println(SEP);
                System.out.println(IND + "Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    // NOTE: no space after the dot, to match sample output
                    System.out.println(IND + (i + 1) + "." + tasks[i]);
                }
                System.out.println(SEP);

            } else if (line.startsWith("mark ")) {
                Integer idx = parseIndex(line.substring(5));
                if (idx == null || idx < 1 || idx > count) {
                    System.out.println(SEP);
                    System.out.println(IND + "Invalid task number");
                    System.out.println(SEP);
                } else {
                    tasks[idx - 1].mark();
                    System.out.println(SEP);
                    System.out.println(IND + "Nice! I've marked this task as done:");
                    System.out.println(IND + "  " + tasks[idx - 1]);
                    System.out.println(SEP);
                }

            } else if (line.startsWith("unmark ")) {
                Integer idx = parseIndex(line.substring(7));
                if (idx == null || idx < 1 || idx > count) {
                    System.out.println(SEP);
                    System.out.println(IND + "Invalid task number");
                    System.out.println(SEP);
                } else {
                    tasks[idx - 1].unmark();
                    System.out.println(SEP);
                    System.out.println(IND + "OK, I've marked this task as not done yet:");
                    System.out.println(IND + "  " + tasks[idx - 1]);
                    System.out.println(SEP);
                }

            } else if (line.startsWith("todo ")) {
                String desc = line.substring(5).trim();
                if (desc.isEmpty()) {
                    printSimpleError("Description of a todo cannot be empty");
                    continue;
                }
                if (count < tasks.length) {
                    tasks[count++] = new Todo(desc);
                    printAdded(tasks[count - 1], count);
                } else {
                    printSimpleError("Task list is full");
                }

            } else if (line.startsWith("deadline ")) {
                // Format: deadline <desc> /by <when>
                String rest = line.substring(9);
                int byIdx = rest.indexOf("/by ");
                if (byIdx < 0) {
                    printSimpleError("Invalid deadline format. Use: deadline <desc> /by <when>");
                    continue;
                }
                String desc = rest.substring(0, byIdx).trim();
                String by = rest.substring(byIdx + 4).trim();
                if (desc.isEmpty() || by.isEmpty()) {
                    printSimpleError("Invalid deadline format. Use: deadline <desc> /by <when>");
                    continue;
                }
                if (count < tasks.length) {
                    tasks[count++] = new Deadline(desc, by);
                    printAdded(tasks[count - 1], count);
                } else {
                    printSimpleError("Task list is full");
                }

            } else if (line.startsWith("event ")) {
                // Format: event <desc> /from <start> /to <end>
                String rest = line.substring(6);
                int fromIdx = rest.indexOf("/from ");
                int toIdx = rest.indexOf("/to ");
                if (fromIdx < 0 || toIdx < 0 || toIdx <= fromIdx) {
                    printSimpleError("Invalid event format. Use: event <desc> /from <start> /to <end>");
                    continue;
                }
                String desc = rest.substring(0, fromIdx).trim();
                String from = rest.substring(fromIdx + 6, toIdx).trim();
                String to = rest.substring(toIdx + 4).trim();
                if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
                    printSimpleError("Invalid event format. Use: event <desc> /from <start> /to <end>");
                    continue;
                }
                if (count < tasks.length) {
                    tasks[count++] = new Event(desc, from, to);
                    printAdded(tasks[count - 1], count);
                } else {
                    printSimpleError("Task list is full");
                }

            } else if (!line.isEmpty()) {
                // Fallback: keep previous behavior for unspecified commands (simple add)
                if (count < tasks.length) {
                    tasks[count++] = new Todo(line); // treat as a simple ToDo
                    printAdded(tasks[count - 1], count);
                } else {
                    printSimpleError("Task list is full");
                }
            } else {
                // empty input -> print empty block to match style
                System.out.println(SEP);
                System.out.println(IND);
                System.out.println(SEP);
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

    private static void printSimpleError(String msg) {
        System.out.println(SEP);
        System.out.println(IND + msg);
        System.out.println(SEP);
    }

    private static Integer parseIndex(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}