package com.google.tagmanager;

import android.util.Log;
import com.google.tagmanager.Logger.LogLevel;

class DefaultLogger implements Logger {
    private static final String LOG_TAG = "GoogleTagManager";
    private LogLevel mLogLevel = LogLevel.WARNING;

    DefaultLogger() {
    }

    public void e(String str) {
        if (this.mLogLevel.ordinal() <= LogLevel.ERROR.ordinal()) {
            Log.e(LOG_TAG, str);
        }
    }

    public void e(String str, Throwable th) {
        if (this.mLogLevel.ordinal() <= LogLevel.ERROR.ordinal()) {
            Log.e(LOG_TAG, str, th);
        }
    }

    public void w(String str) {
        if (this.mLogLevel.ordinal() <= LogLevel.WARNING.ordinal()) {
            Log.w(LOG_TAG, str);
        }
    }

    public void w(String str, Throwable th) {
        if (this.mLogLevel.ordinal() <= LogLevel.WARNING.ordinal()) {
            Log.w(LOG_TAG, str, th);
        }
    }

    public void i(String str) {
        if (this.mLogLevel.ordinal() <= LogLevel.INFO.ordinal()) {
            Log.i(LOG_TAG, str);
        }
    }

    public void i(String str, Throwable th) {
        if (this.mLogLevel.ordinal() <= LogLevel.INFO.ordinal()) {
            Log.i(LOG_TAG, str, th);
        }
    }

    public void d(String str) {
        if (this.mLogLevel.ordinal() <= LogLevel.DEBUG.ordinal()) {
            Log.d(LOG_TAG, str);
        }
    }

    public void d(String str, Throwable th) {
        if (this.mLogLevel.ordinal() <= LogLevel.DEBUG.ordinal()) {
            Log.d(LOG_TAG, str, th);
        }
    }

    public void v(String str) {
        if (this.mLogLevel.ordinal() <= LogLevel.VERBOSE.ordinal()) {
            Log.v(LOG_TAG, str);
        }
    }

    public void v(String str, Throwable th) {
        if (this.mLogLevel.ordinal() <= LogLevel.VERBOSE.ordinal()) {
            Log.v(LOG_TAG, str, th);
        }
    }

    public LogLevel getLogLevel() {
        return this.mLogLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.mLogLevel = logLevel;
    }
}
