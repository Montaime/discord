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
 * [ScreamCommand]
 * <p>This command sends to the channel a screaming message.
 * <p>
 * <p>Usage:
 * <pre>
 * !mt scream
 * </pre>
 */
public final class ScreamCommand extends Command {

    /* Constructors */

    public ScreamCommand() {
        this.keyword = "scream";
        this.aliases = new String[]{"aaa"};
        this.description = "Literally screams at you.";
    }

    /* Methods */

    @Override
    protected void execute(CommandEvent event) {
        event.reply("**AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAH**");
    }
}

