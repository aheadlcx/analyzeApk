package com.ishumei.d;

import android.os.Build;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.ishumei.f.e;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;

public class f {
    private static f a = null;
    private String[] b = new String[]{"/dev/socket/qemud", "/dev/qemu_pipe"};
    private String[] c = new String[]{"goldfish"};
    private String[] d = new String[]{"/sys/qemu_trace", "/system/bin/qemu-props"};
    private String[] e = new String[]{"000000000000000"};
    private String[] f = new String[]{"310260000000000"};
    private String[] g = new String[]{"15555215554", "15555215556", "15555215558", "15555215560", "15555215562", "15555215564", "15555215566", "15555215568", "15555215570", "15555215572", "15555215574", "15555215576", "15555215578", "15555215580", "15555215582", "15555215584"};

    private f() {
    }

    private int a(boolean z) {
        return z ? 1 : 0;
    }

    public static f a() {
        if (a == null) {
            synchronized (f.class) {
                if (a == null) {
                    a = new f();
                }
            }
        }
        return a;
    }

    private boolean c() {
        int i = 0;
        while (i < this.b.length) {
            try {
                if (new File(this.b[i]).exists()) {
                    return true;
                }
                i++;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    private boolean d() {
        Throwable th;
        File file = new File("/proc/tty/drivers");
        if (!file.exists() || !file.canRead()) {
            return false;
        }
        byte[] bArr = new byte[((int) file.length())];
        Closeable fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileInputStream.read(bArr);
                String str = new String(bArr);
                for (String indexOf : this.c) {
                    if (str.indexOf(indexOf) != -1) {
                        e.a(fileInputStream);
                        return true;
                    }
                }
                e.a(fileInputStream);
                return false;
            } catch (Exception e) {
                e.a(fileInputStream);
                return false;
            } catch (Throwable th2) {
                th = th2;
                e.a(fileInputStream);
                throw th;
            }
        } catch (Exception e2) {
            fileInputStream = null;
            e.a(fileInputStream);
            return false;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            e.a(fileInputStream);
            throw th;
        }
    }

    private boolean e() {
        int i = 0;
        while (i < this.d.length) {
            try {
                if (new File(this.d[i]).exists()) {
                    return true;
                }
                i++;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    private boolean f() {
        try {
            String b = l.a().b();
            for (String equalsIgnoreCase : this.g) {
                if (equalsIgnoreCase.equalsIgnoreCase(b)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean g() {
        try {
            String c = l.a().c();
            for (String equalsIgnoreCase : this.e) {
                if (equalsIgnoreCase.equalsIgnoreCase(c)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean h() {
        try {
            return l.a().d().toLowerCase().equals(AlibcConstants.PF_ANDROID);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean i() {
        return Build.BOARD == "unknown" || Build.BOOTLOADER == "unknown" || Build.BRAND == "generic" || Build.DEVICE == "generic" || Build.MODEL == "sdk" || Build.PRODUCT == "sdk" || Build.HARDWARE == "goldfish";
    }

    public String b() {
        return String.format(Locale.CHINA, "%d%d%d%d%d%d%d", new Object[]{Integer.valueOf(a(c())), Integer.valueOf(a(d())), Integer.valueOf(a(e())), Integer.valueOf(a(f())), Integer.valueOf(a(g())), Integer.valueOf(a(i())), Integer.valueOf(a(h()))});
    }
}
