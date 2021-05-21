import { registerWebPlugin, WebPlugin } from '@capacitor/core';
import {
  FirebaseAuthenticationPlugin,
  SignInOptions,
  SignInResult,
} from './definitions';

export class FirebaseAuthenticationWeb
  extends WebPlugin
  implements FirebaseAuthenticationPlugin
{
  constructor() {
    super({
      name: 'FirebaseAuthentication',
      platforms: ['web'],
    });
  }

  public async signIn(_options: SignInOptions): Promise<SignInResult> {
    throw new Error('Not implemented on web.');
  }

  public async signOut(): Promise<void> {
    throw new Error('Not implemented on web.');
  }
}

const FirebaseAuthentication = new FirebaseAuthenticationWeb();

export { FirebaseAuthentication };

registerWebPlugin(FirebaseAuthentication);
