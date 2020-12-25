package me.montai.discord.bot.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This Parser class contains parsing utils.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Parser {

    /** The command argument Pattern object */
    private static final Pattern ARGUMENT_PATTERN = Pattern.compile("[\\S&&[^\"]]+|\"(?:\\\\\"|[^\"])+\"");

    /* Methods */

    /**
     * Split a given String into arguments.
     * Arguments are separated by whitespaces, but words enclosed in double quotes even separated by whitespaces will
     * only produce one argument.
     *
     * examples:
     * <code>
     * Parser.split("word1 word2 word3") -> { "word1", "word2", "word3" }
     * Parser.split("word1 \"word2 word3\"") -> { "word1", "word2 word3" }
     * </code>
     *
     * @param s The String to split.
     *
     * @return A String array containing all the arguments of the given {@code s} String.
     */
    @NotNull
    public static String[] split(final String s) {
        if (s == null || s.isEmpty()) {
            return new String[0];
        }

        final List<String> list = new LinkedList<>();
        final Matcher m = ARGUMENT_PATTERN.matcher(s);

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
