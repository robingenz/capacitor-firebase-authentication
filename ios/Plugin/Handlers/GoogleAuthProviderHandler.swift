import Foundation
import Capacitor
import FirebaseCore
import FirebaseAuth
import GoogleSignIn

class GoogleAuthProviderHandler: NSObject, GIDSignInDelegate {
    var pluginImplementation: FirebaseAuthentication

    init(_ pluginImplementation: FirebaseAuthentication) {
        self.pluginImplementation = pluginImplementation
        super.init()

        GIDSignIn.sharedInstance().clientID = FirebaseApp.app()?.options.clientID
        GIDSignIn.sharedInstance().delegate = self
        GIDSignIn.sharedInstance().presentingViewController = self.pluginImplementation.getPlugin().bridge?.viewController
    }

    func signIn(call: CAPPluginCall) {
        DispatchQueue.main.async {
            GIDSignIn.sharedInstance().signIn()
        }
    }

    func signOut() {
        GIDSignIn.sharedInstance().signOut()
    }

    public func sign(_ signIn: GIDSignIn!, didSignInFor user: GIDGoogleUser!, withError error: Error!) {
        if let error = error {
            self.pluginImplementation.handleFailedSignIn(error: error)
            return
        }
        guard let authentication = user.authentication else {
            return
        }
        let credential = GoogleAuthProvider.credential(withIDToken: authentication.idToken, accessToken: authentication.accessToken)
        self.pluginImplementation.handleSuccessfulSignIn(credential: credential)
    }
}
