package com.liulishuo.filedownloader;

import android.content.Context;
import android.content.Intent;
import com.liulishuo.filedownloader.d.b;
import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent;
import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent.ConnectStatus;
import com.liulishuo.filedownloader.services.FileDownloadService.SharedMainProcessService;
import com.liulishuo.filedownloader.services.e;
import com.liulishuo.filedownloader.services.e.a;
import java.util.ArrayList;
import java.util.List;

class n implements a, t {
    private static final Class<?> a = SharedMainProcessService.class;
    private final ArrayList<Runnable> b = new ArrayList();
    private e c;

    n() {
    }

    public boolean a(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, b bVar, boolean z3) {
        if (!c()) {
            return com.liulishuo.filedownloader.g.a.a(str, str2, z);
        }
        this.c.a(str, str2, z, i, i2, i3, z2, bVar, z3);
        return true;
    }

    public boolean a(int i) {
        if (c()) {
            return this.c.a(i);
        }
        return com.liulishuo.filedownloader.g.a.a(i);
    }

    public byte b(int i) {
        if (c()) {
            return this.c.e(i);
        }
        return com.liulishuo.filedownloader.g.a.b(i);
    }

    public boolean c() {
        return this.c != null;
    }

    public void a(Context context) {
        a(context, null);
    }

    public void a(Context context, Runnable runnable) {
        if (!(runnable == null || this.b.contains(runnable))) {
            this.b.add(runnable);
        }
        context.startService(new Intent(context, a));
    }

    public boolean c(int i) {
        if (c()) {
            return this.c.b(i);
        }
        return com.liulishuo.filedownloader.g.a.c(i);
    }

    public void a(e eVar) {
        this.c = eVar;
        List<Runnable> list = (List) this.b.clone();
        this.b.clear();
        for (Runnable run : list) {
            run.run();
        }
        f.a().b(new DownloadServiceConnectChangedEvent(ConnectStatus.connected, a));
    }

    public void a() {
        this.c = null;
        f.a().b(new DownloadServiceConnectChangedEvent(ConnectStatus.disconnected, a));
    }
}
