package qsbk.app.share;

import android.content.Intent;
import android.os.Bundle;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.security.AccessTokenKeeper;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.thirdparty.ThirdOauth2AccessToken;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;

public class AuthorizeActivity extends BaseActionBarActivity {
    public static ThirdOauth2AccessToken accessToken;
    SsoHandler a;
    int b = 0;

    class a implements WbAuthListener {
        final /* synthetic */ AuthorizeActivity a;

        a(AuthorizeActivity authorizeActivity) {
            this.a = authorizeActivity;
        }

        public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
            AuthorizeActivity.accessToken = new ThirdOauth2AccessToken(oauth2AccessToken);
            AccessTokenKeeper.keepAccessToken(this.a, AuthorizeActivity.accessToken);
            this.a.a(oauth2AccessToken.getToken(), oauth2AccessToken.getExpiresTime() + "", QsbkApp.currentUser.userId + "_sina_access_token");
            this.a.setResult(this.a.b, new Intent());
            this.a.finish();
        }

        public void cancel() {
            ToastAndDialog.makeNegativeToast(this.a.getApplicationContext(), "取消认证", Integer.valueOf(1)).show();
            this.a.finish();
        }

        public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
            ToastAndDialog.makeNegativeToast(this.a.getApplicationContext(), "认证异常 : " + wbConnectErrorMessage.getErrorMessage(), Integer.valueOf(1)).show();
        }
    }

    private void a(String str, String str2, String str3) {
        StringBuffer stringBuffer = new StringBuffer("accessToken=");
        stringBuffer.append(str);
        stringBuffer.append(com.alipay.sdk.sys.a.b);
        stringBuffer.append("expires_in=").append((Long.valueOf(str2).longValue() * 1000) + System.currentTimeMillis());
        SharePreferenceUtils.setSharePreferencesValue(str3, stringBuffer.toString());
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.a != null) {
            this.a.authorizeCallBack(i, i2, intent);
        }
    }

    public String getCustomTitle() {
        if ("sina".equals(getIntent().getStringExtra(QsbkDatabase.TARGET))) {
            return "糗事百科-新浪微博";
        }
        return "糗事百科-人人网";
    }

    protected int a() {
        return R.layout.weibo_authorize;
    }

    protected void a(Bundle bundle) {
        this.b = getIntent().getIntExtra("resultCode", 0);
        setActionbarBackable();
        ThirdParty.getInstance(ThirdPartyConstants.SINA_CONSUMER_KEY, ThirdPartyConstants.SINA_REDIRECT_URL, ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
        LogUtil.d("in AuthroziActivity");
        this.a = new SsoHandler(this);
        this.a.authorize(new a(this));
    }
}
