buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Config.Dependencies.Plugins.gradle)
        classpath(Config.Dependencies.Plugins.kotlinGradle)
        classpath(Config.Dependencies.Plugins.daggerHilt)
        classpath(Config.Dependencies.Plugins.kotlinSerialization)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
