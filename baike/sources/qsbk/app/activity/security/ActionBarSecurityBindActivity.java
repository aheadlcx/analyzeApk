package qsbk.app.activity.security;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.thirdparty.ThirdOauth2AccessToken;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyException;
import qsbk.app.thirdparty.UsersAPI;
import qsbk.app.thirdparty.net.RequestListener;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;

public class ActionBarSecurityBindActivity extends BaseActionBarActivity {
    public static final String KEY_RESPONSE = "response";
    public static final String KEY_SOURCE = "source";
    HashMap<String, Object> a = new HashMap();
    Handler b = new a(this);
    Handler c = new c(this);
    Handler d = new d(this);
    private TextView e;
    private TextView f;
    private TextView g;
    private int h = 20;
    private RelativeLayout i;
    private RelativeLayout j;
    private RelativeLayout k;
    private Button l;
    private ThirdParty m;
    private String n;
    private SsoHandler o;
    private Tencent p;
    private IUiListener q;
    private String r;
    private JSONObject s;
    private String t;
    private boolean u = false;
    private String v = "";
    private boolean w = false;
    private boolean x = false;

    class a implements WbAuthListener {
        final /* synthetic */ ActionBarSecurityBindActivity a;

        a(ActionBarSecurityBindActivity actionBarSecurityBindActivity) {
            this.a = actionBarSecurityBindActivity;
        }

        public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
            AccessTokenKeeper.keepAccessToken(this.a, new ThirdOauth2AccessToken(oauth2AccessToken));
            if (TextUtils.isEmpty(this.a.r)) {
                this.a.a.put("wbtoken", oauth2AccessToken.getToken());
                if (!TextUtils.isEmpty(this.a.v)) {
                    this.a.updateUserInfo(this.a.d.obtainMessage(), this.a.a);
                    return;
                }
                return;
            }
            this.a.setTitle("绑定中");
            UsersAPI usersAPI = new UsersAPI(new ThirdOauth2AccessToken(oauth2AccessToken));
            this.a.t = oauth2AccessToken.getUid();
            usersAPI.getSinaUser(Long.valueOf(this.a.t).longValue(), new c(this.a));
            this.a.a.put("wbtoken", oauth2AccessToken.getToken());
        }

