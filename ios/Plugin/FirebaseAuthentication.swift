import Foundation
import Capacitor
import FirebaseCore
import FirebaseAuth

@objc public class FirebaseAuthentication: NSObject {
    public let errorDeviceUnsupported = "Device is not supported. At least iOS 13 is required."
    private let plugin: FirebaseAuthenticationPlugin
    private var appleAuthProviderHandler: AppleAuthProviderHandler?
    private var googleAuthProviderHandler: GoogleAuthProviderHandler?
    private var oAuthProviderHandler: OAuthProviderHandler?
    private var savedCall: CAPPluginCall?

    init(plugin: FirebaseAuthenticationPlugin) {
        self.plugin = plugin
        super.init()
        if FirebaseApp.app() == nil {
            FirebaseApp.configure()
        }
        self.appleAuthProviderHandler = AppleAuthProviderHandler(self)
        self.googleAuthProviderHandler = GoogleAuthProviderHandler(self)
        self.oAuthProviderHandler = OAuthProviderHandler(self)
    }

    @objc func getCurrentUser() -> User? {
        return Auth.auth().currentUser
    }

    @objc func getIdToken(_ forceRefresh: Bool, completion: @escaping (String?, Error?) -> Void) {
        let user = self.getCurrentUser()
        user?.getIDTokenResult(forcingRefresh: forceRefresh, completion: { result, error in
            if let error = error {
                completion(nil, error)
                return
            }
            completion(result?.token, nil)
        })
    }

    @objc func setLanguageCode(_ languageCode: String) {
        Auth.auth().languageCode = languageCode
    }

    @objc func signInWithApple(_ call: CAPPluginCall) {
        self.savedCall = call
        self.appleAuthProviderHandler?.signIn(call: call)
    }

    @objc func signInWithGithub(_ call: CAPPluginCall) {
        self.savedCall = call
        self.oAuthProviderHandler?.signIn(call: call, providerId: "github.com")
    }

    @objc func signInWithGoogle(_ call: CAPPluginCall) {
        self.savedCall = call
        self.googleAuthProviderHandler?.signIn(call: call)
    }

    @objc func signInWithMicrosoft(_ call: CAPPluginCall) {
        self.savedCall = call
        self.oAuthProviderHandler?.signIn(call: call, providerId: "microsoft.com")
    }

    @objc func signInWithTwitter(_ call: CAPPluginCall) {
        self.savedCall = call
        self.oAuthProviderHandler?.signIn(call: call, providerId: "twitter.com")
    }

    @objc func signInWithYahoo(_ call: CAPPluginCall) {
        self.savedCall = call
        self.oAuthProviderHandler?.signIn(call: call, providerId: "yahoo.com")
    }

    @objc func signOut(_ call: CAPPluginCall) {
        do {
            try Auth.auth().signOut()
            call.resolve()
        } catch let signOutError as NSError {
            call.reject("Error signing out: \(signOutError)")
        }
    }

    @objc func useAppLanguage() {
        Auth.auth().useAppLanguage()
    }

    func handleSuccessfulSignIn(credential: AuthCredential, rawNonce: String? = nil) {
        let skipNativeLogin = true
        var result = JSObject()
        
        result["credential"] = FirebaseAuthenticationHelper.createCredentialResultFromFirebaseCredential(credential)
        result["rawNonce"] = rawNonce
        
        if (skipNativeLogin) {
            savedCall?.resolve(result)
            return
        }
        
        Auth.auth().signIn(with: credential) { (_, error) in
            if let error = error {
                self.handleFailedSignIn(error: error)
                return
            }

            let user = self.getCurrentUser()
            result["user"] = FirebaseAuthenticationHelper.createUserResultFromFirebaseUser(user)

            self.savedCall?.resolve(result)
        }
    }

    func handleFailedSignIn(error: Error) {
        guard let savedCall = self.savedCall else {
            return
        }
        savedCall.reject(error.localizedDescription)
    }

    func getPlugin() -> FirebaseAuthenticationPlugin {
        return self.plugin
    }
}
