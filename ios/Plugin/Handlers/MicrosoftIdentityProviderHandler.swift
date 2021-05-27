import Foundation

class MicrosoftIdentityProviderHandler: NSObject, IdentityProviderHandler {
    var plugin: FirebaseAuthentication? = nil
    var provider: OAuthProvider? = nil
    
    init(plugin: FirebaseAuthentication) {
        super.init()
        self.plugin = plugin

        self.provider = OAuthProvider(providerID: "microsoft.com")
    }
    
    func signIn(call: CAPPluginCall) -> Void {
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
    
    func signOut() -> Void {
        // Not needed.
    }
}
