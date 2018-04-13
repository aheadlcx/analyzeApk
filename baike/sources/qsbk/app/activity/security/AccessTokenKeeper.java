package qsbk.app.activity.security;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import qsbk.app.thirdparty.ThirdOauth2AccessToken;

public class AccessTokenKeeper {
    public static final String PREFERENCES_NAME = "com_weibo_sdk_android";

    public static void keepAccessToken(Context context, ThirdOauth2AccessToken thirdOauth2AccessToken) {
        Editor edit = context.getSharedPreferences(PREFERENCES_NAME, 32768).edit();
        edit.putString("token", thirdOauth2AccessToken.getToken());
        edit.putLong("expiresTime", thirdOauth2AccessToken.getExpiresTime());
        edit.putInt("type", thirdOauth2AccessToken.getType());
        edit.apply();
    }

    public static void clear(Context context) {
        Editor edit = context.getSharedPreferences(PREFERENCES_NAME, 32768).edit();
        edit.clear();
        edit.apply();
    }

    public static ThirdOauth2AccessToken readAccessToken(Context context) {
        ThirdOauth2AccessToken thirdOauth2AccessToken = new ThirdOauth2AccessToken();
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, 32768);
        thirdOauth2AccessToken.setToken(sharedPreferences.getString("token", ""));
        thirdOauth2AccessToken.setExpiresTime(sharedPreferences.getLong("expiresTime", 0));
        thirdOauth2AccessToken.setType(sharedPreferences.getInt("type", 0));
        return thirdOauth2AccessToken;
    }
}
