package com.google.tagmanager;

import android.content.Context;
import com.ali.auth.third.login.LoginConstants;
import com.google.android.gms.common.util.VisibleForTesting;
import java.net.URLEncoder;

class DelayedHitSender implements HitSender {
    private static DelayedHitSender sInstance;
    private static final Object sInstanceLock = new Object();
    private RateLimiter mRateLimiter;
    private HitSendingThread mSendingThread;
    private String mWrapperQueryParameter;
    private String mWrapperUrl;

    private DelayedHitSender(Context context) {
        this(HitSendingThreadImpl.getInstance(context), new SendHitRateLimiter());
    }

    @VisibleForTesting
    DelayedHitSender(HitSendingThread hitSendingThread, RateLimiter rateLimiter) {
        this.mSendingThread = hitSendingThread;
        this.mRateLimiter = rateLimiter;
    }

    public static HitSender getInstance(Context context) {
        HitSender hitSender;
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                sInstance = new DelayedHitSender(context);
            }
            hitSender = sInstance;
        }
        return hitSender;
    }

    public void setUrlWrapModeForTesting(String str, String str2) {
        this.mWrapperUrl = str;
        this.mWrapperQueryParameter = str2;
    }

    public boolean sendHit(String str) {
        if (this.mRateLimiter.tokenAvailable()) {
            if (!(this.mWrapperUrl == null || this.mWrapperQueryParameter == null)) {
                try {
                    str = this.mWrapperUrl + "?" + this.mWrapperQueryParameter + LoginConstants.EQUAL + URLEncoder.encode(str, "UTF-8");
                    Log.v("Sending wrapped url hit: " + str);
                } catch (Throwable e) {
                    Log.w("Error wrapping URL for testing.", e);
                    return false;
                }
            }
            this.mSendingThread.sendHit(str);
            return true;
        }
        Log.w("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
        return false;
    }
}
