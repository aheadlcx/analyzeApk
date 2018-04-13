package com.huawei.hms.support.api.push.a.a;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.support.api.push.a.a.a.c;
import com.huawei.hms.support.api.push.a.a.b.d;

public abstract class a {
    public static String a(Context context, String str, String str2) {
        String str3 = "";
        if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
            try {
                str3 = d.b(context, new c(context, str).b(str2 + "_v2"));
            } catch (Exception e) {
                if (com.huawei.hms.support.log.a.d()) {
                    com.huawei.hms.support.log.a.d("PushDataEncrypterManager", "getSecureData" + e.getMessage());
                }
            }
            if (TextUtils.isEmpty(str3) && com.huawei.hms.support.log.a.a()) {
                com.huawei.hms.support.log.a.a("PushDataEncrypterManager", "not exist for:" + str2);
            }
        }
        return str3;
    }

    public static void b(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                new c(context, str).d(str2 + "_v2");
            } catch (Exception e) {
                if (com.huawei.hms.support.log.a.d()) {
                    com.huawei.hms.support.log.a.d("PushDataEncrypterManager", "removeSecureData" + e.getMessage());
                }
            }
        }
    }

    public static boolean a(Context context, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return new c(context, str).a(str2 + "_v2", d.a(context, str3));
    }
}
