package mtopsdk.xstate;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.m;
import mtopsdk.xstate.a.b;

public class d extends Service {
    private b a = null;
    private Object b = new Object();

    public IBinder onBind(Intent intent) {
        synchronized (this.b) {
            if (this.a == null) {
                this.a = new e(this);
                try {
                    this.a.a();
                } catch (Throwable e) {
                    m.b("mtopsdk.XStateService", "[onBind]init() exception", e);
                } catch (Throwable e2) {
                    m.b("mtopsdk.XStateService", "[onBind]init() error", e2);
                }
            }
        }
        if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
            m.b("mtopsdk.XStateService", "[onBind] XStateService  stub= " + this.a.hashCode());
        }
        return this.a;
    }

    public void onDestroy() {
        super.onDestroy();
        synchronized (this.b) {
            if (this.a != null) {
                try {
                    this.a.b();
                } catch (Throwable e) {
                    m.b("mtopsdk.XStateService", "[onDestroy]unInit() exception", e);
                } catch (Throwable e2) {
                    m.b("mtopsdk.XStateService", "[onDestroy]unInit() error", e2);
                }
            }
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }
}
