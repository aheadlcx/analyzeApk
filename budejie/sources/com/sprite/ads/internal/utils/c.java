package com.sprite.ads.internal.utils;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class c {
    public static String a(String str, JSONObject jSONObject) {
        int i = 0;
        if (!str.contains(".")) {
            return jSONObject.has(str) ? jSONObject.get(str).toString() : null;
        } else {
            String[] split = str.split("\\.");
            if (split == null || split.length == 0) {
                return null;
            }
            String str2 = split[0];
            JSONArray names = jSONObject.names();
            while (i < names.length()) {
                String string = names.getString(i);
                if (str2.equals(string)) {
                    return a(str.substring(str.indexOf(".") + 1, str.length()), jSONObject.getJSONObject(string));
                }
                i++;
            }
            return null;
        }
    }

    public static String a(JSONObject jSONObject, String str) {
        return jSONObject.has(str) ? jSONObject.get(str).toString() : null;
    }

    public static HashMap<String, String> a(JSONObject jSONObject) {
        HashMap<String, String> hashMap = new HashMap();
        JSONArray names = jSONObject.names();
        for (int i = 0; i < names.length(); i++) {
            String string = names.getString(i);
            hashMap.put(string, a(jSONObject, string));
        }
        return hashMap;
    }

    public static Map<String, String> a(String str) {
        return a(new JSONObject(str));
    }

    public static int b(JSONObject jSONObject, String str) {
        return jSONObject.has(str) ? jSONObject.getInt(str) : 0;
    }

    public static double c(JSONObject jSONObject, String str) {
        return jSONObject.has(str) ? jSONObject.getDouble(str) : 0.0d;
    }
}
