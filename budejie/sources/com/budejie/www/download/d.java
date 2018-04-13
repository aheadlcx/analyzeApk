package com.budejie.www.download;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.text.TextUtils;
import java.net.URLDecoder;
import java.util.Locale;

public final class d {
    public static String a(String str, String str2) {
        String str3;
        Exception e;
        try {
            if (TextUtils.isEmpty(str)) {
                str3 = str;
            } else {
                str3 = URLDecoder.decode(i.a(str), "utf-8");
            }
            try {
                if (!TextUtils.isEmpty(str2)) {
                    str2 = URLDecoder.decode(i.a(str2), "utf-8");
                    if (str2.contains("?")) {
                        str2 = str2.substring(0, str2.indexOf("?"));
                    }
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                if (!TextUtils.isEmpty(str3)) {
                    return str2.substring(str2.lastIndexOf("/") + 1);
                }
                if (str3.contains(".")) {
                    return str2.substring(str2.lastIndexOf("/") + 1);
                }
                return str3;
            }
        } catch (Exception e3) {
            e = e3;
            str3 = str;
            e.printStackTrace();
            if (!TextUtils.isEmpty(str3)) {
                return str2.substring(str2.lastIndexOf("/") + 1);
            }
            if (str3.contains(".")) {
                return str2.substring(str2.lastIndexOf("/") + 1);
            }
            return str3;
        }
        if (!TextUtils.isEmpty(str3)) {
            return str2.substring(str2.lastIndexOf("/") + 1);
        }
        if (str3.contains(".")) {
            return str2.substring(str2.lastIndexOf("/") + 1);
        }
        return str3;
    }

    public static void a(Context context, String str, String str2) {
        if (str2.toLowerCase(Locale.CHINA).endsWith(".apk")) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setFlags(268435456);
            intent.setDataAndType(Uri.parse("file://" + str.concat("/").concat(str2)), "application/vnd.android.package-archive");
            context.startActivity(intent);
        }
    }

    public static String a(Context context, String str) {
        PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 1);
        if (packageArchiveInfo != null) {
            return packageArchiveInfo.applicationInfo.packageName;
        }
        return "";
    }
}
