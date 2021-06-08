import Foundation
import Capacitor
import FirebaseCore
import FirebaseAuth

@objc public class FirebaseAuthentication: NSObject {
    public let errorDeviceUnsupported = "Device is not supported. At least iOS 13 is required."
    private let plugin: FirebaseAuthenticationPlugin
    private var appleAuthProviderHandler: AppleAuthProviderHandler? = nil
    private var googleAuthProviderHandler: GoogleAuthProviderHandler? = nil
    private var microsoftAuthProviderHandler: MicrosoftAuthProviderHandler? = nil
    private var savedCall: CAPPluginCall? = nil
    
    init(plugin: FirebaseAuthenticationPlugin) {
        self.plugin = plugin
        super.init()
        if (FirebaseApp.app() == nil) {
            FirebaseApp.configure()
        }
        self.appleAuthProviderHandler = AppleAuthProviderHandler(self)
        self.googleAuthProviderHandler = GoogleAuthProviderHandler(self)
        self.microsoftAuthProviderHandler = MicrosoftAuthProviderHandler(self)
    }

    @objc func signInWithApple(_ call: CAPPluginCall) {
        self.savedCall = call
        self.appleAuthProviderHandler?.signIn(call: call)
    }
    
    @objc func signInWithGoogle(_ call: CAPPluginCall) {
        self.savedCall = call
        self.googleAuthProviderHandler?.signIn(call: call)
    }
    
    @objc func signInWithMicrosoft(_ call: CAPPluginCall) {
        self.savedCall = call
        self.microsoftAuthProviderHandler?.signIn(call: call)
    }

    @objc func signOut(_ call: CAPPluginCall) {
        do {
            try Auth.auth().signOut()
            call.resolve()
        } catch let signOutError as NSError {
            call.reject("Error signing out: \(signOutError)")
        }
    }
    
    func handleSuccessfulSignIn(credential: AuthCredential) -> Void {
        Auth.auth().signIn(with: credential) { (authDataResult, error) in
            if let error = error {
                self.handleFailedSignIn(error: error)
                return
            }
            guard let savedCall = self.savedCall else {
                return
            }
            savedCall.resolve()
        }
    }
    
    func handleFailedSignIn(error: Error) -> Void {
        guard let savedCall = self.savedCall else {
            return
        }
        savedCall.reject(error.localizedDescription)
    }
    
    func getPlugin() -> FirebaseAuthenticationPlugin {
        return self.plugin
    }
}
