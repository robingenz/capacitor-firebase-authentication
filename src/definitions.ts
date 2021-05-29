declare module '@capacitor/core' {
  interface PluginRegistry {
    FirebaseAuthentication: FirebaseAuthenticationPlugin;
  }
}

export interface FirebaseAuthenticationPlugin {
  /**
   * Starts the Apple sign-in flow.
   *
   * Only available for iOS.
   */
  signInWithApple(): Promise<SignInResult>;
  /**
   * Starts the Google sign-in flow.
   *
   * Only available for Android and iOS.
   */
  signInWithGoogle(): Promise<SignInResult>;
  /**
   * Starts the Microsoft sign-in flow.
   *
   * Only available for Android and iOS.
   */
  signInWithMicrosoft(): Promise<SignInResult>;
  /**
   * Starts the sign-out flow.
   *
   * Only available for Android and iOS.
   */
  signOut(): Promise<void>;
}

export interface SignInResult {
  /**
   * Firebase Auth ID Token.
   */
  idToken: string;
  /**
   * Firebase user ID.
   */
  uid: string;
  /**
   * Email address of the user.
   */
  email: string;
  /**
   * Display name of the user.
   */
  displayName: string;
}
