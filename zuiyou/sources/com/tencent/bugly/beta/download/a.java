package com.tencent.bugly.beta.download;

import android.os.Build.VERSION;
import android.util.Log;
import com.tencent.bugly.beta.global.ResBean;
import com.tencent.bugly.beta.global.e;
import com.tencent.bugly.beta.global.f;
import com.tencent.bugly.beta.tinker.TinkerManager;
import com.tencent.bugly.beta.ui.h;
import com.tencent.bugly.beta.upgrade.BetaGrayStrategy;
import com.tencent.bugly.beta.upgrade.c;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.q;
import com.tencent.bugly.proguard.w;
import java.io.File;
import java.util.Map;

public class a implements DownloadListener {
    final int a;
    final Object[] b;

    public a(int i, Object... objArr) {
        this.a = i;
        this.b = objArr;
    }

    public void onReceive(DownloadTask downloadTask) {
        switch (this.a) {
            case 2:
                ((h) this.b[0]).a(downloadTask);
                return;
            default:
                return;
        }
    }

    public void onCompleted(DownloadTask downloadTask) {
        try {
            switch (this.a) {
                case 1:
                    synchronized (this.b[0]) {
                        Map map = (Map) this.b[1];
                        if (map.size() == 0) {
                            return;
                        }
                        int i = 0;
                        for (DownloadTask status : map.values()) {
                            int i2;
                            if (status.getStatus() == 1) {
                                i2 = i + 1;
                            } else {
                                i2 = i;
                            }
                            i = i2;
                        }
                        an.c(i + " has completed", new Object[0]);
                        if (i < map.size()) {
                            return;
                        }
                        for (String str : map.keySet()) {
                            if (((DownloadTask) map.get(str)).getSaveFile() != null && ((DownloadTask) map.get(str)).getSaveFile().exists()) {
                                ResBean.a.a(str, ((DownloadTask) map.get(str)).getSaveFile().getAbsolutePath());
                            }
                        }
                        com.tencent.bugly.beta.global.a.a("rb.bch", ResBean.a);
                        f fVar = (f) this.b[0];
                        fVar.a();
                        fVar.b();
                        return;
                    }
                case 2:
                    ((h) this.b[0]).a(downloadTask);
                    return;
                case 3:
                    c cVar = (c) this.b[0];
                    BetaGrayStrategy betaGrayStrategy = cVar.b;
                    if (betaGrayStrategy != null && betaGrayStrategy.a != null) {
                        if (cVar.f != null) {
                            cVar.f.onDownloadCompleted(cVar.g);
                        }
                        an.a("apk download completed", new Object[0]);
                        p.a.a(new w("download", System.currentTimeMillis(), (byte) 0, downloadTask.getCostTime(), betaGrayStrategy.a.e, betaGrayStrategy.a.m, betaGrayStrategy.a.p, null));
                        Integer num = (Integer) this.b[1];
                        if (com.tencent.bugly.beta.global.a.a(e.E.s, downloadTask.getSaveFile(), betaGrayStrategy.a.f.a)) {
                            this.b[1] = Integer.valueOf(0);
                            p.a.a(new w("install", System.currentTimeMillis(), (byte) 0, 0, betaGrayStrategy.a.e, betaGrayStrategy.a.m, betaGrayStrategy.a.p, null));
                            return;
                        } else if (num.intValue() >= 2) {
                            this.b[1] = Integer.valueOf(0);
                            onFailed(downloadTask, 2080, "file md5 verify fail");
                            downloadTask.delete(true);
                            return;
                        } else if (VERSION.SDK_INT >= 24) {
                            this.b[1] = Integer.valueOf(0);
                            onFailed(downloadTask, 2080, "安装失败，请检查您的App是否兼容7.0设备");
                            downloadTask.delete(true);
                            return;
                        } else {
                            this.b[1] = Integer.valueOf(num.intValue() + 1);
                            downloadTask.delete(true);
                            cVar.c = e.E.p.a(betaGrayStrategy.a.f.b, e.E.t.getAbsolutePath(), null, betaGrayStrategy.a.f.b);
                            h.v.a(betaGrayStrategy.a, cVar.c);
                            cVar.c.addListener(this);
                            BetaReceiver.addTask(cVar.c);
                            cVar.c.download();
                            return;
                        }
                    }
                    return;
                case 4:
                    q qVar = (q) this.b[0];
                    Integer num2 = (Integer) this.b[1];
                    BetaGrayStrategy betaGrayStrategy2 = qVar.b;
                    if (betaGrayStrategy2 != null && betaGrayStrategy2.a != null) {
                        an.a("patch download success !!!", new Object[0]);
                        File saveFile = downloadTask.getSaveFile();
                        if (com.tencent.bugly.beta.global.a.a(saveFile, betaGrayStrategy2.a.f.a, "SHA")) {
                            this.b[1] = Integer.valueOf(0);
                            if (com.tencent.bugly.beta.global.a.a(saveFile, e.E.H)) {
                                an.c("copy " + saveFile.getAbsolutePath() + " to " + e.E.H.getAbsolutePath() + " success!", new Object[0]);
                                if (qVar.c != null) {
                                    an.c("delete temp file", new Object[0]);
                                    qVar.c.delete(true);
                                }
                                com.tencent.bugly.beta.global.a.a("UPLOAD_PATCH_RESULT", false);
                                TinkerManager.getInstance().onDownloadSuccess(e.E.H.getAbsolutePath(), e.E.U);
                                return;
                            }
                            an.a("copy file failed", new Object[0]);
                            TinkerManager.getInstance().onDownloadFailure("copy file failure.");
                            return;
                        } else if (num2.intValue() < 2) {
                            this.b[1] = Integer.valueOf(num2.intValue() + 1);
                            downloadTask.delete(true);
                            qVar.c = e.E.p.a(betaGrayStrategy2.a.f.b, e.E.t.getAbsolutePath(), null, betaGrayStrategy2.a.f.b);
                            qVar.c.setNeededNotify(false);
                            qVar.c.addListener(this);
                            BetaReceiver.addTask(qVar.c);
                            qVar.c.download();
                            return;
                        } else {
                            this.b[1] = Integer.valueOf(0);
                            onFailed(downloadTask, 2080, "file sha1 verify fail");
                            downloadTask.delete(true);
                            TinkerManager.getInstance().onDownloadFailure("retry download patch too many times.");
                            return;
                        }
                    }
                    return;
                default:
                    return;
            }
        } catch (Throwable e) {
            if (!an.b(e)) {
                e.printStackTrace();
            }
        }
        if (!an.b(e)) {
            e.printStackTrace();
        }
    }

