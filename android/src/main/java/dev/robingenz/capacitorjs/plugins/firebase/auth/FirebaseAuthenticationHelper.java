package dev.robingenz.capacitorjs.plugins.firebase.auth;

import com.getcapacitor.JSObject;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.OAuthCredential;
import com.google.firebase.auth.PhoneAuthCredential;
import dev.robingenz.capacitorjs.plugins.firebase.auth.handlers.GoogleAuthProviderHandler;
import dev.robingenz.capacitorjs.plugins.firebase.auth.handlers.OAuthProviderHandler;

public class FirebaseAuthenticationHelper {

    public static JSObject createSignInResult(FirebaseUser user, AuthCredential credential, String idToken) {
        JSObject userResult = FirebaseAuthenticationHelper.createUserResult(user);
        JSObject credentialResult = FirebaseAuthenticationHelper.createCredentialResult(credential, idToken);
        JSObject result = new JSObject();
        result.put("user", userResult);
        result.put("credential", credentialResult);
        return result;
    }

    public static JSObject createUserResult(FirebaseUser user) {
        if (user == null) {
            return null;
        }
        JSObject result = new JSObject();
        result.put("displayName", user.getDisplayName());
        result.put("email", user.getEmail());
        result.put("emailVerified", user.isEmailVerified());
        result.put("isAnonymous", user.isAnonymous());
        result.put("phoneNumber", user.getPhoneNumber());
        result.put("photoUrl", user.getPhotoUrl());
        result.put("providerId", user.getProviderId());
        result.put("tenantId", user.getTenantId());
        result.put("uid", user.getUid());
        return result;
    }

    public static JSObject createCredentialResult(AuthCredential credential, String idToken) {
        if (credential == null && idToken == null) {
            return null;
        }
        JSObject result = new JSObject();
        if (credential != null) {
            result.put("providerId", credential.getProvider());
        }
        if (credential instanceof OAuthCredential) {
            String oAuthAccessToken = ((OAuthCredential) credential).getAccessToken();
            if (oAuthAccessToken != null) {
                result.put("accessToken", oAuthAccessToken);
            }
            String oAuthIdToken = ((OAuthCredential) credential).getIdToken();
            if (oAuthIdToken != null) {
                result.put("idToken", oAuthIdToken);
            }
            String oAuthSecret = ((OAuthCredential) credential).getSecret();
            if (oAuthSecret != null) {
                result.put("secret", oAuthSecret);
            }
        } else if (idToken != null) {
            result.put("idToken", idToken);
        }
        return result;
    }
}
