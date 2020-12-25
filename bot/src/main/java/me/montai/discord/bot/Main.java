package me.montai.discord.bot;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.montai.discord.bot.commands.HelpCommand;
import me.montai.discord.bot.commands.PingCommand;
import me.montai.discord.bot.core.CommandManager;
import me.montai.discord.bot.listeners.StatusListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;

/**
 * This Main class has the entrypoint of the program.
 * The Gradle task 'bot:run' will call the {@link Main#main(String[])} method with the command-line arguments.
 *
 * It is in this Main class that the JDA object is created and the bot is put online.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Main {

    private static JDA jda;

    /* Getters & Setters */

    @NotNull
    public static JDA getJda() {
        return jda;
    }

    /* Methods */

    public static void main(String[] args) {
        try {
            final JDABuilder jdaBuilder = JDABuilder.createDefault(Config.TOKEN)
                .setAutoReconnect(true)
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setActivity(Activity.playing("starting up..."));

            jdaBuilder.addEventListeners(
                new CommandManager()
                    .registerCommand(new HelpCommand())
                    .registerCommand(new PingCommand()),
                new StatusListener()
            );

            jda = jdaBuilder.build().awaitReady();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
