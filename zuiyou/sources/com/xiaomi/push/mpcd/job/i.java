package com.xiaomi.push.mpcd.job;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.misc.c;
import com.xiaomi.xmpush.thrift.d;
import java.util.Calendar;
import java.util.List;

public class i extends f {
    private SharedPreferences a;

    public i(Context context, int i) {
        super(context, i);
        this.a = context.getSharedPreferences("mipush_extra", 0);
    }

    public int a() {
        return 9;
    }

    public String b() {
        String packageName;
        try {
            ActivityManager activityManager = (ActivityManager) this.d.getSystemService("activity");
            if (VERSION.SDK_INT < 21) {
                packageName = ((RunningTaskInfo) activityManager.getRunningTasks(1).get(0)).topActivity.getPackageName();
            } else {
                UsageStatsManager usageStatsManager = (UsageStatsManager) this.d.getSystemService("usagestats");
                Calendar instance = Calendar.getInstance();
                instance.add(5, -1);
                long timeInMillis = instance.getTimeInMillis();
                instance.add(5, 1);
                List queryUsageStats = usageStatsManager.queryUsageStats(0, timeInMillis, instance.getTimeInMillis());
                if (c.a(queryUsageStats)) {
                    return null;
                }
                timeInMillis = 0;
                packageName = "";
                int i = 0;
                while (i < queryUsageStats.size()) {
                    String packageName2;
                    UsageStats usageStats = (UsageStats) queryUsageStats.get(i);
                    if (usageStats.getLastTimeStamp() > timeInMillis) {
                        timeInMillis = usageStats.getLastTimeStamp();
                        packageName2 = usageStats.getPackageName();
                    } else {
                        packageName2 = packageName;
                    }
                    i++;
                    packageName = packageName2;
                }
            }
        } catch (Throwable th) {
            packageName = null;
        }
        if (TextUtils.isEmpty(packageName)) {
            return null;
        }
        if (TextUtils.equals(packageName, this.a.getString("ltapn", null))) {
            return "^";
        }
        this.a.edit().putString("ltapn", packageName).commit();
        return packageName;
    }

    public d d() {
        return d.TopApp;
    }
}
