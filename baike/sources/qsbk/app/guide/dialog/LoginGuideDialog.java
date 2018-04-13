package qsbk.app.guide.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baidu.mobstat.StatService;
import com.qiushibaike.statsdk.StatSDK;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.MainActivity;
import qsbk.app.activity.security.AccessTokenKeeper;
import qsbk.app.im.IMNotifyManager;
import qsbk.app.im.IMPreConnector;
import qsbk.app.thirdparty.ThirdOauth2AccessToken;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.AppContext;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.wxapi.WXAuthHelper;
import qsbk.app.wxapi.WXAuthHelper.OnWXAuthListener;
import qsbk.app.wxapi.WXAuthHelper.WXAuthException;
import qsbk.app.wxapi.WXAuthHelper.WXAuthToken;

public class LoginGuideDialog extends AlertDialog {
    protected static JSONObject a = null;
    protected static Class<?> b = null;
    private static final String c = LoginGuideDialog.class.getSimpleName();
    private static Context d;
    private static ProgressDialog e;
    private static String f;
    private static String g;
    private static String h;
    private static String i;
    private static long j;
    private static ThirdOauth2AccessToken k;
    private static WXAuthHelper l;
    private static Tencent m;
    private static IUiListener n;
    private TextView o;
    private LinearLayout p;
    private LinearLayout q;
    private LinearLayout r;
    private LinearLayout s;
    private int t;
    private Handler u = new a(this);

    class a implements IUiListener {
        final /* synthetic */ LoginGuideDialog a;

        a(LoginGuideDialog loginGuideDialog) {
            this.a = loginGuideDialog;
        }

        protected void a(JSONObject jSONObject) {
            try {
                DebugUtil.debug(LoginGuideDialog.c, "QQ:" + jSONObject.toString());
                LoginGuideDialog.i = jSONObject.getString("openid");
                LoginGuideDialog.g = jSONObject.getString("access_token");
                LoginGuideDialog.h = jSONObject.getString("expires_in");
                LoginGuideDialog.k = new ThirdOauth2AccessToken(LoginGuideDialog.g, LoginGuideDialog.h);
                AccessTokenKeeper.keepAccessToken(LoginGuideDialog.d, LoginGuideDialog.k);
                this.a.c(LoginGuideDialog.g, LoginGuideDialog.h, null);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void onError(UiError uiError) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, uiError.errorMessage, Integer.valueOf(0)).show();
        }

        public void onCancel() {
        }

        public void onComplete(Object obj) {
            a((JSONObject) obj);
        }
    }

    class b implements OnWXAuthListener {
        final /* synthetic */ LoginGuideDialog a;

        b(LoginGuideDialog loginGuideDialog) {
            this.a = loginGuideDialog;
        }

        public void onStart() {
            LoginGuideDialog.p();
        }

        public void onComplete(WXAuthToken wXAuthToken) {
            LoginGuideDialog.q();
            if (wXAuthToken != null && wXAuthToken.isValid()) {
                LoginGuideDialog.g = wXAuthToken.token;
                LoginGuideDialog.h = wXAuthToken.expiresIn + "";
                LoginGuideDialog.i = wXAuthToken.openId;
                this.a.c(wXAuthToken.token, wXAuthToken.expiresIn + "", wXAuthToken.openId);
            }
            if (LoginGuideDialog.l != null) {
                LoginGuideDialog.l.onDestroy();
            }
        }

        public void onError(WXAuthException wXAuthException) {
            LoginGuideDialog.q();
            if (wXAuthException != null) {
                String message = wXAuthException.getMessage();
                if (message != null) {
                    ToastAndDialog.makeNegativeToast(QsbkApp.mContext, message, Integer.valueOf(0)).show();
                }
            }
        }
    }

    public LoginGuideDialog(Context context) {
        super(context);
        d = context;
    }

    public LoginGuideDialog(Context context, int i) {
        super(context, i);
        d = context;
    }

    public LoginGuideDialog(Context context, int i, int i2) {
        super(context, i);
        d = context;
        this.t = i2;
    }

    public static boolean handleQQloginRequest(int i, int i2, Intent intent) {
        if (m == null || n == null) {
            return false;
        }
        Tencent tencent = m;
        Tencent.onActivityResultData(i, i2, intent, n);
        return true;
    }

    private static void b(JSONObject jSONObject) {
        if (jSONObject != null) {
            QsbkApp.valuesMap = new HashMap();
            QsbkApp.getInstance().updateCurrentUserInfo(jSONObject);
        }
    }

    private static void b(String str, String str2, String str3) {
        if (!f.equalsIgnoreCase(ThirdPartyConstants.THIRDPARTY_TYLE_WX)) {
            StringBuffer stringBuffer = new StringBuffer("accessToken=");
            stringBuffer.append(str);
            stringBuffer.append(com.alipay.sdk.sys.a.b);
            stringBuffer.append("expires_in=").append((Long.valueOf(str2).longValue() * 1000) + System.currentTimeMillis());
            SharePreferenceUtils.setSharePreferencesValue(str3, stringBuffer.toString());
        }
    }

    private static void p() {
        if (e == null) {
            e = ProgressDialog.show(d, null, "请稍候...", true, false);
        }
        e.show();
    }

    private static void q() {
        if (e != null) {
            e.dismiss();
        }
    }

    protected static void a() {
        if (d instanceof Activity) {
            ((Activity) d).setProgressBarIndeterminateVisibility(true);
        }
    }

    protected static void b() {
        if (d instanceof Activity) {
            ((Activity) d).setProgressBarIndeterminateVisibility(false);
        }
    }

    private static void b(String str, String str2) {
        StatService.onEvent(AppContext.getContext(), str, str2);
        StatSDK.onEvent(AppContext.getContext(), str, str2);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.login_guide_dialog);
        r();
        s();
    }

    private void r() {
        this.o = (TextView) findViewById(R.id.tv_login_guide_content);
        this.o.setText(d.getResources().getString(this.t));
        this.p = (LinearLayout) findViewById(R.id.ll_qq_login);
        this.q = (LinearLayout) findViewById(R.id.ll_weixin_login);
        this.r = (LinearLayout) findViewById(R.id.ll_weibo_login);
        this.s = (LinearLayout) findViewById(R.id.ll_qiubai_login);
    }

    private void s() {
        this.p.setOnClickListener(new b(this));
        this.q.setOnClickListener(new c(this));
        this.r.setOnClickListener(new d(this));
        this.s.setOnClickListener(new e(this));
    }

    public void onLoginSuccess() {
        IMNotifyManager.getSettingFromCloud();
        LocalBroadcastManager.getInstance(d).sendBroadcast(new Intent(MainActivity.ACTION_QB_LOGIN));
        new IMPreConnector().preConnect("onLoginSuccess");
        new f(this, "qbk-LoginAct-2").start();
    }

    private void c(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("sns", f);
        hashMap.put("token", g);
        hashMap.put("expires_in", h);
        if (str3 != null) {
            hashMap.put("openid", i);
        }
        a();
        new g(this, "qbk-LoginAct-1", hashMap).start();
    }
}
