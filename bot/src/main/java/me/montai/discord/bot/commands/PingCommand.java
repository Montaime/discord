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

import java.time.temporal.ChronoUnit;

/**
 * [PingCommand]
 * <p>This command sends to the channel the current ping latency of the bot.
 * <p>
 * <p>Usage:
 * <pre>
 * !mt ping
 * </pre>
 */
/*
 * todo:
 *  - restrict command access
 */
public final class PingCommand extends Command {

    /* Constructors */

    public PingCommand() {
        this.keyword = "ping";
        this.description = "Gives the bot latency in milliseconds.";
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
