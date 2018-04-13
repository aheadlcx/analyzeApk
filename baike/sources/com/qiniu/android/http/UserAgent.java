package com.qiniu.android.http;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.qiniu.android.common.Constants;
import com.qiniu.android.utils.StringUtils;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Random;

public final class UserAgent {
    private static UserAgent a = new UserAgent();
    public final String id = a();
    public final String ua = a(this.id);

    private UserAgent() {
    }

    public static UserAgent instance() {
        return a;
    }

    private static String a() {
        return System.currentTimeMillis() + "" + new Random().nextInt(999);
    }

    private static String a(String str) {
        return String.format("QiniuAndroid/%s (%s; %s; %s", new Object[]{Constants.VERSION, b(), c(), str});
    }

    private static String b() {
        String str = VERSION.RELEASE;
        if (str == null) {
            return "";
        }
        return StringUtils.strip(str.trim());
    }

    private static String c() {
        String trim = Build.MODEL.trim();
        String a = a(Build.MANUFACTURER.trim(), trim);
        if (TextUtils.isEmpty(a)) {
            a = a(Build.BRAND.trim(), trim);
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (a == null) {
            a = "";
        }
        return StringUtils.strip(stringBuilder.append(a).append(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SERVER).append(trim).toString());
    }

    private static String a(String str, String str2) {
        CharSequence toLowerCase = str.toLowerCase(Locale.getDefault());
        if (toLowerCase.startsWith("unknown") || toLowerCase.startsWith("alps") || toLowerCase.startsWith("android") || toLowerCase.startsWith("sprd") || toLowerCase.startsWith("spreadtrum") || toLowerCase.startsWith("rockchip") || toLowerCase.startsWith("wondermedia") || toLowerCase.startsWith("mtk") || toLowerCase.startsWith("mt65") || toLowerCase.startsWith("nvidia") || toLowerCase.startsWith("brcm") || toLowerCase.startsWith("marvell") || str2.toLowerCase(Locale.getDefault()).contains(toLowerCase)) {
            return null;
        }
        return str;
    }

    public String getUa(String str) {
        String trim = ("" + str).trim();
        return new String((this.ua + "; " + trim.substring(0, Math.min(16, trim.length())) + ")").getBytes(Charset.forName("ISO-8859-1")));
    }
}
