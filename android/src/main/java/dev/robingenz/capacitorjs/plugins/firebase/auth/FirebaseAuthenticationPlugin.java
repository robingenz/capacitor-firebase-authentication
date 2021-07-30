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

@CapacitorPlugin(name = "FirebaseAuthentication")
public class FirebaseAuthenticationPlugin extends Plugin {

    private FirebaseAuthenticationConfig config;
    private FirebaseAuthentication implementation;

    public void load() {
        config = getFirebaseAuthenticationConfig();
        implementation = new FirebaseAuthentication(this, config);
    }

    @PluginMethod
    public void getCurrentUser(PluginCall call) {
        FirebaseUser user = implementation.getCurrentUser();
        JSObject userResult = FirebaseAuthenticationHelper.createUserResultFromFirebaseUser(user);
        JSObject result = new JSObject();
        result.put("user", userResult);
        call.resolve(result);
    }

    @PluginMethod
    public void getIdToken(PluginCall call) {
        Boolean forceRefresh = call.getBoolean("forceRefresh", false);

        implementation.getIdToken(
            forceRefresh,
            new GetIdTokenResultCallback() {
                @Override
                public void success(String token) {
                    JSObject result = new JSObject();
                    result.put("token", token);
                    call.resolve(result);
                }

                @Override
                public void error(String message) {
                    call.reject(message);
                }
            }
        );
    }

    @PluginMethod
    public void setLanguageCode(PluginCall call) {
        String languageCode = call.getString("languageCode", "");

        implementation.setLanguageCode(languageCode);
        call.resolve();
    }

    @PluginMethod
    public void signInWithApple(PluginCall call) {
        implementation.signInWithApple(call);
    }

    @PluginMethod
    public void signInWithGithub(PluginCall call) {
        implementation.signInWithGithub(call);
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
    public void signInWithTwitter(PluginCall call) {
        implementation.signInWithTwitter(call);
    }

    @PluginMethod
    public void signInWithYahoo(PluginCall call) {
        implementation.signInWithYahoo(call);
    }

    @PluginMethod
    public void signOut(PluginCall call) {
        implementation.signOut(call);
    }

    @PluginMethod
    public void useAppLanguage(PluginCall call) {
        implementation.useAppLanguage();
        call.resolve();
    }

    @Override
    public void startActivityForResult(PluginCall call, Intent intent, String callbackName) {
        super.startActivityForResult(call, intent, callbackName);
    }

    @ActivityCallback
    private void handleGoogleAuthProviderActivityResult(PluginCall call, ActivityResult result) {
        implementation.handleGoogleAuthProviderActivityResult(call, result);
    }

    private FirebaseAuthenticationConfig getFirebaseAuthenticationConfig() {
        FirebaseAuthenticationConfig config = new FirebaseAuthenticationConfig();

        boolean skipNativeAuth = getConfig().getBoolean("skipNativeAuth", config.getSkipNativeAuth());
        config.setSkipNativeAuth(skipNativeAuth);

        return config;
    }
}
