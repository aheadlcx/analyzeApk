package qsbk.app.thirdparty;

import android.content.Context;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.constant.WBConstants;
import com.tencent.tauth.Tencent;
import qsbk.app.QsbkApp;
import qsbk.app.thirdparty.Utility.Utility;
import qsbk.app.thirdparty.ui.ThirdPartyDialog;

public class ThirdParty {
    public static final String KEY_EXPIRES = "expires_in";
    public static final String KEY_REFRESHTOKEN = "refresh_token";
    public static final String KEY_TOKEN = "access_token";
    public static String QQ_OAUTH2_ACCESS_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    public static String SINA_OAUTH2_ACCESS_AUTHORIZE = "https://open.weibo.cn/oauth2/authorize";
    private static ThirdParty a = null;
    public static String app_key = "";
    public static String redirecturl = "";
    public static String thirdparty_type = "";
    public ThirdOauth2AccessToken accessToken = null;

    public static synchronized ThirdParty getInstance(String str, String str2, String str3) {
        ThirdParty thirdParty;
        int i = 1;
        synchronized (ThirdParty.class) {
            int i2 = a == null ? 1 : 0;
            if (a == null || !app_key.equals(str)) {
                i = 0;
            }
            if ((i | i2) != 0) {
                a = new ThirdParty();
            }
            app_key = str;
            redirecturl = str2;
            thirdparty_type = str3;
            WbSdk.install(QsbkApp.getInstance(), new AuthInfo(QsbkApp.getInstance(), ThirdPartyConstants.SINA_CONSUMER_KEY, ThirdPartyConstants.SINA_REDIRECT_URL, ThirdPartyConstants.SINA_SCOPE));
            thirdParty = a;
        }
        return thirdParty;
    }

    public static Tencent getTencentInstance(String str, Context context) {
        return Tencent.createInstance(str, context);
    }

    public void setupConsumerConfig(String str, String str2) {
        app_key = str;
        redirecturl = str2;
    }

    public void authorize(Context context, ThirdPartyAuthListener thirdPartyAuthListener) {
        startAuthDialog(context, thirdPartyAuthListener);
    }

    public void startAuthDialog(Context context, ThirdPartyAuthListener thirdPartyAuthListener) {
        startDialog(context, new ThirdPartyParameters(), new a(this, thirdPartyAuthListener));
    }

    public void startDialog(Context context, ThirdPartyParameters thirdPartyParameters, ThirdPartyAuthListener thirdPartyAuthListener) {
        thirdPartyParameters.add("client_id", app_key);
        thirdPartyParameters.add(WBConstants.AUTH_PARAMS_RESPONSE_TYPE, "token");
        thirdPartyParameters.add("redirect_uri", redirecturl);
        thirdPartyParameters.add("display", "mobile");
        if (this.accessToken != null && this.accessToken.isSessionValid()) {
            thirdPartyParameters.add("access_token", this.accessToken.getToken());
        }
        String str = (ThirdPartyConstants.THIRDPARTY_TYLE_SINA.equals(thirdparty_type) ? SINA_OAUTH2_ACCESS_AUTHORIZE : QQ_OAUTH2_ACCESS_AUTHORIZE) + "?" + Utility.encodeUrl(thirdPartyParameters);
        if (context.checkCallingOrSelfPermission("android.permission.INTERNET") != 0) {
            Utility.showAlert(context, "Error", "Application requires permission to access the Internet");
        } else {
            new ThirdPartyDialog(context, str, thirdPartyAuthListener).show();
        }
    }
}
