package qsbk.app.model;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import qsbk.app.utils.UIHelper;
import qsbk.app.wxapi.WXAuthHelper;
import qsbk.app.wxapi.WXAuthHelper.OnWXAuthListener;
import qsbk.app.wxapi.WXAuthHelper.WXAuthException;
import qsbk.app.wxapi.WXAuthHelper.WXAuthToken;

public class WelcomeCard {
    protected static JSONObject a = null;
    protected static Class<?> b = null;
    private static final String c = WelcomeCard.class.getSimpleName();
    private static int d = 0;
    private static Context e;
    private static String f;
    private static String g;
    private static String h;
    private static String i;
    public static volatile WelcomeCard instance;
    private static Tencent j;
    private static IUiListener k;
    private static WXAuthHelper l;
    private static ProgressDialog m;
    public static ThirdOauth2AccessToken mAccessToken;
    private static long n;
    private static Handler o = new x();

    public static class ViewHolder {
        public View divider;
        public ImageView ivWelcome;
        public LinearLayout qiubaiLogin;
        public ImageView qiubaiOfficalAvatar;
        public LinearLayout qqLogin;
        public LinearLayout weiboLogin;
        public LinearLayout weixinLogin;

        public ViewHolder(View view) {
            this.qiubaiOfficalAvatar = (ImageView) view.findViewById(R.id.iv_qiubai_avatar);
            this.ivWelcome = (ImageView) view.findViewById(R.id.iv_welcome);
            this.divider = view.findViewById(R.id.divider);
            this.qqLogin = (LinearLayout) view.findViewById(R.id.ll_qq_login);
            this.weixinLogin = (LinearLayout) view.findViewById(R.id.ll_weixin_login);
            this.weiboLogin = (LinearLayout) view.findViewById(R.id.ll_weibo_login);
            this.qiubaiLogin = (LinearLayout) view.findViewById(R.id.ll_qiubai_login);
        }

        public void initListener() {
            this.qqLogin.setOnClickListener(new aa(this));
            this.weixinLogin.setOnClickListener(new ab(this));
            this.weiboLogin.setOnClickListener(new ac(this));
            this.qiubaiLogin.setOnClickListener(new ad(this));
        }
    }

    static class a implements IUiListener {
        a() {
        }

