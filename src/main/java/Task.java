public class Task {
    private int id;
    private String description;
    private TaskStatus status; // Task status can be PENDING, DONE

    private static int nextId = 1; // Static variable to automatically assign ascending IDs

    public Task() {
    }

    public enum TaskStatus {
        PENDING("Not yet completed"),
        DONE("Done");

        private final String displayName;

        TaskStatus(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    public Task(String description) {
        this.id = nextId++;               // Assign ID and increment for next task
        this.description = description;
        this.status = TaskStatus.PENDING; // Default status is PENDING
    }

    // Getters and setters
    public int getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public TaskStatus getStatus() {
        return status;
    }
    public static int getNextId() {
        return nextId;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public static void setNextId(int id) {
        nextId = id;
    }

    // Override toString method for easy display of task information
    @Override
    public String toString() {
        return "ID: " + id + " - [" + status + "] " + description;
    }
}
