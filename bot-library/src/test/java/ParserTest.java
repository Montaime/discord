import me.montai.discord.bot.library.utils.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void testIsNull() {
        final String[] args = Parser.split(null);

        Assertions.assertArrayEquals(args, new String[0]);
    }

    @Test
    public void testIsEmpty() {
        final String[] args = Parser.split("");

        Assertions.assertArrayEquals(args, new String[0]);
    }

    @Test
    public void testSimpleString() {
        final String[] args = Parser.split("word1 word2 word3");

        Assertions.assertArrayEquals(args, new String[] { "word1", "word2", "word3" });
    }

    @Test
    public void testAdvancedString() {
        final String[] args = Parser.split("word1 \"word2 word3\"");

        Assertions.assertArrayEquals(args, new String[] { "word1", "word2 word3" });
    }
}
