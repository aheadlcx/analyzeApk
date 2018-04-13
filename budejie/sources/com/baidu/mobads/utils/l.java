package com.baidu.mobads.utils;

import android.util.Log;
import com.baidu.mobads.a.a;
import com.baidu.mobads.interfaces.utils.IXAdLogger;

public class l implements IXAdLogger {
    private static volatile l a = null;

    public static l a() {
        if (a == null) {
            synchronized (l.class) {
                if (a == null) {
                    a = new l();
                }
            }
        }
        return a;
    }

    public boolean isLoggable(String str, int i) {
        return i >= a.b;
    }

    public boolean isLoggable(int i) {
        return isLoggable(IXAdLogger.TAG, i);
    }

    private String a(Object[] objArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object append : objArr) {
            stringBuilder.append(append).append(' ');
        }
        return stringBuilder.toString();
    }

    public int d(Object... objArr) {
        if (isLoggable(3)) {
            return d(a(objArr));
        }
        return -1;
    }

    public int d(String str) {
        return d(IXAdLogger.TAG, str);
    }

    public int d(String str, String str2) {
        int i = -1;
        if (isLoggable(3)) {
            try {
                i = Log.d(str, str2);
            } catch (Exception e) {
            }
        }
        return i;
    }

    public int d(Throwable th) {
        return d("", th);
    }

    public int d(String str, Throwable th) {
        int i = -1;
        if (isLoggable(3)) {
            try {
                i = Log.d(IXAdLogger.TAG, str, th);
            } catch (Exception e) {
            }
        }
        return i;
    }

    public int w(String str) {
        int i = -1;
        if (isLoggable(5)) {
            try {
                i = Log.w(IXAdLogger.TAG, str);
            } catch (Exception e) {
            }
        }
        return i;
    }

    public int w(Object... objArr) {
        if (isLoggable(5)) {
            return w(a(objArr));
        }
        return -1;
    }

    public int w(String str, Throwable th) {
        int i = -1;
        if (isLoggable(5)) {
            try {
                i = Log.w(IXAdLogger.TAG, str, th);
            } catch (Exception e) {
            }
        }
        return i;
    }

    public int w(Throwable th) {
        return w("", th);
    }

    public int e(Object... objArr) {
        if (isLoggable(6)) {
            return e(a(objArr));
        }
        return -1;
    }

    public int e(String str) {
        int i = -1;
        if (isLoggable(6)) {
            try {
                i = Log.e(IXAdLogger.TAG, str);
            } catch (Exception e) {
            }
        }
        return i;
    }

    public int e(Throwable th) {
        return e("", th);
    }

    public int e(String str, Throwable th) {
        int i = -1;
        if (isLoggable(6)) {
            try {
                i = Log.e(IXAdLogger.TAG, str, th);
            } catch (Exception e) {
            }
        }
        return i;
    }

    public int i(String str) {
        return i(IXAdLogger.TAG, str);
    }

    public int i(String str, String str2) {
        int i = -1;
        if (isLoggable(4)) {
            try {
                i = Log.i(str, str2);
            } catch (Exception e) {
            }
        }
        return i;
    }

    public int i(Object... objArr) {
        if (isLoggable(4)) {
            return i(a(objArr));
        }
        return -1;
    }

    public int i(String str, Throwable th) {
        int i = -1;
        if (isLoggable(4)) {
            try {
                i = Log.i(IXAdLogger.TAG, str, th);
            } catch (Exception e) {
            }
        }
        return i;
    }
}
