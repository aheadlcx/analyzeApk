package com.ut.mini.core.appstatus;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.alibaba.mtl.log.e.r;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

@TargetApi(14)
public class UTMCAppStatusMonitor implements ActivityLifecycleCallbacks {
    private static UTMCAppStatusMonitor a = null;
    private int J = 0;
    private boolean P = false;
    /* renamed from: a */
    private ScheduledFuture<?> f77a = null;
    private Object d = new Object();
    private Object e = new Object();
    private List<UTMCAppStatusCallbacks> m = new LinkedList();

    private class a implements Runnable {
        final /* synthetic */ UTMCAppStatusMonitor b;

        private a(UTMCAppStatusMonitor uTMCAppStatusMonitor) {
            this.b = uTMCAppStatusMonitor;
        }

        public void run() {
            this.b.P = false;
            synchronized (this.b.e) {
                for (UTMCAppStatusCallbacks onSwitchBackground : this.b.e) {
                    onSwitchBackground.onSwitchBackground();
                }
            }
        }
    }

    private UTMCAppStatusMonitor() {
    }

    public static synchronized UTMCAppStatusMonitor getInstance() {
        UTMCAppStatusMonitor uTMCAppStatusMonitor;
        synchronized (UTMCAppStatusMonitor.class) {
            if (a == null) {
                a = new UTMCAppStatusMonitor();
            }
            uTMCAppStatusMonitor = a;
        }
        return uTMCAppStatusMonitor;
    }

    public void registerAppStatusCallbacks(UTMCAppStatusCallbacks uTMCAppStatusCallbacks) {
        if (uTMCAppStatusCallbacks != null) {
            synchronized (this.e) {
                this.m.add(uTMCAppStatusCallbacks);
            }
        }
    }

    public void unregisterAppStatusCallbacks(UTMCAppStatusCallbacks uTMCAppStatusCallbacks) {
        if (uTMCAppStatusCallbacks != null) {
            synchronized (this.e) {
                this.m.remove(uTMCAppStatusCallbacks);
            }
        }
    }

    private void N() {
        synchronized (this.d) {
            r.a().f(11);
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        synchronized (this.e) {
            for (UTMCAppStatusCallbacks onActivityCreated : this.m) {
                onActivityCreated.onActivityCreated(activity, bundle);
            }
        }
    }

    public void onActivityDestroyed(Activity activity) {
        synchronized (this.e) {
            for (UTMCAppStatusCallbacks onActivityDestroyed : this.m) {
                onActivityDestroyed.onActivityDestroyed(activity);
            }
        }
    }

    public void onActivityPaused(Activity activity) {
        synchronized (this.e) {
            for (UTMCAppStatusCallbacks onActivityPaused : this.m) {
                onActivityPaused.onActivityPaused(activity);
            }
        }
    }

    public void onActivityResumed(Activity activity) {
        synchronized (this.e) {
            for (UTMCAppStatusCallbacks onActivityResumed : this.m) {
                onActivityResumed.onActivityResumed(activity);
            }
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        synchronized (this.e) {
            for (UTMCAppStatusCallbacks onActivitySaveInstanceState : this.m) {
                onActivitySaveInstanceState.onActivitySaveInstanceState(activity, bundle);
            }
        }
    }

    public void onActivityStarted(Activity activity) {
        N();
        this.J++;
        if (!this.P) {
            synchronized (this.e) {
                for (UTMCAppStatusCallbacks onSwitchForeground : this.m) {
                    onSwitchForeground.onSwitchForeground();
                }
            }
        }
        this.P = true;
    }

    public void onActivityStopped(Activity activity) {
        this.J--;
        if (this.J == 0) {
            N();
            r.a().a(11, new a(), 1000);
        }
    }
}
