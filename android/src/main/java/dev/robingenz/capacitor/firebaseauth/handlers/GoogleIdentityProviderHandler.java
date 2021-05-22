package dev.robingenz.capacitor.firebaseauth.handlers;

import android.content.Intent;
import android.util.Log;

import com.getcapacitor.PluginCall;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import dev.robingenz.capacitor.firebaseauth.FirebaseAuthenticationPlugin;
import dev.robingenz.capacitor.firebaseauth.capacitorfirebaseauthentication.R;

public class GoogleIdentityProviderHandler implements IdentityProviderHandler {
    public static final int RC_SIGN_IN = 100;
    private FirebaseAuthenticationPlugin plugin;
    private GoogleSignInClient mGoogleSignInClient;

    public GoogleIdentityProviderHandler(FirebaseAuthenticationPlugin plugin) {
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
        FirebaseAuth.getInstance().signOut();
    }

    public boolean isAuthenticated() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(plugin.getContext());
        if (account == null) {
            return false;
        }
        return true;
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
