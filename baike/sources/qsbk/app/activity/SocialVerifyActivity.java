package qsbk.app.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.security.AccessTokenKeeper;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.EncryptHttpTask;
import qsbk.app.thirdparty.ThirdOauth2AccessToken;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.wxapi.WXAuthHelper;
import qsbk.app.wxapi.WXAuthHelper.OnWXAuthListener;
import qsbk.app.wxapi.WXAuthHelper.WXAuthException;
import qsbk.app.wxapi.WXAuthHelper.WXAuthToken;

public class SocialVerifyActivity extends BaseActionBarActivity implements OnClickListener {
    public static final int REQUEST_VERIFY = 1;
    String a;
    View b;
    View c;
    View d;
    View e;
    View f;
    View g;
    Button h;
    View i;
    ProgressDialog j;
    private String k;
    private EncryptHttpTask l;
    private SsoHandler m;
    private Tencent n;
    private IUiListener o;
    private WXAuthHelper p;
    private ThirdParty q;

    class a implements WbAuthListener {
        final /* synthetic */ SocialVerifyActivity a;

        a(SocialVerifyActivity socialVerifyActivity) {
            this.a = socialVerifyActivity;
        }

        public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
            AccessTokenKeeper.keepAccessToken(this.a, new ThirdOauth2AccessToken(oauth2AccessToken));
            HashMap hashMap = new HashMap();
            hashMap.put("token", oauth2AccessToken.getToken());
            this.a.a(hashMap);
        }

        public void cancel() {
        }

