package com.sprite.ads.internal.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.sprite.ads.R;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdUtil {
    public static void addAdCaption(Context context, ViewGroup viewGroup) {
        View.inflate(context, R.layout.view_ad_caption, viewGroup);
    }

    public static void addGdtCaption(Context context, ViewGroup viewGroup) {
        View.inflate(context, R.layout.view_ad_gdt_caption, viewGroup);
        final View findViewById = viewGroup.findViewById(R.id.ad_gdt_logo);
        final View findViewById2 = viewGroup.findViewById(R.id.ad_gdt_logo_expand);
        findViewById.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                findViewById.setVisibility(8);
                findViewById2.setVisibility(0);
            }
        });
        findViewById2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                findViewById.setVisibility(0);
                findViewById2.setVisibility(8);
            }
        });
    }

    public static void addTuiGuang(Context context, ViewGroup viewGroup) {
        View.inflate(context, R.layout.tuiguang, viewGroup);
    }

    private static String[] getActivePackages(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            Set hashSet = new HashSet();
            List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return null;
            }
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.importance == 100) {
                    hashSet.addAll(Arrays.asList(runningAppProcessInfo.pkgList));
                }
            }
            return (String[]) hashSet.toArray(new String[hashSet.size()]);
        } catch (Exception e) {
            return null;
        }
    }

    private static String[] getActivePackagesCompat(Context context) {
        try {
            List runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
            if (runningTasks == null) {
                return null;
            }
            ComponentName componentName = ((RunningTaskInfo) runningTasks.get(0)).topActivity;
            return new String[]{componentName.getPackageName()};
        } catch (Exception e) {
            return null;
        }
    }

    public static PackageInfo getAppInfo(Context context) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo;
    }

    public static String getAppVersion(Context context) {
        PackageInfo appInfo = getAppInfo(context);
        return appInfo != null ? appInfo.versionName : "0";
    }

    public static boolean isRunningForeground(Context context) {
        String[] activePackages = VERSION.SDK_INT > 20 ? getActivePackages(context) : getActivePackagesCompat(context);
        if (activePackages == null) {
            return false;
        }
        for (String equals : activePackages) {
            if (equals.equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }
}
