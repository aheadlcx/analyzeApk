package com.xiaomi.push.mpcd.job;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.push.mpcd.BroadcastActionsReceiver;
import com.xiaomi.xmpush.thrift.d;

public class e extends f {
    public static String a = "";
    public static String b = "";

    public e(Context context, int i) {
        super(context, i);
    }

    private String a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        String[] split = str2.split(",");
        if (split.length <= 10) {
            return str2;
        }
        for (int length = split.length - 1; length >= split.length - 10; length--) {
            str = str + split[length];
        }
        return str;
    }

    public int a() {
        return 12;
    }

    public String b() {
        String str = "";
        if (!TextUtils.isEmpty(a)) {
            str = str + a(BroadcastActionsReceiver.a, a);
            a = "";
        }
        if (TextUtils.isEmpty(b)) {
            return str;
        }
        str = str + a(BroadcastActionsReceiver.b, b);
        b = "";
        return str;
    }

    public d d() {
        return d.BroadcastAction;
    }
}
