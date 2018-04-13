package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.data.c;
import com.alipay.sdk.sys.b;
import com.alipay.sdk.util.e;
import com.alipay.sdk.util.j;
import com.alipay.sdk.util.l;
import com.alipay.sdk.widget.a;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AuthTask {
    static final Object a = e.class;
    private Activity b;
    private a c;

    public AuthTask(Activity activity) {
        this.b = activity;
        b a = b.a();
        Context context = this.b;
        c.a();
        a.a(context);
        com.alipay.sdk.app.statistic.a.a(activity);
        this.c = new a(activity, a.c);
    }

    private void a() {
        if (this.c != null) {
            this.c.a();
        }
    }

    private void b() {
        if (this.c != null) {
            this.c.b();
        }
    }

    public synchronized Map<String, String> authV2(String str, boolean z) {
        return j.a(auth(str, z));
    }

    public synchronized String auth(String str, boolean z) {
        String a;
        if (z) {
            a();
        }
        b a2 = b.a();
        Context context = this.b;
        c.a();
        a2.a(context);
        String a3 = h.a();
        try {
            Context context2 = this.b;
            String a4 = new com.alipay.sdk.sys.a(this.b).a(str);
            if (a(context2)) {
                a = new e(context2, new a(this)).a(a4);
                if (!TextUtils.equals(a, e.b)) {
                    if (TextUtils.isEmpty(a)) {
                        a = h.a();
                    }
                    com.alipay.sdk.data.a.b().a(this.b);
                    b();
                    com.alipay.sdk.app.statistic.a.a(this.b, str);
                }
            }
            a = a(context2, a4);
            com.alipay.sdk.data.a.b().a(this.b);
            b();
            com.alipay.sdk.app.statistic.a.a(this.b, str);
        } catch (Exception e) {
            com.alipay.sdk.data.a.b().a(this.b);
            b();
            com.alipay.sdk.app.statistic.a.a(this.b, str);
            a = a3;
        } catch (Throwable th) {
            com.alipay.sdk.data.a.b().a(this.b);
            b();
            com.alipay.sdk.app.statistic.a.a(this.b, str);
        }
        return a;
    }

    private String a(Activity activity, String str) {
        String a;
        i iVar;
        a();
        i iVar2 = null;
        try {
            List a2 = com.alipay.sdk.protocol.b.a(new com.alipay.sdk.packet.impl.a().a((Context) activity, str).a().optJSONObject(com.alipay.sdk.cons.c.c).optJSONObject(com.alipay.sdk.cons.c.d));
            b();
            for (int i = 0; i < a2.size(); i++) {
                if (((com.alipay.sdk.protocol.b) a2.get(i)).a == com.alipay.sdk.protocol.a.WapPay) {
                    a = a((com.alipay.sdk.protocol.b) a2.get(i));
                    return a;
                }
            }
            b();
            iVar = null;
        } catch (IOException e) {
            a = e;
            iVar2 = i.a(i.NETWORK_ERROR.h);
            com.alipay.sdk.app.statistic.a.a("net", (Throwable) a);
            iVar = iVar2;
            if (iVar == null) {
                iVar = i.a(i.FAILED.h);
            }
            return h.a(iVar.h, iVar.i, "");
        } catch (Throwable th) {
            a = th;
            com.alipay.sdk.app.statistic.a.a("biz", com.alipay.sdk.app.statistic.c.s, (Throwable) a);
            iVar = iVar2;
            if (iVar == null) {
                iVar = i.a(i.FAILED.h);
            }
            return h.a(iVar.h, iVar.i, "");
        } finally {
            b();
        }
        if (iVar == null) {
            iVar = i.a(i.FAILED.h);
        }
        return h.a(iVar.h, iVar.i, "");
    }

    private String a(com.alipay.sdk.protocol.b bVar) {
        String[] strArr = bVar.b;
        Bundle bundle = new Bundle();
        bundle.putString("url", strArr[0]);
        Intent intent = new Intent(this.b, H5AuthActivity.class);
        intent.putExtras(bundle);
        this.b.startActivity(intent);
        synchronized (a) {
            try {
                a.wait();
            } catch (InterruptedException e) {
                return h.a();
            }
        }
        String str = h.a;
        if (TextUtils.isEmpty(str)) {
            return h.a();
        }
        return str;
    }

    private static boolean a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(l.a(), 128);
            if (packageInfo != null && packageInfo.versionCode >= 73) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
