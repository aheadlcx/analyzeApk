package com.umeng.onlineconfig;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.umeng.onlineconfig.proguard.c;
import com.umeng.onlineconfig.proguard.g;

public class OnlineConfigAgent$b extends c implements Runnable {
    Context a;
    final /* synthetic */ OnlineConfigAgent b;

    public OnlineConfigAgent$b(OnlineConfigAgent onlineConfigAgent, Context context) {
        this.b = onlineConfigAgent;
        this.a = context.getApplicationContext();
    }

    public void run() {
        try {
            if (c()) {
                b();
            }
        } catch (Exception e) {
            OnlineConfigAgent.a(this.b, null);
            OnlineConfigLog.d(a.a, "request online config error", e);
        }
    }

    public boolean a() {
        return true;
    }

    private void b() {
        c cVar = (c) a(new OnlineConfigAgent$a(this.b, this.a), c.class);
        if (cVar == null) {
            OnlineConfigAgent.a(this.b, null);
            return;
        }
        if (OnlineConfigLog.LOG) {
            OnlineConfigLog.i(a.a, "response : " + cVar.b);
        }
        if (cVar.b) {
            OnlineConfigAgent.a(this.b, this.a, cVar);
            OnlineConfigAgent.a(this.b, cVar.a);
            return;
        }
        OnlineConfigAgent.a(this.b, null);
    }

    private boolean c() {
        boolean z = true;
        if (TextUtils.isEmpty(TextUtils.isEmpty(OnlineConfigAgent.a(this.b)) ? g.a(this.a) : OnlineConfigAgent.a(this.b))) {
            OnlineConfigLog.e(a.a, "Appkey is missing ,Please check AndroidManifest.xml or set appKey");
            return false;
        }
        boolean z2;
        OnlineConfigAgent$d onlineConfigAgent$d;
        SharedPreferences a;
        Editor edit;
        boolean z3 = OnlineConfigLog.LOG && g.g(this.a);
        if (!z3) {
            SharedPreferences a2 = d.a(this.a).a();
            long j = a2.getLong("last_test_t", 0);
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - j > a2.getLong("oc_req_i", 600000)) {
                a2.edit().putLong("last_test_t", currentTimeMillis).commit();
                z2 = true;
                OnlineConfigLog.e(a.a, "isDebug=" + z3 + ",isReqTimeout=" + z2);
                if (z3 && !z2) {
                    return false;
                }
                onlineConfigAgent$d = (OnlineConfigAgent$d) a(new OnlineConfigAgent$c(this.b, this.a), OnlineConfigAgent$d.class);
                if (onlineConfigAgent$d != null) {
                    return false;
                }
                a = d.a(this.a).a();
                if (onlineConfigAgent$d.a <= a.getLong("oc_mdf_t", 0)) {
                    z = false;
                }
                edit = a.edit();
                if (onlineConfigAgent$d.b >= 0) {
                    edit.putLong("oc_req_i", onlineConfigAgent$d.b);
                }
                if (onlineConfigAgent$d.a >= 0) {
                    edit.putLong("oc_mdf_told", a.getLong("oc_mdf_t", 0));
                    edit.putLong("oc_mdf_t", onlineConfigAgent$d.a);
                }
                edit.commit();
                return z;
            }
        }
        z2 = false;
        OnlineConfigLog.e(a.a, "isDebug=" + z3 + ",isReqTimeout=" + z2);
        if (z3) {
        }
        onlineConfigAgent$d = (OnlineConfigAgent$d) a(new OnlineConfigAgent$c(this.b, this.a), OnlineConfigAgent$d.class);
        if (onlineConfigAgent$d != null) {
            return false;
        }
        a = d.a(this.a).a();
        if (onlineConfigAgent$d.a <= a.getLong("oc_mdf_t", 0)) {
            z = false;
        }
        edit = a.edit();
        if (onlineConfigAgent$d.b >= 0) {
            edit.putLong("oc_req_i", onlineConfigAgent$d.b);
        }
        if (onlineConfigAgent$d.a >= 0) {
            edit.putLong("oc_mdf_told", a.getLong("oc_mdf_t", 0));
            edit.putLong("oc_mdf_t", onlineConfigAgent$d.a);
        }
        edit.commit();
        return z;
    }
}
