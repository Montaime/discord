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


// things to do:
// regional emoji don't exist for numbers, so we'll likely need a map of 2 strings to access per each value 0-9 inclusive
// concactenating strings doesnt quite work in the same way as c++ does. look into stringbuilder()
package me.montai.discord.bot.commands;

import me.montai.discord.bot.library.commands.Command;
import me.montai.discord.bot.library.commands.CommandEvent;

import java.util.HashMap;

public class TextToRegEmojiCommand extends Command {
    public TextToRegEmojiCommand() {
        // command keyword & description
        this.keyword = "regconv";
        this.description = "Converts text to regional indicator " +
                ":regional_indicator_e::regional_indicator_m::regional_indicator_o:" +
                ":regional_indicator_j::regional_indicator_i: (still in dev, " +
                "Crashes when symbols such as :, ! or ? is used)";
    }

    @Override
    protected void execute(CommandEvent event) {
        // msgArray string array takes in arguments of the command
        String[] msgArray = event.getArgs();

        // joins all arguments taken in as one string, using space as a delimiter
        String joined = String.join(" ", event.getArgs());

        // creating a hashmap for numbers, basic punctuation
        // (as both do not follow regional indicator emoji naming schemes)
        HashMap<String, String> numPunctIds = new HashMap<>();

        // naively inserting 1-9, 0 and ! ? punctuation. unfortunately there is no emoji that looks like a period (.)
        numPunctIds.put("1", ":one:");
        numPunctIds.put("2", ":two:");
        numPunctIds.put("3", ":three:");
        numPunctIds.put("4", ":four:");
        numPunctIds.put("5", ":five:");
        numPunctIds.put("6", ":six:");
        numPunctIds.put("7", ":seven:");
        numPunctIds.put("8", ":eight:");
        numPunctIds.put("9", ":nine:");
        numPunctIds.put("0", ":zero:");
        numPunctIds.put("!", ":exclamation:");
        numPunctIds.put("?", ":question:");

        // if argument length is less than 1 or the length of the joined string = 0, theres nothing there to convert
        if(msgArray.length < 1 || joined.length() == 0){
            throw new IllegalArgumentException("Invalid amount of parameters. Syntax: ``!mt regconv <text>``");
        }

/*        if (msgArray.length == 0 || joined.length() == 0){
            event.reply("__**Invalid syntax!**__ regconv must be used as follows:  ``!mt regconv <text>``");
        }*/

        // otherwise, go through with the conversion and send back reply
        else {
            joined = joined.toLowerCase();
            String reply = regConvert(event, numPunctIds, joined);
            event.reply(reply);
        }
    }

    public String regConvert(CommandEvent event, HashMap<String, String> numIds, String arg){
        // newReply determines the next regional indicator emoji to contactenate
        String newReply = null;
        // helps us build the final string
        StringBuilder result = new StringBuilder();

        // looping through the length of the string
        for (int i = 0; i < arg.length(); i++) {
            String indChar = Character.toString(arg.charAt(i));
            // if its a letter, newReply is equal to its regional indicator emoji id
            if (Character.isLetter(arg.charAt(i))) {
                newReply = ":regional_indicator_" + arg.charAt(i) + ":";
            }
            // if a whitespace is found, replace with an ideographic space
            if (Character.isWhitespace(arg.charAt(i))) {
                newReply = "\u3000"; // inserts an ideographic space to the appended string instead of mashing space like 5 times
            }
            // if the character is a key of num_punct_ids, then newReply is its value.
            if (numIds.containsKey(indChar)) {
                newReply = numIds.get(indChar);
            }
            // appending to result
            result.append(newReply);
        }
        // returning result as a casted string
        return result.toString();
    }
}