<p align="center"><br><img src="https://user-images.githubusercontent.com/236501/85893648-1c92e880-b7a8-11ea-926d-95355b8175c7.png" width="128" height="128" /></p>
<h3 align="center">Firebase Authentication</h3>
<p align="center"><strong><code>@robingenz/capacitor-firebase-authentication</code></strong></p>
<p align="center">
  Capacitor plugin for Firebase Authentication.
</p>

<p align="center">
  <img src="https://img.shields.io/maintenance/yes/2022?style=flat-square" />
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
npm install @robingenz/capacitor-firebase-authentication firebase
npx cap sync
```

Add Firebase to your project if you haven't already ([Android](https://firebase.google.com/docs/android/setup) / [iOS](https://firebase.google.com/docs/ios/setup) / [Web](https://firebase.google.com/docs/web/setup)).

On **iOS**, verify that this function is included in your app's `AppDelegate.swift`:

```swift
func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool {
  return ApplicationDelegateProxy.shared.application(app, open: url, options: options)
}
```

The further installation steps depend on the selected authentication method:

- [Apple Sign-In](docs/setup-apple.md)
- [Facebook Sign-In](docs/setup-facebook.md)
- [GitHub Sign-In](docs/setup-github.md)
- [Google Sign-In](docs/setup-google.md)
- [Microsoft Sign-In](docs/setup-microsoft.md)
- [Play Games Sign-In](docs/setup-play-games.md)
- [Twitter Sign-In](docs/setup-twitter.md)
- [Yahoo Sign-In](docs/setup-yahoo.md)
- [Phone Number Sign-In](docs/setup-phone.md)
- [Custom Token Sign-In](docs/custom-token.md)

**Attention**: Please note that this plugin uses third-party SDKs to offer native sign-in.
These SDKs can initialize on their own and collect various data.
[Here](docs/third-party-sdks.md) you can find more information.

## Configuration

<docgen-config>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

These configuration values are available:

| Prop                 | Type                  | Description                                                                                                                                                                                                                                                                                            | Default                                                                                                                                              |
| -------------------- | --------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ---------------------------------------------------------------------------------------------------------------------------------------------------- |
| **`skipNativeAuth`** | <code>boolean</code>  | Configure whether the plugin should skip the native authentication. Only needed if you want to use the Firebase JavaScript SDK. Only available for Android and iOS.                                                                                                                                    | <code>false</code>                                                                                                                                   |
| **`providers`**      | <code>string[]</code> | Configure which providers you want to use so that only the providers you need are fully initialized. If you do not configure any providers, they will be all initialized. Please note that this does not prevent the automatic initialization of third-party SDKs. Only available for Android and iOS. | <code>["apple.com", "facebook.com", "github.com", "google.com", "microsoft.com", "playgames.google.com", "twitter.com", "yahoo.com", "phone"]</code> |

### Examples

In `capacitor.config.json`:

```json
{
  "plugins": {
    "FirebaseAuthentication": {
      "skipNativeAuth": false,
      "providers": ["apple.com", "google.com"]
    }
  }
}
```

In `capacitor.config.ts`:

```ts
/// <reference types="@capacitor/firebase-authentication" />

import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  plugins: {
    FirebaseAuthentication: {
      skipNativeAuth: false,
      providers: ["apple.com", "google.com"],
    },
  },
};

export default config;
```

</docgen-config>

## Demo

A working example can be found here: [robingenz/capacitor-firebase-authentication-demo](https://github.com/robingenz/capacitor-firebase-authentication-demo)

## Usage

```typescript
import { FirebaseAuthentication } from '@robingenz/capacitor-firebase-authentication';

const getCurrentUser = async () => {
  const result = await FirebaseAuthentication.getCurrentUser();
  return result.user;
};

const getIdToken = async () => {
  const result = await FirebaseAuthentication.getIdToken();
  return result.token;
};

const setLanguageCode = async () => {
  await FirebaseAuthentication.setLanguageCode({ languageCode: 'en-US' });
};

const signInWithApple = async () => {
  await FirebaseAuthentication.signInWithApple();
};

const signInWithFacebook = async () => {
  await FirebaseAuthentication.signInWithFacebook();
};

const signInWithGithub = async () => {
  await FirebaseAuthentication.signInWithGithub();
};

