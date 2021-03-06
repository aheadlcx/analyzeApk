package com.liulishuo.filedownloader.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent;
import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent.ConnectStatus;
import com.liulishuo.filedownloader.f;
import com.liulishuo.filedownloader.g.d;
import com.liulishuo.filedownloader.t;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class a<CALLBACK extends Binder, INTERFACE extends IInterface> implements ServiceConnection, t {
    private final CALLBACK a;
    private volatile INTERFACE b;
    private final Class<?> c;
    private final HashMap<String, Object> d = new HashMap();
    private final List<Context> e = new ArrayList();
    private final ArrayList<Runnable> f = new ArrayList();

    protected abstract void a(INTERFACE interfaceR, CALLBACK callback) throws RemoteException;

    protected abstract CALLBACK b();

    protected abstract INTERFACE b(IBinder iBinder);

    protected abstract void b(INTERFACE interfaceR, CALLBACK callback) throws RemoteException;

    protected INTERFACE d() {
        return this.b;
    }

    protected a(Class<?> cls) {
        this.c = cls;
        this.a = b();
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.b = b(iBinder);
        if (d.a) {
            d.c(this, "onServiceConnected %s %s", componentName, this.b);
        }
        try {
            b(this.b, this.a);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        List<Runnable> list = (List) this.f.clone();
        this.f.clear();
        for (Runnable run : list) {
            run.run();
        }
        f.a().b(new DownloadServiceConnectChangedEvent(ConnectStatus.connected, this.c));
    }

    public void onServiceDisconnected(ComponentName componentName) {
        if (d.a) {
            d.c(this, "onServiceDisconnected %s %s", componentName, this.b);
        }
        a(true);
    }

    private void a(boolean z) {
        if (!(z || this.b == null)) {
            try {
                a(this.b, this.a);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        if (d.a) {
            d.c(this, "release connect resources %s", this.b);
        }
        this.b = null;
        f.a().b(new DownloadServiceConnectChangedEvent(z ? ConnectStatus.lost : ConnectStatus.disconnected, this.c));
    }

    public void a(Context context) {
        a(context, null);
    }

    public void a(Context context, Runnable runnable) {
        if (com.liulishuo.filedownloader.g.f.a(context)) {
            throw new IllegalStateException("Fatal-Exception: You can't bind the FileDownloadService in :filedownloader process.\n It's the invalid operation and is likely to cause unexpected problems.\n Maybe you want to use non-separate process mode for FileDownloader, More detail about non-separate mode, please move to wiki manually: https://github.com/lingochamp/FileDownloader/wiki/filedownloader.properties");
        }
        if (d.a) {
            d.c(this, "bindStartByContext %s", context.getClass().getSimpleName());
        }
        Intent intent = new Intent(context, this.c);
        if (!(runnable == null || this.f.contains(runnable))) {
            this.f.add(runnable);
        }
        if (!this.e.contains(context)) {
            this.e.add(context);
        }
        context.bindService(intent, this, 1);
        context.startService(intent);
    }

    public boolean c() {
        return d() != null;
    }
}
