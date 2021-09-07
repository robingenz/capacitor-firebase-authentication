package dev.robingenz.capacitorjs.plugins.firebase.auth;

import android.content.Intent;
import android.util.Log;
import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import dev.robingenz.capacitorjs.plugins.firebase.auth.handlers.FacebookAuthProviderHandler;
import dev.robingenz.capacitorjs.plugins.firebase.auth.handlers.GoogleAuthProviderHandler;
import dev.robingenz.capacitorjs.plugins.firebase.auth.handlers.OAuthProviderHandler;
import dev.robingenz.capacitorjs.plugins.firebase.auth.handlers.PhoneAuthProviderHandler;
import dev.robingenz.capacitorjs.plugins.firebase.auth.handlers.PlayGamesAuthProviderHandler;

import java.util.Arrays;
import java.util.List;

public class FirebaseAuthentication {

    interface AuthStateChangeListener {
        void onAuthStateChanged();
    }

    @Nullable
    private AuthStateChangeListener authStateChangeListener;

    public static final String TAG = "FirebaseAuthentication";
    public static final String ERROR_SIGN_IN_FAILED = "signIn failed.";
    private FirebaseAuthenticationPlugin plugin;
    private FirebaseAuthenticationConfig config;
    private FirebaseAuth firebaseAuthInstance;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    private FacebookAuthProviderHandler facebookAuthProviderHandler;
    private GoogleAuthProviderHandler googleAuthProviderHandler;
    private OAuthProviderHandler oAuthProviderHandler;
    private PhoneAuthProviderHandler phoneAuthProviderHandler;
    private PlayGamesAuthProviderHandler playGamesAuthProviderHandler;

    public FirebaseAuthentication(FirebaseAuthenticationPlugin plugin, FirebaseAuthenticationConfig config) {
        this.plugin = plugin;
        this.config = config;
        firebaseAuthInstance = FirebaseAuth.getInstance();
        this.initAuthProviderHandlers(config);
        this.firebaseAuthStateListener =
            firebaseAuth -> {
                if (authStateChangeListener != null) {
                    authStateChangeListener.onAuthStateChanged();
                }
            };
        firebaseAuthInstance.addAuthStateListener(this.firebaseAuthStateListener);
    }

    public void setAuthStateChangeListener(@Nullable AuthStateChangeListener listener) {
        this.authStateChangeListener = listener;
    }

    @Nullable
    public AuthStateChangeListener getAuthStateChangeListener() {
        return authStateChangeListener;
    }

    public FirebaseUser getCurrentUser() {
        return firebaseAuthInstance.getCurrentUser();
    }

    public void getIdToken(Boolean forceRefresh, final GetIdTokenResultCallback resultCallback) {
        FirebaseUser user = getCurrentUser();
        Task<GetTokenResult> tokenResultTask = user.getIdToken(forceRefresh);
        tokenResultTask.addOnCompleteListener(
            new OnCompleteListener<GetTokenResult>() {
                public void onComplete(@NonNull Task<GetTokenResult> task) {
                    if (task.isSuccessful()) {
                        String token = task.getResult().getToken();
                        resultCallback.success(token);
                    } else {
                        String message = task.getException().getLocalizedMessage();
                        resultCallback.error(message);
                    }
                }
            }
        );
    }

    public void setLanguageCode(String languageCode) {
        firebaseAuthInstance.setLanguageCode(languageCode);
    }

    public void signInWithApple(PluginCall call) {
        oAuthProviderHandler.signIn(call, "apple.com");
    }

    public void signInWithFacebook(PluginCall call) {
        facebookAuthProviderHandler.signIn(call);
    }

    public void signInWithGithub(PluginCall call) {
        oAuthProviderHandler.signIn(call, "github.com");
    }

    public void signInWithGoogle(PluginCall call) {
        googleAuthProviderHandler.signIn(call);
    }

    public void signInWithMicrosoft(PluginCall call) {
        oAuthProviderHandler.signIn(call, "microsoft.com");
    }

