package android.support.v4.hardware.fingerprint;

import android.hardware.fingerprint.FingerprintManager.AuthenticationCallback;
import android.hardware.fingerprint.FingerprintManager.AuthenticationResult;

final class a extends AuthenticationCallback {
    final /* synthetic */ FingerprintManagerCompat.AuthenticationCallback a;

    a(FingerprintManagerCompat.AuthenticationCallback authenticationCallback) {
        this.a = authenticationCallback;
    }

    public void onAuthenticationError(int i, CharSequence charSequence) {
        this.a.onAuthenticationError(i, charSequence);
    }

    public void onAuthenticationHelp(int i, CharSequence charSequence) {
        this.a.onAuthenticationHelp(i, charSequence);
    }

    public void onAuthenticationSucceeded(AuthenticationResult authenticationResult) {
        this.a.onAuthenticationSucceeded(new FingerprintManagerCompat.AuthenticationResult(FingerprintManagerCompat.b(authenticationResult.getCryptoObject())));
    }

    public void onAuthenticationFailed() {
        this.a.onAuthenticationFailed();
    }
}
