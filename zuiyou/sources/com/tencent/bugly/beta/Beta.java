package com.tencent.bugly.beta;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.a;
import com.tencent.bugly.beta.download.DownloadListener;
import com.tencent.bugly.beta.download.DownloadTask;
import com.tencent.bugly.beta.global.ResBean;
import com.tencent.bugly.beta.global.d;
import com.tencent.bugly.beta.global.e;
import com.tencent.bugly.beta.global.f;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.tencent.bugly.beta.tinker.TinkerApplicationLike;
import com.tencent.bugly.beta.tinker.TinkerManager;
import com.tencent.bugly.beta.tinker.TinkerManager.TinkerPatchResultListener;
import com.tencent.bugly.beta.ui.UILifecycleListener;
import com.tencent.bugly.beta.ui.h;
import com.tencent.bugly.beta.upgrade.BetaGrayStrategy;
import com.tencent.bugly.beta.upgrade.UpgradeListener;
import com.tencent.bugly.beta.upgrade.UpgradeStateListener;
import com.tencent.bugly.beta.upgrade.c;
import com.tencent.bugly.proguard.ac;
import com.tencent.bugly.proguard.am;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ap;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.q;
import com.tencent.bugly.proguard.r;
import com.tencent.bugly.proguard.s;
import com.tencent.bugly.proguard.u;
import com.tencent.bugly.proguard.v;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.y;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Beta extends a {
    public static final String TAG_CANCEL_BUTTON = "beta_cancel_button";
    public static final String TAG_CONFIRM_BUTTON = "beta_confirm_button";
    public static final String TAG_IMG_BANNER = "beta_upgrade_banner";
    public static final String TAG_TIP_MESSAGE = "beta_tip_message";
    public static final String TAG_TITLE = "beta_title";
    public static final String TAG_UPGRADE_FEATURE = "beta_upgrade_feature";
    public static final String TAG_UPGRADE_INFO = "beta_upgrade_info";
    private static DownloadTask a = null;
    public static String appChannel = null;
    public static int appVersionCode = Integer.MIN_VALUE;
    public static String appVersionName = null;
    public static boolean autoCheckUpgrade = true;
    public static boolean autoDownloadOnWifi = false;
    public static boolean autoInit = true;
    public static BetaPatchListener betaPatchListener;
    public static boolean canAutoDownloadPatch = true;
    public static boolean canAutoPatch = true;
    public static List<Class<? extends Activity>> canNotShowUpgradeActs = Collections.synchronizedList(new ArrayList());
    public static boolean canNotifyUserRestart = false;
    public static boolean canShowApkInfo = true;
    public static List<Class<? extends Activity>> canShowUpgradeActs = Collections.synchronizedList(new ArrayList());
    public static int defaultBannerId;
    public static boolean dialogFullScreen = false;
    public static DownloadListener downloadListener;
    public static boolean enableHotfix = false;
    public static boolean enableNotification = true;
    public static long initDelay = 3000;
    public static String initProcessName = null;
    public static Beta instance = new Beta();
    public static int largeIconId;
    public static boolean showInterruptedStrategy = true;
    public static int smallIconId;
    public static List<String> soBlackList = Collections.synchronizedList(new ArrayList());
    public static File storageDir;
    public static String strNetworkTipsCancelBtn = "取消";
    public static String strNetworkTipsConfirmBtn = "继续下载";
    public static String strNetworkTipsMessage = "你已切换到移动网络，是否继续当前下载？";
    public static String strNetworkTipsTitle = "网络提示";
    public static String strNotificationClickToContinue = "继续下载";
    public static String strNotificationClickToInstall = "点击安装";
    public static String strNotificationClickToRetry = "点击重试";
    public static String strNotificationClickToView = "点击查看";
    public static String strNotificationDownloadError = "下载失败";
    public static String strNotificationDownloadSucc = "下载完成";
    public static String strNotificationDownloading = "正在下载";
    public static String strNotificationHaveNewVersion = "有新版本";
    public static String strToastCheckUpgradeError = "检查新版本失败，请稍后重试";
    public static String strToastCheckingUpgrade = "正在检查，请稍候...";
    public static String strToastYourAreTheLatestVersion = "你已经是最新版了";
    public static String strUpgradeDialogCancelBtn = "下次再说";
    public static String strUpgradeDialogContinueBtn = "继续";
    public static String strUpgradeDialogFeatureLabel = "更新说明";
    public static String strUpgradeDialogFileSizeLabel = "包大小";
    public static String strUpgradeDialogInstallBtn = "安装";
    public static String strUpgradeDialogRetryBtn = "重试";
    public static String strUpgradeDialogUpdateTimeLabel = "更新时间";
    public static String strUpgradeDialogUpgradeBtn = "立即更新";
    public static String strUpgradeDialogVersionLabel = "版本";
    public static int tipsDialogLayoutId;
    public static long upgradeCheckPeriod = 0;
    public static int upgradeDialogLayoutId;
    public static UILifecycleListener<UpgradeInfo> upgradeDialogLifecycleListener;
    public static UpgradeListener upgradeListener;
    public static UpgradeStateListener upgradeStateListener;

    public static Beta getInstance() {
        instance.id = 1002;
        instance.version = "1.3.4";
        instance.versionKey = "G10";
        return instance;
    }

    public static void checkUpgrade() {
        checkUpgrade(true, false);
    }

    public static void checkUpgrade(boolean z, boolean z2) {
        try {
            if (TextUtils.isEmpty(e.E.v)) {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    am.a().a(new d(19, Boolean.valueOf(z), Boolean.valueOf(z2)));
                    return;
                }
                synchronized (e.E) {
                    while (TextUtils.isEmpty(e.E.v)) {
                        try {
                            e.E.wait();
                        } catch (InterruptedException e) {
                            an.e("wait error", new Object[0]);
                        }
                    }
                }
            }
            if (!z) {
                if (TextUtils.isEmpty(e.E.v)) {
                    an.e("[beta] BetaModule is uninitialized", new Object[0]);
                } else {
                    BetaGrayStrategy betaGrayStrategy = (BetaGrayStrategy) com.tencent.bugly.beta.global.a.a("st.bch", BetaGrayStrategy.CREATOR);
                    if (betaGrayStrategy == null || betaGrayStrategy.a == null || System.currentTimeMillis() - betaGrayStrategy.e > e.E.c || betaGrayStrategy.a.p == 3) {
                        c.a.a(z, z2, 0);
                    } else {
                        c.a.a(z, z2, 0, null, "");
                    }
                }
            }
            if (!z) {
                return;
            }
            if (TextUtils.isEmpty(e.E.v)) {
                an.e("[beta] BetaModule is uninitialized", new Object[0]);
                if (upgradeStateListener != null) {
                    com.tencent.bugly.beta.utils.e.a(new d(18, upgradeStateListener, Integer.valueOf(-1), Boolean.valueOf(z)));
                    return;
                }
                com.tencent.bugly.beta.utils.e.a(new d(5, strToastCheckUpgradeError));
                return;
            }
            c.a.a(z, z2, 1);
            if (upgradeStateListener != null) {
                com.tencent.bugly.beta.utils.e.a(new d(18, upgradeStateListener, Integer.valueOf(2), Boolean.valueOf(z)));
                return;
            }
            com.tencent.bugly.beta.utils.e.a(new d(5, strToastCheckingUpgrade));
        } catch (Throwable e2) {
            if (!an.b(e2)) {
                e2.printStackTrace();
            }
        }
    }

    public static UpgradeInfo getUpgradeInfo() {
        try {
            c.a.b = (BetaGrayStrategy) com.tencent.bugly.beta.global.a.a("st.bch", BetaGrayStrategy.CREATOR);
            if (c.a.b != null) {
                return new UpgradeInfo(c.a.b.a);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static synchronized void init(Context context, boolean z) {
        synchronized (Beta.class) {
            an.a("Beta init start....", new Object[0]);
            ac a = ac.a();
            int i = instance.id;
            int i2 = e.a + 1;
            e.a = i2;
            a.a(i, i2);
            if (TextUtils.isEmpty(initProcessName)) {
                initProcessName = context.getPackageName();
            }
            an.a("Beta will init at: %s", initProcessName);
            an.a("current process: %s", com.tencent.bugly.crashreport.common.info.a.b().e);
            if (TextUtils.equals(initProcessName, com.tencent.bugly.crashreport.common.info.a.b().e)) {
                e eVar = e.E;
                if (TextUtils.isEmpty(eVar.v)) {
                    XmlResourceParser layout;
                    an.a("current upgrade sdk version:1.3.4", new Object[0]);
                    eVar.D = z;
                    if (upgradeCheckPeriod < 0) {
                        an.d("upgradeCheckPeriod cannot be negative", new Object[0]);
                    } else {
                        eVar.c = upgradeCheckPeriod;
                        an.a("setted upgradeCheckPeriod: %d", Long.valueOf(upgradeCheckPeriod));
                    }
                    if (initDelay < 0) {
                        an.d("initDelay cannot be negative", new Object[0]);
                    } else {
                        eVar.b = initDelay;
                        an.a("setted initDelay: %d", Long.valueOf(initDelay));
                    }
                    if (smallIconId != 0) {
                        try {
                            if (context.getResources().getDrawable(smallIconId) != null) {
                                eVar.f = smallIconId;
                                an.a("setted smallIconId: %d", Integer.valueOf(smallIconId));
                            }
                        } catch (Exception e) {
                            an.e("smallIconId is not available:\n %s", e.toString());
                        }
                    }
                    if (largeIconId != 0) {
                        try {
                            if (context.getResources().getDrawable(largeIconId) != null) {
                                eVar.g = largeIconId;
                                an.a("setted largeIconId: %d", Integer.valueOf(largeIconId));
                            }
                        } catch (Exception e2) {
                            an.e("largeIconId is not available:\n %s", e2.toString());
                        }
                    }
                    if (defaultBannerId != 0) {
                        try {
                            if (context.getResources().getDrawable(defaultBannerId) != null) {
                                eVar.h = defaultBannerId;
                                an.a("setted defaultBannerId: %d", Integer.valueOf(defaultBannerId));
                            }
                        } catch (Exception e22) {
                            an.e("defaultBannerId is not available:\n %s", e22.toString());
                        }
                    }
                    if (upgradeDialogLayoutId != 0) {
                        try {
                            layout = context.getResources().getLayout(upgradeDialogLayoutId);
                            if (layout != null) {
                                eVar.i = upgradeDialogLayoutId;
                                an.a("setted upgradeDialogLayoutId: %d", Integer.valueOf(upgradeDialogLayoutId));
                                layout.close();
                            }
                        } catch (Exception e222) {
                            an.e("upgradeDialogLayoutId is not available:\n %s", e222.toString());
                        }
                    }
                    if (tipsDialogLayoutId != 0) {
                        try {
                            layout = context.getResources().getLayout(tipsDialogLayoutId);
                            if (layout != null) {
                                eVar.j = tipsDialogLayoutId;
                                an.a("setted tipsDialogLayoutId: %d", Integer.valueOf(tipsDialogLayoutId));
                                layout.close();
                            }
                        } catch (Exception e2222) {
                            an.e("tipsDialogLayoutId is not available:\n %s", e2222.toString());
                        }
                    }
                    if (upgradeDialogLifecycleListener != null) {
                        try {
                            eVar.k = upgradeDialogLifecycleListener;
                            an.a("setted upgradeDialogLifecycleListener:%s" + upgradeDialogLifecycleListener, new Object[0]);
                        } catch (Exception e22222) {
                            an.e("upgradeDialogLifecycleListener is not available:\n %", e22222.toString());
                        }
                    }
                    if (!(canShowUpgradeActs == null || canShowUpgradeActs.isEmpty())) {
                        for (Class cls : canShowUpgradeActs) {
                            if (cls != null) {
                                eVar.m.add(cls);
                            }
                        }
                        an.a("setted canShowUpgradeActs: %s", eVar.m);
                    }
                    if (!(canNotShowUpgradeActs == null || canNotShowUpgradeActs.isEmpty())) {
                        for (Class cls2 : canNotShowUpgradeActs) {
                            if (cls2 != null) {
                                eVar.n.add(cls2);
                            }
                        }
                        an.a("setted canNotShowUpgradeActs: %s", eVar.n);
                    }
                    eVar.d = autoCheckUpgrade;
                    String str = "autoCheckUpgrade %s";
                    Object[] objArr = new Object[1];
                    objArr[0] = eVar.d ? "is opened" : "is closed";
                    an.a(str, objArr);
                    eVar.e = showInterruptedStrategy;
                    str = "showInterruptedStrategy %s";
                    objArr = new Object[1];
                    objArr[0] = eVar.e ? "is opened" : "is closed";
                    an.a(str, objArr);
                    str = "isDIY %s";
                    objArr = new Object[1];
                    objArr[0] = upgradeListener != null ? "is opened" : "is closed";
                    an.a(str, objArr);
                    if (storageDir != null) {
                        if (storageDir.exists() || storageDir.mkdirs()) {
                            eVar.l = storageDir;
                            an.a("setted storageDir: %s", storageDir.getAbsolutePath());
                        } else {
                            an.a("storageDir is not exists: %s", storageDir.getAbsolutePath());
                        }
                    }
                    if (eVar.p == null) {
                        eVar.p = s.a;
                    }
                    if (TextUtils.isEmpty(eVar.u)) {
                        eVar.u = com.tencent.bugly.crashreport.common.info.a.b().f();
                    }
                    eVar.R = enableNotification;
                    an.a("enableNotification %s", enableNotification + "");
                    eVar.S = autoDownloadOnWifi;
                    an.a("autoDownloadOnWifi %s", autoDownloadOnWifi + "");
                    eVar.T = canShowApkInfo;
                    an.a("canShowApkInfo %s", canShowApkInfo + "");
                    eVar.U = canAutoPatch;
                    an.a("canAutoPatch %s", canAutoPatch + "");
                    eVar.V = betaPatchListener;
                    eVar.x = appVersionName;
                    eVar.w = appVersionCode;
                    eVar.W = canNotifyUserRestart;
                    an.a("canNotifyUserRestart %s", canNotifyUserRestart + "");
                    eVar.X = canAutoDownloadPatch;
                    an.a("canAutoDownloadPatch %s", canAutoDownloadPatch + "");
                    eVar.Y = enableHotfix;
                    an.a("enableHotfix %s", enableHotfix + "");
                    if (!(soBlackList == null || soBlackList.isEmpty())) {
                        for (String str2 : soBlackList) {
                            if (str2 != null) {
                                eVar.Z.add(str2);
                            }
                        }
                        an.a("setted soBlackList: %s", eVar.Z);
                    }
                    if (appChannel != null) {
                        eVar.P = appChannel;
                        an.a("Beta channel %s", appChannel);
                    }
                    eVar.a(context);
                    ResBean.a = (ResBean) com.tencent.bugly.beta.global.a.a("rb.bch", ResBean.CREATOR);
                    if (ResBean.a == null) {
                        ResBean.a = new ResBean();
                    }
                    c.a.e = upgradeListener;
                    c.a.f = upgradeStateListener;
                    c.a.d = downloadListener;
                    if (!(getStrategyTask() == null || downloadListener == null)) {
                        getStrategyTask().addListener(c.a.d);
                    }
                    if (enableHotfix) {
                        an.a("enableHotfix %s", "1");
                        ap.b("D4", "1");
                        r.a(context);
                    }
                    Resources resources = context.getResources();
                    DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                    Configuration configuration = resources.getConfiguration();
                    configuration.locale = Locale.getDefault();
                    if (configuration.locale.equals(Locale.US) || configuration.locale.equals(Locale.ENGLISH)) {
                        strToastYourAreTheLatestVersion = context.getResources().getString(R.string.strToastYourAreTheLatestVersion);
                        strToastCheckUpgradeError = context.getResources().getString(R.string.strToastCheckUpgradeError);
                        strToastCheckingUpgrade = context.getResources().getString(R.string.strToastCheckingUpgrade);
                        strNotificationDownloading = context.getResources().getString(R.string.strNotificationDownloading);
                        strNotificationClickToView = context.getResources().getString(R.string.strNotificationClickToView);
                        strNotificationClickToInstall = context.getResources().getString(R.string.strNotificationClickToInstall);
                        strNotificationClickToContinue = context.getResources().getString(R.string.strNotificationClickToContinue);
                        strNotificationClickToRetry = context.getResources().getString(R.string.strNotificationClickToRetry);
                        strNotificationDownloadSucc = context.getResources().getString(R.string.strNotificationDownloadSucc);
                        strNotificationDownloadError = context.getResources().getString(R.string.strNotificationDownloadError);
                        strNotificationHaveNewVersion = context.getResources().getString(R.string.strNotificationHaveNewVersion);
                        strNetworkTipsMessage = context.getResources().getString(R.string.strNetworkTipsMessage);
                        strNetworkTipsTitle = context.getResources().getString(R.string.strNetworkTipsTitle);
                        strNetworkTipsConfirmBtn = context.getResources().getString(R.string.strNetworkTipsConfirmBtn);
                        strNetworkTipsCancelBtn = context.getResources().getString(R.string.strNetworkTipsCancelBtn);
                        strUpgradeDialogVersionLabel = context.getResources().getString(R.string.strUpgradeDialogVersionLabel);
                        strUpgradeDialogFileSizeLabel = context.getResources().getString(R.string.strUpgradeDialogFileSizeLabel);
                        strUpgradeDialogUpdateTimeLabel = context.getResources().getString(R.string.strUpgradeDialogUpdateTimeLabel);
                        strUpgradeDialogFeatureLabel = context.getResources().getString(R.string.strUpgradeDialogFeatureLabel);
                        strUpgradeDialogUpgradeBtn = context.getResources().getString(R.string.strUpgradeDialogUpgradeBtn);
                        strUpgradeDialogInstallBtn = context.getResources().getString(R.string.strUpgradeDialogInstallBtn);
                        strUpgradeDialogRetryBtn = context.getResources().getString(R.string.strUpgradeDialogRetryBtn);
                        strUpgradeDialogContinueBtn = context.getResources().getString(R.string.strUpgradeDialogContinueBtn);
                        strUpgradeDialogCancelBtn = context.getResources().getString(R.string.strUpgradeDialogCancelBtn);
                    }
                    resources.updateConfiguration(configuration, displayMetrics);
                    am.a().a(new d(1, new Object[0]), eVar.b);
                    a = ac.a();
                    i = instance.id;
                    i2 = e.a - 1;
                    e.a = i2;
                    a.a(i, i2);
                    an.a("Beta init finished...", new Object[0]);
                } else {
                    an.d("Beta has been initialized [apkMD5 : %s]", eVar.v);
                }
            }
        }
    }

    public synchronized void init(Context context, boolean z, BuglyStrategy buglyStrategy) {
        com.tencent.bugly.crashreport.common.info.a.b().c("G10", "1.3.4");
        if (autoInit) {
            init(context, z);
        }
    }

    public String[] getTables() {
        return new String[]{"dl_1002", "ge_1002", "st_1002"};
    }

    public void onDbUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Throwable th;
        while (i < i2) {
            Cursor query;
            switch (i) {
                case 10:
                    try {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.setLength(0);
                        stringBuilder.append(" CREATE TABLE  IF NOT EXISTS ").append("st_1002").append(" ( ").append("_id").append(" ").append("integer").append(" , ").append("_tp").append(" ").append("text").append(" , ").append("_tm").append(" ").append("int").append(" , ").append("_dt").append(" ").append("blob").append(",primary key(").append("_id").append(",").append("_tp").append(" )) ");
                        an.c("create %s", stringBuilder.toString());
                        sQLiteDatabase.execSQL(stringBuilder.toString());
                    } catch (Throwable th2) {
                        if (!an.b(th2)) {
                            th2.printStackTrace();
                        }
                    }
                    try {
                        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
                        query = sQLiteDatabase2.query("t_pf", null, "_id = 1002", null, null, null, null);
                        if (query != null) {
                            while (query.moveToNext()) {
                                try {
                                    ContentValues contentValues = new ContentValues();
                                    if (query.getLong(query.getColumnIndex("_id")) > 0) {
                                        contentValues.put("_id", Long.valueOf(query.getLong(query.getColumnIndex("_id"))));
                                    }
                                    contentValues.put("_tm", Long.valueOf(query.getLong(query.getColumnIndex("_tm"))));
                                    contentValues.put("_tp", query.getString(query.getColumnIndex("_tp")));
                                    contentValues.put("_dt", query.getBlob(query.getColumnIndex("_dt")));
                                    sQLiteDatabase.replace("st_1002", null, contentValues);
                                } catch (Throwable th3) {
                                    th2 = th3;
                                }
                            }
                            if (query != null) {
                                query.close();
                            }
                        } else if (query != null) {
                            query.close();
                            return;
                        } else {
                            return;
                        }
                    } catch (Throwable th4) {
                        th2 = th4;
                        query = null;
                        break;
                    }
                default:
                    break;
            }
            i++;
        }
        return;
        if (query != null) {
            query.close();
        }
        throw th2;
    }

    public void onDbDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public static void registerDownloadListener(DownloadListener downloadListener) {
        e.E.q = downloadListener;
        if (e.E.q != null && c.a.c != null) {
            c.a.c.addListener(downloadListener);
        }
    }

    public static void unregisterDownloadListener() {
        if (c.a.c != null) {
            c.a.c.removeListener(e.E.q);
        }
        e.E.q = null;
    }

    public static DownloadTask startDownload() {
        if (c.a.h == null || c.a.h.b[0] != c.a.c) {
            c.a.h = new d(13, c.a.c, c.a.b);
        }
        c.a.h.run();
        return c.a.c;
    }

    public static void cancelDownload() {
        if (!(c.a.i != null && c.a.i.b[0] == c.a.c && c.a.i.b[1] == c.a.b && ((Boolean) c.a.i.b[2]).booleanValue() == c.a.g)) {
            c.a.i = new d(14, c.a.c, c.a.b, Boolean.valueOf(c.a.g));
        }
        c.a.i.run();
    }

    public static DownloadTask getStrategyTask() {
        if (a == null) {
            c.a.b = (BetaGrayStrategy) com.tencent.bugly.beta.global.a.a("st.bch", BetaGrayStrategy.CREATOR);
            if (c.a.b != null) {
                a = e.E.p.a(c.a.b.a.f.b, e.E.t.getAbsolutePath(), null, c.a.b.a.f.a);
                c.a.c = a;
            }
        }
        return c.a.c;
    }

    public static synchronized void showUpgradeDialog(String str, int i, String str2, long j, int i2, int i3, String str3, String str4, long j2, String str5, String str6, int i4, DownloadListener downloadListener, Runnable runnable, Runnable runnable2, boolean z) {
        synchronized (Beta.class) {
            Map hashMap = new HashMap();
            hashMap.put("IMG_title", str6);
            hashMap.put("VAL_style", String.valueOf(i4));
            y yVar = new y(str, str2, j, 0, new v(e.E.u, (byte) 1, i3, str3, i2, "", 1, "", str5, "1.3.4", ""), new u(str5, str4, "", j2, ""), (byte) i, 0, 0, null, "", hashMap, null, 1, System.currentTimeMillis(), 1);
            if (!(a == null || a.getDownloadUrl().equals(str4))) {
                a.delete(true);
                a = null;
            }
            if (a == null) {
                a = e.E.p.a(yVar.f.b, e.E.t.getAbsolutePath(), null, yVar.f.a);
            }
            a.addListener(downloadListener);
            h.v.a(yVar, a);
            h.v.r = runnable;
            h.v.s = runnable2;
            f.a.a(e.E.p, yVar.l);
            f fVar;
            Object[] objArr;
            if (z) {
                fVar = f.a;
                objArr = new Object[2];
                objArr[0] = h.v;
                objArr[1] = Boolean.valueOf(yVar.g == (byte) 2);
                fVar.a(new d(2, objArr), 3000);
            } else {
                fVar = f.a;
                objArr = new Object[2];
                objArr[0] = h.v;
                objArr[1] = Boolean.valueOf(yVar.g == (byte) 2);
                fVar.a(new d(2, objArr));
            }
        }
    }

    public static synchronized void onUpgradeReceived(String str, int i, String str2, long j, int i2, int i3, String str3, String str4, long j2, String str5, String str6, int i4, int i5, long j3, String str7, boolean z, boolean z2, int i6, String str8, long j4) {
        synchronized (Beta.class) {
            String str9;
            Map hashMap = new HashMap();
            hashMap.put("IMG_title", str6);
            hashMap.put("VAL_style", String.valueOf(i4));
            v vVar = new v(e.E.u, (byte) 1, i3, str3, i2, "", 1, "", str5, "", "");
            u uVar = new u(str5, str4, "", j2, "");
            y yVar = new y(str, str2, j, 0, vVar, uVar, (byte) i, i5, j3, null, "", hashMap, str7, 1, j4, 1);
            c cVar = c.a;
            if (str8 == null) {
                str9 = "";
            } else {
                str9 = str8;
            }
            cVar.a(z, z2, i6, yVar, str9);
        }
    }

    public static synchronized y getUpgradeStrategy() {
        y yVar;
        synchronized (Beta.class) {
            c.a.b = (BetaGrayStrategy) com.tencent.bugly.beta.global.a.a("st.bch", BetaGrayStrategy.CREATOR);
            try {
                if (c.a.b != null) {
                    yVar = (y) c.a.b.a.clone();
                }
            } catch (Exception e) {
            }
            yVar = null;
        }
        return yVar;
    }

    public static synchronized void installApk(File file) {
        synchronized (Beta.class) {
            try {
                y upgradeStrategy = getUpgradeStrategy();
                if (upgradeStrategy != null && com.tencent.bugly.beta.global.a.a(e.E.s, file, upgradeStrategy.f.a)) {
                    p.a.a(new w("install", System.currentTimeMillis(), (byte) 0, 0, upgradeStrategy.e, upgradeStrategy.m, upgradeStrategy.p, null));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void applyTinkerPatch(Context context, String str) {
        TinkerManager.getInstance().applyPatch(context, str);
    }

    public static void downloadPatch() {
        q.a.b = q.a.a(null);
        try {
            if (q.a.b != null) {
                q.a.a(0, q.a.b.a, true);
            }
        } catch (Exception e) {
        }
    }

    public static void applyDownloadedPatch() {
        if (new File(e.E.H.getAbsolutePath()).exists()) {
            TinkerManager.getInstance().applyPatch(e.E.H.getAbsolutePath(), true);
        } else {
            an.c(Beta.class, "[applyDownloadedPatch] patch file not exist", new Object[0]);
        }
    }

    public static void installTinker() {
        enableHotfix = true;
        installTinker(TinkerApplicationLike.getTinkerPatchApplicationLike());
    }

    public static void installTinker(Object obj) {
        enableHotfix = true;
        TinkerManager.installTinker(obj);
    }

    public static void installTinker(Object obj, Object obj2, Object obj3, Object obj4, TinkerPatchResultListener tinkerPatchResultListener, Object obj5) {
        enableHotfix = true;
        TinkerManager.installTinker(obj, obj2, obj3, obj4, tinkerPatchResultListener, obj5);
    }

    public static void cleanTinkerPatch(boolean z) {
        com.tencent.bugly.beta.global.a.a("IS_PATCH_ROLL_BACK", false);
        TinkerManager.getInstance().cleanPatch(z);
    }

    public static void loadArmLibrary(Context context, String str) {
        TinkerManager.loadArmLibrary(context, str);
    }

    public static void loadArmV7Library(Context context, String str) {
        TinkerManager.loadArmV7Library(context, str);
    }

    public static void loadLibraryFromTinker(Context context, String str, String str2) {
        TinkerManager.loadLibraryFromTinker(context, str, str2);
    }

    public static void loadLibrary(String str) {
        e eVar = e.E;
        if (str != null) {
            try {
                if (!str.isEmpty()) {
                    if (com.tencent.bugly.beta.global.a.b("LoadSoFileResult", true)) {
                        com.tencent.bugly.beta.global.a.a("LoadSoFileResult", false);
                        Object b = com.tencent.bugly.beta.global.a.b(str, "");
                        boolean b2 = com.tencent.bugly.beta.global.a.b("PatchResult", false);
                        if (TextUtils.isEmpty(b) || !b2) {
                            System.loadLibrary(str);
                        } else {
                            TinkerManager.loadLibraryFromTinker(eVar.s, "lib/" + b, str);
                        }
                        com.tencent.bugly.beta.global.a.a("LoadSoFileResult", true);
                        return;
                    }
                    System.loadLibrary(str);
                    com.tencent.bugly.beta.global.a.a("IS_PATCH_ROLL_BACK", true);
                    cleanTinkerPatch(true);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                com.tencent.bugly.beta.global.a.a("LoadSoFileResult", false);
                return;
            }
        }
        an.e("libName is invalid", new Object[0]);
    }

    public static void unInit() {
        if (com.tencent.bugly.beta.global.a.b("IS_PATCH_ROLL_BACK", false)) {
            com.tencent.bugly.beta.global.a.a("IS_PATCH_ROLL_BACK", false);
            TinkerManager.getInstance().cleanPatch(true);
        }
    }
}
