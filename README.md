# nimble-code-test
## Technical Requirements
 - Kotlin (1.9.0)
 - Android Studio (Android Studio Giraffe - 2023.3.1 Patch 2)
 - Gradle (AGP - 8.1.2)
 - Minimun SDK (21)
 
### Tech
- Gradle build with Kotlin Script, Custom Gradle Plugin and [Gradle Version Catalog](https://developer.android.com/build/migrate-to-catalogs)
- Reactive programming with [Kotlin coroutines](https://developer.android.com/kotlin/coroutines)
- Stream with [Kotlin Flow](https://developer.android.com/kotlin/flow)
- UI with Jetpack Compose and [Material3](https://developer.android.com/jetpack/androidx/releases/compose-material3)
- Dependency Injection framework with [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- UI transation with [Compose navigation](https://developer.android.com/jetpack/compose/navigation)
- Datastore Preferences [Datastore Preferences](https://developer.android.com/topic/libraries/architecture/datastore)
- MVVM with Clean Architecture [Recommended Android Architecture](https://developer.android.com/topic/architecture)
- Multimodule Architecture [Modularization](https://developer.android.com/topic/modularization)
- Database with [Room](https://developer.android.com/training/data-storage/room)
- TDD (Unit and Integration test) with JUnit4, Mockito, Retrofit MockWebServer

### Application Features
#### Login
User must log in with their account.
> Add one more logic to validate password
 - at least one Upper letter
 - at least one Lower letter
 - at least one digit
 - at least one special character !@#$%^&+-
 - no space in password
 - at least 6 chars
 - not exceed 24 chars

#### Forgot Password
Show email user typed in login.

#### Surveys
Users can browse a list of surveys.
Automatic usage of refresh token.
Loading animation.
Navigation indicator (bullets) dynamic based on API response.

#### Surveys Detail
Show survey id for furture API request.
User can navigate back to Surveys Screen.

> Application supports both Light, Dark and Dynamic Colors (Android 12 +)

![light](https://github.com/kyawlinnthant/nimble-code-test/assets/24668175/3c80cb33-6faa-480f-a45c-b09b45d77846)
![dark](https://github.com/kyawlinnthant/nimble-code-test/assets/24668175/60fc00c9-7ab8-416f-869b-2951f7986e6b)

### TDD

#### Data Layer Testing
 - ApiService
 - Database
 - Preferences Datastore
 - Repository 

#### Domain Layer Testing
 - Usecases
   
#### Unit Test   
 - FormValidator
 - Network Api Call Handler (safeApiCall)
 - at least 6 chars
 - not exceed 24 chars

### CI
Run Continuous Integration with Github Actions (android_build.yml)
- code_linting
  - run ktlintCheck
- unit_tests
  - run detekt
  - run unit tests
