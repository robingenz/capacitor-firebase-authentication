import Foundation
import Capacitor

protocol IdentityProviderHandler {
    func initialize() -> Void
    func signIn(call: CAPPluginCall) -> Void
    func signOut() -> Void
}
