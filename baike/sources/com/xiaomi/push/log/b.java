package com.xiaomi.push.log;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.common.util.UriUtil;
import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.push.service.at;
import com.xiaomi.smack.util.e;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.live.ui.LiveBaseActivity;

public class b {
    private static volatile b c = null;
    private final ConcurrentLinkedQueue<b> a = new ConcurrentLinkedQueue();
    private Context b;

    class b extends com.xiaomi.channel.commonutils.misc.h.b {
        long b = System.currentTimeMillis();
        final /* synthetic */ b c;

        b(b bVar) {
            this.c = bVar;
        }

        public void b() {
        }

        public boolean d() {
            return true;
        }

        final boolean e() {
            return System.currentTimeMillis() - this.b > LiveBaseActivity.INNER;
        }
    }

    class a extends b {
        final /* synthetic */ b a;

        a(b bVar) {
            this.a = bVar;
            super(bVar);
        }

        public void b() {
            this.a.b();
        }
    }

    class c extends b {
        String a;
        String d;
        File e;
        int f;
        boolean g;
        boolean h;
        final /* synthetic */ b i;

        c(b bVar, String str, String str2, File file, boolean z) {
            this.i = bVar;
            super(bVar);
            this.a = str;
            this.d = str2;
            this.e = file;
            this.h = z;
        }

        private boolean f() {
            int i;
            SharedPreferences sharedPreferences = this.i.b.getSharedPreferences("log.timestamp", 0);
            String string = sharedPreferences.getString("log.requst", "");
            long currentTimeMillis = System.currentTimeMillis();
            try {
                JSONObject jSONObject = new JSONObject(string);
                currentTimeMillis = jSONObject.getLong("time");
                i = jSONObject.getInt("times");
            } catch (JSONException e) {
                i = 0;
            }
            if (System.currentTimeMillis() - currentTimeMillis >= 86400000) {
                currentTimeMillis = System.currentTimeMillis();
                i = 0;
            } else if (i > 10) {
                return false;
            }
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("time", currentTimeMillis);
                jSONObject2.put("times", i + 1);
                sharedPreferences.edit().putString("log.requst", jSONObject2.toString()).commit();
            } catch (JSONException e2) {
                com.xiaomi.channel.commonutils.logger.b.c("JSONException on put " + e2.getMessage());
            }
            return true;
        }

        public void b() {
            try {
                if (f()) {
                    Map hashMap = new HashMap();
                    hashMap.put("uid", at.e());
                    hashMap.put("token", this.d);
                    hashMap.put("net", d.k(this.i.b));
                    d.a(this.a, hashMap, this.e, UriUtil.LOCAL_FILE_SCHEME);
                }
                this.g = true;
            } catch (IOException e) {
            }
        }

        public void c() {
            if (!this.g) {
                this.f++;
                if (this.f < 3) {
                    this.i.a.add(this);
                }
            }
            if (this.g || this.f >= 3) {
                this.e.delete();
            }
            this.i.a((long) ((1 << this.f) * 1000));
        }

        public boolean d() {
            return d.f(this.i.b) || (this.h && d.d(this.i.b));
        }
    }

    private b(Context context) {
        this.b = context;
        this.a.add(new a(this));
        b(0);
    }

    public static b a(Context context) {
        if (c == null) {
            synchronized (b.class) {
                if (c == null) {
                    c = new b(context);
                }
            }
        }
        c.b = context;
        return c;
    }

    private void a(long j) {
        b bVar = (b) this.a.peek();
        if (bVar != null && bVar.d()) {
            b(j);
        }
    }

    private void b() {
        if (!com.xiaomi.channel.commonutils.file.c.b() && !com.xiaomi.channel.commonutils.file.c.a()) {
            try {
                File file = new File(this.b.getExternalFilesDir(null) + "/.logcache");
                if (file.exists() && file.isDirectory()) {
                    for (File delete : file.listFiles()) {
                        delete.delete();
                    }
                }
            } catch (NullPointerException e) {
            }
        }
    }

    private void b(long j) {
        if (!this.a.isEmpty()) {
            e.a(new d(this), j);
        }
    }

    private void c() {
        while (!this.a.isEmpty()) {
            if (((b) this.a.peek()).e() || this.a.size() > 6) {
                com.xiaomi.channel.commonutils.logger.b.c("remove Expired task");
                this.a.remove();
            } else {
                return;
            }
        }
    }

    public void a() {
        c();
        a(0);
    }

    public void a(String str, String str2, Date date, Date date2, int i, boolean z) {
        this.a.add(new c(this, i, date, date2, str, str2, z));
        b(0);
    }
}
