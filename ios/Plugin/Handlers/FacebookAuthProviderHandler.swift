import Foundation
import Capacitor
import FirebaseAuth
import FBSDKLoginKit

class FacebookAuthProviderHandler: NSObject {
    var pluginImplementation: FirebaseAuthentication
    var loginManager: LoginManager

    init(_ pluginImplementation: FirebaseAuthentication) {
        self.pluginImplementation = pluginImplementation
        loginManager = LoginManager()
        super.init()
    }

    func signIn(call: CAPPluginCall) {
        DispatchQueue.main.async {
            self.loginManager.logIn(permissions: ["email", "public_profile"], from: self.pluginImplementation.getPlugin().bridge?.viewController) { result, error in
                if let error = error {
                    self.pluginImplementation.handleFailedSignIn(error: error)
                    return
                }

                guard let token = result?.token else { return }

                let credential = FacebookAuthProvider.credential(withAccessToken: token.tokenString)
                self.pluginImplementation.handleSuccessfulSignIn(credential: credential, nonce: nil)
            }
        }
    }

    func signOut() {
        loginManager.logOut()
    }
}
