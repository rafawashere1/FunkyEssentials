plugins {
    id("java-library")
    id("xyz.jpenilla.run-paper") version "3.0.2"
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.2.build.123-stable")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(25)
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(25)
    }

    processResources {
        val props = mapOf(
            "version" to project.version.toString(),
            "description" to project.description
        )

        inputs.properties(props)
        filteringCharset = "UTF-8"

        filesMatching("plugin.yml") {
            expand(props)
        }
    }

    runServer {
        minecraftVersion("26.2")
        jvmArgs("-Xms2G", "-Xmx2G", "-Dcom.mojang.eula.agree=true")
    }
}
