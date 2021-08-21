package dev.robingenz.capacitorjs.plugins.firebase.auth.handlers;

import android.content.Intent;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.getcapacitor.PluginCall;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import dev.robingenz.capacitorjs.plugins.firebase.auth.FirebaseAuthentication;

public class FacebookAuthProviderHandler {

    public static final int RC_FACEBOOK_AUTH = 105;
    private FirebaseAuthentication pluginImplementation;
    private CallbackManager mCallbackManager;
    private LoginButton loginButton;
    private PluginCall savedCall;

    public FacebookAuthProviderHandler(FirebaseAuthentication pluginImplementation) {
        this.pluginImplementation = pluginImplementation;
        mCallbackManager = CallbackManager.Factory.create();
        loginButton = new LoginButton(pluginImplementation.getPlugin().getContext());
        loginButton.setPermissions("email", "public_profile");
        loginButton.registerCallback(
            mCallbackManager,
            new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    handleSuccessCallback(loginResult);
                }

                @Override
                public void onCancel() {
                    handleCancelCallback();
                }

                @Override
                public void onError(FacebookException exception) {
                    handleErrorCallback(exception);
                }
            }
        );
    }

    public void signIn(PluginCall call) {
        savedCall = call;
        this.loginButton.performClick();
    }

    public void signOut() {
        LoginManager.getInstance().logOut();
    }

    public void handleOnActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleSuccessCallback(LoginResult loginResult) {
        AccessToken token = loginResult.getAccessToken();
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        pluginImplementation.handleSuccessfulSignIn(savedCall, credential);
    }

    private void handleCancelCallback() {
        pluginImplementation.handleFailedSignIn(savedCall, "Facebook login canceled.", null);
    }

    private void handleErrorCallback(FacebookException exception) {
        pluginImplementation.handleFailedSignIn(savedCall, null, exception);
    }
}
