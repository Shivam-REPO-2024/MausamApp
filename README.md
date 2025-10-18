<h1 align="center">🌦️ Mausam – Modern Weather App</h1>

<p align="center">
  <img src="https://img.shields.io/badge/Language-Kotlin-blue?style=for-the-badge&logo=kotlin"/>
  <img src="https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpack-compose"/>
  <img src="https://img.shields.io/badge/Architecture-MVVM-brightgreen?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Networking-Retrofit2-orange?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/State-Flow%20%26%20Coroutines-purple?style=for-the-badge"/>
</p>

<p align="center">
  <b>A sleek, real-time Weather App built with Jetpack Compose, MVVM, and OpenWeatherMap API ☁️</b><br/>
  <i>Get accurate forecasts, smooth animations, and a dynamic GPS-enabled experience 🌍</i>
</p>

---

## 🌟 Overview

**Mausam** (meaning *“Weather”* in Hindi) is a modern, dynamic, and fully reactive Android weather app built with  
**Jetpack Compose**, **MVVM Architecture**, and **Retrofit2**.

It detects your **current location via GPS**, or lets you **search manually** for any city 🌏 —  
then displays current weather, humidity, wind, and weekly forecasts in a beautiful Material 3 UI.

---

## ⚡ Features

✅ Real-time **Current Weather** & **Weekly Forecasts**  
✅ Dual Location Mode → **GPS Auto Detect** 🌐 or **Manual City Search** 🏙️  
✅ Stunning **Compose Shimmer Loading Animations** ✨  
✅ Smooth **Navigation Drawer + Animated Transitions**  
✅ **MVVM Architecture** (ViewModel + Repository + Flow)  
✅ **OpenWeatherMap API** integration  
✅ **Error Handling** + **Clean Loading States**  
✅ **Responsive UI** for all device sizes  

---

## 🧠 Tech Stack

| Layer | Technology Used |
|-------|------------------|
| 🧩 **Language** | Kotlin |
| 🎨 **UI Toolkit** | Jetpack Compose (Material 3) |
| 🧱 **Architecture** | MVVM (Model–View–ViewModel) |
| ⚙️ **Networking** | Retrofit2 + Gson Converter |
| 📡 **Asynchronous** | Kotlin Coroutines + StateFlow |
| 🗺️ **Location** | Google Play Services (FusedLocationProviderClient) |
| 🖼️ **Image Loading** | Coil + Coil-SVG |
| ✨ **UI Animations** | Compose Shimmer |
| ⚡ **Build System** | Gradle (Kotlin DSL + Version Catalog) |

---

## 🏗️ Project Structure

com.chakravyuh.mausam/
│
├── ApiServices/
│   ├── WeatherApiService.kt
│   └── WeatherRetrofitInstance.kt
│
├── model/
│   ├── CurrentWeatherResponse.kt
│   ├── ForecastResponse.kt
│   └── LocationData.kt
│
├── viewModels/
│   ├── WeatherViewModel.kt
│   └── LocationViewModel.kt
│
├── components/
│   ├── WeatherCard.kt
│   ├── SearchBar.kt
│   ├── AppBar.kt
│   └── ShimmerEffects.kt
│
├── utils/
│   ├── LocationUtils.kt
│   ├── DateTimeFormatter.kt
│   └── GetIconResId.kt
│
└── ui/
├── HomeScreen.kt
└── WeeklyReportScreen.kt

---

## 🛰️ Architecture Diagram

