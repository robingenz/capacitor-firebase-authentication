declare module '@capacitor/core' {
  interface PluginRegistry {
    FirebaseAuthentication: FirebaseAuthenticationPlugin;
  }
}

export interface FirebaseAuthenticationPlugin {
  signIn(options: SignInOptions): Promise<SignInResult>;
  signOut(): Promise<void>;
}

export interface SignInOptions {
  provider: SignInProvider;
}

export enum SignInProvider {
  GOOGLE = 'google',
}

export interface SignInResult {
  // TODO
}
