plugins {
    kotlin("jvm")
    id("com.gradleup.shadow") version "9.4.1"
}

dependencies {
    compileOnly(project(":"))
    compileOnly("io.papermc.paper:paper-api:26.1.2.build.+")
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation(project(":sample-pack"))
}

kotlin {
    jvmToolchain(25)
}

tasks {
    build {
        dependsOn(shadowJar)
    }
    shadowJar {
        archiveClassifier.set("")
    }
}