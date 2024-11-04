# Cat Logic App

## Overview

Cat Logic is an Android application designed to help pet owners keep track of their cat's activities, including eating, playing, and health records. The app allows users to log their cat's meals, playtime, and health metrics, providing insights into their pet's daily habits.

## Features

- **Activity Logging**: Easily record and manage eating, playing, and health activities.
- **Data Visualization**: View logs in a structured format.
- **Search Functionality**: Quickly find specific entries with a search bar.
- **User-friendly Interface**: Built using Jetpack Compose for a modern and intuitive user experience.
- **Async API Calls**: Efficiently fetch and update data using Retrofit and Kotlin Coroutines.

## Technology Stack

- **Android**: Kotlin, Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **Networking**: Retrofit, OkHttp
- **Backend**: Django REST Framework hosted on Heroku
- **Database**: PostgreSQL

## Getting Started

To run this project locally, follow these steps:

### Prerequisites

- Android Studio (latest version)
- Android SDK
- Internet connection (for API access)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/cat-logic.git
   cd cat-logic
2. Open the project in Android Studio.

3. Sync the Gradle files to download dependencies.

4. Update the ApiService in the data package with your backend URL.

5. Run the app on an Android device or emulator.

### Usage
1. Launch the app.
2. Select an activity type (Eating, Playing, Health) from the main menu.
3. Use the search bar to filter records.
4. Click on the "Add" button to log new activities.
5. Input the required details, select a date, and save.
