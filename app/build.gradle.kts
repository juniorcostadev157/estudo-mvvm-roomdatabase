plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.junior.formularioroomdatabase"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.junior.formularioroomdatabase"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // 📦 Core e ciclo de vida (base do app)
    implementation(libs.androidx.core.ktx)                  // Funções Kotlin extensions (ex: .let, .run, etc.)
    implementation(libs.androidx.lifecycle.runtime.ktx)     // ViewModel e ciclo de vida com suporte a corrotinas
    implementation(libs.androidx.activity.compose)          // Integração entre Activity e Jetpack Compose

    // 🎨 Compose UI - base visual
    implementation(platform(libs.androidx.compose.bom))     // BOM = Bill Of Materials (centraliza versões do Compose)
    implementation(libs.androidx.ui)                        // UI básica do Compose
    implementation(libs.androidx.ui.graphics)               // Parte gráfica (cores, formas, etc)
    implementation(libs.androidx.ui.tooling.preview)        // Preview de componentes no Android Studio
    implementation(libs.androidx.material3)                 // Material Design 3 (botões, inputs, cards, etc)

    // 🧭 Navegação
    implementation(libs.androidx.navigation.compose)        // Navegação entre telas no Compose

    // 🧪 Testes unitários
    testImplementation(libs.junit)                          // Testes de unidade (JVM)

    // 🤖 Testes Android (instrumentação)
    androidTestImplementation(libs.androidx.junit)          // JUnit para Android
    androidTestImplementation(libs.androidx.espresso.core)  // Espresso = testes de UI
    androidTestImplementation(platform(libs.androidx.compose.bom))  // BOM também nos testes
    androidTestImplementation(libs.androidx.ui.test.junit4)         // Testes específicos de UI com Compose

    // 🛠️ Debug tools (apenas no modo debug)
    debugImplementation(libs.androidx.ui.tooling)           // Ferramentas de inspeção no modo debug
    debugImplementation(libs.androidx.ui.test.manifest)     // Suporte a testes com manifest no debug

    // 🗃️ Room - banco de dados local
    implementation(libs.room.runtime)                       // Room runtime (base do banco)
    implementation(libs.room.ktx)                           // Room com suporte a corrotinas
    ksp(libs.room.compiler)                                 // Compiler do Room (anotations e geração de código)

}
