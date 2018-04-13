package com.google.analytics.tracking.android;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.google.analytics.tracking.android.GAUsage.Field;
import com.google.analytics.tracking.android.Logger.LogLevel;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class EasyTracker extends Tracker {
    private static final int DEFAULT_SAMPLE_RATE = 100;
    private static final String EASY_TRACKER_NAME = "easy_tracker";
    static final int NUM_MILLISECONDS_TO_WAIT_FOR_OPEN_ACTIVITY = 1000;
    private static EasyTracker sInstance;
    private static String sResourcePackageName;
    private int mActivitiesActive;
    private final Map<String, String> mActivityNameMap;
    private Clock mClock;
    private Context mContext;
    private final GoogleAnalytics mGoogleAnalytics;
    private boolean mIsAutoActivityTracking;
    private boolean mIsInForeground;
    private boolean mIsReportUncaughtExceptionsEnabled;
    private long mLastOnStopTime;
    private ParameterLoader mParameterFetcher;
    private ServiceManager mServiceManager;
    private long mSessionTimeout;
    private boolean mStartSessionOnNextSend;
    private Timer mTimer;
    private TimerTask mTimerTask;

    private EasyTracker(Context context) {
        this(context, new ParameterLoaderImpl(context), GoogleAnalytics.getInstance(context), GAServiceManager.getInstance(), null);
    }

    private EasyTracker(Context context, ParameterLoader parameterLoader, GoogleAnalytics googleAnalytics, ServiceManager serviceManager, TrackerHandler trackerHandler) {
        String str = EASY_TRACKER_NAME;
        if (trackerHandler == null) {
            trackerHandler = googleAnalytics;
        }
        super(str, null, trackerHandler);
        this.mIsAutoActivityTracking = false;
        this.mActivitiesActive = 0;
        this.mActivityNameMap = new HashMap();
        this.mIsInForeground = false;
        this.mStartSessionOnNextSend = false;
        if (sResourcePackageName != null) {
            parameterLoader.setResourcePackageName(sResourcePackageName);
        }
        this.mGoogleAnalytics = googleAnalytics;
        setContext(context, parameterLoader, serviceManager);
        this.mClock = new EasyTracker$1(this);
    }

    public static EasyTracker getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new EasyTracker(context);
        }
        return sInstance;
    }

    @VisibleForTesting
    static EasyTracker getNewInstance(Context context, ParameterLoader parameterLoader, GoogleAnalytics googleAnalytics, ServiceManager serviceManager, TrackerHandler trackerHandler) {
        sInstance = new EasyTracker(context, parameterLoader, googleAnalytics, serviceManager, trackerHandler);
        return sInstance;
    }

    boolean checkForNewSession() {
        return this.mSessionTimeout == 0 || (this.mSessionTimeout > 0 && this.mClock.currentTimeMillis() > this.mLastOnStopTime + this.mSessionTimeout);
    }

    private void loadParameters() {
        Log.v("Starting EasyTracker.");
        String string = this.mParameterFetcher.getString("ga_trackingId");
        if (TextUtils.isEmpty(string)) {
            string = this.mParameterFetcher.getString("ga_api_key");
        }
        set(Fields.TRACKING_ID, string);
        Log.v("[EasyTracker] trackingId loaded: " + string);
        string = this.mParameterFetcher.getString("ga_appName");
        if (!TextUtils.isEmpty(string)) {
            Log.v("[EasyTracker] app name loaded: " + string);
            set(Fields.APP_NAME, string);
        }
        string = this.mParameterFetcher.getString("ga_appVersion");
        if (string != null) {
            Log.v("[EasyTracker] app version loaded: " + string);
            set(Fields.APP_VERSION, string);
        }
        string = this.mParameterFetcher.getString("ga_logLevel");
        if (string != null) {
            LogLevel logLevelFromString = getLogLevelFromString(string);
            if (logLevelFromString != null) {
                Log.v("[EasyTracker] log level loaded: " + logLevelFromString);
                this.mGoogleAnalytics.getLogger().setLogLevel(logLevelFromString);
            }
        }
        Double doubleFromString = this.mParameterFetcher.getDoubleFromString("ga_sampleFrequency");
        if (doubleFromString == null) {
            doubleFromString = new Double((double) this.mParameterFetcher.getInt("ga_sampleRate", 100));
        }
        if (doubleFromString.doubleValue() != 100.0d) {
            set(Fields.SAMPLE_RATE, Double.toString(doubleFromString.doubleValue()));
        }
        Log.v("[EasyTracker] sample rate loaded: " + doubleFromString);
        int i = this.mParameterFetcher.getInt("ga_dispatchPeriod", 1800);
        Log.v("[EasyTracker] dispatch period loaded: " + i);
        this.mServiceManager.setLocalDispatchPeriod(i);
        this.mSessionTimeout = (long) (this.mParameterFetcher.getInt("ga_sessionTimeout", 30) * 1000);
        Log.v("[EasyTracker] session timeout loaded: " + this.mSessionTimeout);
        boolean z = this.mParameterFetcher.getBoolean("ga_autoActivityTracking") || this.mParameterFetcher.getBoolean("ga_auto_activity_tracking");
        this.mIsAutoActivityTracking = z;
        Log.v("[EasyTracker] auto activity tracking loaded: " + this.mIsAutoActivityTracking);
        z = this.mParameterFetcher.getBoolean("ga_anonymizeIp");
        if (z) {
            set(Fields.ANONYMIZE_IP, "1");
            Log.v("[EasyTracker] anonymize ip loaded: " + z);
        }
        this.mIsReportUncaughtExceptionsEnabled = this.mParameterFetcher.getBoolean("ga_reportUncaughtExceptions");
        if (this.mIsReportUncaughtExceptionsEnabled) {
            Thread.setDefaultUncaughtExceptionHandler(new ExceptionReporter(this, this.mServiceManager, Thread.getDefaultUncaughtExceptionHandler(), this.mContext));
            Log.v("[EasyTracker] report uncaught exceptions loaded: " + this.mIsReportUncaughtExceptionsEnabled);
        }
        this.mGoogleAnalytics.setDryRun(this.mParameterFetcher.getBoolean("ga_dryRun"));
    }

    private LogLevel getLogLevelFromString(String str) {
        try {
            return LogLevel.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @VisibleForTesting
    void overrideUncaughtExceptionHandler(UncaughtExceptionHandler uncaughtExceptionHandler) {
        if (this.mIsReportUncaughtExceptionsEnabled) {
            Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
        }
    }

    private void setContext(Context context, ParameterLoader parameterLoader, ServiceManager serviceManager) {
        if (context == null) {
            Log.e("Context cannot be null");
        }
        this.mContext = context.getApplicationContext();
        this.mServiceManager = serviceManager;
        this.mParameterFetcher = parameterLoader;
        loadParameters();
    }

    public void activityStart(Activity activity) {
        GAUsage.getInstance().setUsage(Field.EASY_TRACKER_ACTIVITY_START);
        clearExistingTimer();
        if (!this.mIsInForeground && this.mActivitiesActive == 0 && checkForNewSession()) {
            this.mStartSessionOnNextSend = true;
        }
        this.mIsInForeground = true;
        this.mActivitiesActive++;
        if (this.mIsAutoActivityTracking) {
            Map hashMap = new HashMap();
            hashMap.put(Fields.HIT_TYPE, HitTypes.APP_VIEW);
            GAUsage.getInstance().setDisableUsage(true);
            set("&cd", getActivityName(activity));
            send(hashMap);
            GAUsage.getInstance().setDisableUsage(false);
        }
    }

    public void activityStop(Activity activity) {
        GAUsage.getInstance().setUsage(Field.EASY_TRACKER_ACTIVITY_STOP);
        this.mActivitiesActive--;
        this.mActivitiesActive = Math.max(0, this.mActivitiesActive);
        this.mLastOnStopTime = this.mClock.currentTimeMillis();
        if (this.mActivitiesActive == 0) {
            clearExistingTimer();
            this.mTimerTask = new EasyTracker$NotInForegroundTimerTask(this, null);
            this.mTimer = new Timer("waitForActivityStart");
            this.mTimer.schedule(this.mTimerTask, 1000);
        }
    }

    @Deprecated
    public void dispatchLocalHits() {
        this.mServiceManager.dispatchLocalHits();
    }

    private synchronized void clearExistingTimer() {
        if (this.mTimer != null) {
            this.mTimer.cancel();
            this.mTimer = null;
        }
    }

    private String getActivityName(Activity activity) {
        String canonicalName = activity.getClass().getCanonicalName();
        if (this.mActivityNameMap.containsKey(canonicalName)) {
            return (String) this.mActivityNameMap.get(canonicalName);
        }
        String string = this.mParameterFetcher.getString(canonicalName);
        if (string == null) {
            string = canonicalName;
        }
        this.mActivityNameMap.put(canonicalName, string);
        return string;
    }

    @VisibleForTesting
    void setClock(Clock clock) {
        this.mClock = clock;
    }

    @VisibleForTesting
    int getActivitiesActive() {
        return this.mActivitiesActive;
    }

    public void send(Map<String, String> map) {
        if (this.mStartSessionOnNextSend) {
            map.put(Fields.SESSION_CONTROL, "start");
            this.mStartSessionOnNextSend = false;
        }
        super.send(map);
    }

    public static void setResourcePackageName(String str) {
        sResourcePackageName = str;
    }
}
