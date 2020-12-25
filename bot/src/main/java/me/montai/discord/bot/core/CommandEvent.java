package me.montai.discord.bot.core;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.PermissionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public final class CommandEvent {

    private final MessageReceivedEvent event;
    private final String[] args;
    private final CommandManager manager;

    /* Constructors */

    CommandEvent(MessageReceivedEvent event, String[] args, CommandManager manager) {
        this.event = event;
        this.args = args;
        this.manager = manager;
    }

    /* Getters & Setters */

    @NotNull
    public MessageReceivedEvent getEvent() {
        return event;
    }

    @NotNull
    public String[] getArgs() {
        return args;
    }

    @NotNull
    public CommandManager getManager() {
        return manager;
    }

    @NotNull
    public JDA getJDA() {
        return event.getJDA();
    }

    @NotNull
    public SelfUser getSelfUser() {
        return event.getJDA().getSelfUser();
    }

    @Nullable
    public Member getSelfMember() {
        try {
            return event.getGuild().getSelfMember();
        } catch (IllegalStateException ignored) {}
        return null;
    }

    @NotNull
    public User getAuthor() {
        return event.getAuthor();
    }

    @Nullable
    public Member getMember() {
        return event.getMember();
    }

    @NotNull
    public MessageChannel getChannel() {
        return event.getChannel();
    }

    @NotNull
    public ChannelType getChannelType() {
        return event.getChannelType();
    }

    @Nullable
    public Guild getGuild() {
        try {
            return event.getGuild();
        } catch (IllegalStateException ignored) {}
        return null;
    }

    @NotNull
    public Message getMessage() {
        return event.getMessage();
    }

    /* Methods */

    public void reply(String message) {
        reply(message, null, null);
    }

    public void reply(String message, Consumer<Message> success) {
        reply(message, success, null);
    }

    public void reply(String message, Consumer<Message> success, Consumer<Throwable> failure) {
        if (message == null || message.isEmpty()) {
            return;
        }
        try {
            getChannel()
                .sendMessage(message)
                .queue(
                    m -> {
                        if (success != null) {
                            success.accept(m);
                        }
                    },
                    t -> {
                        if (failure != null) {
                            failure.accept(t);
                        }
                    }
                );
        } catch(PermissionException ignored) {}
    }

    public void react(String reaction) {
        if (reaction == null || reaction.isEmpty()) {
            return;
        }
        try {
            event.getMessage().addReaction(reaction.replaceAll("<a?:(.+):(\\d+)>", "$1:$2")).queue();
        } catch(PermissionException ignored) {}
    }
}
