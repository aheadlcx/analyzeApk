package com.tencent.bugly.beta.global;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Parcelable;
import android.text.TextUtils;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.beta.download.BetaReceiver;
import com.tencent.bugly.beta.download.DownloadListener;
import com.tencent.bugly.beta.download.DownloadTask;
import com.tencent.bugly.beta.ui.BetaActivity;
import com.tencent.bugly.beta.ui.g;
import com.tencent.bugly.beta.ui.h;
import com.tencent.bugly.beta.upgrade.BetaGrayStrategy;
import com.tencent.bugly.beta.upgrade.UpgradeListener;
import com.tencent.bugly.beta.upgrade.UpgradeStateListener;
import com.tencent.bugly.beta.upgrade.a;
import com.tencent.bugly.beta.upgrade.b;
import com.tencent.bugly.beta.utils.e;
import com.tencent.bugly.beta.utils.f;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ap;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class d implements Runnable {
    int a;
    public final Object[] b;

    public d(int i, Object... objArr) {
        this.a = i;
        this.b = objArr;
    }

    public void run() {
        try {
            Parcelable parcelable;
            DownloadTask downloadTask;
            boolean booleanValue;
            List<DownloadListener> list;
            switch (this.a) {
                case 1:
                    try {
                        an.a("Beta async init start...", new Object[0]);
                        e eVar = e.E;
                        synchronized (e.E) {
                            try {
                                eVar.v = ap.a(new File(eVar.z.applicationInfo.publicSourceDir), "MD5");
                            } catch (Exception e) {
                                eVar.v = null;
                            }
                            if (TextUtils.isEmpty(eVar.v)) {
                                eVar.v = "null";
                                an.e("无法获取md5值", new Object[0]);
                            }
                            if (eVar.C) {
                                a.a(eVar.t);
                            }
                            BetaGrayStrategy betaGrayStrategy = (BetaGrayStrategy) a.a("st.bch", BetaGrayStrategy.CREATOR);
                            if (!(betaGrayStrategy == null || betaGrayStrategy.a == null)) {
                                if (TextUtils.equals(betaGrayStrategy.a.e.i.toUpperCase(), eVar.v)) {
                                    if (a.a("st.bch")) {
                                        CharSequence b = a.b("installApkMd5", null);
                                        if (TextUtils.isEmpty(b) || !TextUtils.equals(b, eVar.v)) {
                                            an.a("activated from the other way", new Object[0]);
                                        } else {
                                            p.a.a(new w("active", System.currentTimeMillis(), (byte) 0, 0, null, betaGrayStrategy.a.m, betaGrayStrategy.a.p, null));
                                        }
                                        eVar.A.edit().remove("installApkMd5").apply();
                                        eVar.p.a(betaGrayStrategy.a.f.b, eVar.t.getAbsolutePath(), null, null).delete(true);
                                        a.a(eVar.t);
                                        f.a.a();
                                        an.a("upgrade success", new Object[0]);
                                    } else {
                                        an.d("delete strategy failed", new Object[0]);
                                    }
                                }
                                an.a("[this md5:%s] [strategy md5:%s]", eVar.v, betaGrayStrategy.a.e.i);
                            }
                            e.E.notifyAll();
                        }
                        eVar.s.registerReceiver(new BetaReceiver(), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                        if (eVar.d) {
                            Beta.checkUpgrade(false, false);
                        }
                        if (e.E.F.a.b) {
                            ArrayList arrayList = (ArrayList) p.a.a();
                            if (!(arrayList == null || arrayList.isEmpty())) {
                                b.a.a(new x(arrayList), true);
                            }
                        }
                        an.a("Beta async init end...", new Object[0]);
                        return;
                    } catch (Throwable e2) {
                        if (!an.b(e2)) {
                            e2.printStackTrace();
                            return;
                        }
                        return;
                    }
                case 2:
                    g.a((com.tencent.bugly.beta.ui.b) this.b[0], ((Boolean) this.b[1]).booleanValue());
                    return;
                case 3:
                    parcelable = (BetaGrayStrategy) this.b[0];
                    downloadTask = (DownloadTask) this.b[1];
                    if (!parcelable.d) {
                        parcelable.d = true;
                        parcelable.c = System.currentTimeMillis();
                        a.a("st.bch", parcelable);
                    }
                    if (downloadTask.getStatus() == 2) {
                        return;
                    }
                    if (downloadTask.getStatus() == 1) {
                        p.a.a(new w("pop", System.currentTimeMillis(), (byte) 4, 0, parcelable.a.e, parcelable.a.m, parcelable.a.p, null));
                        return;
                    } else {
                        p.a.a(new w("pop", System.currentTimeMillis(), (byte) 3, 0, parcelable.a.e, parcelable.a.m, parcelable.a.p, null));
                        return;
                    }
                case 4:
                    parcelable = (BetaGrayStrategy) this.b[0];
                    downloadTask = (DownloadTask) this.b[1];
                    booleanValue = ((Boolean) this.b[2]).booleanValue();
                    if (downloadTask.getStatus() != 2) {
                        parcelable.d = false;
                        parcelable.c = System.currentTimeMillis();
                        if (!(booleanValue || parcelable.a.g == (byte) 2)) {
                            parcelable.b++;
                        }
                        a.a("st.bch", parcelable);
                        p.a.a(new w("pop", System.currentTimeMillis(), (byte) 2, 0, parcelable.a.e, parcelable.a.m, parcelable.a.p, null));
                        return;
                    }
                    return;
                case 5:
                    f.a(e.E.s, (String) this.b[0]);
                    return;
                case 6:
                    synchronized (this) {
                        if (!((Boolean) this.b[0]).booleanValue()) {
                            this.b[0] = Boolean.valueOf(true);
                            ((Runnable) this.b[1]).run();
                        }
                    }
                    return;
                case 7:
                    ((h) this.b[0]).c();
                    return;
                case 8:
                    list = (List) this.b[0];
                    if (list != null) {
                        for (DownloadListener downloadListener : list) {
                            if (downloadListener != null) {
                                downloadListener.onCompleted((DownloadTask) this.b[1]);
                            }
                        }
                        return;
                    }
                    return;
                case 9:
                    list = (List) this.b[0];
                    if (list != null) {
                        for (DownloadListener downloadListener2 : list) {
                            if (downloadListener2 != null) {
                                downloadListener2.onReceive((DownloadTask) this.b[1]);
                            }
                        }
                    }
                    if (e.E.V != null && e.E.ac == 3) {
                        downloadTask = (DownloadTask) this.b[1];
                        e.E.V.onDownloadReceived(downloadTask.getSavedLength(), downloadTask.getTotalLength());
                        return;
                    }
                    return;
                case 10:
                    list = (List) this.b[0];
                    if (list != null) {
                        for (DownloadListener downloadListener22 : list) {
                            if (downloadListener22 != null) {
                                downloadListener22.onFailed((DownloadTask) this.b[1], ((Integer) this.b[2]).intValue(), (String) this.b[3]);
                            }
                        }
                        return;
                    }
                    return;
                case 11:
                    g.a((com.tencent.bugly.beta.ui.b) this.b[0], ((Boolean) this.b[1]).booleanValue(), ((Boolean) this.b[2]).booleanValue(), ((Long) this.b[3]).longValue());
                    an.a("BetaAct TYPE_actCanShow checking : " + this.b[0].hashCode(), new Object[0]);
                    return;
                case 12:
                    boolean booleanValue2 = ((Boolean) this.b[0]).booleanValue();
                    a aVar = (a) this.b[1];
                    synchronized (this.b[1]) {
                        if (!booleanValue2) {
                            if (!aVar.d) {
                                this.b[0] = Boolean.valueOf(true);
                                aVar.a(aVar.b, null, 0, 0, false, "request is not finished");
                                aVar.c[1] = Boolean.valueOf(true);
                                aVar.d = false;
                                an.a("request is not finished", new Object[0]);
                            }
                        }
                    }
                    return;
                case 13:
                    DownloadTask downloadTask2 = (DownloadTask) this.b[0];
                    Parcelable parcelable2 = (BetaGrayStrategy) this.b[1];
                    if (downloadTask2 == null || parcelable2 == null) {
                        an.a("strategyTask or betaStrategy is null", new Object[0]);
                        return;
                    }
                    switch (downloadTask2.getStatus()) {
                        case 0:
                        case 1:
                        case 3:
                        case 4:
                        case 5:
                            if (!parcelable2.d) {
                                parcelable2.d = true;
                                parcelable2.c = System.currentTimeMillis();
                                a.a("st.bch", parcelable2);
                            }
                            if (downloadTask2.getStatus() != 2) {
                                if (downloadTask2.getStatus() == 1) {
                                    p.a.a(new w("pop", System.currentTimeMillis(), (byte) 4, 0, parcelable2.a.e, parcelable2.a.m, parcelable2.a.p, null));
                                } else {
                                    p.a.a(new w("pop", System.currentTimeMillis(), (byte) 3, 0, parcelable2.a.e, parcelable2.a.m, parcelable2.a.p, null));
                                }
                            }
                            if (downloadTask2.getStatus() == 1 && a.a(e.E.s, downloadTask2.getSaveFile(), parcelable2.a.f.a)) {
                                p.a.a(new w("install", System.currentTimeMillis(), (byte) 0, 0, parcelable2.a.e, parcelable2.a.m, parcelable2.a.p, null));
                                return;
                            } else {
                                downloadTask2.download();
                                return;
                            }
                        case 2:
                            downloadTask2.stop();
                            return;
                        default:
                            return;
                    }
                case 14:
                    downloadTask = (DownloadTask) this.b[0];
                    parcelable = (BetaGrayStrategy) this.b[1];
                    booleanValue = ((Boolean) this.b[2]).booleanValue();
                    if (downloadTask == null || parcelable == null) {
                        an.a("strategyTask or betaStrategy is null", new Object[0]);
                        return;
                    } else if (downloadTask.getStatus() != 2) {
                        parcelable.d = false;
                        parcelable.c = System.currentTimeMillis();
                        if (!(booleanValue || parcelable.a.g == (byte) 2)) {
                            parcelable.b++;
                        }
                        a.a("st.bch", parcelable);
                        p.a.a(new w("pop", System.currentTimeMillis(), (byte) 2, 0, parcelable.a.e, parcelable.a.m, parcelable.a.p, null));
                        return;
                    } else {
                        return;
                    }
                case 15:
                    if (((com.tencent.bugly.beta.ui.b) this.b[0]).b() || TextUtils.equals(g.a(), BetaActivity.class.getCanonicalName())) {
                        e.a(this, 3000);
                        return;
                    } else {
                        g.a((com.tencent.bugly.beta.ui.b) this.b[0], ((Boolean) this.b[1]).booleanValue(), ((Boolean) this.b[2]).booleanValue(), ((Long) this.b[3]).longValue());
                        return;
                    }
                case 16:
                    BetaGrayStrategy betaGrayStrategy2 = (BetaGrayStrategy) this.b[2];
                    ((UpgradeListener) this.b[0]).onUpgrade(((Integer) this.b[1]).intValue(), betaGrayStrategy2 == null ? null : new UpgradeInfo(betaGrayStrategy2.a), ((Boolean) this.b[3]).booleanValue(), ((Boolean) this.b[4]).booleanValue());
                    return;
                case 17:
                    try {
                        PackageManager packageManager = e.E.s.getPackageManager();
                        if (packageManager != null) {
                            ComponentName componentName = new ComponentName(e.E.s, BetaActivity.class.getName());
                            if (componentName != null && packageManager.getComponentEnabledSetting(componentName) == 2) {
                                packageManager.setComponentEnabledSetting(componentName, 1, 1);
                            }
                        }
                        ((Map) this.b[0]).put((Integer) this.b[1], (com.tencent.bugly.beta.ui.b) this.b[2]);
                        Intent intent = new Intent(e.E.s, BetaActivity.class);
                        intent.putExtra("frag", (Integer) this.b[1]);
                        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
                        e.E.s.startActivity(intent);
                        return;
                    } catch (Throwable e22) {
                        an.e("无法正常弹窗，请在AndroidManifest.xml中添加BetaActivity声明：\n<activity\n    android:name=\"com.tencent.bugly.beta.ui.BetaActivity\"\n    android:theme=\"@android:style/Theme.Translucent\" />", new Object[0]);
                        if (!an.b(e22)) {
                            e22.printStackTrace();
                            return;
                        }
                        return;
                    }
                case 18:
                    UpgradeStateListener upgradeStateListener = (UpgradeStateListener) this.b[0];
                    int intValue = ((Integer) this.b[1]).intValue();
                    booleanValue = ((Boolean) this.b[2]).booleanValue();
                    switch (intValue) {
                        case -1:
                            upgradeStateListener.onUpgradeFailed(booleanValue);
                            return;
                        case 0:
                            upgradeStateListener.onUpgradeSuccess(booleanValue);
                            return;
                        case 1:
                            upgradeStateListener.onUpgradeNoVersion(booleanValue);
                            return;
                        case 2:
                            upgradeStateListener.onUpgrading(booleanValue);
                            return;
                        case 3:
                            upgradeStateListener.onDownloadCompleted(booleanValue);
                            return;
                        default:
                            return;
                    }
                case 19:
                    Beta.checkUpgrade(((Boolean) this.b[0]).booleanValue(), ((Boolean) this.b[1]).booleanValue());
                    return;
                default:
                    return;
            }
        } catch (Throwable e222) {
            if (!an.b(e222)) {
                e222.printStackTrace();
            }
        }
        if (!an.b(e222)) {
            e222.printStackTrace();
        }
    }
}
