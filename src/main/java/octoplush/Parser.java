package octoplush;

import octoplush.command.Command;
import octoplush.command.AddCommand;
import octoplush.command.DeleteCommand;
import octoplush.command.ExitCommand;
import octoplush.command.ListCommand;
import octoplush.command.MarkCommand;
import octoplush.command.UnmarkCommand;
import octoplush.task.Deadline;
import octoplush.task.Todo;
import octoplush.task.Event;

public class Parser {

    public static Command parse(String fullCommand) throws OctoplushException {
        String trimmed = fullCommand.trim();

        if (trimmed.equals("bye")) {
            return new ExitCommand();
        } else if (trimmed.equals("list")) {
            return new ListCommand();
        } else if (trimmed.startsWith("mark ")) {
            int index = parseTaskIndex(trimmed.substring(5), "mark");
            return new MarkCommand(index);
        } else if (trimmed.startsWith("unmark ")) {
            int index = parseTaskIndex(trimmed.substring(7), "unmark");
            return new UnmarkCommand(index);
        } else if (trimmed.startsWith("delete ")) {
            int index = parseTaskIndex(trimmed.substring(7), "delete");
            return new DeleteCommand(index);
        } else if (trimmed.startsWith("todo")) {
            String desc = trimmed.substring(4).trim();
            requireNonEmpty(desc, "The description of a todo cannot be empty. Try: todo buy milk");
            return new AddCommand(new Todo(desc));
        } else if (trimmed.startsWith("deadline")) {
            return parseDeadlineCommand(trimmed);
        } else if (trimmed.startsWith("event")) {
            return parseEventCommand(trimmed);
        } else {
            throw new OctoplushException("Sorry, I don't recognise that command. Try: list, todo, deadline, event, mark, unmark, bye.");
        }
    }

    private static Command parseDeadlineCommand(String command) throws OctoplushException {
        String rest = (command.length() >= 8 ? command.substring(8) : "").trim();
        int byIdx = rest.indexOf("/by ");

        if (byIdx < 0) {
            throw new OctoplushException("Invalid deadline format. Use: deadline <desc> /by <when>");
        }

        String desc = rest.substring(0, byIdx).trim();
        String by = rest.substring(byIdx + 4).trim();

        requireNonEmpty(desc, "The description of a deadline cannot be empty.");
        requireNonEmpty(by, "The '/by' time for a deadline cannot be empty.");

        return new AddCommand(new Deadline(desc, by));
    }

    private static Command parseEventCommand(String command) throws OctoplushException {
        String rest = (command.length() >= 5 ? command.substring(5) : "").trim();
        int fromIdx = rest.indexOf("/from ");
        int toIdx = rest.indexOf("/to ");

        if (fromIdx < 0 || toIdx < 0 || toIdx <= fromIdx) {
            throw new OctoplushException("Invalid event format. Use: event <desc> /from <start> /to <end>");
        }

        String desc = rest.substring(0, fromIdx).trim();
        String from = rest.substring(fromIdx + 6, toIdx).trim();
        String to = rest.substring(toIdx + 4).trim();

        requireNonEmpty(desc, "The description of an event cannot be empty.");
        requireNonEmpty(from, "The '/from' time for an event cannot be empty.");
        requireNonEmpty(to, "The '/to' time for an event cannot be empty.");

        return new AddCommand(new Event(desc, from, to));
    }

    private static int parseTaskIndex(String indexStr, String cmdName) throws OctoplushException {
        String trimmed = (indexStr == null ? "" : indexStr.trim());

        if (trimmed.isEmpty()) {
            throw new OctoplushException("You must specify a task number to " + cmdName + ".");
        }

        try {
            int idx = Integer.parseInt(trimmed);
            if (idx < 1) {
                throw new OctoplushException("Task number must be positive.");
            }
            return idx - 1; // Convert to 0-based index
        } catch (NumberFormatException e) {
            throw new OctoplushException("Task number must be an integer for '" + cmdName + "'.");
        }
    }

    private static void requireNonEmpty(String s, String messageIfEmpty) throws OctoplushException {
        if (s == null || s.isEmpty()) {
            throw new OctoplushException(messageIfEmpty);
        }
    }
}