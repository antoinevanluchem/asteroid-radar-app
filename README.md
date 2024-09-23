# Code Review 

## Reviewer Note
> Hello Dear Student!
>
> Your code is very clean, clear, and professional, so great work on this!
> 
> The app behavior is very good, the list of asteroids shows on the main screen very quickly when it starts. 👍
> 
> The API key is very sensitive information, great job hiding it so well. 👍
> 
> Awesome work displaying the asteroids in the list so nicely, but maybe next time that you need it, you could use the CardView(opens in a new tab) widget that will help you design these elements easily.
> 
> You must be proud of yourself since not many students pass this project on the first try. 💪
> 
> Now, you can continue with the lessons and overall, have fun while coding!

## RecyclerView 
✅ Create and reuse views in an Android app using `RecyclerView`
> The list of asteroids is correctly displayed on the main screen by using RecyclerView, an improved version of the old ListView. RecyclerView recycles the views created at the beginning when the views go out of scope, and reuses them when needed to make it seem as if the views were never offloaded and were virtually present outside the window.
> 
> This makes a better performance of the list of data in your app, overall when the list contains many elements.
> When the user clicks on any item in the list, the app opens the detail view of the selected element.

## Network 
✅ Build an application that connects to an internet server to retrieve and display live data
> Only the strictly needed permissions have been added in the Manifest file.
> The NASA API has been used to download the asteroids. Retrofit and Moshi have been correctly used for retrieving and parsing the data from the API.
>

✅ Use networking an dimage best practices to fetch data and images
> As seen in the image above, the image of the day is correctly displayed on the main screen.
>

## Database and Caching 
✅ Create a database to store and access user data over time
> The asteroids are correctly saved in the database as the Android Studio database inspector shows in this image.
> The app filters asteroids from the past. The user correctly can choose from different options.
>

✅ Implement offline caching to allow users to interact with online content offline 
> The app downloads the next 7 days asteroids and saves them in the database once a day using workManager with requirements of internet connection and device plugged in.
>
A You can find [at this link](https://developer.android.com/topic/libraries/architecture/workmanager/how-to/integration-testing) from the Android Official Documentation, instructions on how to test the WorkManager.
>
> The app can display saved asteroids from the database even if an internet connection is not available.
>

## Accessibility 
✅ Add talkback and push-button navigation to make an Android app accessible
> The talkback mode works correctly. Great job adding good descriptions in all the needed places.
>
> Please, take a look [at this link](https://developer.android.com/guide/topics/ui/accessibility/apps) for more details.

# Project Title

Asteroid Radar

## Getting Started

Asteroid Radar is an app to view the asteroids detected by NASA that pass near Earth, you can view all the detected asteroids in a period of time, their data (Size, velocity, distance to Earth) and if they are potentially hazardous.

The app is consists of two screens: A Main screen with a list of all the detected asteroids and a Details screen that is going to display the data of that asteroid once it´s selected in the Main screen list. The main screen will also show the NASA image of the day to make the app more striking.

This kind of app is one of the most usual in the real world, what you will learn by doing this are some of the most fundamental skills you need to know to work as a professional Android developer, as fetching data from the internet, saving data to a database, and display the data in a clear, clear, compelling UI.

### Screenshots

![Screenshot 1](starter/screenshots/screen_1.png)
![Screenshot 2](starter/screenshots/screen_2.png)
![Screenshot 3](starter/screenshots/screen_3.png)
![Screenshot 4](starter/screenshots/screen_4.png)

### Dependencies

```
implementation fileTree(dir: 'libs', include: ['*.jar'])
implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
implementation 'androidx.appcompat:appcompat:1.1.0'
implementation 'androidx.core:core-ktx:1.2.0'
implementation 'androidx.constraintlayout:constraintlayout:1.1.3'

implementation "androidx.lifecycle:lifecycle-extensions:2.2.0"

implementation "android.arch.navigation:navigation-fragment-ktx:1.0.0"
implementation "android.arch.navigation:navigation-ui-ktx:1.0.0"

// Download and parse data
implementation "com.squareup.moshi:moshi:1.8.0"
implementation "com.squareup.moshi:moshi-kotlin:1.8.0"
implementation "com.squareup.retrofit2:retrofit:2.6.2"
implementation "com.squareup.retrofit2:converter-moshi:2.5.0"
implementation 'com.squareup.retrofit2:converter-scalars:2.5.0'

// Kotlin coroutines
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0"
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.0"
implementation "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"

implementation "androidx.recyclerview:recyclerview:1.1.0"

// Image downloader
implementation 'com.squareup.picasso:picasso:2.5.2'

implementation "androidx.room:room-runtime:2.2.3"
kapt "androidx.room:room-compiler:2.2.3"

implementation "android.arch.work:work-runtime-ktx:1.0.1"

testImplementation 'junit:junit:4.12'
androidTestImplementation 'androidx.test.ext:junit:1.1.1'
androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
```

### Installation

To get the project running on your local machine, you need to follow these steps:

**Step 1: Clone the repo**

Use this to clone it to your local machine:
```bash
git clone https://github.com/udacity/REPOSITORY_NAME.git
```

**Step 2: Check out the ‘master’ branch**

This branch is going to let you start working with it. The command to check out a branch would be:

```bash
git checkout master
```

**Step 3: Run the project and check that it compiles correctly**

Open the project in Android Studio and click the Run ‘app’ button, check that it runs correctly and you can see the app in your device or emulator.

## Testing

Explain the steps needed to run any automated tests

### Break Down Tests

Explain what each test does and why

```
Examples here
```
## Project Instructions

You will be provided with a starter code, which includes the necessary dependencies and plugins that you have been using along the courses and that you are going to need to complete this project. 

The most important dependencies we are using are:
- Retrofit to download the data from the Internet.
- Moshi to convert the JSON data we are downloading to usable data in form of custom classes.
- Glide to download and cache images.
- RecyclerView to display the asteroids in a list.

We recommend you following the guidelines seen in the courses, as well as using the components from the Jetpack library:
- ViewModel
- Room
- LiveData
- Data Binding
- Navigation

Android Studio could display a message to update Gradle plugin, or another thing like Kotlin, although it is recommended to have the last versions, it could be you have to do other things in order to make it work.

The application you will build must:
- Include Main screen with a list of clickable asteroids as seen in the provided design.
- Include a Details screen that displays the selected asteroid data once it’s clicked in the Main screen as seen in the provided design. The images in the details screen are going to be provided here, an image for a potentially hazardous asteroids and another one for the non potentially hazardous ones.
- Download and parse data from the NASA NeoWS (Near Earth Object Web Service) API.
- Save the selected asteroid data in the database using a button in details screen.
- Once you save an asteroid in the database, you should be able to display the list of asteroids from web or the database in the main screen top menu.
- Be able to cache the asteroids data by using a worker, so it downloads and saves week asteroids in background when device is charging and wifi is enabled.
- App works in multiple screen sizes and orientations, also it provides talk back and push button navigation.


## Built With

To build this project you are going to use the NASA NeoWS (Near Earth Object Web Service) API, which you can find here.
https://api.nasa.gov/

You will need an API Key which is provided for you in that same link, just fill the fields in the form and click Signup.

## License


