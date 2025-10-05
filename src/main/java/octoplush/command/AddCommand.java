package octoplush.command;

import octoplush.Storage;
import octoplush.TaskList;
import octoplush.Ui;
import octoplush.OctoplushException;
import octoplush.task.Task;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OctoplushException {
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
