import java.io.FileInputStream
import java.util.Properties

val apiKeyPropertiesFile = rootProject.file("apikey.properties")
val apiKeyProperties = Properties()

if (apiKeyPropertiesFile.exists()) {
    apiKeyProperties.load(FileInputStream(apiKeyPropertiesFile))
}


plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.githubprofile"
    compileSdk {
        version = release(37)
    }


    defaultConfig {
        applicationId = "com.example.githubprofile"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        buildConfigField(
            "String",
            "ACCESS_TOKEN",
            "\"${apiKeyProperties.getProperty("ACCESS_TOKEN", "")}\""
        )

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }


    buildFeatures {
        viewBinding = true
        buildConfig = true
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)

// library untuk menampilkan gambar bergerak (splashscreen)
    implementation("com.airbnb.android:lottie:5.2.0")

    // library untuk menampilkan gambar bulat (circle)
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // library untuk menampilkan gambar melalui url
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // library untuk request API
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // library untuk logging hasil request API
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)


}
