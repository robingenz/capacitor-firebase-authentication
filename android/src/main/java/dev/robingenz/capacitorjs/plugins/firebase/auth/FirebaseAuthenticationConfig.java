package dev.robingenz.capacitorjs.plugins.firebase.auth;

public class FirebaseAuthenticationConfig {

    private boolean skipNativeAuth = false;

    public boolean getSkipNativeAuth() {
        return skipNativeAuth;
    }

    public void setSkipNativeAuth(boolean skipNativeAuth) {
        this.skipNativeAuth = skipNativeAuth;
    }
}
