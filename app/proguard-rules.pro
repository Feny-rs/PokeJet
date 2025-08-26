# ----------------------------
# Coil
# ----------------------------
-keep class coil.** { *; }
-keep class com.bumptech.glide.** { *; }

# ----------------------------
# Ktor
# ----------------------------
-keep class io.ktor.** { *; }
-keepclassmembers class io.ktor.** { *; }

# ----------------------------
# Room
# ----------------------------
-keep class androidx.room.** { *; }
-keep class androidx.sqlite.** { *; }
-keepclassmembers class * extends androidx.room.RoomDatabase {
    <init>();
}

# ----------------------------
# Koin
# ----------------------------
-keep class org.koin.** { *; }

# ----------------------------
# Jetpack Compose
# ----------------------------
-keep class androidx.compose.** { *; }
-keep class kotlin.reflect.** { *; }
-keepclassmembers class * {
    @androidx.compose.runtime.Composable <methods>;
}

# ----------------------------
# Kotlin Serialization
# ----------------------------
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class * {
    @kotlinx.serialization.Serializable <fields>;
}

# ----------------------------
# SplashScreen
# ----------------------------
-keep class androidx.core.splashscreen.** { *; }

# ----------------------------
# Navigation Compose
# ----------------------------
-keep class androidx.navigation.** { *; }

# ----------------------------
# Pokekotlin
# ----------------------------
-keep class co.pokeapi.pokekotlin.** { *; }

# ----------------------------
# Annotations
# ----------------------------
-keepattributes *Annotation*

# ----------------------------
# Suppress warnings from missing classes
# ----------------------------
-dontwarn java.lang.management.ManagementFactory
-dontwarn java.lang.management.RuntimeMXBean
-dontwarn org.slf4j.impl.StaticLoggerBinder
-dontwarn org.slf4j.impl.StaticMDCBinder
