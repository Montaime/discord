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

package me.montai.discord.bot;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.montai.discord.bot.commands.HelpCommand;
import me.montai.discord.bot.commands.PingCommand;
import me.montai.discord.bot.commands.ScreamCommand;
import me.montai.discord.bot.library.commands.CommandManager;
import me.montai.discord.bot.listeners.StatusListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;

/**
 * This Main class has the entrypoint of the program.
 * <p>The Gradle task 'bot:run' will call the {@link Main#main(String[])} method with the command-line arguments.
 * <p>
 * <p>It is in this Main class that the JDA object is created and the bot is put online.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Main {

    private static JDA jda;

    /* Getters & Setters */

    @NotNull
    public static JDA getJda() {
        return jda;
    }

    /* Methods */

    public static void main(String[] args) {
        try {
            final JDABuilder jdaBuilder = JDABuilder.createDefault(Config.TOKEN)
                .setAutoReconnect(true)
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setActivity(Activity.playing("starting up..."));

            jdaBuilder.addEventListeners(
                new CommandManager()
                    .registerCommand(new HelpCommand())
                    .registerCommand(new PingCommand())
                    .registerCommand(new ScreamCommand()),
                new StatusListener()
            );

            jda = jdaBuilder.build().awaitReady();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
