package com.umeng.commonsdk.statistics.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.commonsdk.statistics.common.MLog;

public class StatTracer implements b {
    private static Context e = null;
    private final int a;
    private int b;
    private long c;
    private long d;
    public int mFailedRequest;
    public long mLastSuccessfulRequestTime;
    public int mSuccessfulRequest;

    private static class a {
        public static final StatTracer a = new StatTracer();
    }

    private StatTracer() {
        this.a = 3600000;
        this.c = 0;
        this.d = 0;
        a();
    }

    public static StatTracer getInstance(Context context) {
        if (e == null) {
            if (context != null) {
                e = context.getApplicationContext();
            } else {
                MLog.e("inside StatTracer. please check context. context must not be null!");
            }
        }
        return a.a;
    }

    private void a() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(e);
        this.mSuccessfulRequest = sharedPreferences.getInt("successful_request", 0);
        this.mFailedRequest = sharedPreferences.getInt("failed_requests ", 0);
        this.b = sharedPreferences.getInt("last_request_spent_ms", 0);
        this.mLastSuccessfulRequestTime = sharedPreferences.getLong("last_request_time", 0);
        this.c = sharedPreferences.getLong("last_req", 0);
    }

    public int getLastRequestLatency() {
        return this.b > 3600000 ? 3600000 : this.b;
    }

    public boolean isFirstRequest() {
        return this.mLastSuccessfulRequestTime == 0;
    }

    public void logSuccessfulRequest(boolean z) {
        this.mSuccessfulRequest++;
        if (z) {
            this.mLastSuccessfulRequestTime = this.c;
        }
    }

    public void logFailedRequest() {
        this.mFailedRequest++;
    }

    public void logRequestStart() {
        this.c = System.currentTimeMillis();
    }

    public void logRequestEnd() {
        this.b = (int) (System.currentTimeMillis() - this.c);
    }

    public void saveSate() {
        PreferenceWrapper.getDefault(e).edit().putInt("successful_request", this.mSuccessfulRequest).putInt("failed_requests ", this.mFailedRequest).putInt("last_request_spent_ms", this.b).putLong("last_req", this.c).putLong("last_request_time", this.mLastSuccessfulRequestTime).commit();
    }

    public long getFirstActivateTime() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(e);
        this.d = PreferenceWrapper.getDefault(e).getLong("first_activate_time", 0);
        if (this.d == 0) {
            this.d = System.currentTimeMillis();
            sharedPreferences.edit().putLong("first_activate_time", this.d).commit();
        }
        return this.d;
    }

    public long getLastReqTime() {
        return this.c;
    }

    public void onRequestStart() {
        logRequestStart();
    }

    public void onRequestEnd() {
        logRequestEnd();
    }

    public void onRequestSucceed(boolean z) {
        logSuccessfulRequest(z);
    }

    public void onRequestFailed() {
        logFailedRequest();
    }
}
