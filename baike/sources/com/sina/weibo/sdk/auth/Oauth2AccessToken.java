package com.sina.weibo.sdk.auth;

import android.os.Bundle;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Oauth2AccessToken {
    public static final String KEY_ACCESS_TOKEN = "access_token";
    public static final String KEY_EXPIRES_IN = "expires_in";
    public static final String KEY_PHONE_NUM = "phone_num";
    public static final String KEY_REFRESH_TOKEN = "refresh_token";
    public static final String KEY_UID = "uid";
    Bundle a;
    private String b = "";
    private String c = "";
    private String d = "";
    private long e = 0;
    private String f = "";

    @Deprecated
    public Oauth2AccessToken(String str) {
        if (str != null && str.indexOf("{") >= 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                setUid(jSONObject.optString("uid"));
                setToken(jSONObject.optString("access_token"));
                setExpiresIn(jSONObject.optString("expires_in"));
                setRefreshToken(jSONObject.optString("refresh_token"));
                a(jSONObject.optString(KEY_PHONE_NUM));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public Oauth2AccessToken(String str, String str2) {
        this.c = str;
        this.e = System.currentTimeMillis();
        if (str2 != null) {
            this.e += Long.parseLong(str2) * 1000;
        }
    }

    public static Oauth2AccessToken parseAccessToken(String str) {
        if (!TextUtils.isEmpty(str) && str.indexOf("{") >= 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                Oauth2AccessToken oauth2AccessToken = new Oauth2AccessToken();
                oauth2AccessToken.setUid(jSONObject.optString("uid"));
                oauth2AccessToken.setToken(jSONObject.optString("access_token"));
                oauth2AccessToken.setExpiresIn(jSONObject.optString("expires_in"));
                oauth2AccessToken.setRefreshToken(jSONObject.optString("refresh_token"));
                oauth2AccessToken.a(jSONObject.optString(KEY_PHONE_NUM));
                return oauth2AccessToken;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Oauth2AccessToken parseAccessToken(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        Oauth2AccessToken oauth2AccessToken = new Oauth2AccessToken();
        oauth2AccessToken.setUid(a(bundle, "uid", ""));
        oauth2AccessToken.setToken(a(bundle, "access_token", ""));
        oauth2AccessToken.setExpiresIn(a(bundle, "expires_in", ""));
        oauth2AccessToken.setRefreshToken(a(bundle, "refresh_token", ""));
        oauth2AccessToken.a(a(bundle, KEY_PHONE_NUM, ""));
        oauth2AccessToken.setBundle(bundle);
        return oauth2AccessToken;
    }

    public boolean isSessionValid() {
        return !TextUtils.isEmpty(this.c);
    }

    public String toString() {
        return "uid: " + this.b + ", " + "access_token" + ": " + this.c + ", " + "refresh_token" + ": " + this.d + ", " + KEY_PHONE_NUM + ": " + this.f + ", " + "expires_in" + ": " + Long.toString(this.e);
    }

    public String getUid() {
        return this.b;
    }

    public void setUid(String str) {
        this.b = str;
    }

    public String getToken() {
        return this.c;
    }

    public void setToken(String str) {
        this.c = str;
    }

    public String getRefreshToken() {
        return this.d;
    }

    public void setRefreshToken(String str) {
        this.d = str;
    }

    public long getExpiresTime() {
        return this.e;
    }

    public void setExpiresTime(long j) {
        this.e = j;
    }

    public void setExpiresIn(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals("0")) {
            setExpiresTime(System.currentTimeMillis() + (Long.parseLong(str) * 1000));
        }
    }

    private static String a(Bundle bundle, String str, String str2) {
        if (bundle == null) {
            return str2;
        }
        String string = bundle.getString(str);
        if (string != null) {
            return string;
        }
        return str2;
    }

    public String getPhoneNum() {
        return this.f;
    }

    private void a(String str) {
        this.f = str;
    }

    public Bundle getBundle() {
        return this.a;
    }

    public void setBundle(Bundle bundle) {
        this.a = bundle;
    }
}
