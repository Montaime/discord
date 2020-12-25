package me.montai.discord.bot.core;

import org.jetbrains.annotations.NotNull;

public abstract class Command {

    protected String keyword = null;
    protected String[] aliases = new String[0];
    protected String description = "no description available";

    /* Getters & Setters */

    @NotNull
    public String getKeyword() {
        return keyword;
    }

    @NotNull
    public String[] getAliases() {
        return aliases;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    /* Methods */

    protected abstract void execute(CommandEvent event);

    /* Internal */

    final void run(CommandEvent event) {
        try {
            execute(event);
        } catch(Throwable t) {
            throw t instanceof RuntimeException ? (RuntimeException) t : new RuntimeException(t);
        }
    }
}
