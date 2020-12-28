<h1 align="center">
  <br>
  <img src="https://raw.githubusercontent.com/Montaime/discord/assets/assets/logo_circle_background_white.png?token=ADH5G6O3C44ZFBJR5AXUDL275EPYQ" alt="Montaime Records logo" width="256">
  <br>
</h1>

<h4 align="center">Source code of the Montaime Discord music bot</h4>

## Installation

> **Note:** This guide assumes that you are on a Unix-like operating system and that you have already created your bot account on [Discord developer portal](https://discord.com/developers/applications).

### Setup

1. Clone the `Montaime/discord` repository

```shell
git clone https://github.com/Montaime/discord.git
```

2. Move to the `bot/` directory in your local repository

```shell
cd discord/bot
```

3. Copy the `gradle.properties.sample` file and rename it to `gradle.properties`

```shell
cp gradle.properties.sample gradle.properties
```

4. Open the newly created `gradle.properties` and change the value of the `discordToken` variable to your Discord bot token

```properties
# Discord Bot Token
discordToken=replace_this_with_your_bot_token
             ^ replace this string with your Discord bot token
```

### Running

1. Move at the root of the local repository

2. Run the following Gradle tasks

```shell
sh gradlew clean bot:run
```
