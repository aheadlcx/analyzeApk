package com.huawei.hms.support.api.push.a.a.a;

import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.huawei.hms.support.api.client.ApiClient;
import com.huawei.hms.support.api.client.SubAppInfo;
import com.iflytek.cloud.SpeechUtility;
import com.meizu.cloud.pushsdk.platform.pushstrategy.Strategy;
import com.tencent.tinker.loader.hotplug.EnvConsts;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class a {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static JSONArray a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return new JSONArray(str);
            } catch (JSONException e) {
                if (!com.huawei.hms.support.log.a.a()) {
                    return null;
                }
                com.huawei.hms.support.log.a.a("BaseUtil", "cast jsonString to jsonArray error");
                return null;
            }
        } else if (!com.huawei.hms.support.log.a.a()) {
            return null;
        } else {
            com.huawei.hms.support.log.a.a("BaseUtil", "jsonString is null");
            return null;
        }
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return "";
        }
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            cArr[i * 2] = a[(b & 240) >> 4];
            cArr[(i * 2) + 1] = a[b & 15];
        }
        return new String(cArr);
    }

    public static byte[] b(String str) {
        byte[] bArr = new byte[(str.length() / 2)];
        try {
            byte[] bytes = str.getBytes("UTF-8");
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = (byte) (((byte) (Byte.decode("0x" + new String(new byte[]{bytes[i * 2]}, "UTF-8")).byteValue() << 4)) ^ Byte.decode("0x" + new String(new byte[]{bytes[(i * 2) + 1]}, "UTF-8")).byteValue());
            }
        } catch (UnsupportedEncodingException e) {
            if (com.huawei.hms.support.log.a.a()) {
                com.huawei.hms.support.log.a.a("BaseUtil", "hexString2ByteArray error" + e.getMessage());
            }
        }
        return bArr;
    }

    public static JSONArray a(List<String> list, Context context) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        c cVar = new c(context, "tags_info");
        for (String str : list) {
            if (cVar.c(str)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("tagKey", str);
                jSONObject.put("opType", 2);
                if (jSONObject.length() > 0) {
                    jSONArray.put(jSONObject);
                }
            } else if (com.huawei.hms.support.log.a.a()) {
                com.huawei.hms.support.log.a.a("BaseUtil", str + " not exist, need not to remove");
            }
        }
        return jSONArray;
    }

    public static void a(ApiClient apiClient, String str) {
        if (apiClient != null) {
            Map hashMap = new HashMap();
            hashMap.put(EnvConsts.PACKAGE_MANAGER_SRVNAME, apiClient.getPackageName());
            hashMap.put("sdk_ver", String.valueOf(20502300));
            Object obj = null;
            SubAppInfo subAppInfo = apiClient.getSubAppInfo();
            if (subAppInfo != null) {
                obj = subAppInfo.getSubAppID();
            }
            if (obj == null) {
                obj = apiClient.getAppID();
            }
            hashMap.put(Strategy.APP_ID, obj);
            String[] split = str.split("\\.");
            if (split.length == 2) {
                hashMap.put(NotificationCompat.CATEGORY_SERVICE, split[0]);
                hashMap.put("api_name", split[1]);
            }
            hashMap.put(SpeechUtility.TAG_RESOURCE_RESULT, String.valueOf(0));
            hashMap.put("cost_time", String.valueOf(0));
            com.huawei.hms.support.b.a.a().a(apiClient.getContext(), "HMS_SDK_API_CALL", hashMap);
        }
    }
}
