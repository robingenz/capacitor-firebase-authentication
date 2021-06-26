import Foundation
import Capacitor
import FirebaseCore
import FirebaseAuth

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(FirebaseAuthenticationPlugin)
public class FirebaseAuthenticationPlugin: CAPPlugin {
    private var implementation: FirebaseAuthentication? = nil
    
    override public func load() {
        self.implementation = FirebaseAuthentication(plugin: self)
    }

    @objc func getCurrentUser(_ call: CAPPluginCall) {
        let user = implementation?.getCurrentUser()
        let result = self.createGetCurrentUserResultFromFirebaseUser(user)
        call.resolve(result)
    }

    @objc func getIdToken(_ call: CAPPluginCall) {
        let forceRefresh = call.getBool("forceRefresh", false)
        let user = implementation?.getCurrentUser()
        self.createGetIdTokenResultFromFirebaseUser(user, forceRefresh: forceRefresh, completion: { result, error in
            if let error = error {
                call.reject(error.localizedDescription)
                return;
            }
            call.resolve(result)
        })
    }

    @objc func signInWithApple(_ call: CAPPluginCall) {
        implementation?.signInWithApple(call)
    }
    
    @objc func signInWithGoogle(_ call: CAPPluginCall) {
        implementation?.signInWithGoogle(call)
    }
    
    @objc func signInWithMicrosoft(_ call: CAPPluginCall) {
        implementation?.signInWithMicrosoft(call)
    }
    
    @objc func signOut(_ call: CAPPluginCall) {
        implementation?.signOut(call)
    }
    
    private func createGetCurrentUserResultFromFirebaseUser(_ user: User?) -> JSObject {
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
    
    private func createGetIdTokenResultFromFirebaseUser(_ user: User?, forceRefresh: Bool, completion: @escaping (JSObject, Error?) -> Void) -> Void {
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
