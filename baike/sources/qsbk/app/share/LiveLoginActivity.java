package qsbk.app.share;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.activity.MainActivity;
import qsbk.app.activity.security.AccessTokenKeeper;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.IMNotifyManager;
import qsbk.app.im.IMPreConnector;
import qsbk.app.thirdparty.ThirdOauth2AccessToken;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.wxapi.WXAuthHelper;
import qsbk.app.wxapi.WXAuthHelper.OnWXAuthListener;
import qsbk.app.wxapi.WXAuthHelper.WXAuthException;
import qsbk.app.wxapi.WXAuthHelper.WXAuthToken;

public class LiveLoginActivity extends Activity implements OnClickListener {
    public static int LOGIN_QB = 782;
    public static int LOGIN_QQ = 779;
    public static int LOGIN_WB = 781;
    public static int LOGIN_WX = 780;
    private static final String a = LiveLoginActivity.class.getSimpleName();
    private ImageView b;
    private LinearLayout c;
    private LinearLayout d;
    private LinearLayout e;
    private LinearLayout f;
    private Tencent g;
    private IUiListener h;
    private WXAuthHelper i;
    private ThirdParty j;
    private SsoHandler k;

    class a implements WbAuthListener {
        final /* synthetic */ LiveLoginActivity a;

        a(LiveLoginActivity liveLoginActivity) {
            this.a = liveLoginActivity;
        }

        public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
            AccessTokenKeeper.keepAccessToken(this.a, new ThirdOauth2AccessToken(oauth2AccessToken));
            this.a.a(oauth2AccessToken.getToken(), oauth2AccessToken.getExpiresTime() + "", null, ThirdPartyConstants.THIRDPARTY_TYLE_SINA, oauth2AccessToken.getUid());
        }

        public void cancel() {
        }

