package me.montai.discord.bot.commands;

import me.montai.discord.bot.core.Command;
import me.montai.discord.bot.core.CommandEvent;

public class HelpCommand extends Command {

    public HelpCommand() {
        this.keyword = "help";
        this.aliases = new String[] { "h", "?" };
        this.description = "Sends the list of commands";
    }

    @Override
    protected void execute(CommandEvent event) {
        final StringBuilder sb = new StringBuilder();

        event.getManager().getCommands()
            .stream()
            .sorted((cmd1, cmd2) -> cmd1.getKeyword().compareToIgnoreCase(cmd2.getKeyword()))
            .forEach(cmd ->
                sb.append("**")
                .append(cmd.getKeyword())
                .append("**: ")
                .append(cmd.getDescription())
                .append('\n')
            );
        event.reply(sb.toString());
    }
}
