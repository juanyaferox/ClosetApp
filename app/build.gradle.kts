plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.feroxdev.closetApp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.feroxdev.closetApp"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17

    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.inappmessaging)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //noinspection UseTomlInstead,GradleDependency
    implementation("androidx.navigation:navigation-fragment-ktx:2.4.0")
    //noinspection UseTomlInstead,GradleDependency
    implementation("androidx.navigation:navigation-ui-ktx:2.4.0")

    //**ROOM DEPEDENCIES**//
    val room_version = "2.6.1"
    //noinspection UseTomlInstead,GradleDependency
    implementation("androidx.room:room-runtime:$room_version")
    //noinspection UseTomlInstead,GradleDependency
    annotationProcessor("androidx.room:room-compiler:$room_version")
    //noinspection KaptUsageInsteadOfKsp,UseTomlInstead,GradleDependency
    kapt("androidx.room:room-compiler:$room_version")
    //noinspection UseTomlInstead,GradleDependency
    implementation("androidx.room:room-ktx:$room_version")
    //noinspection UseTomlInstead,GradleDependency
    implementation("androidx.room:room-rxjava2:$room_version")
    //noinspection UseTomlInstead,GradleDependency
    implementation("androidx.room:room-rxjava3:$room_version")
    //noinspection UseTomlInstead,GradleDependency
    implementation("androidx.room:room-guava:$room_version")
    //noinspection UseTomlInstead,GradleDependency
    testImplementation("androidx.room:room-testing:$room_version")
    //noinspection UseTomlInstead,GradleDependency
    implementation("androidx.room:room-paging:$room_version")

    //**LIFECYCLE DEPEDENCIES**//
    val lifecycle_version = "2.4.0"
    //noinspection UseTomlInstead,GradleDependency
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    //noinspection UseTomlInstead,GradleDependency
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")

}