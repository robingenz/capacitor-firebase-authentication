import Foundation
import Capacitor
import FirebaseCore
import FirebaseAuth

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(FirebaseAuthentication)
public class FirebaseAuthentication: CAPPlugin {
    public let errorDeviceUnsupported = "Device is not supported. At least iOS 13 is required."
    var authProviderHandlers = [AuthProvider : AuthProviderHandler]()
    var savedCall: CAPPluginCall? = nil
    
    public override func load() {
        if (FirebaseApp.app() == nil) {
            FirebaseApp.configure()
        }
        authProviderHandlers[AuthProvider.Apple] = AppleAuthProviderHandler(plugin: self)
        authProviderHandlers[AuthProvider.Google] = GoogleAuthProviderHandler(plugin: self)
        authProviderHandlers[AuthProvider.Microsoft] = MicrosoftAuthProviderHandler(plugin: self)
    }

    @objc func signInWithApple(_ call: CAPPluginCall) {
        self.savedCall = call
        authProviderHandlers[AuthProvider.Apple]?.signIn(call: call)
    }
    
    @objc func signInWithGoogle(_ call: CAPPluginCall) {
        self.savedCall = call
        authProviderHandlers[AuthProvider.Google]?.signIn(call: call)
    }
    
    @objc func signInWithMicrosoft(_ call: CAPPluginCall) {
        self.savedCall = call
        authProviderHandlers[AuthProvider.Microsoft]?.signIn(call: call)
    }

    @objc func signOut(_ call: CAPPluginCall) {
        let firebaseAuth = Auth.auth()
        do {
            try firebaseAuth.signOut()
            for handler in self.authProviderHandlers.values {
                handler.signOut()
            }
            call.success()
        } catch let signOutError as NSError {
            call.reject("Error signing out: \(signOutError)")
        }
    }
    
    func handleSuccessfulSignIn(credential: AuthCredential) -> Void {
        Auth.auth().signIn(with: credential) { (authDataResult, error) in
            if let error = error {
                self.handleFailedSignIn(error: error)
                return
            }
            guard let user = authDataResult?.user else {
                return
            }
            guard let savedCall = self.savedCall else {
                return
            }
            self.createSignInResultFrom(user: user, completion: { signInResult, error in
                if let error = error {
                    savedCall.reject(error.localizedDescription)
                    return;
                }
                savedCall.resolve(signInResult as PluginResultData)
            })
        }
    }
    
    func handleFailedSignIn(error: Error) -> Void {
        guard let savedCall = self.savedCall else {
            return
        }
        savedCall.reject(error.localizedDescription)
    }
    
    private func createSignInResultFrom(user: User, completion: @escaping ([String:Any?], Error?) -> Void) -> Void {
        user.getIDTokenResult(forcingRefresh: false, completion: { result, error in
            if let error = error {
                completion([:], error);
                return
            }
            let result = [
                "idToken": result?.token ?? "",
                "uid": user.uid,
                "email": user.email ?? "",
                "displayName": user.displayName ?? ""
            ] as [String : Any]
            completion(result, nil)
        })
    }
}
