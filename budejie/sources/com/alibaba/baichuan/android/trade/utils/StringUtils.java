package com.alibaba.baichuan.android.trade.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import com.ali.auth.third.core.model.Constants;
import com.ali.auth.third.login.LoginConstants;
import com.alibaba.fastjson.JSONObject;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StringUtils {
    public static boolean compatVersion(String str, String str2) {
        boolean z = true;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        String[] split = str.split("[.]");
        String[] split2 = str2.split("[.]");
        int length = split.length < split2.length ? split.length : split2.length;
        int i = 0;
        while (i < length) {
            int intValue = Integer.valueOf(split[i]).intValue();
            int intValue2 = Integer.valueOf(split2[i]).intValue();
            if (intValue != intValue2) {
                return intValue > intValue2;
            } else {
                i++;
            }
        }
        if (split.length <= split2.length) {
            z = false;
        }
        return z;
    }

    public static String encod(String str) {
        try {
            str = URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String map2String(Map map) {
        return new JSONObject(map).toJSONString();
    }

    public static boolean obj2Boolean(Object obj) {
        return obj == null ? false : obj instanceof Boolean ? ((Boolean) obj).booleanValue() : obj instanceof String ? str2Boolean((String) obj) : false;
    }

    public static int[] obj2IntArray(Object obj) {
        if (obj == null || !(obj instanceof List)) {
            return null;
        }
        List list = (List) obj;
        int[] iArr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            iArr[i] = (int) obj2Number(list.get(i));
        }
        return iArr;
    }

    public static List obj2List(Object obj) {
        try {
            return (List) obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Long obj2Long(Object obj) {
        try {
            return Long.valueOf(Long.parseLong(obj.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return new Long(-1);
        }
    }

    public static Map obj2MapObject(Object obj) {
        try {
            return (Map) obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map obj2MapString(Object obj) {
        try {
            return (Map) obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static double obj2Number(Object obj) {
        double d = 0.0d;
        if (obj != null) {
            try {
                if (!TextUtils.isEmpty(obj.toString())) {
                    d = Double.parseDouble(obj.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return d;
    }

    public static String obj2String(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static String[] obj2StringArray(Object obj) {
        if (obj == null || !(obj instanceof List)) {
            return null;
        }
        List list = (List) obj;
        String[] strArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = String.valueOf(list.get(i));
        }
        return strArr;
    }

    public static Map parserUrl(String str) {
        Map concurrentHashMap = new ConcurrentHashMap();
        parserUrl(str, concurrentHashMap);
        return concurrentHashMap;
    }

    public static void parserUrl(String str, Map map) {
        if (!TextUtils.isEmpty(str) && map != null) {
            Object obj = null;
            String[] split = str.trim().split("[?]");
            if (split.length > 1) {
                obj = split[1];
            }
            if (!TextUtils.isEmpty(obj)) {
                for (String split2 : obj.split("&")) {
                    String[] split3 = split2.split(LoginConstants.EQUAL);
                    if (split3.length > 1) {
                        map.put(split3[0], split3[1]);
                    } else if (!TextUtils.isEmpty(split3[0])) {
                        map.put(split3[0], "");
                    }
                }
            }
        }
    }

    @SuppressLint({"DefaultLocale"})
    public static boolean str2Boolean(String str) {
        return Constants.SERVICE_SCOPE_FLAG_VALUE.equals(str.toLowerCase()) || "1".equals(str) || "YES".equals(str);
    }
}
