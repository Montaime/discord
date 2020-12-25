package me.montai.discord.bot.core;

import lombok.Getter;

@Getter
public abstract class Command {

    protected String keyword = null;
    protected String[] aliases = new String[0];
    protected String description = "no description available";

    protected abstract void execute(CommandEvent event);

    final void run(CommandEvent event) {
        try {
            execute(event);
        } catch(Throwable t) {
            throw t instanceof RuntimeException ? (RuntimeException) t : new RuntimeException(t);
        }
    }
}
