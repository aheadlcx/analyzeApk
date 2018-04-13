package com.google.analytics.tracking.android;

import android.text.TextUtils;
import com.google.analytics.tracking.android.GAUsage.Field;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Tracker {
    private final AppFieldsDefaultProvider mAppFieldsDefaultProvider;
    private final ClientIdDefaultProvider mClientIdDefaultProvider;
    private final TrackerHandler mHandler;
    private final String mName;
    private final Map<String, String> mParams;
    private RateLimiter mRateLimiter;
    private final ScreenResolutionDefaultProvider mScreenResolutionDefaultProvider;

    Tracker(String str, String str2, TrackerHandler trackerHandler) {
        this(str, str2, trackerHandler, ClientIdDefaultProvider.getProvider(), ScreenResolutionDefaultProvider.getProvider(), AppFieldsDefaultProvider.getProvider(), new SendHitRateLimiter());
    }

    @VisibleForTesting
    Tracker(String str, String str2, TrackerHandler trackerHandler, ClientIdDefaultProvider clientIdDefaultProvider, ScreenResolutionDefaultProvider screenResolutionDefaultProvider, AppFieldsDefaultProvider appFieldsDefaultProvider, RateLimiter rateLimiter) {
        this.mParams = new HashMap();
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Tracker name cannot be empty.");
        }
        this.mName = str;
        this.mHandler = trackerHandler;
        this.mParams.put(Fields.TRACKING_ID, str2);
        this.mParams.put(Fields.USE_SECURE, "1");
        this.mClientIdDefaultProvider = clientIdDefaultProvider;
        this.mScreenResolutionDefaultProvider = screenResolutionDefaultProvider;
        this.mAppFieldsDefaultProvider = appFieldsDefaultProvider;
        this.mRateLimiter = rateLimiter;
    }

    public String getName() {
        GAUsage.getInstance().setUsage(Field.GET_TRACKER_NAME);
        return this.mName;
    }

    @VisibleForTesting
    RateLimiter getRateLimiter() {
        return this.mRateLimiter;
    }

    public void send(Map<String, String> map) {
        GAUsage.getInstance().setUsage(Field.SEND);
        Map hashMap = new HashMap();
        hashMap.putAll(this.mParams);
        if (map != null) {
            hashMap.putAll(map);
        }
        if (TextUtils.isEmpty((CharSequence) hashMap.get(Fields.TRACKING_ID))) {
            Log.w(String.format("Missing tracking id (%s) parameter.", new Object[]{Fields.TRACKING_ID}));
        }
        String str = (String) hashMap.get(Fields.HIT_TYPE);
        if (TextUtils.isEmpty(str)) {
            Log.w(String.format("Missing hit type (%s) parameter.", new Object[]{Fields.HIT_TYPE}));
            str = "";
        }
        if (str.equals(HitTypes.TRANSACTION) || str.equals(HitTypes.ITEM) || this.mRateLimiter.tokenAvailable()) {
            this.mHandler.sendHit(hashMap);
        } else {
            Log.w("Too many hits sent too quickly, rate limiting invoked.");
        }
    }

    public String get(String str) {
        GAUsage.getInstance().setUsage(Field.GET);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (this.mParams.containsKey(str)) {
            return (String) this.mParams.get(str);
        }
        if (str.equals(Fields.LANGUAGE)) {
            return Utils.getLanguage(Locale.getDefault());
        }
        if (this.mClientIdDefaultProvider != null && this.mClientIdDefaultProvider.providesField(str)) {
            return this.mClientIdDefaultProvider.getValue(str);
        }
        if (this.mScreenResolutionDefaultProvider != null && this.mScreenResolutionDefaultProvider.providesField(str)) {
            return this.mScreenResolutionDefaultProvider.getValue(str);
        }
        if (this.mAppFieldsDefaultProvider == null || !this.mAppFieldsDefaultProvider.providesField(str)) {
            return null;
        }
        return this.mAppFieldsDefaultProvider.getValue(str);
    }

    public void set(String str, String str2) {
        GAUsage.getInstance().setUsage(Field.SET);
        if (str2 == null) {
            this.mParams.remove(str);
        } else {
            this.mParams.put(str, str2);
        }
    }
}
