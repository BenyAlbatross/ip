package octoplush;

import octoplush.task.Deadline;
import octoplush.task.Task;
import octoplush.task.Todo;
import octoplush.task.Event;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private final Path filePath;
    private static final DateTimeFormatter STORAGE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Creates a new Storage instance with the specified file path.
     *
     * @param filePath The path to the file for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return ArrayList of tasks loaded from the file.
     * @throws OctoplushException If there is an error reading the file.
     */
    public ArrayList<Task> load() throws OctoplushException {
        ArrayList<Task> tasks = new ArrayList<>();

        if (!Files.exists(filePath)) {
            return tasks; // First run: nothing to load
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = parseTaskFromFile(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new OctoplushException("Could not load tasks: " + e.getMessage());
        }

        return tasks;
    }

    private Task parseTaskFromFile(String line) {
        String[] parts = line.split("\\|");

        // Trim all parts
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }

        if (parts.length < 3) {
            return null; // Corrupted line
        }

        char tag = parts[0].isEmpty() ? '?' : parts[0].charAt(0);
        boolean done = "1".equals(parts[1]);
        String desc = parts[2];

        Task task = null;
        switch (tag) {
        case 'T':
            task = new Todo(desc);
            break;
        case 'D':
            if (parts.length >= 4) {
                LocalDateTime by = LocalDateTime.parse(parts[3], STORAGE_FORMAT);
                task = new Deadline(desc, by);
            }
            break;
        case 'E':
            if (parts.length >= 5) {
                LocalDateTime from = LocalDateTime.parse(parts[3], STORAGE_FORMAT);
                LocalDateTime to = LocalDateTime.parse(parts[4], STORAGE_FORMAT);
                task = new Event(desc, from, to);
            }
            break;
        default:
            return null; // Unknown type
        }

        if (task != null && done) {
            task.mark();
        }

        return task;
    }

    /**
     * Saves tasks to the storage file.
     *
     * @param tasks The list of tasks to save.
     * @throws OctoplushException If there is an error writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws OctoplushException {
        try {
            Files.createDirectories(filePath.getParent());
            try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filePath.toFile())))) {
                for (Task task : tasks) {
                    pw.println(formatTaskForFile(task));
                }
            }
        } catch (IOException e) {
            throw new OctoplushException("Could not save tasks: " + e.getMessage());
        }
    }

    private String formatTaskForFile(Task task) {
        char tag = task.tag();
        String doneFlag = task.isDone() ? "1" : "0";

        if (task instanceof Todo) {
            return tag + " | " + doneFlag + " | " + task.getDescription();
        } else if (task instanceof Deadline d) {
            return tag + " | " + doneFlag + " | " + d.getDescription() + " | " + d.getByString();
        } else if (task instanceof Event e) {
            return tag + " | " + doneFlag + " | " + e.getDescription() + " | " + e.getFromString() + " | " + e.getToString();
        }

        return "";
    }
}