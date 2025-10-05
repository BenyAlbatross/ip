# Octoplush User Guide

Octoplush is a simple command-line task manager that helps you keep track of your todos, deadlines, and events. It saves your tasks automatically so you never lose track of what needs to be done.

*Inspired by the invertible octopus plushie.*

<img src="TheOctoplush.jpeg" width="300">

## Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
  - [Adding a todo: `todo`](#adding-a-todo-todo)
  - [Adding a deadline: `deadline`](#adding-a-deadline-deadline)
  - [Adding an event: `event`](#adding-an-event-event)
  - [Listing all tasks: `list`](#viewing-all-tasks-list)
  - [Marking tasks: `mark`](#marking-a-task-as-done-mark)
  - [Unmarking tasks: `unmark`](#marking-a-task-as-not-done-unmark)
  - [Deleting tasks: `delete`](#deleting-a-task-delete)
  - [Finding tasks: `find`](#finding-tasks-find)
  - [Exiting the program: `bye`](#exiting-the-program-bye)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick Start

1. Ensure you have Java 17 or above installed on your computer.
2. Download the latest `octoplush.jar` from the releases page.
3. Open a command terminal and navigate to the folder containing the jar file.
4. Run the application with: `java -jar octoplush.jar`
5. Type commands and press Enter to execute them.
6. Refer to the Features section below for details on available commands.

## Features

### Viewing all tasks: `list`

Shows all tasks in your task list.

**Format:** `list`

**Example:**
```
list
```

**Expected output:**
```
    ____________________________________________________________
     Here are the tasks in your list:
     1.[T][ ] bake cake
     2.[D][X] bake cake (by: Dec 25 2025, 6:00pm)
     3.[E][ ] CS2113 project meeting (from: Dec 20 2025, 2:00pm to: Dec 20 2025, 4:00pm)
    ____________________________________________________________
```

---

### Adding a todo: `todo`

Adds a simple todo task to your list.

**Format:** `todo DESCRIPTION`

**Example:**
```
todo bake cake
```

**Expected output:**
```
    ____________________________________________________________
     Got it. I've added this task:
       [T][ ] bake cake
     Now you have 1 tasks in the list.
    ____________________________________________________________
```

---

### Adding a deadline: `deadline`

Adds a task with a deadline to your list.

**Format:** `deadline DESCRIPTION /by DEADLINE`

- `DEADLINE` can be in the following formats:
  - `yyyy-MM-dd HHmm` (e.g., `2025-12-25 1800`)
  - `yyyy-MM-dd` (defaults to 11:59pm, e.g., `2025-12-25`)
  - `MM-dd` (assumes current year, defaults to 11:59pm, e.g., `12-25`)

**Example:**
```
deadline bake cake /by 2025-12-25 1800
```

**Expected output:**
```
    ____________________________________________________________
     Got it. I've added this task:
       [D][ ] bake cake (by: Dec 25 2025, 6:00pm)
     Now you have 2 tasks in the list.
    ____________________________________________________________
```

---

### Adding an event: `event`

Adds an event with a start and end time to your list.

**Format:** `event DESCRIPTION /from START /to END`

- `START` and `END` can be in the following formats:
  - `yyyy-MM-dd HHmm` (e.g., `2025-12-20 1400`)
  - `yyyy-MM-dd` (defaults to 11:59pm, e.g., `2025-12-20`)
  - `MM-dd` (assumes current year, defaults to 11:59pm, e.g., `12-20`)

**Example:**
```
event CS2113 project meeting /from 2025-12-20 1400 /to 2025-12-20 1600
```

**Expected output:**
```
    ____________________________________________________________
     Got it. I've added this task:
       [E][ ] CS2113 project meeting (from: Dec 20 2025, 2:00pm to: Dec 20 2025, 4:00pm)
     Now you have 3 tasks in the list.
    ____________________________________________________________
```

---

### Marking a task as done: `mark`

Marks a task as completed.

**Format:** `mark INDEX`

- `INDEX` must be a positive integer (1, 2, 3, ...)

**Example:**
```
mark 2
```

**Expected output:**
```
    ____________________________________________________________
     Nice! I've marked this task as done:
       [D][X] bake cake (by: Dec 25 2025, 6:00pm)
    ____________________________________________________________
```

---

### Marking a task as not done: `unmark`

Marks a task as not completed.

**Format:** `unmark INDEX`

- `INDEX` must be a positive integer (1, 2, 3, ...)

**Example:**
```
unmark 2
```

**Expected output:**
```
    ____________________________________________________________
     OK, I've marked this task as not done yet:
       [D][ ] bake cake (by: Dec 25 2025, 6:00pm)
    ____________________________________________________________
```

---

### Finding tasks: `find`

Searches for tasks containing a specific keyword.

**Format:** `find KEYWORD`

**Example:**
```
find cake
```

**Expected output:**
```
    ____________________________________________________________
     Here are the matching tasks in your list:
     1.[T][ ] bake cake
     2.[D][X] bake cake (by: Dec 25 2025, 6:00pm)
    ____________________________________________________________
```

---

### Deleting a task: `delete`

Removes a task from your list.

**Format:** `delete INDEX`

- `INDEX` must be a positive integer (1, 2, 3, ...)

**Example:**
```
delete 3
```

**Expected output:**
```
    ____________________________________________________________
     Noted. I've removed this task:
       [E][ ] CS2113 project meeting (from: Dec 20 2025, 2:00pm to: Dec 20 2025, 4:00pm)
     Now you have 2 items in the list.
    ____________________________________________________________
```

---

### Exiting the program: `bye`

Exits the application.

**Format:** `bye`

**Example:**
```
bye
```

**Expected output:**
```
    ____________________________________________________________
     Bye. Hope to see you again soon!
    ____________________________________________________________
```

---

## FAQ

**Q: Where are my tasks stored?**
A: Tasks are automatically saved to `data/octoplush.txt` in the same directory as the jar file.

**Q: Can I edit the data file directly?**
A: Yes, but be careful! The file uses a specific format (`TAG | DONE | DESCRIPTION | EXTRA`). Incorrect formatting may cause tasks to be skipped when loading.

**Q: What happens if I type a command incorrectly?**
A: Octoplush will show an error message with suggestions on the correct format.

**Q: Do I need to manually save my tasks?**
A: No, tasks are automatically saved after every add, delete, mark, or unmark operation.

**Q: What date formats are supported?**
A: Octoplush supports flexible date input formats:
- Full format: `yyyy-MM-dd HHmm` (e.g., `2025-12-25 1800`)
- Date only: `yyyy-MM-dd` (defaults to 11:59pm, e.g., `2025-12-25`)
- Short format: `MM-dd` (assumes current year, defaults to 11:59pm, e.g., `12-25`)

Dates are displayed in a friendly format like "Dec 25 2025, 6:00pm".

---

## Command Summary

| Command | Format | Example |
|---------|--------|---------|
| List | `list` | `list` |
| Todo | `todo DESCRIPTION` | `todo read book` |
| Deadline | `deadline DESCRIPTION /by DEADLINE` | `deadline return book /by 2025-12-25 1800` |
| Event | `event DESCRIPTION /from START /to END` | `event meeting /from 2025-12-20 1400 /to 2025-12-20 1600` |
| Mark | `mark INDEX` | `mark 1` |
| Unmark | `unmark INDEX` | `unmark 1` |
| Find | `find KEYWORD` | `find book` |
| Delete | `delete INDEX` | `delete 2` |
| Exit | `bye` | `bye` |