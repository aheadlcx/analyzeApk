package cn.xiaochuan.base;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public abstract class BaseApplication extends DefaultApplicationLike {
    private static Application ___APP_CONTEXT = null;

    public BaseApplication(Application application, int i, boolean z, long j, long j2, Intent intent) {
        super(application, i, z, j, j2, intent);
    }

    public void onCreate() {
        super.onCreate();
        ___APP_CONTEXT = getApplication();
    }

    @NonNull
    public static Context getAppContext() {
        return ___APP_CONTEXT.getApplicationContext();
    }

    public static Application __getApplication() {
        return ___APP_CONTEXT;
    }

    public static boolean isApplicationInBackground() {
        List runningTasks = ((ActivityManager) getAppContext().getSystemService("activity")).getRunningTasks(1);
        if (runningTasks.isEmpty() || !((RunningTaskInfo) runningTasks.get(0)).topActivity.getPackageName().equals(getAppContext().getPackageName())) {
            return true;
        }
        return false;
    }

    public static String getProcessName(int i) {
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
