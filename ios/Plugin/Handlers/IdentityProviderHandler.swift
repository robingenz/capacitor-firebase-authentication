import Foundation
import Capacitor

protocol IdentityProviderHandler {
    func signIn(call: CAPPluginCall) -> Void
    func signOut() -> Void
}
