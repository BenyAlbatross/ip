package octoplush.command;

import octoplush.Storage;
import octoplush.TaskList;
import octoplush.Ui;
import octoplush.OctoplushException;
import octoplush.task.Task;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OctoplushException {
        Task deletedTask = tasks.delete(index);
        ui.showTaskDeleted(deletedTask, tasks.size());
        storage.save(tasks.getTasks());
    }
}
