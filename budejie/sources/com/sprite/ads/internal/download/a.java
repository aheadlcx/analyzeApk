package com.sprite.ads.internal.download;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.sprite.ads.internal.utils.f;
import java.net.URLDecoder;
import java.util.Locale;

public final class a {
    public static String a(Context context, String str) {
        PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 1);
        return packageArchiveInfo != null ? packageArchiveInfo.applicationInfo.packageName : "";
    }

    public static String a(String str, String str2) {
        String decode;
        Exception e;
        try {
            decode = !TextUtils.isEmpty(str) ? URLDecoder.decode(f.a(str), "utf-8") : str;
            try {
                if (!TextUtils.isEmpty(str2)) {
                    str2 = URLDecoder.decode(f.a(str2), "utf-8");
                    if (str2.contains("?")) {
                        str2 = str2.substring(0, str2.indexOf("?"));
                    }
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return TextUtils.isEmpty(decode) ? decode.contains(".") ? decode : str2.substring(str2.lastIndexOf("/") + 1) : str2.substring(str2.lastIndexOf("/") + 1);
            }
        } catch (Exception e3) {
            e = e3;
            decode = str;
            e.printStackTrace();
            if (TextUtils.isEmpty(decode)) {
            }
        }
        if (TextUtils.isEmpty(decode)) {
        }
    }

    public static void a(Context context, String str, String str2) {
        if (str2.toLowerCase(Locale.CHINA).endsWith(".apk")) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setFlags(268435456);
            intent.setDataAndType(Uri.parse("file://" + str.concat("/").concat(str2)), "application/vnd.android.package-archive");
            context.startActivity(intent);
        }
    }
}
