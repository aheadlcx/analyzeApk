package qsbk.app.model;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
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
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.activity.MainActivity;
import qsbk.app.activity.security.AccessTokenKeeper;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.im.IMNotifyManager;
import qsbk.app.im.IMPreConnector;
import qsbk.app.thirdparty.ThirdOauth2AccessToken;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.AppContext;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.wxapi.WXAuthHelper;
import qsbk.app.wxapi.WXAuthHelper.OnWXAuthListener;
import qsbk.app.wxapi.WXAuthHelper.WXAuthException;
import qsbk.app.wxapi.WXAuthHelper.WXAuthToken;

public class UserLoginGuideCard {
    protected static JSONObject a = null;
    protected static Class<?> b = null;
    private static final String c = UserLoginGuideCard.class.getSimpleName();
    private static Context d;
    private static int e = 9;
    private static String f = null;
    private static int g = -1;
    private static boolean h = false;
    private static ProgressDialog i;
    public static volatile UserLoginGuideCard instance;
    private static String j;
    private static String k;
    private static String l;
    private static WXAuthHelper m;
    public static ThirdOauth2AccessToken mAccessToken;
    private static String n;
    private static long o;
    private static Tencent p;
    private static IUiListener q;
    private static Handler r = new p();
    public final String SIGNED_IN = ActionBarLoginActivity.SIGNED_IN;
    public final String TOAST_WHEN_CREATED = ActionBarLoginActivity.TOAST_WHEN_CREATED;
    private int s = 0;
    private int t = 5;

    public static class ViewHolder {
        public ImageView loginCard;
        public LinearLayout qiubaiLogin;
        public ImageView qiubaiOfficalAvatar;
        public LinearLayout qqLogin;
        public LinearLayout weiboLogin;
        public LinearLayout weixinLogin;

        public ViewHolder(View view) {
            this.qiubaiOfficalAvatar = (ImageView) view.findViewById(R.id.iv_qiubai_avatar);
            this.loginCard = (ImageView) view.findViewById(R.id.iv_card_desc);
            this.qqLogin = (LinearLayout) view.findViewById(R.id.ll_qq_login);
            this.weixinLogin = (LinearLayout) view.findViewById(R.id.ll_weixin_login);
            this.weiboLogin = (LinearLayout) view.findViewById(R.id.ll_weibo_login);
            this.qiubaiLogin = (LinearLayout) view.findViewById(R.id.ll_qiubai_login);
        }

        public void initListener() {
            this.qqLogin.setOnClickListener(new t(this));
            this.weixinLogin.setOnClickListener(new u(this));
            this.weiboLogin.setOnClickListener(new v(this));
            this.qiubaiLogin.setOnClickListener(new w(this));
        }
    }

    static class a implements IUiListener {
        a() {
        }

        protected void a(JSONObject jSONObject) {
            try {
                DebugUtil.debug(UserLoginGuideCard.c, "QQ:" + jSONObject.toString());
                UserLoginGuideCard.n = jSONObject.getString("openid");
                UserLoginGuideCard.k = jSONObject.getString("access_token");
                UserLoginGuideCard.l = jSONObject.getString("expires_in");
                UserLoginGuideCard.mAccessToken = new ThirdOauth2AccessToken(UserLoginGuideCard.k, UserLoginGuideCard.l);
                AccessTokenKeeper.keepAccessToken(UserLoginGuideCard.d, UserLoginGuideCard.mAccessToken);
                UserLoginGuideCard.d(UserLoginGuideCard.k, UserLoginGuideCard.l, null);
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
            UserLoginGuideCard.p();
        }

        public void onComplete(WXAuthToken wXAuthToken) {
            UserLoginGuideCard.q();
            if (wXAuthToken != null && wXAuthToken.isValid()) {
                UserLoginGuideCard.k = wXAuthToken.token;
                UserLoginGuideCard.l = wXAuthToken.expiresIn + "";
                UserLoginGuideCard.n = wXAuthToken.openId;
                UserLoginGuideCard.d(wXAuthToken.token, wXAuthToken.expiresIn + "", wXAuthToken.openId);
                if (UserLoginGuideCard.m != null) {
                    UserLoginGuideCard.m.onDestroy();
                }
            }
        }

        public void onError(WXAuthException wXAuthException) {
            UserLoginGuideCard.q();
            if (wXAuthException != null) {
                String message = wXAuthException.getMessage();
                if (message != null) {
                    ToastAndDialog.makeNegativeToast(QsbkApp.mContext, message, Integer.valueOf(0)).show();
                }
            }
        }
    }

    private UserLoginGuideCard() {
    }

    private UserLoginGuideCard(Context context) {
        d = context;
    }

