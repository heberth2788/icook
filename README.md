# I Cook App

The I cook android app

## Architecture

MVVM: for separating the UI from the data layer

### Layers:

1. UI Layer

    1.1. Activity & Composables

    1.2. ViewModel

2. Domain layer (Not needed in this case)

3. Data Layer

    2.1. Repository

    2.2. DataSource

## Technologies

- Kotlin: for building the app
- Android Jetpack Compose: for building the UI
- Google Maps: for displaying maps
- Hilt: for dependency injection
- Flows: for asynchronous data streams
- Coroutines: for asynchronous programming
- Room: for caching data
<!-- - Firebase (and Firebase emulators): for real-time database and authentication -->
- Retrofit: for making network requests
- R8: for minifying and optimizing the code
- JUnit: for testing
