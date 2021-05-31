import Foundation
import Capacitor
import FirebaseCore
import FirebaseAuth
import GoogleSignIn

class GoogleAuthProviderHandler: NSObject, AuthProviderHandler, GIDSignInDelegate {
    var plugin: FirebaseAuthentication? = nil
    
    init(plugin: FirebaseAuthentication) {
        super.init()
        self.plugin = plugin
        
        GIDSignIn.sharedInstance().clientID = FirebaseApp.app()?.options.clientID
        GIDSignIn.sharedInstance().delegate = self
        GIDSignIn.sharedInstance().presentingViewController = self.plugin?.bridge.viewController
    }
    
    func signIn(call: CAPPluginCall) -> Void {
        DispatchQueue.main.async {
            GIDSignIn.sharedInstance().signIn()
        }
    }
    
    func signOut() -> Void {
        GIDSignIn.sharedInstance().signOut()
    }
    
    public func sign(_ signIn: GIDSignIn!, didSignInFor user: GIDGoogleUser!, withError error: Error!) {
        if let error = error {
            self.plugin?.handleFailedSignIn(error: error)
            return
        }
        guard let authentication = user.authentication else {
            return
        }
        let credential = GoogleAuthProvider.credential(withIDToken: authentication.idToken, accessToken: authentication.accessToken)
        self.plugin?.handleSuccessfulSignIn(credential: credential)
    }
}
