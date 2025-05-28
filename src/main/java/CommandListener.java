import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        // 1. Ignore messages from other bots including the bot itself
        if (event.getAuthor().isBot()) return;

        // 2. Get the message content
        Message message = event.getMessage();
        String content = message.getContentRaw(); // Get the raw content of the message

        // 3. Handle the !ping command
        if (content.equals("nigga")) {
            MessageChannel channel = event.getChannel(); // Get the channel where the message was sent
            channel.sendMessage("stfu").queue(); // Send the response "Pong!" to the same channel
                                                       // .queue() is used to send the message asynchronously
        }
    }

}
