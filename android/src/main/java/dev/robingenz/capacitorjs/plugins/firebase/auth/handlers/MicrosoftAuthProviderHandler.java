package dev.robingenz.capacitorjs.plugins.firebase.auth.handlers;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;

import com.getcapacitor.PluginCall;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.OAuthProvider;

import dev.robingenz.capacitorjs.plugins.firebase.auth.FirebaseAuthenticationPlugin;

public class MicrosoftAuthProviderHandler implements AuthProviderHandler {
    private FirebaseAuthenticationPlugin plugin;
    private OAuthProvider.Builder provider;

    public MicrosoftAuthProviderHandler(FirebaseAuthenticationPlugin plugin) {
        this.plugin = plugin;
        provider = OAuthProvider.newBuilder("microsoft.com");
    }

    public void signIn(PluginCall call) {
        Task<AuthResult> pendingResultTask = plugin.getFirebaseAuthInstance().getPendingAuthResult();
        if (pendingResultTask == null) {
            startActivityForSignIn(call);
        } else {
            finishActivityForSignIn(call, pendingResultTask);
        }
    }

    public void signOut() {
        // Not needed.
    }

    public void handleOnActivityResult(PluginCall call, ActivityResult result) {
        // Not needed.
    }

    private void startActivityForSignIn(final PluginCall call) {
        plugin.getFirebaseAuthInstance().startActivityForSignInWithProvider(plugin.getActivity(), provider.build())
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

    private void finishActivityForSignIn(final PluginCall call, Task<AuthResult> pendingResultTask) {
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
