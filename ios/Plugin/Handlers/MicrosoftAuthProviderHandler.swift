import Foundation
import Capacitor
import FirebaseCore
import FirebaseAuth

class MicrosoftAuthProviderHandler: NSObject, AuthProviderHandler {
    var plugin: FirebaseAuthentication? = nil
    var provider: OAuthProvider? = nil
    
    init(plugin: FirebaseAuthentication) {
        super.init()
        self.plugin = plugin

        self.provider = OAuthProvider(providerID: "microsoft.com")
    }
    
    func signIn(call: CAPPluginCall) -> Void {
        DispatchQueue.main.async {
            self.startSignInFlow()
        }
    }
    
    func signOut() -> Void {
        // Not needed.
    }

    private func startSignInFlow() -> Void {
        self.provider?.getCredentialWith(nil) { credential, error in
            if let error = error {
                self.plugin?.handleFailedSignIn(error: error)
                return
            }
            if let credential = credential {
                self.plugin?.handleSuccessfulSignIn(credential: credential)
            }
        }
    }
}
