import Foundation
import Capacitor
import FirebaseCore
import FirebaseAuth
import GoogleSignIn

class GoogleIdentityProviderHandler: NSObject, IdentityProviderHandler, GIDSignInDelegate {
    var plugin: FirebaseAuthentication? = nil
    
    init(plugin: FirebaseAuthentication) {
        self.plugin = plugin
    }
    
    func initialize() -> Void {
        GIDSignIn.sharedInstance().clientID = FirebaseApp.app()?.options.clientID
        GIDSignIn.sharedInstance().delegate = self
        GIDSignIn.sharedInstance().presentingViewController = self.plugin?.bridge.viewController
        
        NotificationCenter.default.addObserver(self, selector: #selector(self.handleUrlOpen(notification:)), name: Notification.Name(CAPNotifications.URLOpen.name()), object: nil)
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
    
    @objc func handleUrlOpen(notification: Notification) -> Void {
        guard let object = notification.object as? [String:Any?] else {
            return
        }
        guard let url = object["url"] as? URL else {
            return
        }
        GIDSignIn.sharedInstance().handle(url)
    }
}
