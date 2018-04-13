package com.umeng.commonsdk.statistics.common;

import android.text.TextUtils;
import android.util.Log;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Formatter;
import java.util.Locale;

public class MLog {
    public static boolean DEBUG = false;
    private static String a = AnalyticsConstants.LOG_TAG;
    private static int b = 2000;

    private MLog() {
    }

    public static void i(Locale locale, String str, Object... objArr) {
        try {
            i(a, new Formatter(locale).format(str, objArr).toString(), null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void d(Locale locale, String str, Object... objArr) {
        try {
            d(a, new Formatter(locale).format(str, objArr).toString(), null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void e(Locale locale, String str, Object... objArr) {
        try {
            e(a, new Formatter(locale).format(str, objArr).toString(), null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void v(Locale locale, String str, Object... objArr) {
        try {
            v(a, new Formatter(locale).format(str, objArr).toString(), null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void w(Locale locale, String str, Object... objArr) {
        try {
            w(a, new Formatter(locale).format(str, objArr).toString(), null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void i(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                i(a, new Formatter().format(str, objArr).toString(), null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            i(str, str2, null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void d(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                d(a, new Formatter().format(str, objArr).toString(), null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            d(str, str2, null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void e(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                e(a, new Formatter().format(str, objArr).toString(), null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            e(str, str2, null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void v(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                v(a, new Formatter().format(str, objArr).toString(), null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            v(str, str2, null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void w(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                w(a, new Formatter().format(str, objArr).toString(), null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            w(str, str2, null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void i(Throwable th) {
        i(a, null, th);
    }

    public static void v(Throwable th) {
        v(a, null, th);
    }

    public static void w(Throwable th) {
        w(a, null, th);
    }

    public static void d(Throwable th) {
        d(a, null, th);
    }

    public static void e(Throwable th) {
        e(a, null, th);
    }

    public static void i(String str, Throwable th) {
        i(a, str, th);
    }

    public static void v(String str, Throwable th) {
        v(a, str, th);
    }

    public static void w(String str, Throwable th) {
        w(a, str, th);
    }

    public static void d(String str, Throwable th) {
        d(a, str, th);
    }

    public static void e(String str, Throwable th) {
        e(a, str, th);
    }

    public static void v(String str) {
        v(a, str, null);
    }

    public static void d(String str) {
        d(a, str, null);
    }

    public static void i(String str) {
        i(a, str, null);
    }

    public static void w(String str) {
        w(a, str, null);
    }

    public static void e(String str) {
        e(a, str, null);
    }

    public static void v(String str, String str2, Throwable th) {
        if (DEBUG) {
            a(1, str, str2, th);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (DEBUG) {
            a(2, str, str2, th);
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (DEBUG) {
            a(3, str, str2, th);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (DEBUG) {
            a(4, str, str2, th);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (DEBUG) {
            a(5, str, str2, th);
        }
    }

    private static void a(int i, String str, String str2, Throwable th) {
        int i2 = 0;
        if (!TextUtils.isEmpty(str2)) {
            int length = str2.length();
            int i3 = b;
            int i4 = 0;
            while (i2 < 100) {
                if (length <= i3) {
                    switch (i) {
                        case 1:
                            Log.v(str, str2.substring(i4, length));
                            break;
                        case 2:
                            Log.d(str, str2.substring(i4, length));
                            break;
                        case 3:
                            Log.i(str, str2.substring(i4, length));
                            break;
                        case 4:
                            Log.w(str, str2.substring(i4, length));
                            break;
                        case 5:
                            Log.e(str, str2.substring(i4, length));
                            break;
                    }
                }
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
                    default:
                        break;
                }
                i2++;
                i4 = i3;
                i3 = b + i3;
            }
        }
        if (th != null) {
            String stackTrace = getStackTrace(th);
            if (!TextUtils.isEmpty(stackTrace)) {
                switch (i) {
                    case 1:
                        Log.v(str, stackTrace);
                        return;
                    case 2:
                        Log.d(str, stackTrace);
                        return;
                    case 3:
                        Log.i(str, stackTrace);
                        return;
                    case 4:
                        Log.w(str, stackTrace);
                        return;
                    case 5:
                        Log.e(str, stackTrace);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public static String getStackTrace(Throwable th) {
        Throwable th2;
        StringWriter stringWriter = null;
        String str = "";
        StringWriter stringWriter2;
        PrintWriter printWriter;
        try {
            stringWriter2 = new StringWriter();
            try {
                printWriter = new PrintWriter(stringWriter2);
                try {
                    th.printStackTrace(printWriter);
                    printWriter.flush();
                    stringWriter2.flush();
                    str = stringWriter2.toString();
                    if (stringWriter2 != null) {
                        try {
                            stringWriter2.close();
                        } catch (Throwable th3) {
                        }
                    }
                    if (printWriter != null) {
                        printWriter.close();
                    }
                } catch (Throwable th4) {
                    th2 = th4;
                    if (stringWriter2 != null) {
                        try {
                            stringWriter2.close();
                        } catch (Throwable th5) {
                        }
                    }
                    if (printWriter != null) {
                        printWriter.close();
                    }
                    throw th2;
                }
            } catch (Throwable th6) {
                th2 = th6;
                printWriter = null;
                if (stringWriter2 != null) {
                    stringWriter2.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
                throw th2;
            }
        } catch (Throwable th7) {
            th2 = th7;
            printWriter = null;
            stringWriter2 = null;
            if (stringWriter2 != null) {
                stringWriter2.close();
            }
            if (printWriter != null) {
                printWriter.close();
            }
            throw th2;
        }
        return str;
    }
}
