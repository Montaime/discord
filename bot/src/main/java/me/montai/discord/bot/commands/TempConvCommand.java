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

package me.montai.discord.bot.commands;
import me.montai.discord.bot.library.commands.Command;
import me.montai.discord.bot.library.commands.CommandEvent;

import java.text.DecimalFormat;
import java.util.Arrays;

public class TempConvCommand extends Command {

    public TempConvCommand() {
        // command keyword & description
        this.keyword = "tconv";
        this.description = "Converts temperature in F° and/or C° interchangeably.";
    }

    @Override
    protected void execute(CommandEvent event) {
        // msgArray string array takes in arguments of the command
        final String[] msgArray = event.getArgs();
        // identifiers for conversion (f to c, c to f)
        final String fToC = "fc";
        final String cToF = "cf";

        // if length of arguments is not equal to 2, throw illegalargumentexception
        if (msgArray.length != 2){
            throw new IllegalArgumentException("Invalid amount of parameters. Syntax: ``!mt tconv <fc/cf> <temp>``");
        }
        // check if the 1st arg is either "fc" or "cf"
        if(!Arrays.asList("fc", "cf").contains(msgArray[0].toLowerCase())){
            throw new IllegalArgumentException("**Invalid 1st argument!** First argument must either be \"fc\" or \"cf\".");
        }
        // check if 2nd arg is a number thats either positive or negative
        if(!msgArray[1].matches("-?[0-9]+")){
            throw new IllegalArgumentException("**Invalid 3rd arg!** Must be a positive or negative number.");
        }
        else{
            // initialize botResponse string
            String botResponse = "";

            // take in 2nd argument as a string, parse it as a double
            double temp = Double.parseDouble(msgArray[1]);

            // double used as the main conversion value carried over as the bots reply
            double conversion = 0;
            DecimalFormat twoDec = new DecimalFormat("#.00");

            // F° to C° conversion
            if (msgArray[0].equals("fc")) {
                // (5/9) must be cast as a double for accurate result
                conversion = (temp - 32) * ((double)5/9);
                botResponse = "Ok, I'll convert F° to C°! **Result: **";
            }
            // C° to F° conversion
            else if(msgArray[0].equals("cf")) {
                // (9/5) must be cast as a double for accurate result
                conversion = (temp * ((double)9/5)) + 32;
                botResponse = "Ok, I'll convert C° to F°! **Result: **";
            }
            else {
                // otherwise, syntax is somehow invalid (might remove due to !Arrays.asList check above)
                event.reply("Invalid syntax! ``!mt tconv <fc/cf> <temp>``");
            }
            // cast conversion value to a string, format to 2 decimal places using DecimalFormat
            String finalConversion = twoDec.format(conversion);
            // bot reply
            event.reply(botResponse + finalConversion);
        }
    }
}