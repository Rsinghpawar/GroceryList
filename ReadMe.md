# RahulDemo - Grocery App

A modern, robust Android application built with Jetpack Compose that demonstrates professional software engineering practices, including Clean Architecture, MVVM, and reactive programming.

<div align="center">

| | | |
|:---:|:---:|:---:|
| <img src="https://github.com/user-attachments/assets/ce68ab85-d857-43be-8fb3-c31a9f7178ec" width="250"/> | <img src="https://github.com/user-attachments/assets/0ada18f8-dd2e-427b-9634-b685369581d3" width="250"/> | <img src="https://github.com/user-attachments/assets/b0b88f7b-5e03-4bcb-bfec-8ba2d15c6290" width="250"/> |
| <img src="https://github.com/user-attachments/assets/f052cc1a-0bba-4d90-8930-c5ec1603631c" width="250"/> | <img src="https://github.com/user-attachments/assets/91d2143c-493d-4d8f-a146-c9b9d6c96962" width="250"/> | <img src="https://github.com/user-attachments/assets/e746d029-26b6-41b9-a7e1-5f6b2626cb16" width="250"/> |
| <img src="https://github.com/user-attachments/assets/faf8acaa-c61c-42fd-aac2-270be6135400" width="250"/> | <img src="https://github.com/user-attachments/assets/f40ea7c9-9338-4404-82af-c03c09380258" width="250"/> | <img src="https://github.com/user-attachments/assets/0c783a54-8107-4072-b6d2-9969102257fe" width="250"/> |

</div>



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
