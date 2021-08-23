# Firebase JavaScript SDK

> The [Firebase JavaScript SDK](https://firebase.google.com/docs/reference/js) implements the client-side libraries used by applications using Firebase services.

## How to use this plugin with the Firebase JavaScript SDK

By default, this plugin signs the user in only on the native layer of the app.
In order to use the Firebase JavaScript SDK, a sign-in on the web layer is required.
To do this, follow these steps:

1. [Add Firebase to your JavaScript project](https://firebase.google.com/docs/web/setup)
1. Set the configuration option `skipNativeAuth` to `true` (see [here](https://github.com/robingenz/capacitor-firebase-authentication#configuration)).
1. Sign in on the native layer, create web credentials and sign in on the web using [`signInWithCredential`](https://firebase.google.com/docs/reference/js/firebase.auth.Auth#signinwithcredential) (see [Examples](#examples)).

## Examples

```js
import { FirebaseAuthentication } from '@robingenz/capacitor-firebase-authentication';
import 'firebase/auth';
import firebase from 'firebase/app';

const signInWithApple = async () => {
  // 1. Sign in on the native layer
  const signInResult = await FirebaseAuthentication.signInWithApple();
  const idToken = result.credential?.idToken;
  const nonce = result.credential?.nonce;
  if (!idToken || !nonce) {
    return;
  }
  // 2. Sign in on the web layer using the id token and nonce
  const provider = new firebase.auth.OAuthProvider('apple.com');
  const credential = provider.credential({ idToken, nonce });
  await firebase.auth().signInWithCredential(credential);
};

const signInWithGoogle = async () => {
  // 1. Sign in on the native layer
  await FirebaseAuthentication.signInWithGoogle();
  // 2. Fetch the Firebase Auth ID Token
  const { token } = FirebaseAuthentication.getIdToken();
  // 3. Sign in on the web layer using the id token
  const credential = firebase.auth.GoogleAuthProvider.credential(token);
  await firebase.auth().signInWithCredential(credential);
};

const signInWithMicrosoft = async () => {
  // 1. Sign in on the native layer
  await FirebaseAuthentication.signInWithMicrosoft();
  // 2. Fetch the Firebase Auth ID Token
  const { token } = FirebaseAuthentication.getIdToken();
  // 3. Sign in on the web layer using the id token
  const provider = new firebase.auth.OAuthProvider('microsoft.com');
  const credential = provider.credential({ idToken: token });
  await firebase.auth().signInWithCredential(credential);

const signInWithPhoneNumber = async () => {
  // 1. Start phone number verification
  const result = FirebaseAuthentication.signInWithPhoneNumber({
    phoneNumber: '123456789',
  });
  // 2. Let the user enter the SMS code
  const verificationCode = window.prompt(
    'Please enter the verification code that was sent to your mobile device.',
  );
  // 3. Sign in on the web layer using the verification ID and verification code.
  const credential = firebase.auth.PhoneAuthProvider.credential(result.verificationId, verificationCode);
  await firebase.auth().signInWithCredential(credential);
};
```

## Limitations

Unfortunately, it is currently not possible to sign in to Apple on Android using the Firebase JavaScript SDK (see [#53](https://github.com/robingenz/capacitor-firebase-authentication/issues/53)).
