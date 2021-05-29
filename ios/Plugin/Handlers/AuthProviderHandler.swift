import Foundation
import Capacitor

protocol AuthProviderHandler {
    func signIn(call: CAPPluginCall) -> Void
    func signOut() -> Void
}
