package dev.robingenz.capacitorjs.plugins.firebase.auth;

import com.getcapacitor.JSObject;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

public class FirebaseAuthenticationHelper {

    public static JSObject createGetCurrentUserResultFromFirebaseUser(FirebaseUser user) {
        JSObject result = new JSObject();
        if (user == null) {
            result.put("user", null);
            return result;
        }
        JSObject userResult = new JSObject();
        userResult.put("displayName", user.getDisplayName());
        userResult.put("email", user.getEmail());
        userResult.put("emailVerified", user.isEmailVerified());
        userResult.put("isAnonymous", user.isAnonymous());
        userResult.put("phoneNumber", user.getPhoneNumber());
        userResult.put("photoUrl", user.getPhotoUrl());
        userResult.put("providerId", user.getProviderId());
        userResult.put("tenantId", user.getTenantId());
        userResult.put("uid", user.getUid());
        result.put("user", userResult);
        return result;
    }

    public static JSObject createGetIdTokenResultFromFirebaseUser(FirebaseUser user, Boolean forceRefresh) {
        GetTokenResult tokenResult = user.getIdToken(forceRefresh).getResult();
        JSObject result = new JSObject();
        result.put("token", tokenResult.getToken());
        return result;
    }
}
