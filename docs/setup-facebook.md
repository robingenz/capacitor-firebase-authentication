# Set up authentication using Facebook Sign-In

## Android

1. See [Before you begin](https://firebase.google.com/docs/auth/android/facebook-login#before_you_begin) and follow the instructions to configure sign-in with Facebook correctly.  
   **Attention**: Skip step 4. The dependency for the Firebase Authentication Android library is already declared by the plugin.
1. Add the following `string` elements to `android/app/src/main/res/values/strings.xml` after the `resources` element:

```xml
<string name="facebook_app_id">[APP_ID]</string>
<string name="fb_login_protocol_scheme">fb[APP_ID]</string>
```

`[APP_ID]` must be replaced with your Facebook app ID.

1. Add the following elements to `android/app/src/main/AndroidManifest.xml` after the `application` element:

```xml
<meta-data
   android:name="com.facebook.sdk.ApplicationId"
   android:value="@string/facebook_app_id"/>

<activity
   android:name="com.facebook.FacebookActivity"
   android:configChanges= "keyboard|keyboardHidden|screenLayout|screenSize|orientation"
   android:label="@string/app_name" />

<activity
   android:name="com.facebook.CustomTabActivity"
   android:exported="true">
   <intent-filter>
      <action android:name="android.intent.action.VIEW" />
      <category android:name="android.intent.category.DEFAULT" />
      <category android:name="android.intent.category.BROWSABLE" />
      <data android:scheme="@string/fb_login_protocol_scheme" />
   </intent-filter>
</activity>
```

## iOS

1. See [Before you begin](https://firebase.google.com/docs/auth/ios/facebook-login#before_you_begin) and follow the instructions to configure and enable sign-in with Facebook correctly.  
   **Attention**: Skip step 2. The `Firebase/Auth` pod is already added by the plugin.

## Web

🚧 Currently not supported.
