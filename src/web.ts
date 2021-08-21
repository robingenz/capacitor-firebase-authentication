import { WebPlugin } from '@capacitor/core';
import type {
  FirebaseAuthenticationPlugin,
  GetCurrentUserResult,
  GetIdTokenResult,
  SetLanguageCodeOptions,
  SignInResult,
} from './definitions';

export class FirebaseAuthenticationWeb
  extends WebPlugin
  implements FirebaseAuthenticationPlugin {
  public getCurrentUser(): Promise<GetCurrentUserResult> {
    throw new Error('Not implemented on web.');
  }

  public getIdToken(): Promise<GetIdTokenResult> {
    throw new Error('Not implemented on web.');
  }

  public setLanguageCode(_options: SetLanguageCodeOptions): Promise<void> {
    throw new Error('Not implemented on web.');
  }

  public async signInWithApple(): Promise<SignInResult> {
    throw new Error('Not implemented on web.');
  }

  public async signInWithFacebook(): Promise<SignInResult> {
    throw new Error('Not implemented on web.');
  }

  public async signInWithGithub(): Promise<SignInResult> {
    throw new Error('Not implemented on web.');
  }

  public async signInWithGoogle(): Promise<SignInResult> {
    throw new Error('Not implemented on web.');
  }

  public async signInWithMicrosoft(): Promise<SignInResult> {
    throw new Error('Not implemented on web.');
  }

  public async signInWithTwitter(): Promise<SignInResult> {
    throw new Error('Not implemented on web.');
  }

  public async signInWithYahoo(): Promise<SignInResult> {
    throw new Error('Not implemented on web.');
  }

  public async signOut(): Promise<void> {
    throw new Error('Not implemented on web.');
  }

  public useAppLanguage(): Promise<void> {
    throw new Error('Not implemented on web.');
  }
}
