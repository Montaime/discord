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
