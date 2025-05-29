# ToDoListBot for Discord

A simple and effective Discord bot designed to help users manage their daily tasks and to-do lists directly within Discord. This bot is built using Java and the JDA (Java Discord API) library.

## Features

Currently, ToDoListBot supports the following commands:

* `!ping` - Checks if the bot is online and responsive.
* `!addtask <description>` - Adds a new task to your to-do list.
    * Example: `!addtask Buy groceries`
* `!listtasks` - Displays all current tasks.
* `!donetask <task_ID>` - Marks a specific task as completed.
    * Example: `!donetask 1`
* `!removetask <task_ID>` - Removes a specific task from the list. (Alias: `!deletetask <task_ID>`)
    * Example: `!removetask 1`
* `!help` - Shows this help message with all available commands.

## Technologies Used

* **Java:** Core programming language (e.g., JDK 11 or higher recommended).
* **JDA (Java Discord API):** Library for interacting with the Discord API.
* **Jackson:** Library for serializing/deserializing Java objects to/from JSON (for data persistence).
* **Maven:** Build automation and dependency management tool.

## Prerequisites

* Java Development Kit (JDK) - Version 11 or newer is recommended.
* Apache Maven (usually bundled with IDEs like IntelliJ IDEA).
* A Discord Account and a Server where you have permissions to add bots.

## Setup and Configuration

1.  **Clone the Repository:**
    ```bash
    git clone [https://github.com/doggoodeity/Discord-ToDoList-Bot.git]
    cd Discord-ToDoList-Bot
    ```

2.  **Configure Bot Token:**
    * Navigate to `src/main/java/MainBot.java`.
    * Find the line:
        ```java
        String botToken = "YOUR_DISCORD_BOT_TOKEN_HERE"; // Or your current token
        ```
    * Replace `"YOUR_DISCORD_BOT_TOKEN_HERE"` (or your current token if it's still there) with your **actual Discord Bot Token**.
    * **IMPORTANT SECURITY NOTE:** It is strongly recommended **NOT** to commit your actual bot token directly into the code if your repository is public. For local development and testing, this direct replacement is okay. For a more secure approach, especially for deployment or public repositories, consider using environment variables or a configuration file that is excluded from Git (e.g., added to `.gitignore`).

3.  **Discord Developer Portal Setup:**
    * Go to the [Discord Developer Portal](https://discord.com/developers/applications).
    * Select your bot application.
    * Navigate to the "Bot" page.
    * Ensure the following **Privileged Gateway Intents** are **ENABLED**:
        * `SERVER MEMBERS INTENT`
        * `MESSAGE CONTENT INTENT`
    * Click "Save Changes" if prompted.

4.  **Build the Project (Optional - IntelliJ usually handles this):**
    If you need to build manually using Maven:
    ```bash
    mvn clean package
    ```

5.  **Run the Bot:**
    * **Using IntelliJ IDEA (Recommended):** Open the project. IntelliJ should automatically detect it as a Maven project and download dependencies. Then, simply run the `main` method in the `MainBot.java` file.
    * **Using Command Line (after building with `mvn clean package`):**
        ```bash
        java -jar target/ToDoListBot-1.0-SNAPSHOT.jar
        ```
        (The JAR file name might vary based on your `pom.xml` configuration for `artifactId` and `version`.)

## Inviting the Bot to Your Server

1.  Go to your bot's application page on the [Discord Developer Portal](https://discord.com/developers/applications).
2.  Navigate to "OAuth2" -> "URL Generator".
3.  Under "SCOPES", select `bot`.
4.  Under "BOT PERMISSIONS", select the necessary permissions. For ToDoListBot, common permissions would be:
    * `Read Messages/View Channels`
    * `Send Messages`
    * `Embed Links` (if you plan to use embeds for nicer messages)
    * `Read Message History`
5.  Copy the generated URL at the bottom of the page.
6.  Paste the URL into your web browser, select the server you want to add the bot to, and click "Authorize".

## Usage

Once the bot is configured, running, and invited to your server, you can interact with it using the commands listed in the [Features](#features) section (e.g., `!addtask Read a book`).

## Future Enhancements (To-Do)

* [ ] User-specific task lists.
* [ ] Task reminders/due dates.
* [ ] Edit existing task descriptions.
* [ ] Option to use Discord Embeds for richer message formatting.
* [ ] More robust error handling and command parsing.

---
