package com.meizu.cloud.pushsdk.pushtracer.tracker;

import android.content.Context;
import com.meizu.cloud.pushsdk.PushManager;
import com.meizu.cloud.pushsdk.pushtracer.constant.TrackerConstants;
import com.meizu.cloud.pushsdk.pushtracer.dataload.SelfDescribingJson;
import com.meizu.cloud.pushsdk.pushtracer.dataload.TrackerDataload;
import com.meizu.cloud.pushsdk.pushtracer.emitter.Emitter;
import com.meizu.cloud.pushsdk.pushtracer.event.PushEvent;
import com.meizu.cloud.pushsdk.pushtracer.utils.LogLevel;
import com.meizu.cloud.pushsdk.pushtracer.utils.Logger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Tracker {
    private static final String TAG = Tracker.class.getSimpleName();
    protected String appId;
    protected boolean base64Encoded;
    protected AtomicBoolean dataCollection = new AtomicBoolean(true);
    protected Emitter emitter;
    protected LogLevel level;
    protected String namespace;
    protected long sessionCheckInterval;
    protected boolean sessionContext;
    protected Subject subject;
    protected int threadCount;
    protected TimeUnit timeUnit;
    protected Session trackerSession;
    protected final String trackerVersion = PushManager.TAG;

    public static class TrackerBuilder {
        protected static Class<? extends Tracker> defaultTrackerClass;
        protected final String appId;
        protected long backgroundTimeout;
        protected boolean base64Encoded;
        protected final Context context;
        protected final Emitter emitter;
        protected long foregroundTimeout;
        protected LogLevel logLevel;
        protected final String namespace;
        protected long sessionCheckInterval;
        protected boolean sessionContext;
        protected Subject subject;
        protected int threadCount;
        protected TimeUnit timeUnit;
        private Class<? extends Tracker> trackerClass;

        public TrackerBuilder(Emitter emitter, String str, String str2, Context context) {
            this(emitter, str, str2, context, defaultTrackerClass);
        }

        public TrackerBuilder(Emitter emitter, String str, String str2, Context context, Class<? extends Tracker> cls) {
            this.subject = null;
            this.base64Encoded = false;
            this.logLevel = LogLevel.OFF;
            this.sessionContext = false;
            this.foregroundTimeout = 600;
            this.backgroundTimeout = 300;
            this.sessionCheckInterval = 15;
            this.threadCount = 10;
            this.timeUnit = TimeUnit.SECONDS;
            this.emitter = emitter;
            this.namespace = str;
            this.appId = str2;
            this.context = context;
            this.trackerClass = cls;
        }

        public TrackerBuilder subject(Subject subject) {
            this.subject = subject;
            return this;
        }

        public TrackerBuilder base64(Boolean bool) {
            this.base64Encoded = bool.booleanValue();
            return this;
        }

        public TrackerBuilder level(LogLevel logLevel) {
            this.logLevel = logLevel;
            return this;
        }

        public TrackerBuilder sessionContext(boolean z) {
            this.sessionContext = z;
            return this;
        }

        public TrackerBuilder foregroundTimeout(long j) {
            this.foregroundTimeout = j;
            return this;
        }

        public TrackerBuilder backgroundTimeout(long j) {
            this.backgroundTimeout = j;
            return this;
        }

        public TrackerBuilder sessionCheckInterval(long j) {
            this.sessionCheckInterval = j;
            return this;
        }

        public TrackerBuilder threadCount(int i) {
            this.threadCount = i;
            return this;
        }

        public TrackerBuilder timeUnit(TimeUnit timeUnit) {
            this.timeUnit = timeUnit;
            return this;
        }
    }

    public abstract void pauseSessionChecking();

    public abstract void resumeSessionChecking();

    public Tracker(TrackerBuilder trackerBuilder) {
        int i = 2;
        this.emitter = trackerBuilder.emitter;
        this.appId = trackerBuilder.appId;
        this.base64Encoded = trackerBuilder.base64Encoded;
        this.namespace = trackerBuilder.namespace;
        this.subject = trackerBuilder.subject;
        this.level = trackerBuilder.logLevel;
        this.sessionContext = trackerBuilder.sessionContext;
        this.sessionCheckInterval = trackerBuilder.sessionCheckInterval;
        if (trackerBuilder.threadCount >= 2) {
            i = trackerBuilder.threadCount;
        }
        this.threadCount = i;
        this.timeUnit = trackerBuilder.timeUnit;
        if (this.sessionContext) {
            this.trackerSession = new Session(trackerBuilder.foregroundTimeout, trackerBuilder.backgroundTimeout, trackerBuilder.timeUnit, trackerBuilder.context);
        }
        Logger.updateLogLevel(trackerBuilder.logLevel);
        Logger.i(TAG, "Tracker created successfully.", new Object[0]);
    }

    private void addEventPayload(TrackerDataload trackerDataload, List<SelfDescribingJson> list, boolean z) {
        if (this.subject != null) {
            trackerDataload.addMap(new HashMap(this.subject.getSubject()));
            trackerDataload.add("et", getFinalContext(list).getMap());
        }
        Logger.i(TAG, "Adding new payload to event storage: %s", trackerDataload);
        this.emitter.add(trackerDataload, z);
    }

    private SelfDescribingJson getFinalContext(List<SelfDescribingJson> list) {
        if (this.sessionContext) {
            list.add(this.trackerSession.getSessionContext());
        }
        if (this.subject != null) {
            if (!this.subject.getSubjectLocation().isEmpty()) {
                list.add(new SelfDescribingJson(TrackerConstants.GEOLOCATION_SCHEMA, this.subject.getSubjectLocation()));
            }
            if (!this.subject.getSubjectMobile().isEmpty()) {
                list.add(new SelfDescribingJson(TrackerConstants.MOBILE_SCHEMA, this.subject.getSubjectMobile()));
            }
        }
        List linkedList = new LinkedList();
        for (SelfDescribingJson map : list) {
            linkedList.add(map.getMap());
        }
        return new SelfDescribingJson(TrackerConstants.SCHEMA_CONTEXTS, linkedList);
    }

    public void track(PushEvent pushEvent, boolean z) {
        if (this.dataCollection.get()) {
            addEventPayload(pushEvent.getDataLoad(), pushEvent.getSelfDescribingJson(), z);
        }
    }

    public void track(PushEvent pushEvent) {
        track(pushEvent, true);
    }

    public void pauseEventTracking() {
        if (this.dataCollection.compareAndSet(true, false)) {
            pauseSessionChecking();
            getEmitter().shutdown();
        }
    }

    public void resumeEventTracking() {
        if (this.dataCollection.compareAndSet(false, true)) {
            resumeSessionChecking();
            getEmitter().flush();
        }
    }

    public void restartEventTracking() {
        if (this.dataCollection.get()) {
            getEmitter().flush();
        }
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setEmitter(Emitter emitter) {
        getEmitter().shutdown();
        this.emitter = emitter;
    }

    public String getTrackerVersion() {
        getClass();
        return PushManager.TAG;
    }

    public Subject getSubject() {
        return this.subject;
    }

    public Emitter getEmitter() {
        return this.emitter;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public String getAppId() {
        return this.appId;
    }

    public boolean getBase64Encoded() {
        return this.base64Encoded;
    }

    public LogLevel getLogLevel() {
        return this.level;
    }

    public Session getSession() {
        return this.trackerSession;
    }

    public boolean getDataCollection() {
        return this.dataCollection.get();
    }

    public int getThreadCount() {
        return this.threadCount;
    }
}
