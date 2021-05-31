package dev.robingenz.capacitorjs.plugins.firebase.auth;

import android.content.Intent;
import androidx.activity.result.ActivityResult;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "FirebaseAuthentication")
public class FirebaseAuthenticationPlugin extends Plugin {

    private FirebaseAuthentication implementation;

    public void load() {
        implementation = new FirebaseAuthentication(this);
    }

    @PluginMethod
    public void signInWithApple(PluginCall call) {
        call.reject("Not implemented on Android.");
    }

    @PluginMethod
    public void signInWithGoogle(PluginCall call) {
        implementation.signInWithGoogle(call);
    }

    @PluginMethod
    public void signInWithMicrosoft(PluginCall call) {
        implementation.signInWithMicrosoft(call);
    }

    @PluginMethod
    public void signOut(PluginCall call) {
        implementation.signOut(call);
    }

    @Override
    public void startActivityForResult(PluginCall call, Intent intent, String callbackName) {
        super.startActivityForResult(call, intent, callbackName);
    }

    @ActivityCallback
    private void handleGoogleAuthProviderActivityResult(PluginCall call, ActivityResult result) {
        implementation.handleGoogleAuthProviderActivityResult(call, result);
    }
}
