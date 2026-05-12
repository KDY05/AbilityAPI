import com.vanniktech.maven.publish.SonatypeHost

plugins {
    kotlin("jvm") version "2.3.21"
    id("com.vanniktech.maven.publish") version "0.29.0"
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

kotlin {
    jvmToolchain(8)
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    if (project.hasProperty("signing.keyId")) {
        signAllPublications()
    }

    coordinates(group.toString(), "ability-api", version.toString())

    pom {
        name = "AbilityAPI"
        description = "Minecraft Paper plugin API for ability and skill management"
        url = "https://github.com/kdy05/AbilityAPI"

        licenses {
            license {
                name = "MIT License"
                url = "https://opensource.org/licenses/MIT"
            }
        }

        developers {
            developer {
                id = "kdy05"
                name = "kdy05"
            }
        }

        scm {
            connection = "scm:git:git://github.com/kdy05/AbilityAPI.git"
            developerConnection = "scm:git:ssh://github.com/kdy05/AbilityAPI.git"
            url = "https://github.com/kdy05/AbilityAPI"
        }
    }
}

subprojects {
    repositories {
        mavenCentral()
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
}
