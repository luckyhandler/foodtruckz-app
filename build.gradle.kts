buildscript {
    val compose_version by extra("1.0.5")
    val coil_version by extra("1.3.1")
    val core_ktx_version by extra("1.7.0")
    val appcompat_version by extra("1.4.1")
    val lifecycle_runtime_ktx_version by extra("2.4.0")
    val activity_compose_version by extra("1.4.0")
    val material_version by extra("1.5.0")
    val retrofit_version by extra("2.9.0")
    val okhttp_version by extra("4.9.1")
    val timber_version by extra("4.7.1")
    val junit_version by extra("4.13.2")
    val junit_androidx_version by extra("1.1.3")

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}