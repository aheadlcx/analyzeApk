package qsbk.app.wxapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelmsg.SendAuth.Req;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.share.ShareUtils;
import qsbk.app.utils.image.issue.Logger;

public class WXAuthHelper {
    public static final String WX_SCOPE = "snsapi_userinfo";
    public static final String WX_STATE = "qsbk_wechat_login";
    private static final String a = WXAuthHelper.class.getSimpleName();
    public static volatile WXAuthHelper instance;
    private final Context b;
    private OnWXAuthListener c;
    private BroadcastReceiver d = new a(this);

    public interface OnWXAuthListener {
        void onComplete(WXAuthToken wXAuthToken);

        void onError(WXAuthException wXAuthException);

        void onStart();
    }

    public static class WXAuthException extends Exception {
        public WXAuthException(String str, Throwable th) {
            super(str, th);
        }

        public WXAuthException(String str) {
            super(str);
        }

        public WXAuthException(Throwable th) {
            super(th);
        }
    }

    public static class WXAuthToken {
        public long expiresIn = -1;
        public String openId;
        public String refreshToken;
        public String token;

        public WXAuthToken(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.token = jSONObject.getString("access_token");
                this.openId = jSONObject.getString("openid");
                this.expiresIn = jSONObject.getLong("expires_in");
                this.refreshToken = jSONObject.getString("refresh_token");
            } catch (JSONException e) {
            }
        }

        public boolean isValid() {
            return (this.token == null || this.openId == null || this.expiresIn == -1) ? false : true;
        }
    }

    private WXAuthHelper(Context context) {
        this.b = context;
    }

    public static WXAuthHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (WXAuthHelper.class) {
                if (instance == null) {
                    instance = new WXAuthHelper(context);
                }
            }
        }
        return instance;
    }

    private void a() {
        if (this.c != null) {
            this.c.onStart();
        }
    }

    private void a(WXAuthToken wXAuthToken) {
        if (this.c != null) {
            this.c.onComplete(wXAuthToken);
        }
    }

    private void a(WXAuthException wXAuthException) {
        if (this.c != null) {
            this.c.onError(wXAuthException);
        }
    }

    private void a(String str, String str2) {
        a();
        new b(this, str2).executeOnExecutor(SimpleHttpTask.SERIAL_EXECUTOR, new String[]{"https://api.weixin.qq.com/sns/oauth2/access_token"});
    }

    public void startAuth(OnWXAuthListener onWXAuthListener) {
        if (this.b == null) {
            Logger.getInstance().debug(a, "startAuth", " paramter activity is null");
            return;
        }
        this.c = onWXAuthListener;
        if (ShareUtils.isWxInstalled(this.b)) {
            BaseReq req = new Req();
            req.scope = WX_SCOPE;
            req.state = WX_STATE;
            WXAPIFactory.createWXAPI(this.b, Constants.APP_ID, false).sendReq(req);
            this.b.registerReceiver(this.d, new IntentFilter(WX_STATE));
            return;
        }
        a(new WXAuthException("微信未安装，请先安装微信"));
    }

    public void onDestroy() {
        try {
            this.b.unregisterReceiver(this.d);
            instance = null;
        } catch (Throwable th) {
        }
    }
}
