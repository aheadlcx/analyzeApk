package com.liulishuo.filedownloader;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.liulishuo.filedownloader.c.b;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.e;
import com.liulishuo.filedownloader.services.FileDownloadService.SeparateProcessService;

class o extends com.liulishuo.filedownloader.services.a<a, b> {

    protected static class a extends com.liulishuo.filedownloader.c.a.a {
        protected a() {
        }

        public void a(MessageSnapshot messageSnapshot) throws RemoteException {
            e.a().a(messageSnapshot);
        }
    }

    protected /* synthetic */ void a(IInterface iInterface, Binder binder) throws RemoteException {
        b((b) iInterface, (a) binder);
    }

    protected /* synthetic */ Binder b() {
        return a();
    }

    protected /* synthetic */ IInterface b(IBinder iBinder) {
        return a(iBinder);
    }

    protected /* synthetic */ void b(IInterface iInterface, Binder binder) throws RemoteException {
        a((b) iInterface, (a) binder);
    }

    o() {
        super(SeparateProcessService.class);
    }

    protected a a() {
        return new a();
    }

    protected b a(IBinder iBinder) {
        return com.liulishuo.filedownloader.c.b.a.a(iBinder);
    }

    protected void a(b bVar, a aVar) throws RemoteException {
        bVar.a((com.liulishuo.filedownloader.c.a) aVar);
    }

    protected void b(b bVar, a aVar) throws RemoteException {
        bVar.b((com.liulishuo.filedownloader.c.a) aVar);
    }

    public boolean a(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, com.liulishuo.filedownloader.d.b bVar, boolean z3) {
        if (!c()) {
            return com.liulishuo.filedownloader.g.a.a(str, str2, z);
        }
        try {
            ((b) d()).a(str, str2, z, i, i2, i3, z2, bVar, z3);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean a(int i) {
        if (!c()) {
            return com.liulishuo.filedownloader.g.a.a(i);
        }
        try {
            return ((b) d()).a(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public byte b(int i) {
        if (!c()) {
            return com.liulishuo.filedownloader.g.a.b(i);
        }
        try {
            return ((b) d()).e(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return (byte) 0;
        }
    }

    public boolean c(int i) {
        if (!c()) {
            return com.liulishuo.filedownloader.g.a.c(i);
        }
        try {
            return ((b) d()).b(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }
}
