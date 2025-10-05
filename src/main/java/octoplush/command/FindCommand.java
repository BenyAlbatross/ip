package octoplush.command;

import octoplush.Storage;
import octoplush.TaskList;
import octoplush.Ui;
import octoplush.task.Task;

import java.util.ArrayList;

/**
 * Command to find tasks containing a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a command to find tasks containing a keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        ui.showFoundTasks(matchingTasks);
    }
}
