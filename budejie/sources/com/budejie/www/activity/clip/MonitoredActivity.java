package com.budejie.www.activity.clip;

import android.app.Activity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;

public class MonitoredActivity extends Activity {
    private final ArrayList<b> a = new ArrayList();

    public interface b {
        void a(MonitoredActivity monitoredActivity);

        void b(MonitoredActivity monitoredActivity);

        void c(MonitoredActivity monitoredActivity);

        void d(MonitoredActivity monitoredActivity);
    }

    public static class a implements b {
        public void a(MonitoredActivity monitoredActivity) {
        }

        public void b(MonitoredActivity monitoredActivity) {
        }

        public void c(MonitoredActivity monitoredActivity) {
        }

        public void d(MonitoredActivity monitoredActivity) {
        }
    }

    public void a(b bVar) {
        if (!this.a.contains(bVar)) {
            this.a.add(bVar);
        }
    }

    public void b(b bVar) {
        this.a.remove(bVar);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((b) it.next()).a(this);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((b) it.next()).b(this);
        }
    }

    protected void onStart() {
        super.onStart();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((b) it.next()).c(this);
        }
    }

    protected void onStop() {
        super.onStop();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((b) it.next()).d(this);
        }
    }
}
