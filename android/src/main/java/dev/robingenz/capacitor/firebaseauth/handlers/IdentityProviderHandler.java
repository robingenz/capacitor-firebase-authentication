package dev.robingenz.capacitor.firebaseauth.handlers;


import android.content.Intent;

import com.getcapacitor.PluginCall;

public interface IdentityProviderHandler {
    String TAG = "IdentityProviderHandler";
    void signIn(PluginCall call);
    void signOut();
    boolean isAuthenticated();
    int getRequestCode();
    void handleOnActivityResult(int requestCode, int resultCode, Intent data);
}
