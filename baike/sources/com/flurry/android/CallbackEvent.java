package com.flurry.android;

public final class CallbackEvent {
    public static final int ADS_LOADED_FROM_CACHE = 201;
    public static final int ADS_UPDATED = 202;
    public static final int ERROR_MARKET_LAUNCH = 101;
    private int a;
    private long b = System.currentTimeMillis();
    private String c;

    CallbackEvent(int i) {
        this.a = i;
    }

    public final int getType() {
        return this.a;
    }

    public final String getMessage() {
        return this.c;
    }

    public final void setMessage(String str) {
        this.c = str;
    }

    public final long getTimestamp() {
        return this.b;
    }

    public final void setTimestamp(long j) {
        this.b = j;
    }
}
