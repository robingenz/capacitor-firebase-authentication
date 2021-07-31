import Foundation
import Capacitor
import FirebaseCore
import FirebaseAuth

public class FirebaseAuthenticationHelper {
    public static func createSignInResult(credential: AuthCredential, user: User?, nonce: String?) -> JSObject {
        let userResult = self.createUserResultFromFirebaseUser(user)
        let credentialResult = self.createCredentialResultFromAuthCredential(credential, nonce: nonce)
        var result = JSObject()
        result["user"] = userResult
        result["credential"] = credentialResult
        return result
    }

    public static func createUserResultFromFirebaseUser(_ user: User?) -> JSObject? {
        if user == nil {
            return nil
        }
        var result = JSObject()
        result["displayName"] = user?.displayName
        result["email"] = user?.email
        result["emailVerified"] = user?.isEmailVerified
        result["isAnonymous"] = user?.isAnonymous
        result["phoneNumber"] = user?.phoneNumber
        result["photoUrl"] = user?.photoURL?.absoluteString
        result["providerId"] = user?.providerID
        result["tenantId"] = user?.tenantID
        result["uid"] = user?.uid
        return result
    }

    public static func createCredentialResultFromAuthCredential(_ credential: AuthCredential?, nonce: String?) -> JSObject? {
        guard let credential = credential else {
            return nil
        }
        var result = JSObject()
        result["providerId"] = credential.provider
        
        if let oAuthCredential = credential as? OAuthCredential {
            // If it's OAuthCredential, we can get its values directly
            
            let accessToken = oAuthCredential.accessToken
            if accessToken != nil {
                result["accessToken"] = accessToken
            }
            let idToken = oAuthCredential.idToken
            if idToken != nil {
                result["idToken"] = idToken
            }
            let secret = oAuthCredential.secret
            if secret != nil {
                result["secret"] = secret
            }
        } else {
            // However, a AuthCredential doesn't expose idToken/accessToken that
            // we need. So we use KVC to try and get them.
        
            if let idToken = credential.value(forKey: "_IDToken") as? String {
                result["idToken"] = idToken
            }
            if let accessToken = credential.value(forKey: "_accessToken") as? String {
                result["accessToken"] = accessToken
            }
        }
        
        if let nonce = nonce {
            result["nonce"] = nonce
        }
        return result
    }
}
