plugins {
    kotlin("jvm")
    id("com.gradleup.shadow") version "9.4.1"
}

dependencies {
    compileOnly(project(":"))
    compileOnly("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation(project(":sample-pack"))
}

kotlin {
    jvmToolchain(8)
}

tasks {
    build {
        dependsOn(shadowJar)
    }
    shadowJar {
        archiveClassifier.set("")
    }
}