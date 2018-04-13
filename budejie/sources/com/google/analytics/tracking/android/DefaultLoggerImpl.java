package com.google.analytics.tracking.android;

import android.util.Log;
import com.google.analytics.tracking.android.Logger.LogLevel;
import com.google.android.gms.common.util.VisibleForTesting;

class DefaultLoggerImpl implements Logger {
    @VisibleForTesting
    static final String LOG_TAG = "GAV3";
    private LogLevel mLogLevel = LogLevel.INFO;

    DefaultLoggerImpl() {
    }

    public void verbose(String str) {
        if (this.mLogLevel.ordinal() <= LogLevel.VERBOSE.ordinal()) {
            Log.v(LOG_TAG, formatMessage(str));
        }
    }

    public void info(String str) {
        if (this.mLogLevel.ordinal() <= LogLevel.INFO.ordinal()) {
            Log.i(LOG_TAG, formatMessage(str));
        }
    }

    public void warn(String str) {
        if (this.mLogLevel.ordinal() <= LogLevel.WARNING.ordinal()) {
            Log.w(LOG_TAG, formatMessage(str));
        }
    }

    public void error(String str) {
        if (this.mLogLevel.ordinal() <= LogLevel.ERROR.ordinal()) {
            Log.e(LOG_TAG, formatMessage(str));
        }
    }

    public void error(Exception exception) {
        if (this.mLogLevel.ordinal() <= LogLevel.ERROR.ordinal()) {
            Log.e(LOG_TAG, null, exception);
        }
    }

    public void setLogLevel(LogLevel logLevel) {
        this.mLogLevel = logLevel;
    }

    public LogLevel getLogLevel() {
        return this.mLogLevel;
    }

    private String formatMessage(String str) {
        return Thread.currentThread().toString() + ": " + str;
    }
}
