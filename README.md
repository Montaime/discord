# Discord

### Setup

Go inside the `discord/bot/` directory and copy the `gradle.properties.sample` file.

Remove the `.sample` extension and edit its content:

```properties
# Discord Bot Token
discordToken=replace_this_with_your_bot_token
             ^ replace this string with your bot token
```

> Create & grab your bot token from the **developer portal** at https://discord.com/developers/applications

### Running

At the root of the project, under the `discord/` directory, run the command:

```shell
sh gradlew clean bot:run
```