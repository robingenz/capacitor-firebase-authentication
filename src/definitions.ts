declare module '@capacitor/core' {
  interface PluginRegistry {
    FirebaseAuthentication: FirebaseAuthenticationPlugin;
  }
}

export interface FirebaseAuthenticationPlugin {
  /**
   * Starts the sign-in flow.
   *
   * Only available for Android and iOS.
   */
  signIn(options: SignInOptions): Promise<SignInResult>;
  /**
   * Starts the sign-out flow.
   *
   * Only available for Android and iOS.
   */
  signOut(): Promise<void>;
}

export interface SignInOptions {
  provider: SignInProvider;
}

export enum SignInProvider {
  Google = 'google',
  Apple = 'apple',
}

export interface SignInResult {
  idToken: string;
  uid: string;
  email: string;
  displayName: string;
}
