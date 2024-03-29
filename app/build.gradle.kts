plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.eventhub"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.eventhub"
        minSdk = 24
        targetSdk = 33
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
}

dependencies {

    //noinspection GradleCompatible,GradleCompatible
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation ("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("com.google.android.material:material:1.5.0-alpha05")
    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("androidx.cardview:cardview:1.0.0")
    implementation ("com.google.firebase:firebase-auth:19.3.0")
    implementation ("com.caverock:androidsvg:1.4")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")
    implementation ("com.google.firebase:firebase-auth:21.0.1")
    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation ("com.github.ZEGOCLOUD:zego_uikit_prebuilt_live_streaming_android:+")
    implementation(platform("com.google.firebase:firebase-bom:32.5.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation ("com.firebaseui:firebase-ui-storage:7.2.0")
    implementation ("com.google.firebase:firebase-storage:19.2.2")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}