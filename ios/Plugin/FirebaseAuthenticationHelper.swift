import Foundation
import Capacitor
import FirebaseCore
import FirebaseAuth

public class FirebaseAuthenticationHelper {
    public static func createUserResultFromFirebaseUser(_ user: User?) -> JSObject? {
        if (user == nil) {
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
    
    public static func createGetIdTokenResultFromFirebaseUser(_ user: User?, forceRefresh: Bool, completion: @escaping (JSObject, Error?) -> Void) -> Void {
        user?.getIDTokenResult(forcingRefresh: forceRefresh, completion: { result, error in
            if let error = error {
                completion([:], error);
                return
            }
            let result = [
                "token": result?.token ?? ""
            ]
            completion(result, nil)
        })
    }
}
