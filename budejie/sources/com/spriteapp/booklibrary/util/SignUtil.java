package com.spriteapp.booklibrary.util;

import android.net.Uri;
import com.ali.auth.third.login.LoginConstants;
import com.spriteapp.booklibrary.config.HuaXiSDK;
import com.tencent.connect.common.Constants;
import com.tencent.open.GameAppOperation;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class SignUtil {
    private static long currentTime;

    public static String createSign(String str) {
        Map treeMap = new TreeMap();
        treeMap.put(Constants.PARAM_CLIENT_ID, HuaXiSDK.getInstance().getClientId());
        treeMap.put("url", Uri.encode("http://s.hxdrive.net/"));
        treeMap.put("timestamp", String.valueOf(currentTime));
        treeMap.put(GameAppOperation.QQFAV_DATALINE_VERSION, str);
        return MD5Util.encryptMD5(getSignValue(treeMap) + HuaXiSDK.getInstance().getSignSecret());
    }

    private static String getSignValue(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            stringBuilder.append((String) entry.getKey()).append(LoginConstants.EQUAL).append((String) entry.getValue()).append(it.hasNext() ? "&" : "");
        }
        return stringBuilder.toString();
    }

    public static long getCurrentTime() {
        currentTime = System.currentTimeMillis() / 1000;
        return currentTime;
    }
}
