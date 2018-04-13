package com.meizu.cloud.pushsdk.pushtracer.tracker;

import android.content.Context;
import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import com.meizu.cloud.pushsdk.pushtracer.constant.TrackerConstants;
import com.meizu.cloud.pushsdk.pushtracer.dataload.SelfDescribingJson;
import com.meizu.cloud.pushsdk.pushtracer.utils.FileStore;
import com.meizu.cloud.pushsdk.pushtracer.utils.Logger;
import com.meizu.cloud.pushsdk.pushtracer.utils.Util;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Session {
    private static String TAG = Session.class.getSimpleName();
    private long accessedLast;
    private long backgroundTimeout;
    private Context context;
    private String currentSessionId = null;
    private long foregroundTimeout;
    private AtomicBoolean isBackground = new AtomicBoolean(false);
    private String previousSessionId;
    private int sessionIndex = 0;
    private String sessionStorage = "SQLITE";
    private String userId;

    public Session(long j, long j2, TimeUnit timeUnit, Context context) {
        this.foregroundTimeout = timeUnit.toMillis(j);
        this.backgroundTimeout = timeUnit.toMillis(j2);
        this.context = context;
        Map sessionFromFile = getSessionFromFile();
        if (sessionFromFile == null) {
            this.userId = Util.getEventId();
        } else {
            try {
                String obj = sessionFromFile.get(Parameters.SESSION_USER_ID).toString();
                String obj2 = sessionFromFile.get(Parameters.SESSION_ID).toString();
                int intValue = ((Integer) sessionFromFile.get(Parameters.SESSION_INDEX)).intValue();
                this.userId = obj;
                this.sessionIndex = intValue;
                this.currentSessionId = obj2;
            } catch (Exception e) {
                Logger.e(TAG, "Exception occurred retrieving session info from file: %s", e.getMessage());
                this.userId = Util.getEventId();
            }
        }
        updateSessionInfo();
        updateAccessedTime();
        Logger.i(TAG, "Tracker Session Object created.", new Object[0]);
    }

    public SelfDescribingJson getSessionContext() {
        Logger.i(TAG, "Getting session context...", new Object[0]);
        updateAccessedTime();
        return new SelfDescribingJson(TrackerConstants.SESSION_SCHEMA, getSessionValues());
    }

    public void checkAndUpdateSession() {
        long j;
        Logger.d(TAG, "Checking and updating session information.", new Object[0]);
        long currentTimeMillis = System.currentTimeMillis();
        if (this.isBackground.get()) {
            j = this.backgroundTimeout;
        } else {
            j = this.foregroundTimeout;
        }
        if (!Util.isTimeInRange(this.accessedLast, currentTimeMillis, j)) {
            updateSessionInfo();
            updateAccessedTime();
        }
    }

    public void setIsBackground(boolean z) {
        Logger.d(TAG, "Application is in the background: %s", Boolean.valueOf(z));
        this.isBackground.set(z);
    }

    public Map getSessionValues() {
        Map hashMap = new HashMap();
        hashMap.put(Parameters.SESSION_USER_ID, this.userId);
        hashMap.put(Parameters.SESSION_ID, this.currentSessionId);
        hashMap.put(Parameters.SESSION_PREVIOUS_ID, this.previousSessionId);
        hashMap.put(Parameters.SESSION_INDEX, Integer.valueOf(this.sessionIndex));
        hashMap.put(Parameters.SESSION_STORAGE, this.sessionStorage);
        return hashMap;
    }

    private void updateSessionInfo() {
        this.previousSessionId = this.currentSessionId;
        this.currentSessionId = Util.getEventId();
        this.sessionIndex++;
        Logger.d(TAG, "Session information is updated:", new Object[0]);
        Logger.d(TAG, " + Session ID: %s", this.currentSessionId);
        Logger.d(TAG, " + Previous Session ID: %s", this.previousSessionId);
        Logger.d(TAG, " + Session Index: %s", Integer.valueOf(this.sessionIndex));
        saveSessionToFile();
    }

    private boolean saveSessionToFile() {
        return FileStore.saveMapToFile(TrackerConstants.SNOWPLOW_SESSION_VARS, getSessionValues(), this.context);
    }

    private Map getSessionFromFile() {
        return FileStore.getMapFromFile(TrackerConstants.SNOWPLOW_SESSION_VARS, this.context);
    }

    private void updateAccessedTime() {
        this.accessedLast = System.currentTimeMillis();
    }

    public int getSessionIndex() {
        return this.sessionIndex;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getCurrentSessionId() {
        return this.currentSessionId;
    }

    public String getPreviousSessionId() {
        return this.previousSessionId;
    }

    public String getSessionStorage() {
        return this.sessionStorage;
    }

    public long getForegroundTimeout() {
        return this.foregroundTimeout;
    }

    public long getBackgroundTimeout() {
        return this.backgroundTimeout;
    }
}
