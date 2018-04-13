package qsbk.app.thirdparty;

import android.text.TextUtils;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import org.json.JSONException;
import org.json.JSONObject;

public class ThirdOauth2AccessToken {
    public static final int QQ = 2;
    public static final int WEIBO = 1;
    private int a;
    private String b;
    private String c;
    private long d;

    public ThirdOauth2AccessToken() {
        this.b = "";
        this.c = "";
        this.d = 0;
    }

    public ThirdOauth2AccessToken(String str) {
        this.b = "";
        this.c = "";
        this.d = 0;
        if (str != null && str.indexOf("{") >= 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                setToken(jSONObject.optString("access_token"));
                setExpiresIn(jSONObject.optString("expires_in"));
                setRefreshToken(jSONObject.optString("refresh_token"));
                setType(jSONObject.optInt("type"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public ThirdOauth2AccessToken(String str, String str2) {
        this.b = "";
        this.c = "";
        this.d = 0;
        this.b = str;
        this.d = System.currentTimeMillis() + (Long.parseLong(str2) * 1000);
    }

    public ThirdOauth2AccessToken(Oauth2AccessToken oauth2AccessToken) {
        this.b = "";
        this.c = "";
        this.d = 0;
        this.a = 1;
        this.b = oauth2AccessToken.getToken();
        this.d = oauth2AccessToken.getExpiresTime();
    }

    public boolean isSessionValid() {
        return !TextUtils.isEmpty(this.b) && (this.d == 0 || System.currentTimeMillis() < this.d);
    }

    public String getToken() {
        return this.b;
    }

    public void setToken(String str) {
        this.b = str;
    }

    public String getRefreshToken() {
        return this.c;
    }

    public void setRefreshToken(String str) {
        this.c = str;
    }

    public long getExpiresTime() {
        return this.d;
    }

    public void setExpiresTime(long j) {
        this.d = j;
    }

    public void setExpiresIn(String str) {
        if (str != null && !str.equals("0")) {
            setExpiresTime(System.currentTimeMillis() + (Long.parseLong(str) * 1000));
        }
    }

    public int getType() {
        return this.a;
    }

    public void setType(int i) {
        this.a = i;
    }
}
