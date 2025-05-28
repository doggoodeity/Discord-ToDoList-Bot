import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import javax.security.auth.login.LoginException; // Will be replaced in JDA 5.0.0

public class MainBot {
    public static void main(String[] args) {
        String botToken = "YOUR_BOT_TOKEN";

        if (botToken.equals("YOUR_BOT_TOKEN")) {
            System.out.println("Please replace 'YOUR_BOT_TOKEN' with your actual bot token.");
            return;
        }

        try {
            JDABuilder builder = JDABuilder.createDefault(botToken);

            // Enable necessary intents for the bot
            builder.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS);

            // Set the bot's activity
            builder.setActivity(Activity.playing("your mom xdd"));

            // Register the command listener
            builder.addEventListeners(new CommandListener());

            // Build bot instance
            builder.build();

            System.out.println("Bot is now running!");

        } catch (Exception e) { // JDA 5.0.0 won't require LoginException
            e.printStackTrace();
            System.out.println("Failed to start the bot. Please check your token and internet connection.");
        }
    }
}
