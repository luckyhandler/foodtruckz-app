plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    namespace = "de.handler.foodtruckz.library.core"
}

dependencies {
    api("androidx.core:core-ktx:1.7.0")
    api("androidx.appcompat:appcompat:1.4.1")
    api("com.google.android.material:material:1.5.0")
    api("io.coil-kt:coil-compose:1.3.1")
    api("androidx.compose.ui:ui:${rootProject.extra["compose_version"]}")
    api("androidx.compose.material:material:${rootProject.extra["compose_version"]}")
    api("androidx.compose.ui:ui-tooling:${rootProject.extra["compose_version"]}")
    api("androidx.compose.runtime:runtime-livedata:${rootProject.extra["compose_version"]}")
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    api("androidx.activity:activity-compose:1.4.0")
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:converter-moshi:2.9.0")
    api("com.squareup.okhttp3:logging-interceptor:4.9.1")
    api("com.jakewharton.timber:timber:4.7.1")
    api("androidx.core:core-ktx:1.7.0")
    api("androidx.appcompat:appcompat:1.4.1")
    api("com.google.android.material:material:1.5.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}