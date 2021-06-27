import Foundation
import Capacitor
import FirebaseCore
import FirebaseAuth

public class FirebaseAuthenticationHelper {
    public static func createGetCurrentUserResultFromFirebaseUser(_ user: User?) -> JSObject {
        var result = JSObject()
        if (user == nil) {
            result["user"] = nil
            return result
        }
        var userResult = JSObject()
        userResult["displayName"] = user?.displayName
        userResult["email"] = user?.email
        userResult["emailVerified"] = user?.isEmailVerified
        userResult["isAnonymous"] = user?.isAnonymous
        userResult["phoneNumber"] = user?.phoneNumber
        userResult["photoUrl"] = user?.photoURL?.absoluteString
        userResult["providerId"] = user?.providerID
        userResult["tenantId"] = user?.tenantID
        userResult["uid"] = user?.uid
        result["user"] = userResult
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
