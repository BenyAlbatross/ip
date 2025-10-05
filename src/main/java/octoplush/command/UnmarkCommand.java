package octoplush.command;

import octoplush.Storage;
import octoplush.TaskList;
import octoplush.Ui;
import octoplush.OctoplushException;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OctoplushException {
        tasks.unmarkTask(index);
        ui.showTaskUnmarked(tasks.get(index));
        storage.save(tasks.getTasks());
    }
}
