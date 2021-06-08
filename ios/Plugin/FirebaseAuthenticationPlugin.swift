import Foundation
import Capacitor

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
        var user = implementation?.getCurrentUser()
        var result = self.createGetCurrentUserResultFromFirebaseUser(user)
        return result
    }

    @objc func getIdToken(_ call: CAPPluginCall) {
        var user = implementation?.getCurrentUser()
        self.createGetIdTokenResultFromFirebaseUser(user, completion: { result, error in
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
    
    private func createGetCurrentUserResultFromFirebaseUser(_ user: User) -> ) -> JSObject {
        let result = JSObject()
        if (user == nil) {
            result["user"] = nil
            return result
        }
        let userResult = [
            "displayName": user.displayName ?? "",
            "email": user.email ?? "",
            "emailVerified": user.emailVerified ?? "",
            "isAnonymous": user.isAnonymous ?? "",
            "phoneNumber": user.phoneNumber ?? "",
            "photoUrl": user.photoUrl ?? "",
            "providerId": user.providerId ?? "",
            "tenantId": user.tenantId ?? "",
            "uid": user.uid,
        ]
        result["user"] = userResult
        return result
    }
    
    private func createGetIdTokenResultFromFirebaseUser(_ user: User, completion: @escaping (JSObject, Error?) -> Void) -> Void {
        user.getIDTokenResult(forcingRefresh: false, completion: { result, error in
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
