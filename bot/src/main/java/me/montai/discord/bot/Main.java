package me.montai.discord.bot;

import lombok.Getter;
import me.montai.discord.bot.commands.HelpCommand;
import me.montai.discord.bot.commands.PingCommand;
import me.montai.discord.bot.core.CommandManager;
import me.montai.discord.bot.listeners.StatusListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

@Getter
public final class Main {

    private static JDA jda;

    /* Methods */

    public static void main(String[] args) {
        try {
            final JDABuilder jdaBuilder = JDABuilder.createDefault(Config.TOKEN)
                .setAutoReconnect(true)
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setActivity(Activity.playing("starting up..."));

            jdaBuilder.addEventListeners(
                new CommandManager()
                    .addCommand(new HelpCommand())
                    .addCommand(new PingCommand()),
                new StatusListener()
            );

            jda = jdaBuilder.build().awaitReady();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
