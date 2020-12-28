<h1 align="center">
  <br>
  <img src="https://raw.githubusercontent.com/Montaime/discord/assets/assets/logo_circle_background_white.png" alt="Montaime Records logo" width="256">
  <br>
</h1>

<h4 align="center">Source code of the Montaime Discord music bot</h4>

<p align="center">
  <a href="https://montai.me/"><img alt="website" src="https://img.shields.io/badge/Website-montai.me-black?logo=safari&logoColor=white&style=flat-square"/></a>
  <a href="https://soundcloud.com/montaime"><img alt="soundcloud" src="https://img.shields.io/badge/SoundCloud-Montaime-FF6600?logo=soundcloud&logoColor=white&style=flat-square"/></a>
  <a href="https://open.spotify.com/user/56bxplytbqnkkuzfagb24gbch?si=jSdXN2doSTaMqc6aUIThbA"><img alt="spotify" src="https://img.shields.io/badge/Spotify-Montaime-1DB954?logo=spotify&logoColor=white&style=flat-square"/></a>
  <br>
  <a href="https://github.com/Montaime/discord/actions?query=event%3Apush+branch%3Amain+workflow%3A%22Java+CI+with+Gradle%22"><img alt="ci" src="https://img.shields.io/github/workflow/status/Montaime/discord/Java%20CI%20with%20Gradle?event=push&label=CI&logo=github&logoColor=white&style=flat-square"/></a>
  <a href="https://github.com/Montaime/discord/blob/main/LICENSE"><img alt="license" src="https://img.shields.io/github/license/Montaime/discord?color=lightgray&label=License&logo=apache&style=flat-square"/></a>
</p>

<br>

## Installation

> **Note:** This guide assumes that you are on a Unix-like operating system and that you have already created your bot account on the [Discord developer portal](https://discord.com/developers/applications).

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

<br>

## Contributing

If you want to help with the development of the project you are more than welcome to do so. When contributing, make sure to base your branch off of the `development` branch and create your Pull Request into that same branch
Pull Requests into the `main` branch will be rejected.

You can find the full [contributing guidelines](https://github.com/Montaime/discord/blob/main/.github/CONTRIBUTING.md).

<br>

## License

> **Note:** Click here to read the [entire license](https://github.com/Montaime/discord/blob/main/LICENSE).

```
Copyright 2020 Robin Mercier, Alexandru Galetus

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