        public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
        }
    }

    private class b implements IUiListener {
        final /* synthetic */ SocialVerifyActivity a;
        private String b;

        private b(SocialVerifyActivity socialVerifyActivity) {
            this.a = socialVerifyActivity;
        }

        protected void a(JSONObject jSONObject) {
            try {
                this.b = jSONObject.getString("access_token");
                HashMap hashMap = new HashMap();
                hashMap.put("token", this.b);
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
        final /* synthetic */ SocialVerifyActivity a;

        private c(SocialVerifyActivity socialVerifyActivity) {
            this.a = socialVerifyActivity;
        }

        public void onStart() {
            this.a.a(true);
        }

        public void onComplete(WXAuthToken wXAuthToken) {
            if (this.a != null) {
                this.a.m();
                if (wXAuthToken != null && wXAuthToken.isValid()) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("token", wXAuthToken.token);
                    hashMap.put("openid", wXAuthToken.openId);
                    this.a.a(hashMap);
                }
            }
        }

        public void onError(WXAuthException wXAuthException) {
            if (this.a != null) {
                this.a.m();
                if (wXAuthException != null) {
                    String message = wXAuthException.getMessage();
                    if (message != null) {
                        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, message, Integer.valueOf(0)).show();
                    }
                }
            }
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Activity activity) {
        activity.startActivityForResult(new Intent(activity, SocialVerifyActivity.class), 1);
    }

    protected String f() {
        return getString(R.string.third_part_verify);
    }

    protected int a() {
        return R.layout.activity_social_verify;
    }

    protected void a(Bundle bundle) {
        int i = 8;
        if (QsbkApp.currentUser == null) {
            finish();
            return;
        }
        int i2;
        setActionbarBackable();
        this.b = findViewById(R.id.qq);
        this.c = findViewById(R.id.wx);
        this.d = findViewById(R.id.wb);
        this.h = (Button) findViewById(R.id.done);
        this.e = findViewById(R.id.qq_selected);
        this.f = findViewById(R.id.wx_selcted);
        this.g = findViewById(R.id.wb_selected);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.b.setVisibility(QsbkApp.currentUser.isBindQQ() ? 0 : 8);
        View view = this.c;
        if (QsbkApp.currentUser.isBindWX()) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        view.setVisibility(i2);
        View view2 = this.d;
        if (QsbkApp.currentUser.isBindWeibo()) {
            i = 0;
        }
        view2.setVisibility(i);
        if (QsbkApp.currentUser.isBindQQ()) {
            this.a = ThirdPartyConstants.THIRDPARTY_TYLE_QQ;
            this.e.setVisibility(0);
            this.i = this.e;
        } else if (QsbkApp.currentUser.isBindWX()) {
            this.a = ThirdPartyConstants.THIRDPARTY_TYLE_WX;
            this.f.setVisibility(0);
            this.i = this.f;
        } else if (QsbkApp.currentUser.isBindWeibo()) {
            this.a = ThirdPartyConstants.THIRDPARTY_TYLE_SINA;
            this.g.setVisibility(0);
            this.i = this.g;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.m != null) {
            this.m.authorizeCallBack(i, i2, intent);
        }
        if (this.n != null) {
            Tencent tencent = this.n;
            Tencent.onActivityResultData(i, i2, intent, this.o);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.l != null) {
            this.l.cancel(true);
        }
    }

    public void onClick(View view) {
        if (!(view.getId() == R.id.done || this.i == null)) {
            this.i.setVisibility(8);
        }
        switch (view.getId()) {
            case R.id.qq:
                this.a = ThirdPartyConstants.THIRDPARTY_TYLE_QQ;
                this.e.setVisibility(0);
                this.i = this.e;
                return;
            case R.id.done:
                if (ThirdPartyConstants.THIRDPARTY_TYLE_QQ.equals(this.a)) {
                    k();
                    return;
                } else if (ThirdPartyConstants.THIRDPARTY_TYLE_SINA.equals(this.a)) {
                    i();
                    return;
                } else {
                    j();
                    return;
                }
            case R.id.wx:
                this.a = ThirdPartyConstants.THIRDPARTY_TYLE_WX;
                this.f.setVisibility(0);
                this.i = this.f;
                return;
            case R.id.wb:
                this.a = ThirdPartyConstants.THIRDPARTY_TYLE_SINA;
                this.g.setVisibility(0);
                this.i = this.g;
                return;
            default:
                return;
        }
    }

    private void i() {
        this.a = ThirdPartyConstants.THIRDPARTY_TYLE_SINA;
        this.q = ThirdParty.getInstance(ThirdPartyConstants.SINA_CONSUMER_KEY, ThirdPartyConstants.SINA_REDIRECT_URL, ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
        this.m = new SsoHandler(this);
        this.m.authorize(new a(this));
    }

    private void j() {
        this.a = ThirdPartyConstants.THIRDPARTY_TYLE_WX;
        this.p = WXAuthHelper.getInstance(this);
        this.p.startAuth(new c());
    }

    private void k() {
        this.a = ThirdPartyConstants.THIRDPARTY_TYLE_QQ;
        this.o = new b();
        this.n = ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, getApplicationContext());
        this.n.login(this, "get_user_info,get_simple_userinfo", this.o);
    }

    private void a(HashMap<String, Object> hashMap) {
        if (!(hashMap == null || hashMap.get("token") == null)) {
            this.k = hashMap.get("token").toString();
        }
        if (TextUtils.isEmpty(this.a) || TextUtils.isEmpty(this.k)) {
            ToastAndDialog.makeNegativeToast(this, "验证失败，请选择本人使用的社交账号");
        }
        l();
    }

    private void l() {
        String str = Constants.VERIFY_THIRD;
        this.l = new EncryptHttpTask(str, str, new add(this));
        Map hashMap = new HashMap();
        hashMap.put("sns_type", this.a);
        hashMap.put("token", this.k);
        this.l.setMapParams(hashMap);
        this.l.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        a(false);
    }

    void g() {
        Intent intent = new Intent();
        intent.putExtra("type", this.a);
        intent.putExtra("token", this.k);
        setResult(-1, intent);
        finish();
    }

    private void a(boolean z) {
        if (this.j == null) {
            this.j = ProgressDialog.show(this, null, "请稍候...", true, z);
        }
        this.j.setCancelable(z);
        if (!isFinishing()) {
            this.j.show();
        }
    }

    private void m() {
        if (this.j != null) {
            this.j.dismiss();
        }
    }
}
