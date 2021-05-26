package dev.robingenz.capacitor.firebaseauth.handlers;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.getcapacitor.PluginCall;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.OAuthProvider;

import dev.robingenz.capacitor.firebaseauth.FirebaseAuthentication;
import dev.robingenz.capacitor.firebaseauth.capacitorfirebaseauthentication.R;

public class MicrosoftIdentityProviderHandler implements IdentityProviderHandler {
    public static final int RC_SIGN_IN = 101;
    private FirebaseAuthentication plugin;
    private FirebaseAuth mAuth;
    private OAuthProvider.Builder provider;

    public MicrosoftIdentityProviderHandler(FirebaseAuthentication plugin) {
        this.plugin = plugin;
        mAuth = FirebaseAuth.getInstance();
        provider = OAuthProvider.newBuilder("microsoft.com");
    }

    public void signIn(PluginCall call) {
        Task<AuthResult> pendingResultTask = mAuth.getPendingAuthResult();
        if (pendingResultTask == null) {
            startActivityForSignIn(call);
        } else {
            finishActivityForSignIn(pendingResultTask, call);
        }
    }

    public void signOut() {
        // Not needed.
    }

    public int getRequestCode() {
        return RC_SIGN_IN;
    }

    public void handleOnActivityResult(int requestCode, int resultCode, Intent data) {
        // Not needed.
    }

    private void startActivityForSignIn(final PluginCall call) {
        mAuth.startActivityForSignInWithProvider(plugin.getActivity(), provider.build())
            .addOnSuccessListener(
                new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        AuthCredential credential = authResult.getCredential();
                        plugin.handleSuccessfulSignIn(call, credential);
                    }
                })
            .addOnFailureListener(
                new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        plugin.handleFailedSignIn(call, exception);
                    }
                });
    }

    private void finishActivityForSignIn(Task<AuthResult> pendingResultTask, final PluginCall call) {
        pendingResultTask
            .addOnSuccessListener(
                new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        AuthCredential credential = authResult.getCredential();
                        plugin.handleSuccessfulSignIn(call, credential);
                    }
                })
            .addOnFailureListener(
                new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        plugin.handleFailedSignIn(call, exception);
                    }
                });
    }
}
