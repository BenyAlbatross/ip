package octoplush;

import octoplush.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new OctoplushException("Invalid task number: " + (index + 1));
        }
        return tasks.remove(index);
    }

    public Task get(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new OctoplushException("Invalid task number: " + (index + 1));
        }
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void markTask(int index) {
        get(index).mark();
    }

    public void unmarkTask(int index) {
        get(index).unmark();
    }
}
