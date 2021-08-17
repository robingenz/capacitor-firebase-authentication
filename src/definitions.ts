/// <reference types="@capacitor/cli" />

declare module '@capacitor/cli' {
  export interface PluginsConfig {
    /**
     * These configuration values are available:
     */
    FirebaseAuthentication?: {
      /**
       * Configure whether the plugin should skip the native authentication.
       * Only needed if you want to use the Firebase JavaScript SDK.
       *
       * Only available for Android and iOS.
       *
       * @default false
       * @example false
       */
      skipNativeAuth?: boolean;
    };
  }
}

export interface FirebaseAuthenticationPlugin {
  /**
   * Fetches the currently signed-in user.
   *
   * Only available for Android and iOS.
   */
  getCurrentUser(): Promise<GetCurrentUserResult>;
  /**
   * Fetches the Firebase Auth ID Token for the currently signed-in user.
   *
   * Only available for Android and iOS.
   */
  getIdToken(options?: GetIdTokenOptions): Promise<GetIdTokenResult>;
  /**
   * Sets the user-facing language code for auth operations.
   *
   * Only available for Android and iOS.
   */
  setLanguageCode(options: SetLanguageCodeOptions): Promise<void>;
  /**
   * Starts the Apple sign-in flow.
   *
   * Only available for Android and iOS.
   */
  signInWithApple(options?: SignInOptions): Promise<SignInResult>;
  /**
   * Starts the GitHub sign-in flow.
   *
   * Only available for Android and iOS.
   */
  signInWithGithub(options?: SignInOptions): Promise<SignInResult>;
  /**
   * Starts the Google sign-in flow.
   *
   * Only available for Android and iOS.
   */
  signInWithGoogle(options?: SignInOptions): Promise<SignInResult>;
  /**
   * Starts the Microsoft sign-in flow.
   *
   * Only available for Android and iOS.
   */
  signInWithMicrosoft(options?: SignInOptions): Promise<SignInResult>;
  /**
   * Starts the Twitter sign-in flow.
   *
   * Only available for Android and iOS.
   */
  signInWithTwitter(options?: SignInOptions): Promise<SignInResult>;
  /**
   * Starts the Yahoo sign-in flow.
   *
   * Only available for Android and iOS.
   */
  signInWithYahoo(options?: SignInOptions): Promise<SignInResult>;
  /**
   * Starts the sign-out flow.
   *
   * Only available for Android and iOS.
   */
  signOut(): Promise<void>;
  /**
   * Sets the user-facing language code to be the default app language.
   *
   * Only available for Android and iOS.
   */
  useAppLanguage(): Promise<void>;
}

export interface GetCurrentUserResult {
  /**
   * The currently signed-in user, or null if there isn't any.
   */
  user: User | null;
}

export interface GetIdTokenOptions {
  /**
   * Force refresh regardless of token expiration.
   */
  forceRefresh: boolean;
}

export interface GetIdTokenResult {
  /**
   * The Firebase Auth ID token JWT string.
   */
  token: string;
}

export interface SetLanguageCodeOptions {
  /**
   * BCP 47 language code.
   *
   * Example: `en-US`.
   */
  languageCode: string;
}

export interface SignInOptions {
  /**
   * Configures custom parameters to be passed to the identity provider during the OAuth sign-in flow.
   */
  customParameters?: SignInCustomParameter[];
}

export interface SignInCustomParameter {
  /**
   * The custom parameter key (e.g. `login_hint`).
   */
  key: string;
  /**
   * The custom parameter value (e.g. `user@firstadd.onmicrosoft.com`).
   */
  value: string;
}

export interface SignInResult {
  /**
   * The currently signed-in user, or null if there isn't any.
   */
  user: User | null;
  /**
   * Credentials returned by an auth provider.
   */
  credential: AuthCredential | null;
}

export interface User {
  displayName: string | null;
  email: string | null;
  emailVerified: boolean;
  isAnonymous: boolean;
  phoneNumber: string | null;
  photoUrl: string | null;
  providerId: string;
  tenantId: string | null;
  uid: string;
}

export interface AuthCredential {
  /**
   * The authentication provider ID for the credential.
   * For example, 'google.com'.
   */
  providerId: string;
  /**
   * The OAuth access token associated with the credential if it belongs to an OAuth provider.
   */
  accessToken?: string;
  /**
   * The OAuth ID token associated with the credential if it belongs to an OIDC provider.
   */
  idToken?: string;
  /**
   * The OAuth access token secret associated with the credential if it belongs to an OAuth 1.0 provider.
   */
  secret?: string;
  /**
   * The random string used to make sure that the ID token you get was granted specifically in response to your app's authentication request.
   */
  nonce?: string;
}
