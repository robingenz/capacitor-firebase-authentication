import { registerWebPlugin, WebPlugin } from '@capacitor/core';
import { FirebaseAuthenticationPlugin, SignInResult } from './definitions';

export class FirebaseAuthenticationWeb extends WebPlugin
  implements FirebaseAuthenticationPlugin {
  constructor() {
    super({
      name: 'FirebaseAuthentication',
      platforms: ['web'],
    });
  }

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

const FirebaseAuthentication = new FirebaseAuthenticationWeb();

export { FirebaseAuthentication };

registerWebPlugin(FirebaseAuthentication);
