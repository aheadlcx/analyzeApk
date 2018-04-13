package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.proguard.g;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import java.io.File;

public class i extends a {
    private Context a;
    private String b = null;
    private String c = null;

    public i(Context context) {
        super("oldumid");
        this.a = context;
    }

    public String f() {
        return this.b;
    }

    public boolean g() {
        return h();
    }

    public boolean h() {
        this.c = UMEnvelopeBuild.imprintProperty(this.a, g.f, null);
        if (!TextUtils.isEmpty(this.c)) {
            this.c = DataHelper.encryptBySHA1(this.c);
            Object readFile = HelperUtils.readFile(new File("/sdcard/Android/data/.um/sysid.dat"));
            Object readFile2 = HelperUtils.readFile(new File("/sdcard/Android/obj/.um/sysid.dat"));
            Object readFile3 = HelperUtils.readFile(new File("/data/local/tmp/.um/sysid.dat"));
            if (TextUtils.isEmpty(readFile)) {
                l();
            } else if (!this.c.equals(readFile)) {
                this.b = readFile;
                return true;
            }
            if (TextUtils.isEmpty(readFile2)) {
                k();
            } else if (!this.c.equals(readFile2)) {
                this.b = readFile2;
                return true;
            }
            if (TextUtils.isEmpty(readFile3)) {
                j();
            } else if (!this.c.equals(readFile3)) {
                this.b = readFile3;
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
            HelperUtils.writeFile(new File("/data/local/tmp/.um/sysid.dat"), this.c);
        } catch (Throwable th) {
        }
    }

    private void k() {
        try {
            b("/sdcard/Android/obj/.um");
            HelperUtils.writeFile(new File("/sdcard/Android/obj/.um/sysid.dat"), this.c);
        } catch (Throwable th) {
        }
    }

    private void l() {
        try {
            b("/sdcard/Android/data/.um");
            HelperUtils.writeFile(new File("/sdcard/Android/data/.um/sysid.dat"), this.c);
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
