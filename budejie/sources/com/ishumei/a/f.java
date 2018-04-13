package com.ishumei.a;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.System;
import android.text.TextUtils;
import cn.v6.sixrooms.utils.SharedPreferencesUtils;
import com.ishumei.dfp.SMSDK;
import com.ishumei.f.e;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class f {
    private static f g = null;
    private Map<String, Integer> a;
    private Map<String, Integer> b;
    private String c;
    private String d;
    private List<d> e;
    private Context f;
    private com.ishumei.c.b h;

    private static abstract class d implements Comparable {
        public int b;
        public String c;
        public int d;

        private d() {
            this.b = 0;
            this.c = null;
            this.d = 0;
        }

        public abstract String a();

        public abstract void a(String str);

        public abstract boolean b();

        public int compareTo(Object obj) {
            return obj instanceof d ? ((d) obj).b - this.b : 0;
        }
    }

    private class a extends d {
        final /* synthetic */ f a;

        public a(f fVar) {
            this.a = fVar;
            super();
            this.b = 4;
            this.c = "sdcard";
            this.d = 4;
        }

        private String d() {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }

        private String e() {
            int i = 0;
            String d = d();
            File file = new File(d, "shumei.txt");
            com.ishumei.f.c.a("SmidManager", "exter store:" + file.getAbsolutePath());
            try {
                return e.a(file);
            } catch (Exception e) {
                file = new File(d);
                if (!file.canRead()) {
                    return "";
                }
                File[] listFiles = file.listFiles();
                int length = listFiles.length;
                int i2 = i;
                while (i < length) {
                    File file2 = listFiles[i];
                    int i3 = i2 + 1;
                    if (i2 < 30 && file2.isDirectory() && file2.canWrite()) {
                        File file3 = new File(file2, ".thumbcache_idx0");
                        if (file3.canRead()) {
                            try {
                                return e.a(file3);
                            } catch (Exception e2) {
                            }
                        } else {
                            continue;
                        }
                    }
                    i++;
                    i2 = i3;
                }
                return "";
            }
        }

        public String a() {
            return e();
        }

        public void a(String str) {
            b(str);
        }

        public void b(String str) {
            int i = 0;
            String d = d();
            File file = new File(d);
            if (file.canWrite() && file.canRead()) {
                for (File file2 : file.listFiles()) {
                    if (i < 10) {
                        i++;
                    } else if (i < 15 && file2.isDirectory() && file2.canWrite()) {
                        try {
                            e.a(new File(file2, ".thumbcache_idx0"), str);
                        } catch (Exception e) {
                        }
                        i++;
                    }
                }
                try {
                    e.a(new File(d, "shumei.txt"), str);
                    return;
                } catch (Exception e2) {
                    com.ishumei.b.c.a(new Exception("smid: " + str + "exception: " + e2));
                    throw new Exception("sv failed");
                }
            }
            throw new Exception("sv failed");
        }

        public boolean b() {
            return c();
        }

        public boolean c() {
            String d = d();
            File file = new File(d);
            if (!file.canWrite()) {
                return false;
            }
            int i = 0;
            for (File file2 : file.listFiles()) {
                if (i < 30 && file2.isDirectory() && file2.canWrite()) {
                    try {
                        new File(file2, ".thumbcache_idx0").delete();
                    } catch (Exception e) {
                    }
                    i++;
                }
            }
            file = new File(d, "shumei.txt");
            try {
                file.delete();
                return true;
            } catch (Exception e2) {
                com.ishumei.b.c.a(new Exception("delete " + file.getAbsolutePath() + " exception: " + e2));
                return false;
            }
        }
    }

    private class b extends d {
        final /* synthetic */ f a;

        public b(f fVar) {
            this.a = fVar;
            super();
            this.b = 3;
            this.c = com.alipay.sdk.sys.a.j;
            this.d = 1;
        }

        private String d() {
            if (this.a.f == null) {
                com.ishumei.f.c.d("SmidManager", "mContext == null:\n" + Thread.getAllStackTraces());
            }
            String str = "";
            try {
                str = System.getString(this.a.f.getContentResolver(), "com.shumei.deviceid");
                com.ishumei.f.d.a(str);
                return str;
            } catch (Exception e) {
                return str;
            }
        }

        public String a() {
            return d();
        }

        public void a(String str) {
            b(str);
        }

        public void b(String str) {
            if (VERSION.SDK_INT < 23) {
                if (this.a.f == null) {
                    throw new Exception("sv failed");
                }
                try {
                    System.putString(this.a.f.getContentResolver(), "com.shumei.deviceid", str);
                } catch (Exception e) {
                    throw new Exception("sv failed");
                }
            }
        }

        public boolean b() {
            return c();
        }

        public boolean c() {
            if (VERSION.SDK_INT >= 23) {
                return true;
            }
            if (this.a.f == null) {
                return false;
            }
            try {
                System.putString(this.a.f.getContentResolver(), "com.shumei.deviceid", null);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    private class c extends d {
        final /* synthetic */ f a;

        public c(f fVar) {
            this.a = fVar;
            super();
            this.b = 2;
            this.c = "sharedpref";
            this.d = 2;
        }

        private String d() {
            int i = VERSION.SDK_INT < 23 ? 3 : 0;
            this.a.f;
            String string = this.a.f.getSharedPreferences("com.shumei", i).getString("deviceid", "");
            com.ishumei.f.d.a(string);
            return string;
        }

        public String a() {
            return d();
        }

        public void a(String str) {
            b(str);
        }

        public void b(String str) {
            int i = VERSION.SDK_INT < 23 ? 2 : 0;
            if (this.a.f == null) {
                throw new Exception("sv failed");
            }
            Editor edit = this.a.f.getSharedPreferences("com.shumei", i).edit();
            edit.putString("deviceid", str);
            if (!edit.commit()) {
                throw new Exception("sv failed");
            }
        }

        public boolean b() {
            return c();
        }

        public boolean c() {
            int i = VERSION.SDK_INT < 23 ? 2 : 0;
            if (this.a.f == null) {
                return false;
            }
            Editor edit = this.a.f.getSharedPreferences("com.shumei", i).edit();
            edit.remove("deviceid");
            return edit.commit();
        }
    }

    private f() {
        this.a = new HashMap();
        this.b = new HashMap();
        this.c = null;
        this.d = null;
        this.e = new LinkedList();
        this.f = null;
        this.h = new com.ishumei.c.b(this, true, 2, true, 0, true) {
            int a = 0;
            final /* synthetic */ f b;

            public void run() {
                synchronized (this.b) {
                    for (d dVar : this.b.e) {
                        try {
                            dVar.a(this.b.d);
                            this.b.a.put(dVar.c, Integer.valueOf(0));
                        } catch (Exception e) {
                            this.b.a.put(dVar.c, Integer.valueOf(1));
                        }
                    }
                    this.a++;
                }
                if (this.a < 3) {
                    this.d = true;
                    this.e = false;
                    this.f = 15000;
                    this.g = false;
                    a();
                    return;
                }
                this.d = false;
                this.e = true;
                this.f = 0;
                this.g = true;
                this.a = 0;
            }
        };
        this.f = com.ishumei.b.d.a;
        try {
            a(new b(this));
            a(new c(this));
            a(new a(this));
            g();
        } catch (Exception e) {
            com.ishumei.f.c.d("SmidManager", "SmidManager constructor failed: " + e);
        }
    }

    public static f a() {
        if (g == null) {
            synchronized (f.class) {
                if (g == null) {
                    g = new f();
                }
            }
        }
        return g;
    }

    private synchronized void a(d dVar) {
        this.e.add(dVar);
    }

    private void g() {
        Collections.sort(this.e, new Comparator<d>(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public int a(d dVar, d dVar2) {
                return dVar2.b - dVar.b;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((d) obj, (d) obj2);
            }
        });
    }

    public synchronized void a(String str) {
        if (str != null) {
            if (!str.isEmpty()) {
                if (!TextUtils.equals(this.d, str)) {
                    this.d = str;
                    this.h.a();
                }
            }
        }
    }

    public Map<String, Object> b() {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put("smid", c());
        hashMap.put("smidFrom", this.c);
        hashMap.put("smidReads", this.b);
        hashMap.put("smidWrites", this.a);
        return hashMap;
    }

    public synchronized String c() {
        String str;
        if (TextUtils.isEmpty(this.d)) {
            for (d dVar : this.e) {
                try {
                    String a = dVar.a();
                    if (TextUtils.isEmpty(a)) {
                        this.b.put(dVar.c, Integer.valueOf(1));
                    } else if (a.length() == 62) {
                        this.d = a;
                        this.c = SharedPreferencesUtils.SP_KEY_READ;
                        this.b.put(dVar.c, Integer.valueOf(0));
                        str = a;
                        break;
                    }
                } catch (Exception e) {
                    com.ishumei.f.c.d("SmidManager", "getSmid failed: " + e);
                    this.b.put(dVar.c, Integer.valueOf(1));
                }
            }
            str = "";
        } else {
            str = this.d;
        }
        return str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.Map<java.lang.String, java.util.List<java.lang.Integer>> d() {
        /*
        r8 = this;
        monitor-enter(r8);
        r4 = new java.util.HashMap;	 Catch:{ all -> 0x0049 }
        r4.<init>();	 Catch:{ all -> 0x0049 }
        r0 = r8.e;	 Catch:{ all -> 0x0049 }
        r3 = r0.iterator();	 Catch:{ all -> 0x0049 }
    L_0x000c:
        r0 = r3.hasNext();	 Catch:{ all -> 0x0049 }
        if (r0 == 0) goto L_0x00f4;
    L_0x0012:
        r0 = r3.next();	 Catch:{ all -> 0x0049 }
        r0 = (com.ishumei.a.f.d) r0;	 Catch:{ all -> 0x0049 }
        r2 = 0;
        r2 = r0.a();	 Catch:{ Exception -> 0x0099, all -> 0x00d7 }
        r1 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x0099 }
        if (r1 == 0) goto L_0x004c;
    L_0x0023:
        r1 = r8.b;	 Catch:{ Exception -> 0x0099 }
        r5 = r0.c;	 Catch:{ Exception -> 0x0099 }
        r6 = 1;
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ Exception -> 0x0099 }
        r1.put(r5, r6);	 Catch:{ Exception -> 0x0099 }
        r1 = r4.get(r2);	 Catch:{ all -> 0x0049 }
        r1 = (java.util.List) r1;	 Catch:{ all -> 0x0049 }
        if (r1 != 0) goto L_0x003c;
    L_0x0037:
        r1 = new java.util.ArrayList;	 Catch:{ all -> 0x0049 }
        r1.<init>();	 Catch:{ all -> 0x0049 }
    L_0x003c:
        r0 = r0.d;	 Catch:{ all -> 0x0049 }
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ all -> 0x0049 }
        r1.add(r0);	 Catch:{ all -> 0x0049 }
        r4.put(r2, r1);	 Catch:{ all -> 0x0049 }
        goto L_0x000c;
    L_0x0049:
        r0 = move-exception;
        monitor-exit(r8);
        throw r0;
    L_0x004c:
        r1 = r2.length();	 Catch:{ Exception -> 0x0099 }
        r5 = 62;
        if (r1 == r5) goto L_0x006e;
    L_0x0054:
        r1 = r4.get(r2);	 Catch:{ all -> 0x0049 }
        r1 = (java.util.List) r1;	 Catch:{ all -> 0x0049 }
        if (r1 != 0) goto L_0x0061;
    L_0x005c:
        r1 = new java.util.ArrayList;	 Catch:{ all -> 0x0049 }
        r1.<init>();	 Catch:{ all -> 0x0049 }
    L_0x0061:
        r0 = r0.d;	 Catch:{ all -> 0x0049 }
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ all -> 0x0049 }
        r1.add(r0);	 Catch:{ all -> 0x0049 }
        r4.put(r2, r1);	 Catch:{ all -> 0x0049 }
        goto L_0x000c;
    L_0x006e:
        r1 = r8.b;	 Catch:{ Exception -> 0x0099 }
        r5 = r0.c;	 Catch:{ Exception -> 0x0099 }
        r6 = 0;
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ Exception -> 0x0099 }
        r1.put(r5, r6);	 Catch:{ Exception -> 0x0099 }
        r1 = "read";
        r8.c = r1;	 Catch:{ Exception -> 0x0099 }
        r1 = r4.get(r2);	 Catch:{ all -> 0x0049 }
        r1 = (java.util.List) r1;	 Catch:{ all -> 0x0049 }
        if (r1 != 0) goto L_0x008b;
    L_0x0086:
        r1 = new java.util.ArrayList;	 Catch:{ all -> 0x0049 }
        r1.<init>();	 Catch:{ all -> 0x0049 }
    L_0x008b:
        r0 = r0.d;	 Catch:{ all -> 0x0049 }
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ all -> 0x0049 }
        r1.add(r0);	 Catch:{ all -> 0x0049 }
        r4.put(r2, r1);	 Catch:{ all -> 0x0049 }
        goto L_0x000c;
    L_0x0099:
        r1 = move-exception;
        r2 = "";
        r5 = r8.b;	 Catch:{ all -> 0x00f6 }
        r6 = r0.c;	 Catch:{ all -> 0x00f6 }
        r7 = 1;
        r7 = java.lang.Integer.valueOf(r7);	 Catch:{ all -> 0x00f6 }
        r5.put(r6, r7);	 Catch:{ all -> 0x00f6 }
        r5 = "SmidManager";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00f6 }
        r7 = "getAllSmid failed: ";
        r6.<init>(r7);	 Catch:{ all -> 0x00f6 }
        r1 = r6.append(r1);	 Catch:{ all -> 0x00f6 }
        r1 = r1.toString();	 Catch:{ all -> 0x00f6 }
        com.ishumei.f.c.d(r5, r1);	 Catch:{ all -> 0x00f6 }
        r1 = r4.get(r2);	 Catch:{ all -> 0x0049 }
        r1 = (java.util.List) r1;	 Catch:{ all -> 0x0049 }
        if (r1 != 0) goto L_0x00c9;
    L_0x00c4:
        r1 = new java.util.ArrayList;	 Catch:{ all -> 0x0049 }
        r1.<init>();	 Catch:{ all -> 0x0049 }
    L_0x00c9:
        r0 = r0.d;	 Catch:{ all -> 0x0049 }
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ all -> 0x0049 }
        r1.add(r0);	 Catch:{ all -> 0x0049 }
        r4.put(r2, r1);	 Catch:{ all -> 0x0049 }
        goto L_0x000c;
    L_0x00d7:
        r1 = move-exception;
        r3 = r2;
        r2 = r1;
    L_0x00da:
        r1 = r4.get(r3);	 Catch:{ all -> 0x0049 }
        r1 = (java.util.List) r1;	 Catch:{ all -> 0x0049 }
        if (r1 != 0) goto L_0x00e7;
    L_0x00e2:
        r1 = new java.util.ArrayList;	 Catch:{ all -> 0x0049 }
        r1.<init>();	 Catch:{ all -> 0x0049 }
    L_0x00e7:
        r0 = r0.d;	 Catch:{ all -> 0x0049 }
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ all -> 0x0049 }
        r1.add(r0);	 Catch:{ all -> 0x0049 }
        r4.put(r3, r1);	 Catch:{ all -> 0x0049 }
        throw r2;	 Catch:{ all -> 0x0049 }
    L_0x00f4:
        monitor-exit(r8);
        return r4;
    L_0x00f6:
        r1 = move-exception;
        r3 = r2;
        r2 = r1;
        goto L_0x00da;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ishumei.a.f.d():java.util.Map<java.lang.String, java.util.List<java.lang.Integer>>");
    }

    public boolean e() {
        boolean z;
        synchronized (this) {
            z = true;
            for (d dVar : this.e) {
                try {
                    if (dVar.b()) {
                        this.a.put(dVar.c, Integer.valueOf(1));
                        this.b.put(dVar.c, Integer.valueOf(1));
                    } else {
                        z = false;
                    }
                } catch (Exception e) {
                    com.ishumei.f.c.d("SmidManager", "delete smid failed: " + e);
                }
            }
        }
        this.d = "";
        return z;
    }

    public String f() {
        if (this.f == null) {
            throw new Exception("mContext == null:\n" + Thread.getAllStackTraces());
        }
        String z2 = SMSDK.z2(this.f);
        if (!com.ishumei.f.d.a(z2) && com.ishumei.f.d.a(this.d)) {
            this.c = "gen";
        }
        return z2;
    }
}
