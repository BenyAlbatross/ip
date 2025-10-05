package octoplush;

import octoplush.task.Task;

import java.util.ArrayList;

/**
 * Manages a list of tasks with operations to add, delete, mark, and retrieve tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list initialized with the given tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list at the specified index.
     *
     * @param index The 0-based index of the task to delete.
     * @return The deleted task.
     * @throws OctoplushException If the index is invalid.
     */
    public Task delete(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new OctoplushException("Invalid task number: " + (index + 1));
        }
        return tasks.remove(index);
    }

    /**
     * Gets a task from the list at the specified index.
     *
     * @param index The 0-based index of the task to retrieve.
     * @return The task at the specified index.
     * @throws OctoplushException If the index is invalid.
     */
    public Task get(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new OctoplushException("Invalid task number: " + (index + 1));
        }
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets the underlying ArrayList of tasks.
     *
     * @return The ArrayList containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The 0-based index of the task to mark.
     * @throws OctoplushException If the index is invalid.
     */
    public void markTask(int index) {
        get(index).mark();
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index The 0-based index of the task to unmark.
     * @throws OctoplushException If the index is invalid.
     */
    public void unmarkTask(int index) {
        get(index).unmark();
    }
}
