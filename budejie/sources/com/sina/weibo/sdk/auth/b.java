package com.sina.weibo.sdk.auth;

import android.os.Bundle;
import android.text.TextUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.tencent.connect.common.Constants;

public class b {
    Bundle a;
    private String b = "";
    private String c = "";
    private String d = "";
    private long e = 0;
    private String f = "";

    public static b a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        b bVar = new b();
        bVar.a(a(bundle, HistoryOpenHelper.COLUMN_UID, ""));
        bVar.b(a(bundle, Constants.PARAM_ACCESS_TOKEN, ""));
        bVar.d(a(bundle, Constants.PARAM_EXPIRES_IN, ""));
        bVar.c(a(bundle, "refresh_token", ""));
        bVar.e(a(bundle, "phone_num", ""));
        bVar.b(bundle);
        return bVar;
    }

    public boolean a() {
        return !TextUtils.isEmpty(this.c);
    }

    public String toString() {
        return "uid: " + this.b + ", " + Constants.PARAM_ACCESS_TOKEN + ": " + this.c + ", " + "refresh_token" + ": " + this.d + ", " + "phone_num" + ": " + this.f + ", " + Constants.PARAM_EXPIRES_IN + ": " + Long.toString(this.e);
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public String c() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public String d() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public long e() {
        return this.e;
    }

    public void a(long j) {
        this.e = j;
    }

    public void d(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals("0")) {
            a(System.currentTimeMillis() + (Long.parseLong(str) * 1000));
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

    private void e(String str) {
        this.f = str;
    }

    public void b(Bundle bundle) {
        this.a = bundle;
    }
}
