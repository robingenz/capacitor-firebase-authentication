package dev.robingenz.capacitorjs.plugins.firebase.auth.handlers;

public class GoogleAuthProviderHandler  implements AuthProviderHandler {
    public static final int RC_SIGN_IN = 100;
    private FirebaseAuthentication plugin;
    private GoogleSignInClient mGoogleSignInClient;

    public GoogleAuthProviderHandler(FirebaseAuthentication plugin) {
        this.plugin = plugin;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(plugin.getContext().getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(plugin.getActivity(), gso);
    }

    public void signIn(PluginCall call) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        plugin.startActivityForResult(call, signInIntent, RC_SIGN_IN);
    }

    public void signOut() {
        mGoogleSignInClient.signOut();
    }

    public int getRequestCode() {
        return RC_SIGN_IN;
    }

    public void handleOnActivityResult(int requestCode, int resultCode, Intent data) {
        PluginCall savedCall = plugin.getSavedCall();
        if (savedCall == null) {
            return;
        }
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            String idToken = account.getIdToken();
            AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
            Log.d(TAG, "Google Sign-In succeeded.");
            plugin.handleSuccessfulSignIn(savedCall, credential);
        } catch (ApiException exception) {
            Log.w(TAG, "Google Sign-In failed.", exception);
            plugin.handleFailedSignIn(savedCall, exception);
        }
    }
}
