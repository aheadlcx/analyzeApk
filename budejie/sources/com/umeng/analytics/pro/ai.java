package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;

public class ai extends y {
    private static final String a = "oldumid";
    private Context b;
    private String c = null;
    private String d = null;

    public ai(Context context) {
        super(a);
        this.b = context;
    }

    public String f() {
        return this.c;
    }

    public boolean g() {
        return h();
    }

    public boolean h() {
        this.d = af.a(this.b).b().g(null);
        if (!TextUtils.isEmpty(this.d)) {
            this.d = bt.c(this.d);
            Object b = bw.b(new File("/sdcard/Android/data/.um/sysid.dat"));
            Object b2 = bw.b(new File("/sdcard/Android/obj/.um/sysid.dat"));
            Object b3 = bw.b(new File("/data/local/tmp/.um/sysid.dat"));
            if (TextUtils.isEmpty(b)) {
                l();
            } else if (!this.d.equals(b)) {
                this.c = b;
                return true;
            }
            if (TextUtils.isEmpty(b2)) {
                k();
            } else if (!this.d.equals(b2)) {
                this.c = b2;
                return true;
            }
            if (TextUtils.isEmpty(b3)) {
                j();
            } else if (!this.d.equals(b3)) {
                this.c = b3;
                return true;
            }
        }
        return false;
    }

    public void i() {
        try {
            l();
            k();
            j();
        } catch (Exception e) {
        }
    }

    private void j() {
        try {
            b("/data/local/tmp/.um");
            bw.a(new File("/data/local/tmp/.um/sysid.dat"), this.d);
        } catch (Throwable th) {
        }
    }

    private void k() {
        try {
            b("/sdcard/Android/obj/.um");
            bw.a(new File("/sdcard/Android/obj/.um/sysid.dat"), this.d);
        } catch (Throwable th) {
        }
    }

    private void l() {
        try {
            b("/sdcard/Android/data/.um");
            bw.a(new File("/sdcard/Android/data/.um/sysid.dat"), this.d);
        } catch (Throwable th) {
        }
    }

    private void b(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
