import Foundation
import Capacitor
import FirebaseCore
import FirebaseAuth

class OAuthProviderHandler: NSObject {
    var pluginImplementation: FirebaseAuthentication
    var provider: OAuthProvider?

    init(_ pluginImplementation: FirebaseAuthentication) {
        self.pluginImplementation = pluginImplementation
    }

    func signIn(call: CAPPluginCall, providerId: String) {
        self.provider = OAuthProvider(providerID: providerId)
        DispatchQueue.main.async {
            self.startSignInFlow()
        }
    }

    private func startSignInFlow() {
        self.provider?.getCredentialWith(nil) { credential, error in
            if let error = error {
                self.pluginImplementation.handleFailedSignIn(error: error)
                return
            }
            if let credential = credential {
                self.pluginImplementation.handleSuccessfulSignIn(credential: credential, nonce: nil)
            }
        }
    }
}
