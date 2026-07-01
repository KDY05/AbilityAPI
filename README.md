# AbilityAPI

An ability & skill management library for Minecraft Paper/Spigot plugins.

> This repository contains only the public API. The core implementation plugin is available for purchase on [Discord Server](https://discord.gg/G3uRK54KtT).

## Platform

| Requirement | Version |
|-------------|---------|
| Spigot / Paper | 1.16.5 or later |
| Java | 8 or later |

The API JAR is `compileOnly` — the implementation plugin must be installed on the server at runtime.

## Import

### Gradle (Kotlin DSL)

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    compileOnly("io.github.kdy05:ability-api:0.4.0")
}
```

### Gradle (Groovy DSL)

```groovy
repositories {
    mavenCentral()
}

dependencies {
    compileOnly 'io.github.kdy05:ability-api:0.4.0'
}
```

### Maven

```xml
<dependency>
    <groupId>io.github.kdy05</groupId>
    <artifactId>ability-api</artifactId>
    <version>0.4.0</version>
    <scope>provided</scope>
</dependency>
```

## Documentation

- [Writing Ability Packs](docs/writing-ability-packs.md) — how to create abilities and register them as a pack
- [Game Plugin Integration](docs/game-plugin-integration.md) — how to register abilities and control distribution from a game plugin
