import { WebPlugin } from '@capacitor/core';

import type {
  FirebaseAuthenticationPlugin,
  GetCurrentUserResult,
  GetIdTokenOptions,
  GetIdTokenResult,
} from './definitions';

export class FirebaseAuthenticationWeb
  extends WebPlugin
  implements FirebaseAuthenticationPlugin {
  public getCurrentUser(): Promise<GetCurrentUserResult> {
    throw new Error('Not implemented on web.');
  }

  public getIdToken(_options?: GetIdTokenOptions): Promise<GetIdTokenResult> {
    throw new Error('Not implemented on web.');
  }

  public async signInWithApple(): Promise<void> {
    throw new Error('Not implemented on web.');
  }

  public async signInWithGithub(): Promise<void> {
    throw new Error('Not implemented on web.');
  }

  public async signInWithGoogle(): Promise<void> {
    throw new Error('Not implemented on web.');
  }

  public async signInWithMicrosoft(): Promise<void> {
    throw new Error('Not implemented on web.');
  }

  public async signInWithTwitter(): Promise<void> {
    throw new Error('Not implemented on web.');
  }

  public async signInWithYahoo(): Promise<void> {
    throw new Error('Not implemented on web.');
  }

  public async signOut(): Promise<void> {
    throw new Error('Not implemented on web.');
  }
}
