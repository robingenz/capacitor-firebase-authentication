import Foundation
import Capacitor
import FirebaseCore
import FirebaseAuth

public class FirebaseAuthenticationHelper {
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
    
    public static func createCredentialResultFromFirebaseCredential(_ credential: AuthCredential) -> JSObject? {
        if let credential = credential as? OAuthCredential {            
            var result = JSObject()
            result["providerId"] = credential.provider
            result["idToken"] = credential.idToken
            result["accessToken"] = credential.accessToken
            return result
        } else {
            var result = JSObject()
            result["providerId"] = credential.provider
            return result
        }
    }
}
