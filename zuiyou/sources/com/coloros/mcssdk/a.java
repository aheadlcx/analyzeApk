package com.coloros.mcssdk;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PointerIconCompat;
import com.coloros.mcssdk.a.b;
import com.coloros.mcssdk.a.d;
import com.coloros.mcssdk.a.e;
import com.coloros.mcssdk.b.c;
import com.iflytek.cloud.SpeechConstant;
import com.sina.weibo.sdk.constant.WBConstants;
import java.util.ArrayList;
import java.util.List;

public class a {
    private static int h = 0;
    private Context a;
    private List<c> b;
    private List<d> c;
    private String d;
    private String e;
    private String f;
    private com.coloros.mcssdk.d.c g;

    private a() {
        this.b = new ArrayList();
        this.c = new ArrayList();
        synchronized (a.class) {
            if (h > 0) {
                throw new RuntimeException("PushManager can't create again!");
            }
            h++;
        }
        a(new com.coloros.mcssdk.a.a());
        a(new e());
        a(new b());
        a(new com.coloros.mcssdk.b.a());
        a(new com.coloros.mcssdk.b.d());
        a(new com.coloros.mcssdk.b.b());
    }

    private void a(int i) {
        a(i, "");
    }

    private void a(int i, String str) {
        Intent intent = new Intent();
        intent.setAction("com.coloros.mcssdk.action.RECEIVE_SDK_MESSAGE");
        intent.setPackage("com.coloros.mcs");
        intent.putExtra("type", i);
        intent.putExtra(SpeechConstant.PARAMS, str);
        intent.putExtra("appPackage", this.a.getPackageName());
        intent.putExtra(WBConstants.SSO_APP_KEY, this.d);
        intent.putExtra("appSecret", this.e);
        intent.putExtra("registerID", this.f);
        intent.putExtra("sdkVersion", f());
        this.a.startService(intent);
    }

    public static void a(Context context, com.coloros.mcssdk.e.a aVar, String str) {
        try {
            Intent intent = new Intent();
            intent.setAction("com.coloros.mcssdk.action.RECEIVE_SDK_MESSAGE");
            intent.setPackage("com.coloros.mcs");
            intent.putExtra("type", 12291);
            intent.putExtra("taskID", aVar.e());
            intent.putExtra("appPackage", aVar.f());
            intent.putExtra("messageID", aVar.g());
            intent.putExtra("messageType", aVar.a());
            intent.putExtra("eventID", str);
            context.startService(intent);
        } catch (Exception e) {
            com.coloros.mcssdk.c.c.b("statisticMessage--Exception" + e.getMessage());
        }
    }

    public static void a(Context context, com.coloros.mcssdk.e.d dVar, String str) {
        try {
            Intent intent = new Intent();
            intent.setAction("com.coloros.mcssdk.action.RECEIVE_SDK_MESSAGE");
            intent.setPackage("com.coloros.mcs");
            intent.putExtra("type", 12291);
            intent.putExtra("taskID", dVar.e());
            intent.putExtra("appPackage", dVar.f());
            intent.putExtra("messageID", dVar.g());
            intent.putExtra("messageType", dVar.a());
            intent.putExtra("eventID", str);
            context.startService(intent);
        } catch (Exception e) {
            com.coloros.mcssdk.c.c.b("statisticMessage--Exception" + e.getMessage());
        }
    }

    private synchronized void a(d dVar) {
        if (dVar != null) {
            this.c.add(dVar);
        }
    }

    private synchronized void a(c cVar) {
        if (cVar != null) {
            this.b.add(cVar);
        }
    }

    public static boolean a(Context context) {
        return com.coloros.mcssdk.c.d.a(context, "com.coloros.mcs") && com.coloros.mcssdk.c.d.b(context, "com.coloros.mcs") >= PointerIconCompat.TYPE_NO_DROP && com.coloros.mcssdk.c.d.a(context, "com.coloros.mcs", "supportOpenPush");
    }

    public static a c() {
        return c.a;
    }

    private void g() {
        if (this.a == null) {
            throw new IllegalArgumentException("please call the register first!");
        }
    }

    public List<d> a() {
        return this.c;
    }

    public void a(Context context, String str, String str2, com.coloros.mcssdk.d.c cVar) {
        if (context == null) {
            throw new IllegalArgumentException("context is null !");
        } else if (a(context)) {
            this.d = str;
            this.e = str2;
            this.a = context.getApplicationContext();
            this.g = cVar;
            a(12289);
        } else {
            throw new IllegalArgumentException("the phone is not support oppo push!");
        }
    }

    public void a(String str) {
        this.f = str;
    }

    public List<c> b() {
        return this.b;
    }

    public com.coloros.mcssdk.d.c d() {
        return this.g;
    }

    public void e() {
        g();
        a(12289);
    }

    public String f() {
        return "1.0.1";
    }
}
