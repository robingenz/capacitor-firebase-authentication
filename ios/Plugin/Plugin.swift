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
    public let errorProviderMissing = "provider must be provided."
    public let errorProviderNotSupported = "provider is not supported."
    var identiyProviderHandlers = [IdentityProvider : IdentityProviderHandler]()
    var savedCall: CAPPluginCall? = nil
    
    public override func load() {
        if (FirebaseApp.app() == nil) {
            FirebaseApp.configure()
        }
        identiyProviderHandlers[IdentityProvider.Apple] = AppleIdentityProviderHandler(plugin: self)
        identiyProviderHandlers[IdentityProvider.Google] = GoogleIdentityProviderHandler(plugin: self)
        identiyProviderHandlers[IdentityProvider.Microsoft] = MicrosoftIdentityProviderHandler(plugin: self)
    }

    @objc func signIn(_ call: CAPPluginCall) {
        guard let provider = call.getString("provider") else {
            call.reject(errorProviderMissing)
            return
        }
        
        let parsedProvider = self.parseProvider(provider)
        if parsedProvider == IdentityProvider.Unknown {
            call.reject(errorProviderNotSupported)
            return
        }
        
        if let user = Auth.auth().currentUser {
            self.createSignInResultFrom(user: user, completion: { signInResult, error in
                if let error = error {
                    call.reject(error.localizedDescription)
                    return
                }
                call.resolve(signInResult as PluginResultData)
            })
            return;
        }
        
        self.savedCall = call
        
        identiyProviderHandlers[parsedProvider]?.signIn(call: call)
    }

    @objc func signOut(_ call: CAPPluginCall) {
        let firebaseAuth = Auth.auth()
        do {
            try firebaseAuth.signOut()
            for handler in self.identiyProviderHandlers.values {
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
    
    private func parseProvider(_ provider: String) -> IdentityProvider {
        switch provider {
        case "apple":
            return IdentityProvider.Apple
        case "google":
            return IdentityProvider.Google
        case "microsoft":
            return IdentityProvider.Microsoft
        default:
            return IdentityProvider.Unknown
        }
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
