package qsbk.app.wxapi;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.core.AsyncTask;
import qsbk.app.thirdparty.ThirdPartyException;
import qsbk.app.thirdparty.ThirdPartyParameters;
import qsbk.app.thirdparty.net.HttpManager;
import qsbk.app.utils.image.issue.Logger;
import qsbk.app.wxapi.WXAuthHelper.WXAuthException;
import qsbk.app.wxapi.WXAuthHelper.WXAuthToken;

class b extends AsyncTask<String, Void, String> {
    final /* synthetic */ String a;
    final /* synthetic */ WXAuthHelper b;

    b(WXAuthHelper wXAuthHelper, String str) {
        this.b = wXAuthHelper;
        this.a = str;
    }

    protected String a(String... strArr) {
        ThirdPartyParameters thirdPartyParameters = new ThirdPartyParameters();
        thirdPartyParameters.add("appid", Constants.APP_ID);
        thirdPartyParameters.add("secret", Constants.WX_APP_SECRET);
        thirdPartyParameters.add("code", this.a);
        thirdPartyParameters.add("grant_type", "authorization_code");
        try {
            return HttpManager.openUrl(strArr[0], "GET", thirdPartyParameters);
        } catch (ThirdPartyException e) {
            return null;
        }
    }

    protected void a(String str) {
        if (TextUtils.isEmpty(str)) {
            this.b.a(new WXAuthException("出现网络错误，请稍后重试。"));
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("access_token")) {
                WXAuthToken wXAuthToken = new WXAuthToken();
                wXAuthToken.token = jSONObject.getString("access_token");
                wXAuthToken.openId = jSONObject.getString("openid");
                wXAuthToken.expiresIn = jSONObject.getLong("expires_in");
                wXAuthToken.refreshToken = jSONObject.getString("refresh_token");
                this.b.a(wXAuthToken);
                return;
            }
            if (jSONObject.has("errmsg")) {
                this.b.a(new WXAuthException(jSONObject.getString("errmsg")));
                return;
            }
            this.b.a(new WXAuthException("出现未知错误，请稍后重试。"));
        } catch (JSONException e) {
            Logger.getInstance().debug(ActionBarLoginActivity.class.getSimpleName(), "WX onPostExecute ", e + "");
        }
    }
}
