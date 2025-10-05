package octoplush.command;

import octoplush.TaskList;
import octoplush.Ui;
import octoplush.Storage;
import octoplush.OctoplushException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws OctoplushException;

    public boolean isExit() {
        return false;
    }
}
