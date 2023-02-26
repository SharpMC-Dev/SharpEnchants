repositories {

    maven("https://repo.aikar.co/content/groups/aikar/")

}


dependencies {
    implementation(project(":ruins-api"))
    implementation(project(":ruins-common"))

    implementation("co.aikar:acf-paper:0.5.1-SNAPSHOT")


}

tasks {
    build {
        dependsOn(shadowJar)
    }

    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        mergeServiceFiles()

        relocate("co.aikar.acf", "org.sharpmc.runes.acf")
    }
}