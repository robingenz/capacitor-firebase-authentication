package dev.robingenz.capacitorjs.plugins.firebase.auth;

import android.content.Intent;
import android.util.Log;
import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import dev.robingenz.capacitorjs.plugins.firebase.auth.handlers.GoogleAuthProviderHandler;
import dev.robingenz.capacitorjs.plugins.firebase.auth.handlers.OAuthProviderHandler;

public class FirebaseAuthentication {

    public static final String TAG = "FirebaseAuthentication";
    public static final String ERROR_SIGN_IN_FAILED = "signIn failed.";
    private FirebaseAuthenticationPlugin plugin;
    private FirebaseAuth firebaseAuthInstance;
    private GoogleAuthProviderHandler googleAuthProviderHandler;
    private OAuthProviderHandler oAuthProviderHandler;

    public FirebaseAuthentication(FirebaseAuthenticationPlugin plugin) {
        this.plugin = plugin;
        firebaseAuthInstance = FirebaseAuth.getInstance();
        googleAuthProviderHandler = new GoogleAuthProviderHandler(this);
        oAuthProviderHandler = new OAuthProviderHandler(this);
    }

    public FirebaseUser getCurrentUser() {
        return firebaseAuthInstance.getCurrentUser();
    }

    public String getIdToken(Boolean forceRefresh) {
        FirebaseUser user = getCurrentUser();
        GetTokenResult tokenResult = user.getIdToken(forceRefresh).getResult();
        return tokenResult.getToken();
    }

    public void setLanguageCode(String languageCode) {
        firebaseAuthInstance.setLanguageCode(languageCode);
    }

    public void signInWithApple(PluginCall call) {
        oAuthProviderHandler.signIn(call, "apple.com");
    }

    public void signInWithGithub(PluginCall call) {
        oAuthProviderHandler.signIn(call, "github.com");
    }

    public void signInWithGoogle(PluginCall call) {
        googleAuthProviderHandler.signIn(call);
    }

    public void signInWithMicrosoft(PluginCall call) {
        oAuthProviderHandler.signIn(call, "microsoft.com");
    }

    public void signInWithTwitter(PluginCall call) {
        oAuthProviderHandler.signIn(call, "twitter.com");
    }

    public void signInWithYahoo(PluginCall call) {
        oAuthProviderHandler.signIn(call, "yahoo.com");
    }

    public void signOut(PluginCall call) {
        FirebaseAuth.getInstance().signOut();
        googleAuthProviderHandler.signOut();
        call.resolve();
    }

    public void useAppLanguage() {
        firebaseAuthInstance.useAppLanguage();
    }

    public void startActivityForResult(PluginCall call, Intent intent, String callbackName) {
        plugin.startActivityForResult(call, intent, callbackName);
    }

    public void handleGoogleAuthProviderActivityResult(PluginCall call, ActivityResult result) {
        googleAuthProviderHandler.handleOnActivityResult(call, result);
    }

    public void handleSuccessfulSignIn(final PluginCall call, AuthCredential credential) {
        firebaseAuthInstance
            .signInWithCredential(credential)
            .addOnCompleteListener(
                plugin.getActivity(),
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential succeeded.");
                            FirebaseUser user = getCurrentUser();
                            JSObject userResult = FirebaseAuthenticationHelper.createUserResultFromFirebaseUser(user);
                            JSObject result = new JSObject();
                            result.put("user", userResult);
                            call.resolve(result);
                        } else {
                            Log.w(TAG, "signInWithCredential failed.", task.getException());
                            call.reject(ERROR_SIGN_IN_FAILED);
                        }
                    }
                }
            )
            .addOnFailureListener(
                plugin.getActivity(),
                new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception ex) {
                        Log.w(TAG, "signInWithCredential failed.", ex);
                        call.reject(ERROR_SIGN_IN_FAILED);
                    }
                }
            );
    }

    public void handleFailedSignIn(PluginCall call, Exception exception) {
        call.reject(exception.getLocalizedMessage());
    }

    public FirebaseAuth getFirebaseAuthInstance() {
        return firebaseAuthInstance;
    }

    public FirebaseAuthenticationPlugin getPlugin() {
        return plugin;
    }
}
