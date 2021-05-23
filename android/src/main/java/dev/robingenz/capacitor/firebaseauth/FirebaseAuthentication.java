package dev.robingenz.capacitor.firebaseauth;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import java.util.HashMap;

import dev.robingenz.capacitor.firebaseauth.handlers.GoogleIdentityProviderHandler;
import dev.robingenz.capacitor.firebaseauth.handlers.IdentityProviderHandler;
import dev.robingenz.capacitor.firebaseauth.util.IdentityProvider;

@NativePlugin(
        requestCodes={GoogleIdentityProviderHandler.RC_SIGN_IN}
)
public class FirebaseAuthentication extends Plugin {
    public static final String TAG = "FirebaseAuthentication";
    public static final String ERROR_PROVIDER_MISSING = "provider must be provided.";
    public static final String ERROR_PROVIDER_NOT_SUPPORTED = "provider is not supported.";
    public static final String ERROR_SIGN_IN_FAILED = "signIn failed.";
    private FirebaseAuth mAuth;
    HashMap<IdentityProvider, IdentityProviderHandler> identityProviderHandlers = new HashMap();

    public void load() {
        mAuth = FirebaseAuth.getInstance();
        identityProviderHandlers.put(IdentityProvider.GOOGLE, new GoogleIdentityProviderHandler(this));
    }

    @PluginMethod()
    public void signIn(PluginCall call) {
        String provider = call.getString("provider");
        if (provider == null) {
            call.reject(ERROR_PROVIDER_MISSING);
            return;
        }

        IdentityProvider parsedProvider = this.parseProvider(provider);
        if (parsedProvider == IdentityProvider.UNKNOWN) {
            call.reject(ERROR_PROVIDER_NOT_SUPPORTED);
            return;
        }

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Log.d(TAG, "User already signed in.");
            JSObject signInResult = createSignInResultFrom(currentUser);
            call.resolve(signInResult);
            return;
        }

        identityProviderHandlers.get(parsedProvider).signIn(call);
    }

    @PluginMethod()
    public void signOut(PluginCall call) {
        FirebaseAuth.getInstance().signOut();
        for (IdentityProvider provider : identityProviderHandlers.keySet()) {
            IdentityProviderHandler handler = identityProviderHandlers.get(provider);
            handler.signOut();
        }
        call.resolve();
    }

    @Override
    public void startActivityForResult(PluginCall call, Intent intent, int resultCode) {
        saveCall(call);
        super.startActivityForResult(call, intent, resultCode);
    }

    @Override
    public void handleOnActivityResult(int requestCode, int resultCode, Intent data) {
        super.handleOnActivityResult(requestCode, resultCode, data);
        for (IdentityProvider provider : identityProviderHandlers.keySet()) {
            IdentityProviderHandler handler = identityProviderHandlers.get(provider);
            if (handler.getRequestCode() == requestCode) {
                handler.handleOnActivityResult(requestCode, resultCode, data);
                break;
            }
        }
    }

    public void handleSuccessfulSignIn(final PluginCall call, AuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this.getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential succeeded.");
                            FirebaseUser user = mAuth.getCurrentUser();
                            JSObject signInResult = createSignInResultFrom(user);
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

    public void handleFailedSignIn(PluginCall call, ApiException exception) {
        call.reject(exception.getLocalizedMessage());
    }

    private IdentityProvider parseProvider(String provider) {
        switch (provider) {
            case "google":
                return IdentityProvider.GOOGLE;
            default:
                return IdentityProvider.UNKNOWN;
        }
    }

    private JSObject createSignInResultFrom(FirebaseUser user) {
        GetTokenResult tokenResult = user.getIdToken(false).getResult();
        JSObject result = new JSObject();
        result.put("idToken", tokenResult.getToken());
        result.put("uid", user.getUid());
        result.put("email", user.getEmail());
        result.put("displayName", user.getDisplayName());
        return result;
    }
}
