# Changelog

All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

### [0.4.1](https://github.com/robingenz/capacitor-firebase-authentication/compare/v0.4.0...v0.4.1) (2022-01-26)


### Bug Fixes

* inline source code in esm map files ([680165b](https://github.com/robingenz/capacitor-firebase-authentication/commit/680165b484795eb29f2fc09eb23d40bd0938bf3a))

## [0.4.0](https://github.com/robingenz/capacitor-firebase-authentication/compare/v0.3.12...v0.4.0) (2021-12-05)


### ⚠ BREAKING CHANGES

* Exclude optional third-party SDKs by default. See `BREAKING.md` file.

### build

* exclude optional third-party SDKs by default ([#110](https://github.com/robingenz/capacitor-firebase-authentication/issues/110)) ([31fcc65](https://github.com/robingenz/capacitor-firebase-authentication/commit/31fcc65535aeabbd9420779c2dc74412057cc337))

### [0.3.12](https://github.com/robingenz/capacitor-firebase-authentication/compare/v0.3.11...v0.3.12) (2021-11-13)


### Features

* add `signInWithCustomToken` ([#100](https://github.com/robingenz/capacitor-firebase-authentication/issues/100)) ([ccf7481](https://github.com/robingenz/capacitor-firebase-authentication/commit/ccf7481056242f3886abfa7a3c90574a8bdce079))

### [0.3.11](https://github.com/robingenz/capacitor-firebase-authentication/compare/v0.3.10...v0.3.11) (2021-09-15)


### Features

* **android:** support Play Games sign-in ([#83](https://github.com/robingenz/capacitor-firebase-authentication/issues/83)) ([d086a0e](https://github.com/robingenz/capacitor-firebase-authentication/commit/d086a0ee5a1259a93155b65b7c378377ae38ab9a))

### [0.3.10](https://github.com/robingenz/capacitor-firebase-authentication/compare/v0.3.9...v0.3.10) (2021-09-08)


### Bug Fixes

* catch errors when initializing the Facebook SDK ([#84](https://github.com/robingenz/capacitor-firebase-authentication/issues/84)) ([f6e77da](https://github.com/robingenz/capacitor-firebase-authentication/commit/f6e77da346ddccfbab16f0bb96883d25fe9872c6))

### [0.3.9](https://github.com/robingenz/capacitor-firebase-authentication/compare/v0.3.8...v0.3.9) (2021-08-28)


### Features

* add web support ([#74](https://github.com/robingenz/capacitor-firebase-authentication/issues/74)) ([665ebe3](https://github.com/robingenz/capacitor-firebase-authentication/commit/665ebe3f853fe7c885a3a7e61941033f28f1aba2))

### [0.3.8](https://github.com/robingenz/capacitor-firebase-authentication/compare/v0.3.7...v0.3.8) (2021-08-28)


### Features

* add `onAuthStateChanged` listener ([#71](https://github.com/robingenz/capacitor-firebase-authentication/issues/71)) ([b556dc0](https://github.com/robingenz/capacitor-firebase-authentication/commit/b556dc07a67919b4567a516f5a255bdbb2fd340c))


### Bug Fixes

* **ios:** `signInWithPhoneNumber` is not implemented ([#73](https://github.com/robingenz/capacitor-firebase-authentication/issues/73)) ([659328b](https://github.com/robingenz/capacitor-firebase-authentication/commit/659328b7ce664509462923242dbfcc29d8071e28))

### [0.3.7](https://github.com/robingenz/capacitor-firebase-authentication/compare/v0.3.6...v0.3.7) (2021-08-26)


### Bug Fixes

* return `idToken` on sign in ([#67](https://github.com/robingenz/capacitor-firebase-authentication/issues/67)) ([cb2568c](https://github.com/robingenz/capacitor-firebase-authentication/commit/cb2568c8cc9ae68c0e3d46cf01f1f67b6dd89171))

### [0.3.6](https://github.com/robingenz/capacitor-firebase-authentication/compare/v0.3.5...v0.3.6) (2021-08-23)


### Features

* support Phone Number Sign-In ([#63](https://github.com/robingenz/capacitor-firebase-authentication/issues/63)) ([2ea8317](https://github.com/robingenz/capacitor-firebase-authentication/commit/2ea831727baafac41845ad0ebb9ef184605126bd))

### [0.3.5](https://github.com/robingenz/capacitor-firebase-authentication/compare/v0.3.4...v0.3.5) (2021-08-22)


### Features

* add `providers` config option ([#62](https://github.com/robingenz/capacitor-firebase-authentication/issues/62)) ([7b62469](https://github.com/robingenz/capacitor-firebase-authentication/commit/7b624697a0f190b7ea9cfdbf02623b9e7cdf60c1))
* add Sign-In with Facebook ([#61](https://github.com/robingenz/capacitor-firebase-authentication/issues/61)) ([998aa34](https://github.com/robingenz/capacitor-firebase-authentication/commit/998aa3417c5d0255f71686bafbf6f1c0360c152d))

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
