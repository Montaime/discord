package me.montai.discord.bot.commands;

import me.montai.discord.bot.core.Command;
import me.montai.discord.bot.core.CommandEvent;

import java.time.temporal.ChronoUnit;

public final class PingCommand extends Command {

    /* Constructors */

    public PingCommand() {
        this.keyword = "ping";
        this.description = "Gives the bot latency in milliseconds";
    }

    /* Methods */

    @Override
    protected void execute(CommandEvent event) {
        event.reply("Ping: ...", m -> {
            final long ping = event.getMessage().getTimeCreated().until(m.getTimeCreated(), ChronoUnit.MILLIS);
            m.editMessage(String.format("Ping: %dms", ping)).queue();
        });
    }
}
