package com.xiaomi.push.service.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.android.c;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.mipush.sdk.MiPushClient;
import dalvik.system.DexClassLoader;
import java.io.File;
import junit.framework.Assert;
import org.eclipse.paho.client.mqttv3.MqttTopic;

class a {
    private Context a;
    private String b;
    private String c;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private String g;
    private SharedPreferences h;

    public a(Context context, String str) {
        boolean z = false;
        this.a = context.getApplicationContext();
        this.b = str;
        this.h = this.a.getSharedPreferences(MiPushClient.PREF_EXTRA, 0);
        if (!TextUtils.isEmpty(this.b)) {
            z = true;
        }
        Assert.assertEquals(true, z);
    }

    public static String a(Context context, String str) {
        return b(context, str) + MqttTopic.TOPIC_LEVEL_SEPARATOR + str + ".apk";
    }

    private String a(String str) {
        try {
            return this.a.getPackageManager().getPackageArchiveInfo(str, 16512).applicationInfo.metaData.getString("Launcher");
        } catch (Exception e) {
            return null;
        }
    }

    private void a(int i) {
        this.h.edit().putInt(this.b + "_asset_app_version", i).commit();
    }

    private void a(long j) {
        this.h.edit().putLong(this.b + "_asset_modified", j).commit();
    }

    private static String b(Context context, String str) {
        return context.getDir(str, 0).getAbsolutePath();
    }

    private void b(int i) {
        this.h.edit().putInt(this.b + "_asset_version", i).commit();
    }

    private void b(long j) {
        this.h.edit().putLong(this.b + "_local_modified", j).commit();
    }

    private void b(String str) {
        this.h.edit().putString(this.b + "_asset_launcher", str).commit();
    }

    private void c(int i) {
        this.h.edit().putInt(this.b + "_local_version", i).commit();
    }

    private void c(String str) {
        this.h.edit().putString(this.b + "_local_launcher", str).commit();
    }

    private boolean g() {
        File file = new File(l());
        return file.exists() && (s() != file.lastModified() || q() == 0);
    }

