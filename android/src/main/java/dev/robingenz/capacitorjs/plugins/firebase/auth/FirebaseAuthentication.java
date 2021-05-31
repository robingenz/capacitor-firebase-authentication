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
import dev.robingenz.capacitorjs.plugins.firebase.auth.handlers.MicrosoftAuthProviderHandler;

public class FirebaseAuthentication {

    public static final String TAG = "FirebaseAuthentication";
    public static final String ERROR_SIGN_IN_FAILED = "signIn failed.";
    private FirebaseAuthenticationPlugin plugin;
    private FirebaseAuth firebaseAuthInstance;
    private GoogleAuthProviderHandler googleAuthProviderHandler;
    private MicrosoftAuthProviderHandler microsoftAuthProviderHandler;

    public FirebaseAuthentication(FirebaseAuthenticationPlugin plugin) {
        this.plugin = plugin;
        firebaseAuthInstance = FirebaseAuth.getInstance();
        googleAuthProviderHandler = new GoogleAuthProviderHandler(this);
        microsoftAuthProviderHandler = new MicrosoftAuthProviderHandler(this);
    }

    public void signInWithGoogle(PluginCall call) {
        googleAuthProviderHandler.signIn(call);
    }

    public void signInWithMicrosoft(PluginCall call) {
        microsoftAuthProviderHandler.signIn(call);
    }

    public void signOut(PluginCall call) {
        FirebaseAuth.getInstance().signOut();
        call.resolve();
    }

    public void startActivityForResult(PluginCall call, Intent intent, String callbackName) {
        plugin.startActivityForResult(call, intent, callbackName);
    }

    public void handleGoogleAuthProviderActivityResult(PluginCall call, ActivityResult result) {}

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
                            FirebaseUser user = firebaseAuthInstance.getCurrentUser();
                            JSObject signInResult = createSignInResultFromFirebaseUser(user);
                            call.resolve(signInResult);
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

    private JSObject createSignInResultFromFirebaseUser(FirebaseUser user) {
        GetTokenResult tokenResult = user.getIdToken(false).getResult();
        JSObject result = new JSObject();
        result.put("idToken", tokenResult.getToken());
        result.put("uid", user.getUid());
        result.put("email", user.getEmail());
        result.put("displayName", user.getDisplayName());
        return result;
    }
}
