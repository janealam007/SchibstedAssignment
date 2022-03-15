# SchibstedAssignment
Schibsted / Tori.fi Android Developer assignment

## Covered Topics
1. Native application with Kotlin 

2. Android Clean Architecture

3. Dependency injection (Hilt)

4. For threading, use (Coroutines/Flows)

5. For the presentation layer(MVVM)

6. Api calling library(Retrofit)

7. Image loading library(Glide)

8. Xml databinding, ViewModel(Jetpack)

9. Android Unit-Test(Google truth && Junit-Test)

## Installation
plugins(app/gradle)

```bash
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-kapt'
    id 'dagger.hilt.android.plugin'
    id 'kotlin-parcelize'
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
```

Add dataBinding inside the android(app/gradle)
```bash
    buildFeatures{
        dataBinding=true
    }
```

Dependency library list(app/gradle)
```bash
    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.4.1'
    implementation 'com.google.android.material:material:1.5.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.3'

    testImplementation 'junit:junit:4.13.2'
    testImplementation "com.google.truth:truth:1.1"
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation "com.google.truth:truth:1.1"
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'

    // Retrofit
    implementation 'com.squareup.okhttp3:okhttp:5.0.0-alpha.2'
    implementation 'com.squareup.retrofit2:converter-scalars:2.9.0'

    implementation "com.squareup.retrofit2:retrofit:2.9.0"
    implementation "com.squareup.retrofit2:converter-gson:2.9.0"
    implementation "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2"
    implementation "com.google.code.gson:gson:2.8.6"

    // Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2'

    // Coroutine Lifecycle Scopes
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"

    // Glide
    implementation 'com.github.bumptech.glide:glide:4.12.0'
    kapt 'com.github.bumptech.glide:compiler:4.12.0'

    // Activity KTX for viewModels()
    implementation "androidx.activity:activity-ktx:1.4.0"

    //Dagger - Hilt
    implementation "com.google.dagger:hilt-android:2.39.1"
    kapt "com.google.dagger:hilt-android-compiler:2.39.1"
    kapt "androidx.hilt:hilt-compiler:1.0.0"

```
Add resolutionStrategy for dependency library hilt
```bash
    resolutionStrategy {
        eachPlugin {
            if( requested.id.id == 'dagger.hilt.android.plugin') {
                useModule("com.google.dagger:hilt-android-gradle-plugin:2.39.1")
            }
        }
    }
```

Kotlin data class generate from json
```bash
install plugin for Json to Kotlin class
Click Android studio-> Preferences-> plugins -> market place
search by Json to Kotlin class and install
```

## Add Manifest internet permission 

```bash
<uses-permission android:name="android.permission.INTERNET"/>

# Do not forget to add inside the activity tag
android:supportsRtl="true"

```


## Api calling 

```bash
#API link
https://api.flickr.com/services/rest/?method=flickr.photos.search&format=json&nojsoncallback=1&text=dogs&api_key=b59eaa142fbb03d0ba6c93882fd62e30

#Apk link
https://drive.google.com/file/d/18IHXIEyvUXptJ3zf2TGDJk1wzWQHffNP/view?usp=sharing

#App Screen shorts
https://drive.google.com/file/d/1Aa3DmwbjLQ0WdO-HFuO0wx7swE6I8pO1/view?usp=sharing
![Screenshot](screenshot_1.png)

https://drive.google.com/file/d/1EYOnxRsRz4sbetL8rUuapcqS117s_Ip-/view?usp=sharing
![Screenshot](screenshot_2.png)

```


## Further development 

```bash
## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
