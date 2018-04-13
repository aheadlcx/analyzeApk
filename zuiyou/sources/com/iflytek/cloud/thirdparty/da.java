package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import com.iflytek.cloud.util.ContactManager;
import com.iflytek.cloud.util.ContactManager.ContactListener;

public class da extends ContactManager {
    private static da a = null;
    private static Context b = null;
    private static int c = 4;
    private static dg d = null;
    private static cz e = null;
    private static a f;
    private static ContactListener h = null;
    private HandlerThread g = null;
    private Handler i;
    private long j = 0;

    private class a extends ContentObserver {
        final /* synthetic */ da a;

        public a(da daVar, Handler handler) {
            this.a = daVar;
            super(handler);
        }

        public void onChange(boolean z) {
            cb.a("iFly_ContactManager", "ContactObserver_Contact| onChange");
            if (System.currentTimeMillis() - this.a.j < 5000) {
                cb.a("iFly_ContactManager", "onChange too much");
                return;
            }
            this.a.j = System.currentTimeMillis();
            this.a.d();
        }
    }

    private da() {
        if (VERSION.SDK_INT > c) {
            d = new df(b);
        } else {
            d = new de(b);
        }
        e = new cz(b, d);
        this.g = new HandlerThread("ContactManager_worker");
        this.g.start();
        this.i = new Handler(this.g.getLooper());
        this.g.setPriority(1);
        f = new a(this, this.i);
    }

    public static da a() {
        return a;
    }

    public static da a(Context context, ContactListener contactListener) {
        h = contactListener;
        b = context;
        if (a == null) {
            a = new da();
            b.getContentResolver().registerContentObserver(d.a(), true, f);
        }
        return a;
    }

    public static void c() {
        if (a != null) {
            a.b();
            a = null;
        }
    }

    private void d() {
        try {
            if (h != null && e != null) {
                String a = dc.a(e.a(), '\n');
                String str = b.getFilesDir().getParent() + '/' + "name.txt";
                String a2 = db.a(str);
                if (a == null || a2 == null || !a.equals(a2)) {
                    db.a(str, a, true);
                    h.onContactQueryFinish(a, true);
                    return;
                }
                cb.a("iFly_ContactManager", "contact name is not change.");
                h.onContactQueryFinish(a, false);
            }
        } catch (Throwable e) {
            cb.a(e);
        }
    }

    public void asyncQueryAllContactsName() {
        this.i.post(new Runnable(this) {
            final /* synthetic */ da a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.d();
            }
        });
    }

    public void b() {
        if (f != null) {
            b.getContentResolver().unregisterContentObserver(f);
            if (this.g != null) {
                this.g.quit();
            }
        }
    }

    public String queryAllContactsName() {
        if (e == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : e.a()) {
            stringBuilder.append(str + '\n');
        }
        return stringBuilder.toString();
    }
}
