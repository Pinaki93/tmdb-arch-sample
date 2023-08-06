import com.android.build.api.dsl.ApplicationDefaultConfig
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "dev.pinaki.tmdbarchsample"
    compileSdk = 33

    defaultConfig {
        applicationId = "dev.pinaki.tmdbarchsample"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        // Load secrets from secrets.properties located in the root project directory
        loadProperties(file("../secrets.properties"))
        loadProperties(file("../defaults.properties"))

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.7.0-rc01")

    // add retrofit, moshi, okhttp, coroutines, hilt,dependencies here
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    implementation("com.google.dagger:hilt-android:2.44.2")
    implementation("androidx.navigation:navigation-runtime-ktx:2.6.0")
    kapt("com.google.dagger:hilt-android-compiler:2.44.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")
    // coroutines android
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    // ktx extensions
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")



    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

fun ApplicationDefaultConfig.loadProperties(secretsFile: File) {
    val secretsProperties = Properties()
    secretsProperties.load(secretsFile.reader())

    val defaultConfigValues = mutableMapOf<String, String>()

    secretsProperties.forEach { key, value ->
        defaultConfigValues[key.toString()] = value.toString()
    }

    defaultConfigValues.forEach { (key, value) ->
        buildConfigField("String", key, "\"$value\"")
    }
}