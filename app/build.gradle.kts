plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.example.aicameratranslator"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.aicameratranslator"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }

    // This block can often be removed when using Hilt, but it's safe to keep.
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    // --- AndroidX Core UI ---
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // --- Navigation ---
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // --- Lifecycle & ViewModel ---
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // --- Room (Offline Mode / History / Favorites) ---
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.fragment)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // --- CameraX (OCR) ---
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)

    // --- ML Kit OCR (Text Recognition) ---
    implementation(libs.google.mlkit.text.recognition)

    // --- Networking (API Translation) ---
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.gson)
    implementation(libs.squareup.okhttp)
    // FIX: Use the correct, direct alias for the logging interceptor
    implementation(libs.logging.interceptor)

    // --- Coroutines (Background Tasks) ---
    implementation(libs.kotlinx.coroutines.android)

    // --- Analytics & Crash Reporting ---
    implementation(libs.sentry.android)
    implementation(libs.appcenter.analytics)
    implementation(libs.appcenter.crashes)

    // --- Hilt (Dependency Injection) ---
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)


    // --- Testing ---
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
