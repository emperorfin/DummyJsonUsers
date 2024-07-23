# DummyJsonUsers

DummyJsonUsers is a native Android app project that uses [DummyJSON webservice REST API](https://dummyjson.com/) to
allow a user scroll through paginated DummyJSON users.

## Functionality

* When the app is newly installed and if the user opens it without an internet connectivity, the
  screen just shows a retry button and an error message and allows the user to refresh the screen
  assuming they have an internet connectivity. A snack bar no internet connectivity error message is
  also shown which disappears after a short time. If the user tries refreshing without an internet
  connectivity, the above same thing happens. If there's an internet connectivity but it's not active,
  a retry button with a different error message is shown but a snack bar no internet connectivity
  error message is not shown since there's an internet connectivity but just that it's not active.
* The app fetches a paginated list of DummyJSON users (from the dummyjson.com web service) as the 
  user scrolls when there's an active internet connectivity. The paginated list of DummyJSON users is then displayed on the
  screen. This list of users is not cached to the Room database. A DummyJSON user gets cached to the
  Room database when the user taps a DummyJSON user from the list to view more details about it. So what this
  means is that the user can view a list of cached DummyJSON users (previously viewed DummyJSON users) and details of
  each offline.
* Assuming the app opens for the first time with an active internet connectivity, the app displays
  a screen with an initial list of DummyJSON users.
* Whenever the user subsequently visits the "Dummy Json Users" screen which is where the aforesaid 
  DummyJSON users get displayed, if without internet connectivity, the cached list of DummyJSON users is displayed.
  But with internet connectivity (which must be active), a fresh list of DummyJSON users is fetched 
  from the web service and displayed on the screen. If there's an internet connectivity but is not active, 
  rather than displaying a cached list of DummyJSON users, a retry button with a different error message 
  is shown but a snack bar no internet connectivity error message is not shown.

## Project Tech-stack and Characteristics

* Android Framework
* Kotlin
* Jetpack Compose (Google's first class new UI toolkit that's declarative with better and performant UI components
  than the old View UI toolkit with it's components such as RecyclerView)
* Jetpack Compose Material Design 3 Components
* ViewModel
* Kotlin Coroutine
* StateFlow (better than LiveData)
* MVVM Architectural Design Pattern
* Repository Pattern
* Navigation
* Offline Storage (via Room)
* Retrofit
* [DummyJSON REST API](https://dummyjson.com/)
* Dependency Injection (Hilt)