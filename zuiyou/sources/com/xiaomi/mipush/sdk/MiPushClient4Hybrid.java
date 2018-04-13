package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.c.a;
import com.xiaomi.xmpush.thrift.ak;
import com.xiaomi.xmpush.thrift.ar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiPushClient4Hybrid {
    private static Map<String, a> a = new HashMap();
    private static Map<String, Long> b = new HashMap();
    private static o c;

    public static void onNotificationMessageClicked(Context context, String str, MiPushMessage miPushMessage) {
        if (!TextUtils.isEmpty(str) && c != null) {
            c.a(str, miPushMessage);
        }
    }

    public static void onReceiveRegisterResult(Context context, ak akVar) {
        List list;
        String k = akVar.k();
        if (akVar.f() == 0) {
            a aVar = (a) a.get(k);
            if (aVar != null) {
                aVar.b(akVar.h, akVar.i);
                c.a(context).a(k, aVar);
            }
        }
        if (TextUtils.isEmpty(akVar.h)) {
            list = null;
        } else {
            list = new ArrayList();
            list.add(akVar.h);
        }
        MiPushCommandMessage generateCommandMessage = PushMessageHelper.generateCommandMessage("register", list, akVar.f, akVar.g, null);
        if (c != null) {
            c.a(k, generateCommandMessage);
        }
    }

    public static void onReceiveUnregisterResult(Context context, ar arVar) {
        MiPushCommandMessage generateCommandMessage = PushMessageHelper.generateCommandMessage("unregister", null, arVar.f, arVar.g, null);
        String h = arVar.h();
        if (c != null) {
            c.b(h, generateCommandMessage);
        }
    }
}
