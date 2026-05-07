plugins {
    kotlin("jvm") version "2.3.21" apply false
}

subprojects {
    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}
