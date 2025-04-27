SafeAI - Android App
SafeAI
SafeAI is an Android app designed for tracking and reporting safety incidents. It allows users to filter
incidents by severity and submit new incidents. The app features an intuitive, interactive interface for
managing safety data.
Features:
- Report safety incidents with a title, description, and severity.
- View detailed information about each incident.
- Filter incidents by severity (Low, Medium, High).
- User-friendly interface with modern design principles.
Prerequisites:
To run this project locally, you'll need the following:
- Android Studio (latest version)
 - Download Android Studio (https://developer.android.com/studio)
- Java JDK (Java Development Kit)
 - Download JDK (https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- Git (to clone the repository)
 - Download Git (https://git-scm.com/)
Installation Instructions:
Step 1: Clone the Repository
First, clone the repository to your local machine using Git. Open a terminal or command prompt and
run the following command:
git clone https://github.com/ManishTayde9370/SafeAi.git
Step 2: Open the Project in Android Studio
1. Launch Android Studio.
2. Select Open an existing project.
3. Navigate to the folder where the repository was cloned and select the project folder (SafeAi).
Step 3: Install Dependencies
- Android Studio will automatically detect the dependencies and sync the project.
- If not, click on the Sync Now button that appears in the top-right corner to sync the project with
Gradle.
Step 4: Set Up an Emulator or Device
- Option 1: Use an Android Emulator.
 - In Android Studio, click on AVD Manager (Android Virtual Device Manager) to create a new
emulator.
- Option 2: Connect a physical Android device to your computer.
 - Ensure that USB Debugging is enabled on your device.
Step 5: Run the Project
- Click on the Run button (green triangle) in Android Studio, or press Shift + F10 to build and run the
app on your emulator or connected device.
Language & Frameworks:
- Programming Language: Kotlin
- Android Framework: Android SDK (API level 21 or higher)
Design Decisions & Challenges:
- UI Design: The app uses a RecyclerView to display a list of incidents. Each incident includes a title,
description, and severity, allowing users to filter incidents based on severity (Low, Medium, High).
- Data Persistence: Currently, the app does not save incidents permanently. You can extend this by
implementing Room Database or SharedPreferences to persist data locally for future use.
- Handling Filters: Filtering incidents based on severity was a challenge to implement dynamically. It
is now handled by filtering the data within the RecyclerView adapter.
Contributing:
Feel free to contribute to this project by forking the repository, reporting bugs, or submitting pull
requests.
License:
This project is open-source and available under the MIT License.
If you encounter any issues or have suggestions for improvement, feel free to open an issue or
create a pull request.
