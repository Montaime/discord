package me.montai.discord.bot;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This Config class contains static fields representing the different variables in the gradle.properties file.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Config {

    /** The Discord bot token from the gradle.properties file */
    public static final String TOKEN = "@token@";
}
