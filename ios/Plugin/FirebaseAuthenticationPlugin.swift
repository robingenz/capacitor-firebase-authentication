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
        let result = FirebaseAuthenticationHelper.createGetCurrentUserResultFromFirebaseUser(user)
        call.resolve(result)
    }

    @objc func getIdToken(_ call: CAPPluginCall) {
        let forceRefresh = call.getBool("forceRefresh", false)
        let user = implementation?.getCurrentUser()
        FirebaseAuthenticationHelper.createGetIdTokenResultFromFirebaseUser(user, forceRefresh: forceRefresh, completion: { result, error in
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
}
