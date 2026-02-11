# RahulDemo - Grocery App

A modern, robust Android application built with Jetpack Compose that demonstrates professional software engineering practices, including Clean Architecture, MVVM, and reactive programming.





## 📱 Features

- **Grocery List**: Seamlessly add grocery list items with a user-friendly interface.
- **Adding Items**: Users can
    - Add/Edit items
    - Choose category (e.g., Vegetables, Fruits, Dairy), this field is optional
    - User can delete by swiping left or by tapping 3 dot icon to see delete option
    - User can mark items as completed
    - List can be filtered by category or show all items
    - List can be sorted by category, completion and more
    - The app also works well with Dark Theme
      
- **Known Issues(In progress)**: On Edit the textfield does gain focus but the keyboard sometimes does not show up, this is a known issue with Jetpack Compose
- Performance can further be optimized.

## 🛠 Tech Stack

- **UI**: Jetpack Compose (Material 3)
- **Dependency Injection**: Hilt
- **Persistence Storage**: Room
- **Asynchronous Flow**: Kotlin Coroutines & StateFlow
- **Animations**: Compose Animation
- **Architecture**: MVVM + Clean Architecture (Data, Domain, Presentation)
- **Testing**: JUnit, MockK (Unit testing for ViewModels, UseCases, Repositories, and Mappers) Skipped for now, cand be added later if needed

## 🏗 Architecture

The project follows **Clean Architecture** principles to ensure scalability and testability:
- **Presentation Layer**: Compose screens, ViewModels (MVI-ish `onAction` pattern), and UI state management.
- **Domain Layer**: Pure Kotlin business logic containing Models and UseCases (e.g., `CalculatePortfolioSummaryUseCase`).
- **Data Layer**: API definitions, Repository implementations, and Data Transfer Objects (DTOs) with Mappers.



## 🧪 Testing (Skipped for now)

The app is backed by a comprehensive suite of unit tests:
- **Repository Tests**: Verifies data fetching and mapping logic using mock responses.
- **UseCase Tests**: Ensures business logic for financial calculations is 100% accurate.
- **ViewModel Tests**: Validates UI state transitions, action handling, and automatic network recovery logic.

## 🚀 Getting Started

1. Clone the repository.
2. Open in Android Studio (Ladybug or newer recommended).
3. Sync the project with Gradle files.
4. Run the `:app` module on an emulator or physical device.
5. To run tests: Right-click `app/src/test` folder and select **Run 'Tests in...'**
