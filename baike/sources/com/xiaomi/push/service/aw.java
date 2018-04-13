package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class aw {
    private static aw a = new aw();
    private static ExecutorService b = Executors.newSingleThreadExecutor();
    private Context c;
    private Map<String, b> d = new HashMap();
    private String e;
    private final ArrayList<com.xiaomi.push.service.av.b> f = new ArrayList();

    public interface b {
        void a(ArrayList<com.xiaomi.push.service.av.b> arrayList);

        boolean a(com.xiaomi.push.service.av.b bVar);
    }

    public class a implements Runnable {
        b a;
        String b;
        final /* synthetic */ aw c;

        public a(aw awVar) {
            this.c = awVar;
        }

        public void run() {
            this.c.d.put(this.b, this.a);
            this.c.c("Add uploader, provider is " + this.b);
        }
    }

    public class c implements Runnable {
        final /* synthetic */ aw a;
        private Context b;

        public c(aw awVar) {
            this.a = awVar;
        }

        public void run() {
            if (this.a.c != null) {
                com.xiaomi.channel.commonutils.logger.b.d("[TinyDataManager]: please do not init TinyDataManager repeatly.");
                return;
            }
            this.a.c = this.b;
            this.a.a(new com.xiaomi.push.service.av.a(this.b), "SHORT_UPLOADER_FROM_SELF");
            this.a.c("Init");
        }
    }

    private class d implements Runnable {
        String a;
        final /* synthetic */ aw b;

        public d(aw awVar, String str) {
            this.b = awVar;
            this.a = str;
        }

        public void run() {
            this.b.c(this.a);
        }
    }

    private class e implements Runnable {
        String a;
        final /* synthetic */ aw b;

        private e(aw awVar) {
            this.b = awVar;
        }

        public void run() {
            if (TextUtils.isEmpty(this.b.e)) {
                this.b.e = this.a;
                for (int i = 0; i < this.b.f.size(); i++) {
                    com.xiaomi.push.service.av.b bVar = (com.xiaomi.push.service.av.b) this.b.f.get(i);
                    if (!bVar.c.f && bVar.c.a == null) {
                        bVar.c.a = this.a;
                    }
                }
                this.b.c("Set channel to " + this.a);
                return;
            }
            com.xiaomi.channel.commonutils.logger.b.d("[TinyDataManager]:channel is not null, please do not set repeatly.");
        }
    }

    private class f implements Runnable {
        com.xiaomi.push.service.av.b a;
        final /* synthetic */ aw b;

        private f(aw awVar) {
            this.b = awVar;
        }

        public void run() {
            String str;
            Object obj = 1;
            if (this.a.c.f) {
                this.a.c.a = "push_sdk_channel";
            } else {
                this.a.c.a = this.b.e;
            }
            this.a.a = av.a();
            b e = this.b.c();
            String str2 = null;
            Object obj2 = null;
            if (e == null) {
                str2 = "uploader is null";
                obj2 = 1;
            }
            if (obj2 == null && this.b.b()) {
                str2 = "TinyDataManager need init";
                obj2 = 1;
            }
            if (obj2 == null && this.a.c.a == null) {
                str2 = "request channel is null";
                obj2 = 1;
            }
            if (obj2 != null || e.a(this.a)) {
                obj = obj2;
                str = str2;
            } else {
                str = "uploader refuse upload";
            }
            if (obj != null) {
                com.xiaomi.channel.commonutils.logger.b.c(this.a.toString() + " is added to pending list. Pending Reason is " + str);
                this.b.f.add(this.a);
                return;
            }
            com.xiaomi.channel.commonutils.logger.b.c(this.a.toString() + " is uploaded immediately.");
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.a);
            e.a(arrayList);
        }
    }

    private aw() {
    }

    public static aw a() {
        return a;
    }

    private void a(com.xiaomi.push.service.av.b bVar) {
        Runnable fVar = new f();
        fVar.a = bVar;
        b.execute(fVar);
    }

    private b c() {
        b bVar = (b) this.d.get("UPLOADER_FROM_MIPUSHCLIENT");
        if (bVar != null) {
            return bVar;
        }
        bVar = (b) this.d.get("UPLOADER_FROM_XMPUSHSERVICE");
        return bVar == null ? null : bVar;
    }

    private boolean c(String str) {
        com.xiaomi.channel.commonutils.logger.b.c("TinyDataManager is checking and uploading tiny data, reason is " + str + ", the size of pending list is " + this.f.size());
        if (b()) {
            return false;
        }
        b c = c();
        if (c == null) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            com.xiaomi.push.service.av.b bVar = (com.xiaomi.push.service.av.b) it.next();
            if (bVar.c.a != null && c.a(bVar)) {
                arrayList.add(bVar);
            }
        }
        if (arrayList.size() != 0) {
            c.a(arrayList);
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                bVar = (com.xiaomi.push.service.av.b) it2.next();
                com.xiaomi.channel.commonutils.logger.b.c("Pending Data " + bVar.toString() + " uploaded by TinyDataManager, reason is " + str);
                this.f.remove(bVar);
            }
        }
        return true;
    }

    public void a(Context context) {
        if (context == null) {
            com.xiaomi.channel.commonutils.logger.b.d("[TinyDataManager]:context is null, TinyDataManager.init(Context, TinyDataUploader) failed.");
            return;
        }
        Runnable cVar = new c(this);
        cVar.b = context;
        b.execute(cVar);
    }

    public void a(b bVar, String str) {
        if (bVar == null) {
            com.xiaomi.channel.commonutils.logger.b.d("[TinyDataManager]: please do not add null uploader to TinyDataManager.");
        } else if (TextUtils.isEmpty(str)) {
            com.xiaomi.channel.commonutils.logger.b.d("[TinyDataManager]: can not add a provider from unkown resource.");
        } else {
            Runnable aVar = new a(this);
            aVar.b = str;
            aVar.a = bVar;
            b.execute(aVar);
        }
    }

    public void a(String str) {
        if (TextUtils.isEmpty(this.e)) {
            Runnable eVar = new e();
            eVar.a = str;
            b.execute(eVar);
            return;
        }
        com.xiaomi.channel.commonutils.logger.b.d("[TinyDataManager]:channel is not null, please do not set repeatly.");
    }

    public boolean a(int i, String str, String str2, long j, String str3) {
        return a(i, str, str2, j, str3, true);
    }

    public boolean a(int i, String str, String str2, long j, String str3, boolean z) {
        if (av.a(str, str2, j, str3)) {
            return false;
        }
        com.xiaomi.push.service.av.b bVar = new com.xiaomi.push.service.av.b();
        bVar.b = i;
        bVar.c.g = str;
        bVar.c.c = str2;
        bVar.c.d = j;
        bVar.c.b = str3;
        bVar.c.f = z;
        bVar.c.e = System.currentTimeMillis();
        a(bVar);
        return true;
    }

    public boolean a(String str, String str2, long j, String str3) {
        return a(0, str, str2, j, str3);
    }

    public void b(String str) {
        b.execute(new d(this, str));
    }

    public boolean b() {
        return this.c == null;
    }
}
