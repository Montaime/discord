/*
 * Copyright 2020 Robin Mercier, Alexandru Galetus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.montai.discord.bot.library.commands;

import me.montai.discord.bot.library.utils.Parser;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * This CommandManager class represents a command issued by a user.
 *
 * todo:
 *  - turn this class into a singleton
 */
public final class CommandManager extends ListenerAdapter {

    /** The bot command prefix */
    public static final String PREFIX = "!sc";

    private final List<Command> commands;
    private final Map<String, Integer> commandIndices;

    /* Constructors */

    public CommandManager() {
        this.commands = new LinkedList<>();
        this.commandIndices = new HashMap<>();
    }

    /* Getters & Setters */

    /**
     * Get the list of registered commands.
     *
     * @return The list of commands.
     */
    @NotNull
    public List<Command> getCommands() {
        return commands;
    }

    /* Methods */

    /**
     * Register a new command.
     *
     * @param command The command to be registered.
     *
     * @return The current instance of the CommandManager.
     */
    @NotNull
    public CommandManager registerCommand(Command command) {
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
