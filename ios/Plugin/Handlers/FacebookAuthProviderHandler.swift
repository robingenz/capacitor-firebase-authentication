import Foundation
import Capacitor
import FirebaseAuth
import FBSDKLoginKit

class FacebookAuthProviderHandler: NSObject {
    public let errorSignInCanceled = "Sign in canceled."
    private var pluginImplementation: FirebaseAuthentication
    private var loginManager: LoginManager

    init(_ pluginImplementation: FirebaseAuthentication) {
        self.pluginImplementation = pluginImplementation
        loginManager = LoginManager()
        super.init()
    }

    func signIn(call: CAPPluginCall) {
        DispatchQueue.main.async {
            self.loginManager.logIn(permissions: ["email", "public_profile"], from: self.pluginImplementation.getPlugin().bridge?.viewController) { result, error in
                if let error = error {
                    self.pluginImplementation.handleFailedSignIn(message: nil, error: error)
                    return
                }

                guard let accessToken = result?.token else {
                    self.pluginImplementation.handleFailedSignIn(message: self.errorSignInCanceled, error: nil)
                    return
                }

                let token = accessToken.tokenString
                let credential = FacebookAuthProvider.credential(withAccessToken: token)
                self.pluginImplementation.handleSuccessfulSignIn(credential: credential, idToken: token, nonce: nil)
            }
        }
    }

    func signOut() {
        loginManager.logOut()
    }
}