        public void cancel() {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "取消认证", Integer.valueOf(0)).show();
        }

        public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "认证异常 : " + wbConnectErrorMessage.getErrorMessage(), Integer.valueOf(0)).show();
        }
    }

    private class b implements IUiListener {
        final /* synthetic */ ActionBarSecurityBindActivity a;
        private String b;

        private b(ActionBarSecurityBindActivity actionBarSecurityBindActivity) {
            this.a = actionBarSecurityBindActivity;
        }

        public void onComplete(Object obj) {
            this.a.q();
            a((JSONObject) obj);
        }

        protected void a(JSONObject jSONObject) {
            try {
                this.b = jSONObject.getString("access_token");
                this.a.a.put("qqtoken", this.b);
                if (!TextUtils.isEmpty(this.a.v)) {
                    this.a.updateUserInfo(this.a.d.obtainMessage(), this.a.a);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void onError(UiError uiError) {
        }

        public void onCancel() {
        }
    }

    class c implements RequestListener {
        final /* synthetic */ ActionBarSecurityBindActivity a;

        c(ActionBarSecurityBindActivity actionBarSecurityBindActivity) {
            this.a = actionBarSecurityBindActivity;
        }

        public void onComplete(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                Message obtainMessage = this.a.c.obtainMessage();
                obtainMessage.obj = jSONObject.getString("screen_name");
                obtainMessage.sendToTarget();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void onIOException(IOException iOException) {
        }

        public void onError(ThirdPartyException thirdPartyException) {
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public boolean isOldUser() {
        return !TextUtils.isEmpty(this.v);
    }

    protected int a() {
        return R.layout.actionbar_activity_security_bind;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            this.v = getIntent().getStringExtra("source");
            if (TextUtils.isEmpty(this.v)) {
                this.r = getIntent().getStringExtra(KEY_RESPONSE);
                if (!TextUtils.isEmpty(this.r)) {
                    JSONObject jSONObject = new JSONObject(this.r);
                    this.a.put(QsbkDatabase.LOGIN, jSONObject.getString(QsbkDatabase.LOGIN));
                    this.a.put("pass", jSONObject.getString("pass"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        l();
        m();
        setActionbarBackable();
    }

    protected String f() {
        return "绑定";
    }

    protected void a(Bundle bundle) {
    }

    private void a(String str) {
        ((TextView) findViewById(R.id.top_hint)).setText("您的帐号不安全，请绑定任一密保");
        findViewById(R.id.top_hint).setVisibility(0);
    }

    private void i() {
        findViewById(R.id.top_hint).setVisibility(8);
    }

    private void b(String str) {
        this.l.setVisibility(0);
        this.l.setText(str);
    }

    private void j() {
    }

    private boolean k() {
        return !TextUtils.isEmpty(this.r);
    }

    private void l() {
        if (TextUtils.isEmpty(this.r)) {
            setTitle("设定密保");
        } else {
            setTitle("密保");
        }
        this.k = (RelativeLayout) findViewById(R.id.mailLayout);
        this.i = (RelativeLayout) findViewById(R.id.sinaLayout);
        this.j = (RelativeLayout) findViewById(R.id.qqLayout);
        this.l = (Button) findViewById(R.id.overRegister);
        if (isOldUser()) {
            b("完成绑定");
            a("您的帐号不安全，请绑定任一密保");
        } else if (k()) {
            b("完成注册");
            a(getResources().getString(R.string.securitybind_top_hint));
        } else {
            j();
            i();
        }
        this.e = (TextView) findViewById(R.id.sina);
        this.f = (TextView) findViewById(R.id.qq);
        this.g = (TextView) findViewById(R.id.mail);
        if (QsbkApp.currentUser != null) {
            if (!TextUtils.isEmpty(QsbkApp.currentUser.email.trim())) {
                this.g.setText(QsbkApp.currentUser.email);
                findViewById(R.id.email_mark).setVisibility(0);
            }
            if (!TextUtils.isEmpty(QsbkApp.currentUser.wb.trim())) {
                this.e.setText(QsbkApp.currentUser.wb);
                findViewById(R.id.sina_mark).setVisibility(0);
            }
            if (!TextUtils.isEmpty(QsbkApp.currentUser.qq)) {
                this.f.setText(QsbkApp.currentUser.qq);
                findViewById(R.id.qq_mark).setVisibility(0);
            }
        }
    }

    private void m() {
        this.l.setOnClickListener(new e(this));
        this.i.setOnClickListener(new f(this));
        this.j.setOnClickListener(new h(this));
        this.k.setOnClickListener(new j(this));
    }

    protected void g() {
        SharePreferenceUtils.setSharePreferencesValue("loginName", (String) this.a.get(QsbkDatabase.LOGIN));
    }

    private void n() {
        new l(this, "bqk-Security1").start();
    }

    public void updateUserInfo(Message message, Map<String, Object> map) {
        setTitle("绑定中...");
        new m(this, "bqk-Security2", map, message).start();
    }

    public void handleToken(JSONObject jSONObject) {
        if (jSONObject != null) {
            QsbkApp.valuesMap = new HashMap();
            QsbkApp.getInstance().updateCurrentUserInfo(jSONObject);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.h == i && intent != null) {
            this.g.setText(intent.getStringExtra("email"));
            this.a.put("email", intent.getStringExtra("email"));
            this.u = true;
        }
        if (this.o != null && this.x) {
            this.o.authorizeCallBack(i, i2, intent);
            this.x = false;
        }
        if (this.p != null && this.w) {
            Tencent tencent = this.p;
            Tencent.onActivityResultData(i, i2, intent, this.q);
            this.w = false;
        }
    }

    private void q() {
        new UserInfo(this, this.p.getQQToken()).getUserInfo(new b(this));
    }

    private void c(String str) {
        Message obtainMessage = this.c.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = str;
        obtainMessage.sendToTarget();
    }
}
