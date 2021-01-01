plugins {
    val kotlinVersion = "1.4.20"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    id("net.mamoe.mirai-console") version "2.0-M2-1-dev-1"
}

group = "org.photo"
version = "1.0.0"

repositories {
    mavenLocal()
    jcenter()
    mavenCentral()
}