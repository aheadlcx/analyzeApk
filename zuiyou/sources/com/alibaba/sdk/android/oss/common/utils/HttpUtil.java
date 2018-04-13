package com.alibaba.sdk.android.oss.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

public class HttpUtil {
    static final /* synthetic */ boolean $assertionsDisabled = (!HttpUtil.class.desiredAssertionStatus());
    private static final String ISO_8859_1_CHARSET = "iso-8859-1";
    private static final String JAVA_CHARSET = "utf-8";

    public static String urlEncode(String str, String str2) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, str2).replace("+", "%20").replace("*", "%2A").replace("%7E", "~").replace("%2F", "/");
        } catch (Throwable e) {
            throw new IllegalArgumentException("failed to encode url!", e);
        }
    }

    public static String paramToQueryString(Map<String, String> map, String str) throws UnsupportedEncodingException {
        if (map == null || map.size() == 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Entry entry : map.entrySet()) {
            String str2 = (String) entry.getKey();
            String str3 = (String) entry.getValue();
            if (obj == null) {
                stringBuilder.append("&");
            }
            stringBuilder.append(str2);
            if (str3 != null) {
                stringBuilder.append("=").append(urlEncode(str3, str));
            }
            obj = null;
        }
        return stringBuilder.toString();
    }

    public static void convertHeaderCharsetFromIso88591(Map<String, String> map) {
        convertHeaderCharset(map, ISO_8859_1_CHARSET, "utf-8");
    }

    public static void convertHeaderCharsetToIso88591(Map<String, String> map) {
        convertHeaderCharset(map, "utf-8", ISO_8859_1_CHARSET);
    }

    private static void convertHeaderCharset(Map<String, String> map, String str, String str2) {
        if ($assertionsDisabled || map != null) {
            for (Entry entry : map.entrySet()) {
                if (entry.getValue() != null) {
                    try {
                        entry.setValue(new String(((String) entry.getValue()).getBytes(str), str2));
                    } catch (UnsupportedEncodingException e) {
                        throw new AssertionError("Invalid charset name.");
                    }
                }
            }
            return;
        }
        throw new AssertionError();
    }
}
