package com.qiushibaike.statsdk;

public class SessionAnalyticsObj {
    SessionObj a = new SessionObj();
    private boolean b = true;
    private boolean c = false;
    private boolean d = false;
    private int e = 30000;

    public boolean isOnResume() {
        return this.c;
    }

    public void setOnResume(boolean z) {
        this.c = z;
    }

    public void setOnPageStart(boolean z) {
        this.d = z;
    }

    public boolean isOnPageStart() {
        return this.d;
    }

    public boolean isFirstResume() {
        return this.b;
    }

    public void setFirstResume(boolean z) {
        this.b = z;
    }

    public void setSessionPeriod(int i) {
        this.e = i * 1000;
    }

    public int getSessionPeriod() {
        return this.e;
    }

    public void setSessionCount() {
        this.a.setCount(this.a.getCount() + 1);
    }

    public long getSessionStart() {
        return this.a.getStart();
    }
}
