package com.baidu.lbsapi.auth;

import org.json.JSONException;
import org.json.JSONObject;

class a {
    static String a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", -1);
            jSONObject.put("message", str);
        } catch (JSONException e) {
        }
        return jSONObject.toString();
    }
}
