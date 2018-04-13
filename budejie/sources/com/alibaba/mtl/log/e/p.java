package com.alibaba.mtl.log.e;

import android.text.TextUtils;
import com.ali.auth.third.login.LoginConstants;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class p {
    public static String convertObjectToString(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof String) {
            return ((String) obj).toString();
        }
        if (obj instanceof Integer) {
            return "" + ((Integer) obj).intValue();
        }
        if (obj instanceof Long) {
            return "" + ((Long) obj).longValue();
        }
        if (obj instanceof Double) {
            return "" + ((Double) obj).doubleValue();
        }
        if (obj instanceof Float) {
            return "" + ((Float) obj).floatValue();
        }
        if (obj instanceof Short) {
            return "" + ((Short) obj).shortValue();
        }
        if (obj instanceof Byte) {
            return "" + ((Byte) obj).byteValue();
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).toString();
        }
        if (obj instanceof Character) {
            return ((Character) obj).toString();
        }
        return obj.toString();
    }

    public static Map<String, String> b(Map<String, String> map) {
        if (map == null) {
            return map;
        }
        Map<String, String> hashMap = new HashMap();
        for (String str : map.keySet()) {
            if (str instanceof String) {
                String str2 = (String) map.get(str);
                if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
                    try {
                        hashMap.put(URLEncoder.encode(str, "UTF-8"), URLEncoder.encode(str2, "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return hashMap;
    }

    public static String c(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        Object obj = 1;
        for (String str : map.keySet()) {
            Object obj2;
            String convertObjectToString = convertObjectToString(map.get(str));
            String str2 = convertObjectToString(str2);
            if (!(convertObjectToString == null || str2 == null)) {
                if (obj != null) {
                    stringBuffer.append(str2 + LoginConstants.EQUAL + convertObjectToString);
                    obj2 = null;
                    obj = obj2;
                } else {
                    stringBuffer.append(",").append(str2 + LoginConstants.EQUAL + convertObjectToString);
                }
            }
            obj2 = obj;
            obj = obj2;
        }
        return stringBuffer.toString();
    }
}
