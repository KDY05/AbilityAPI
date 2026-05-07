plugins {
    kotlin("jvm")
}

dependencies {
    compileOnly(project(":ability-api"))
    compileOnly("io.papermc.paper:paper-api:26.1.2.build.+")
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

kotlin {
    jvmToolchain(25)
}
