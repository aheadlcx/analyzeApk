package cz.msebera.android.httpclient.extras;

import android.util.Log;

public class HttpClientAndroidLog {
    private String a;
    private boolean b = false;
    private boolean c = false;
    private boolean d = false;
    private boolean e = false;
    private boolean f = false;

    public HttpClientAndroidLog(Object obj) {
        this.a = obj.toString();
    }

    public void enableDebug(boolean z) {
        this.b = z;
    }

    public boolean isDebugEnabled() {
        return this.b;
    }

    public void debug(Object obj) {
        if (isDebugEnabled()) {
            Log.d(this.a, obj.toString());
        }
    }

    public void debug(Object obj, Throwable th) {
        if (isDebugEnabled()) {
            Log.d(this.a, obj.toString(), th);
        }
    }

    public void enableError(boolean z) {
        this.c = z;
    }

    public boolean isErrorEnabled() {
        return this.c;
    }

    public void error(Object obj) {
        if (isErrorEnabled()) {
            Log.e(this.a, obj.toString());
        }
    }

    public void error(Object obj, Throwable th) {
        if (isErrorEnabled()) {
            Log.e(this.a, obj.toString(), th);
        }
    }

    public void enableWarn(boolean z) {
        this.e = z;
    }

    public boolean isWarnEnabled() {
        return this.e;
    }

    public void warn(Object obj) {
        if (isWarnEnabled()) {
            Log.w(this.a, obj.toString());
        }
    }

    public void warn(Object obj, Throwable th) {
        if (isWarnEnabled()) {
            Log.w(this.a, obj.toString(), th);
        }
    }

    public void enableInfo(boolean z) {
        this.f = z;
    }

    public boolean isInfoEnabled() {
        return this.f;
    }

    public void info(Object obj) {
        if (isInfoEnabled()) {
            Log.i(this.a, obj.toString());
        }
    }

    public void info(Object obj, Throwable th) {
        if (isInfoEnabled()) {
            Log.i(this.a, obj.toString(), th);
        }
    }

    public void enableTrace(boolean z) {
        this.d = z;
    }

    public boolean isTraceEnabled() {
        return this.d;
    }

    public void trace(Object obj) {
        if (isTraceEnabled()) {
            Log.i(this.a, obj.toString());
        }
    }

    public void trace(Object obj, Throwable th) {
        if (isTraceEnabled()) {
            Log.i(this.a, obj.toString(), th);
        }
    }
}
