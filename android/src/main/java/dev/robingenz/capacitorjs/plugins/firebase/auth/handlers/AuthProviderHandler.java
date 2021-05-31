package dev.robingenz.capacitorjs.plugins.firebase.auth.handlers;

import androidx.activity.result.ActivityResult;

import com.getcapacitor.PluginCall;

public interface AuthProviderHandler {
    String TAG = "AuthProviderHandler";
    void signIn(PluginCall call);
    void signOut();
    void handleOnActivityResult(PluginCall call, ActivityResult result);
}
