package com.umeng.analytics.c;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.a.b;
import com.umeng.a.e;
import java.io.File;

public class k extends a {
    private static final String a = "oldumid";
    private Context b;
    private String c = null;
    private String d = null;

    public k(Context context) {
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
        this.d = h.a(this.b).b().g(null);
        if (!TextUtils.isEmpty(this.d)) {
            this.d = b.c(this.d);
            Object a = e.a(new File("/sdcard/Android/data/.um/sysid.dat"));
            Object a2 = e.a(new File("/sdcard/Android/obj/.um/sysid.dat"));
            Object a3 = e.a(new File("/data/local/tmp/.um/sysid.dat"));
            if (TextUtils.isEmpty(a)) {
                l();
            } else if (!this.d.equals(a)) {
                this.c = a;
                return true;
            }
            if (TextUtils.isEmpty(a2)) {
                k();
            } else if (!this.d.equals(a2)) {
                this.c = a2;
                return true;
            }
            if (TextUtils.isEmpty(a3)) {
                j();
            } else if (!this.d.equals(a3)) {
                this.c = a3;
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
            e.a(new File("/data/local/tmp/.um/sysid.dat"), this.d);
        } catch (Throwable th) {
        }
    }

    private void k() {
        try {
            b("/sdcard/Android/obj/.um");
            e.a(new File("/sdcard/Android/obj/.um/sysid.dat"), this.d);
        } catch (Throwable th) {
        }
    }

    private void l() {
        try {
            b("/sdcard/Android/data/.um");
            e.a(new File("/sdcard/Android/data/.um/sysid.dat"), this.d);
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
