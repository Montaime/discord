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

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

/**
 * This Command class represents a bot command.
 * Bot commands need to extend this class in order to be registered.
 */
public abstract class Command {

    /** The keyword triggering the command. */
    protected String keyword = "null";

    /** Aliases for the command. These will trigger the command as well. */
    protected String[] aliases = new String[0];

    /** The description of the command. This description is used in the help command. */
    protected String description = "no help description available";

    /* Getters & Setters */

    /**
     * Get the command keyword.
     *
     * @return The keyword.
     */
    @NotNull
    public String getKeyword() {
        return keyword;
    }

    /**
     * Get the command aliases.
     *
     * @return The aliases.
     */
    @NotNull
    public String[] getAliases() {
        return aliases;
    }

    /**
     * Get the command description.
     *
     * @return The description.
     */
    @NotNull
    public String getDescription() {
        return description;
    }

    /* Methods */

    /**
     * This method is where the command logic is to be written.
     *
     * @param event The CommandEvent sent by a user.
     */
    protected abstract void execute(CommandEvent event);

    /* Internal */

    /**
     * This methods serves as intermediate between the {@link CommandManager#onMessageReceived(MessageReceivedEvent)}
     * and the {@link Command#execute(CommandEvent)} methods.
     * This method calls the {@link Command#execute(CommandEvent)} one in a try-catch block in order to prevent the bot
     * from crashing due to an exception thrown by the logic inside the {@link Command#execute(CommandEvent)} method.
     *
     * @param event The CommandEvent to pass to the Command#execute method.
     */
    final void run(CommandEvent event) {
        try {
            execute(event);
        } catch(Throwable t) {
            event.reply(String.format("%s",
                    t.getMessage()
            ));
            throw t instanceof RuntimeException ? (RuntimeException) t : new RuntimeException(t);
        }
    }
}
