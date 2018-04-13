package cn.v6.sixrooms.surfaceanim;

import android.os.Bundle;
import android.os.Message;
import android.os.RemoteException;
import cn.v6.sixrooms.surfaceanim.util.AnimBeanHelper;

final class a implements Runnable {
    final /* synthetic */ Object a;

    a(Object obj) {
        this.a = obj;
    }

    public final void run() {
        while (true) {
            if (AnimEngine.b != null && AnimEngine.b.getBinder().isBinderAlive()) {
                break;
            }
            AnimEngine.b(AnimEngine.d);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Message obtain = Message.obtain();
        obtain.what = 1;
        Bundle bundle = null;
        try {
            bundle = AnimBeanHelper.getAnimBeanBundle(this.a);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
        obtain.setData(bundle);
        try {
            if (AnimEngine.b != null) {
                AnimEngine.b.send(obtain);
            }
        } catch (RemoteException e3) {
            e3.printStackTrace();
        }
    }
}
