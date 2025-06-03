# 🎮 Movie Explorer App

This is a Kotlin-based Android application developed using **Jetpack Compose** as part of the **5COSC023C Mobile Application Development** module at the University of Westminster.

The app helps users discover movies using the OMDb API and supports local data storage with Room (SQLite).

---

## 📌 Features

* ✅ Add hardcoded movies to local Room database
* ✅ Search movies by title via OMDb API
* ✅ View and save retrieved movie details
* ✅ Search for movies by actor (case-insensitive partial match)
* ✅ Bonus: Search for multiple movies by a partial title via OMDb API
* ✅ Orientation-safe UI state persistence (rotation support)

---

## 🚀 Getting Started

### Prerequisites

* Android Studio Giraffe or newer
* Kotlin 1.9+
* API Key from [OMDb API](https://www.omdbapi.com/apikey.aspx)

### Setup

1. Clone this repo:

   ```bash
   git clone https://github.com/your-username/movie-explorer-app.git
   ```
2. Open the project in Android Studio.
3. Add your API key in the constants file:

   ```kotlin
   const val API_KEY = "your_api_key_here"
   ```
4. Run the app on an emulator or physical device.

---

## 🖼️ Screenshots

| Home Screen                                                                                     | Add Movies                                                                                     | Search by Title                                                                                     |
| ----------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- |
| ![Home Screen](https://github.com/user-attachments/assets/01671674-fa77-4001-ad4d-a28d1592ae3c) | ![Add Movies](https://github.com/user-attachments/assets/09c3fd56-3da4-40c5-9d68-e67b813331ba) | ![Search by Title](https://github.com/user-attachments/assets/3ae3c580-c5a3-4d82-bf0a-b3f5e4e793cd) |

| Movie Detail                                                                                     | Search by Actor                                                                                  | Title Substring Search                              |
| ------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------ | --------------------------------------------------- |
| ![Movie Detail](https://github.com/user-attachments/assets/69e8fe73-2441-4863-9b9c-591d6d604a66) | ![Search Actor](https://github.com/user-attachments/assets/9b1133c3-c190-456f-b420-9a5ad94522de) | ![Title Substring](https://github.com/user-attachments/assets/33544960-6d5b-4765-aa32-082f132c9fc4)
) |

---

## 📂 Project Structure

```
📁 app/
 ├── 📁 data/
 │   ├── 📄 MovieDao.kt
 │   ├── 📄 MovieDatabase.kt
 │   └── 📄 MovieEntity.kt
 ├── 📁 ui/
 │   ├── 📄 HomeScreen.kt
 │   ├── 📄 MovieSearchScreen.kt
 │   └── 📄 ActorSearchScreen.kt
 ├── 📁 network/
 │   └── 📄 OmdbApiService.kt
 ├── 📄 MainActivity.kt
 └── 📄 Constants.kt
```

---

## 📃 License

This project is for educational purposes only. No third-party libraries were used, as per coursework requirements.

---

## 😋 Author

**Anish Anthony De Silva**
University of Westminster
Module: 5COSC023C – Mobile Application Development
