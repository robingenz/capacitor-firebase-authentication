package dev.robingenz.capacitorjs.plugins.firebase.auth;

public interface GetIdTokenResultCallback {
    void success(String token);
    void error(String message);
}
