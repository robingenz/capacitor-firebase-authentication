package dev.robingenz.capacitorjs.plugins.firebase.auth.handlers;

import androidx.annotation.NonNull;
import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import dev.robingenz.capacitorjs.plugins.firebase.auth.FirebaseAuthentication;
import dev.robingenz.capacitorjs.plugins.firebase.auth.FirebaseAuthenticationHelper;

import java.util.concurrent.TimeUnit;

public class PhoneAuthProviderHandler {

    private FirebaseAuthentication pluginImplementation;

    public PhoneAuthProviderHandler(FirebaseAuthentication pluginImplementation) {
        this.pluginImplementation = pluginImplementation;
    }

    public void signIn(PluginCall call) {
        String phoneNumber = call.getString("phoneNumber");
        String verificationId = call.getString("verificationId");
        String verificationCode = call.getString("verificationCode");

        if (verificationCode == null) {
            verifyPhoneNumber(call, phoneNumber);
        } else {
            handleVerificationCode(call, verificationId, verificationCode);
        }
    }

    private void verifyPhoneNumber(PluginCall call, String phoneNumber) {
        PhoneAuthOptions.Builder builder = PhoneAuthOptions
            .newBuilder(pluginImplementation.getFirebaseAuthInstance())
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(pluginImplementation.getPlugin().getActivity())
            .setCallbacks(createCallbacks(call));
        PhoneAuthOptions options = builder.build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void handleVerificationCode(PluginCall call, String verificationId, String verificationCode) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, verificationCode);
        pluginImplementation.handleSuccessfulSignIn(call, credential);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks createCallbacks(PluginCall call) {
        return new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                pluginImplementation.handleSuccessfulSignIn(call, credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException exception) {
                pluginImplementation.handleFailedSignIn(call, null, exception);
            }

            @Override
            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                JSObject ret = new JSObject();
                ret.put("verificationId", verificationId);
                pluginImplementation.getPlugin().notifyListeners("phoneCodeSent", ret);
                boolean skipNativeAuth = pluginImplementation.getConfig().getSkipNativeAuth();
                if (skipNativeAuth) {
                    pluginImplementation.handleSuccessfulSignIn(call, null);
                }
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String verificationId) {
                pluginImplementation.handleFailedSignIn(call, "The auto-verification period has timed out.", null);
            }
        };
    }
}