        protected void a(JSONObject jSONObject) {
            try {
                DebugUtil.debug(WelcomeCard.c, "QQ:" + jSONObject.toString());
                WelcomeCard.i = jSONObject.getString("openid");
                WelcomeCard.g = jSONObject.getString("access_token");
                WelcomeCard.h = jSONObject.getString("expires_in");
                WelcomeCard.mAccessToken = new ThirdOauth2AccessToken(WelcomeCard.g, WelcomeCard.h);
                AccessTokenKeeper.keepAccessToken(WelcomeCard.e, WelcomeCard.mAccessToken);
                WelcomeCard.d(WelcomeCard.g, WelcomeCard.h, null);
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

    static class b implements OnWXAuthListener {
        b() {
        }

        public void onStart() {
            WelcomeCard.p();
        }

        public void onComplete(WXAuthToken wXAuthToken) {
            WelcomeCard.q();
            if (wXAuthToken != null && wXAuthToken.isValid()) {
                WelcomeCard.g = wXAuthToken.token;
                WelcomeCard.h = wXAuthToken.expiresIn + "";
                WelcomeCard.i = wXAuthToken.openId;
                WelcomeCard.d(wXAuthToken.token, wXAuthToken.expiresIn + "", wXAuthToken.openId);
                if (WelcomeCard.l != null) {
                    WelcomeCard.l.onDestroy();
                }
            }
        }

        public void onError(WXAuthException wXAuthException) {
            WelcomeCard.q();
            if (wXAuthException != null) {
                String message = wXAuthException.getMessage();
                if (message != null) {
                    ToastAndDialog.makeNegativeToast(QsbkApp.mContext, message, Integer.valueOf(0)).show();
                }
            }
        }
    }

    private WelcomeCard(Context context) {
        e = context;
    }

    public static WelcomeCard getInstance(Context context) {
        if (instance == null) {
            synchronized (WelcomeCard.class) {
                if (instance == null) {
                    instance = new WelcomeCard(context);
                }
            }
        }
        return instance;
    }

    public static void handleToken(JSONObject jSONObject) {
        if (jSONObject != null) {
            QsbkApp.valuesMap = new HashMap();
            QsbkApp.getInstance().updateCurrentUserInfo(jSONObject);
        }
    }

    private static void c(String str, String str2, String str3) {
        if (!f.equalsIgnoreCase(ThirdPartyConstants.THIRDPARTY_TYLE_WX)) {
            StringBuffer stringBuffer = new StringBuffer("accessToken=");
            stringBuffer.append(str);
            stringBuffer.append(com.alipay.sdk.sys.a.b);
            stringBuffer.append("expires_in=").append((Long.valueOf(str2).longValue() * 1000) + System.currentTimeMillis());
            SharePreferenceUtils.setSharePreferencesValue(str3, stringBuffer.toString());
        }
    }

    public static boolean handleQQloginRequest(int i, int i2, Intent intent) {
        if (j == null || k == null) {
            return false;
        }
        Tencent tencent = j;
        Tencent.onActivityResultData(i, i2, intent, k);
        return true;
    }

    public static void onLoginSuccess() {
        IMNotifyManager.getSettingFromCloud();
        LocalBroadcastManager.getInstance(e).sendBroadcast(new Intent(MainActivity.ACTION_QB_LOGIN));
        new IMPreConnector().preConnect("onLoginSuccess");
        new y("qbk-LoginAct-2").start();
    }

    protected static void a() {
        if (e instanceof Activity) {
            ((Activity) e).setProgressBarIndeterminateVisibility(true);
        }
    }

    protected static void b() {
        if (e instanceof Activity) {
            ((Activity) e).setProgressBarIndeterminateVisibility(false);
        }
    }

    private static void p() {
        if (m == null) {
            m = ProgressDialog.show(e, null, "请稍候...", true, false);
        }
        m.show();
    }

    private static void q() {
        if (m != null) {
            m.dismiss();
        }
    }

    public static boolean isNeedToShow() {
        return !r();
    }

    public static int getPosition() {
        return d;
    }

    private static boolean r() {
        return SharePreferenceUtils.getSharePreferencesBoolValue("welcome_card_has_show");
    }

    private static void s() {
        SharePreferenceUtils.setSharePreferencesValue("welcome_card_has_show", true);
    }

    public static void syncLoadIfNeed(Context context) {
        DebugUtil.debug(c, "syncLoadIfNeed, mbWelcomeCardHasShow=" + r());
    }

    public static View getView(LayoutInflater layoutInflater, View view, ViewGroup viewGroup, int i) {
        ViewHolder viewHolder;
        if (view == null || !(view.getTag() instanceof ViewHolder)) {
            view = layoutInflater.inflate(R.layout.layout_welcome_card, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        UIHelper.imageViewFilter(viewHolder.qiubaiOfficalAvatar);
        viewHolder.initListener();
        if (viewHolder.divider != null) {
            int i2;
            View view2 = viewHolder.divider;
            if (i == 0) {
                i2 = 8;
            } else {
                i2 = 0;
            }
            view2.setVisibility(i2);
        }
        t();
        s();
        return view;
    }

    private static void t() {
        StatService.onEvent(AppContext.getContext(), "welcomeCard", null);
        StatSDK.onEvent(AppContext.getContext(), "welcomeCard", null);
    }

    private static void b(String str, String str2) {
        StatService.onEvent(AppContext.getContext(), str, str2);
        StatSDK.onEvent(AppContext.getContext(), str, str2);
    }

    private static void d(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("sns", f);
        hashMap.put("token", str);
        hashMap.put("expires_in", str2);
        if (str3 != null) {
            hashMap.put("openid", i);
        }
        a();
        new z("qbk-LoginAct-1", hashMap).start();
    }
}
