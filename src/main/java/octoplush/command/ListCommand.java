package octoplush.command;

import octoplush.Storage;
import octoplush.TaskList;
import octoplush.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
