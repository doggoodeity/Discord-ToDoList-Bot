import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;   // List to store tasks
    private static final String TASKS_FILE_PATH = "tasks.json"; // File path for storing tasks
    private ObjectMapper objectMapper;

    public TaskManager() {
        this.tasks = new ArrayList<>(); // Initialize an empty ArrayList when TaskManager is created
        this.objectMapper = new ObjectMapper(); // Pretty print JSON output
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        loadTasks(); // Load tasks from file when TaskManager is created
    }

    private void loadTasks() {
        try {
            File tasksFile = new File(TASKS_FILE_PATH);
            if (tasksFile.exists() && tasksFile.length() > 0) {
                // Read tasks from the JSON file
                this.tasks = objectMapper.readValue(tasksFile, new TypeReference<List<Task>>() {
                });
                if (!this.tasks.isEmpty()) {
                    int maxId = 0;
                    for (Task task : this.tasks) {
                        if (task.getId() > maxId) {
                            maxId = task.getId();
                        }
                    }
                    Task.setNextId(maxId + 1);
                }
            } else {
                System.out.println("No tasks file found or file is empty. Starting with an empty task list.");
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks from file: " + e.getMessage());
            // e.printStackTrace(); // Uncomment for detailed error trace
        }
    }

    private void saveTasks() {
        try {
            // Write tasks to the JSON file
            objectMapper.writeValue(new File(TASKS_FILE_PATH), this.tasks);
        } catch (IOException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
            // e.printStackTrace(); // Uncomment for detailed error trace
        }
    }
    // Method to add a task based on description
    public void addTask(String description) {
        if (description == null || description.trim().isEmpty()) {
            System.out.println("Error: Task description cannot be null or empty.");
            return; // Early return if description is invalid
        }
        Task newTask = new Task(description); // Create a new Task object
        this.tasks.add(newTask);              // Add the new task to the list
        System.out.println("Task added: " + newTask); // Printing for debugging purposes
    }

    // Method to get All tasks
    // Returns a copy of the task list to prevent external modification
    public List<Task> getAllTasks() {
        return new ArrayList<>(this.tasks); // Return a copy of the tasks list
    }

    // Method to find a task by ID
    public Task findTaskById(int id) {
        for (Task task : this.tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    // Method to mark a task as done
    public boolean markTaskAsDone(int id) {
        Task tasktoMark = findTaskById(id);
        if (tasktoMark != null) {
            tasktoMark.setStatus(Task.TaskStatus.DONE);
            System.out.println("Task marked as done: " + tasktoMark);
            return true; // Return true if task was found and marked as done
        }
        System.out.println("Error: Task with ID " + id + " not found.");
        return false;
    }

    // Method to remove a task by ID
    public boolean removeTask(int id) {
        Task taskToDelete = findTaskById(id);
        if (taskToDelete != null) {
            this.tasks.remove(taskToDelete);
            System.out.println("Task removed: " + taskToDelete);
            return true;
        }
        System.out.println("Error: Task with ID " + id + " not found.");
        return false; // Return false if task was not found
    }
}