    public void onFailed(DownloadTask downloadTask, int i, String str) {
        try {
            switch (this.a) {
                case 1:
                    synchronized (this.b[0]) {
                        f fVar = (f) this.b[0];
                        fVar.a();
                        fVar.b();
                    }
                    return;
                case 2:
                    ((h) this.b[0]).a(downloadTask);
                    return;
                case 3:
                    c cVar = (c) this.b[0];
                    if (downloadTask != null) {
                        p.a.a(new w("download", System.currentTimeMillis(), (byte) 1, downloadTask.getCostTime(), cVar.b.a.e, cVar.b.a.m, cVar.b.a.p, null));
                    }
                    an.e("upgrade failed：(%d)%s", new Object[]{Integer.valueOf(i), str});
                    Log.e(an.b, "download fail, please try later");
                    return;
                case 4:
                    q qVar = (q) this.b[0];
                    if (downloadTask != null) {
                        p.a.a(new w("download", System.currentTimeMillis(), (byte) 1, downloadTask.getCostTime(), qVar.b.a.e, qVar.b.a.m, qVar.b.a.p, null));
                    }
                    an.e("hotfix download failed：(%d)%s", new Object[]{Integer.valueOf(i), str});
                    TinkerManager.getInstance().onDownloadFailure(str);
                    return;
                default:
                    return;
            }
        } catch (Throwable e) {
            if (!an.b(e)) {
                e.printStackTrace();
            }
        }
        if (!an.b(e)) {
            e.printStackTrace();
        }
    }
}
