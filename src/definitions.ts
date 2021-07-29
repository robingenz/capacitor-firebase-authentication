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
   */
  setLanguageCode(options: SetLanguageCodeOptions): Promise<void>;
  /**
   * Starts the Apple sign-in flow.
   *
   * Only available for Android and iOS.
   */
  signInWithApple(): Promise<SignInResult>;
  /**
   * Starts the GitHub sign-in flow.
   *
   * Only available for Android and iOS.
   */
  signInWithGithub(): Promise<SignInResult>;
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
   * Starts the Twitter sign-in flow.
   *
   * Only available for Android and iOS.
   */
  signInWithTwitter(): Promise<SignInResult>;
  /**
   * Starts the Yahoo sign-in flow.
   *
   * Only available for Android and iOS.
   */
  signInWithYahoo(): Promise<SignInResult>;
  /**
   * Starts the sign-out flow.
   *
   * Only available for Android and iOS.
   */
  signOut(): Promise<void>;
  /**
   * Sets the user-facing language code to be the default app language.
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

export interface SignInResult {
  /**
   * The currently signed-in user, or null if there isn't any.
   */
  user: User | null;
  /**
   * Credentials returned by an auth provider.
   */
  credential: AuthCredential | OAuthCredential | null;
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
}

export interface OAuthCredential extends AuthCredential {
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
