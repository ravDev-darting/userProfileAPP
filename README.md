# UserProfileApp — Android Architecture Showcase

UserProfileApp is a **production-style Android sample project** built to demonstrate **modern Android development best practices**.  
The project focuses on **Clean Architecture**, **MVVM**, and **XML + Jetpack Compose interoperability** using a **shared ViewModel**.

This app was created specifically to showcase Android architecture skills for **EU / Germany-based Android engineering roles**.

---

## 🎯 What This App Proves 

This project demonstrates:

- XML + Jetpack Compose interoperability
- Shared ViewModel across different UI technologies
- Clean Architecture (data / domain / presentation)
- MVVM with `StateFlow`
- Retrofit + Coroutines + Flow
- Loading, Success, and Error state handling
- Navigation Component
- Modern Gradle setup using Version Catalog (`libs.versions.toml`)
- Production-safe project structure (scalable & testable)

---

## 🧩 App Behavior

- Fetches **one user profile** from:
  https://jsonplaceholder.typicode.com/users

## Screen Scope

This project intentionally uses **a single screen**.

The purpose is to:
- Demonstrate Clean Architecture and MVVM
- Show state-driven UI using StateFlow
- Focus on data flow rather than navigation complexity

All user details (name, email, phone, address) are displayed on one screen.
This keeps the project simple while highlighting architectural correctness.


### Layers

- **Presentation**
    - UI (XML Fragment & Compose Fragment)
    - ViewModel
    - UI State handling

- **Domain**
    - Business models
    - Repository interfaces
    - Use cases

- **Data**
    - Retrofit API
    - DTO → Domain mapping
    - Repository implementation

---

## 📁 Project Structure

```
UserProfileApp/
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/example/userprofile/
│       │   ├── MainActivity.kt
│       │   │
│       │   ├── data/
│       │   │   ├── remote/
│       │   │   │   ├── ApiService.kt
│       │   │   │   └── RetrofitClient.kt
│       │   │   ├── mapper/
│       │   │   │   └── UserMapper.kt
│       │   │   └── repository/
│       │   │       └── UserRepositoryImpl.kt
│       │   │
│       │   ├── domain/
│       │   │   ├── model/
│       │   │   │   └── User.kt
│       │   │   ├── repository/
│       │   │   │   └── UserRepository.kt
│       │   │   └── usecase/
│       │   │       └── GetUserUseCase.kt
│       │   │
│       │   └── presentation/
│       │       ├── state/
│       │       │   └── UiState.kt
│       │       ├── viewmodel/
│       │       │   └── UserViewModel.kt
│       │       ├── xml/
│       │       │   └── UserXmlFragment.kt
│       │       └── compose/
│       │           └── UserComposeFragment.kt
│       │
│       └── res/
│           ├── layout/
│           │   ├── activity_main.xml
│           │   └── fragment_user_xml.xml
│           └── navigation/
│               └── nav_graph.xml
│
├── gradle/
│   └── libs.versions.toml
├── settings.gradle.kts
└── build.gradle.kts

```


---

## 🔄 State Management

The UI is driven by a single `UiState` sealed class:

```kotlin
sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

```

---

## 🚀 How to Run the Project

Clone the repository:
git clone https://github.com/ravDev-darting/userProfileAPP