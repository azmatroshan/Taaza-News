# Taaza News
### [Download APK](https://drive.google.com/file/d/174-Og5OxXiIkevPZvvlWuW1lHNIxp6Fa/view?usp=sharing)
Taaza News is an Android app that displays the latest news from various news sources using the News API.
## Key Features

- View the latest news from various sources
- View the news articles category-wise
- Read full articles within the app
- Swipe left/right to see different categories's news articles
- Share favorite news articles to others effortlessly

## Screenshots
<details>
<summary>Light Mode</summary>
  <img width="300" hspace="12" src="https://user-images.githubusercontent.com/93484428/227797646-cc2aebc7-fab8-4c76-9ed7-f617a12bb088.jpg" alt="NoInternetLight">
  <img width="300" hspace="12" src="https://user-images.githubusercontent.com/93484428/227797689-c11d79ee-c65d-46ba-81f5-cda133355410.jpg" alt="LoadingLight">
  <img width="300" hspace="12" src="https://user-images.githubusercontent.com/93484428/227797714-a476f0e7-ed2a-498a-a8e5-a6f073f3801f.jpg" alt="AllLight">
  <img width="300" hspace="12" src="https://user-images.githubusercontent.com/93484428/227797735-f10f4621-de58-4bae-95ed-70e4df4fc31f.jpg" alt="PoliticsLight">
  <img width="300" hspace="12" src="https://user-images.githubusercontent.com/93484428/227797743-697931db-7fe5-4b1f-8b27-733f948a94e7.jpg" alt="TechnologyLight">
</details>


<details>
<summary>Dark Mode</summary>
  <img src="https://user-images.githubusercontent.com/93484428/227798784-f13e2162-a84c-4b0f-9566-38623ecb76ae.jpg" width="300" hspace="12">
  <img src="https://user-images.githubusercontent.com/93484428/227798809-660611da-16ba-4f87-8f85-dea0a050810b.jpg" width="300" hspace="12">
  <img src="https://user-images.githubusercontent.com/93484428/227798803-0e745672-6f8f-450a-a12a-bfcbbdfa35d9.jpg" width="300" hspace="12">
  <img src="https://user-images.githubusercontent.com/93484428/227798848-60265a20-b9e6-4419-9209-cbf77691159e.jpg" width="300" hspace="12">
  <img src="https://user-images.githubusercontent.com/93484428/227798853-46ec7a02-aa83-4e32-b22d-a66360ae26c4.jpg" width="300" hspace="12">
</details>


## Installation

1. Clone the repository: `git clone https://github.com/azmatroshan/Taaza-News.git`
2. Open the project in Android Studio
3. Build and run the app on your Android device

Alternatively, you can download the app from [here](https://drive.google.com/file/d/174-Og5OxXiIkevPZvvlWuW1lHNIxp6Fa/view?usp=sharing).

## Configuration

Before using the app, you need to obtain an API key from [News API](https://newsapi.org/) and replace the `apiKey` value in the `NewsViewModel.kt` file.

1. Sign up for a News API account [here](https://newsapi.org/account/register).
2. After signing up, you'll receive an API key that you can use to access the News API.
3. Open the `NewsViewModel.kt` file in the app and replace the `apiKey` value with your own API key.

```kotlin
class NewsViewModel : ViewModel() {
   //...
    private val apiKey = "YOUR_API_KEY"
   //...
}
