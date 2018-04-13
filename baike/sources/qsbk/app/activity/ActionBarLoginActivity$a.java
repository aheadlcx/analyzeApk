package qsbk.app.activity;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import qsbk.app.activity.security.AccessTokenKeeper;
import qsbk.app.thirdparty.ThirdOauth2AccessToken;
import qsbk.app.utils.ToastAndDialog;

class ActionBarLoginActivity$a implements WbAuthListener {
    final /* synthetic */ ActionBarLoginActivity a;

    ActionBarLoginActivity$a(ActionBarLoginActivity actionBarLoginActivity) {
        this.a = actionBarLoginActivity;
    }

    public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
        ActionBarLoginActivity.b(this.a, oauth2AccessToken.getToken());
        ActionBarLoginActivity.e(this.a, oauth2AccessToken.getUid());
        ActionBarLoginActivity.c(this.a, oauth2AccessToken.getExpiresTime() + "");
        ActionBarLoginActivity.accessToken = new ThirdOauth2AccessToken(oauth2AccessToken);
        AccessTokenKeeper.keepAccessToken(this.a, ActionBarLoginActivity.accessToken);
        ActionBarLoginActivity.b(this.a, oauth2AccessToken.getToken(), oauth2AccessToken.getExpiresTime() + "", null);
    }

    public void cancel() {
    }

    public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
        ToastAndDialog.makeNegativeToast(this.a.getApplicationContext(), "认证异常 : " + wbConnectErrorMessage.getErrorMessage(), Integer.valueOf(1)).show();
    }
}
