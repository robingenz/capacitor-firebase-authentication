import { WebPlugin } from '@capacitor/core';
import { FirebaseAuthenticationPlugin } from './definitions';

export class FirebaseAuthenticationWeb extends WebPlugin implements FirebaseAuthenticationPlugin {
  constructor() {
    super({
      name: 'FirebaseAuthentication',
      platforms: ['web'],
    });
  }

  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}

const FirebaseAuthentication = new FirebaseAuthenticationWeb();

export { FirebaseAuthentication };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(FirebaseAuthentication);
