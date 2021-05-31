package dev.robingenz.capacitorjs.plugins.firebase.auth;

import android.content.Intent;
import android.util.Log;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import java.util.HashMap;

import dev.robingenz.capacitorjs.plugins.firebase.auth.handlers.AuthProviderHandler;
import dev.robingenz.capacitorjs.plugins.firebase.auth.handlers.GoogleAuthProviderHandler;
import dev.robingenz.capacitorjs.plugins.firebase.auth.handlers.MicrosoftAuthProviderHandler;
import dev.robingenz.capacitorjs.plugins.firebase.auth.utils.AuthProvider;

@CapacitorPlugin(name = "FirebaseAuthentication")
public class FirebaseAuthenticationPlugin extends Plugin {
    public static final String TAG = "FirebaseAuthentication";
    public static final String ERROR_SIGN_IN_FAILED = "signIn failed.";
    private FirebaseAuthentication implementation = new FirebaseAuthentication();
    private FirebaseAuth firebaseAuthInstance;
    HashMap<AuthProvider, AuthProviderHandler> authProviderHandlers = new HashMap();

    public void load() {
        firebaseAuthInstance = FirebaseAuth.getInstance();
        authProviderHandlers.put(AuthProvider.GOOGLE, new GoogleAuthProviderHandler(this));
        authProviderHandlers.put(AuthProvider.MICROSOFT, new MicrosoftAuthProviderHandler(this));
    }

    @PluginMethod()
    public void signInWithApple(PluginCall call) {
        call.reject("Not implemented on Android.");
    }

    @PluginMethod()
    public void signInWithGoogle(PluginCall call) {
        authProviderHandlers.get(AuthProvider.GOOGLE).signIn(call);
    }

    @PluginMethod()
    public void signInWithMicrosoft(PluginCall call) {
        authProviderHandlers.get(AuthProvider.MICROSOFT).signIn(call);
    }

    @PluginMethod()
    public void signOut(PluginCall call) {
        FirebaseAuth.getInstance().signOut();
        for (AuthProvider provider : authProviderHandlers.keySet()) {
            AuthProviderHandler handler = authProviderHandlers.get(provider);
            handler.signOut();
        }
        call.resolve();
    }

    @Override
    public void startActivityForResult(PluginCall call, Intent intent, String callbackName) {
        super.startActivityForResult(call, intent, callbackName);
    }

    @ActivityCallback
    private void handleGoogleAuthProviderActivityResult(PluginCall call, ActivityResult result) {
        authProviderHandlers.get(AuthProvider.GOOGLE).handleOnActivityResult(call, result);
    }

    public void handleSuccessfulSignIn(final PluginCall call, AuthCredential credential) {
        firebaseAuthInstance.signInWithCredential(credential)
                .addOnCompleteListener(this.getActivity(), new OnCompleteListener<AuthResult>() {
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
                }).addOnFailureListener(this.getActivity(), new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception ex) {
                Log.w(TAG, "signInWithCredential failed.", ex);
                call.reject(ERROR_SIGN_IN_FAILED);
            }
        });
    }

    public void handleFailedSignIn(PluginCall call, Exception exception) {
        call.reject(exception.getLocalizedMessage());
    }

    public FirebaseAuth getFirebaseAuthInstance() {
        return firebaseAuthInstance;
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
