package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.core.model.User;

class r {
    static final r a = new r();

    r() {
    }

    public synchronized void a(Context context, boolean z) {
        b(context, z);
    }

    private void b(Context context, boolean z) {
        int i = 1;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            List arrayList = new ArrayList(1);
            try {
                arrayList = packageManager.getInstalledPackages(0);
            } catch (Throwable e) {
                bd.b(e);
            }
            JSONArray jSONArray = new JSONArray();
            for (PackageInfo packageInfo : r0) {
                ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                if (applicationInfo != null) {
                    boolean z2 = (applicationInfo.flags & 1) != 0;
                    String charSequence = applicationInfo.loadLabel(packageManager).toString();
                    String str = applicationInfo.sourceDir;
                    if (z == z2) {
                        a(z, charSequence, str, packageInfo, jSONArray);
                    }
                }
            }
            if (jSONArray.length() != 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(System.currentTimeMillis() + "|");
                if (!z) {
                    i = 0;
                }
                stringBuilder.append(i);
                String str2 = "";
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("app_list", jSONArray);
                    jSONObject.put("meta-data", stringBuilder.toString());
                    str2 = cs.a(jSONObject.toString().getBytes());
                } catch (Exception e2) {
                }
                if (!TextUtils.isEmpty(str2)) {
                    y.APP_LIST.a(System.currentTimeMillis(), str2);
                }
            }
        }
    }

    private void a(boolean z, String str, String str2, PackageInfo packageInfo, JSONArray jSONArray) {
        long j = 0;
        if (!z || !packageInfo.packageName.startsWith("com.android.")) {
            long j2;
            try {
                j2 = packageInfo.firstInstallTime;
            } catch (Throwable th) {
                bd.b(th);
                j2 = j;
            }
            try {
                j = packageInfo.lastUpdateTime;
            } catch (Throwable th2) {
                bd.b(th2);
            }
            long a = a(str2);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("n", packageInfo.packageName);
                jSONObject.put("a", str);
                jSONObject.put("v", String.valueOf(packageInfo.versionName));
                jSONObject.put(User.FEMALE, j2);
                jSONObject.put("l", j);
                jSONObject.put("m", a);
                jSONArray.put(jSONObject);
            } catch (Throwable th3) {
                bd.b(th3);
            }
        }
    }

    private long a(String str) {
        if (str == null) {
            return 0;
        }
        File file = new File(str);
        if (file == null || !file.exists()) {
            return 0;
        }
        return file.lastModified();
    }
}
