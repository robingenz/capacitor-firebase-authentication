package dev.robingenz.capacitorjs.plugins.firebase.auth.handlers;

import androidx.annotation.NonNull;
import com.getcapacitor.PluginCall;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.OAuthProvider;
import dev.robingenz.capacitorjs.plugins.firebase.auth.FirebaseAuthentication;

public class AppleAuthProviderHandler {

    private FirebaseAuthentication pluginImplementation;
    private OAuthProvider.Builder provider;

    public AppleAuthProviderHandler(FirebaseAuthentication pluginImplementation) {
        this.pluginImplementation = pluginImplementation;
        provider = OAuthProvider.newBuilder("apple.com");
    }

    public void signIn(PluginCall call) {
        Task<AuthResult> pendingResultTask = pluginImplementation.getFirebaseAuthInstance().getPendingAuthResult();
        if (pendingResultTask == null) {
            startActivityForSignIn(call);
        } else {
            finishActivityForSignIn(call, pendingResultTask);
        }
    }

    private void startActivityForSignIn(final PluginCall call) {
        pluginImplementation
            .getFirebaseAuthInstance()
            .startActivityForSignInWithProvider(pluginImplementation.getPlugin().getActivity(), provider.build())
            .addOnSuccessListener(
                new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        AuthCredential credential = authResult.getCredential();
                        pluginImplementation.handleSuccessfulSignIn(call, credential);
                    }
                }
            )
            .addOnFailureListener(
                new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        pluginImplementation.handleFailedSignIn(call, exception);
                    }
                }
            );
    }

    private void finishActivityForSignIn(final PluginCall call, Task<AuthResult> pendingResultTask) {
        pendingResultTask
            .addOnSuccessListener(
                new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        AuthCredential credential = authResult.getCredential();
                        pluginImplementation.handleSuccessfulSignIn(call, credential);
                    }
                }
            )
            .addOnFailureListener(
                new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        pluginImplementation.handleFailedSignIn(call, exception);
                    }
                }
            );
    }
}
