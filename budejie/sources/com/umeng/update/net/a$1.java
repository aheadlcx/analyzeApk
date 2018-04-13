package com.umeng.update.net;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.umeng.update.net.a.a;
import u.upd.b;

class a$1 implements ServiceConnection {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        b.c(a.b(), "ServiceConnection.onServiceConnected");
        a.a(this.a, new Messenger(iBinder));
        try {
            Message obtain = Message.obtain(null, 4);
            a aVar = new a(a.a(this.a), a.b(this.a), a.c(this.a));
            aVar.d = a.d(this.a);
            aVar.e = a.e(this.a);
            aVar.f = a.f(this.a);
            aVar.g = a.g(this.a);
            aVar.h = a.h(this.a);
            aVar.i = a.i(this.a);
            obtain.setData(aVar.a());
            obtain.replyTo = this.a.a;
            a.j(this.a).send(obtain);
        } catch (RemoteException e) {
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        b.c(a.b(), "ServiceConnection.onServiceDisconnected");
        a.a(this.a, null);
    }
}
