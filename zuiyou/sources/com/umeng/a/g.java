package com.umeng.a;

import android.util.Log;
import com.umeng.analytics.a;
import java.util.Formatter;

public class g {
    public static boolean a = false;
    private static String b = a.c;
    private static int c = 2000;

    public static void a(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                d(b, new Formatter().format(str, objArr).toString(), null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            d(str, str2, null);
        } catch (Throwable th) {
            a(th);
        }
    }

    public static void a(Throwable th) {
        d(b, null, th);
    }

    public static void a(String str, Throwable th) {
        d(b, str, th);
    }

    public static void a(String str) {
        a(b, str, null);
    }

    public static void b(String str) {
        b(b, str, null);
    }

    public static void c(String str) {
        c(b, str, null);
    }

    public static void d(String str) {
        d(b, str, null);
    }

    public static void a(String str, String str2, Throwable th) {
        if (a) {
            a(2, str, str2, th);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        if (a) {
            a(3, str, str2, th);
        }
    }

    public static void c(String str, String str2, Throwable th) {
        if (a) {
            a(4, str, str2, th);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (a) {
            a(5, str, str2, th);
        }
    }

    private static void a(int i, String str, String str2, Throwable th) {
        int length = str2.length();
        int i2 = 0;
        int i3 = c;
        int i4 = 0;
        while (i2 < 100) {
            if (length > i3) {
                switch (i) {
                    case 1:
                        Log.v(str, str2.substring(i4, i3));
                        break;
                    case 2:
                        Log.d(str, str2.substring(i4, i3));
                        break;
                    case 3:
                        Log.i(str, str2.substring(i4, i3));
                        break;
                    case 4:
                        Log.w(str, str2.substring(i4, i3));
                        break;
                    case 5:
                        Log.e(str, str2.substring(i4, i3));
                        break;
                }
                int i5 = i3 + c;
                if (th != null) {
                    for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                        switch (i) {
                            case 1:
                                Log.v(str, "\t\tat\t" + stackTraceElement.toString());
                                break;
                            case 2:
                                Log.d(str, "\t\tat\t" + stackTraceElement.toString());
                                break;
                            case 3:
                                Log.i(str, "\t\tat\t" + stackTraceElement.toString());
                                break;
                            case 4:
                                Log.w(str, "\t\tat\t" + stackTraceElement.toString());
                                break;
                            case 5:
                                Log.e(str, "\t\tat\t" + stackTraceElement.toString());
                                break;
                            default:
                                break;
                        }
                    }
                }
                i2++;
                i4 = i3;
                i3 = i5;
            } else {
                switch (i) {
                    case 1:
                        Log.v(str, str2.substring(i4, length));
                        return;
                    case 2:
                        Log.d(str, str2.substring(i4, length));
                        return;
                    case 3:
                        Log.i(str, str2.substring(i4, length));
                        return;
                    case 4:
                        Log.w(str, str2.substring(i4, length));
                        return;
                    case 5:
                        Log.e(str, str2.substring(i4, length));
                        return;
                    default:
                        return;
                }
            }
        }
    }
}
