# Changelog

All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

### [0.3.4](https://github.com/robingenz/capacitor-firebase-authentication/compare/v0.3.3...v0.3.4) (2021-08-12)


### Features

* support custom provider parameters ([#58](https://github.com/robingenz/capacitor-firebase-authentication/issues/58)) ([5d25cc5](https://github.com/robingenz/capacitor-firebase-authentication/commit/5d25cc5b4e7b5fe6475e70c2bea4fae8d5ca6e34))

### [0.3.3](https://github.com/robingenz/capacitor-firebase-authentication/compare/v0.3.2...v0.3.3) (2021-07-30)


### Features

* implement `skipNativeAuth` config option ([#54](https://github.com/robingenz/capacitor-firebase-authentication/issues/54)) ([9bdf53b](https://github.com/robingenz/capacitor-firebase-authentication/commit/9bdf53b89b210461569e903e5131fef961ae3140))
* **ios:** return `nonce` on sign in  ([#52](https://github.com/robingenz/capacitor-firebase-authentication/issues/52)) ([a0455d8](https://github.com/robingenz/capacitor-firebase-authentication/commit/a0455d828865b4f5227d3ef0c1c93c6afc5c22ec))
* return `AuthCredential` on sign in ([#51](https://github.com/robingenz/capacitor-firebase-authentication/issues/51)) ([adb8b60](https://github.com/robingenz/capacitor-firebase-authentication/commit/adb8b60726a6e50aac86c3b09d75cc08771f396c))
* **ios:** update `GoogleSignIn` pod to `6.0.0` ([#45](https://github.com/robingenz/capacitor-firebase-authentication/issues/45)) ([9e5d169](https://github.com/robingenz/capacitor-firebase-authentication/commit/9e5d16902da8146b91e9b82a8dc10230d0b0e92b))

### [0.3.2](https://github.com/robingenz/capacitor-firebase-authentication/compare/v0.3.1...v0.3.2) (2021-07-14)


### Bug Fixes

* cannot call value of non-function type 'GIDSignIn' ([#40](https://github.com/robingenz/capacitor-firebase-authentication/issues/40)) ([b9b6cfc](https://github.com/robingenz/capacitor-firebase-authentication/commit/b9b6cfc34db2abe9c5aa94a96df1baf3e6c86643))

### [0.3.1](https://github.com/robingenz/capacitor-firebase-authentication/compare/v0.3.0...v0.3.1) (2021-07-05)


### Bug Fixes

* **ios:** AuthCredentialCallback never fired ([#38](https://github.com/robingenz/capacitor-firebase-authentication/issues/38)) ([8615f0a](https://github.com/robingenz/capacitor-firebase-authentication/commit/8615f0a8fa081f37e155e3d011b2b6b3df5a5757))

## [0.3.0](https://github.com/robingenz/capacitor-firebase-authentication/compare/v0.2.0...v0.3.0) (2021-07-04)


### ⚠ BREAKING CHANGES

* `signIn` methods now return `SignInResult`.

### Features

* add methods `useAppLanguage` and `setLanguageCode` ([#36](https://github.com/robingenz/capacitor-firebase-authentication/issues/36)) ([5a27bdf](https://github.com/robingenz/capacitor-firebase-authentication/commit/5a27bdf1edf2e43b63ec04201daece6718d053f8))
* implement `SignInResult` ([#35](https://github.com/robingenz/capacitor-firebase-authentication/issues/35)) ([df92bdb](https://github.com/robingenz/capacitor-firebase-authentication/commit/df92bdbdb8b8dab50f056c7a63cda0f5c1c9a1b5))


### Bug Fixes

* **android:** `getIdToken` error `Task is not yet complete` ([71546df](https://github.com/robingenz/capacitor-firebase-authentication/commit/71546dfd464f483671d2ba2eef72f7fe3b54c28d))

## [0.2.0](https://github.com/robingenz/capacitor-firebase-authentication/compare/v0.1.0...v0.2.0) (2021-07-03)


### ⚠ BREAKING CHANGES

* `signInWithApple`, `signInWithGoogle` and `signInWithMicrosoft` now return `void`.

### Features

* add methods `signInWithGithub`, `signInWithTwitter` and `signInWithYahoo` ([#28](https://github.com/robingenz/capacitor-firebase-authentication/issues/28)) ([f190390](https://github.com/robingenz/capacitor-firebase-authentication/commit/f1903904b050dddaaabd0456e68ae1b8f45c96e8))
* **android:** add method `signInWithApple` ([#22](https://github.com/robingenz/capacitor-firebase-authentication/issues/22)) ([4b959c3](https://github.com/robingenz/capacitor-firebase-authentication/commit/4b959c3c99d802fddfcf97eb0c3b80b24cb9d7be))
* add methods `getCurrentUser` and `getIdToken` ([#19](https://github.com/robingenz/capacitor-firebase-authentication/issues/19)) ([902cf97](https://github.com/robingenz/capacitor-firebase-authentication/commit/902cf9761e10bb2a4f7edb1764d42f748d7076fe))

## 0.1.0 (2021-06-10)

Initial release 🎉
