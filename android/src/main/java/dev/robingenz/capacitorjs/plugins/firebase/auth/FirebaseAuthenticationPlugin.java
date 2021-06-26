package dev.robingenz.capacitorjs.plugins.firebase.auth;

import android.content.Intent;
import androidx.activity.result.ActivityResult;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

@CapacitorPlugin(name = "FirebaseAuthentication")
public class FirebaseAuthenticationPlugin extends Plugin {

    private FirebaseAuthentication implementation;

    public void load() {
        implementation = new FirebaseAuthentication(this);
    }

    @PluginMethod
    public void getCurrentUser(PluginCall call) {
        FirebaseUser user = implementation.getCurrentUser();
        JSObject result = createGetCurrentUserResultFromFirebaseUser(user);
        call.resolve(result);
    }

    @PluginMethod
    public void getIdToken(PluginCall call) {
        Boolean forceRefresh = call.getBoolean("forceRefresh", false);

        FirebaseUser user = implementation.getCurrentUser();
        JSObject result = createGetIdTokenResultFromFirebaseUser(user, forceRefresh);
        call.resolve(result);
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

    private JSObject createGetCurrentUserResultFromFirebaseUser(FirebaseUser user) {
        JSObject result = new JSObject();
        if (user == null) {
            result.put("user", null);
            return result;
        }
        JSObject userResult = new JSObject();
        userResult.put("displayName", user.getDisplayName());
        userResult.put("email", user.getEmail());
        userResult.put("emailVerified", user.isEmailVerified());
        userResult.put("isAnonymous", user.isAnonymous());
        userResult.put("phoneNumber", user.getPhoneNumber());
        userResult.put("photoUrl", user.getPhotoUrl());
        userResult.put("providerId", user.getProviderId());
        userResult.put("tenantId", user.getTenantId());
        userResult.put("uid", user.getUid());
        result.put("user", userResult);
        return result;
    }

    private JSObject createGetIdTokenResultFromFirebaseUser(FirebaseUser user, Boolean forceRefresh) {
        GetTokenResult tokenResult = user.getIdToken(forceRefresh).getResult();
        JSObject result = new JSObject();
        result.put("token", tokenResult.getToken());
        return result;
    }
}
