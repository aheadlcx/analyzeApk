package com.zxt.download2;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.webkit.URLUtil;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

public class g {
    private static final String a = (Environment.getExternalStorageDirectory().getPath() + "/download/");
    private static g b;
    private static int c = 0;
    private a d;
    private HashMap<f, e> e = new HashMap();
    private HashMap<f, CopyOnWriteArraySet<b>> f = new HashMap();

    private g(Context context) {
        this.d = new a(context, "download.db");
    }

    public static synchronized g a(Context context) {
        g gVar;
        synchronized (g.class) {
            if (b == null) {
                b = new g(context);
            }
            gVar = b;
        }
        return gVar;
    }

    public void a(f fVar) {
        if (fVar.d() == null || fVar.d().trim().length() == 0) {
            Log.w("DownloadTaskManager", "file path is invalid. file path : " + fVar.d() + ", use default file path : " + a);
            fVar.b(a);
        }
        if (fVar.c() == null || fVar.c().trim().length() == 0) {
            Log.w("DownloadTaskManager", "file name is invalid. file name : " + fVar.c());
            throw new IllegalArgumentException("file name is invalid");
        } else if (fVar.getUrl() == null || !URLUtil.isHttpUrl(fVar.getUrl())) {
            Log.w("DownloadTaskManager", "invalid http url: " + fVar.getUrl());
            throw new IllegalArgumentException("invalid http url");
        } else if (this.e.containsKey(fVar)) {
            Log.w("DownloadTaskManager", "task existed");
        } else if (c <= 0 || this.e.size() <= c) {
            if (this.f.get(fVar) == null) {
                this.f.put(fVar, new CopyOnWriteArraySet());
            }
            fVar.a(DownloadState.INITIALIZE);
            if (!fVar.equals(a(fVar.getUrl()))) {
                c(fVar);
            }
            e eVar = new e(this, fVar);
            this.e.put(fVar, eVar);
            eVar.f();
        } else {
            Log.w("DownloadTaskManager", "trial version can only add " + c + " download task, please buy  a lincense");
        }
    }

    public void a(f fVar, boolean z) {
        if (!this.e.containsKey(fVar)) {
            return;
        }
        if (z) {
            ((e) this.e.get(fVar)).e();
        } else {
            ((e) this.e.get(fVar)).d();
        }
    }

    public void b(f fVar) {
        if (fVar.d() == null || fVar.d().trim().length() == 0) {
            Log.w("DownloadTaskManager", "file path is invalid. file path : " + fVar.d() + ", use default file path : " + a);
            fVar.b(a);
        }
        if (fVar.c() == null || fVar.c().trim().length() == 0) {
            Log.w("DownloadTaskManager", "file name is invalid. file name : " + fVar.c());
            throw new IllegalArgumentException("file name is invalid");
        } else if (fVar.getUrl() == null || !URLUtil.isHttpUrl(fVar.getUrl())) {
            Log.w("DownloadTaskManager", "invalid http url: " + fVar.getUrl());
            throw new IllegalArgumentException("invalid http url");
        } else {
            if (this.f.get(fVar) == null) {
                this.f.put(fVar, new CopyOnWriteArraySet());
            }
            fVar.a(DownloadState.INITIALIZE);
            if (!fVar.equals(a(fVar.getUrl()))) {
                c(fVar);
            }
            e eVar = new e(this, fVar);
            this.e.put(fVar, eVar);
            eVar.f();
        }
    }

    public List<f> a() {
        return this.d.b();
    }

    public List<f> b() {
        return this.d.a();
    }

    void c(f fVar) {
        this.d.a(fVar);
    }

    void d(f fVar) {
        this.d.b(fVar);
    }

    public void e(f fVar) {
        if (fVar.i() != DownloadState.FINISHED) {
            Iterator it = g(fVar).iterator();
            while (it.hasNext()) {
                ((b) it.next()).d();
            }
            g(fVar).clear();
        }
        this.e.remove(fVar);
        this.f.remove(fVar);
        this.d.c(fVar);
    }

    public void f(f fVar) {
        b(fVar.d() + "/" + fVar.c());
    }

    f a(String str) {
        return this.d.a(str);
    }

    CopyOnWriteArraySet<b> g(f fVar) {
        if (this.f.get(fVar) != null) {
            return (CopyOnWriteArraySet) this.f.get(fVar);
        }
        return new CopyOnWriteArraySet();
    }

    public void a(f fVar, b bVar) {
        if (this.f.get(fVar) != null) {
            ((CopyOnWriteArraySet) this.f.get(fVar)).add(bVar);
            Log.d("DownloadTaskManager", fVar.c() + " addListener ");
            return;
        }
        this.f.put(fVar, new CopyOnWriteArraySet());
        ((CopyOnWriteArraySet) this.f.get(fVar)).add(bVar);
    }

    public void h(f fVar) {
        this.f.remove(fVar);
    }

    public static boolean b(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public boolean c(String str) {
        if (this.d.a(str) != null) {
            return true;
        }
        return false;
    }
}
