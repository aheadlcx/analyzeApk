package com.google.analytics.tracking.android;

import com.google.android.gms.common.util.VisibleForTesting;

class SendHitRateLimiter implements RateLimiter {
    private long mLastTrackTime;
    private final int mMaxTokens;
    private final long mMillisecondsPerToken;
    private final Object mTokenLock;
    private double mTokens;

    public SendHitRateLimiter(int i, long j) {
        this.mTokenLock = new Object();
        this.mMaxTokens = i;
        this.mTokens = (double) this.mMaxTokens;
        this.mMillisecondsPerToken = j;
    }

    public SendHitRateLimiter() {
        this(60, 2000);
    }

    @VisibleForTesting
    void setLastTrackTime(long j) {
        this.mLastTrackTime = j;
    }

    @VisibleForTesting
    void setTokensAvailable(long j) {
        this.mTokens = (double) j;
    }

    public boolean tokenAvailable() {
        boolean z;
        synchronized (this.mTokenLock) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.mTokens < ((double) this.mMaxTokens)) {
                double d = ((double) (currentTimeMillis - this.mLastTrackTime)) / ((double) this.mMillisecondsPerToken);
                if (d > 0.0d) {
                    this.mTokens = Math.min((double) this.mMaxTokens, d + this.mTokens);
                }
            }
            this.mLastTrackTime = currentTimeMillis;
            if (this.mTokens >= 1.0d) {
                this.mTokens -= 1.0d;
                z = true;
            } else {
                Log.w("Excessive tracking detected.  Tracking call ignored.");
                z = false;
            }
        }
        return z;
    }
}
