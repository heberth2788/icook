# I Cook App

The I cook android app

## Architecture

MVVM: for separating the UI from the data layer

<img width="788" alt="Screenshot 2025-02-14 at 02 57 16" src="https://github.com/user-attachments/assets/9f33e913-bce8-4cfb-a6b0-c74d41f94a1a" />

### Layers:

1. UI Layer

    1.1. Activity & Composables

    1.2. ViewModel

2. Domain layer (Not needed in this case)

3. Data Layer

    2.1. Repository

    2.2. DataSource

### Technologies

- Kotlin: for building the app
- Android Jetpack Compose: for building and navigate the UI
- Coil: for load images asynchronously from internet
- Google Maps: for displaying maps
  * Maps Compose library: for displaying maps with Compose
  * Secrets gradle plugin: to hide the maps API key from VCS
- Hilt: for dependency injection
- Flows: for asynchronous data streams
- Coroutines: for asynchronous programming
- Retrofit: for making network requests
- JUnit: for testing

### To improve:

1. Improve the UX/UI
2. Add Room or DataStore for caching data
3. Add new EndPoint for search food recipe by id
4. Add Domain Layer when the app is more complex
5. Add Firebase authentication for login and register
6. Add CI/CE pipeline
7. Add R8 for minifying and optimizing the code

### Screenshots:

![image](https://github.com/user-attachments/assets/270937ba-e2dc-4189-a8fe-0581168debb5)

![image](https://github.com/user-attachments/assets/c66d0f0c-b389-4260-9cb6-4ca32527849e)

![image](https://github.com/user-attachments/assets/3f21d4dd-50c6-4302-9f35-584331428c65)

![image](https://github.com/user-attachments/assets/e569dc83-6b31-4a4d-82a9-d5151f669e3c)

