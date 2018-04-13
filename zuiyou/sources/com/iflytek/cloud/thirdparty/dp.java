package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.Field;

public class dp {
    private String a;
    private String b;
    private String c;
    private String d;
    private TelephonyManager e;

    public dp(Context context) {
        this.e = (TelephonyManager) context.getSystemService("phone");
        a();
        this.d = a(context);
        String str = "";
        if (VERSION.SDK_INT > 7) {
            str = Build.HARDWARE;
        }
        this.c = "Android";
        this.b = a("MANUFACTURER") + "|" + a("MODEL") + "|" + a("PRODUCT") + "|ANDROID" + VERSION.RELEASE + "|" + this.d + "|" + str;
    }

    private String a(Context context) {
        int width;
        int height;
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay.getOrientation() == 0) {
            width = defaultDisplay.getWidth();
            height = defaultDisplay.getHeight();
        } else {
            width = defaultDisplay.getHeight();
            height = defaultDisplay.getWidth();
        }
        return width + "*" + height;
    }

    private String a(String str) {
        try {
            Field field = Build.class.getField(str);
            Build build = new Build();
            if (field != null) {
                return field.get(build).toString();
            }
        } catch (Throwable e) {
            dr.a("", "", e);
        }
        return "";
    }

    public String a() {
        try {
            if (this.a == null || this.a.length() <= 0) {
                this.a = this.e.getDeviceId();
                dr.a("", "getIMEI:" + this.a);
            }
        } catch (Throwable e) {
            dr.a("", "", e);
        }
        return this.a;
    }

    public String b() {
        return this.b;
    }
}
