package me.montai.discord.bot.listeners;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * This StatusListener class contains methods listening to the bot status.
 */
public final class StatusListener extends ListenerAdapter {

    /* Methods */

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        event.getJDA()
            .getPresence()
            .setPresence(
                OnlineStatus.ONLINE,
                Activity.listening("!sc help")
            );
    }
}
