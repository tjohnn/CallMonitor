
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlinx-serialization")
    id("com.google.dagger.hilt.android")
}
apply {
    plugin("kotlin-android")
}

android {
    defaultConfig {
        applicationId = "com.tjohnn.callmonitor"
        minSdk = Config.Versions.minSdk
        compileSdk = Config.Versions.compileSdk
        targetSdk = Config.Versions.targetSdk
        versionCode = Config.Versions.versionCode
        versionName = Config.Versions.versionName
        testInstrumentationRunner = Config.Dependencies.Test.testInstrumentationRunner
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    @Suppress("UnstableApiUsage")
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        named("debug") {
            isMinifyEnabled = false
        }
        named("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    packagingOptions {
        jniLibs {
            excludes.add("META-INF/*")
        }
        resources {
            excludes.add("META-INF/*")
        }
    }
}

dependencies {
    implementation(Config.Dependencies.Kotlin.standardLibrary)
    implementation(Config.Dependencies.Ui.appCompat)
    implementation(Config.Dependencies.Ui.materialDesign)
    implementation(Config.Dependencies.Ui.constraintLayout)
    implementation(Config.Dependencies.Ui.recyclerView)
    implementation(Config.Dependencies.Ui.coreKtx)
    implementation(Config.Dependencies.Ui.navigationUi)
    implementation(Config.Dependencies.Ui.navigationFragment)
    implementation(Config.Dependencies.Ui.fragment)

    implementation(Config.Dependencies.Network.ktorServer)
    implementation(Config.Dependencies.Network.ktorServerCioEngine)
    implementation(Config.Dependencies.Network.ktorContentNegotiation)
    implementation(Config.Dependencies.Network.ktorStatusPages)
    implementation(Config.Dependencies.Network.kotlinSerialization)

    implementation(Config.Dependencies.Coroutine.core)
    implementation(Config.Dependencies.Coroutine.android)

    implementation(Config.Dependencies.Di.hilt)
    kapt(Config.Dependencies.Di.hiltCompiler)

    implementation(Config.Dependencies.Logging.timber)

    testImplementation(Config.Dependencies.Test.junit)
    testImplementation(Config.Dependencies.Test.mockito)
    testImplementation(Config.Dependencies.Test.mockitoKotlin)
    testImplementation(Config.Dependencies.Test.navigationTesting)
    testImplementation(Config.Dependencies.Test.coroutineTest)

    androidTestImplementation(Config.Dependencies.Test.espresso)
    androidTestImplementation(Config.Dependencies.Test.androidxTestExtJunit)
    androidTestImplementation(Config.Dependencies.Test.androidXTestCore)
    androidTestImplementation(Config.Dependencies.Test.espressoCore)
    androidTestImplementation(Config.Dependencies.Test.espressoIdlingResource)
    androidTestImplementation(Config.Dependencies.Test.espressoIdlingConcurrent)
    androidTestImplementation(Config.Dependencies.Test.espressoContrib)
    androidTestImplementation(Config.Dependencies.Test.espressoIntent)
    androidTestImplementation(Config.Dependencies.Test.androidXTestRunner)
    androidTestImplementation(Config.Dependencies.Test.androidXTextRules)
    androidTestImplementation(Config.Dependencies.Test.androidXTestOrchestrator)
}
