package com.qiushibaike.statsdk;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;

public class SessionAnalytics {
    private static HandlerThread a;
    private static Handler b;
    private static SessionAnalytics c;
    private static SessionAnalyticsObj d;

    private SessionAnalytics() {
        a = new HandlerThread(SessionAnalytics.class.getSimpleName(), 10);
        a.start();
        b = new Handler(a.getLooper());
        d = new SessionAnalyticsObj();
    }

    public static SessionAnalytics getInstance() {
        SessionAnalytics sessionAnalytics;
        synchronized (SessionAnalytics.class) {
            if (c == null) {
                c = new SessionAnalytics();
            }
            sessionAnalytics = c;
        }
        return sessionAnalytics;
    }

    public void onPause(Context context, long j) {
    }

    public void onResume(Context context, long j) {
    }
}
