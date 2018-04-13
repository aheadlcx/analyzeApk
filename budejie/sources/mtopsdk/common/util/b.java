package mtopsdk.common.util;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;

class b implements ServiceConnection {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.a.d) {
            if (m.a(TBSdkLog$LogEnable.DebugEnable)) {
                m.a("mtopsdk.AsyncServiceBinder", "[onServiceConnected] Service connected called. interfaceName =" + this.a.c());
            }
            try {
                for (Class cls : this.a.b.getDeclaredClasses()) {
                    if (cls.getSimpleName().equals("Stub")) {
                        this.a.a = (IInterface) cls.getDeclaredMethod("asInterface", new Class[]{IBinder.class}).invoke(cls, new Object[]{iBinder});
                    }
                }
            } catch (Exception e) {
                if (m.a(TBSdkLog$LogEnable.WarnEnable)) {
                    m.c("mtopsdk.AsyncServiceBinder", "[onServiceConnected] Service bind failed. interfaceName=" + this.a.c());
                }
            }
            if (this.a.a != null) {
                this.a.a();
            }
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        synchronized (this.a.d) {
            this.a.a = null;
        }
    }
}