```mermaid
%%{init: {"theme": "base", "themeVariables": { 
"primaryColor": "#4A90E2", 
"secondaryColor": "#8E44AD",
"tertiaryColor": "#F1C40F",
"edgeLabelBackground":"#f5f5f5",
"fontSize": "13px"
}}}%%

graph LR
%% Layout Left-to-Right

%% UI Layer
A1["UI Layer<br/>Jetpack Compose Screens"]:::ui
A2["UI Components<br/>WeatherCard / AppBar / Drawer"]:::ui
A1 -.-> A2

%% ViewModel Layer
B1["WeatherViewModel<br/>StateFlow + Coroutines"]:::vm
B2["LocationViewModel<br/>GPS & Manual Search Control"]:::vm
A2 -->|observes| B1
A2 -->|observes| B2

%% Repository Layer
C1["Repository Layer<br/>Business Logic & Data Handling"]:::repo
B1 -->|fetchWeather / fetchForecast| C1
B2 -->|getCoordinates / updateLocation| C1

%% Network Layer
D1["Retrofit API Service"]:::net
C1 -->|calls API endpoints| D1

%% API Layer
E1["OpenWeatherMap API"]:::api
D1 -->|GET weather / forecast / geocoding| E1

%% Utilities
U1["LocationUtils<br/>GPS Updates & Permissions"]:::util
U2["DateTimeFormatter<br/>Time & Date Helpers"]:::util
U3["GetIconResId<br/>Maps API Icons to Drawables"]:::util
B2 -->|uses| U1
B1 -->|uses| U2
B1 -->|uses| U3

%% Curved arrows
A1 -.->|UI Flow| B1
A1 -.->|UI Flow| B2
C1 -.->|Data Flow| D1
D1 -.->|API Call| E1

%% Style nodes
classDef ui fill:#E8F4FF,stroke:#4A90E2,stroke-width:2px,color:#000,font-weight:bold;
classDef vm fill:#F3E8FF,stroke:#8E44AD,stroke-width:2px,color:#000,font-weight:bold;
classDef repo fill:#FFF6E8,stroke:#F1C40F,stroke-width:2px,color:#000,font-weight:bold;
classDef net fill:#E8FFF3,stroke:#27AE60,stroke-width:2px,color:#000,font-weight:bold;
classDef api fill:#E8FFFF,stroke:#16A085,stroke-width:2px,color:#000,font-weight:bold;
classDef util fill:#F8E8FF,stroke:#9B59B6,stroke-width:2px,color:#000,font-weight:bold;

%% Assign classes
class A1,A2 ui;
class B1,B2 vm;
class C1 repo;
class D1 net;
class E1 api;
class U1,U2,U3 util;
```
---
# 📸 Screenshots 

<p align="center">

<img width="405" height="850" alt="Screenshot 2025-10-18 at 8 23 21 PM" src="https://github.com/user-attachments/assets/c6f3fee7-dd71-4325-8389-36cb67b132f1" />
<img width="405" height="850" alt="Screenshot 2025-10-18 at 8 23 33 PM" src="https://github.com/user-attachments/assets/3280dfca-3be4-4612-af0e-08fbf0f4d6cf" />
<img width="405" height="850" alt="Screenshot 2025-10-18 at 8 24 44 PM" src="https://github.com/user-attachments/assets/79b79ff9-8325-4c7e-b61b-944bdc6eabff" />

  
</p>

---

# ⚙️ Setup & Installation

1️⃣ Clone this repository

```bash
git clone https://github.com/your-username/Mausam.git
```
2️⃣ Open the project in Android Studio

3️⃣ Add your API key
Go to OpenWeatherMap → get your free API key.
Then in local.properties, add:

```bash
API_KEY=your_api_key_here
```
4️⃣ Build & Run 🚀

---

# 📸 Demo

🎥 Watch the Demo on YouTube

https://youtu.be/aPypfNKVNgI

---

🧩 Future Enhancements
	•	🌙 Add Dark Mode
	•	💾 Offline caching via Room DB
	•	🎬 Shared element animations between screens
	•	🗣️ AI-based weather insights (Gemini/OpenAI)
	•	🌍 Multi-language support

---

## 🧑‍💻 Author

<p align="center">
  <img src="https://avatars.githubusercontent.com/u/174893894?s=96&v=4" width="120" height="120" style="border-radius:50%;" alt="Shivam Mishra"/>
</p>

<h3 align="center">Shivam Mishra</h3>
<p align="center">
  📱 <b>Android Developer</b> | 💻 Kotlin | 🎨 Jetpack Compose Enthusiast  
</p>

<p align="center">
  <a href="https://github.com/Shivam-REPO-2024" target="_blank">
    <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white"/>
  </a>
  <a href="https://www.linkedin.com/in/shivam-kumar-mishra" target="_blank">
    <img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"/>
  </a>
  <a href="https://youtube.com/@binaryshastra?si=4olpuZdKzoawX-aE" target="_blank">
    <img src="https://img.shields.io/badge/YouTube-FF0000?style=for-the-badge&logo=youtube&logoColor=white"/>
  </a>
</p>

---

<h3 align="center">⭐ If you like this project, don't forget to give it a star!</h3>

---
🛠️ License

This project is licensed under the MIT License.

⸻
<h3 align="center">⭐ If you like <b>Mausam</b>, don’t forget to give it a star — it motivates me to build more! ⭐</h3>
```