const signInWithGoogle = async () => {
  await FirebaseAuthentication.signInWithGoogle();
};

const signInWithMicrosoft = async () => {
  await FirebaseAuthentication.signInWithMicrosoft();
};

const signInWithPlayGames = async () => {
  await FirebaseAuthentication.signInWithPlayGames();
};

const signInWithPhoneNumber = async () => {
  const { verificationId } = await FirebaseAuthentication.signInWithPhoneNumber(
    {
      phoneNumber: '123456789',
    },
  );
  const verificationCode = window.prompt(
    'Please enter the verification code that was sent to your mobile device.',
  );
  await FirebaseAuthentication.signInWithPhoneNumber({
    verificationId,
    verificationCode,
  });
};

const signInWithTwitter = async () => {
  await FirebaseAuthentication.signInWithTwitter();
};

const signInWithYahoo = async () => {
  await FirebaseAuthentication.signInWithYahoo();
};

const signOut = async () => {
  await FirebaseAuthentication.signOut();
};

const useAppLanguage = async () => {
  await FirebaseAuthentication.useAppLanguage();
};
```

## API

<docgen-index>

* [`getCurrentUser()`](#getcurrentuser)
* [`getIdToken(...)`](#getidtoken)
* [`setLanguageCode(...)`](#setlanguagecode)
* [`signInWithApple(...)`](#signinwithapple)
* [`signInWithFacebook(...)`](#signinwithfacebook)
* [`signInWithGithub(...)`](#signinwithgithub)
* [`signInWithGoogle(...)`](#signinwithgoogle)
* [`signInWithMicrosoft(...)`](#signinwithmicrosoft)
* [`signInWithPlayGames(...)`](#signinwithplaygames)
* [`signInWithTwitter(...)`](#signinwithtwitter)
* [`signInWithYahoo(...)`](#signinwithyahoo)
* [`signInWithPhoneNumber(...)`](#signinwithphonenumber)
* [`signInWithCustomToken(...)`](#signinwithcustomtoken)
* [`signOut()`](#signout)
* [`useAppLanguage()`](#useapplanguage)
* [`addListener('authStateChange', ...)`](#addlistenerauthstatechange)
* [`removeAllListeners()`](#removealllisteners)
* [`addListener(string, ...)`](#addlistenerstring)
* [`removeAllListeners()`](#removealllisteners)
* [`verify()`](#verify)
* [`setPersistence(...)`](#setpersistence)
* [`onAuthStateChanged(...)`](#onauthstatechanged)
* [`onIdTokenChanged(...)`](#onidtokenchanged)
* [`updateCurrentUser(...)`](#updatecurrentuser)
* [`useDeviceLanguage()`](#usedevicelanguage)
* [`signOut()`](#signout)
* [`confirm(...)`](#confirm)
* [`resolveSignIn(...)`](#resolvesignin)
* [`getSession()`](#getsession)
* [`enroll(...)`](#enroll)
* [`unenroll(...)`](#unenroll)
* [`setItem(...)`](#setitem)
* [`getItem(...)`](#getitem)
* [`removeItem(...)`](#removeitem)
* [`delete()`](#delete)
* [`getIdToken(...)`](#getidtoken)
* [`getIdTokenResult(...)`](#getidtokenresult)
* [`reload()`](#reload)
* [`toJSON()`](#tojson)
* [Interfaces](#interfaces)
* [Type Aliases](#type-aliases)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### getCurrentUser()

```typescript
getCurrentUser() => Promise<GetCurrentUserResult>
```

Fetches the currently signed-in user.

**Returns:** <code>Promise&lt;<a href="#getcurrentuserresult">GetCurrentUserResult</a>&gt;</code>

--------------------


### getIdToken(...)

```typescript
getIdToken(options?: GetIdTokenOptions | undefined) => Promise<GetIdTokenResult>
```

Fetches the Firebase Auth ID Token for the currently signed-in user.

| Param         | Type                                                            |
| ------------- | --------------------------------------------------------------- |
| **`options`** | <code><a href="#getidtokenoptions">GetIdTokenOptions</a></code> |

**Returns:** <code>Promise&lt;<a href="#getidtokenresult">GetIdTokenResult</a>&gt;</code>

--------------------


### setLanguageCode(...)

```typescript
setLanguageCode(options: SetLanguageCodeOptions) => Promise<void>
```

Sets the user-facing language code for auth operations.

| Param         | Type                                                                      |
| ------------- | ------------------------------------------------------------------------- |
| **`options`** | <code><a href="#setlanguagecodeoptions">SetLanguageCodeOptions</a></code> |

--------------------


### signInWithApple(...)

```typescript
signInWithApple(options?: SignInOptions | undefined) => Promise<SignInResult>
```

Starts the Apple sign-in flow.

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code><a href="#signinoptions">SignInOptions</a></code> |

**Returns:** <code>Promise&lt;<a href="#signinresult">SignInResult</a>&gt;</code>

--------------------


### signInWithFacebook(...)

```typescript
signInWithFacebook(options?: SignInOptions | undefined) => Promise<SignInResult>
```

Starts the Facebook sign-in flow.

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code><a href="#signinoptions">SignInOptions</a></code> |

**Returns:** <code>Promise&lt;<a href="#signinresult">SignInResult</a>&gt;</code>

--------------------


### signInWithGithub(...)

```typescript
signInWithGithub(options?: SignInOptions | undefined) => Promise<SignInResult>
```

Starts the GitHub sign-in flow.

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code><a href="#signinoptions">SignInOptions</a></code> |

**Returns:** <code>Promise&lt;<a href="#signinresult">SignInResult</a>&gt;</code>

--------------------


### signInWithGoogle(...)

```typescript
signInWithGoogle(options?: SignInOptions | undefined) => Promise<SignInResult>
```

Starts the Google sign-in flow.

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code><a href="#signinoptions">SignInOptions</a></code> |

**Returns:** <code>Promise&lt;<a href="#signinresult">SignInResult</a>&gt;</code>

--------------------


### signInWithMicrosoft(...)

```typescript
signInWithMicrosoft(options?: SignInOptions | undefined) => Promise<SignInResult>
```

Starts the Microsoft sign-in flow.

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code><a href="#signinoptions">SignInOptions</a></code> |

**Returns:** <code>Promise&lt;<a href="#signinresult">SignInResult</a>&gt;</code>

--------------------


### signInWithPlayGames(...)

```typescript
signInWithPlayGames(options?: SignInOptions | undefined) => Promise<SignInResult>
```

Starts the Play Games sign-in flow.

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code><a href="#signinoptions">SignInOptions</a></code> |

**Returns:** <code>Promise&lt;<a href="#signinresult">SignInResult</a>&gt;</code>

--------------------


### signInWithTwitter(...)

```typescript
signInWithTwitter(options?: SignInOptions | undefined) => Promise<SignInResult>
```

Starts the Twitter sign-in flow.

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code><a href="#signinoptions">SignInOptions</a></code> |

**Returns:** <code>Promise&lt;<a href="#signinresult">SignInResult</a>&gt;</code>

--------------------


### signInWithYahoo(...)

```typescript
signInWithYahoo(options?: SignInOptions | undefined) => Promise<SignInResult>
```

Starts the Yahoo sign-in flow.

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code><a href="#signinoptions">SignInOptions</a></code> |

**Returns:** <code>Promise&lt;<a href="#signinresult">SignInResult</a>&gt;</code>

--------------------


### signInWithPhoneNumber(...)

```typescript
signInWithPhoneNumber(options: SignInWithPhoneNumberOptions) => Promise<SignInWithPhoneNumberResult>
```

Starts the sign-in flow using a phone number.

Either the phone number or the verification code and verification ID must be provided.

Only available for Android and iOS.

| Param         | Type                                                                                  |
| ------------- | ------------------------------------------------------------------------------------- |
| **`options`** | <code><a href="#signinwithphonenumberoptions">SignInWithPhoneNumberOptions</a></code> |

**Returns:** <code>Promise&lt;<a href="#signinwithphonenumberresult">SignInWithPhoneNumberResult</a>&gt;</code>

--------------------


### signInWithCustomToken(...)

```typescript
signInWithCustomToken(options: SignInWithCustomTokenOptions) => Promise<SignInResult>
```

Starts the Custom Token sign-in flow.

This method cannot be used in combination with `skipNativeAuth` on Android and iOS.
In this case you have to use the `signInWithCustomToken` interface of the Firebase JS SDK directly.

| Param         | Type                                                                                  |
| ------------- | ------------------------------------------------------------------------------------- |
| **`options`** | <code><a href="#signinwithcustomtokenoptions">SignInWithCustomTokenOptions</a></code> |

**Returns:** <code>Promise&lt;<a href="#signinresult">SignInResult</a>&gt;</code>

--------------------


### signOut()

```typescript
signOut() => Promise<void>
```

Starts the sign-out flow.

--------------------


### useAppLanguage()

```typescript
useAppLanguage() => Promise<void>
```

Sets the user-facing language code to be the default app language.

--------------------


### addListener('authStateChange', ...)

```typescript
addListener(eventName: 'authStateChange', listenerFunc: AuthStateChangeListener) => Promise<PluginListenerHandle> & PluginListenerHandle
```

Listen for the user's sign-in state changes.

| Param              | Type                                                                        |
| ------------------ | --------------------------------------------------------------------------- |
| **`eventName`**    | <code>'authStateChange'</code>                                              |
| **`listenerFunc`** | <code><a href="#authstatechangelistener">AuthStateChangeListener</a></code> |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt; & <a href="#pluginlistenerhandle">PluginListenerHandle</a></code>

--------------------


### removeAllListeners()

```typescript
removeAllListeners() => Promise<void>
```

Remove all listeners for this plugin.

--------------------


### addListener(string, ...)

```typescript
addListener(eventName: string, listenerFunc: (...args: any[]) => any) => Promise<PluginListenerHandle>
```

| Param              | Type                                    |
| ------------------ | --------------------------------------- |
| **`eventName`**    | <code>string</code>                     |
| **`listenerFunc`** | <code>(...args: any[]) =&gt; any</code> |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt;</code>

--------------------


### removeAllListeners()

```typescript
removeAllListeners() => Promise<void>
```

--------------------


### verify()

```typescript
verify() => Promise<string>
```

Executes the verification process.

**Returns:** <code>Promise&lt;string&gt;</code>

--------------------


### setPersistence(...)

```typescript
setPersistence(persistence: Persistence) => Promise<void>
```

Changes the type of persistence on the `Auth` instance.

| Param             | Type                                                | Description                                                  |
| ----------------- | --------------------------------------------------- | ------------------------------------------------------------ |
| **`persistence`** | <code><a href="#persistence">Persistence</a></code> | - The {@link <a href="#persistence">Persistence</a>} to use. |

--------------------


### onAuthStateChanged(...)

```typescript
onAuthStateChanged(nextOrObserver: NextOrObserver<User | null>, error?: ErrorFn | undefined, completed?: CompleteFn | undefined) => Unsubscribe
```

Adds an observer for changes to the user's sign-in state.

| Param                | Type                                                                                              | Description                                    |
| -------------------- | ------------------------------------------------------------------------------------------------- | ---------------------------------------------- |
| **`nextOrObserver`** | <code><a href="#nextorobserver">NextOrObserver</a>&lt;<a href="#user">User</a> \| null&gt;</code> | - callback triggered on change.                |
| **`error`**          | <code><a href="#errorfn">ErrorFn</a></code>                                                       | - callback triggered on error.                 |
| **`completed`**      | <code><a href="#completefn">CompleteFn</a></code>                                                 | - callback triggered when observer is removed. |

**Returns:** <code><a href="#unsubscribe">Unsubscribe</a></code>

--------------------


### onIdTokenChanged(...)

```typescript
onIdTokenChanged(nextOrObserver: NextOrObserver<User | null>, error?: ErrorFn | undefined, completed?: CompleteFn | undefined) => Unsubscribe
```

Adds an observer for changes to the signed-in user's ID token.

| Param                | Type                                                                                              | Description                                    |
| -------------------- | ------------------------------------------------------------------------------------------------- | ---------------------------------------------- |
| **`nextOrObserver`** | <code><a href="#nextorobserver">NextOrObserver</a>&lt;<a href="#user">User</a> \| null&gt;</code> | - callback triggered on change.                |
| **`error`**          | <code><a href="#errorfn">ErrorFn</a></code>                                                       | - callback triggered on error.                 |
| **`completed`**      | <code><a href="#completefn">CompleteFn</a></code>                                                 | - callback triggered when observer is removed. |

**Returns:** <code><a href="#unsubscribe">Unsubscribe</a></code>

--------------------


### updateCurrentUser(...)

```typescript
updateCurrentUser(user: User | null) => Promise<void>
```

Asynchronously sets the provided user as {@link Auth.currentUser} on the {@link Auth} instance.

| Param      | Type                                          | Description                                 |
| ---------- | --------------------------------------------- | ------------------------------------------- |
| **`user`** | <code><a href="#user">User</a> \| null</code> | - The new {@link <a href="#user">User</a>}. |

--------------------


### useDeviceLanguage()

```typescript
useDeviceLanguage() => void
```

Sets the current language to the default device/browser preference.

--------------------


### signOut()

```typescript
signOut() => Promise<void>
```

Signs out the current user.

--------------------


### confirm(...)

```typescript
confirm(verificationCode: string) => Promise<UserCredential>
```

Finishes a phone number sign-in, link, or reauthentication.

| Param                  | Type                | Description                                           |
| ---------------------- | ------------------- | ----------------------------------------------------- |
| **`verificationCode`** | <code>string</code> | - The code that was sent to the user's mobile device. |

**Returns:** <code>Promise&lt;<a href="#usercredential">UserCredential</a>&gt;</code>

--------------------


### resolveSignIn(...)

```typescript
resolveSignIn(assertion: MultiFactorAssertion) => Promise<UserCredential>
```

A helper function to help users complete sign in with a second factor using an
{@link <a href="#multifactorassertion">MultiFactorAssertion</a>} confirming the user successfully completed the second factor
challenge.

| Param           | Type                                                                  | Description                                           |
| --------------- | --------------------------------------------------------------------- | ----------------------------------------------------- |
| **`assertion`** | <code><a href="#multifactorassertion">MultiFactorAssertion</a></code> | - The multi-factor assertion to resolve sign-in with. |

**Returns:** <code>Promise&lt;<a href="#usercredential">UserCredential</a>&gt;</code>

--------------------


### getSession()

```typescript
getSession() => Promise<MultiFactorSession>
```

Returns the session identifier for a second factor enrollment operation. This is used to
identify the user trying to enroll a second factor.

**Returns:** <code>Promise&lt;<a href="#multifactorsession">MultiFactorSession</a>&gt;</code>

--------------------


### enroll(...)

```typescript
enroll(assertion: MultiFactorAssertion, displayName?: string | null | undefined) => Promise<void>
```


Enrolls a second factor as identified by the {@link <a href="#multifactorassertion">MultiFactorAssertion</a>} for the
user.

| Param             | Type                                                                  | Description                                  |
| ----------------- | --------------------------------------------------------------------- | -------------------------------------------- |
| **`assertion`**   | <code><a href="#multifactorassertion">MultiFactorAssertion</a></code> | - The multi-factor assertion to enroll with. |
| **`displayName`** | <code>string \| null</code>                                           | - The display name of the second factor.     |

--------------------


### unenroll(...)

```typescript
unenroll(option: MultiFactorInfo | string) => Promise<void>
```

Unenrolls the specified second factor.

| Param        | Type                                                                  | Description                            |
| ------------ | --------------------------------------------------------------------- | -------------------------------------- |
| **`option`** | <code>string \| <a href="#multifactorinfo">MultiFactorInfo</a></code> | - The multi-factor option to unenroll. |

--------------------


### setItem(...)

```typescript
setItem(key: string, value: string) => Promise<void>
```

Persist an item in storage.

| Param       | Type                | Description      |
| ----------- | ------------------- | ---------------- |
| **`key`**   | <code>string</code> | - storage key.   |
| **`value`** | <code>string</code> | - storage value. |

--------------------


### getItem(...)

```typescript
getItem(key: string) => Promise<string | null>
```

Retrieve an item from storage.

| Param     | Type                | Description    |
| --------- | ------------------- | -------------- |
| **`key`** | <code>string</code> | - storage key. |

**Returns:** <code>Promise&lt;string | null&gt;</code>

--------------------


### removeItem(...)

```typescript
removeItem(key: string) => Promise<void>
```

Remove an item from storage.

| Param     | Type                | Description    |
| --------- | ------------------- | -------------- |
| **`key`** | <code>string</code> | - storage key. |

--------------------


### delete()

```typescript
delete() => Promise<void>
```

Deletes and signs out the user.

--------------------


### getIdToken(...)

```typescript
getIdToken(forceRefresh?: boolean | undefined) => Promise<string>
```

Returns a JSON Web Token (JWT) used to identify the user to a Firebase service.

| Param              | Type                 | Description                                     |
| ------------------ | -------------------- | ----------------------------------------------- |
| **`forceRefresh`** | <code>boolean</code> | - Force refresh regardless of token expiration. |

**Returns:** <code>Promise&lt;string&gt;</code>

--------------------


### getIdTokenResult(...)

```typescript
getIdTokenResult(forceRefresh?: boolean | undefined) => Promise<IdTokenResult>
```

Returns a deserialized JSON Web Token (JWT) used to identitfy the user to a Firebase service.

| Param              | Type                 | Description                                     |
| ------------------ | -------------------- | ----------------------------------------------- |
| **`forceRefresh`** | <code>boolean</code> | - Force refresh regardless of token expiration. |

**Returns:** <code>Promise&lt;<a href="#idtokenresult">IdTokenResult</a>&gt;</code>

--------------------


### reload()

```typescript
reload() => Promise<void>
```

Refreshes the user, if signed in.

--------------------


### toJSON()

```typescript
toJSON() => object
```

Returns a JSON-serializable representation of this object.

**Returns:** <code>object</code>

--------------------


### Interfaces


#### GetCurrentUserResult

| Prop       | Type                                          | Description                                               |
| ---------- | --------------------------------------------- | --------------------------------------------------------- |
| **`user`** | <code><a href="#user">User</a> \| null</code> | The currently signed-in user, or null if there isn't any. |


#### User

| Prop                | Type                        |
| ------------------- | --------------------------- |
| **`displayName`**   | <code>string \| null</code> |
| **`email`**         | <code>string \| null</code> |
| **`emailVerified`** | <code>boolean</code>        |
| **`isAnonymous`**   | <code>boolean</code>        |
| **`phoneNumber`**   | <code>string \| null</code> |
| **`photoUrl`**      | <code>string \| null</code> |
| **`providerId`**    | <code>string</code>         |
| **`tenantId`**      | <code>string \| null</code> |
| **`uid`**           | <code>string</code>         |


#### GetIdTokenResult

| Prop        | Type                | Description                            |
| ----------- | ------------------- | -------------------------------------- |
| **`token`** | <code>string</code> | The Firebase Auth ID token JWT string. |


#### GetIdTokenOptions

| Prop               | Type                 | Description                                   |
| ------------------ | -------------------- | --------------------------------------------- |
| **`forceRefresh`** | <code>boolean</code> | Force refresh regardless of token expiration. |


#### SetLanguageCodeOptions

| Prop               | Type                | Description                             |
| ------------------ | ------------------- | --------------------------------------- |
| **`languageCode`** | <code>string</code> | BCP 47 language code. Example: `en-US`. |


#### SignInResult

| Prop             | Type                                                              | Description                                               |
| ---------------- | ----------------------------------------------------------------- | --------------------------------------------------------- |
| **`user`**       | <code><a href="#user">User</a> \| null</code>                     | The currently signed-in user, or null if there isn't any. |
| **`credential`** | <code><a href="#authcredential">AuthCredential</a> \| null</code> | Credentials returned by an auth provider.                 |


#### AuthCredential

| Prop              | Type                | Description                                                                                                                              |
| ----------------- | ------------------- | ---------------------------------------------------------------------------------------------------------------------------------------- |
| **`providerId`**  | <code>string</code> | The authentication provider ID for the credential. Example: `google.com`.                                                                |
| **`accessToken`** | <code>string</code> | The OAuth access token associated with the credential if it belongs to an OAuth provider.                                                |
| **`idToken`**     | <code>string</code> | The OAuth ID token associated with the credential if it belongs to an OIDC provider.                                                     |
| **`secret`**      | <code>string</code> | The OAuth access token secret associated with the credential if it belongs to an OAuth 1.0 provider.                                     |
| **`nonce`**       | <code>string</code> | The random string used to make sure that the ID token you get was granted specifically in response to your app's authentication request. |


#### SignInOptions

| Prop                   | Type                                 | Description                                                                                       |
| ---------------------- | ------------------------------------ | ------------------------------------------------------------------------------------------------- |
| **`customParameters`** | <code>SignInCustomParameter[]</code> | Configures custom parameters to be passed to the identity provider during the OAuth sign-in flow. |


#### SignInCustomParameter

| Prop        | Type                | Description                                                        |
| ----------- | ------------------- | ------------------------------------------------------------------ |
| **`key`**   | <code>string</code> | The custom parameter key (e.g. `login_hint`).                      |
| **`value`** | <code>string</code> | The custom parameter value (e.g. `user@firstadd.onmicrosoft.com`). |


#### SignInWithPhoneNumberResult

| Prop                 | Type                | Description                                                             |
| -------------------- | ------------------- | ----------------------------------------------------------------------- |
| **`verificationId`** | <code>string</code> | The verification ID, which is needed to identify the verification code. |


#### SignInWithPhoneNumberOptions

| Prop                   | Type                | Description                                                                                                                                         |
| ---------------------- | ------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------- |
| **`phoneNumber`**      | <code>string</code> | The phone number to be verified.                                                                                                                    |
| **`verificationId`**   | <code>string</code> | The verification ID which will be returned when `signInWithPhoneNumber` is called for the first time. The `verificationCode` must also be provided. |
| **`verificationCode`** | <code>string</code> | The verification code from the SMS message. The `verificationId` must also be provided.                                                             |


#### SignInWithCustomTokenOptions

| Prop        | Type                | Description                       |
| ----------- | ------------------- | --------------------------------- |
| **`token`** | <code>string</code> | The custom token to sign in with. |


#### PluginListenerHandle

| Prop         | Type                                      |
| ------------ | ----------------------------------------- |
| **`remove`** | <code>() =&gt; Promise&lt;void&gt;</code> |


#### AuthStateChange

| Prop       | Type                                          | Description                                               |
| ---------- | --------------------------------------------- | --------------------------------------------------------- |
| **`user`** | <code><a href="#user">User</a> \| null</code> | The currently signed-in user, or null if there isn't any. |


#### Persistence

An interface covering the possible persistence mechanism types.

| Prop       | Type                                        | Description                                                                                                                                                                                                                                                   |
| ---------- | ------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **`type`** | <code>'SESSION' \| 'LOCAL' \| 'NONE'</code> | Type of <a href="#persistence">Persistence</a>. - 'SESSION' is used for temporary persistence such as `sessionStorage`. - 'LOCAL' is used for long term persistence such as `localStorage` or `IndexedDB`. - 'NONE' is used for in-memory, or no persistence. |


#### Observer

| Prop           | Type                                               |
| -------------- | -------------------------------------------------- |
| **`next`**     | <code><a href="#nextfn">NextFn</a>&lt;T&gt;</code> |
| **`error`**    | <code><a href="#errorfn">ErrorFn</a></code>        |
| **`complete`** | <code><a href="#completefn">CompleteFn</a></code>  |


#### Error

| Prop          | Type                |
| ------------- | ------------------- |
| **`name`**    | <code>string</code> |
| **`message`** | <code>string</code> |
| **`stack`**   | <code>string</code> |


#### UserCredential

A structure containing a {@link <a href="#user">User</a>}, the {@link OperationType}, and the provider ID.

| Prop                | Type                                                | Description                                                                              |
| ------------------- | --------------------------------------------------- | ---------------------------------------------------------------------------------------- |
| **`user`**          | <code><a href="#user">User</a></code>               | The user authenticated by this credential.                                               |
| **`providerId`**    | <code>string \| null</code>                         | The provider which was used to authenticate the user.                                    |
| **`operationType`** | <code>'link' \| 'reauthenticate' \| 'signIn'</code> | The type of operation which was used to authenticate the user (such as sign-in or link). |


#### MultiFactorAssertion

The base class for asserting ownership of a second factor.

| Prop           | Type                 | Description                          |
| -------------- | -------------------- | ------------------------------------ |
| **`factorId`** | <code>'phone'</code> | The identifier of the second factor. |


#### MultiFactorSession

An interface defining the multi-factor session object used for enrolling a second factor on a
user or helping sign in an enrolled user with a second factor.


#### MultiFactorInfo

A structure containing the information of a second factor entity.

| Prop                 | Type                        | Description                                                         |
| -------------------- | --------------------------- | ------------------------------------------------------------------- |
| **`uid`**            | <code>string</code>         | The multi-factor enrollment ID.                                     |
| **`displayName`**    | <code>string \| null</code> | The user friendly name of the current second factor.                |
| **`enrollmentTime`** | <code>string</code>         | The enrollment date of the second factor formatted as a UTC string. |
| **`factorId`**       | <code>'phone'</code>        | The identifier of the second factor.                                |


#### IdTokenResult

Interface representing ID token result obtained from {@link <a href="#user">User</a>.getIdTokenResult}.

| Prop                     | Type                                                | Description                                                                                                                |
| ------------------------ | --------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------- |
| **`authTime`**           | <code>string</code>                                 | The authentication time formatted as a UTC string.                                                                         |
| **`expirationTime`**     | <code>string</code>                                 | The ID token expiration time formatted as a UTC string.                                                                    |
| **`issuedAtTime`**       | <code>string</code>                                 | The ID token issuance time formatted as a UTC string.                                                                      |
| **`signInProvider`**     | <code>string \| null</code>                         | The sign-in provider through which the ID token was obtained (anonymous, custom, phone, password, etc).                    |
| **`signInSecondFactor`** | <code>string \| null</code>                         | The type of second factor associated with this session, provided the user was multi-factor authenticated (eg. phone, etc). |
| **`token`**              | <code>string</code>                                 | The Firebase Auth ID token JWT string.                                                                                     |
| **`claims`**             | <code><a href="#parsedtoken">ParsedToken</a></code> | The entire payload claims of the ID token including the standard reserved claims as well as the custom claims.             |


#### ParsedToken

Interface representing a parsed ID token.

| Prop              | Type                                                                        | Description                                                                         |
| ----------------- | --------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| **`'exp'`**       | <code>string</code>                                                         | Expiration time of the token.                                                       |
| **`'sub'`**       | <code>string</code>                                                         | UID of the user.                                                                    |
| **`'auth_time'`** | <code>string</code>                                                         | Time at which authentication was performed.                                         |
| **`'iat'`**       | <code>string</code>                                                         | Issuance time of the token.                                                         |
| **`'firebase'`**  | <code>{ sign_in_provider?: string; sign_in_second_factor?: string; }</code> | Firebase specific claims, containing the provider(s) used to authenticate the user. |


### Type Aliases


#### AuthStateChangeListener

Callback to receive the user's sign-in state change notifications.

<code>(change: <a href="#authstatechange">AuthStateChange</a>): void</code>


#### Unsubscribe

<code>(): void</code>


#### NextOrObserver

Type definition for an event callback.

<code><a href="#nextfn">NextFn</a>&lt;T | null&gt; | <a href="#observer">Observer</a>&lt;T | null&gt;</code>


#### NextFn

<code>(value: T): void</code>


#### ErrorFn

<code>(error: <a href="#error">Error</a>): void</code>


#### CompleteFn

<code>(): void</code>

</docgen-api>

## FAQ

1. **What does this plugin do?**  
   This plugin enables the use of [Firebase Authentication](https://firebase.google.com/docs/auth) in a Capacitor app.
   It uses the Firebase SDK for [Java](https://firebase.google.com/docs/reference/android) (Android), [Swift](https://firebase.google.com/docs/reference/swift) (iOS) and [JavaScript](https://firebase.google.com/docs/reference/js).
1. **What is the difference between the web implementation of this plugin and the Firebase JS SDK?**  
   The web implementation of this plugin encapsulates the Firebase JS SDK and enables a consistent interface across all platforms.
   You can decide if you prefer to use the web implementation or the Firebase JS SDK.
1. **What is the difference between the native and web authentication?**  
   For web authentication, the Firebase JS SDK is used. This only works to a limited extent on Android and iOS in the WebViews (see [here](https://developers.googleblog.com/2016/08/modernizing-oauth-interactions-in-native-apps.html)).
   For native authentication, the native SDKs from Firebase, Google, etc. are used. 
   These offer all the functionalities that the Firebase JS SDK also offers on the web. 
   However, after a login with the native SDK, the user is only logged in on the native layer of the app. 
   If the user should also be logged in on the web layer, additional steps are required (see [here](https://github.com/robingenz/capacitor-firebase-authentication/blob/main/docs/firebase-js-sdk.md)).
1. **How can I use this plugin with the Firebase JavaScript SDK?**  
   See [here](https://github.com/robingenz/capacitor-firebase-authentication/blob/main/docs/firebase-js-sdk.md).

## Changelog

See [CHANGELOG.md](CHANGELOG.md).

## License

See [LICENSE](LICENSE).
