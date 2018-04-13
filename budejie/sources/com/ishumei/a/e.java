package com.ishumei.a;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import com.ishumei.b.d;
import com.ishumei.c.b;
import com.ishumei.f.c;
import java.io.IOException;

public class e {
    private static e c = null;
    private int a;
    private Context b;
    private b d;

    private e() {
        this.a = 0;
        this.b = null;
        this.d = new b(this, true, 2, false, 10000, true) {
            final /* synthetic */ e a;

            public void run() {
                try {
                    this.a.a(this.a.a);
                } catch (Exception e) {
                    c.d("SeqManager", "setSettingSeq failed: " + e);
                }
                try {
                    this.a.b(this.a.a);
                } catch (Exception e2) {
                    c.d("SeqManager", "setSharedPreferencesSeq failed: " + e2);
                }
            }
        };
        this.b = d.a;
    }

    public static e a() {
        if (c == null) {
            synchronized (e.class) {
                if (c == null) {
                    c = new e();
                }
            }
        }
        return c;
    }

    private void a(String str) {
        if (VERSION.SDK_INT >= 23) {
            throw new IOException("sdk " + VERSION.SDK_INT + " less then 23");
        }
        try {
            if (this.b == null) {
                throw new Exception("mContext == null");
            }
            System.putString(this.b.getContentResolver(), "com.shumei.seq", str);
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }

    private void b(String str) {
        int i = VERSION.SDK_INT < 23 ? 2 : 0;
        try {
            if (this.b == null) {
                throw new Exception("mContext == null");
            }
            Editor edit = this.b.getSharedPreferences("seq", i).edit();
            edit.putString("seq", str);
            if (!edit.commit()) {
                throw new IOException("editor commit failed");
            }
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }

    private String c() {
        String string;
        Object e;
        String str = "";
        try {
            if (this.b == null) {
                throw new Exception("mContext = null");
            }
            try {
                string = System.getString(this.b.getContentResolver(), "com.shumei.seq");
                try {
                    if (com.ishumei.f.d.a(string)) {
                        throw new IOException("from setting empty id");
                    }
                } catch (Exception e2) {
                    e = e2;
                    c.d("SeqManager", "get seq from Settings failed: " + e);
                    return string;
                }
            } catch (Exception e3) {
                Exception exception = e3;
                string = str;
                Exception exception2 = exception;
                c.d("SeqManager", "get seq from Settings failed: " + e);
                return string;
            }
            return string;
        } catch (Throwable e4) {
            throw new Exception(e4);
        }
    }

    private String d() {
        try {
            int i = VERSION.SDK_INT < 23 ? 3 : 0;
            if (this.b == null) {
                throw new Exception("mContext == null");
            }
            String string = this.b.getSharedPreferences("seq", i).getString("seq", null);
            if (!com.ishumei.f.d.a(string)) {
                return string;
            }
            throw new Exception("from shared preference empty id");
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String e() {
        /*
        r3 = this;
        r0 = 0;
        r0 = r3.c();	 Catch:{ Exception -> 0x000c }
        r1 = com.ishumei.f.d.b(r0);	 Catch:{ Exception -> 0x000c }
        if (r1 == 0) goto L_0x0014;
    L_0x000b:
        return r0;
    L_0x000c:
        r1 = move-exception;
        r1 = "SeqManager";
        r2 = "getSeq from setting failed";
        com.ishumei.f.c.a(r1, r2);
    L_0x0014:
        r0 = r3.d();	 Catch:{ Exception -> 0x001f }
        r1 = com.ishumei.f.d.b(r0);	 Catch:{ Exception -> 0x001f }
        if (r1 == 0) goto L_0x000b;
    L_0x001e:
        goto L_0x000b;
    L_0x001f:
        r1 = move-exception;
        r1 = "SeqManager";
        r2 = "getSeq sfrom shared perferences failed";
        com.ishumei.f.c.a(r1, r2);
        goto L_0x000b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ishumei.a.e.e():java.lang.String");
    }

    public synchronized String b() {
        if (this.a == 0) {
            String str = null;
            try {
                str = e();
            } catch (Exception e) {
            }
            if (com.ishumei.f.d.b(str)) {
                try {
                    this.a = Integer.parseInt(str);
                } catch (Exception e2) {
                }
            }
        }
        this.a++;
        this.d.a();
        return this.a;
    }
}
