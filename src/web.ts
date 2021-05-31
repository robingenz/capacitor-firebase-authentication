import { WebPlugin } from '@capacitor/core';

import type { FirebaseAuthenticationPlugin, SignInResult } from './definitions';

export class FirebaseAuthenticationWeb extends WebPlugin implements FirebaseAuthenticationPlugin {
 
  public async signInWithApple(): Promise<SignInResult> {
    throw new Error('Not implemented on web.');
  }

  public async signInWithGoogle(): Promise<SignInResult> {
    throw new Error('Not implemented on web.');
  }

  public async signInWithMicrosoft(): Promise<SignInResult> {
    throw new Error('Not implemented on web.');
  }

  public async signOut(): Promise<void> {
    throw new Error('Not implemented on web.');
  }
}
