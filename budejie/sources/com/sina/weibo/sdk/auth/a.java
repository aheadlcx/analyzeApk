package com.sina.weibo.sdk.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.tencent.connect.common.Constants;

public class a {
    public static void a(Context context, b bVar) {
        if (context != null && bVar != null) {
            Editor edit = context.getSharedPreferences("com_weibo_sdk_android", 32768).edit();
            edit.putString(HistoryOpenHelper.COLUMN_UID, bVar.b());
            edit.putString(Constants.PARAM_ACCESS_TOKEN, bVar.c());
            edit.putString("refresh_token", bVar.d());
            edit.putLong(Constants.PARAM_EXPIRES_IN, bVar.e());
            edit.commit();
        }
    }

    public static b a(Context context) {
        if (context == null) {
            return null;
        }
        b bVar = new b();
        SharedPreferences sharedPreferences = context.getSharedPreferences("com_weibo_sdk_android", 32768);
        bVar.a(sharedPreferences.getString(HistoryOpenHelper.COLUMN_UID, ""));
        bVar.b(sharedPreferences.getString(Constants.PARAM_ACCESS_TOKEN, ""));
        bVar.c(sharedPreferences.getString("refresh_token", ""));
        bVar.a(sharedPreferences.getLong(Constants.PARAM_EXPIRES_IN, 0));
        return bVar;
    }
}
