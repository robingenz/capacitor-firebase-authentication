# Breaking Changes

This is a comprehensive list of the breaking changes introduced in the major version releases of Capacitor Firebase Authentication plugin.

## Versions

- [Version 0.4.x](#version-04x)

## Version 0.4.x

- [Google](#google)
- [Facebook](#facebook)
- [Play Games](#play-games)

### Google

On **Android**, add the following project variable to your `variables.gradle` file (usually `android/build.gradle`):

```diff
ext {
+    rgcfaIncludeGoogle = true
}
```

On **iOS**, add the `RobingenzCapacitorFirebaseAuthentication/Google` pod to your `Podfile` (usually `ios/App/Podfile`):

```diff
target 'App' do
capacitor_pods
# Add your Pods here
+  pod 'RobingenzCapacitorFirebaseAuthentication/Google', :path => '../../node_modules/@robingenz/capacitor-firebase-authentication'
end
```
And then run `npx cap sync`.

### Facebook

On **Android**, add the following project variable to your `variables.gradle` file (usually `android/build.gradle`):

```diff
ext {
+    rgcfaIncludeFacebook = true
}
```

On **iOS**, add the `RobingenzCapacitorFirebaseAuthentication/Facebook` pod to your `Podfile` (usually `ios/App/Podfile`):

```diff
target 'App' do
capacitor_pods
# Add your Pods here
+  pod 'RobingenzCapacitorFirebaseAuthentication/Facebook', :path => '../../node_modules/@robingenz/capacitor-firebase-authentication'
end
```
And then run `npx cap sync`.

### Play Games

On **Android**, add the following project variable to your `variables.gradle` file (usually `android/build.gradle`):

```diff
ext {
+    rgcfaIncludeGoogle = true
}
```
