package com.loc;

import android.content.Context;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobstat.Config;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import qsbk.app.core.model.CustomButton;
import qsbk.app.core.model.User;

public final class x {
    public static final String a = "/a/";
    static final String b = CustomButton.POSITION_BOTTOM;
    static final String c = "c";
    static final String d = "d";
    public static final String e = IXAdRequestInfo.GPS;
    public static final String f = "h";
    public static final String g = Config.SESSTION_END_TIME;
    public static final String h = User.FEMALE;

    public static Class<? extends ao> a(int i) {
        switch (i) {
            case 0:
                return aj.class;
            case 1:
                return al.class;
            case 2:
                return ai.class;
            default:
                return null;
        }
    }

    public static String a(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getFilesDir().getAbsolutePath());
        stringBuilder.append(a);
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    static void a(Context context) {
        try {
            ad d = d(2);
            if (d != null) {
                d.b(context);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    static void a(Context context, s sVar, String str, String str2) {
        try {
            if (sVar.e()) {
                ExecutorService b = z.b();
                if (b != null && !b.isShutdown()) {
                    b.submit(new ef(context, str2, sVar, str));
                }
            }
        } catch (RejectedExecutionException e) {
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    static void a(Context context, Throwable th, int i, String str, String str2) {
        try {
            ExecutorService b = z.b();
            if (b != null && !b.isShutdown()) {
                b.submit(new eg(context, i, th, str, str2));
            }
        } catch (RejectedExecutionException e) {
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    public static ao b(int i) {
        switch (i) {
            case 0:
                return new aj();
            case 1:
                return new al();
            case 2:
                return new ai();
            default:
                return null;
        }
    }

    static void b(Context context) {
        try {
            ExecutorService b = z.b();
            if (b != null && !b.isShutdown()) {
                b.submit(new eh(context));
            }
        } catch (Throwable th) {
            w.a(th, "Log", "processLog");
        }
    }

    public static String c(int i) {
        switch (i) {
            case 0:
                return c;
            case 1:
                return b;
            case 2:
                return d;
            default:
                return "";
        }
    }

    static ad d(int i) {
        switch (i) {
            case 0:
                return new ab(i);
            case 1:
                return new ac(i);
            case 2:
                return new aa(i);
            default:
                return null;
        }
    }
}
