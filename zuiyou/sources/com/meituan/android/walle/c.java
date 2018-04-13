package com.meituan.android.walle;

import com.umeng.analytics.b.g;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class c {
    public static b a(File file) {
        Map b = b(file);
        if (b == null) {
            return null;
        }
        String str = (String) b.get(g.b);
        b.remove(g.b);
        return new b(str, b);
    }

    public static Map<String, String> b(File file) {
        try {
            String c = c(file);
            if (c == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(c);
            Iterator keys = jSONObject.keys();
            Map<String, String> hashMap = new HashMap();
            while (keys.hasNext()) {
                String obj = keys.next().toString();
                hashMap.put(obj, jSONObject.getString(obj));
            }
            return hashMap;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String c(File file) {
        return e.a(file, 1903654775);
    }
}
