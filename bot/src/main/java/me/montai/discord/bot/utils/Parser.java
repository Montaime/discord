package me.montai.discord.bot.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Parser {

    private static final Pattern COMMAND_PATTERN = Pattern.compile("[\\S&&[^\"]]+|\"(?:\\\\\"|[^\"])+\"");

    /* Methods */

    @NotNull
    public static String[] split(final String s) {
        if (s == null || s.isEmpty()) {
            return new String[0];
        }

        final List<String> list = new LinkedList<>();
        final Matcher m = COMMAND_PATTERN.matcher(s);

        while (m.find()) {
            final String arg = m.group(0);

            if (arg.matches("\".*\"")) {
                list.add(arg.substring(1, arg.length() - 1));
            } else if (!arg.isEmpty() && !arg.matches("\\s+")) {
                list.add(arg);
            }
        }
        return list.toArray(new String[0]);
    }
}
