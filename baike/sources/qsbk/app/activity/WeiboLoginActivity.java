package qsbk.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.activity.security.AccessTokenKeeper;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.IMNotifyManager;
import qsbk.app.im.IMPreConnector;
import qsbk.app.thirdparty.ThirdOauth2AccessToken;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;

public class WeiboLoginActivity extends Activity {
    private ThirdParty a;
    private String b;
    private String c;
    private String d;
    private SsoHandler e;

    class a implements WbAuthListener {
        final /* synthetic */ WeiboLoginActivity a;

        a(WeiboLoginActivity weiboLoginActivity) {
            this.a = weiboLoginActivity;
        }

        public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
            AccessTokenKeeper.keepAccessToken(this.a, new ThirdOauth2AccessToken(oauth2AccessToken));
            this.a.b = oauth2AccessToken.getToken();
            this.a.d = oauth2AccessToken.getUid();
            this.a.c = oauth2AccessToken.getExpiresTime() + "";
            this.a.b();
        }

        public void cancel() {
        }

        public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "认证异常 : " + wbConnectErrorMessage.getErrorMessage(), Integer.valueOf(0)).show();
        }
    }

    private static void b(String str, String str2, String str3) {
        StringBuffer stringBuffer = new StringBuffer("accessToken=");
        stringBuffer.append(str);
        stringBuffer.append(com.alipay.sdk.sys.a.b);
        stringBuffer.append("expires_in=").append((Long.valueOf(str2).longValue() * 1000) + System.currentTimeMillis());
        SharePreferenceUtils.setSharePreferencesValue(str3, stringBuffer.toString());
    }

    private static void b(JSONObject jSONObject) {
        if (jSONObject != null) {
            QsbkApp.valuesMap = new HashMap();
            QsbkApp.getInstance().updateCurrentUserInfo(jSONObject);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = ThirdParty.getInstance(ThirdPartyConstants.SINA_CONSUMER_KEY, ThirdPartyConstants.SINA_REDIRECT_URL, ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
        this.e = new SsoHandler(this);
        this.e.authorize(new a(this));
    }

    private void a() {
        Intent intent = new Intent(this, FillUserDataActivity.class);
        intent.putExtra("type", ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
        intent.putExtra("token", this.b);
        intent.putExtra("expires_in", this.c);
        intent.putExtra("uid", this.d);
        startActivity(intent);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.e != null) {
            this.e.authorizeCallBack(i, i2, intent);
        }
    }

    private void b() {
        Map hashMap = new HashMap();
        hashMap.put("sns", ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
        hashMap.put("token", this.b);
        hashMap.put("expires_in", this.c);
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.THIRDPARTY_LOGIN, new agn(this));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.executeOnExecutor(SimpleHttpTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void c() {
        IMNotifyManager.getSettingFromCloud();
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(MainActivity.ACTION_QB_LOGIN));
        new IMPreConnector().preConnect("onLoginSuccess");
        new ago(this, "qsbk-LoginAct-Weibo").start();
    }
}
