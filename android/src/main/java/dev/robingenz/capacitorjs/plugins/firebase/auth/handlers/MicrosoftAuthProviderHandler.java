package dev.robingenz.capacitorjs.plugins.firebase.auth.handlers;

public class MicrosoftAuthProviderHandler implements AuthProviderHandler {
    public static final int RC_SIGN_IN = 101;
    private FirebaseAuthentication plugin;
    private OAuthProvider.Builder provider;

    public MicrosoftAuthProviderHandler(FirebaseAuthentication plugin) {
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

    public int getRequestCode() {
        return RC_SIGN_IN;
    }

    public void handleOnActivityResult(int requestCode, int resultCode, Intent data) {
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
