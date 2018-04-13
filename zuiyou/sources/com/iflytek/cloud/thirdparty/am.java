package com.iflytek.cloud.thirdparty;

import android.os.Bundle;
import com.iflytek.aiui.AIUIConstant;
import com.umeng.analytics.b.g;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class am {
    public static Bundle a(Map<String, byte[]> map) {
        if (map == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        for (Entry entry : map.entrySet()) {
            bundle.putByteArray((String) entry.getKey(), (byte[]) entry.getValue());
        }
        return bundle;
    }

    public static String a() {
        try {
            return new JSONObject("{\"data\": [{\"params\": {\"sub\": \"asr\"},\"content\": [{\"dte\": \"utf8\", \"dtf\": \"json\", \"cnt_id\": \"0\" }]}]}").toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static JSONObject a(int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("power", -1);
            jSONObject.put("beam", i);
            jSONObject.put("angle", -1);
            jSONObject.put(g.b, -1);
            jSONObject.put("CMScore", -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("rc", 4);
            jSONObject.put(AIUIConstant.WORK_MODE_INTENT, jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
