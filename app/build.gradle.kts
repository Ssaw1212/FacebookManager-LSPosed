plugins {
    id("com.android.application")
}

android {
    namespace = "com.ssaw1212.facebookmanager"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.ssaw1212.facebookmanager"
        minSdk = 26
        targetSdk = 36
        versionCode = 2
        versionName = "1.0.0-modern"
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    packaging {
        resources {
            merges += "META-INF/xposed/java_init.list"
            merges += "META-INF/xposed/module.prop"
            merges += "META-INF/xposed/scope.list"
        }
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.13.0")

    compileOnly("io.github.libxposed:api:102.0.0")
}
