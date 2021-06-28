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
   * Starts the Apple sign-in flow.
   *
   * Only available for Android and iOS.
   */
  signInWithApple(): Promise<void>;
  /**
   * Starts the GitHub sign-in flow.
   *
   * Only available for Android and iOS.
   */
  signInWithGithub(): Promise<void>;
  /**
   * Starts the Google sign-in flow.
   *
   * Only available for Android and iOS.
   */
  signInWithGoogle(): Promise<void>;
  /**
   * Starts the Microsoft sign-in flow.
   *
   * Only available for Android and iOS.
   */
  signInWithMicrosoft(): Promise<void>;
  /**
   * Starts the Twitter sign-in flow.
   *
   * Only available for Android and iOS.
   */
  signInWithTwitter(): Promise<void>;
  /**
   * Starts the Yahoo sign-in flow.
   *
   * Only available for Android and iOS.
   */
  signInWithYahoo(): Promise<void>;
  /**
   * Starts the sign-out flow.
   *
   * Only available for Android and iOS.
   */
  signOut(): Promise<void>;
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
  token: string;
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
