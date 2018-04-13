package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.ali.auth.third.core.model.Constants;
import com.tencent.smtt.sdk.TbsDownloadConfig.TbsConfigKey;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.aa;
import com.tencent.smtt.utils.k;
import dalvik.system.DexClassLoader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class aj {
    public static ThreadLocal<Integer> a = new ak();
    static boolean b = false;
    private static aj c = null;
    private static final Lock h = new ReentrantLock();
    private static final Lock i = new ReentrantLock();
    private static Handler k = null;
    private static final Long[][] l;
    private static boolean m = false;
    private int d = 0;
    private FileLock e;
    private FileOutputStream f;
    private boolean g = false;
    private boolean j = false;

    static {
        r0 = new Long[7][];
        r0[0] = new Long[]{Long.valueOf(25413), Long.valueOf(11460320)};
        r0[1] = new Long[]{Long.valueOf(25436), Long.valueOf(12009376)};
        r0[2] = new Long[]{Long.valueOf(25437), Long.valueOf(11489180)};
        r0[3] = new Long[]{Long.valueOf(25438), Long.valueOf(11489180)};
        r0[4] = new Long[]{Long.valueOf(25439), Long.valueOf(12013472)};
        r0[5] = new Long[]{Long.valueOf(25440), Long.valueOf(11489180)};
        r0[6] = new Long[]{Long.valueOf(25442), Long.valueOf(11489180)};
        l = r0;
    }

    private aj() {
        if (k == null) {
            k = new al(this, ah.a().getLooper());
        }
    }

    static synchronized aj a() {
        aj ajVar;
        synchronized (aj.class) {
            if (c == null) {
                synchronized (aj.class) {
                    if (c == null) {
                        c = new aj();
                    }
                }
            }
            ajVar = c;
        }
        return ajVar;
    }

    private void a(int i, String str, Context context) {
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        Throwable th;
        BufferedInputStream bufferedInputStream2;
        BufferedOutputStream bufferedOutputStream2 = null;
        new File(str).delete();
        TbsLog.i("TbsInstaller", "Local tbs apk(" + str + ") is deleted!", true);
        File file = new File(context.getDir("tbs", 0), "core_unzip_tmp");
        if (file != null && file.canRead()) {
            File file2 = new File(file, "tbs.conf");
            Properties properties = new Properties();
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file2));
                try {
                    properties.load(bufferedInputStream);
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedOutputStream2 != null) {
                        bufferedOutputStream2.close();
                    }
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    throw th;
                }
                try {
                    properties.setProperty("tbs_local_installation", Constants.SERVICE_SCOPE_FLAG_VALUE);
                    properties.store(bufferedOutputStream, null);
                    TbsLog.i("TbsInstaller", "TBS_LOCAL_INSTALLATION is set!", true);
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedOutputStream2 = bufferedOutputStream;
                    if (bufferedOutputStream2 != null) {
                        bufferedOutputStream2.close();
                    }
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                bufferedInputStream = null;
                if (bufferedOutputStream2 != null) {
                    bufferedOutputStream2.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                throw th;
            }
        }
    }

    public static void a(Context context) {
        if (!n(context)) {
            if (c(context, "core_unzip_tmp")) {
                TbsCoreLoadStat.getInstance().a(context, 417, new Throwable("TMP_TBS_UNZIP_FOLDER_NAME"));
                TbsLog.e("TbsInstaller", "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_TBS_UNZIP_FOLDER_NAME");
            } else if (c(context, "core_share_backup_tmp")) {
                TbsCoreLoadStat.getInstance().a(context, 417, new Throwable("TMP_BACKUP_TBSCORE_FOLDER_NAME"));
                TbsLog.e("TbsInstaller", "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_BACKUP_TBSCORE_FOLDER_NAME");
            } else if (c(context, "core_copy_tmp")) {
                TbsCoreLoadStat.getInstance().a(context, 417, new Throwable("TMP_TBS_COPY_FOLDER_NAME"));
                TbsLog.e("TbsInstaller", "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_TBS_COPY_FOLDER_NAME");
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.TargetApi(11)
    private void a(android.content.Context r13, android.content.Context r14, int r15) {
        /*
        r12 = this;
        r4 = 1;
        r5 = 0;
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13);
        r1 = -524; // 0xfffffffffffffdf4 float:NaN double:NaN;
        r0.setInstallInterruptCode(r1);
        r0 = r12.b(r14);
        if (r0 == 0) goto L_0x0012;
    L_0x0011:
        return;
    L_0x0012:
        r0 = "TbsInstaller";
        r1 = "TbsInstaller-copyTbsCoreInThread start!";
        com.tencent.smtt.utils.TbsLog.i(r0, r1);
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 11;
        if (r0 < r1) goto L_0x0057;
    L_0x001f:
        r0 = "tbs_preloadx5_check_cfg_file";
        r1 = 4;
        r0 = r14.getSharedPreferences(r0, r1);
    L_0x0026:
        r1 = "tbs_precheck_disable_version";
        r2 = -1;
        r0 = r0.getInt(r1, r2);
        if (r0 != r15) goto L_0x005e;
    L_0x002f:
        r0 = "TbsInstaller";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "TbsInstaller-copyTbsCoreInThread -- version:";
        r1 = r1.append(r2);
        r1 = r1.append(r15);
        r2 = " is disabled by preload_x5_check!";
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.tencent.smtt.utils.TbsLog.e(r0, r1);
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13);
        r1 = -525; // 0xfffffffffffffdf3 float:NaN double:NaN;
        r0.setInstallInterruptCode(r1);
        goto L_0x0011;
    L_0x0057:
        r0 = "tbs_preloadx5_check_cfg_file";
        r0 = r14.getSharedPreferences(r0, r5);
        goto L_0x0026;
    L_0x005e:
        r0 = r12.m(r14);
        if (r0 != 0) goto L_0x006e;
    L_0x0064:
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13);
        r1 = -526; // 0xfffffffffffffdf2 float:NaN double:NaN;
        r0.setInstallInterruptCode(r1);
        goto L_0x0011;
    L_0x006e:
        r0 = i;
        r0 = r0.tryLock();
        if (r0 == 0) goto L_0x04a3;
    L_0x0076:
        r0 = h;
        r0.lock();
        r0 = com.tencent.smtt.sdk.ae.a(r14);	 Catch:{ Exception -> 0x02aa }
        r1 = "copy_core_ver";
        r1 = r0.c(r1);	 Catch:{ Exception -> 0x02aa }
        r0 = com.tencent.smtt.sdk.ae.a(r14);	 Catch:{ Exception -> 0x02aa }
        r2 = "copy_status";
        r0 = r0.b(r2);	 Catch:{ Exception -> 0x02aa }
        if (r1 != r15) goto L_0x00b0;
    L_0x0091:
        r0 = com.tencent.smtt.sdk.QbSdk.j;	 Catch:{ Exception -> 0x02aa }
        r1 = 220; // 0xdc float:3.08E-43 double:1.087E-321;
        r0.onInstallFinish(r1);	 Catch:{ Exception -> 0x02aa }
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13);	 Catch:{ Exception -> 0x02aa }
        r1 = -528; // 0xfffffffffffffdf0 float:NaN double:NaN;
        r0.setInstallInterruptCode(r1);	 Catch:{ Exception -> 0x02aa }
        r0 = h;
        r0.unlock();
        r0 = i;
        r0.unlock();
    L_0x00ab:
        r12.b();
        goto L_0x0011;
    L_0x00b0:
        r2 = r12.d(r14);	 Catch:{ Exception -> 0x02aa }
        r3 = "TbsInstaller";
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x02aa }
        r6.<init>();	 Catch:{ Exception -> 0x02aa }
        r7 = "TbsInstaller-copyTbsCoreInThread tbsCoreInstalledVer=";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x02aa }
        r6 = r6.append(r2);	 Catch:{ Exception -> 0x02aa }
        r6 = r6.toString();	 Catch:{ Exception -> 0x02aa }
        com.tencent.smtt.utils.TbsLog.i(r3, r6);	 Catch:{ Exception -> 0x02aa }
        if (r2 != r15) goto L_0x00e9;
    L_0x00ce:
        r0 = com.tencent.smtt.sdk.QbSdk.j;	 Catch:{ Exception -> 0x02aa }
        r1 = 220; // 0xdc float:3.08E-43 double:1.087E-321;
        r0.onInstallFinish(r1);	 Catch:{ Exception -> 0x02aa }
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13);	 Catch:{ Exception -> 0x02aa }
        r1 = -528; // 0xfffffffffffffdf0 float:NaN double:NaN;
        r0.setInstallInterruptCode(r1);	 Catch:{ Exception -> 0x02aa }
        r0 = h;
        r0.unlock();
        r0 = i;
        r0.unlock();
        goto L_0x00ab;
    L_0x00e9:
        r3 = com.tencent.smtt.sdk.ae.a(r14);	 Catch:{ Exception -> 0x02aa }
        r3 = r3.b();	 Catch:{ Exception -> 0x02aa }
        if (r3 <= 0) goto L_0x00f5;
    L_0x00f3:
        if (r15 > r3) goto L_0x00f9;
    L_0x00f5:
        if (r1 <= 0) goto L_0x00fc;
    L_0x00f7:
        if (r15 <= r1) goto L_0x00fc;
    L_0x00f9:
        r12.g(r14);	 Catch:{ Exception -> 0x02aa }
    L_0x00fc:
        r1 = 3;
        if (r0 != r1) goto L_0x0114;
    L_0x00ff:
        if (r2 <= 0) goto L_0x0114;
    L_0x0101:
        if (r15 > r2) goto L_0x0108;
    L_0x0103:
        r1 = 88888888; // 0x54c5638 float:9.60787E-36 double:4.3916946E-316;
        if (r15 != r1) goto L_0x0114;
    L_0x0108:
        r0 = -1;
        r12.g(r14);	 Catch:{ Exception -> 0x02aa }
        r1 = "TbsInstaller";
        r2 = "TbsInstaller-copyTbsCoreInThread -- update TBS.....";
        r3 = 1;
        com.tencent.smtt.utils.TbsLog.i(r1, r2, r3);	 Catch:{ Exception -> 0x02aa }
    L_0x0114:
        r1 = com.tencent.smtt.utils.k.b(r14);	 Catch:{ Exception -> 0x02aa }
        if (r1 != 0) goto L_0x0161;
    L_0x011a:
        r0 = com.tencent.smtt.utils.aa.a();	 Catch:{ Exception -> 0x02aa }
        r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14);	 Catch:{ Exception -> 0x02aa }
        r2 = r2.getDownloadMinFreeSpace();	 Catch:{ Exception -> 0x02aa }
        r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13);	 Catch:{ Exception -> 0x02aa }
        r5 = -529; // 0xfffffffffffffdef float:NaN double:NaN;
        r4.setInstallInterruptCode(r5);	 Catch:{ Exception -> 0x02aa }
        r4 = com.tencent.smtt.sdk.TbsLogReport.a(r14);	 Catch:{ Exception -> 0x02aa }
        r5 = 210; // 0xd2 float:2.94E-43 double:1.04E-321;
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x02aa }
        r6.<init>();	 Catch:{ Exception -> 0x02aa }
        r7 = "rom is not enough when copying tbs core! curAvailROM=";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x02aa }
        r0 = r6.append(r0);	 Catch:{ Exception -> 0x02aa }
        r1 = ",minReqRom=";
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x02aa }
        r0 = r0.append(r2);	 Catch:{ Exception -> 0x02aa }
        r0 = r0.toString();	 Catch:{ Exception -> 0x02aa }
        r4.a(r5, r0);	 Catch:{ Exception -> 0x02aa }
        r0 = h;
        r0.unlock();
        r0 = i;
        r0.unlock();
        goto L_0x00ab;
    L_0x0161:
        if (r0 <= 0) goto L_0x016f;
    L_0x0163:
        r0 = h;
        r0.unlock();
        r0 = i;
        r0.unlock();
        goto L_0x00ab;
    L_0x016f:
        if (r0 != 0) goto L_0x01aa;
    L_0x0171:
        r0 = com.tencent.smtt.sdk.ae.a(r14);	 Catch:{ Exception -> 0x02aa }
        r1 = "copy_retry_num";
        r0 = r0.c(r1);	 Catch:{ Exception -> 0x02aa }
        r1 = 10;
        if (r0 <= r1) goto L_0x019f;
    L_0x017f:
        r0 = com.tencent.smtt.sdk.TbsLogReport.a(r14);	 Catch:{ Exception -> 0x02aa }
        r1 = 211; // 0xd3 float:2.96E-43 double:1.042E-321;
        r2 = "exceed copy retry num!";
        r0.a(r1, r2);	 Catch:{ Exception -> 0x02aa }
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13);	 Catch:{ Exception -> 0x02aa }
        r1 = -530; // 0xfffffffffffffdee float:NaN double:NaN;
        r0.setInstallInterruptCode(r1);	 Catch:{ Exception -> 0x02aa }
        r0 = h;
        r0.unlock();
        r0 = i;
        r0.unlock();
        goto L_0x00ab;
    L_0x019f:
        r1 = com.tencent.smtt.sdk.ae.a(r14);	 Catch:{ Exception -> 0x02aa }
        r2 = "copy_retry_num";
        r0 = r0 + 1;
        r1.a(r2, r0);	 Catch:{ Exception -> 0x02aa }
    L_0x01aa:
        r0 = r12.h(r13);	 Catch:{ Exception -> 0x02aa }
        r6 = r12.l(r14);	 Catch:{ Exception -> 0x02aa }
        if (r0 == 0) goto L_0x0475;
    L_0x01b4:
        if (r6 == 0) goto L_0x0475;
    L_0x01b6:
        r1 = com.tencent.smtt.sdk.ae.a(r14);	 Catch:{ Exception -> 0x02aa }
        r2 = 0;
        r1.a(r15, r2);	 Catch:{ Exception -> 0x02aa }
        r1 = new com.tencent.smtt.utils.z;	 Catch:{ Exception -> 0x02aa }
        r1.<init>();	 Catch:{ Exception -> 0x02aa }
        r1.a(r0);	 Catch:{ Exception -> 0x02aa }
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x02aa }
        r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13);	 Catch:{ Exception -> 0x02aa }
        r8 = -551; // 0xfffffffffffffdd9 float:NaN double:NaN;
        r7.setInstallInterruptCode(r8);	 Catch:{ Exception -> 0x02aa }
        r7 = com.tencent.smtt.utils.k.b(r0, r6);	 Catch:{ Exception -> 0x02aa }
        r8 = "TbsInstaller";
        r9 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x02aa }
        r9.<init>();	 Catch:{ Exception -> 0x02aa }
        r10 = "TbsInstaller-copyTbsCoreInThread time=";
        r9 = r9.append(r10);	 Catch:{ Exception -> 0x02aa }
        r10 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x02aa }
        r2 = r10 - r2;
        r2 = r9.append(r2);	 Catch:{ Exception -> 0x02aa }
        r2 = r2.toString();	 Catch:{ Exception -> 0x02aa }
        com.tencent.smtt.utils.TbsLog.i(r8, r2);	 Catch:{ Exception -> 0x02aa }
        if (r7 == 0) goto L_0x044d;
    L_0x01f7:
        r1.b(r0);	 Catch:{ Exception -> 0x02aa }
        r0 = r1.a();	 Catch:{ Exception -> 0x02aa }
        if (r0 != 0) goto L_0x022b;
    L_0x0200:
        r0 = "TbsInstaller";
        r1 = "TbsInstaller-copyTbsCoreInThread copy-verify fail!";
        com.tencent.smtt.utils.TbsLog.i(r0, r1);	 Catch:{ Exception -> 0x02aa }
        r0 = 1;
        com.tencent.smtt.utils.k.a(r6, r0);	 Catch:{ Exception -> 0x02aa }
        r0 = com.tencent.smtt.sdk.TbsLogReport.a(r14);	 Catch:{ Exception -> 0x02aa }
        r1 = 213; // 0xd5 float:2.98E-43 double:1.05E-321;
        r2 = "TbsCopy-Verify fail after copying tbs core!";
        r0.a(r1, r2);	 Catch:{ Exception -> 0x02aa }
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13);	 Catch:{ Exception -> 0x02aa }
        r1 = -531; // 0xfffffffffffffded float:NaN double:NaN;
        r0.setInstallInterruptCode(r1);	 Catch:{ Exception -> 0x02aa }
        r0 = h;
        r0.unlock();
        r0 = i;
        r0.unlock();
        goto L_0x00ab;
    L_0x022b:
        r3 = 0;
        r2 = 0;
        r0 = new java.io.File;	 Catch:{ Exception -> 0x0296, all -> 0x02a3 }
        r1 = "1";
        r0.<init>(r6, r1);	 Catch:{ Exception -> 0x0296, all -> 0x02a3 }
        r1 = new java.util.Properties;	 Catch:{ Exception -> 0x0296, all -> 0x02a3 }
        r1.<init>();	 Catch:{ Exception -> 0x0296, all -> 0x02a3 }
        if (r0 == 0) goto L_0x0293;
    L_0x023b:
        r2 = r0.exists();	 Catch:{ Exception -> 0x04c7, all -> 0x02a3 }
        if (r2 == 0) goto L_0x0293;
    L_0x0241:
        if (r1 == 0) goto L_0x0293;
    L_0x0243:
        r7 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x04c7, all -> 0x02a3 }
        r7.<init>(r0);	 Catch:{ Exception -> 0x04c7, all -> 0x02a3 }
        r2 = new java.io.BufferedInputStream;	 Catch:{ Exception -> 0x04c7, all -> 0x02a3 }
        r2.<init>(r7);	 Catch:{ Exception -> 0x04c7, all -> 0x02a3 }
        r1.load(r2);	 Catch:{ Exception -> 0x04cb }
        r0 = r4;
    L_0x0251:
        if (r2 == 0) goto L_0x0256;
    L_0x0253:
        r2.close();	 Catch:{ IOException -> 0x04bd }
    L_0x0256:
        r2 = r0;
    L_0x0257:
        if (r2 == 0) goto L_0x033e;
    L_0x0259:
        r3 = r6.listFiles();	 Catch:{ Exception -> 0x02aa }
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13);	 Catch:{ Exception -> 0x02aa }
        r7 = -552; // 0xfffffffffffffdd8 float:NaN double:NaN;
        r0.setInstallInterruptCode(r7);	 Catch:{ Exception -> 0x02aa }
        r0 = r5;
    L_0x0267:
        r7 = r3.length;	 Catch:{ Exception -> 0x02aa }
        if (r0 >= r7) goto L_0x033e;
    L_0x026a:
        r7 = r3[r0];	 Catch:{ Exception -> 0x02aa }
        r8 = "1";
        r9 = r7.getName();	 Catch:{ Exception -> 0x02aa }
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x02aa }
        if (r8 != 0) goto L_0x0290;
    L_0x0278:
        r8 = r7.getName();	 Catch:{ Exception -> 0x02aa }
        r9 = ".dex";
        r8 = r8.endsWith(r9);	 Catch:{ Exception -> 0x02aa }
        if (r8 != 0) goto L_0x0290;
    L_0x0284:
        r8 = "tbs.conf";
        r9 = r7.getName();	 Catch:{ Exception -> 0x02aa }
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x02aa }
        if (r8 == 0) goto L_0x02cd;
    L_0x0290:
        r0 = r0 + 1;
        goto L_0x0267;
    L_0x0293:
        r2 = r3;
        r0 = r5;
        goto L_0x0251;
    L_0x0296:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
    L_0x0299:
        r0.printStackTrace();	 Catch:{ all -> 0x04c3 }
        if (r2 == 0) goto L_0x02a1;
    L_0x029e:
        r2.close();	 Catch:{ IOException -> 0x04b7 }
    L_0x02a1:
        r2 = r4;
        goto L_0x0257;
    L_0x02a3:
        r0 = move-exception;
    L_0x02a4:
        if (r3 == 0) goto L_0x02a9;
    L_0x02a6:
        r3.close();	 Catch:{ IOException -> 0x04b1 }
    L_0x02a9:
        throw r0;	 Catch:{ Exception -> 0x02aa }
    L_0x02aa:
        r0 = move-exception;
        r1 = com.tencent.smtt.sdk.TbsLogReport.a(r14);	 Catch:{ all -> 0x030c }
        r2 = 215; // 0xd7 float:3.01E-43 double:1.06E-321;
        r0 = r0.toString();	 Catch:{ all -> 0x030c }
        r1.a(r2, r0);	 Catch:{ all -> 0x030c }
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13);	 Catch:{ all -> 0x030c }
        r1 = -537; // 0xfffffffffffffde7 float:NaN double:NaN;
        r0.setInstallInterruptCode(r1);	 Catch:{ all -> 0x030c }
        r0 = h;
        r0.unlock();
        r0 = i;
        r0.unlock();
        goto L_0x00ab;
    L_0x02cd:
        r8 = com.tencent.smtt.utils.a.a(r7);	 Catch:{ Exception -> 0x02aa }
        r9 = r7.getName();	 Catch:{ Exception -> 0x02aa }
        r10 = "";
        r9 = r1.getProperty(r9, r10);	 Catch:{ Exception -> 0x02aa }
        r10 = "";
        r10 = r9.equals(r10);	 Catch:{ Exception -> 0x02aa }
        if (r10 != 0) goto L_0x031b;
    L_0x02e3:
        r8 = r8.equals(r9);	 Catch:{ Exception -> 0x02aa }
        if (r8 == 0) goto L_0x031b;
    L_0x02e9:
        r8 = "TbsInstaller";
        r9 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x02aa }
        r9.<init>();	 Catch:{ Exception -> 0x02aa }
        r10 = "md5_check_success for (";
        r9 = r9.append(r10);	 Catch:{ Exception -> 0x02aa }
        r7 = r7.getName();	 Catch:{ Exception -> 0x02aa }
        r7 = r9.append(r7);	 Catch:{ Exception -> 0x02aa }
        r9 = ")";
        r7 = r7.append(r9);	 Catch:{ Exception -> 0x02aa }
        r7 = r7.toString();	 Catch:{ Exception -> 0x02aa }
        com.tencent.smtt.utils.TbsLog.i(r8, r7);	 Catch:{ Exception -> 0x02aa }
        goto L_0x0290;
    L_0x030c:
        r0 = move-exception;
        r1 = h;
        r1.unlock();
        r1 = i;
        r1.unlock();
        r12.b();
        throw r0;
    L_0x031b:
        r0 = "TbsInstaller";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x02aa }
        r1.<init>();	 Catch:{ Exception -> 0x02aa }
        r3 = "md5_check_failure for (";
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x02aa }
        r3 = r7.getName();	 Catch:{ Exception -> 0x02aa }
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x02aa }
        r3 = ")";
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x02aa }
        r1 = r1.toString();	 Catch:{ Exception -> 0x02aa }
        com.tencent.smtt.utils.TbsLog.e(r0, r1);	 Catch:{ Exception -> 0x02aa }
        r4 = r5;
    L_0x033e:
        r0 = "TbsInstaller";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x02aa }
        r1.<init>();	 Catch:{ Exception -> 0x02aa }
        r3 = "copyTbsCoreInThread - md5_check_success:";
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x02aa }
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x02aa }
        r1 = r1.toString();	 Catch:{ Exception -> 0x02aa }
        com.tencent.smtt.utils.TbsLog.i(r0, r1);	 Catch:{ Exception -> 0x02aa }
        if (r2 == 0) goto L_0x0385;
    L_0x0358:
        if (r4 != 0) goto L_0x0385;
    L_0x035a:
        r0 = "TbsInstaller";
        r1 = "copyTbsCoreInThread - md5 incorrect -> delete destTmpDir!";
        com.tencent.smtt.utils.TbsLog.e(r0, r1);	 Catch:{ Exception -> 0x02aa }
        r0 = 1;
        com.tencent.smtt.utils.k.a(r6, r0);	 Catch:{ Exception -> 0x02aa }
        r0 = com.tencent.smtt.sdk.TbsLogReport.a(r14);	 Catch:{ Exception -> 0x02aa }
        r1 = 213; // 0xd5 float:2.98E-43 double:1.05E-321;
        r2 = "TbsCopy-Verify md5 fail after copying tbs core!";
        r0.a(r1, r2);	 Catch:{ Exception -> 0x02aa }
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13);	 Catch:{ Exception -> 0x02aa }
        r1 = -532; // 0xfffffffffffffdec float:NaN double:NaN;
        r0.setInstallInterruptCode(r1);	 Catch:{ Exception -> 0x02aa }
        r0 = h;
        r0.unlock();
        r0 = i;
        r0.unlock();
        goto L_0x00ab;
    L_0x0385:
        r0 = "TbsInstaller";
        r1 = "TbsInstaller-copyTbsCoreInThread success!";
        com.tencent.smtt.utils.TbsLog.i(r0, r1);	 Catch:{ Exception -> 0x02aa }
        r0 = 1;
        r12.f(r14, r0);	 Catch:{ Exception -> 0x02aa }
        r1 = com.tencent.smtt.sdk.ac.b(r13);	 Catch:{ Exception -> 0x02aa }
        if (r1 == 0) goto L_0x03ac;
    L_0x0396:
        r0 = r1.exists();	 Catch:{ Exception -> 0x02aa }
        if (r0 == 0) goto L_0x03ac;
    L_0x039c:
        r2 = new java.io.File;	 Catch:{ Exception -> 0x02aa }
        r0 = com.tencent.smtt.sdk.TbsDownloader.getOverSea(r14);	 Catch:{ Exception -> 0x02aa }
        if (r0 == 0) goto L_0x0418;
    L_0x03a4:
        r0 = "x5.oversea.tbs.org";
    L_0x03a6:
        r2.<init>(r1, r0);	 Catch:{ Exception -> 0x02aa }
        com.tencent.smtt.sdk.ac.a(r2, r14);	 Catch:{ Exception -> 0x02aa }
    L_0x03ac:
        r0 = com.tencent.smtt.sdk.ae.a(r14);	 Catch:{ Exception -> 0x02aa }
        r1 = 1;
        r0.a(r15, r1);	 Catch:{ Exception -> 0x02aa }
        r0 = r12.j;	 Catch:{ Exception -> 0x02aa }
        if (r0 == 0) goto L_0x041b;
    L_0x03b8:
        r0 = com.tencent.smtt.sdk.TbsLogReport.a(r14);	 Catch:{ Exception -> 0x02aa }
        r1 = 220; // 0xdc float:3.08E-43 double:1.087E-321;
        r2 = "continueInstallWithout core success";
        r0.a(r1, r2);	 Catch:{ Exception -> 0x02aa }
    L_0x03c3:
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13);	 Catch:{ Exception -> 0x02aa }
        r1 = -533; // 0xfffffffffffffdeb float:NaN double:NaN;
        r0.setInstallInterruptCode(r1);	 Catch:{ Exception -> 0x02aa }
        r0 = "TbsInstaller";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x02aa }
        r1.<init>();	 Catch:{ Exception -> 0x02aa }
        r2 = "TbsInstaller-copyTbsCoreInThread success -- version:";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x02aa }
        r1 = r1.append(r15);	 Catch:{ Exception -> 0x02aa }
        r1 = r1.toString();	 Catch:{ Exception -> 0x02aa }
        com.tencent.smtt.utils.TbsLog.i(r0, r1);	 Catch:{ Exception -> 0x02aa }
        r0 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x02aa }
        r1 = 11;
        if (r0 < r1) goto L_0x0427;
    L_0x03ea:
        r0 = "tbs_preloadx5_check_cfg_file";
        r1 = 4;
        r0 = r14.getSharedPreferences(r0, r1);	 Catch:{ Exception -> 0x02aa }
    L_0x03f1:
        r0 = r0.edit();	 Catch:{ Throwable -> 0x042f }
        r1 = "tbs_preload_x5_counter";
        r2 = 0;
        r0.putInt(r1, r2);	 Catch:{ Throwable -> 0x042f }
        r1 = "tbs_preload_x5_recorder";
        r2 = 0;
        r0.putInt(r1, r2);	 Catch:{ Throwable -> 0x042f }
        r1 = "tbs_preload_x5_version";
        r0.putInt(r1, r15);	 Catch:{ Throwable -> 0x042f }
        r0.commit();	 Catch:{ Throwable -> 0x042f }
    L_0x0409:
        com.tencent.smtt.utils.aa.a(r14);	 Catch:{ Exception -> 0x02aa }
    L_0x040c:
        r0 = h;
        r0.unlock();
        r0 = i;
        r0.unlock();
        goto L_0x00ab;
    L_0x0418:
        r0 = "x5.tbs.org";
        goto L_0x03a6;
    L_0x041b:
        r0 = com.tencent.smtt.sdk.TbsLogReport.a(r14);	 Catch:{ Exception -> 0x02aa }
        r1 = 220; // 0xdc float:3.08E-43 double:1.087E-321;
        r2 = "success";
        r0.a(r1, r2);	 Catch:{ Exception -> 0x02aa }
        goto L_0x03c3;
    L_0x0427:
        r0 = "tbs_preloadx5_check_cfg_file";
        r1 = 0;
        r0 = r14.getSharedPreferences(r0, r1);	 Catch:{ Exception -> 0x02aa }
        goto L_0x03f1;
    L_0x042f:
        r0 = move-exception;
        r1 = "TbsInstaller";
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x02aa }
        r2.<init>();	 Catch:{ Exception -> 0x02aa }
        r3 = "Init tbs_preload_x5_counter#2 exception:";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x02aa }
        r0 = android.util.Log.getStackTraceString(r0);	 Catch:{ Exception -> 0x02aa }
        r0 = r2.append(r0);	 Catch:{ Exception -> 0x02aa }
        r0 = r0.toString();	 Catch:{ Exception -> 0x02aa }
        com.tencent.smtt.utils.TbsLog.e(r1, r0);	 Catch:{ Exception -> 0x02aa }
        goto L_0x0409;
    L_0x044d:
        r0 = "TbsInstaller";
        r1 = "TbsInstaller-copyTbsCoreInThread fail!";
        com.tencent.smtt.utils.TbsLog.i(r0, r1);	 Catch:{ Exception -> 0x02aa }
        r0 = com.tencent.smtt.sdk.ae.a(r14);	 Catch:{ Exception -> 0x02aa }
        r1 = 2;
        r0.a(r15, r1);	 Catch:{ Exception -> 0x02aa }
        r0 = 0;
        com.tencent.smtt.utils.k.a(r6, r0);	 Catch:{ Exception -> 0x02aa }
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13);	 Catch:{ Exception -> 0x02aa }
        r1 = -534; // 0xfffffffffffffdea float:NaN double:NaN;
        r0.setInstallInterruptCode(r1);	 Catch:{ Exception -> 0x02aa }
        r0 = com.tencent.smtt.sdk.TbsLogReport.a(r14);	 Catch:{ Exception -> 0x02aa }
        r1 = 212; // 0xd4 float:2.97E-43 double:1.047E-321;
        r2 = "copy fail!";
        r0.a(r1, r2);	 Catch:{ Exception -> 0x02aa }
        goto L_0x040c;
    L_0x0475:
        if (r0 != 0) goto L_0x048b;
    L_0x0477:
        r0 = com.tencent.smtt.sdk.TbsLogReport.a(r14);	 Catch:{ Exception -> 0x02aa }
        r1 = 213; // 0xd5 float:2.98E-43 double:1.05E-321;
        r2 = "src-dir is null when copying tbs core!";
        r0.a(r1, r2);	 Catch:{ Exception -> 0x02aa }
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13);	 Catch:{ Exception -> 0x02aa }
        r1 = -535; // 0xfffffffffffffde9 float:NaN double:NaN;
        r0.setInstallInterruptCode(r1);	 Catch:{ Exception -> 0x02aa }
    L_0x048b:
        if (r6 != 0) goto L_0x040c;
    L_0x048d:
        r0 = com.tencent.smtt.sdk.TbsLogReport.a(r14);	 Catch:{ Exception -> 0x02aa }
        r1 = 214; // 0xd6 float:3.0E-43 double:1.057E-321;
        r2 = "dst-dir is null when copying tbs core!";
        r0.a(r1, r2);	 Catch:{ Exception -> 0x02aa }
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13);	 Catch:{ Exception -> 0x02aa }
        r1 = -536; // 0xfffffffffffffde8 float:NaN double:NaN;
        r0.setInstallInterruptCode(r1);	 Catch:{ Exception -> 0x02aa }
        goto L_0x040c;
    L_0x04a3:
        r12.b();
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13);
        r1 = -538; // 0xfffffffffffffde6 float:NaN double:NaN;
        r0.setInstallInterruptCode(r1);
        goto L_0x0011;
    L_0x04b1:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ Exception -> 0x02aa }
        goto L_0x02a9;
    L_0x04b7:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ Exception -> 0x02aa }
        goto L_0x02a1;
    L_0x04bd:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ Exception -> 0x02aa }
        goto L_0x0256;
    L_0x04c3:
        r0 = move-exception;
        r3 = r2;
        goto L_0x02a4;
    L_0x04c7:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0299;
    L_0x04cb:
        r0 = move-exception;
        goto L_0x0299;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.aj.a(android.content.Context, android.content.Context, int):void");
    }

    private boolean a(Context context, File file) {
        boolean z = true;
        TbsLog.i("TbsInstaller", "TbsInstaller-unzipTbs start");
        if (k.c(file)) {
            File file2;
            try {
                file2 = new File(context.getDir("tbs", 0), "core_unzip_tmp");
                if (file2 != null && file2.exists()) {
                    k.b(file2);
                }
            } catch (Throwable th) {
                TbsLog.e("TbsInstaller", "TbsInstaller-unzipTbs -- delete unzip folder if exists exception" + Log.getStackTraceString(th));
            }
            file2 = k(context);
            if (file2 == null) {
                TbsLogReport.a(context).a(205, "tmp unzip dir is null!");
                TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-521);
                return false;
            }
            try {
                k.a(file2);
                boolean a = k.a(file, file2);
                if (a) {
                    f(context, true);
                } else {
                    k.b(file2);
                    TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-522);
                    TbsLog.e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#1! exist:" + file2.exists());
                }
                TbsLog.i("TbsInstaller", "TbsInstaller-unzipTbs done");
                return a;
            } catch (Throwable th2) {
                TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-523);
                TbsLogReport.a(context).a(206, th2);
                if (file2 == null || !file2.exists()) {
                    z = false;
                }
                if (z && file2 != null) {
                    k.b(file2);
                    TbsLog.e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exist:" + file2.exists());
                }
            } catch (Throwable th22) {
                TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-523);
                TbsLogReport.a(context).a(207, th22);
                if (file2 == null || !file2.exists()) {
                    z = false;
                }
                if (z && file2 != null) {
                    k.b(file2);
                    TbsLog.e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exist:" + file2.exists());
                }
            } catch (Throwable th3) {
                TbsLog.e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exception:" + Log.getStackTraceString(th3));
            }
        } else {
            TbsLogReport.a(context).a(204, "apk is invalid!");
            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-520);
            return false;
        }
        String str = "TbsInstaller";
        String str2 = "TbsInstaller-unzipTbs done";
        TbsLog.i(str, str2);
        return false;
        str = "TbsInstaller";
        str2 = "TbsInstaller-unzipTbs done";
        TbsLog.i(str, str2);
        return false;
    }

    @TargetApi(11)
    private void b(Context context, String str, int i) {
        int i2 = 200;
        int i3 = 0;
        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-501);
        if (b(context)) {
            TbsLog.i("TbsInstaller", "isTbsLocalInstalled --> no installation!", true);
            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-502);
            return;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread tbsApkPath=" + str);
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread tbsCoreTargetVer=" + i);
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread currentProcessId=" + Process.myPid());
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread currentThreadName=" + Thread.currentThread().getName());
        if ((VERSION.SDK_INT >= 11 ? context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 4) : context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 0)).getInt("tbs_precheck_disable_version", -1) == i) {
            TbsLog.e("TbsInstaller", "TbsInstaller-installTbsCoreInThread -- version:" + i + " is disabled by preload_x5_check!");
            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-503);
        } else if (!k.b(context)) {
            long a = aa.a();
            long downloadMinFreeSpace = TbsDownloadConfig.getInstance(context).getDownloadMinFreeSpace();
            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-504);
            TbsLogReport.a(context).a(210, "rom is not enough when installing tbs core! curAvailROM=" + a + ",minReqRom=" + downloadMinFreeSpace);
        } else if (m(context)) {
            boolean tryLock = i.tryLock();
            TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread locked =" + tryLock);
            if (tryLock) {
                int i4;
                boolean z;
                TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-507);
                h.lock();
                int c = ae.a(context).c("copy_core_ver");
                int b = ae.a(context).b();
                TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread tbsCoreCopyVer =" + c);
                TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread tbsCoreInstallVer =" + b);
                TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread tbsCoreTargetVer =" + i);
                if ((b > 0 && i > b) || (c > 0 && i > c)) {
                    g(context);
                }
                c = ae.a(context).c();
                b = d(context);
                TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread installStatus1=" + c);
                TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread tbsCoreInstalledVer=" + b);
                if (c < 0 || c >= 2) {
                    if (c == 3 && b > 0 && (i > b || i == 88888888)) {
                        c = -1;
                        g(context);
                        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread -- update TBS.....", true);
                    }
                    i4 = c;
                    z = false;
                } else {
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread -- retry.....", true);
                    i4 = c;
                    z = true;
                }
                TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-508);
                TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread installStatus2=" + i4);
                if (i4 < 1) {
                    String d;
                    TbsLog.i("TbsInstaller", "STEP 2/2 begin installation.....", true);
                    TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-509);
                    if (z) {
                        c = ae.a(context).c("unzip_retry_num");
                        if (c > 10) {
                            TbsLogReport.a(context).a(201, "exceed unzip retry num!");
                            t(context);
                            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-510);
                            h.unlock();
                            i.unlock();
                            b();
                            return;
                        }
                        ae.a(context).b(c + 1);
                    }
                    if (str == null) {
                        d = ae.a(context).d("install_apk_path");
                        if (d == null) {
                            TbsLogReport.a(context).a(202, "apk path is null!");
                            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-511);
                            h.unlock();
                            i.unlock();
                            b();
                            return;
                        }
                    }
                    d = str;
                    try {
                        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread apkPath =" + d);
                        int b2 = b(context, d);
                        if (b2 == 0) {
                            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-512);
                            TbsLogReport.a(context).a(203, "apk version is 0!");
                            h.unlock();
                            i.unlock();
                            b();
                            return;
                        }
                        ae.a(context).a("install_apk_path", d);
                        ae.a(context).b(b2, 0);
                        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-548);
                        if (a(context, new File(d))) {
                            if (z) {
                                c = ae.a(context).b("unlzma_status");
                                if (c > 5) {
                                    TbsLogReport.a(context).a(223, "exceed unlzma retry num!");
                                    TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-553);
                                    t(context);
                                    ac.c(context);
                                    TbsDownloadConfig.getInstance(context).a.put(TbsConfigKey.KEY_NEEDDOWNLOAD, Boolean.valueOf(true));
                                    TbsDownloadConfig.getInstance(context).a.put(TbsConfigKey.KEY_FULL_PACKAGE, Boolean.valueOf(true));
                                    TbsDownloadConfig.getInstance(context).commit();
                                    h.unlock();
                                    i.unlock();
                                    b();
                                    return;
                                }
                                ae.a(context).d(c + 1);
                            }
                            TbsLog.i("TbsInstaller", "unlzma begin");
                            b = TbsDownloadConfig.getInstance().mPreferences.getInt(TbsConfigKey.KEY_RESPONSECODE, 0);
                            if (d(context) != 0) {
                                Object a2 = QbSdk.a(context, "can_unlzma", null);
                                tryLock = (a2 == null || !(a2 instanceof Boolean)) ? false : ((Boolean) a2).booleanValue();
                                if (tryLock) {
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("responseCode", b);
                                    bundle.putString("unzip_temp_path", k(context).getAbsolutePath());
                                    a2 = QbSdk.a(context, "unlzma", bundle);
                                    if (a2 == null) {
                                        TbsLog.i("TbsInstaller", "unlzma return null");
                                        TbsLogReport.a(context).a(222, "unlzma is null");
                                    } else if (a2 instanceof Boolean) {
                                        if (((Boolean) a2).booleanValue()) {
                                            TbsLog.i("TbsInstaller", "unlzma success");
                                            tryLock = true;
                                        } else {
                                            TbsLog.i("TbsInstaller", "unlzma return false");
                                            TbsLogReport.a(context).a(222, "unlzma return false");
                                            tryLock = false;
                                        }
                                        boolean z2 = tryLock;
                                    } else if (a2 instanceof Bundle) {
                                        i3 = 1;
                                    } else if (a2 instanceof Throwable) {
                                        TbsLog.i("TbsInstaller", "unlzma failure because Throwable" + Log.getStackTraceString((Throwable) a2));
                                        TbsLogReport.a(context).a(222, (Throwable) a2);
                                    }
                                    if (i3 == 0) {
                                        h.unlock();
                                        i.unlock();
                                        b();
                                        return;
                                    }
                                }
                            }
                            TbsLog.i("TbsInstaller", "unlzma finished");
                            ae.a(context).b(b2, 1);
                            i3 = b2;
                        } else {
                            TbsLogReport.a(context).a(207, "unzipTbsApk failed");
                            h.unlock();
                            i.unlock();
                            b();
                            return;
                        }
                    } catch (Throwable th) {
                        h.unlock();
                        i.unlock();
                        b();
                    }
                }
                if (i4 < 2) {
                    if (z) {
                        c = ae.a(context).c("dexopt_retry_num");
                        if (c > 10) {
                            TbsLogReport.a(context).a(208, "exceed dexopt retry num!");
                            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-514);
                            t(context);
                            h.unlock();
                            i.unlock();
                            b();
                            return;
                        }
                        ae.a(context).a(c + 1);
                    }
                    TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-549);
                    if (c(context, 0)) {
                        ae.a(context).b(i3, 2);
                        TbsLog.i("TbsInstaller", "STEP 2/2 installation completed! you can restart!", true);
                        TbsLog.i("TbsInstaller", "STEP 2/2 installation completed! you can restart! version:" + i);
                        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-516);
                        Editor edit = (VERSION.SDK_INT >= 11 ? context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 4) : context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 0)).edit();
                        edit.putInt("tbs_preload_x5_counter", 0);
                        edit.putInt("tbs_preload_x5_recorder", 0);
                        edit.putInt("tbs_preload_x5_version", i);
                        edit.commit();
                        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-517);
                        if (i == 88888888) {
                            a(i, str, context);
                        }
                        if (this.j) {
                            TbsLogReport a3 = TbsLogReport.a(context);
                            if (ae.a(context).d() == 1) {
                                i2 = 221;
                            }
                            a3.a(i2, "continueInstallWithout core success");
                        } else {
                            TbsLogReport.a(context).a(ae.a(context).d() == 1 ? 221 : 200, "success");
                        }
                    } else {
                        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-515);
                        h.unlock();
                        i.unlock();
                        b();
                        return;
                    }
                } else if (i4 == 2) {
                    QbSdk.j.onInstallFinish(200);
                }
                h.unlock();
                i.unlock();
                b();
                return;
            }
            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-519);
            b();
        } else {
            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-505);
        }
    }

    private boolean b(Context context, File file) {
        try {
            File[] listFiles = file.listFiles(new ap(this));
            int length = listFiles.length;
            if (VERSION.SDK_INT < 16 && context.getPackageName() != null && context.getPackageName().equalsIgnoreCase(TbsConfig.APP_DEMO)) {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                }
            }
            ClassLoader classLoader = context.getClassLoader();
            for (int i = 0; i < length; i++) {
                TbsLog.i("TbsInstaller", "jarFile: " + listFiles[i].getAbsolutePath());
                DexClassLoader dexClassLoader = new DexClassLoader(listFiles[i].getAbsolutePath(), file.getAbsolutePath(), null, classLoader);
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLogReport.a(context).a(209, e2.toString());
            TbsLog.i("TbsInstaller", "TbsInstaller-doTbsDexOpt done");
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean c(android.content.Context r9, int r10) {
        /*
        r8 = this;
        r2 = 1;
        r3 = 0;
        r0 = "TbsInstaller";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r4 = "TbsInstaller-doTbsDexOpt start - dirMode: ";
        r1 = r1.append(r4);
        r1 = r1.append(r10);
        r1 = r1.toString();
        com.tencent.smtt.utils.TbsLog.i(r0, r1);
        switch(r10) {
            case 0: goto L_0x0037;
            case 1: goto L_0x0075;
            case 2: goto L_0x007a;
            default: goto L_0x001d;
        };
    L_0x001d:
        r0 = "TbsInstaller";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x008f }
        r1.<init>();	 Catch:{ Exception -> 0x008f }
        r4 = "doDexoptOrDexoat mode error: ";
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x008f }
        r1 = r1.append(r10);	 Catch:{ Exception -> 0x008f }
        r1 = r1.toString();	 Catch:{ Exception -> 0x008f }
        com.tencent.smtt.utils.TbsLog.e(r0, r1);	 Catch:{ Exception -> 0x008f }
        r2 = r3;
    L_0x0036:
        return r2;
    L_0x0037:
        r0 = r8.k(r9);	 Catch:{ Exception -> 0x008f }
    L_0x003b:
        r1 = "java.vm.version";
        r1 = java.lang.System.getProperty(r1);	 Catch:{ Throwable -> 0x0081 }
        if (r1 == 0) goto L_0x007f;
    L_0x0043:
        r4 = "2";
        r1 = r1.startsWith(r4);	 Catch:{ Throwable -> 0x0081 }
        if (r1 == 0) goto L_0x007f;
    L_0x004b:
        r1 = r2;
    L_0x004c:
        r4 = r1;
    L_0x004d:
        r1 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x008f }
        r5 = 23;
        if (r1 != r5) goto L_0x008d;
    L_0x0053:
        r1 = r2;
    L_0x0054:
        r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r9);	 Catch:{ Exception -> 0x008f }
        r5 = r5.mPreferences;	 Catch:{ Exception -> 0x008f }
        r6 = "tbs_stop_preoat";
        r7 = 0;
        r5 = r5.getBoolean(r6, r7);	 Catch:{ Exception -> 0x008f }
        if (r4 == 0) goto L_0x0068;
    L_0x0063:
        if (r1 == 0) goto L_0x0068;
    L_0x0065:
        if (r5 != 0) goto L_0x0068;
    L_0x0067:
        r3 = r2;
    L_0x0068:
        if (r3 == 0) goto L_0x0070;
    L_0x006a:
        r1 = r8.c(r9, r0);	 Catch:{ Exception -> 0x008f }
        if (r1 != 0) goto L_0x0036;
    L_0x0070:
        r2 = r8.b(r9, r0);	 Catch:{ Exception -> 0x008f }
        goto L_0x0036;
    L_0x0075:
        r0 = r8.l(r9);	 Catch:{ Exception -> 0x008f }
        goto L_0x003b;
    L_0x007a:
        r0 = r8.h(r9);	 Catch:{ Exception -> 0x008f }
        goto L_0x003b;
    L_0x007f:
        r1 = r3;
        goto L_0x004c;
    L_0x0081:
        r1 = move-exception;
        r4 = com.tencent.smtt.sdk.TbsLogReport.a(r9);	 Catch:{ Exception -> 0x008f }
        r5 = 226; // 0xe2 float:3.17E-43 double:1.117E-321;
        r4.a(r5, r1);	 Catch:{ Exception -> 0x008f }
        r4 = r3;
        goto L_0x004d;
    L_0x008d:
        r1 = r3;
        goto L_0x0054;
    L_0x008f:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = com.tencent.smtt.sdk.TbsLogReport.a(r9);
        r3 = 209; // 0xd1 float:2.93E-43 double:1.033E-321;
        r0 = r0.toString();
        r1.a(r3, r0);
        r0 = "TbsInstaller";
        r1 = "TbsInstaller-doTbsDexOpt done";
        com.tencent.smtt.utils.TbsLog.i(r0, r1);
        goto L_0x0036;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.aj.c(android.content.Context, int):boolean");
    }

    private boolean c(Context context, File file) {
        try {
            File file2 = new File(file, "tbs_sdk_extension_dex.jar");
            File file3 = new File(file, "tbs_sdk_extension_dex.dex");
            DexClassLoader dexClassLoader = new DexClassLoader(file2.getAbsolutePath(), file.getAbsolutePath(), null, context.getClassLoader());
            Object a = c.a(context, file3.getAbsolutePath());
            if (TextUtils.isEmpty(a)) {
                TbsLogReport.a(context).a(226, "can not find oat command");
                return false;
            }
            for (File file4 : file.listFiles(new aq(this))) {
                String substring = file4.getName().substring(0, file4.getName().length() - 4);
                Runtime.getRuntime().exec("/system/bin/dex2oat " + a.replaceAll("tbs_sdk_extension_dex", substring) + " --dex-location=" + a().h(context) + File.separator + substring + ".jar").waitFor();
            }
            return true;
        } catch (Throwable e) {
            e.printStackTrace();
            TbsLogReport.a(context).a(226, e);
            return false;
        }
    }

    private static boolean c(Context context, String str) {
        File file = new File(context.getDir("tbs", 0), str);
        if (file == null || !file.exists()) {
            return false;
        }
        File file2 = new File(file, "tbs.conf");
        return file2 != null && file2.exists();
    }

    private synchronized boolean c(Context context, boolean z) {
        Throwable th;
        boolean z2 = false;
        boolean z3 = true;
        synchronized (this) {
            TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy");
            try {
                if (m(context)) {
                    boolean tryLock = h.tryLock();
                    TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy Locked =" + tryLock);
                    if (tryLock) {
                        int b = ae.a(context).b("copy_status");
                        int a = a(false, context);
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy copyStatus =" + b);
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy tbsCoreInstalledVer =" + a);
                        if (b == 1) {
                            if (a == 0) {
                                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy tbsCoreInstalledVer = 0", true);
                                p(context);
                            } else if (z) {
                                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy tbsCoreInstalledVer != 0", true);
                                p(context);
                            }
                            h.unlock();
                            z2 = z3;
                        }
                        z3 = false;
                        try {
                            h.unlock();
                            z2 = z3;
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            z2 = z3;
                            th = th3;
                            TbsLogReport.a(context).a(215, th.toString());
                            QbSdk.a(context, "TbsInstaller::enableTbsCoreFromCopy exception:" + Log.getStackTraceString(th));
                            return z2;
                        }
                    }
                    b();
                }
            } catch (Throwable th4) {
                th = th4;
                TbsLogReport.a(context).a(215, th.toString());
                QbSdk.a(context, "TbsInstaller::enableTbsCoreFromCopy exception:" + Log.getStackTraceString(th));
                return z2;
            }
        }
        return z2;
    }

    private Context d(Context context, int i) {
        TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreHostContext tbsCoreTargetVer=" + i);
        if (i <= 0) {
            return null;
        }
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        int i2 = 0;
        while (i2 < coreProviderAppList.length) {
            if (!context.getPackageName().equalsIgnoreCase(coreProviderAppList[i2]) && d(context, coreProviderAppList[i2])) {
                Context a = a(context, coreProviderAppList[i2]);
                if (a == null) {
                    continue;
                } else if (c(a)) {
                    int d = d(a);
                    TbsLog.i("TbsInstaller", "TbsInstaller-getTbsCoreHostContext hostTbsCoreVer=" + d);
                    if (d != 0 && d == i) {
                        TbsLog.i("TbsInstaller", "TbsInstaller-getTbsCoreHostContext targetApp=" + coreProviderAppList[i2]);
                        return a;
                    }
                } else {
                    TbsLog.e("TbsInstaller", "TbsInstaller--getTbsCoreHostContext " + coreProviderAppList[i2] + " illegal signature go on next");
                }
            }
            i2++;
        }
        return null;
    }

    private boolean d(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (NameNotFoundException e) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    private synchronized boolean d(Context context, boolean z) {
        Exception exception;
        boolean z2 = true;
        boolean z3 = false;
        synchronized (this) {
            TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip canRenameTmpDir =" + z);
            try {
                if (m(context)) {
                    boolean tryLock = h.tryLock();
                    TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip locked=" + tryLock);
                    if (tryLock) {
                        int c = ae.a(context).c();
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip installStatus=" + c);
                        int a = a(false, context);
                        if (c == 2) {
                            if (a == 0) {
                                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip tbsCoreInstalledVer = 0", false);
                                o(context);
                            } else if (z) {
                                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip tbsCoreInstalledVer != 0", false);
                                o(context);
                            }
                            h.unlock();
                            z3 = z2;
                        }
                        z2 = false;
                        try {
                            h.unlock();
                            z3 = z2;
                        } catch (Exception e) {
                            Exception exception2 = e;
                            z3 = z2;
                            exception = exception2;
                            QbSdk.a(context, "TbsInstaller::enableTbsCoreFromUnzip Exception: " + exception);
                            exception.printStackTrace();
                            return z3;
                        }
                    }
                    b();
                }
            } catch (Exception e2) {
                exception = e2;
                QbSdk.a(context, "TbsInstaller::enableTbsCoreFromUnzip Exception: " + exception);
                exception.printStackTrace();
                return z3;
            } catch (Throwable th) {
                h.unlock();
            }
        }
        return z3;
    }

    private synchronized boolean e(Context context, boolean z) {
        return false;
    }

    private void f(Context context, boolean z) {
        if (context == null) {
            TbsLogReport.a(context).a(225, "setTmpFolderCoreToRead context is null");
            return;
        }
        try {
            File file = new File(context.getDir("tbs", 0), "tmp_folder_core_to_read.conf");
            if (!z) {
                k.b(file);
            } else if (file == null || !file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            TbsLogReport.a(context).a(225, "setTmpFolderCoreToRead Exception message is " + e.getMessage() + " Exception cause is " + e.getCause());
        }
    }

    static File j(Context context) {
        File file = new File(context.getDir("tbs", 0), "core_private");
        return file != null ? (file.isDirectory() || file.mkdir()) ? file : null : null;
    }

    private static boolean n(Context context) {
        if (context == null) {
            return true;
        }
        try {
            return new File(context.getDir("tbs", 0), "tmp_folder_core_to_read.conf").exists();
        } catch (Exception e) {
            return true;
        }
    }

    private void o(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromUnzip");
        if (bi.b().a() == null && bi.b().a(context) == null) {
            TbsLog.e("TbsInstaller", "generateNewTbsCoreFromUnzip -- failed to get rename fileLock#2!");
            return;
        }
        try {
            q(context);
            r(context);
            if (!TbsShareManager.isThirdPartyApp(context)) {
                TbsShareManager.a(context);
            }
            ae.a(context).a(0);
            ae.a(context).b(0);
            ae.a(context).d(0);
            ae.a(context).a("incrupdate_retry_num", 0);
            ae.a(context).b(0, 3);
            ae.a(context).a("");
            ae.a(context).c(-1);
            if (TbsShareManager.isThirdPartyApp(context)) {
                TbsShareManager.writeCoreInfoForThirdPartyApp(context, f(context), true);
            }
            a.set(Integer.valueOf(0));
        } catch (Throwable th) {
            th.printStackTrace();
            TbsLogReport.a(context).a(219, "exception when renameing from unzip:" + th.toString());
            TbsLog.e("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromUnzip Exception", true);
        }
    }

    private void p(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromCopy");
        if (bi.b().a() == null && bi.b().a(context) == null) {
            TbsLog.e("TbsInstaller", "generateNewTbsCoreFromCopy -- failed to get rename fileLock#2!");
            return;
        }
        try {
            q(context);
            s(context);
            TbsShareManager.a(context);
            ae.a(context).a(0, 3);
            a.set(Integer.valueOf(0));
        } catch (Exception e) {
            e.printStackTrace();
            TbsLogReport.a(context).a(219, "exception when renameing from copy:" + e.toString());
        }
    }

    private void q(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--deleteOldCore");
        k.a(h(context), false);
    }

    private void r(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--renameShareDir");
        File k = k(context);
        File h = h(context);
        if (k != null && h != null) {
            k.renameTo(h);
            f(context, false);
        }
    }

    private void s(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--renameTbsCoreCopyDir");
        File l = l(context);
        File h = h(context);
        if (l != null && h != null) {
            l.renameTo(h);
            f(context, false);
        }
    }

    private void t(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--clearNewTbsCore");
        File k = k(context);
        if (k != null) {
            k.a(k, false);
        }
        ae.a(context).b(0, 5);
        ae.a(context).c(-1);
        QbSdk.a(context, "TbsInstaller::clearNewTbsCore forceSysWebViewInner!");
    }

    public int a(boolean z, Context context) {
        if (z || ((Integer) a.get()).intValue() <= 0) {
            a.set(Integer.valueOf(d(context)));
        }
        return ((Integer) a.get()).intValue();
    }

    Context a(Context context, String str) {
        try {
            return context.createPackageContext(str, 2);
        } catch (Exception e) {
            return null;
        }
    }

    void a(Context context, Bundle bundle) {
        if (bundle != null && context != null) {
            Object obj = new Object[]{context, bundle};
            Message message = new Message();
            message.what = 3;
            message.obj = obj;
            k.sendMessage(message);
        }
    }

    void a(Context context, String str, int i) {
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore tbsApkPath=" + str);
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore tbsCoreTargetVer=" + i);
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore currentProcessId=" + Process.myPid());
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore currentThreadName=" + Thread.currentThread().getName());
        Object obj = new Object[]{context, str, Integer.valueOf(i)};
        Message message = new Message();
        message.what = 1;
        message.obj = obj;
        k.sendMessage(message);
    }

    void a(Context context, boolean z) {
        boolean z2 = false;
        if (z) {
            this.j = true;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessId=" + Process.myPid());
        StringBuilder append = new StringBuilder().append("TbsInstaller-continueInstallTbsCore currentThreadName=");
        String name = Thread.currentThread().getName();
        TbsLog.i("TbsInstaller", append.append(name).toString());
        if (m(context)) {
            int c;
            int b;
            int c2;
            int tryLock = h.tryLock();
            if (tryLock != 0) {
                try {
                    c = ae.a(context).c();
                    b = ae.a(context).b();
                    name = ae.a(context).d("install_apk_path");
                    c2 = ae.a(context).c("copy_core_ver");
                    tryLock = ae.a(context).b("copy_status");
                } finally {
                    z2 = h;
                    z2.unlock();
                }
            } else {
                tryLock = -1;
                name = null;
                b = 0;
                c = -1;
                c2 = 0;
            }
            b();
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore installStatus=" + c);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsCoreInstallVer=" + b);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsApkPath=" + name);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsCoreCopyVer=" + c2);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore copyStatus=" + tryLock);
            if (TbsShareManager.isThirdPartyApp(context)) {
                b(context, TbsShareManager.a(context, z2));
                return;
            }
            int i = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsConfigKey.KEY_RESPONSECODE, z2);
            if (i == 1 || i == 2 || i == 4) {
                z2 = true;
            }
            if (!z2) {
                Bundle bundle = new Bundle();
                bundle.putInt("operation", 10001);
                a(context, bundle);
            }
            if (c > -1 && c < 2) {
                a(context, name, b);
            }
            if (tryLock == 0) {
                a(context, c2);
            }
        }
    }

    boolean a(Context context, int i) {
        if (TbsDownloader.getOverSea(context)) {
            return false;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore targetTbsCoreVer=" + i);
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore currentProcessId=" + Process.myPid());
        TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore currentThreadName=" + Thread.currentThread().getName());
        if (d(context, i) != null) {
            Object obj = new Object[]{d(context, i), context, Integer.valueOf(i)};
            Message message = new Message();
            message.what = 2;
            message.obj = obj;
            k.sendMessage(message);
            return true;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller--installLocalTbsCore copy from null");
        return false;
    }

    public synchronized boolean a(Context context, Context context2) {
        TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp");
        if (!m) {
            m = true;
            new am(this, context2, context).start();
        }
        return true;
    }

    public boolean a(Context context, File[] fileArr) {
        return false;
    }

    int b(Context context, String str) {
        PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 0);
        return packageArchiveInfo != null ? packageArchiveInfo.versionCode : 0;
    }

    File b(Context context, Context context2) {
        File file = new File(context2.getDir("tbs", 0), "core_share");
        return file != null ? (file.isDirectory() || ((context != null && TbsShareManager.isThirdPartyApp(context)) || file.mkdir())) ? file : null : null;
    }

    synchronized void b() {
        int i = this.d;
        this.d = i - 1;
        if (i > 1 || !this.g) {
            TbsLog.i("TbsInstaller", "releaseTbsInstallingFileLock with skip");
        } else {
            TbsLog.i("TbsInstaller", "releaseTbsInstallingFileLock without skip");
            k.a(this.e, this.f);
            this.g = false;
        }
    }

    void b(Context context, int i) {
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreForThirdPartyApp");
        if (i > 0) {
            int d = d(context);
            if (d != i) {
                Context d2 = TbsShareManager.d(context);
                if (d2 != null) {
                    TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp hostContext != null");
                    a(context, d2);
                } else if (d <= 0) {
                    TbsLog.i("TbsInstaller", "TbsInstaller--installTbsCoreForThirdPartyApp hostContext == null");
                    QbSdk.a(context, "TbsInstaller::installTbsCoreForThirdPartyApp forceSysWebViewInner #2");
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void b(android.content.Context r11, android.os.Bundle r12) {
        /*
        r10 = this;
        r9 = 217; // 0xd9 float:3.04E-43 double:1.07E-321;
        r8 = -546; // 0xfffffffffffffdde float:NaN double:NaN;
        r2 = 2;
        r3 = 1;
        r5 = 0;
        r0 = r10.b(r11);
        if (r0 == 0) goto L_0x0017;
    L_0x000d:
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11);
        r1 = -539; // 0xfffffffffffffde5 float:NaN double:NaN;
        r0.setInstallInterruptCode(r1);
    L_0x0016:
        return;
    L_0x0017:
        r0 = "TbsInstaller";
        r1 = "TbsInstaller-installLocalTesCoreExInThread";
        com.tencent.smtt.utils.TbsLog.i(r0, r1);
        if (r12 == 0) goto L_0x0016;
    L_0x0020:
        if (r11 == 0) goto L_0x0016;
    L_0x0022:
        r0 = com.tencent.smtt.utils.k.b(r11);
        if (r0 != 0) goto L_0x0064;
    L_0x0028:
        r0 = com.tencent.smtt.utils.aa.a();
        r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11);
        r2 = r2.getDownloadMinFreeSpace();
        r4 = com.tencent.smtt.sdk.TbsLogReport.a(r11);
        r5 = 210; // 0xd2 float:2.94E-43 double:1.04E-321;
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = "rom is not enough when patching tbs core! curAvailROM=";
        r6 = r6.append(r7);
        r0 = r6.append(r0);
        r1 = ",minReqRom=";
        r0 = r0.append(r1);
        r0 = r0.append(r2);
        r0 = r0.toString();
        r4.a(r5, r0);
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11);
        r1 = -540; // 0xfffffffffffffde4 float:NaN double:NaN;
        r0.setInstallInterruptCode(r1);
        goto L_0x0016;
    L_0x0064:
        r0 = r10.m(r11);
        if (r0 != 0) goto L_0x0074;
    L_0x006a:
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11);
        r1 = -541; // 0xfffffffffffffde3 float:NaN double:NaN;
        r0.setInstallInterruptCode(r1);
        goto L_0x0016;
    L_0x0074:
        r0 = i;
        r0 = r0.tryLock();
        r1 = "TbsInstaller";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r6 = "TbsInstaller-installLocalTesCoreExInThread locked=";
        r4 = r4.append(r6);
        r4 = r4.append(r0);
        r4 = r4.toString();
        com.tencent.smtt.utils.TbsLog.i(r1, r4);
        if (r0 == 0) goto L_0x027f;
    L_0x0094:
        r1 = 0;
        r0 = 1;
        com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r0);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r0 = r10.d(r11);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        if (r0 <= 0) goto L_0x00a9;
    L_0x009f:
        r0 = com.tencent.smtt.sdk.ae.a(r11);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r0 = r0.d();	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        if (r0 != r3) goto L_0x00c1;
    L_0x00a9:
        r0 = 0;
        com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r0);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r0 = i;
        r0.unlock();
        r10.b();
        r0 = "TbsInstaller";
        r1 = "TbsInstaller-installLocalTesCoreExInThread PATCH_NONEEDPATCH";
        com.tencent.smtt.utils.TbsLog.i(r0, r1);
    L_0x00bc:
        com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r5);
        goto L_0x0016;
    L_0x00c1:
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r0 = r0.mPreferences;	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r4 = "tbs_responsecode";
        r6 = 0;
        r0 = r0.getInt(r4, r6);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        if (r0 == r3) goto L_0x00d5;
    L_0x00d0:
        if (r0 == r2) goto L_0x00d5;
    L_0x00d2:
        r4 = 4;
        if (r0 != r4) goto L_0x0149;
    L_0x00d5:
        r0 = r3;
    L_0x00d6:
        if (r0 != 0) goto L_0x032b;
    L_0x00d8:
        r0 = com.tencent.smtt.sdk.ae.a(r11);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r4 = "incrupdate_retry_num";
        r0 = r0.c(r4);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r4 = 5;
        if (r0 <= r4) goto L_0x014b;
    L_0x00e5:
        r0 = "TbsInstaller";
        r4 = "TbsInstaller-installLocalTesCoreExInThread exceed incrupdate num";
        com.tencent.smtt.utils.TbsLog.i(r0, r4);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r0 = "old_apk_location";
        r0 = r12.getString(r0);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r4 = "new_apk_location";
        r4 = r12.getString(r4);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r6 = "diff_file_location";
        r6 = r12.getString(r6);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r7 = new java.io.File;	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r7.<init>(r0);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        com.tencent.smtt.utils.k.b(r7);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r0 = new java.io.File;	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r0.<init>(r4);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        com.tencent.smtt.utils.k.b(r0);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r0 = new java.io.File;	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r0.<init>(r6);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        com.tencent.smtt.utils.k.b(r0);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r0 = r0.a;	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r4 = "tbs_needdownload";
        r6 = 1;
        r6 = java.lang.Boolean.valueOf(r6);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r0.put(r4, r6);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r0.commit();	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r0 = com.tencent.smtt.sdk.TbsLogReport.a(r11);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r4 = 224; // 0xe0 float:3.14E-43 double:1.107E-321;
        r6 = "incrUpdate exceed retry max num";
        r0.a(r4, r6);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r0 = i;
        r0.unlock();
        r10.b();
        r0 = "TbsInstaller";
        r1 = "TbsInstaller-installLocalTesCoreExInThread PATCH_NONEEDPATCH";
        com.tencent.smtt.utils.TbsLog.i(r0, r1);
        goto L_0x00bc;
    L_0x0149:
        r0 = r5;
        goto L_0x00d6;
    L_0x014b:
        r4 = com.tencent.smtt.sdk.ae.a(r11);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r6 = "incrupdate_retry_num";
        r0 = r0 + 1;
        r4.a(r6, r0);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r0 = j(r11);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        if (r0 == 0) goto L_0x032b;
    L_0x015c:
        r4 = new java.io.File;	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r6 = "x5.tbs";
        r4.<init>(r0, r6);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        if (r4 == 0) goto L_0x032b;
    L_0x0165:
        r0 = r4.exists();	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        if (r0 == 0) goto L_0x032b;
    L_0x016b:
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r4 = -550; // 0xfffffffffffffdda float:NaN double:NaN;
        r0.setInstallInterruptCode(r4);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        r1 = com.tencent.smtt.sdk.QbSdk.a(r11, r12);	 Catch:{ Exception -> 0x01cd, all -> 0x022f }
        if (r1 != 0) goto L_0x01c6;
    L_0x017a:
        r0 = r3;
    L_0x017b:
        r4 = i;
        r4.unlock();
        r10.b();
        if (r0 != 0) goto L_0x02d6;
    L_0x0185:
        r0 = "TbsInstaller";
        r2 = "TbsInstaller-installLocalTesCoreExInThread PATCH_SUCCESS";
        com.tencent.smtt.utils.TbsLog.i(r0, r2);
        r0 = com.tencent.smtt.sdk.ae.a(r11);
        r2 = "incrupdate_retry_num";
        r0.a(r2, r5);
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11);
        r2 = -544; // 0xfffffffffffffde0 float:NaN double:NaN;
        r0.setInstallInterruptCode(r2);
        r0 = com.tencent.smtt.sdk.ae.a(r11);
        r2 = -1;
        r0.b(r5, r2);
        r0 = com.tencent.smtt.sdk.ae.a(r11);
        r0.c(r3);
        r0 = "apk_path";
        r0 = r1.getString(r0);
        r2 = new java.io.File;
        r2.<init>(r0);
        com.tencent.smtt.sdk.ac.a(r2, r11);
        r2 = "tbs_core_ver";
        r1 = r1.getInt(r2);
        r10.a(r11, r0, r1);
        goto L_0x00bc;
    L_0x01c6:
        r0 = "patch_result";
        r0 = r1.getInt(r0);	 Catch:{ Exception -> 0x01cd }
        goto L_0x017b;
    L_0x01cd:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0321 }
        r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11);	 Catch:{ all -> 0x0326 }
        r6 = -543; // 0xfffffffffffffde1 float:NaN double:NaN;
        r4.setInstallInterruptCode(r6);	 Catch:{ all -> 0x0326 }
        r4 = com.tencent.smtt.sdk.TbsLogReport.a(r11);	 Catch:{ all -> 0x0326 }
        r6 = 218; // 0xda float:3.05E-43 double:1.077E-321;
        r0 = r0.toString();	 Catch:{ all -> 0x0326 }
        r4.a(r6, r0);	 Catch:{ all -> 0x0326 }
        r0 = i;
        r0.unlock();
        r10.b();
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11);
        r0.setInstallInterruptCode(r8);
        r0 = "TbsInstaller";
        r1 = "TbsInstaller-installLocalTesCoreExInThread PATCH_FAIL";
        com.tencent.smtt.utils.TbsLog.i(r0, r1);
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11);
        r0 = r0.a;
        r1 = "tbs_needdownload";
        r2 = java.lang.Boolean.valueOf(r3);
        r0.put(r1, r2);
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11);
        r0.commit();
        r0 = com.tencent.smtt.sdk.TbsLogReport.a(r11);
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "incrUpdate fail! patch ret=";
        r1 = r1.append(r2);
        r1 = r1.append(r3);
        r1 = r1.toString();
        r0.a(r9, r1);
        goto L_0x00bc;
    L_0x022f:
        r0 = move-exception;
        r4 = r1;
        r1 = r2;
    L_0x0232:
        r6 = i;
        r6.unlock();
        r10.b();
        if (r1 != 0) goto L_0x028d;
    L_0x023c:
        r1 = "TbsInstaller";
        r2 = "TbsInstaller-installLocalTesCoreExInThread PATCH_SUCCESS";
        com.tencent.smtt.utils.TbsLog.i(r1, r2);
        r1 = com.tencent.smtt.sdk.ae.a(r11);
        r2 = "incrupdate_retry_num";
        r1.a(r2, r5);
        r1 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11);
        r2 = -544; // 0xfffffffffffffde0 float:NaN double:NaN;
        r1.setInstallInterruptCode(r2);
        r1 = com.tencent.smtt.sdk.ae.a(r11);
        r2 = -1;
        r1.b(r5, r2);
        r1 = com.tencent.smtt.sdk.ae.a(r11);
        r1.c(r3);
        r1 = "apk_path";
        r1 = r4.getString(r1);
        r2 = new java.io.File;
        r2.<init>(r1);
        com.tencent.smtt.sdk.ac.a(r2, r11);
        r2 = "tbs_core_ver";
        r2 = r4.getInt(r2);
        r10.a(r11, r1, r2);
    L_0x027b:
        com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r5);
        throw r0;
    L_0x027f:
        r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11);
        r1 = -547; // 0xfffffffffffffddd float:NaN double:NaN;
        r0.setInstallInterruptCode(r1);
        r10.b();
        goto L_0x0016;
    L_0x028d:
        if (r1 != r2) goto L_0x0297;
    L_0x028f:
        r1 = "TbsInstaller";
        r2 = "TbsInstaller-installLocalTesCoreExInThread PATCH_NONEEDPATCH";
        com.tencent.smtt.utils.TbsLog.i(r1, r2);
        goto L_0x027b;
    L_0x0297:
        r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11);
        r2.setInstallInterruptCode(r8);
        r2 = "TbsInstaller";
        r4 = "TbsInstaller-installLocalTesCoreExInThread PATCH_FAIL";
        com.tencent.smtt.utils.TbsLog.i(r2, r4);
        r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11);
        r2 = r2.a;
        r4 = "tbs_needdownload";
        r3 = java.lang.Boolean.valueOf(r3);
        r2.put(r4, r3);
        r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11);
        r2.commit();
        r2 = com.tencent.smtt.sdk.TbsLogReport.a(r11);
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "incrUpdate fail! patch ret=";
        r3 = r3.append(r4);
        r1 = r3.append(r1);
        r1 = r1.toString();
        r2.a(r9, r1);
        goto L_0x027b;
    L_0x02d6:
        if (r0 != r2) goto L_0x02e1;
    L_0x02d8:
        r0 = "TbsInstaller";
        r1 = "TbsInstaller-installLocalTesCoreExInThread PATCH_NONEEDPATCH";
        com.tencent.smtt.utils.TbsLog.i(r0, r1);
        goto L_0x00bc;
    L_0x02e1:
        r1 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11);
        r1.setInstallInterruptCode(r8);
        r1 = "TbsInstaller";
        r2 = "TbsInstaller-installLocalTesCoreExInThread PATCH_FAIL";
        com.tencent.smtt.utils.TbsLog.i(r1, r2);
        r1 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11);
        r1 = r1.a;
        r2 = "tbs_needdownload";
        r3 = java.lang.Boolean.valueOf(r3);
        r1.put(r2, r3);
        r1 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r11);
        r1.commit();
        r1 = com.tencent.smtt.sdk.TbsLogReport.a(r11);
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "incrUpdate fail! patch ret=";
        r2 = r2.append(r3);
        r0 = r2.append(r0);
        r0 = r0.toString();
        r1.a(r9, r0);
        goto L_0x00bc;
    L_0x0321:
        r0 = move-exception;
        r4 = r1;
        r1 = r2;
        goto L_0x0232;
    L_0x0326:
        r0 = move-exception;
        r4 = r1;
        r1 = r3;
        goto L_0x0232;
    L_0x032b:
        r0 = r2;
        goto L_0x017b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.aj.b(android.content.Context, android.os.Bundle):void");
    }

    void b(Context context, boolean z) {
        if (!QbSdk.b) {
            if (VERSION.SDK_INT < 8) {
                TbsLog.e("TbsInstaller", "android version < 2.1 no need install X5 core", true);
            } else if (!n(context)) {
            } else {
                if (c(context, "core_unzip_tmp") && d(context, z)) {
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromUnzip!!", true);
                } else if (c(context, "core_share_backup_tmp") && e(context, z)) {
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromBackup!!", true);
                } else if (c(context, "core_copy_tmp") && c(context, z)) {
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromCopy!!", true);
                } else {
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreIfNeeded, error !!", true);
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    boolean b(android.content.Context r11) {
        /*
        r10 = this;
        r1 = 1;
        r2 = 0;
        r0 = r10.h(r11);
        r3 = new java.io.File;
        r4 = "tbs.conf";
        r3.<init>(r0, r4);
        if (r3 == 0) goto L_0x0015;
    L_0x000f:
        r4 = r3.exists();
        if (r4 != 0) goto L_0x0017;
    L_0x0015:
        r0 = r2;
    L_0x0016:
        return r0;
    L_0x0017:
        r5 = new java.util.Properties;
        r5.<init>();
        r4 = 0;
        r6 = new java.io.FileInputStream;	 Catch:{ Throwable -> 0x0082, all -> 0x0094 }
        r6.<init>(r3);	 Catch:{ Throwable -> 0x0082, all -> 0x0094 }
        r3 = new java.io.BufferedInputStream;	 Catch:{ Throwable -> 0x0082, all -> 0x0094 }
        r3.<init>(r6);	 Catch:{ Throwable -> 0x0082, all -> 0x0094 }
        r5.load(r3);	 Catch:{ Throwable -> 0x00a6, all -> 0x00a1 }
        r4 = "tbs_local_installation";
        r6 = "false";
        r4 = r5.getProperty(r4, r6);	 Catch:{ Throwable -> 0x00a6, all -> 0x00a1 }
        r4 = java.lang.Boolean.valueOf(r4);	 Catch:{ Throwable -> 0x00a6, all -> 0x00a1 }
        r4 = r4.booleanValue();	 Catch:{ Throwable -> 0x00a6, all -> 0x00a1 }
        if (r4 == 0) goto L_0x00b0;
    L_0x003c:
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x00ab, all -> 0x00a1 }
        r8 = r0.lastModified();	 Catch:{ Throwable -> 0x00ab, all -> 0x00a1 }
        r6 = r6 - r8;
        r8 = 259200000; // 0xf731400 float:1.1984677E-29 double:1.280618154E-315;
        r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r0 <= 0) goto L_0x007e;
    L_0x004c:
        r0 = r1;
    L_0x004d:
        r5 = "TbsInstaller";
        r6 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00ab, all -> 0x00a1 }
        r6.<init>();	 Catch:{ Throwable -> 0x00ab, all -> 0x00a1 }
        r7 = "TBS_LOCAL_INSTALLATION is:";
        r6 = r6.append(r7);	 Catch:{ Throwable -> 0x00ab, all -> 0x00a1 }
        r6 = r6.append(r4);	 Catch:{ Throwable -> 0x00ab, all -> 0x00a1 }
        r7 = " expired=";
        r6 = r6.append(r7);	 Catch:{ Throwable -> 0x00ab, all -> 0x00a1 }
        r6 = r6.append(r0);	 Catch:{ Throwable -> 0x00ab, all -> 0x00a1 }
        r6 = r6.toString();	 Catch:{ Throwable -> 0x00ab, all -> 0x00a1 }
        com.tencent.smtt.utils.TbsLog.i(r5, r6);	 Catch:{ Throwable -> 0x00ab, all -> 0x00a1 }
        if (r0 != 0) goto L_0x0080;
    L_0x0071:
        r0 = r4 & r1;
        if (r3 == 0) goto L_0x0016;
    L_0x0075:
        r3.close();	 Catch:{ IOException -> 0x0079 }
        goto L_0x0016;
    L_0x0079:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0016;
    L_0x007e:
        r0 = r2;
        goto L_0x004d;
    L_0x0080:
        r1 = r2;
        goto L_0x0071;
    L_0x0082:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
        r2 = r4;
    L_0x0086:
        r1.printStackTrace();	 Catch:{ all -> 0x00a3 }
        if (r2 == 0) goto L_0x0016;
    L_0x008b:
        r2.close();	 Catch:{ IOException -> 0x008f }
        goto L_0x0016;
    L_0x008f:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0016;
    L_0x0094:
        r0 = move-exception;
        r3 = r4;
    L_0x0096:
        if (r3 == 0) goto L_0x009b;
    L_0x0098:
        r3.close();	 Catch:{ IOException -> 0x009c }
    L_0x009b:
        throw r0;
    L_0x009c:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x009b;
    L_0x00a1:
        r0 = move-exception;
        goto L_0x0096;
    L_0x00a3:
        r0 = move-exception;
        r3 = r2;
        goto L_0x0096;
    L_0x00a6:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
        r2 = r3;
        goto L_0x0086;
    L_0x00ab:
        r0 = move-exception;
        r1 = r0;
        r2 = r3;
        r0 = r4;
        goto L_0x0086;
    L_0x00b0:
        r0 = r2;
        goto L_0x004d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.aj.b(android.content.Context):boolean");
    }

    boolean c(Context context) {
        try {
            Signature signature = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0];
            if (context.getPackageName().equals(TbsConfig.APP_QB)) {
                if (!signature.toCharsString().equals("3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a")) {
                    return false;
                }
            } else if (context.getPackageName().equals("com.tencent.mm")) {
                if (!signature.toCharsString().equals("308202eb30820254a00302010202044d36f7a4300d06092a864886f70d01010505003081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e74301e170d3131303131393134333933325a170d3431303131313134333933325a3081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e7430819f300d06092a864886f70d010101050003818d0030818902818100c05f34b231b083fb1323670bfbe7bdab40c0c0a6efc87ef2072a1ff0d60cc67c8edb0d0847f210bea6cbfaa241be70c86daf56be08b723c859e52428a064555d80db448cdcacc1aea2501eba06f8bad12a4fa49d85cacd7abeb68945a5cb5e061629b52e3254c373550ee4e40cb7c8ae6f7a8151ccd8df582d446f39ae0c5e930203010001300d06092a864886f70d0101050500038181009c8d9d7f2f908c42081b4c764c377109a8b2c70582422125ce545842d5f520aea69550b6bd8bfd94e987b75a3077eb04ad341f481aac266e89d3864456e69fba13df018acdc168b9a19dfd7ad9d9cc6f6ace57c746515f71234df3a053e33ba93ece5cd0fc15f3e389a3f365588a9fcb439e069d3629cd7732a13fff7b891499")) {
                    return false;
                }
            } else if (context.getPackageName().equals("com.tencent.mobileqq")) {
                if (!signature.toCharsString().equals("30820253308201bca00302010202044bbb0361300d06092a864886f70d0101050500306d310e300c060355040613054368696e61310f300d06035504080c06e58c97e4baac310f300d06035504070c06e58c97e4baac310f300d060355040a0c06e885bee8aeaf311b3019060355040b0c12e697a0e7babfe4b89ae58aa1e7b3bbe7bb9f310b30090603550403130251513020170d3130303430363039343831375a180f32323834303132303039343831375a306d310e300c060355040613054368696e61310f300d06035504080c06e58c97e4baac310f300d06035504070c06e58c97e4baac310f300d060355040a0c06e885bee8aeaf311b3019060355040b0c12e697a0e7babfe4b89ae58aa1e7b3bbe7bb9f310b300906035504031302515130819f300d06092a864886f70d010101050003818d0030818902818100a15e9756216f694c5915e0b529095254367c4e64faeff07ae13488d946615a58ddc31a415f717d019edc6d30b9603d3e2a7b3de0ab7e0cf52dfee39373bc472fa997027d798d59f81d525a69ecf156e885fd1e2790924386b2230cc90e3b7adc95603ddcf4c40bdc72f22db0f216a99c371d3bf89cba6578c60699e8a0d536950203010001300d06092a864886f70d01010505000381810094a9b80e80691645dd42d6611775a855f71bcd4d77cb60a8e29404035a5e00b21bcc5d4a562482126bd91b6b0e50709377ceb9ef8c2efd12cc8b16afd9a159f350bb270b14204ff065d843832720702e28b41491fbc3a205f5f2f42526d67f17614d8a974de6487b2c866efede3b4e49a0f916baa3c1336fd2ee1b1629652049")) {
                    return false;
                }
            } else if (context.getPackageName().equals(TbsConfig.APP_DEMO)) {
                if (!signature.toCharsString().equals("3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a")) {
                    return false;
                }
            } else if (context.getPackageName().equals("com.qzone")) {
                if (!signature.toCharsString().equals("308202ad30820216a00302010202044c26cea2300d06092a864886f70d010105050030819a310b3009060355040613023836311530130603550408130c4265696a696e672043697479311530130603550407130c4265696a696e67204369747931263024060355040a131d515a6f6e65205465616d206f662054656e63656e7420436f6d70616e7931183016060355040b130f54656e63656e7420436f6d70616e79311b301906035504031312416e64726f696420515a6f6e65205465616d301e170d3130303632373034303830325a170d3335303632313034303830325a30819a310b3009060355040613023836311530130603550408130c4265696a696e672043697479311530130603550407130c4265696a696e67204369747931263024060355040a131d515a6f6e65205465616d206f662054656e63656e7420436f6d70616e7931183016060355040b130f54656e63656e7420436f6d70616e79311b301906035504031312416e64726f696420515a6f6e65205465616d30819f300d06092a864886f70d010101050003818d003081890281810082d6aca037a9843fbbe88b6dd19f36e9c24ce174c1b398f3a529e2a7fe02de99c27539602c026edf96ad8d43df32a85458bca1e6fbf11958658a7d6751a1d9b782bf43a8c19bd1c06bdbfd94c0516326ae3cf638ac42bb470580e340c46e6f306a772c1ef98f10a559edf867f3f31fe492808776b7bd953b2cba2d2b2d66a44f0203010001300d06092a864886f70d0101050500038181006003b04a8a8c5be9650f350cda6896e57dd13e6e83e7f891fc70f6a3c2eaf75cfa4fc998365deabbd1b9092159edf4b90df5702a0d101f8840b5d4586eb92a1c3cd19d95fbc1c2ac956309eda8eef3944baf08c4a49d3b9b3ffb06bc13dab94ecb5b8eb74e8789aa0ba21cb567f538bbc59c2a11e6919924a24272eb79251677")) {
                    return false;
                }
            } else if (context.getPackageName().equals("com.tencent.qqpimsecure") && !signature.toCharsString().equals("30820239308201a2a00302010202044c96f48f300d06092a864886f70d01010505003060310b300906035504061302434e310b300906035504081302474431123010060355040713094775616e677a686f753110300e060355040a130754656e63656e74310b3009060355040b130233473111300f0603550403130857696c736f6e57753020170d3130303932303035343334335a180f32303635303632333035343334335a3060310b300906035504061302434e310b300906035504081302474431123010060355040713094775616e677a686f753110300e060355040a130754656e63656e74310b3009060355040b130233473111300f0603550403130857696c736f6e577530819f300d06092a864886f70d010101050003818d0030818902818100b56e79dbb1185a79e52d792bb3d0bb3da8010d9b87da92ec69f7dc5ad66ab6bfdff2a6a1ed285dd2358f28b72a468be7c10a2ce30c4c27323ed4edcc936080e5bedc2cbbca0b7e879c08a631182793f44bb3ea284179b263410c298e5f6831032c9702ba4a74e2ccfc9ef857f12201451602fc8e774ac59d6398511586c83d1d0203010001300d06092a864886f70d0101050500038181002475615bb65b8d8786b890535802948840387d06b1692ff3ea47ef4c435719ba1865b81e6bfa6293ce31747c3cd6b34595b485cc1563fd90107ba5845c28b95c79138f0dec288940395bc10f92f2b69d8dc410999deb38900974ce9984b678030edfba8816582f56160d87e38641288d8588d2a31e20b89f223d788dd35cc9c8")) {
                return false;
            }
            return true;
        } catch (Exception e) {
            TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore getPackageInfo fail");
            return false;
        }
    }

    int d(Context context) {
        String str;
        String str2;
        Exception e;
        Throwable th;
        int i = 0;
        BufferedInputStream bufferedInputStream = null;
        BufferedInputStream bufferedInputStream2;
        try {
            File file = new File(h(context), "tbs.conf");
            if (file == null || !file.exists()) {
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e2) {
                        str = "TbsInstaller";
                        str2 = "TbsInstaller--getTbsCoreInstalledVerInNolock IOException=" + e2.toString();
                        TbsLog.i(str, str2);
                        return i;
                    }
                }
                return i;
            }
            Properties properties = new Properties();
            bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
            try {
                properties.load(bufferedInputStream2);
                bufferedInputStream2.close();
                str2 = properties.getProperty("tbs_core_version");
                if (str2 == null) {
                    if (bufferedInputStream2 != null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (IOException e22) {
                            str = "TbsInstaller";
                            str2 = "TbsInstaller--getTbsCoreInstalledVerInNolock IOException=" + e22.toString();
                            TbsLog.i(str, str2);
                            return i;
                        }
                    }
                    return i;
                }
                i = Integer.parseInt(str2);
                if (bufferedInputStream2 != null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException e222) {
                        str = "TbsInstaller";
                        str2 = "TbsInstaller--getTbsCoreInstalledVerInNolock IOException=" + e222.toString();
                        TbsLog.i(str, str2);
                        return i;
                    }
                }
                return i;
            } catch (Exception e3) {
                e = e3;
                try {
                    TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerInNolock Exception=" + e.toString());
                    if (bufferedInputStream2 != null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (IOException e2222) {
                            str = "TbsInstaller";
                            str2 = "TbsInstaller--getTbsCoreInstalledVerInNolock IOException=" + e2222.toString();
                            TbsLog.i(str, str2);
                            return i;
                        }
                    }
                    return i;
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedInputStream2 != null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (IOException e22222) {
                            TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerInNolock IOException=" + e22222.toString());
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e4) {
            e = e4;
            bufferedInputStream2 = bufferedInputStream;
            TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerInNolock Exception=" + e.toString());
            if (bufferedInputStream2 != null) {
                bufferedInputStream2.close();
            }
            return i;
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream2 = bufferedInputStream;
            if (bufferedInputStream2 != null) {
                bufferedInputStream2.close();
            }
            throw th;
        }
    }

    boolean e(Context context) {
        File file = new File(h(context), "tbs.conf");
        return file != null && file.exists();
    }

    int f(Context context) {
        Exception e;
        Throwable th;
        if (!m(context)) {
            return -1;
        }
        boolean tryLock = h.tryLock();
        TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVer locked=" + tryLock);
        if (tryLock) {
            BufferedInputStream bufferedInputStream = null;
            BufferedInputStream bufferedInputStream2;
            try {
                File file = new File(h(context), "tbs.conf");
                if (file == null || !file.exists()) {
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e2) {
                            TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVer IOException=" + e2.toString());
                        }
                    }
                    h.unlock();
                    b();
                    return 0;
                }
                Properties properties = new Properties();
                bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
                try {
                    properties.load(bufferedInputStream2);
                    bufferedInputStream2.close();
                    String property = properties.getProperty("tbs_core_version");
                    if (property == null) {
                        if (bufferedInputStream2 != null) {
                            try {
                                bufferedInputStream2.close();
                            } catch (IOException e22) {
                                TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVer IOException=" + e22.toString());
                            }
                        }
                        h.unlock();
                        b();
                        return 0;
                    }
                    a.set(Integer.valueOf(Integer.parseInt(property)));
                    int intValue = ((Integer) a.get()).intValue();
                    if (bufferedInputStream2 != null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (IOException e3) {
                            TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVer IOException=" + e3.toString());
                        }
                    }
                    h.unlock();
                    b();
                    return intValue;
                } catch (Exception e4) {
                    e = e4;
                    try {
                        TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVer Exception=" + e.toString());
                        if (bufferedInputStream2 != null) {
                            try {
                                bufferedInputStream2.close();
                            } catch (IOException e222) {
                                TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVer IOException=" + e222.toString());
                            }
                        }
                        h.unlock();
                        b();
                        return 0;
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedInputStream2 != null) {
                            try {
                                bufferedInputStream2.close();
                            } catch (IOException e32) {
                                TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVer IOException=" + e32.toString());
                            }
                        }
                        h.unlock();
                        b();
                        throw th;
                    }
                }
            } catch (Exception e5) {
                e = e5;
                bufferedInputStream2 = bufferedInputStream;
                TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVer Exception=" + e.toString());
                if (bufferedInputStream2 != null) {
                    bufferedInputStream2.close();
                }
                h.unlock();
                b();
                return 0;
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream2 = bufferedInputStream;
                if (bufferedInputStream2 != null) {
                    bufferedInputStream2.close();
                }
                h.unlock();
                b();
                throw th;
            }
        }
        b();
        return 0;
    }

    void g(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--cleanStatusAndTmpDir");
        ae.a(context).a(0);
        ae.a(context).b(0);
        ae.a(context).d(0);
        ae.a(context).a("incrupdate_retry_num", 0);
        ae.a(context).b(0, -1);
        ae.a(context).a("");
        ae.a(context).a("copy_retry_num", 0);
        ae.a(context).a(0, -1);
        ae.a(context).c(-1);
        k.a(k(context), true);
        k.a(l(context), true);
    }

    File h(Context context) {
        return b(null, context);
    }

    File i(Context context) {
        File file = new File(context.getDir("tbs", 0), "share");
        return file != null ? (file.isDirectory() || file.mkdir()) ? file : null : null;
    }

    File k(Context context) {
        File file = new File(context.getDir("tbs", 0), "core_unzip_tmp");
        return file != null ? (file.isDirectory() || file.mkdir()) ? file : null : null;
    }

    File l(Context context) {
        File file = new File(context.getDir("tbs", 0), "core_copy_tmp");
        return file != null ? (file.isDirectory() || file.mkdir()) ? file : null : null;
    }

    synchronized boolean m(Context context) {
        boolean z = true;
        synchronized (this) {
            this.d++;
            if (this.g) {
                TbsLog.i("TbsInstaller", "getTbsInstallingFileLock success,is cached= true");
            } else {
                this.f = k.b(context, true, "tbslock.txt");
                if (this.f != null) {
                    this.e = k.a(context, this.f);
                    if (this.e == null) {
                        z = false;
                    } else {
                        TbsLog.i("TbsInstaller", "getTbsInstallingFileLock success,is cached= false");
                        this.g = true;
                    }
                } else {
                    z = false;
                }
            }
        }
        return z;
    }
}