    private boolean h() {
        File file = new File(d());
        return file.exists() && (t() == file.lastModified() || r() == 0);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean i() {
        /*
        r7 = this;
        r0 = 0;
        r2 = new java.io.File;
        r1 = r7.l();
        r2.<init>(r1);
        r1 = r2.exists();
        if (r1 == 0) goto L_0x0046;
    L_0x0010:
        r1 = 0;
        r3 = r7.a;	 Catch:{ FileNotFoundException -> 0x0047, Exception -> 0x006d }
        r3 = r3.getAssets();	 Catch:{ FileNotFoundException -> 0x0047, Exception -> 0x006d }
        r4 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x0047, Exception -> 0x006d }
        r4.<init>();	 Catch:{ FileNotFoundException -> 0x0047, Exception -> 0x006d }
        r5 = r7.b;	 Catch:{ FileNotFoundException -> 0x0047, Exception -> 0x006d }
        r4 = r4.append(r5);	 Catch:{ FileNotFoundException -> 0x0047, Exception -> 0x006d }
        r5 = ".apk";
        r4 = r4.append(r5);	 Catch:{ FileNotFoundException -> 0x0047, Exception -> 0x006d }
        r4 = r4.toString();	 Catch:{ FileNotFoundException -> 0x0047, Exception -> 0x006d }
        r1 = r3.openFd(r4);	 Catch:{ FileNotFoundException -> 0x0047, Exception -> 0x006d }
        r4 = r1.getLength();	 Catch:{ FileNotFoundException -> 0x0047, Exception -> 0x0089 }
        r2 = r2.length();	 Catch:{ FileNotFoundException -> 0x0047, Exception -> 0x0089 }
        r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r2 == 0) goto L_0x003d;
    L_0x003c:
        r0 = 1;
    L_0x003d:
        if (r1 == 0) goto L_0x0046;
    L_0x003f:
        r1 = r1.getParcelFileDescriptor();
    L_0x0043:
        com.xiaomi.channel.commonutils.file.a.a(r1);
    L_0x0046:
        return r0;
    L_0x0047:
        r2 = move-exception;
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x007b }
        r2.<init>();	 Catch:{ all -> 0x007b }
        r3 = "no ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x007b }
        r3 = r7.b;	 Catch:{ all -> 0x007b }
        r2 = r2.append(r3);	 Catch:{ all -> 0x007b }
        r3 = ".apk file in assets of app";
        r2 = r2.append(r3);	 Catch:{ all -> 0x007b }
        r2 = r2.toString();	 Catch:{ all -> 0x007b }
        com.xiaomi.channel.commonutils.logger.b.d(r2);	 Catch:{ all -> 0x007b }
        if (r1 == 0) goto L_0x0046;
    L_0x0068:
        r1 = r1.getParcelFileDescriptor();
        goto L_0x0043;
    L_0x006d:
        r2 = move-exception;
        r6 = r2;
        r2 = r1;
        r1 = r6;
    L_0x0071:
        r1.printStackTrace();	 Catch:{ all -> 0x0086 }
        if (r2 == 0) goto L_0x0046;
    L_0x0076:
        r1 = r2.getParcelFileDescriptor();
        goto L_0x0043;
    L_0x007b:
        r0 = move-exception;
    L_0x007c:
        if (r1 == 0) goto L_0x0085;
    L_0x007e:
        r1 = r1.getParcelFileDescriptor();
        com.xiaomi.channel.commonutils.file.a.a(r1);
    L_0x0085:
        throw r0;
    L_0x0086:
        r0 = move-exception;
        r1 = r2;
        goto L_0x007c;
    L_0x0089:
        r2 = move-exception;
        r6 = r2;
        r2 = r1;
        r1 = r6;
        goto L_0x0071;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.module.a.i():boolean");
    }

    private void j() {
        String str;
        try {
            b.b("copyAssetFile start");
            String[] list = this.a.getAssets().list("");
            if (list != null) {
                for (String str2 : list) {
                    if (!TextUtils.isEmpty(str2) && str2.startsWith(this.b)) {
                        c.a(this.a, str2, l());
                        str = "copyAssetFile end";
                        break;
                    }
                }
            }
            str = "copyAssetFile end";
        } catch (Exception e) {
            e.printStackTrace();
            str = "copyAssetFile end";
        } catch (Throwable th) {
            b.b("copyAssetFile end");
        }
        b.b(str);
    }

    private String k() {
        return b(this.a, this.b);
    }

    private String l() {
        return k() + MqttTopic.TOPIC_LEVEL_SEPARATOR + this.b + "_asset.apk";
    }

    private String m() {
        return k() + "/lib/";
    }

    private String n() {
        return k() + "/asset_lib/";
    }

    private boolean o() {
        try {
            return p() != com.xiaomi.channel.commonutils.android.b.b(this.a, this.a.getPackageName());
        } catch (Exception e) {
            return true;
        }
    }

    private int p() {
        int i = 0;
        try {
            i = this.h.getInt(this.b + "_asset_app_version", 0);
        } catch (Exception e) {
        }
        return i;
    }

    private int q() {
        int i = 0;
        try {
            i = this.h.getInt(this.b + "_asset_version", 0);
        } catch (Exception e) {
        }
        return i;
    }

    private int r() {
        int i = 0;
        try {
            i = this.h.getInt(this.b + "_local_version", 0);
        } catch (Exception e) {
        }
        return i;
    }

    private long s() {
        long j = 0;
        try {
            j = this.h.getLong(this.b + "_asset_modified", 0);
        } catch (Exception e) {
        }
        return j;
    }

    private long t() {
        long j = 0;
        try {
            j = this.h.getLong(this.b + "_local_modified", 0);
        } catch (Exception e) {
        }
        return j;
    }

    private String u() {
        String str = null;
        try {
            str = this.h.getString(this.b + "_asset_launcher", null);
        } catch (Exception e) {
        }
        return str;
    }

    private String v() {
        String str = null;
        try {
            str = this.h.getString(this.b + "_local_launcher", null);
        } catch (Exception e) {
        }
        return str;
    }

    public int a() {
        return this.d;
    }

    public String b() {
        return this.g;
    }

    public synchronized DexClassLoader c() {
        DexClassLoader dexClassLoader;
        Object obj = 1;
        synchronized (this) {
            try {
                Object obj2;
                String d;
                File file;
                b.b("load apk " + this.b);
                String l = l();
                File file2 = new File(l);
                boolean i = i();
                b.b("assert app size changed : " + i);
                if (!file2.exists() || i || o()) {
                    b.b("re-copy asset file");
                    j();
                }
                if (file2.exists()) {
                    b.b("check modify.");
                    if (g() || i || o()) {
                        b.b("modified.");
                        this.e = com.xiaomi.channel.commonutils.android.b.c(this.a, l);
                        b(this.e);
                        a(com.xiaomi.channel.commonutils.android.b.b(this.a, this.a.getPackageName()));
                        a(file2.lastModified());
                        obj2 = 1;
                        d = d();
                        file = new File(d);
                        if (file.exists()) {
                            if (h()) {
                                this.f = r();
                            } else {
                                this.f = com.xiaomi.channel.commonutils.android.b.c(this.a, d);
                                c(this.f);
                                b(file.lastModified());
                                b.b("asset version " + this.e);
                                b.b("local version " + this.f);
                                if (this.e < this.f) {
                                    if (this.e > this.d) {
                                        this.d = this.e;
                                        this.c = l();
                                        if (obj2 == null) {
                                            this.g = a(this.c);
                                            b(this.g);
                                            com.xiaomi.channel.commonutils.android.a.a(this.a, this.c, n());
                                        } else {
                                            this.g = u();
                                        }
                                        dexClassLoader = new DexClassLoader(this.c, this.a.getDir("dex", 0).getAbsolutePath(), n(), ClassLoader.getSystemClassLoader());
                                        b.b("load apk : " + this.c + ", version : " + this.d);
                                        b.b("load apk done.");
                                    }
                                } else if (this.f > this.d) {
                                    this.d = this.f;
                                    this.c = d();
                                    if (obj == null) {
                                        this.g = a(this.c);
                                        c(this.g);
                                        com.xiaomi.channel.commonutils.android.a.a(this.a, this.c, m());
                                    } else {
                                        this.g = v();
                                    }
                                    dexClassLoader = new DexClassLoader(this.c, this.a.getDir("dex", 0).getAbsolutePath(), m(), ClassLoader.getSystemClassLoader());
                                    b.b("load apk : " + this.c + ", version : " + this.d);
                                    b.b("load apk done.");
                                }
                                b.b("load apk : " + this.c + ", version : " + this.d);
                                b.b("load apk done.");
                                dexClassLoader = null;
                            }
                        }
                        obj = null;
                        b.b("asset version " + this.e);
                        b.b("local version " + this.f);
                        if (this.e < this.f) {
                            if (this.f > this.d) {
                                this.d = this.f;
                                this.c = d();
                                if (obj == null) {
                                    this.g = v();
                                } else {
                                    this.g = a(this.c);
                                    c(this.g);
                                    com.xiaomi.channel.commonutils.android.a.a(this.a, this.c, m());
                                }
                                dexClassLoader = new DexClassLoader(this.c, this.a.getDir("dex", 0).getAbsolutePath(), m(), ClassLoader.getSystemClassLoader());
                                b.b("load apk : " + this.c + ", version : " + this.d);
                                b.b("load apk done.");
                            }
                        } else if (this.e > this.d) {
                            this.d = this.e;
                            this.c = l();
                            if (obj2 == null) {
                                this.g = u();
                            } else {
                                this.g = a(this.c);
                                b(this.g);
                                com.xiaomi.channel.commonutils.android.a.a(this.a, this.c, n());
                            }
                            dexClassLoader = new DexClassLoader(this.c, this.a.getDir("dex", 0).getAbsolutePath(), n(), ClassLoader.getSystemClassLoader());
                            b.b("load apk : " + this.c + ", version : " + this.d);
                            b.b("load apk done.");
                        }
                        b.b("load apk : " + this.c + ", version : " + this.d);
                        b.b("load apk done.");
                        dexClassLoader = null;
                    } else {
                        b.b("not modified.");
                        this.e = q();
                    }
                }
                obj2 = null;
                d = d();
                file = new File(d);
                if (file.exists()) {
                    if (h()) {
                        this.f = r();
                    } else {
                        this.f = com.xiaomi.channel.commonutils.android.b.c(this.a, d);
                        c(this.f);
                        b(file.lastModified());
                        b.b("asset version " + this.e);
                        b.b("local version " + this.f);
                        if (this.e < this.f) {
                            if (this.e > this.d) {
                                this.d = this.e;
                                this.c = l();
                                if (obj2 == null) {
                                    this.g = a(this.c);
                                    b(this.g);
                                    com.xiaomi.channel.commonutils.android.a.a(this.a, this.c, n());
                                } else {
                                    this.g = u();
                                }
                                dexClassLoader = new DexClassLoader(this.c, this.a.getDir("dex", 0).getAbsolutePath(), n(), ClassLoader.getSystemClassLoader());
                                b.b("load apk : " + this.c + ", version : " + this.d);
                                b.b("load apk done.");
                            }
                        } else if (this.f > this.d) {
                            this.d = this.f;
                            this.c = d();
                            if (obj == null) {
                                this.g = a(this.c);
                                c(this.g);
                                com.xiaomi.channel.commonutils.android.a.a(this.a, this.c, m());
                            } else {
                                this.g = v();
                            }
                            dexClassLoader = new DexClassLoader(this.c, this.a.getDir("dex", 0).getAbsolutePath(), m(), ClassLoader.getSystemClassLoader());
                            b.b("load apk : " + this.c + ", version : " + this.d);
                            b.b("load apk done.");
                        }
                        b.b("load apk : " + this.c + ", version : " + this.d);
                        b.b("load apk done.");
                        dexClassLoader = null;
                    }
                }
                obj = null;
                b.b("asset version " + this.e);
                b.b("local version " + this.f);
                if (this.e < this.f) {
                    if (this.f > this.d) {
                        this.d = this.f;
                        this.c = d();
                        if (obj == null) {
                            this.g = v();
                        } else {
                            this.g = a(this.c);
                            c(this.g);
                            com.xiaomi.channel.commonutils.android.a.a(this.a, this.c, m());
                        }
                        dexClassLoader = new DexClassLoader(this.c, this.a.getDir("dex", 0).getAbsolutePath(), m(), ClassLoader.getSystemClassLoader());
                        b.b("load apk : " + this.c + ", version : " + this.d);
                        b.b("load apk done.");
                    }
                } else if (this.e > this.d) {
                    this.d = this.e;
                    this.c = l();
                    if (obj2 == null) {
                        this.g = u();
                    } else {
                        this.g = a(this.c);
                        b(this.g);
                        com.xiaomi.channel.commonutils.android.a.a(this.a, this.c, n());
                    }
                    dexClassLoader = new DexClassLoader(this.c, this.a.getDir("dex", 0).getAbsolutePath(), n(), ClassLoader.getSystemClassLoader());
                    b.b("load apk : " + this.c + ", version : " + this.d);
                    b.b("load apk done.");
                }
                b.b("load apk : " + this.c + ", version : " + this.d);
                b.b("load apk done.");
            } catch (Throwable e) {
                b.a(e);
                b.b("load apk : " + this.c + ", version : " + this.d);
                b.b("load apk done.");
            } catch (Throwable th) {
                b.b("load apk : " + this.c + ", version : " + this.d);
                b.b("load apk done.");
            }
            dexClassLoader = null;
        }
        return dexClassLoader;
    }

    public String d() {
        return a(this.a, this.b);
    }

    public String e() {
        return this.b;
    }

    public String f() {
        return this.c;
    }
}
