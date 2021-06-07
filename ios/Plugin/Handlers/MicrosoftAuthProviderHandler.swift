import Foundation
import Capacitor
import FirebaseCore
import FirebaseAuth

class MicrosoftAuthProviderHandler: NSObject {
    var pluginImplementation: FirebaseAuthentication
    var provider: OAuthProvider
    
    init(_ pluginImplementation: FirebaseAuthentication) {
        self.pluginImplementation = pluginImplementation
        self.provider = OAuthProvider(providerID: "microsoft.com")
    }
    
    func signIn(call: CAPPluginCall) -> Void {
        DispatchQueue.main.async {
            self.startSignInFlow()
        }
    }

    private func startSignInFlow() -> Void {
        self.provider.getCredentialWith(nil) { credential, error in
            if let error = error {
                self.pluginImplementation.handleFailedSignIn(error: error)
                return
            }
            if let credential = credential {
                self.pluginImplementation.handleSuccessfulSignIn(credential: credential)
            }
        }
    }
}
