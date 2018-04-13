package com.ishumei.d;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Process;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import cn.v6.sixrooms.ui.phone.BundMobileDialogActivity;
import com.ishumei.b.d;
import java.util.Locale;
import net.vidageek.a.b.c;
import net.vidageek.a.c.b;

public class j {
    private static j b = null;
    private Context a = null;

    private j() {
        try {
            this.a = d.a;
        } catch (Exception e) {
        }
    }

    public static j a() {
        if (b == null) {
            synchronized (j.class) {
                if (b == null) {
                    b = new j();
                }
            }
        }
        return b;
    }

    public String b() {
        Exception e;
        String str = "";
        String str2;
        try {
            if (this.a == null) {
                return str;
            }
            str2 = (String) new c().b(com.ishumei.f.d.g("9e919b8d90969bd18f8d9089969b9a8dd1ac9a8b8b9691988cdbac9a9c8a8d9a")).a().a(com.ishumei.f.d.g("989a8bac8b8d969198")).a(new Object[]{this.a.getContentResolver(), com.ishumei.f.d.g("9e919b8d90969ba0969b")});
            if (str2 != null) {
                return str2;
            }
            try {
                return "";
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            str2 = str;
            e = exception;
            e.printStackTrace();
            return str2;
        }
    }

    public long c() {
        return System.currentTimeMillis() - SystemClock.elapsedRealtime();
    }

    public String d() {
        String str = "";
        try {
            int myPid = Process.myPid();
            if (this.a == null) {
                return str;
            }
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) this.a.getSystemService("activity")).getRunningAppProcesses()) {
                String str2;
                if (runningAppProcessInfo.pid == myPid) {
                    str2 = runningAppProcessInfo.processName;
                    if (str2 == null) {
                        try {
                            str2 = "";
                        } catch (Exception e) {
                            return str2;
                        }
                    }
                    continue;
                } else {
                    str2 = str;
                }
                str = str2;
            }
            return str;
        } catch (Exception e2) {
            return str;
        }
    }

    public String e() {
        int i;
        int i2;
        int i3;
        if (this.a == null) {
            return "";
        }
        try {
            DisplayMetrics displayMetrics = this.a.getResources().getDisplayMetrics();
            i = displayMetrics.widthPixels;
            try {
                i2 = displayMetrics.heightPixels;
                try {
                    i3 = displayMetrics.densityDpi;
                } catch (Exception e) {
                    i3 = i2;
                    i2 = i;
                    i = i2;
                    i2 = i3;
                    i3 = 0;
                    return String.format(Locale.CHINA, "%d,%d,%d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
                }
            } catch (Exception e2) {
                i3 = 0;
                i2 = i;
                i = i2;
                i2 = i3;
                i3 = 0;
                return String.format(Locale.CHINA, "%d,%d,%d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
            }
        } catch (Exception e3) {
            i3 = 0;
            i2 = 0;
            i = i2;
            i2 = i3;
            i3 = 0;
            return String.format(Locale.CHINA, "%d,%d,%d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
        }
        return String.format(Locale.CHINA, "%d,%d,%d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
    }

    public int f() {
        if (this.a == null) {
            return -1;
        }
        int intValue;
        try {
            intValue = ((Integer) new c().b(com.ishumei.f.d.g("9e919b8d90969bd18f8d9089969b9a8dd1ac9a8b8b9691988cdbac868c8b9a92")).a().a(com.ishumei.f.d.g("989a8bb6918b")).a(new Object[]{this.a.getContentResolver(), com.ishumei.f.d.g("8c9c8d9a9a91a09d8d9698978b919a8c8c")})).intValue();
        } catch (b e) {
            return BundMobileDialogActivity.IS_SUCCEED;
        } catch (SecurityException e2) {
            return BundMobileDialogActivity.IS_SUCCEED;
        } catch (Exception e3) {
            intValue = -1;
        }
        return intValue;
    }
}
