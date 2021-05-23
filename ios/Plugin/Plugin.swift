import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(FirebaseAuthentication)
public class FirebaseAuthentication: CAPPlugin {

    @objc func signIn(_ call: CAPPluginCall) {
        let provider = call.getString("provider") ?? ""
        call.success([
            "value": value
        ])
    }

    @objc func signOut(_ call: CAPPluginCall) {
        call.success()
    }
}
