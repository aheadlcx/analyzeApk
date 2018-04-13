package com.budejie.www.activity.video;

import com.umeng.analytics.pro.x;
import java.util.HashMap;
import java.util.Map;

public class h {
    public static Map<String, Object> a(String str) {
        Map<String, Object> hashMap = new HashMap();
        try {
            Process exec = Runtime.getRuntime().exec(str);
            hashMap.put("input", exec.getInputStream());
            hashMap.put(x.aF, exec.getErrorStream());
            return hashMap;
        } catch (Exception e) {
            return null;
        }
    }
}
