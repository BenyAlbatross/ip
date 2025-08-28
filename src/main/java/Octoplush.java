import java.util.Scanner;

public class Octoplush {
    private static final String SEP = "    ____________________________________________________________";
    private static final String IND = "     ";

    // Task class
    private static class Task {
        private final String description;
        private boolean done;

        Task(String description) {
            this.description = description;
            this.done = false;
        }

        void mark() {
            this.done = true;
        }
        void unmark() {
            this.done = false;
        }

        @Override
        public String toString() {
            return "[" + (done ? "X" : " ") + "] " + description;
        }
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
                    System.out.println(IND + (i + 1) + ". " + tasks[i]);
                }
                System.out.println(SEP);
            } else if (line.startsWith("mark ")) {
                Integer idx = parseIndex(line.substring(5));
                if (idx == null || idx < 1 || idx > count) {
                    // simple guard; spec doesn't require error text
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
            } else if (!line.isEmpty()) {
                // Treat any other non-empty line as a new task
                if (count < tasks.length) {
                    tasks[count++] = new Task(line);
                    System.out.println(SEP);
                    System.out.println(IND + "added: " + line);
                    System.out.println(SEP);
                } else {
                    System.out.println(SEP);
                    System.out.println(IND + "Task list is full");
                    System.out.println(SEP);
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

    private static Integer parseIndex(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}