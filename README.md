## Architecture and implementation decisions

- The app is implemented with MVVM architecture following the Clean Architecture principles. It doesn't follow them strictly to avoid redundant complexity in such a simple app, but the main concept is showcased. The ***UI*** layer is represented with Jetpack Compose files declaring the screen and its components. The ***presentation*** layer contains ViewModel, presentation entities and the conversion/formatting logic. The ***data*** layer provides the data from the local source (Room database), which is then used by ***domain*** layer through repository facade and provided to ViewModel via use cases.
- Jetpack Compose has been chosen for the UI layer because it's the most up-to-date technology and is ideal for real-time UI updates.
- Dagger/Hilt combination is used for dependency injection since it's a staple in the majority of production applications.
- Room is used for database access for simplicity and convenience, but other solutions could have been used as well.
- The app works reactively using unidirectional data flow: the user events (typing, sending) are flowing upstream to update the data in cache and persistent storage, and then the data flows downstream to update the UI reactively.
- A simple user switching is implemented to showcase two-way messaging, it is not persisted in the database.

## App limitations and possible improvements

- The app has just one module for the sake of simplicity. In real application we would probably create a chat feature module and messages data module to allow reusing the data across multiple features. Also the user management would be put in a separate library module, as well as some of the reusable UI components.
- There are no tests in the application. I'm usually writing tests as I go, but for the sake of saving time, I've decided to go without them this time. In normal development, I'd follow the testing guidelines for a particular project and write either unit tests for each component, or/and VM unit/integration tests covering all the components it is using.
- There is no navigation in the app. The "back" button just closes the application. In the production app this screen would be integrated into a navigation tree as a node, and navigated to from different places.
- This app doesn't have "sent"/"read" indicators logic. I wanted to implement it initially and even added a UI for it, but since it's not a part of the task, I'll add it to "possible improvements". :)
- There is no real user management, just simple in-memory switching between two users.
- Initially I planned to implement pagination, the paging3 library has good integration with Room database. But it's not a part of the task, and it probably makes more sense if we're using a remote source (API) to get data from the server.
- There is a lot more that the chat app could benefit from: typing indicator, media attachments, message editing/deleting, unreads, better date/time formatting, etc., but this is just speculation outside of the scope of this task. :)

## Short video of the app in action

https://github.com/GeorgiyShur/MuzzTask/assets/18367685/db92e700-392d-42c7-8ed3-a0222ba7eaea

