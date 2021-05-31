package dev.robingenz.capacitorjs.plugins.firebase.auth.handlers;


import android.content.Intent;

import com.getcapacitor.PluginCall;

public interface AuthProviderHandler {
    String TAG = "AuthProviderHandler";
    void signIn(PluginCall call);
    void signOut();
    int getRequestCode();
    void handleOnActivityResult(int requestCode, int resultCode, Intent data);
}
