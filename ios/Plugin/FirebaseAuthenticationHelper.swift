import Foundation
import Capacitor
import FirebaseCore
import FirebaseAuth

public class FirebaseAuthenticationHelper {
    public static func createSignInResult(_ credential: AuthCredential, _ user: User?) -> JSObject {
        let userResult = self.createUserResultFromFirebaseUser(user)
        let credentialResult = self.createCredentialResultFromAuthCredential(credential)
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

    public static func createCredentialResultFromAuthCredential(_ credential: AuthCredential?) -> JSObject? {
        if credential == nil {
            return nil
        }
        var result = JSObject()
        result["providerId"] = credential.provider
        if let oAuthCredential = credential as? OAuthCredential {
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
        }
        return result
    }
}
