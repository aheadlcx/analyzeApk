package qsbk.app.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.security.AccessTokenKeeper;
import qsbk.app.activity.security.UnBindActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.UserInfo;
import qsbk.app.thirdparty.ThirdOauth2AccessToken;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.widget.SecurityBindView;
import qsbk.app.wxapi.WXAuthHelper;
import qsbk.app.wxapi.WXAuthHelper.OnWXAuthListener;
import qsbk.app.wxapi.WXAuthHelper.WXAuthException;
import qsbk.app.wxapi.WXAuthHelper.WXAuthToken;

public class SocialBindActivity extends BaseActionBarActivity {
    private SecurityBindView a;
    private SecurityBindView b;
    private SecurityBindView c;
    private ProgressDialog d;
    private SsoHandler e;
    private Tencent f;
    private IUiListener g;
    private WXAuthHelper h;
    private ThirdParty i;
    private String j;

    class a implements WbAuthListener {
        final /* synthetic */ SocialBindActivity a;

        a(SocialBindActivity socialBindActivity) {
            this.a = socialBindActivity;
        }

        public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
            ThirdOauth2AccessToken thirdOauth2AccessToken = new ThirdOauth2AccessToken(oauth2AccessToken);
            AccessTokenKeeper.keepAccessToken(this.a, thirdOauth2AccessToken);
            Map hashMap = new HashMap();
            hashMap.put("wbtoken", thirdOauth2AccessToken.getToken());
            this.a.a(hashMap);
        }

        public void cancel() {
        }

