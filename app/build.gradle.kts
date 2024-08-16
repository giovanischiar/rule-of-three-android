plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "io.schiar.ruleofthree"
    compileSdk = 34

    defaultConfig {
        applicationId = "io.schiar.ruleofthree"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Activity
    //noinspection KtxExtensionAvailable
    implementation(libs.androidx.activity)
    implementation(libs.androidx.activity.compose.v182)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Compose
    implementation(libs.androidx.foundation.layout.android)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.animation.core)
    implementation(libs.androidx.animation)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.runtime)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.text)
    implementation(libs.androidx.ui.unit)
    implementation(libs.androidx.material3) // Material

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

task("generateIcons", type = Exec::class) {
    dependsOn("mergeDebugResources")
    inputs.file("src/main/res/values/iconpalette.xml")
    inputs.dir("../scripts/IconCreator/Icon")
    outputs.file("src/main/res/drawable/ic_launcher_background.xml")
    outputs.file("src/main/res/drawable-v24/ic_launcher_foreground.xml")
    project.exec {
        commandLine = listOf("bash", "../scripts/IconCreator/Library/icon-creator.sh", "android", "../..", "../Icon")
    }
}