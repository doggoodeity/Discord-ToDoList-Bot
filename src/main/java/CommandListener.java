import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class CommandListener extends ListenerAdapter {

    private final TaskManager taskManager; // Variable to hold the TaskManager instance

    // Constructor to receive TaskManager instance
    public CommandListener(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        Message message = event.getMessage();
        String content = message.getContentRaw();
        MessageChannel channel = event.getChannel();

        if (content.equals("!ping")) {
            channel.sendMessage("Heartbeat spotted!").queue();
        }

        // !addtask <description> command
        else if (content.startsWith("!addtask")) {
            // Get the description after the command
            String description = content.substring("!addtask".length()).trim();

            if (description.isEmpty()) {
                channel.sendMessage("Please enter a description for the task. For example: `!addtask Do errands`").queue();
            } else {
                this.taskManager.addTask(description);
                channel.sendMessage("Task added: \"" + description + "\"").queue();
            }
        }

        // !listtasks command
        else if (content.equals("!listtasks")) {
            List<Task> allTasks = this.taskManager.getAllTasks();
            if (allTasks.isEmpty()) {
                channel.sendMessage("No tasks available for now.").queue();
            } else {
                StringBuilder response = new StringBuilder("Your task list:\n");
                for (Task task : allTasks) {
                    // Using toString method of Task class for better formatting
                    response.append("- ").append(task.toString()).append("\n");
                }
                channel.sendMessage(response.toString()).queue();
            }
        }

        // !donetask command
        else if (content.startsWith("!donetask ")) {
            String idString = content.substring("!donetask ".length()).trim();
            try {
                int taskId = Integer.parseInt(idString); // Convert the ID String to an integer

                Task taskToMark = this.taskManager.findTaskById(taskId); // Find the task first
                if (taskToMark == null) {
                    channel.sendMessage("Error: Task with ID " + taskId + " is not found.").queue();
                } else if (taskToMark.getStatus() == Task.TaskStatus.DONE) {
                    channel.sendMessage("Task with ID " + taskId + " is already marked as done.").queue();
                }
                else {
                    boolean success = this.taskManager.markTaskAsDone(taskId); // Mark the task as done
                    if (success) {
                        channel.sendMessage("Task with ID " + taskId + " has been marked as done.").queue();
                    } else {
                        channel.sendMessage("Error: Could not mark task with ID " + taskId + " as done.").queue();
                    }
                }
            } catch (NumberFormatException e) {
                channel.sendMessage("Invalid task ID format. Please enter a valid number. For example: `!donetask 1`").queue();
            }
        }

        // !removetask and !deletetask command
        else if (content.startsWith("!removetask ") || content.startsWith("!deletetask ")) {
            String commandPrefix;
            if (content.startsWith("!removetask ")) {
                commandPrefix = "!removetask ";
            } else {
                commandPrefix = "!deletetask ";
            }

            String idString = content.substring(commandPrefix.length()).trim();
            try {
                int taskId = Integer.parseInt(idString);

                Task taskToDelete = this.taskManager.findTaskById(taskId); // Find the task before deleting
                if (taskToDelete == null) {
                    channel.sendMessage("Error: Task with ID " + taskId + " is not found.").queue();
                } else {
                    channel.sendMessage("Error: Cannot delete task with ID: " + taskId ).queue();
                }
            } catch (NumberFormatException e) {
                channel.sendMessage("Invalid task ID format. Please enter a valid number. For example: `!removetask 1`").queue();
            }
        }

        else if (content.equals("!help")) {
            StringBuilder helpMessage = new StringBuilder();
            helpMessage.append("**Welcome to doggodeity's ToDoList Bot!**\n");
            helpMessage.append("Here are commands that you can use:\n");
            helpMessage.append("```\n"); // Start the code markdown block
            helpMessage.append("!ping         - Check if the bot is working.\n");
            helpMessage.append("!addtask <description> - Add a new task.\n");
            helpMessage.append("  Example: !addtask Drink my daily cup of milk\n");
            helpMessage.append("!listtasks    - List all tasks that you currently have.\n");
            helpMessage.append("!donetask <ID>  - Mark a task as done.\n");
            helpMessage.append("  Example: !donetask 1\n");
            helpMessage.append("!removetask <ID> - Delete a task.\n");
            helpMessage.append("  (Or !deletetask <ID>)\n");
            helpMessage.append("  Example: !removetask 1\n");
            helpMessage.append("!help         - Show this help message.\n");
            helpMessage.append("```\n"); // End the code markdown block
            channel.sendMessage(helpMessage.toString()).queue();
        }
    }
}
