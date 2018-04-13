package com.baidu.mobstat;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import java.util.List;

public enum ao {
    SERVICE(1),
    NO_SERVICE(2),
    RECEIVER(3),
    ERISED(4);
    
    private int e;

    public abstract void a(Context context);

    private ao(int i) {
        this.e = i;
    }

    public String toString() {
        return String.valueOf(this.e);
    }

    public static ao a(int i) {
        for (ao aoVar : values()) {
            if (aoVar.e == i) {
                return aoVar;
            }
        }
        return NO_SERVICE;
    }

    public static boolean b(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null) {
            try {
                List runningServices = activityManager.getRunningServices(Integer.MAX_VALUE);
                int i = 0;
                while (runningServices != null && i < runningServices.size()) {
                    if ("com.baidu.bottom.service.BottomService".equals(((RunningServiceInfo) runningServices.get(i)).service.getClassName())) {
                        return true;
                    }
                    i++;
                }
            } catch (Throwable e) {
                db.a(e);
            }
        }
        return false;
    }
}