    public static UserLoginGuideCard getInstance(Context context) {
        if (instance == null) {
            synchronized (UserLoginGuideCard.class) {
                if (instance == null) {
                    instance = new UserLoginGuideCard(context);
                }
            }
        }
        return instance;
    }

    public static UserLoginGuideCard getNewInstance(Context context) {
        return new UserLoginGuideCard(context);
    }

    public static void handleToken(JSONObject jSONObject) {
        if (jSONObject != null) {
            QsbkApp.valuesMap = new HashMap();
            QsbkApp.getInstance().updateCurrentUserInfo(jSONObject);
        }
    }

    private static void c(String str, String str2, String str3) {
        if (!j.equalsIgnoreCase(ThirdPartyConstants.THIRDPARTY_TYLE_WX)) {
            StringBuffer stringBuffer = new StringBuffer("accessToken=");
            stringBuffer.append(str);
            stringBuffer.append(com.alipay.sdk.sys.a.b);
            stringBuffer.append("expires_in=").append((Long.valueOf(str2).longValue() * 1000) + System.currentTimeMillis());
            SharePreferenceUtils.setSharePreferencesValue(str3, stringBuffer.toString());
        }
    }

    public static void onLoginSuccess() {
        IMNotifyManager.getSettingFromCloud();
        LocalBroadcastManager.getInstance(d).sendBroadcast(new Intent(MainActivity.ACTION_QB_LOGIN));
        new IMPreConnector().preConnect("onLoginSuccess");
        new q("qbk-LoginAct-2").start();
    }

    public static boolean isNeedToShow() {
        return QsbkApp.currentUser == null;
    }

    public static int getPosition() {
        return e;
    }

    private static void a(Context context) {
        context.getSharedPreferences("evaluate", 0).edit().putInt("position", e).putString("title", f).putInt("state", g).apply();
    }

    private static void b(Context context) {
        context.getSharedPreferences("evaluate", 0).edit().clear().apply();
    }

    public static void syncLoadIfNeed(Context context, boolean z) {
        int i = 0;
        if (!z) {
            b(context);
            if (HttpUtils.netIsAvailable()) {
                g = 0;
                h = false;
                try {
                    Object obj = HttpClient.getIntentce().get("https://api.qiushibaike.com/eval?uuid=" + DeviceUtils.getAndroidId());
                    if (!TextUtils.isEmpty(obj)) {
                        JSONObject jSONObject = new JSONObject(obj);
                        if (jSONObject.getInt(NotificationCompat.CATEGORY_ERROR) == 0) {
                            h = true;
                            if (jSONObject.optInt("flag", 0) != 1) {
                                i = -1;
                            }
                            g = i;
                            e = jSONObject.optInt("pos", 9);
                            f = jSONObject.optString("title");
                            a(context);
                        }
                    }
                } catch (QiushibaikeException e) {
                    e.printStackTrace();
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private static void p() {
        if (i == null) {
            i = ProgressDialog.show(d, null, "请稍候...", true, false);
        }
        i.show();
    }

    private static void q() {
        if (i != null) {
            i.dismiss();
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

    public static View getView(LayoutInflater layoutInflater, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null || !(view.getTag() instanceof ViewHolder)) {
            view = layoutInflater.inflate(R.layout.layout_user_login_guide_card, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        b("login_guide_card", null);
        UIHelper.imageViewFilter(viewHolder.qiubaiOfficalAvatar);
        if (h) {
            h = false;
            g++;
            a(viewGroup.getContext());
        }
        viewHolder.initListener();
        view.setOnClickListener(new r(viewHolder));
        return view;
    }

    private static void b(String str, String str2) {
        StatService.onEvent(AppContext.getContext(), str, str2);
        StatSDK.onEvent(AppContext.getContext(), str, str2);
    }

    private static void d(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("sns", j);
        hashMap.put("token", k);
        hashMap.put("expires_in", l);
        if (str3 != null) {
            hashMap.put("openid", n);
        }
        a();
        new s("qbk-LoginAct-1", hashMap).start();
    }

    public static boolean handleQQloginRequest(int i, int i2, Intent intent) {
        if (p == null || q == null) {
            return false;
        }
        Tencent tencent = p;
        Tencent.onActivityResultData(i, i2, intent, q);
        return true;
    }

    public synchronized void insertLoginGuideCard(int i, ArrayList<Object> arrayList) {
        DebugUtil.debug(c, "insertLoginGuideCard, position=" + i + ",objs=" + arrayList);
        int i2 = this.t + i;
        if (i2 > 0 && !arrayList.isEmpty() && i2 < arrayList.size() - 1) {
            DebugUtil.debug(c, "insertLoginGuideCard, insertPosition=" + i2);
        }
    }
}
