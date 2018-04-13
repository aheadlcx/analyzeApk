package com.sina.weibo.sdk.a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import com.sina.weibo.sdk.a;
import com.sina.weibo.sdk.auth.c;

public class h {
    public static boolean a(Context context, Intent intent) {
        boolean z = false;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, z);
            if (resolveActivity != null) {
                try {
                    z = a(packageManager.getPackageInfo(resolveActivity.activityInfo.packageName, 64).signatures, "18da2bf10352443a00a5e046d9fca6bd");
                } catch (NameNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        return z;
    }

    public static boolean a(Context context, c cVar, Intent intent) {
        if ((cVar != null && cVar.c() <= 10352) || cVar == null) {
            return true;
        }
        String stringExtra = intent != null ? intent.getStringExtra("_weibo_appPackage") : null;
        if (stringExtra == null || intent.getStringExtra("_weibo_transaction") == null || !a.a(context, stringExtra)) {
            return false;
        }
        return true;
    }

    public static boolean a(Signature[] signatureArr, String str) {
        if (signatureArr == null || str == null) {
            return false;
        }
        for (Signature toByteArray : signatureArr) {
            if (str.equals(e.a(toByteArray.toByteArray()))) {
                return true;
            }
        }
        return false;
    }
}
