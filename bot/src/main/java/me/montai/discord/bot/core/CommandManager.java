package me.montai.discord.bot.core;

import me.montai.discord.bot.utils.Parser;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class CommandManager extends ListenerAdapter {

    private static final String PREFIX = "!sc";

    private final List<Command> commands;
    private final Map<String, Integer> commandIndices;

    /* Constructors */

    public CommandManager() {
        this.commands = new LinkedList<>();
        this.commandIndices = new HashMap<>();
    }

    /* Getters & Setters */

    @NotNull
    public List<Command> getCommands() {
        return commands;
    }

    /* Methods */

    @NotNull
    public CommandManager addCommand(Command command) {
        final int index = commands.size();

        synchronized (commandIndices) {
            final String keyword = command.getKeyword().toLowerCase();

            if (commandIndices.containsKey(keyword)) {
                throw new IllegalArgumentException(String.format("The command keyword '%s' is already in use!", keyword));
            }
            for (String alias : command.getAliases()) {
                if (commandIndices.containsKey(alias.toLowerCase())) {
                    throw new IllegalArgumentException(String.format("The command alias '%s' is already in use!", alias));
                }
            }

            commandIndices.put(keyword, index);
            for (String alias : command.getAliases()) {
                commandIndices.put(alias.toLowerCase(), index);
            }
        }
        commands.add(index, command);
        return this;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        final String raw = event.getMessage().getContentRaw();
        String content = null;

        if (raw.startsWith("<@" + event.getJDA().getSelfUser().getId() + ">")
        || raw.startsWith("<@!" + event.getJDA().getSelfUser().getId() + ">")) {
            content = raw.substring(raw.indexOf(">") + 1);
        } else if (raw.toLowerCase().startsWith(PREFIX.toLowerCase())) {
            content = raw.substring(PREFIX.length());
        }

        if (content == null) {
            return;
        }

        final String[] parts = Parser.split(content);
        final String keyword = parts.length == 0 ? null : parts[0];
        final String[] args = Arrays.copyOfRange(parts, 1, parts.length);

        if (keyword == null) {
            return;
        }

        final Command command;
        synchronized (commandIndices) {
            final int i = commandIndices.getOrDefault(keyword.toLowerCase(), -1);
            command = i != -1 ? commands.get(i) : null;
        }

        if (command != null) {
            final CommandEvent commandEvent = new CommandEvent(event, args, this);

            command.run(commandEvent);
        }
    }
}
