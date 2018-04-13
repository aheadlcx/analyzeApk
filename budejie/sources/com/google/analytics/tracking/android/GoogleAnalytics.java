package com.google.analytics.tracking.android;

import android.content.Context;
import android.text.TextUtils;
import com.google.analytics.tracking.android.GAUsage.Field;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class GoogleAnalytics extends TrackerHandler {
    private static GoogleAnalytics sInstance;
    private volatile Boolean mAppOptOut;
    private Context mContext;
    private Tracker mDefaultTracker;
    private boolean mDryRun;
    private Logger mLogger;
    private AnalyticsThread mThread;
    private final Map<String, Tracker> mTrackers;

    @VisibleForTesting
    protected GoogleAnalytics(Context context) {
        this(context, GAThread.getInstance(context));
    }

    private GoogleAnalytics(Context context, AnalyticsThread analyticsThread) {
        this.mAppOptOut = Boolean.valueOf(false);
        this.mTrackers = new HashMap();
        if (context == null) {
            throw new IllegalArgumentException("context cannot be null");
        }
        this.mContext = context.getApplicationContext();
        this.mThread = analyticsThread;
        AppFieldsDefaultProvider.initializeProvider(this.mContext);
        ScreenResolutionDefaultProvider.initializeProvider(this.mContext);
        ClientIdDefaultProvider.initializeProvider(this.mContext);
        this.mLogger = new DefaultLoggerImpl();
    }

    public static GoogleAnalytics getInstance(Context context) {
        GoogleAnalytics googleAnalytics;
        synchronized (GoogleAnalytics.class) {
            if (sInstance == null) {
                sInstance = new GoogleAnalytics(context);
            }
            googleAnalytics = sInstance;
        }
        return googleAnalytics;
    }

    static GoogleAnalytics getInstance() {
        GoogleAnalytics googleAnalytics;
        synchronized (GoogleAnalytics.class) {
            googleAnalytics = sInstance;
        }
        return googleAnalytics;
    }

    @VisibleForTesting
    static GoogleAnalytics getNewInstance(Context context, AnalyticsThread analyticsThread) {
        GoogleAnalytics googleAnalytics;
        synchronized (GoogleAnalytics.class) {
            if (sInstance != null) {
                sInstance.close();
            }
            sInstance = new GoogleAnalytics(context, analyticsThread);
            googleAnalytics = sInstance;
        }
        return googleAnalytics;
    }

    @VisibleForTesting
    static void clearInstance() {
        synchronized (GoogleAnalytics.class) {
            sInstance = null;
            clearDefaultProviders();
        }
    }

    @VisibleForTesting
    static void clearDefaultProviders() {
        AppFieldsDefaultProvider.dropInstance();
        ScreenResolutionDefaultProvider.dropInstance();
        ClientIdDefaultProvider.dropInstance();
    }

    public void setDryRun(boolean z) {
        GAUsage.getInstance().setUsage(Field.SET_DRY_RUN);
        this.mDryRun = z;
    }

    public boolean isDryRunEnabled() {
        GAUsage.getInstance().setUsage(Field.GET_DRY_RUN);
        return this.mDryRun;
    }

    public Tracker getTracker(String str, String str2) {
        Tracker tracker;
        synchronized (this) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("Tracker name cannot be empty");
            }
            tracker = (Tracker) this.mTrackers.get(str);
            if (tracker == null) {
                tracker = new Tracker(str, str2, this);
                this.mTrackers.put(str, tracker);
                if (this.mDefaultTracker == null) {
                    this.mDefaultTracker = tracker;
                }
            }
            if (!TextUtils.isEmpty(str2)) {
                tracker.set(Fields.TRACKING_ID, str2);
            }
            GAUsage.getInstance().setUsage(Field.GET_TRACKER);
        }
        return tracker;
    }

    public Tracker getTracker(String str) {
        return getTracker(str, str);
    }

    public Tracker getDefaultTracker() {
        Tracker tracker;
        synchronized (this) {
            GAUsage.getInstance().setUsage(Field.GET_DEFAULT_TRACKER);
            tracker = this.mDefaultTracker;
        }
        return tracker;
    }

    public void setDefaultTracker(Tracker tracker) {
        synchronized (this) {
            GAUsage.getInstance().setUsage(Field.SET_DEFAULT_TRACKER);
            this.mDefaultTracker = tracker;
        }
    }

    public void closeTracker(String str) {
        synchronized (this) {
            GAUsage.getInstance().setUsage(Field.CLOSE_TRACKER);
            if (((Tracker) this.mTrackers.remove(str)) == this.mDefaultTracker) {
                this.mDefaultTracker = null;
            }
        }
    }

    void sendHit(Map<String, String> map) {
        synchronized (this) {
            if (map == null) {
                throw new IllegalArgumentException("hit cannot be null");
            }
            Utils.putIfAbsent(map, Fields.LANGUAGE, Utils.getLanguage(Locale.getDefault()));
            Utils.putIfAbsent(map, Fields.SCREEN_RESOLUTION, ScreenResolutionDefaultProvider.getProvider().getValue(Fields.SCREEN_RESOLUTION));
            map.put("&_u", GAUsage.getInstance().getAndClearSequence());
            GAUsage.getInstance().getAndClearUsage();
            this.mThread.sendHit(map);
        }
    }

    @VisibleForTesting
    void close() {
    }

    public void setAppOptOut(boolean z) {
        GAUsage.getInstance().setUsage(Field.SET_APP_OPT_OUT);
        this.mAppOptOut = Boolean.valueOf(z);
        if (this.mAppOptOut.booleanValue()) {
            this.mThread.clearHits();
        }
    }

    public boolean getAppOptOut() {
        GAUsage.getInstance().setUsage(Field.GET_APP_OPT_OUT);
        return this.mAppOptOut.booleanValue();
    }

    public Logger getLogger() {
        return this.mLogger;
    }

    public void setLogger(Logger logger) {
        GAUsage.getInstance().setUsage(Field.SET_LOGGER);
        this.mLogger = logger;
    }
}
