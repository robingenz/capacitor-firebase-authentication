package dev.robingenz.capacitorjs.plugins.firebase.auth.handlers;

import com.getcapacitor.PluginCall;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.OAuthProvider;
import dev.robingenz.capacitorjs.plugins.firebase.auth.FirebaseAuthentication;

public class OAuthProviderHandler {

    private final FirebaseAuthentication pluginImplementation;

    public OAuthProviderHandler(FirebaseAuthentication pluginImplementation) {
        this.pluginImplementation = pluginImplementation;
    }

    public void signIn(PluginCall call, String providerId) {
        OAuthProvider.Builder provider = OAuthProvider.newBuilder(providerId);
        Task<AuthResult> pendingResultTask = pluginImplementation.getFirebaseAuthInstance().getPendingAuthResult();
        if (pendingResultTask == null) {
            startActivityForSignIn(call, provider);
        } else {
            finishActivityForSignIn(call, pendingResultTask);
        }
    }

    private void startActivityForSignIn(final PluginCall call, OAuthProvider.Builder provider) {
        pluginImplementation
            .getFirebaseAuthInstance()
            .startActivityForSignInWithProvider(pluginImplementation.getPlugin().getActivity(), provider.build())
            .addOnSuccessListener(
                authResult -> {
                    AuthCredential credential = authResult.getCredential();
                    pluginImplementation.handleSuccessfulSignIn(call, credential);
                }
            )
            .addOnFailureListener(exception -> pluginImplementation.handleFailedSignIn(call, exception));
    }

    private void finishActivityForSignIn(final PluginCall call, Task<AuthResult> pendingResultTask) {
        pendingResultTask
            .addOnSuccessListener(
                authResult -> {
                    AuthCredential credential = authResult.getCredential();
                    pluginImplementation.handleSuccessfulSignIn(call, credential);
                }
            )
            .addOnFailureListener(exception -> pluginImplementation.handleFailedSignIn(call, exception));
    }
}
