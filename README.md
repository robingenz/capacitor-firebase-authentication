<p align="center"><br><img src="https://user-images.githubusercontent.com/236501/85893648-1c92e880-b7a8-11ea-926d-95355b8175c7.png" width="128" height="128" /></p>
<h3 align="center">Firebase Authentication</h3>
<p align="center"><strong><code>@robingenz/capacitor-firebase-authentication</code></strong></p>
<p align="center">
  Capacitor plugin for Firebase Authentication.
</p>

<p align="center">
  <img src="https://img.shields.io/maintenance/yes/2021?style=flat-square" />
  <a href="https://github.com/robingenz/capacitor-firebase-authentication/actions?query=workflow%3A%22CI%22"><img src="https://img.shields.io/github/workflow/status/robingenz/capacitor-firebase-authentication/CI/main?style=flat-square" /></a>
  <a href="https://www.npmjs.com/package/@robingenz/capacitor-firebase-authentication"><img src="https://img.shields.io/npm/l/@robingenz/capacitor-firebase-authentication?style=flat-square" /></a>
<br>
  <a href="https://www.npmjs.com/package/@robingenz/capacitor-firebase-authentication"><img src="https://img.shields.io/npm/dw/@robingenz/capacitor-firebase-authentication?style=flat-square" /></a>
  <a href="https://www.npmjs.com/package/@robingenz/capacitor-firebase-authentication"><img src="https://img.shields.io/npm/v/@robingenz/capacitor-firebase-authentication?style=flat-square" /></a>
<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->
<a href="#contributors-"><img src="https://img.shields.io/badge/all%20contributors-1-orange?style=flat-square" /></a>
<!-- ALL-CONTRIBUTORS-BADGE:END -->
</p>

## Maintainers

| Maintainer | GitHub                                    | Social                                        |
| ---------- | ----------------------------------------- | --------------------------------------------- |
| Robin Genz | [robingenz](https://github.com/robingenz) | [@robin_genz](https://twitter.com/robin_genz) |

## Installation

```
npm install @robingenz/capacitor-firebase-authentication
npx cap sync
```

Add Firebase to your project if you haven't already ([Android](https://firebase.google.com/docs/android/setup) / [iOS](https://firebase.google.com/docs/ios/setup)).

On **iOS**, verify that this function is included in your app's `AppDelegate.swift`:

```swift
func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool {
  return ApplicationDelegateProxy.shared.application(app, open: url, options: options)
}
```

The further installation steps depend on the selected authentication method:

- [Apple Sign-In](docs/setup-apple.md)
- [Google Sign-In](docs/setup-google.md)
- [Microsoft Sign-In](docs/setup-microsoft.md)

## Configuration

No configuration required for this plugin.

<!-- ## Demo

A working example can be found here: [robingenz/capacitor-plugin-demo](https://github.com/robingenz/capacitor-plugin-demo) -->

## Usage

```typescript
import { Plugins } from '@capacitor/core';
const { FirebaseAuthentication } = Plugins;

const signInWithGoogle = async () => {
  const result = await FirebaseAuthentication.signInWithGoogle();
};

const signInWithApple = async () => {
  const result = await FirebaseAuthentication.signInWithApple();
};

const signInWithMicrosoft = async () => {
  const result = await FirebaseAuthentication.signInWithMicrosoft();
};

const signOut = async () => {
  await FirebaseAuthentication.signOut();
};
```

## API

<docgen-index>

* [`signInWithApple()`](#signinwithapple)
* [`signInWithGoogle()`](#signinwithgoogle)
* [`signInWithMicrosoft()`](#signinwithmicrosoft)
* [`signOut()`](#signout)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### signInWithApple()

```typescript
signInWithApple() => Promise<SignInResult>
```

Starts the Apple sign-in flow.

Only available for iOS.

**Returns:** <code>Promise&lt;<a href="#signinresult">SignInResult</a>&gt;</code>

--------------------


### signInWithGoogle()

```typescript
signInWithGoogle() => Promise<SignInResult>
```

Starts the Google sign-in flow.

Only available for Android and iOS.

**Returns:** <code>Promise&lt;<a href="#signinresult">SignInResult</a>&gt;</code>

--------------------


### signInWithMicrosoft()

```typescript
signInWithMicrosoft() => Promise<SignInResult>
```

Starts the Microsoft sign-in flow.

Only available for Android and iOS.

**Returns:** <code>Promise&lt;<a href="#signinresult">SignInResult</a>&gt;</code>

--------------------


### signOut()

```typescript
signOut() => Promise<void>
```

Starts the sign-out flow.

Only available for Android and iOS.

--------------------


### Interfaces


#### SignInResult

| Prop              | Type                | Description                |
| ----------------- | ------------------- | -------------------------- |
| **`idToken`**     | <code>string</code> | Firebase Auth ID Token.    |
| **`uid`**         | <code>string</code> | Firebase user ID.          |
| **`email`**       | <code>string</code> | Email address of the user. |
| **`displayName`** | <code>string</code> | Display name of the user.  |

</docgen-api>

## Changelog

See [CHANGELOG.md](CHANGELOG.md).

## License

See [LICENSE](LICENSE).
