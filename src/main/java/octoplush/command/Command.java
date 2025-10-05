package octoplush.command;

import octoplush.TaskList;
import octoplush.Ui;
import octoplush.Storage;
import octoplush.OctoplushException;

/**
 * Represents an executable command in the Octoplush application.
 * All specific command types inherit from this abstract class.
 */
public abstract class Command {
    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasks The task list to operate on.
     * @param ui The UI for displaying messages to the user.
     * @param storage The storage for persisting tasks.
     * @throws OctoplushException If an error occurs during command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws OctoplushException;

    /**
     * Checks if this command should terminate the application.
     *
     * @return true if the application should exit, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
