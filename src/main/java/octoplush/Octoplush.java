package octoplush;

import octoplush.command.Command;
import octoplush.task.Task;
import java.util.ArrayList;

/**
 * Main class for the Octoplush assistant.
 * Handles initialization, loading tasks from storage, and running the main command loop.
 */
public class Octoplush {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Creates an Octoplush instance with the specified file path for task storage.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Octoplush(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (OctoplushException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main command loop of the application.
     * Continuously reads user commands, parses them, executes them, and displays results
     * until the user exits the application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (OctoplushException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Entry point for the Octoplush application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Octoplush("data/octoplush.txt").run();
    }
}