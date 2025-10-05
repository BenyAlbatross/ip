package octoplush;

import octoplush.task.Task;

import java.util.Scanner;

public class Ui {
    private static final String SEP = "    ____________________________________________________________";
    private static final String IND = "     ";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        showLine();
        System.out.println(IND + "Hello! I'm Octoplush");
        System.out.println(IND + "What can I do for you?");
        showLine();
    }

    public void showLine() {
        System.out.println(SEP);
    }

    public void showError(String message) {
        System.out.println(IND + message);
    }

    public void showLoadingError() {
        showError("Could not load tasks from file. Starting with an empty task list.");
    }

    public String readCommand() {
        String line = scanner.nextLine().trim();
        if (line.isEmpty()) {
            System.out.println(IND);
            return readCommand(); // Recursive call to get next input
        }
        return line;
    }

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println(IND + "Got it. I've added this task:");
        System.out.println(IND + "  " + task);
        System.out.println(IND + "Now you have " + totalTasks + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int totalTasks) {
        System.out.println(IND + "Noted. I've removed this task:");
        System.out.println(IND + "  " + task);
        System.out.println(IND + "Now you have " + totalTasks + " items in the list.");
    }

    public void showTaskMarked(Task task) {
        System.out.println(IND + "Nice! I've marked this task as done:");
        System.out.println(IND + "  " + task);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println(IND + "OK, I've marked this task as not done yet:");
        System.out.println(IND + "  " + task);
    }

    public void showTaskList(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println(IND + "Your list is empty. Add tasks with: todo, deadline, or event.");
        } else {
            System.out.println(IND + "Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(IND + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    public void showGoodbye() {
        System.out.println(IND + "Bye. Hope to see you again soon!");
    }

    public void close() {
        scanner.close();
    }
}