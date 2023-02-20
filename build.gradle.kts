import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    `java-library`
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "com.vertmix.sharpmc"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

allprojects {
    apply(plugin = "java-library")
    apply(plugin = "com.github.johnrengelman.shadow")

    java {
        withSourcesJar()
        withJavadocJar()
    }

    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://repo.lucko.me/")

    }

    dependencies {
        compileOnly("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT")
        compileOnly("me.lucko:helper:5.6.13")
    }

    tasks {
        build {
            dependsOn(shadowJar)
        }

        named<ShadowJar>("shadowJar") {
            mergeServiceFiles()
        }
    }
}