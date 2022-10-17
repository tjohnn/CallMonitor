
object Config {

    object Versions {
        const val minSdk = 23
        const val targetSdk = 32
        const val compileSdk = 32
        const val versionName = "1.0"
        const val versionCode: Int = 1

        const val kotlin = "1.7.10"

        const val junit = "4.13.2"
        const val mockito = "4.0.0"
        const val mockitoKotlin = "2.2.0"

        const val dagger = "2.44"

        const val ktor = "2.1.2"

        const val timber = "5.0.1"

        const val androidxAppCompat = "1.3.1"
        const val androidxRecyclerView = "1.2.1"
        const val materialDesign = "1.6.1"

        const val androidNavigation = "2.5.2"
        const val androidxFragment = "1.4.0"
        const val ktx = "1.0.2"
        const val constraintLayout = "2.1.0"

        const val androidxTestExtJunit = "1.1.3"
        const val espresso = "3.4.0"
        const val androidxTest = "1.4.0"

        const val androidGradlePlugin = "7.1.3"

        const val coroutines = "1.6.4"
    }

    object Dependencies {

        object Kotlin {
            const val standardLibrary = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        }

        object Test {
            const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            const val junit = "junit:junit:${Versions.junit}"
            const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
            const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
            const val androidxTestExtJunit = "androidx.test.ext:junit:${Versions.androidxTestExtJunit}"
            const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
            const val navigationTesting = "androidx.navigation:navigation-testing:${Versions.androidNavigation}"
            const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
            val androidXTestCore = "androidx.test:core:${Versions.androidxTest}"
            val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
            val espressoIdlingResource = "androidx.test.espresso:espresso-idling-resource:${Versions.espresso}"
            val espressoIdlingConcurrent = "androidx.test.espresso.idling:idling-concurrent:${Versions.espresso}"
            val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
            val espressoIntent = "androidx.test.espresso:espresso-intents:${Versions.espresso}"
            val androidXTestRunner = "androidx.test:runner:${Versions.androidxTest}"
            val androidXTextRules = "androidx.test:rules:${Versions.androidxTest}"
            val androidXTestOrchestrator = "androidx.test:orchestrator:${Versions.androidxTest}"
        }

        object Ui {
            const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.androidxRecyclerView}"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
            const val coreKtx = "androidx.core:core-ktx:${Versions.ktx}"
            const val appCompat = "androidx.appcompat:appcompat:${Versions.androidxAppCompat}"
            const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
            const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.androidNavigation}"
            const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.androidNavigation}"
            const val fragment = "androidx.fragment:fragment-ktx:${Versions.androidxFragment}"
        }

        object Di {
            const val hilt = "com.google.dagger:hilt-android:${Versions.dagger}"
            const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.dagger}"
        }

        object Network {
            const val ktorServer = "io.ktor:ktor-server-core:${Versions.ktor}"
            const val ktorServerCioEngine = "io.ktor:ktor-server-cio:${Versions.ktor}"
            const val ktorContentNegotiation = "io.ktor:ktor-server-content-negotiation:${Versions.ktor}"
            const val ktorStatusPages = "io.ktor:ktor-server-status-pages:${Versions.ktor}"
            const val kotlinSerialization = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
        }

        object Logging {
            const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
        }

        object Coroutine {
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        }

        object Plugins {
            const val gradle = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
            const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
            const val daggerHilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.dagger}"
            const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
        }
    }
}
