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

package me.montai.discord.bot.commands;

import me.montai.discord.bot.library.commands.Command;
import me.montai.discord.bot.library.commands.CommandEvent;

/**
 * [HelpCommand]
 *
 * > !sc help
 *
 * This command sends in the channel the list of commands.
 *
 * todo:
 *  - send the list of commands to the user DMs
 */
public final class HelpCommand extends Command {

    /* Constructors */

    public HelpCommand() {
        this.keyword = "help";
        this.aliases = new String[] { "h", "?" };
        this.description = "Sends the list of commands";
    }

    /* Methods */

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
