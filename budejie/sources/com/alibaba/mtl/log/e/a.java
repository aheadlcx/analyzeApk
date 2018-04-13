package com.alibaba.mtl.log.e;

import android.text.TextUtils;
import com.qq.e.comm.constants.Constants.KEYS;
import org.json.JSONException;
import org.json.JSONObject;

public class a {

    public static class a {
        public static a a = new a();
        public boolean G = false;
        public String ad = null;

        public boolean i() {
            if ("E0102".equalsIgnoreCase(this.ad)) {
                return true;
            }
            return false;
        }

        public boolean j() {
            if ("E0111".equalsIgnoreCase(this.ad) || "E0112".equalsIgnoreCase(this.ad)) {
                return true;
            }
            return false;
        }
    }

    public static a a(String str) {
        a aVar = new a();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("success")) {
                Object string = jSONObject.getString("success");
                if (!TextUtils.isEmpty(string) && string.equals("success")) {
                    aVar.G = true;
                }
            }
            if (jSONObject.has(KEYS.RET)) {
                aVar.ad = jSONObject.getString(KEYS.RET);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return aVar;
    }
}
