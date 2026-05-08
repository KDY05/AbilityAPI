import com.vanniktech.maven.publish.SonatypeHost

plugins {
    kotlin("jvm") version "2.3.21"
    id("com.vanniktech.maven.publish") version "0.29.0"
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.1.2.build.+")
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

kotlin {
    jvmToolchain(25)
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()

    coordinates(group.toString(), "ability-api", version.toString())

    pom {
        name = "AbilityAPI"
        description = "Minecraft Paper plugin API for ability and skill management"
        url = "https://github.com/kdy05/AbilityAPI"

        licenses {
            license {
                name = "GNU General Public License v3.0"
                url = "https://www.gnu.org/licenses/gpl-3.0.html"
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
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}
