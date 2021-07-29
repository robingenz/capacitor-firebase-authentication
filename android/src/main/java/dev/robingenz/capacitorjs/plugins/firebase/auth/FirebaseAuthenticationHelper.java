package dev.robingenz.capacitorjs.plugins.firebase.auth;

import android.util.Log;
import com.getcapacitor.JSObject;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.OAuthCredential;
import java.lang.reflect.Method;

public class FirebaseAuthenticationHelper {

    public static final String TAG = "FirebaseAuthentication";

    public static JSObject createSignInResult(FirebaseUser user, AuthCredential credential) {
        JSObject userResult = FirebaseAuthenticationHelper.createUserResultFromFirebaseUser(user);
        JSObject credentialResult = FirebaseAuthenticationHelper.createCredentialResultFromAuthCredential(credential);
        JSObject result = new JSObject();
        result.put("user", userResult);
        result.put("credential", credentialResult);
        return result;
    }

    public static JSObject createUserResultFromFirebaseUser(FirebaseUser user) {
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

    public static JSObject createCredentialResultFromAuthCredential(AuthCredential credential) {
        if (credential == null) {
            return null;
        }
        JSObject result = new JSObject();
        result.put("providerId", credential.getProvider());
        if (credential instanceof OAuthCredential) {
            String accessToken = ((OAuthCredential) credential).getAccessToken();
            if (accessToken != null) {
                result.put("accessToken", accessToken);
            }
            String idToken = ((OAuthCredential) credential).getIdToken();
            if (idToken != null) {
                result.put("idToken", idToken);
            }
            String secret = ((OAuthCredential) credential).getSecret();
            if (secret != null) {
                result.put("secret", secret);
            }
        }
        if (credential.getProvider().equals("apple.com")) {
            String rawNonce = FirebaseAuthenticationHelper.tryGetRawNonceFromAuthCredential(credential);
            if (rawNonce != null) {
                result.put("nonce", rawNonce);
            }
        }
        return result;
    }

    private static String tryGetRawNonceFromAuthCredential(AuthCredential credential) {
        try {
            Method method = credential.getClass().getMethod("getRawNonce");
            return (String) method.invoke(credential);
        } catch (Exception ex) {
            Log.w(TAG, "Try to get raw nonce from auth credential failed.", ex);
            return null;
        }
    }
}
