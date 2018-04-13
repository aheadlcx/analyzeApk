package com.xiaomi.tinyData;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.push.service.XMPushService.k;
import com.xiaomi.push.service.am;
import com.xiaomi.push.service.be;
import com.xiaomi.push.service.bf;
import com.xiaomi.xmpush.thrift.f;
import com.xiaomi.xmpush.thrift.g;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class a implements k {
    private Context a;
    private boolean b;
    private int c;

    public a(Context context) {
        this.a = context;
    }

    private String a(String str) {
        return "com.xiaomi.xmsf".equals(str) ? "1000271" : this.a.getSharedPreferences("pref_registered_pkg_names", 0).getString(str, null);
    }

    private HashMap<String, ArrayList<f>> a(List<f> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        HashMap<String, ArrayList<f>> hashMap = new HashMap();
        for (f fVar : list) {
            a(fVar);
            ArrayList arrayList = (ArrayList) hashMap.get(fVar.k());
            if (arrayList == null) {
                arrayList = new ArrayList();
                hashMap.put(fVar.k(), arrayList);
            }
            arrayList.add(fVar);
        }
        return hashMap;
    }

    private void a(Context context) {
        this.b = am.a(context).a(g.TinyDataUploadSwitch.a(), true);
        this.c = am.a(context).a(g.TinyDataUploadFrequency.a(), 7200);
        this.c = Math.max(60, this.c);
    }

    private void a(c cVar, ArrayList<f> arrayList, String str) {
        if (arrayList != null && arrayList.size() != 0) {
            b.c("A tinyData is uploaded immediately for " + ((f) arrayList.get(0)).d());
            cVar.a(arrayList, ((f) arrayList.get(0)).p(), str);
        }
    }

    private void a(f fVar) {
        if (fVar.f) {
            fVar.a("push_sdk_channel");
        }
        if (TextUtils.isEmpty(fVar.m())) {
            fVar.f(be.a());
        }
        fVar.b(System.currentTimeMillis());
        if (TextUtils.isEmpty(fVar.p())) {
            fVar.e(this.a.getPackageName());
        }
        if (TextUtils.isEmpty(fVar.k())) {
            fVar.e(fVar.p());
        }
    }

    private boolean a(c cVar) {
        if (!d.c(this.a) || cVar == null || TextUtils.isEmpty(a(this.a.getPackageName()))) {
            return false;
        }
        if (new File(this.a.getFilesDir(), "tiny_data.data").exists()) {
            return true;
        }
        b.a("TinyData(TinyDataCacheUploader) no ready file to get data.");
        return false;
    }

    private boolean b() {
        return Math.abs((System.currentTimeMillis() / 1000) - this.a.getSharedPreferences("mipush_extra", 0).getLong("last_tiny_data_upload_timestamp", -1)) > ((long) this.c);
    }

    private void c() {
        Editor edit = this.a.getSharedPreferences("mipush_extra", 0).edit();
        edit.putLong("last_tiny_data_upload_timestamp", System.currentTimeMillis() / 1000);
        edit.commit();
    }

    public void a() {
        a(this.a);
        if (this.b && b()) {
            c a = b.a(this.a).a();
            if (a(a)) {
                HashMap a2 = a(bf.a(this.a));
                if (a2 != null && a2.size() != 0) {
                    for (Entry entry : a2.entrySet()) {
                        try {
                            a(a, (ArrayList) entry.getValue(), (String) entry.getKey());
                        } catch (Exception e) {
                        }
                    }
                    c();
                }
            }
        }
    }
}
