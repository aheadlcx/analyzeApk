package cn.xiaochuankeji.tieba.analyse;

import android.content.Context;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.h.c;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport.UserStrategy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class a {
    public static void a(Context context) {
        String packageChannel = AppController.instance().packageChannel();
        String packageName = context.getPackageName();
        String a = a(Process.myPid());
        BuglyStrategy userStrategy = new UserStrategy(context);
        boolean z = a == null || a.equals(packageName);
        userStrategy.setUploadProcess(z);
        userStrategy.setAppChannel(packageChannel);
        userStrategy.setEnableANRCrashMonitor(true);
        userStrategy.setEnableNativeCrashMonitor(true);
        userStrategy.setDeviceID(AppController.instance().deviceID());
        Beta.initDelay = 10000;
        Beta.autoInit = true;
        Beta.largeIconId = R.drawable.mipush_notification;
        Beta.smallIconId = R.drawable.mipush_small_notification;
        Beta.upgradeListener = c.a;
        Beta.strToastCheckingUpgrade = null;
        Beta.autoDownloadOnWifi = true;
        Beta.autoCheckUpgrade = false;
        Beta.enableHotfix = true;
        Beta.canAutoDownloadPatch = true;
        Beta.canAutoPatch = true;
        Beta.canNotifyUserRestart = false;
        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        Bugly.setAppChannel(BaseApplication.getAppContext(), packageChannel);
        Bugly.init(context, "930f41d203", false, userStrategy);
    }

    public static void a(Throwable th) {
        com.izuiyou.a.a.a.a(th);
    }

    private static String a(int i) {
        Throwable th;
        BufferedReader bufferedReader = null;
        BufferedReader bufferedReader2;
        try {
            bufferedReader2 = new BufferedReader(new FileReader("/proc/" + i + "/cmdline"));
            try {
                String readLine = bufferedReader2.readLine();
                if (!TextUtils.isEmpty(readLine)) {
                    readLine = readLine.trim();
                }
                if (bufferedReader2 == null) {
                    return readLine;
                }
                try {
                    bufferedReader2.close();
                    return readLine;
                } catch (IOException e) {
                    e.printStackTrace();
                    return readLine;
                }
            } catch (Throwable th2) {
                th = th2;
                try {
                    th.printStackTrace();
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
    }
}
