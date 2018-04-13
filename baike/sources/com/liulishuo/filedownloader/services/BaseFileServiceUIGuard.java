package com.liulishuo.filedownloader.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.liulishuo.filedownloader.FileDownloadEventPool;
import com.liulishuo.filedownloader.IFileDownloadServiceProxy;
import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent;
import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent.ConnectStatus;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class BaseFileServiceUIGuard<CALLBACK extends Binder, INTERFACE extends IInterface> implements ServiceConnection, IFileDownloadServiceProxy {
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

    protected INTERFACE c() {
        return this.b;
    }

    protected BaseFileServiceUIGuard(Class<?> cls) {
        this.c = cls;
        this.a = b();
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.b = b(iBinder);
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "onServiceConnected %s %s", componentName, this.b);
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
        FileDownloadEventPool.getImpl().asyncPublishInNewThread(new DownloadServiceConnectChangedEvent(ConnectStatus.connected, this.c));
    }

    public void onServiceDisconnected(ComponentName componentName) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "onServiceDisconnected %s %s", componentName, this.b);
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
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "release connect resources %s", this.b);
        }
        this.b = null;
        FileDownloadEventPool.getImpl().asyncPublishInNewThread(new DownloadServiceConnectChangedEvent(z ? ConnectStatus.lost : ConnectStatus.disconnected, this.c));
    }

    public void bindStartByContext(Context context) {
        bindStartByContext(context, null);
    }

    public void bindStartByContext(Context context, Runnable runnable) {
        if (FileDownloadUtils.isDownloaderProcess(context)) {
            throw new IllegalStateException("Fatal-Exception: You can't bind the FileDownloadService in :filedownloader process.\n It's the invalid operation, and is likely to cause unexpected problems.\n Maybe you want to use non-separate process mode for FileDownloader, More detail about non-separate mode, please move to wiki manually: https://github.com/lingochamp/FileDownloader/wiki/filedownloader.properties");
        }
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "bindStartByContext %s", context.getClass().getSimpleName());
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

    public void unbindByContext(Context context) {
        if (this.e.contains(context)) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "unbindByContext %s", context);
            }
            this.e.remove(context);
            if (this.e.isEmpty()) {
                a(false);
            }
            Intent intent = new Intent(context, this.c);
            context.unbindService(this);
            context.stopService(intent);
        }
    }

    public void startService(Context context) {
        context.startService(new Intent(context, this.c));
    }

    public boolean isConnected() {
        return c() != null;
    }
}
