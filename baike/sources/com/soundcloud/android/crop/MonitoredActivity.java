package com.soundcloud.android.crop;

import android.app.Activity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;

abstract class MonitoredActivity extends Activity {
    private final ArrayList<LifeCycleListener> a = new ArrayList();

    public interface LifeCycleListener {
        void onActivityCreated(MonitoredActivity monitoredActivity);

        void onActivityDestroyed(MonitoredActivity monitoredActivity);

        void onActivityStarted(MonitoredActivity monitoredActivity);

        void onActivityStopped(MonitoredActivity monitoredActivity);
    }

    public static class LifeCycleAdapter implements LifeCycleListener {
        public void onActivityCreated(MonitoredActivity monitoredActivity) {
        }

        public void onActivityDestroyed(MonitoredActivity monitoredActivity) {
        }

        public void onActivityStarted(MonitoredActivity monitoredActivity) {
        }

        public void onActivityStopped(MonitoredActivity monitoredActivity) {
        }
    }

    MonitoredActivity() {
    }

    public void addLifeCycleListener(LifeCycleListener lifeCycleListener) {
        if (!this.a.contains(lifeCycleListener)) {
            this.a.add(lifeCycleListener);
        }
    }

    public void removeLifeCycleListener(LifeCycleListener lifeCycleListener) {
        this.a.remove(lifeCycleListener);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((LifeCycleListener) it.next()).onActivityCreated(this);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((LifeCycleListener) it.next()).onActivityDestroyed(this);
        }
    }

    protected void onStart() {
        super.onStart();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((LifeCycleListener) it.next()).onActivityStarted(this);
        }
    }

    protected void onStop() {
        super.onStop();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((LifeCycleListener) it.next()).onActivityStopped(this);
        }
    }
}
