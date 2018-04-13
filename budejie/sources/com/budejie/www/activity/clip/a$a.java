package com.budejie.www.activity.clip;

import android.app.ProgressDialog;
import android.os.Handler;
import com.budejie.www.activity.clip.MonitoredActivity.a;
import com.umeng.analytics.MobclickAgent;

class a$a extends a implements Runnable {
    private final MonitoredActivity a;
    private final ProgressDialog b;
    private final Runnable c;
    private final Handler d;
    private final Runnable e = new Runnable(this) {
        final /* synthetic */ a$a a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.a.b(this.a);
            if (this.a.b.getWindow() != null) {
                this.a.b.dismiss();
            }
        }
    };

    public a$a(MonitoredActivity monitoredActivity, Runnable runnable, ProgressDialog progressDialog, Handler handler) {
        this.a = monitoredActivity;
        this.b = progressDialog;
        this.c = runnable;
        this.a.a(this);
        this.d = handler;
    }

    public void run() {
        try {
            this.c.run();
            this.d.post(this.e);
        } catch (InternalError e) {
            if (this.a != null) {
                MobclickAgent.onEvent(this.a, "E07_A02", "InternalError com.budejie.www.activity.clip.Util run()");
            }
            this.d.post(this.e);
        } catch (Throwable th) {
            this.d.post(this.e);
        }
    }

    public void b(MonitoredActivity monitoredActivity) {
        try {
            this.e.run();
        } catch (InternalError e) {
            if (this.a != null) {
                MobclickAgent.onEvent(this.a, "E07_A02", "InternalError com.budejie.www.activity.clip.Util onActivityDestroyed()");
            }
        }
        this.d.removeCallbacks(this.e);
    }

    public void d(MonitoredActivity monitoredActivity) {
        this.b.hide();
    }

    public void c(MonitoredActivity monitoredActivity) {
        this.b.show();
    }
}
