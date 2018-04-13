package com.xiaomi.push.service;

import com.xiaomi.push.service.aw.b;
import java.util.ArrayList;

class az implements b {
    final /* synthetic */ XMPushService a;

    az(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    private String a(String str) {
        return "com.xiaomi.xmsf".equals(str) ? "1000271" : this.a.getSharedPreferences("pref_registered_pkg_names", 0).getString(str, null);
    }

    public void a(ArrayList<av.b> arrayList) {
        this.a.a(new ba(this, 4, arrayList));
    }

    public boolean a(av.b bVar) {
        return a(this.a.getPackageName()) != null;
    }
}
