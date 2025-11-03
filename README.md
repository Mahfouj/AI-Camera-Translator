# 📸 AI Camera Translator

AI Camera Translator is a **modern Android application** that uses your **camera** to instantly **recognize and translate text** from the real world — powered by **Google ML Kit**, **CameraX**, and **Clean Architecture** principles.  
Built entirely in **Kotlin**, this app delivers speed, accuracy, and a smooth user experience with offline-ready translation support.

---

## 🚀 Features

- 🔍 **Real-time OCR (Text Recognition)** using Google ML Kit  
- 🌐 **Instant text translation** (supports on-device or API-based)  
- 🧠 **Clean Architecture (MVVM + UseCases + Repository)**  
- ⚡ **Kotlin Coroutines** for smooth background operations  
- 🧩 **Dependency Injection** with Hilt  
- 🎥 **CameraX Integration** for real-time text capture  
- 💾 **History tracking** via Room Database  
- 🔊 **Text-to-Speech (TTS)** playback for translated text  
- 🖋️ **Elegant Material Design UI** with smooth navigation  
- 🔒 **Offline-ready** and optimized for performance  

---

## 🧱 Tech Stack

| Category | Tools / Libraries |
|-----------|------------------|
| **Language** | Kotlin |
| **Architecture** | Clean Architecture (MVVM) |
| **Dependency Injection** | Hilt |
| **Database** | Room |
| **Networking** | Retrofit, OkHttp |
| **Camera** | CameraX |
| **ML / OCR** | Google ML Kit |
| **Coroutines** | kotlinx.coroutines |
| **UI / UX** | Material Components, ViewBinding |
| **Crash & Analytics** | Sentry, App Center |
| **Testing** | JUnit, Espresso |

---

## 🗂️ Project Structure

com.example.aicameratranslator/
│
├── data/
│ ├── api/ # Retrofit services
│ ├── local/ # Room database + DAO
│ ├── repository/ # Data source implementations
│
├── domain/
│ ├── model/ # Data models
│ ├── usecase/ # Business logic
│
├── presentation/
│ ├── camera/ # Camera preview + OCR handling
│ ├── translation/ # Translation UI + logic
│ ├── history/ # Saved translation history
│
└── di/ # Hilt modules

yaml
Copy code

---

## ⚙️ Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/Mahfouj/AI-Camera-Translator.git
Open the project in Android Studio (Giraffe or later)

Build & Run

Minimum SDK: 24 (Android 7.0)

Target SDK: 36

(Optional) For API-based translation:
Add your base URL or API key in
TranslationApiService.kt

🧠 Learning Goals
This project demonstrates:

How to use CameraX for real-time text scanning

Implementing Google ML Kit OCR

Applying Clean Architecture + MVVM in Kotlin

Using Room for local storage

Integrating Hilt for dependency injection

Building a production-ready Android app

👨‍💻 Developer
Mahfouz al Farddin
