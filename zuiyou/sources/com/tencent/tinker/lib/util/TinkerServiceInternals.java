package com.tencent.tinker.lib.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.os.Process;
import android.util.Log;
import com.tencent.tinker.lib.service.TinkerPatchService;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.util.List;

public class TinkerServiceInternals extends ShareTinkerInternals {
    private static final String TAG = "Tinker.ServiceInternals";
    private static String patchServiceProcessName = null;

    public static void killTinkerPatchServiceProcess(Context context) {
        String tinkerPatchServiceName = getTinkerPatchServiceName(context);
        if (tinkerPatchServiceName != null) {
            List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
            if (runningAppProcesses != null) {
                for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo.processName.equals(tinkerPatchServiceName)) {
                        Process.killProcess(runningAppProcessInfo.pid);
                    }
                }
            }
        }
    }

    public static boolean isTinkerPatchServiceRunning(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        String tinkerPatchServiceName = getTinkerPatchServiceName(context);
        if (tinkerPatchServiceName == null) {
            return false;
        }
        try {
            List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return false;
            }
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.processName.equals(tinkerPatchServiceName)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            Log.e(TAG, "isTinkerPatchServiceRunning Exception: " + e.toString());
            return false;
        } catch (Error e2) {
            Log.e(TAG, "isTinkerPatchServiceRunning Error: " + e2.toString());
            return false;
        }
    }

    public static String getTinkerPatchServiceName(Context context) {
        if (patchServiceProcessName != null) {
            return patchServiceProcessName;
        }
        String serviceProcessName = getServiceProcessName(context, TinkerPatchService.class);
        if (serviceProcessName == null) {
            return null;
        }
        patchServiceProcessName = serviceProcessName;
        return patchServiceProcessName;
    }

    public static boolean isInTinkerPatchServiceProcess(Context context) {
        String processName = ShareTinkerInternals.getProcessName(context);
        String tinkerPatchServiceName = getTinkerPatchServiceName(context);
        if (tinkerPatchServiceName == null || tinkerPatchServiceName.length() == 0) {
            return false;
        }
        return processName.equals(tinkerPatchServiceName);
    }

    private static String getServiceProcessName(Context context, Class<? extends Service> cls) {
        try {
            return context.getPackageManager().getServiceInfo(new ComponentName(context, cls), 0).processName;
        } catch (Throwable th) {
            return null;
        }
    }
}
