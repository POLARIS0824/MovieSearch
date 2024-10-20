plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android") version "1.9.10"// 用标准方式引入 Kotlin 插件
    //id("org.jetbrains.kotlin.kapt") // 引入 kapt 插件
}

android {
    namespace = "com.example.moviesearch"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.moviesearch"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        kotlinOptions {
//            jvmTarget = "1.8"
//        }
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
    // 替换 libs.appcompat, libs.material, 等为实际的依赖版本
    implementation("androidx.appcompat:appcompat:1.6.1") // AppCompat
    implementation("com.google.android.material:material:1.8.0") // Material Components
    implementation("androidx.activity:activity-ktx:1.6.0") // Activity KTX
    implementation("androidx.constraintlayout:constraintlayout:2.1.4") // ConstraintLayout

    testImplementation("junit:junit:4.13.2") // JUnit 测试库
    androidTestImplementation("androidx.test.ext:junit:1.1.5") // Android 扩展 JUnit
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") // Espresso 测试库

    // 其他现有依赖保持不变
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    implementation("com.github.bumptech.glide:glide:4.13.2")
    implementation("com.squareup.picasso:picasso:2.71828")
    //kapt("com.github.bumptech.glide:compiler:4.13.2")
}
