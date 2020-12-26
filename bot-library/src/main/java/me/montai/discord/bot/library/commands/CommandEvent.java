package me.montai.discord.bot.library.commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.PermissionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * This CommandEvent class represents a command issued by a user.
 */
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

    /**
     * Get the original JDA MessageReceivedEvent.
     *
     * @return The MessageReceivedEvent.
     */
    @NotNull
    public MessageReceivedEvent getEvent() {
        return event;
    }

    /**
     * Get the arguments of the command.
     *
     * @return The arguments.
     */
    @NotNull
    public String[] getArgs() {
        return args;
    }

    /**
     * Get the CommandManager the command was registered in.
     */
    @NotNull
    public CommandManager getManager() {
        return manager;
    }

    /**
     * Get the JDA object instance.
     *
     * @return The JDA object.
     */
    @NotNull
    public JDA getJDA() {
        return event.getJDA();
    }

    /**
     * Get the bot SelfUser object.
     *
     * @return The SelfUser.
     */
    @NotNull
    public SelfUser getSelfUser() {
        return event.getJDA().getSelfUser();
    }

    /**
     * Get the bot as a Member.
     *
     * @return The Member, or null if the message wasn't sent in a guild.
     */
    @Nullable
    public Member getSelfMember() {
        try {
            return event.getGuild().getSelfMember();
        } catch (IllegalStateException ignored) {}
        return null;
    }

    /**
     * Get the User who sent the message.
     *
     * @return The User.
     */
    @NotNull
    public User getAuthor() {
        return event.getAuthor();
    }

    /**
     * Get the message author as a Member of the guild.
     *
     * @return The Member, or null if the message wasn't sent in a guild.
     */
    @Nullable
    public Member getMember() {
        return event.getMember();
    }

    /**
     * Get the MessageChannel the message was sent in.
     *
     * @return The MessageChannel.
     */
    @NotNull
    public MessageChannel getChannel() {
        return event.getChannel();
    }

    /**
     * Get the ChannelType of the channel the message was sent in.
     *
     * @return The ChannelType.
     */
    @NotNull
    public ChannelType getChannelType() {
        return event.getChannelType();
    }

    /**
     * Get the Guild the message was sent in.
     *
     * @return The Guild, or null if the message wasn't sent in a guild.
     */
    @Nullable
    public Guild getGuild() {
        try {
            return event.getGuild();
        } catch (IllegalStateException ignored) {}
        return null;
    }

    /**
     * Get the command Message.
     *
     * @return The Message.
     */
    @NotNull
    public Message getMessage() {
        return event.getMessage();
    }

    /* Methods */

    /**
     * Reply to the command message with a new one.
     *
     * @param message The content of the message.
     */
    public void reply(String message) {
        reply(message, null, null);
    }

    /**
     * Reply to the command message with a new one.
     *
     * @param message The content of the message.
     * @param success The action to execute after the Message is sent.
     */
    public void reply(String message, Consumer<Message> success) {
        reply(message, success, null);
    }

    /**
     * Reply to the command message with a new one.
     *
     * @param message The content of the message.
     * @param success The action to execute after the Message is sent.
     * @param failure The action to execute if an error occurs when sending the Message.
     */
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

    /**
     * Add a reaction to the command Message.
     *
     * @param reaction The reaction to add.
     */
    public void react(String reaction) {
        if (reaction == null || reaction.isEmpty()) {
            return;
        }
        try {
            event.getMessage().addReaction(reaction.replaceAll("<a?:(.+):(\\d+)>", "$1:$2")).queue();
        } catch(PermissionException ignored) {}
    }
}
