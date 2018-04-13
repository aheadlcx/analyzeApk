package com.budejie.www.push;

import android.text.TextUtils;
import com.budejie.www.util.aa;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    public static c a(String str) {
        aa.a("MessageTypeParser", "data-->" + str);
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("extra")) {
                    String decode;
                    String string = jSONObject.getString("extra");
                    try {
                        decode = URLDecoder.decode(string, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        aa.a("MessageTypeParser", "UnsupportedEncodingException-->" + e.toString());
                        e.printStackTrace();
                        decode = string;
                    }
                    JSONObject jSONObject2 = new JSONObject(decode);
                    aa.a("MessageTypeParser", "jObject-->" + jSONObject2);
                    if (jSONObject2.has("x")) {
                        jSONObject = jSONObject2.getJSONObject("x");
                        if (jSONObject.has("jie")) {
                            return a(jSONObject.getJSONObject("jie"));
                        }
                        if (jSONObject.has("adk")) {
                            JSONArray jSONArray = jSONObject.getJSONArray("adk");
                            List arrayList = new ArrayList();
                            for (int i = 0; i < jSONArray.length(); i++) {
                                arrayList.add(jSONArray.getString(i));
                            }
                            return new d((String) arrayList.get(0));
                        }
                    }
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
                aa.a("MessageTypeParser", "JSONException-->" + e2.toString());
            }
        }
        return null;
    }

    private static b a(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.has("id")) {
            return null;
        }
        b bVar = new b();
        String string = jSONObject.getString("id");
        int i = jSONObject.getInt("type");
        bVar.a = string;
        bVar.b = i;
        aa.a("MessageTypeParser", "type_id:" + string + "," + i);
        return bVar;
    }
}
