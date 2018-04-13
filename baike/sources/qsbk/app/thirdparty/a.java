package qsbk.app.thirdparty;

import android.os.Bundle;
import android.util.Log;

class a implements ThirdPartyAuthListener {
    final /* synthetic */ ThirdPartyAuthListener a;
    final /* synthetic */ ThirdParty b;

    a(ThirdParty thirdParty, ThirdPartyAuthListener thirdPartyAuthListener) {
        this.b = thirdParty;
        this.a = thirdPartyAuthListener;
    }

    public void onComplete(Bundle bundle) {
        if (this.b.accessToken == null) {
            this.b.accessToken = new ThirdOauth2AccessToken();
        }
        this.b.accessToken.setToken(bundle.getString("access_token"));
        this.b.accessToken.setExpiresIn(bundle.getString("expires_in"));
        this.b.accessToken.setRefreshToken(bundle.getString("refresh_token"));
        if (this.b.accessToken.isSessionValid()) {
            Log.d("ThirdParty-authorize", "Login Success! access_token=" + this.b.accessToken.getToken() + " expires=" + this.b.accessToken.getExpiresTime() + " refresh_token=" + this.b.accessToken.getRefreshToken());
            this.a.onComplete(bundle);
            return;
        }
        Log.d("ThirdParty-authorize", "Failed to receive access token");
        this.a.onThirdPartyException(new ThirdPartyException("Failed to receive access token."));
    }

    public void onError(ThirdPartyDialogError thirdPartyDialogError) {
        Log.d("ThirdParty-authorize", "Login failed: " + thirdPartyDialogError + "  url=" + thirdPartyDialogError.a());
        this.a.onError(thirdPartyDialogError);
    }

    public void onThirdPartyException(ThirdPartyException thirdPartyException) {
        Log.d("ThirdParty-authorize", "Login failed: " + thirdPartyException);
        this.a.onThirdPartyException(thirdPartyException);
    }

    public void onCancel() {
        Log.d("ThirdParty-authorize", "Login canceled");
        this.a.onCancel();
    }
}
