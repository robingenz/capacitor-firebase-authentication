import Foundation
import Capacitor
import FirebaseCore
import FirebaseAuth

class OAuthProviderHandler: NSObject {
    var pluginImplementation: FirebaseAuthentication

    init(_ pluginImplementation: FirebaseAuthentication) {
        self.pluginImplementation = pluginImplementation
    }

    func signIn(call: CAPPluginCall, providerId: String) {
        let provider = OAuthProvider(providerID: providerId)
        DispatchQueue.main.async {
            self.startSignInFlow(provider: provider)
        }
    }

    private func startSignInFlow(provider: OAuthProvider) {
        provider.getCredentialWith(nil) { credential, error in
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