    public void signInWithPhoneNumber(PluginCall call) {
        phoneAuthProviderHandler.signIn(call);
    }

    public void signInWithPlayGames(PluginCall call) {
        playGamesAuthProviderHandler.signIn(call);
    }

    public void signInWithTwitter(PluginCall call) {
        oAuthProviderHandler.signIn(call, "twitter.com");
    }

    public void signInWithYahoo(PluginCall call) {
        oAuthProviderHandler.signIn(call, "yahoo.com");
    }

    public void signOut(PluginCall call) {
        FirebaseAuth.getInstance().signOut();
        if (googleAuthProviderHandler != null) {
            googleAuthProviderHandler.signOut();
        }
        if (facebookAuthProviderHandler != null) {
            facebookAuthProviderHandler.signOut();
        }
        if (playGamesAuthProviderHandler != null) {
            playGamesAuthProviderHandler.signOut();
        }
        call.resolve();
    }

    public void useAppLanguage() {
        firebaseAuthInstance.useAppLanguage();
    }

    public void startActivityForResult(PluginCall call, Intent intent, String callbackName) {
        plugin.startActivityForResult(call, intent, callbackName);
    }

    public void handleGoogleAuthProviderActivityResult(PluginCall call, ActivityResult result) {
        googleAuthProviderHandler.handleOnActivityResult(call, result);
    }

    public void handlePlayGamesAuthProviderActivityResult(PluginCall call, ActivityResult result) {
        playGamesAuthProviderHandler.handleOnActivityResult(call, result);
    }

    public void handleOnActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FacebookAuthProviderHandler.RC_FACEBOOK_AUTH && facebookAuthProviderHandler != null) {
            facebookAuthProviderHandler.handleOnActivityResult(requestCode, resultCode, data);
        }
    }

    public void handleSuccessfulSignIn(final PluginCall call, AuthCredential credential, String idToken) {
        boolean skipNativeAuth = this.config.getSkipNativeAuth();
        if (skipNativeAuth) {
            JSObject signInResult = FirebaseAuthenticationHelper.createSignInResult(null, credential, idToken);
            call.resolve(signInResult);
            return;
        }
        firebaseAuthInstance
            .signInWithCredential(credential)
            .addOnCompleteListener(
                plugin.getActivity(),
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential succeeded.");
                            FirebaseUser user = getCurrentUser();
                            JSObject signInResult = FirebaseAuthenticationHelper.createSignInResult(user, credential, idToken);
                            call.resolve(signInResult);
                        } else {
                            Log.e(TAG, "signInWithCredential failed.", task.getException());
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
                        Log.e(TAG, "signInWithCredential failed.", ex);
                        call.reject(ERROR_SIGN_IN_FAILED);
                    }
                }
            );
    }

    public void handleFailedSignIn(PluginCall call, String message, Exception exception) {
        if (message == null && exception != null) {
            message = exception.getLocalizedMessage();
        }
        call.reject(message, exception);
    }

    public FirebaseAuth getFirebaseAuthInstance() {
        return firebaseAuthInstance;
    }

    public FirebaseAuthenticationPlugin getPlugin() {
        return plugin;
    }

    public FirebaseAuthenticationConfig getConfig() {
        return config;
    }

    private void initAuthProviderHandlers(FirebaseAuthenticationConfig config) {
        List providerList = Arrays.asList(config.getProviders());
        if (providerList.contains("facebook.com")) {
            facebookAuthProviderHandler = new FacebookAuthProviderHandler(this);
        }
        if (providerList.contains("google.com")) {
            googleAuthProviderHandler = new GoogleAuthProviderHandler(this);
        }
        if (providerList.contains("playgames.google.com")) {
            playGamesAuthProviderHandler = new PlayGamesAuthProviderHandler(this);
        }
        if (providerList.contains("phone")) {
            phoneAuthProviderHandler = new PhoneAuthProviderHandler(this);
        }
        oAuthProviderHandler = new OAuthProviderHandler(this);
    }
}