        public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
            ToastAndDialog.makeNegativeToast(this.a.getApplicationContext(), "认证异常 : " + wbConnectErrorMessage.getErrorMessage(), Integer.valueOf(1)).show();
        }
    }

    private class b implements IUiListener {
        final /* synthetic */ LiveLoginActivity a;

        public b(LiveLoginActivity liveLoginActivity) {
            this.a = liveLoginActivity;
        }

        protected void a(JSONObject jSONObject) {
            try {
                jSONObject.getString("openid");
                String string = jSONObject.getString("access_token");
                String string2 = jSONObject.getString("expires_in");
                AccessTokenKeeper.keepAccessToken(this.a, new ThirdOauth2AccessToken(string, string2));
                this.a.a(string, string2, null, ThirdPartyConstants.THIRDPARTY_TYLE_QQ, null);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void onError(UiError uiError) {
            ToastAndDialog.makeNegativeToast(this.a, uiError.errorMessage, Integer.valueOf(0)).show();
        }

        public void onCancel() {
        }

        public void onComplete(Object obj) {
            a((JSONObject) obj);
        }
    }

    private class c implements OnWXAuthListener {
        final /* synthetic */ LiveLoginActivity a;

        private c(LiveLoginActivity liveLoginActivity) {
            this.a = liveLoginActivity;
        }

        public void onStart() {
        }

        public void onComplete(WXAuthToken wXAuthToken) {
            if (wXAuthToken != null && wXAuthToken.isValid()) {
                this.a.a(wXAuthToken.token, wXAuthToken.expiresIn + "", wXAuthToken.openId, ThirdPartyConstants.THIRDPARTY_TYLE_WX, null);
            }
            if (this.a.i != null) {
                this.a.i.onDestroy();
            }
        }

        public void onError(WXAuthException wXAuthException) {
            if (wXAuthException != null) {
                String message = wXAuthException.getMessage();
                if (message != null) {
                    ToastAndDialog.makeNegativeToast(this.a, message).show();
                }
            }
        }
    }

    public static void launch(Activity activity, int i) {
        Intent intent = new Intent();
        intent.setClass(activity, LiveLoginActivity.class);
        activity.startActivityForResult(intent, i);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.AppBaseTheme.share.new.Dark.live);
        }
        setContentView(R.layout.live_login_activity);
        this.b = (ImageView) findViewById(R.id.live_login_delete);
        this.c = (LinearLayout) findViewById(R.id.live_login_qq);
        this.d = (LinearLayout) findViewById(R.id.live_login_wx);
        this.e = (LinearLayout) findViewById(R.id.live_login_wb);
        this.f = (LinearLayout) findViewById(R.id.live_login_qb);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.live_login_qq:
                LoginForQQ();
                return;
            case R.id.live_login_wx:
                c();
                return;
            case R.id.live_login_wb:
                b();
                return;
            case R.id.live_login_qb:
                a();
                return;
            case R.id.live_login_delete:
                setResult(0);
                finish();
                return;
            default:
                return;
        }
    }

    private void a() {
        if (QsbkApp.currentUser == null) {
            startActivityForResult(new Intent(this, ActionBarLoginActivity.class), 100);
        }
    }

    private void b() {
        if (this.g != null) {
            this.g = null;
        }
        this.j = ThirdParty.getInstance(ThirdPartyConstants.SINA_CONSUMER_KEY, ThirdPartyConstants.SINA_REDIRECT_URL, ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
        this.k = new SsoHandler(this);
        this.k.authorize(new a(this));
    }

    private void c() {
        if (this.g != null) {
            this.g = null;
        }
        if (this.k != null) {
            this.k = null;
        }
        this.i = WXAuthHelper.getInstance(this);
        this.i.startAuth(new c());
    }

    public void LoginForQQ() {
        if (this.k != null) {
            this.k = null;
        }
        this.h = new b(this);
        this.g = ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, this);
        this.g.login(this, "all", this.h);
    }

    private void a(String str, String str2, String str3, String str4, String str5) {
        Map hashMap = new HashMap();
        hashMap.put("sns", str4);
        hashMap.put("token", str);
        hashMap.put("expires_in", str2);
        if (str3 != null) {
            hashMap.put("openid", str3);
        }
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.THIRDPARTY_LOGIN, new g(this, str4, str, str2, str3, str5));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 99) {
            if (i2 == -1) {
                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(MainActivity.ACTION_QB_LOGIN));
                setResult(-1);
                finish();
            }
        } else if (i != 100) {
            if (this.g != null) {
                Tencent tencent = this.g;
                Tencent.onActivityResultData(i, i2, intent, this.h);
            }
            if (this.k != null) {
                this.k.authorizeCallBack(i, i2, intent);
            }
        } else if (i2 == -1) {
            setResult(-1);
            finish();
        }
    }

    protected void a(String str) {
        SharePreferenceUtils.setSharePreferencesValue("loginName", str);
    }

    public void handleToken(JSONObject jSONObject) {
        if (jSONObject != null) {
            QsbkApp.valuesMap = new HashMap();
            QsbkApp.getInstance().updateCurrentUserInfo(jSONObject);
        }
    }

    private void a(String str, String str2, String str3, String str4) {
        if (!str4.equalsIgnoreCase(ThirdPartyConstants.THIRDPARTY_TYLE_WX)) {
            StringBuffer stringBuffer = new StringBuffer("accessToken=");
            stringBuffer.append(str);
            stringBuffer.append(com.alipay.sdk.sys.a.b);
            stringBuffer.append("expires_in=").append((Long.valueOf(str2).longValue() * 1000) + System.currentTimeMillis());
            SharePreferenceUtils.setSharePreferencesValue(str3, stringBuffer.toString());
        }
    }

    public void onLoginSuccess() {
        IMNotifyManager.getSettingFromCloud();
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(MainActivity.ACTION_QB_LOGIN));
        new IMPreConnector().preConnect("onLoginSuccess");
        new h(this, "qbk-LoginAct-2").start();
    }
}
