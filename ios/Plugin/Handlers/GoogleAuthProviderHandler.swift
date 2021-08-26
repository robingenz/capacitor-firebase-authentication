import Foundation
import Capacitor
import FirebaseCore
import FirebaseAuth
import GoogleSignIn

class GoogleAuthProviderHandler: NSObject {
    var pluginImplementation: FirebaseAuthentication

    init(_ pluginImplementation: FirebaseAuthentication) {
        self.pluginImplementation = pluginImplementation
        super.init()
    }

    func signIn(call: CAPPluginCall) {
        guard let clientId = FirebaseApp.app()?.options.clientID else { return }
        let config = GIDConfiguration(clientID: clientId)
        guard let controller = self.pluginImplementation.getPlugin().bridge?.viewController else { return }

        DispatchQueue.main.async {
            GIDSignIn.sharedInstance.signIn(with: config, presenting: controller) { user, error in
                if let error = error {
                    self.pluginImplementation.handleFailedSignIn(message: nil, error: error)
                    return
                }

                guard let authentication = user?.authentication else { return }
                guard let idToken = authentication.idToken else { return }

                let credential = GoogleAuthProvider.credential(withIDToken: idToken, accessToken: authentication.accessToken)
                self.pluginImplementation.handleSuccessfulSignIn(credential: credential, idToken: idToken, nonce: nil)
            }
        }
    }

    func signOut() {
        GIDSignIn.sharedInstance.signOut()
    }
}
