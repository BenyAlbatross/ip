package octoplush.command;

import octoplush.Storage;
import octoplush.TaskList;
import octoplush.Ui;
import octoplush.OctoplushException;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OctoplushException {
        tasks.markTask(index);
        ui.showTaskMarked(tasks.get(index));
        storage.save(tasks.getTasks());
    }
}