        public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
        }
    }

    private class b implements IUiListener {
        final /* synthetic */ SocialBindActivity a;
        private String b;

        private b(SocialBindActivity socialBindActivity) {
            this.a = socialBindActivity;
        }

        protected void a(JSONObject jSONObject) {
            try {
                this.b = jSONObject.getString("access_token");
                Map hashMap = new HashMap();
                hashMap.put("qqtoken", this.b);
                this.a.a(hashMap);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void onError(UiError uiError) {
        }

        public void onCancel() {
        }

        public void onComplete(Object obj) {
            a((JSONObject) obj);
        }
    }

    private class c implements OnWXAuthListener {
        final /* synthetic */ SocialBindActivity a;

        private c(SocialBindActivity socialBindActivity) {
            this.a = socialBindActivity;
        }

        public void onStart() {
            this.a.r();
        }

        public void onComplete(WXAuthToken wXAuthToken) {
            this.a.s();
            if (wXAuthToken != null && wXAuthToken.isValid()) {
                Map hashMap = new HashMap();
                hashMap.put("wxtoken", wXAuthToken.token);
                hashMap.put("openid", wXAuthToken.openId);
                this.a.a(hashMap);
            }
        }

        public void onError(WXAuthException wXAuthException) {
            this.a.s();
            if (wXAuthException != null) {
                String message = wXAuthException.getMessage();
                if (message != null) {
                    ToastAndDialog.makeNegativeToast(QsbkApp.mContext, message, Integer.valueOf(0)).show();
                }
            }
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, SocialBindActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    public static void fillUserDataFromServer(JSONObject jSONObject) {
        Object obj = 1;
        if (QsbkApp.currentUser != null) {
            try {
                JSONObject optJSONObject = jSONObject.optJSONObject("userdata");
                if (optJSONObject != null) {
                    Object obj2 = null;
                    if (optJSONObject.has(ThirdPartyConstants.THIRDPARTY_TYLE_SINA)) {
                        QsbkApp.currentUser.wb = optJSONObject.getString(ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
                        obj2 = 1;
                    }
                    if (optJSONObject.has(ThirdPartyConstants.THIRDPARTY_TYLE_QQ)) {
                        QsbkApp.currentUser.qq = optJSONObject.getString(ThirdPartyConstants.THIRDPARTY_TYLE_QQ);
                        obj2 = 1;
                    }
                    if (optJSONObject.has(ThirdPartyConstants.THIRDPARTY_TYLE_WX)) {
                        QsbkApp.currentUser.wx = optJSONObject.getString(ThirdPartyConstants.THIRDPARTY_TYLE_WX);
                        obj2 = 1;
                    }
                    optJSONObject = jSONObject.optJSONObject("user");
                    if (optJSONObject != null) {
                        if (optJSONObject.has("email")) {
                            QsbkApp.currentUser.email = optJSONObject.getString("email");
                            obj2 = 1;
                        }
                        if (optJSONObject.has("state")) {
                            QsbkApp.currentUser.state = optJSONObject.getString("state");
                            if (obj != null) {
                                SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
                            }
                        }
                    }
                    obj = obj2;
                    if (obj != null) {
                        SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
                    }
                }
            } catch (JSONException e) {
            }
        }
    }

    protected String f() {
        return getResources().getString(R.string.social_bind);
    }

    protected int a() {
        return R.layout.activity_social_bind;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        if (QsbkApp.currentUser != null) {
            this.a = (SecurityBindView) findViewById(R.id.weibo);
            this.b = (SecurityBindView) findViewById(R.id.qq);
            this.c = (SecurityBindView) findViewById(R.id.wx);
            i();
            g();
            return;
        }
        finish();
    }

    private void g() {
        String str = Constants.MY_INFO;
        r();
        new SimpleHttpTask(str, new acv(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void i() {
        if (a(QsbkApp.currentUser.wx)) {
            this.c.setMainText("绑定微信");
            this.c.setStatus(1);
        } else {
            this.c.setMainText(QsbkApp.currentUser.wx);
            this.c.setStatus(3);
        }
        this.c.setOnClickListener(new acw(this));
        if (a(QsbkApp.currentUser.wb)) {
            this.a.setMainText("绑定新浪微博");
            this.a.setStatus(1);
        } else {
            this.a.setMainText(QsbkApp.currentUser.wb);
            this.a.setStatus(3);
        }
        this.a.setOnClickListener(new acy(this));
        if (a(QsbkApp.currentUser.qq)) {
            this.b.setMainText("绑定QQ");
            this.b.setStatus(1);
        } else {
            this.b.setMainText(QsbkApp.currentUser.qq);
            this.b.setStatus(3);
        }
        this.b.setOnClickListener(new ada(this));
    }

    private void j() {
        this.j = ThirdPartyConstants.THIRDPARTY_TYLE_SINA;
        this.i = ThirdParty.getInstance(ThirdPartyConstants.SINA_CONSUMER_KEY, ThirdPartyConstants.SINA_REDIRECT_URL, ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
        this.e = new SsoHandler(this);
        this.e.authorize(new a(this));
    }

    private void k() {
        this.j = ThirdPartyConstants.THIRDPARTY_TYLE_WX;
        this.h = WXAuthHelper.getInstance(this);
        this.h.startAuth(new c());
    }

    private void l() {
        this.j = ThirdPartyConstants.THIRDPARTY_TYLE_QQ;
        this.g = new b();
        this.f = ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, getApplicationContext());
        this.f.login(this, "get_user_info,get_simple_userinfo", this.g);
    }

    private void m() {
        a("解绑后无法使用新浪微博登录，确定解绑？", 101, 2);
    }

    private void n() {
        a("解绑后无法使用微信登录，确定解绑？", 103, 3);
    }

    private void q() {
        a("解绑后无法使用QQ登录，确定解绑？", 102, 1);
    }

    private void a(String str, int i, int i2) {
        startActivityForResult(UnBindActivity.makeIntent(this, i2, str), i);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        a(i, i2, intent);
    }

    private void a(int i, int i2, Intent intent) {
        if (i2 == -1) {
            switch (i) {
                case 101:
                    QsbkApp.currentUser.wb = "";
                    UserInfo.updateServerJsonNearby(QsbkApp.currentUser, QsbkApp.currentUser.encodeToJsonObject());
                    SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
                    i();
                    break;
                case 102:
                    QsbkApp.currentUser.qq = "";
                    UserInfo.updateServerJsonNearby(QsbkApp.currentUser, QsbkApp.currentUser.encodeToJsonObject());
                    SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
                    i();
                    break;
                case 103:
                    QsbkApp.currentUser.wx = "";
                    UserInfo.updateServerJsonNearby(QsbkApp.currentUser, QsbkApp.currentUser.encodeToJsonObject());
                    SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
                    i();
                    break;
            }
        }
        if (this.e != null) {
            this.e.authorizeCallBack(i, i2, intent);
        }
        if (this.f != null) {
            Tencent tencent = this.f;
            Tencent.onActivityResultData(i, i2, intent, this.g);
        }
    }

    private void a(Map<String, Object> map) {
        r();
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.UPDATE_USERINFO, new adc(this));
        simpleHttpTask.setMapParams(map);
        simpleHttpTask.executeOnExecutor(SimpleHttpTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void r() {
        if (this.d == null) {
            this.d = ProgressDialog.show(this, null, "请稍候...", true, false);
        }
        this.d.show();
    }

    private void s() {
        if (this.d != null) {
            this.d.dismiss();
        }
    }

    private boolean a(String str) {
        return TextUtils.isEmpty(str) || str.trim().length() == 0;
    }
}
