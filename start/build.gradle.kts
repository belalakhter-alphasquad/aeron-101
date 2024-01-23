plugins {
    application
}

application {
    mainClass.set("start.App")
}


tasks.named<Jar>("jar") {
    manifest {
        attributes(
            "Main-Class" to "start.App",
            "Add-Opens" to "java.base/sun.nio.ch",
            "Implementation-Title" to "aeron-101 start",
            "Implementation-Version" to project.version
        )
    }
    archiveBaseName.set("start")
    archiveVersion.set("1.0.0")
    from(sourceSets.main.get().output)

    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}



repositories {
    mavenCentral()
}

dependencies {
 implementation("io.aeron:aeron-all:1.43.0")
 implementation("org.agrona:agrona:1.20.0")
}