package qsbk.app.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import com.alipay.sdk.sys.a;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import java.util.HashMap;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.base.BaseEmotionActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.IMNotifyManager;
import qsbk.app.im.IMPreConnector;
import qsbk.app.thirdparty.ThirdOauth2AccessToken;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.Util;
import qsbk.app.widget.SizeNotifierRelativeLayout;
import qsbk.app.widget.SizeNotifierRelativeLayout$SizeNotifierRelativeLayoutDelegate;
import qsbk.app.wxapi.WXAuthHelper;

public class ActionBarLoginActivity extends ActionBarLoginRegisterBaseActivity implements SizeNotifierRelativeLayout$SizeNotifierRelativeLayoutDelegate {
    public static final String SCOPE = "all";
    public static final String SIGNED_IN = "sign_in";
    public static final String TOAST_WHEN_CREATED = "toast_when_created";
    public static ThirdOauth2AccessToken accessToken;
    private static final String f = ActionBarLoginActivity.class.getSimpleName();
    private Runnable A = new k(this);
    private Handler B = new u(this);
    private boolean C;
    private ThirdParty g;
    private String h;
    private String i;
    private String j;
    private WXAuthHelper k;
    private String l;
    private String m;
    private SsoHandler n;
    private Tencent o;
    private IUiListener p;
    private SizeNotifierRelativeLayout q;
    private ScrollView r = null;
    private String s;
    private boolean t = false;
    private RelativeLayout u;
    private RelativeLayout v;
    private RelativeLayout w;
    private View x;
    private boolean y = false;
    private ProgressDialog z;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return c();
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, ActionBarLoginActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    private static final void j() {
        new v().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]);
    }

    protected int a() {
        return R.layout.actionbar_activity_login;
    }

    private void k() {
        if (this.r != null) {
            this.r.postDelayed(new w(this), 200);
        }
    }

    private void l() {
        findViewById(R.id.question).setOnClickListener(new x(this));
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Login_Night);
        } else {
            setTheme(R.style.Login);
        }
    }

    private void m() {
        this.u = (RelativeLayout) findViewById(R.id.qqLayout);
        this.u.setOnClickListener(new aa(this));
    }

    private void n() {
        this.w = (RelativeLayout) findViewById(R.id.wxLayout);
        this.k = WXAuthHelper.getInstance(this);
        this.w.setOnClickListener(new ab(this));
    }

    protected void onResume() {
        super.onResume();
        if (this.y && this.z.isShowing()) {
            this.w.postDelayed(this.A, 1000);
        }
    }

    protected void onPause() {
        super.onPause();
        this.w.removeCallbacks(this.A);
    }

    protected void onDestroy() {
        if (this.k != null) {
            this.k.onDestroy();
        }
        super.onDestroy();
    }

    public void onSizeChanged(int i) {
        if (i > Util.dp(50.0f)) {
            SharePreferenceUtils.setSharePreferencesValue(BaseEmotionActivity.KEYBOARD_HEIGHT, i);
        }
    }

    private void q() {
        this.v = (RelativeLayout) findViewById(R.id.sinaLayout);
        this.v.setOnClickListener(new ac(this));
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        DebugUtil.debug(f, "onActivityResult requestCode:" + i);
        if (this.n != null) {
            this.n.authorizeCallBack(i, i2, intent);
        }
        if (this.o != null) {
            Tencent tencent = this.o;
            Tencent.onActivityResultData(i, i2, intent, this.p);
        }
    }

    public void finish() {
        super.finish();
    }

    private void a(String str, String str2, String str3) {
        if (!this.h.equalsIgnoreCase(ThirdPartyConstants.THIRDPARTY_TYLE_WX)) {
            StringBuffer stringBuffer = new StringBuffer("accessToken=");
            stringBuffer.append(str);
            stringBuffer.append(a.b);
            stringBuffer.append("expires_in=").append((Long.valueOf(str2).longValue() * 1000) + System.currentTimeMillis());
            SharePreferenceUtils.setSharePreferencesValue(str3, stringBuffer.toString());
        }
    }

    public void onLoginSuccess() {
        IMNotifyManager.getSettingFromCloud();
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(MainActivity.ACTION_QB_LOGIN));
        new IMPreConnector().preConnect("onLoginSuccess");
        j();
    }

    private void b(String str, String str2, String str3) {
        if (!this.t) {
            this.t = true;
            HashMap hashMap = new HashMap();
            hashMap.put("sns", this.h);
            hashMap.put("token", str);
            hashMap.put("expires_in", str2);
            if (str3 != null) {
                hashMap.put("openid", str3);
            }
            a(false);
            new ad(this, "qbk-LoginAct-1", hashMap).start();
        }
    }

    protected void a(Bundle bundle) {
        super.a(bundle);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.s = extras.getString("from");
            Object string = extras.getString(TOAST_WHEN_CREATED);
            if (!TextUtils.isEmpty(string)) {
                ToastAndDialog.makeNeutralToast(this, string, Integer.valueOf(1)).show();
            }
        }
        l();
        m();
        q();
        n();
        t();
        this.q = (SizeNotifierRelativeLayout) findViewById(R.id.size_nofify_layout);
        this.q.setSizeNotifierRelativeLayoutDelegate(this);
        this.r = (ScrollView) findViewById(R.id.id_scroll_view);
        this.x = findViewById(R.id.agreement);
        this.x.setOnClickListener(new l(this));
        this.d.setOnClickListener(new m(this));
        this.d.setOnFocusChangeListener(new n(this));
        this.e.setOnFocusChangeListener(new o(this));
        this.e.setOnClickListener(new p(this));
    }

    private void a(boolean z) {
        this.C = false;
        if (this.z == null) {
            this.z = ProgressDialog.show(this, null, "请稍候...", true, z);
            this.z.setOnCancelListener(new q(this));
        }
        if (!isFinishing()) {
            this.z.show();
        }
    }

    private void r() {
        if (this.z != null && !isFinishing()) {
            this.z.dismiss();
        }
    }

    private void s() {
        if (g()) {
            a(false);
            submit();
            return;
        }
        r();
    }

    private void t() {
        this.e.setOnEditorActionListener(new r(this));
        findViewById(R.id.id_btn_login).setOnClickListener(new s(this));
    }

    public void submit() {
        LogUtil.d("submit");
        d_();
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.LOGIN, new t(this));
        simpleHttpTask.setMapParams(i());
        simpleHttpTask.execute();
    }

    protected String c() {
        return "登录 / 注册";
    }

    protected boolean d() {
        return true;
    }
}
