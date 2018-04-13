package com.google.analytics.tracking.android;

import android.text.TextUtils;
import com.ali.auth.third.core.model.Constants;
import com.ali.auth.third.login.LoginConstants;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.httpclient.HttpState;

class Utils {
    private static final char[] HEXBYTES = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    Utils() {
    }

    public static Map<String, String> parseURLParameters(String str) {
        Map<String, String> hashMap = new HashMap();
        for (String split : str.split("&")) {
            String[] split2 = split.split(LoginConstants.EQUAL);
            if (split2.length > 1) {
                hashMap.put(split2[0], split2[1]);
            } else if (split2.length == 1 && split2[0].length() != 0) {
                hashMap.put(split2[0], null);
            }
        }
        return hashMap;
    }

    public static double safeParseDouble(String str) {
        return safeParseDouble(str, 0.0d);
    }

    public static double safeParseDouble(String str, double d) {
        if (str != null) {
            try {
                d = Double.parseDouble(str);
            } catch (NumberFormatException e) {
            }
        }
        return d;
    }

    public static long safeParseLong(String str) {
        long j = 0;
        if (str != null) {
            try {
                j = Long.parseLong(str);
            } catch (NumberFormatException e) {
            }
        }
        return j;
    }

    public static boolean safeParseBoolean(String str, boolean z) {
        if (str == null) {
            return z;
        }
        if (str.equalsIgnoreCase(Constants.SERVICE_SCOPE_FLAG_VALUE) || str.equalsIgnoreCase("yes") || str.equalsIgnoreCase("1")) {
            return true;
        }
        if (str.equalsIgnoreCase(HttpState.PREEMPTIVE_DEFAULT) || str.equalsIgnoreCase("no") || str.equalsIgnoreCase("0")) {
            return false;
        }
        return z;
    }

    public static String filterCampaign(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.contains("?")) {
            String[] split = str.split("[\\?]");
            if (split.length > 1) {
                str = split[1];
            }
        }
        if (str.contains("%3D")) {
            try {
                str = URLDecoder.decode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } else if (!str.contains(LoginConstants.EQUAL)) {
            return null;
        }
        Map parseURLParameters = parseURLParameters(str);
        String[] strArr = new String[]{"dclid", "utm_source", "gclid", "utm_campaign", "utm_medium", "utm_term", "utm_content", "utm_id", "gmob_t"};
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            if (!TextUtils.isEmpty((CharSequence) parseURLParameters.get(strArr[i]))) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append("&");
                }
                stringBuilder.append(strArr[i]).append(LoginConstants.EQUAL).append((String) parseURLParameters.get(strArr[i]));
            }
        }
        return stringBuilder.toString();
    }

    static String getLanguage(Locale locale) {
        if (locale == null || TextUtils.isEmpty(locale.getLanguage())) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(locale.getLanguage().toLowerCase());
        if (!TextUtils.isEmpty(locale.getCountry())) {
            stringBuilder.append("-").append(locale.getCountry().toLowerCase());
        }
        return stringBuilder.toString();
    }

    static String hexEncode(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            cArr[i * 2] = HEXBYTES[i2 >> 4];
            cArr[(i * 2) + 1] = HEXBYTES[i2 & 15];
        }
        return new String(cArr);
    }

    static int fromHexDigit(char c) {
        int i = c - 48;
        if (i > 9) {
            return i - 7;
        }
        return i;
    }

    static byte[] hexDecode(String str) {
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) ((fromHexDigit(str.charAt(i * 2)) << 4) | fromHexDigit(str.charAt((i * 2) + 1)));
        }
        return bArr;
    }

    public static void putIfAbsent(Map<String, String> map, String str, String str2) {
        if (!map.containsKey(str)) {
            map.put(str, str2);
        }
    }
}
