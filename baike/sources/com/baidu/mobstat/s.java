package com.baidu.mobstat;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

class s {
    static s a = new s();
    private String b = "";

    s() {
    }

    public synchronized void a(Context context, boolean z) {
        int i = 1;
        if (!z) {
            i = 20;
        }
        a(context, z, i);
    }

    private void a(Context context, boolean z, int i) {
        ArrayList a = a(context, i);
        if (a != null && a.size() != 0) {
            if (z) {
                String b = ((t) a.get(0)).b();
                if (a(b, this.b)) {
                    this.b = b;
                }
            }
            a(context, a, z);
        }
    }

    private ArrayList<t> a(Context context, int i) {
        if (VERSION.SDK_INT >= 21) {
            return c(context, i);
        }
        return b(context, i);
    }

    private ArrayList<t> b(Context context, int i) {
        List runningTasks;
        try {
            runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(50);
        } catch (Throwable e) {
            bd.b(e);
            runningTasks = null;
        }
        if (r0 == null) {
            return new ArrayList();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (RunningTaskInfo runningTaskInfo : r0) {
            if (linkedHashMap.size() > i) {
                break;
            }
            ComponentName componentName = runningTaskInfo.topActivity;
            if (componentName != null) {
                String packageName = componentName.getPackageName();
                if (!(TextUtils.isEmpty(packageName) || b(context, packageName) || linkedHashMap.containsKey(packageName))) {
                    linkedHashMap.put(packageName, new t(packageName, a(context, packageName), ""));
                }
            }
        }
        return new ArrayList(linkedHashMap.values());
    }

    private ArrayList<t> c(Context context, int i) {
        List runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return new ArrayList();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i2 = 0; i2 < runningAppProcesses.size() && linkedHashMap.size() <= i; i2++) {
            RunningAppProcessInfo runningAppProcessInfo = (RunningAppProcessInfo) runningAppProcesses.get(i2);
            if (a(runningAppProcessInfo.importance)) {
                String[] strArr = runningAppProcessInfo.pkgList;
                if (!(strArr == null || strArr.length == 0)) {
                    String str = runningAppProcessInfo.pkgList[0];
                    if (!(TextUtils.isEmpty(str) || b(context, str) || linkedHashMap.containsKey(str))) {
                        linkedHashMap.put(str, new t(str, a(context, str), String.valueOf(runningAppProcessInfo.importance)));
                    }
                }
            }
        }
        return new ArrayList(linkedHashMap.values());
    }

    private boolean a(int i) {
        if (i == 100 || i == 200 || i == 130) {
            return true;
        }
        return false;
    }

    private boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || str.equals(this.b)) {
            return false;
        }
        return true;
    }

    private String a(Context context, String str) {
        String str2 = "";
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return str2;
        }
        try {
            str2 = packageManager.getPackageInfo(str, 0).versionName;
        } catch (Throwable e) {
            bd.b(e);
        }
        return str2 == null ? "" : str2;
    }

    private boolean b(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getPackageInfo(str, 0).applicationInfo;
            if (applicationInfo == null || (applicationInfo.flags & 1) == 0) {
                return false;
            }
            return true;
        } catch (Throwable e) {
            bd.b(e);
            return false;
        }
    }

    private void a(Context context, ArrayList<t> arrayList, boolean z) {
        String a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.currentTimeMillis() + "|");
        stringBuilder.append(z ? 1 : 0);
        String str = "";
        try {
            JSONObject a2;
            JSONArray jSONArray = new JSONArray();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                a2 = ((t) it.next()).a();
                if (a2 != null) {
                    jSONArray.put(a2);
                }
            }
            a2 = new JSONObject();
            a2.put("app_trace", jSONArray);
            a2.put("meta-data", stringBuilder.toString());
            a = cs.a(a2.toString().getBytes());
        } catch (Throwable e) {
            bd.b(e);
            a = str;
        }
        if (!TextUtils.isEmpty(a)) {
            y.APP_TRACE.a(System.currentTimeMillis(), a);
        }
    }
}
