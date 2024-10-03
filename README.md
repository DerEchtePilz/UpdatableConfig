# UpdatableConfig

UpdatableConfig is a tool written originally for the [CommandAPI](https://github.com/JorelAli/CommandAPI) which aims to eliminate the use of config version
fields as the indicator if a config has changed.

Its features include:

- **Adding config options.** If you already have an existing config and later you decide to add a new config option, this tool will handle the addition for you while leaving any other config option untouched.
- **Removing config options.** Similarly, if you have an existing config and later you want to remove a config option for some reason, this tool will handle the removal for you.
- **Comment operations.** You can set, modify and remove comments for specific config options.

*Comment support only works with underlying systems that support comments.*

## How it works

UpdatableConfig contains two interfaces that need to be implemented by the user, `DefaultConfig` and `ConfigurationAdapter`.

The `DefaultConfig` interface defines two methods that each return maps of `String`s to `CommentedConfigOption`s or `CommentedSection`s respectively. These methods are used
to determine the option set for the config when it is being generated or updated. This interface needs to be implemented to contain config options.

The `ConfigurationAdapter` is a generic interface that is used as a middle layer between the UpdatableConfig system and your program. Therefore it needs to be implemented in 
accordance to the JavaDocs of the interface itself and the JavaDocs of the methods within the interface. Exemplary implementations can be found [here using Bukkit's YamlConfiguration
class](#how-it-works) and [here using Configurate's `CommentedConfigurationNode`](#how-it-works).

## How to build it

> [!NOTE]
> This is intended to be a temporary step until I manage to put this on MavenCentral

Building this project can be done easily by running

```
./gradlew build
```

Optionally, you can also run `./gradlew clean build` to first remove any previous outputs.

You should then be able to find the generated jar in `build/libs`.

-----

However, as this is intended to be used as an external library, it is much more recommended to install it to your local Maven repository using 

```
./gradlew publishToMavenLocal
```

As mentioned before, you can also optionally run `./gradlew clean publishToMavenLocal` to first remove previous outputs.

## How to use it

> [!NOTE]
> This step requires to install this library to your local Maven repository.

### Gradle

Add the Gradle Shadow plugin to your build script as described here: https://gradleup.com/shadow/

Next, add UpdatableConfig to your project:

```kotlin
repositories {
    mavenLocal()
}

dependencies {
    implementation("io.github.derechtepilz:updatableconfig:1.0.0")
}
```

### Maven

Add the `maven-shade-plugin` to your `pom.xml` as described here: https://maven.apache.org/plugins/maven-shade-plugin/index.html

Next, add UpdatableConfig to your project:

```xml
<dependencies>
    <dependency>
        <groupId>io.github.derechtepilz</groupId>
        <artifactId>updatableconfig</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```