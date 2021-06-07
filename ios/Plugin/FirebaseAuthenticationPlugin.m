#import <Foundation/Foundation.h>
#import <Capacitor/Capacitor.h>

// Define the plugin using the CAP_PLUGIN Macro, and
// each method the plugin supports using the CAP_PLUGIN_METHOD macro.
CAP_PLUGIN(FirebaseAuthenticationPlugin, "FirebaseAuthentication",
           CAP_PLUGIN_METHOD(signInWithApple, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(signInWithGoogle, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(signInWithMicrosoft, CAPPluginReturnPromise);
           CAP_PLUGIN_METHOD(signOut, CAPPluginReturnPromise);
)
