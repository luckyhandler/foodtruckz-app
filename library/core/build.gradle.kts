plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 26
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
    }
    namespace = "de.handler.foodtruckz.library.core"
}

dependencies {
    api("io.coil-kt:coil-compose:${rootProject.extra["coil_version"]}")
    api("androidx.core:core-ktx:${rootProject.extra["core_ktx_version"]}")
    api("androidx.appcompat:appcompat:${rootProject.extra["appcompat_version"]}")
    api("androidx.compose.ui:ui:${rootProject.extra["compose_version"]}")
    api("androidx.compose.material:material:${rootProject.extra["compose_version"]}")
    api("androidx.compose.ui:ui-tooling:${rootProject.extra["compose_version"]}")
    api("androidx.compose.runtime:runtime-livedata:${rootProject.extra["compose_version"]}")
    api("androidx.compose.runtime:runtime:${rootProject.extra["compose_version"]}")
    api("androidx.lifecycle:lifecycle-runtime-ktx:${rootProject.extra["lifecycle_runtime_ktx_version"]}")
    api("androidx.activity:activity-compose:${rootProject.extra["activity_compose_version"]}")
    api("com.google.android.material:material:${rootProject.extra["material_version"]}")
    api("com.jakewharton.timber:timber:${rootProject.extra["timber_version"]}")
    api("com.google.android.gms:play-services-location:${rootProject.extra["play_services_version"]}")


    testImplementation("junit:junit:${rootProject.extra["junit_version"]}")
    androidTestImplementation("androidx.test.ext:junit:${rootProject.extra["junit_androidx_version"]}")
}