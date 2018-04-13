package com.sina.weibo.sdk.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.ali.auth.third.login.LoginConstants;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.UUID;

public class j {
    public static Bundle a(String str) {
        try {
            URL url = new URL(str);
            Bundle b = b(url.getQuery());
            b.putAll(b(url.getRef()));
            return b;
        } catch (MalformedURLException e) {
            return new Bundle();
        }
    }

    public static Bundle b(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            for (String split : str.split("&")) {
                String[] split2 = split.split(LoginConstants.EQUAL);
                try {
                    bundle.putString(URLDecoder.decode(split2[0], "UTF-8"), URLDecoder.decode(split2[1], "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return bundle;
    }

    public static String a() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String a(Context context, String str) {
        String str2 = null;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            for (Signature toByteArray : packageInfo.signatures) {
                byte[] toByteArray2 = toByteArray.toByteArray();
                if (toByteArray2 != null) {
                    str2 = e.a(toByteArray2);
                    break;
                }
            }
        } catch (NameNotFoundException e) {
        } catch (Exception e2) {
        }
        return str2;
    }

    public static String c(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public static String b(Context context, String str) {
        a a = a.a(context);
        Object b = a.b();
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        a.a(str);
        return "";
    }

    public static String a(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        Object packageName = context.getPackageName();
        String str = "weibosdk";
        if (!TextUtils.isEmpty(packageName) && packageName.contains("com.sina.weibo")) {
            str = "weibo";
        }
        stringBuilder.append(Build.MANUFACTURER).append("-").append(Build.MODEL);
        stringBuilder.append("__");
        stringBuilder.append(str);
        stringBuilder.append("__");
        try {
            stringBuilder.append("0041005000".replaceAll("\\s+", "_"));
        } catch (Exception e) {
            stringBuilder.append("unknown");
        }
        stringBuilder.append("__").append(AlibcConstants.PF_ANDROID).append("__android").append(VERSION.RELEASE);
        return stringBuilder.toString();
    }
}
