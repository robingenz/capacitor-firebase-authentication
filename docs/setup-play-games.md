# Set up authentication using Play Games Sign-In

## Android

1.  Add the Google Play services SDK to your module (app-level) Gradle file (usually `app/build.gradle`):
      ```diff
      dependencies {
      +    // Declare the dependency for the Google Play services library and specify its version
      +    implementation "com.google.android.gms:play-services-auth:19.0.0"
      }
      ```
1. See [Before you begin](https://firebase.google.com/docs/auth/android/play-games#before_you_begin) and follow the instructions to configure sign-in with Play Games correctly.  
   **Attention**: Skip step 2 in [Set up your Android project](https://firebase.google.com/docs/auth/android/play-games#set_up_your_android_project). The dependency for the Firebase Authentication Android library is already declared by the plugin.

## iOS

🚧 Currently not supported.

## Web

🚧 Currently not supported.
