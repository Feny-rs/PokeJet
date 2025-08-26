# 📱 Pokémon App

A simple Android application built with **Kotlin** using the **MVVM** architecture pattern.  
This app fetches Pokémon data from the [PokéAPI](https://github.com/PokeAPI/pokekotlin) and allows users to view a list of Pokémon and see detailed information.  

---

## ✨ Features
- ⚡ **List & Grid Mode**: Switch between list and grid view for Pokémon.
- 📜 **Pagination**: Efficiently load data page by page from the API.
- 🔍 **Detail Screen**: View detailed information about each Pokémon.
- 🗄️ **Local Caching**: Data persistence using **Room Database**.
- 🌐 **REST API** integration using **Ktor**.
- 🖼️ **Image Loading** with **Coil**.
- 💉 **Dependency Injection** with **Koin**.
- 📐 **MVVM Architecture** for clean and scalable codebase.

---

## 🛠️ Tech Stack
- **Language**: [Kotlin](https://kotlinlang.org/)
- **Architecture**: MVVM (Model-View-ViewModel)
- **Network**: [Ktor](https://ktor.io/)
- **Dependency Injection**: [Koin](https://insert-koin.io/)
- **Image Loading**: [Coil](https://coil-kt.github.io/coil/)
- **Local Database**: [Room](https://developer.android.com/training/data-storage/room)

---

## 📷 Screenshots
| List Mode                   | Grid Mode                   | Detail Screen                   |
|-----------------------------|-----------------------------|---------------------------------|
| ![List](assets/ss_list.png) | ![Grid](assets/ss_grid.png) | ![Detail](assets/ss_detail.png) |

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Ladybug 🐞 or newer
- JDK 17+
- Gradle 8+

### Installation
1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/pokemon-app.git
2. Open in Android Studio.
3. Sync Gradle and run the app on an emulator or real device.

---

## 📂 **Project Structure**
```
.
├── app
│   ├── build
│   │   ├── generated
│   │   ├── intermediates
│   │   ├── kotlin
│   │   ├── kspCaches
│   │   ├── outputs
│   │   └── tmp
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   ├── sampledata
│   └── src
│       ├── androidTest
│       ├── main
│       └── test
├── build.gradle.kts
├── gradle
│   ├── libs.versions.toml
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradle.properties
├── gradlew
├── gradlew.bat
├── local.properties
└── settings.gradle.kts
```


## 🔗 **API Source**
[pokeapi](https://pokeapi.co/docs/v2#wrap)
