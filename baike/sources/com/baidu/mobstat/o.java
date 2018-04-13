package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class o {
    static o a = new o();

    public synchronized void a(Context context) {
        b(context);
    }

    private void b(Context context) {
        a(context, c(context));
    }

    private ArrayList<p> c(Context context) {
        ArrayList<p> arrayList = new ArrayList();
        Iterator it = d(context).iterator();
        while (it.hasNext()) {
            PackageInfo packageInfo = (PackageInfo) it.next();
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (applicationInfo != null) {
                String str;
                String str2 = packageInfo.packageName;
                String str3 = packageInfo.versionName;
                String str4 = "";
                Signature[] signatureArr = packageInfo.signatures;
                if (signatureArr == null || signatureArr.length == 0) {
                    str = str4;
                } else {
                    str = signatureArr[0].toChars().toString();
                }
                str4 = cz.a(str.getBytes());
                str = "";
                Object obj = applicationInfo.sourceDir;
                if (!TextUtils.isEmpty(obj)) {
                    str = cz.a(new File(obj));
                }
                arrayList.add(new p(str2, str3, str4, str));
            }
        }
        return arrayList;
    }

    private ArrayList<PackageInfo> d(Context context) {
        ArrayList<PackageInfo> arrayList = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return arrayList;
        }
        List arrayList2 = new ArrayList(1);
        try {
            arrayList2 = packageManager.getInstalledPackages(64);
        } catch (Throwable e) {
            bd.b(e);
        }
        for (PackageInfo packageInfo : r0) {
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (applicationInfo != null && (applicationInfo.flags & 1) == 0) {
                arrayList.add(packageInfo);
            }
        }
        return arrayList;
    }

    private void a(Context context, ArrayList<p> arrayList) {
        String a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.currentTimeMillis());
        String str = "";
        try {
            JSONObject a2;
            JSONArray jSONArray = new JSONArray();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                a2 = ((p) it.next()).a();
                if (a2 != null) {
                    jSONArray.put(a2);
                }
            }
            a2 = new JSONObject();
            a2.put("app_apk", jSONArray);
            a2.put("meta-data", stringBuilder.toString());
            a = cs.a(a2.toString().getBytes());
        } catch (Throwable e) {
            bd.b(e);
            a = str;
        }
        if (!TextUtils.isEmpty(a)) {
            y.APP_APK.a(System.currentTimeMillis(), a);
        }
    }
}
