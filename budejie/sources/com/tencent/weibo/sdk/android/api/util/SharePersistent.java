package com.tencent.weibo.sdk.android.api.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.tencent.weibo.sdk.android.model.AccountModel;

public class SharePersistent {
    private static final String FILE_NAME = "ANDROID_SDK";
    private static SharePersistent instance;

    private SharePersistent() {
    }

    public static SharePersistent getInstance() {
        if (instance == null) {
            instance = new SharePersistent();
        }
        return instance;
    }

    public boolean put(Context context, String str, String str2) {
        Editor edit = context.getSharedPreferences(FILE_NAME, 0).edit();
        edit.putString(str, str2);
        return edit.commit();
    }

    public boolean put(Context context, String str, long j) {
        Editor edit = context.getSharedPreferences(FILE_NAME, 0).edit();
        edit.putLong(str, j);
        return edit.commit();
    }

    public AccountModel getAccount(Context context) {
        AccountModel accountModel = new AccountModel();
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, 0);
        accountModel.setAccessToken(sharedPreferences.getString("ACCESS_TOKEN", ""));
        accountModel.setExpiresIn(sharedPreferences.getLong("EXPIRES_IN", 0));
        accountModel.setOpenID(sharedPreferences.getString("OPEN_ID", ""));
        accountModel.setOpenKey(sharedPreferences.getString("OPEN_KEY", ""));
        accountModel.setRefreshToken(sharedPreferences.getString("REFRESH_TOKEN", ""));
        accountModel.setName(sharedPreferences.getString("NAME", ""));
        accountModel.setNike(sharedPreferences.getString("NICK", ""));
        return accountModel;
    }

    public String get(Context context, String str) {
        return context.getSharedPreferences(FILE_NAME, 0).getString(str, "");
    }

    public long getLong(Context context, String str) {
        return context.getSharedPreferences(FILE_NAME, 0).getLong(str, 0);
    }

    public boolean clear(Context context, String str) {
        return context.getSharedPreferences(FILE_NAME, 0).edit().clear().commit();
    }
}
