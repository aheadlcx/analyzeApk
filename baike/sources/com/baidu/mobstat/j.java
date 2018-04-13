package com.baidu.mobstat;

import android.text.TextUtils;
import com.tencent.stat.DeviceInfo;
import org.json.JSONObject;

class j {
    public String a;
    public String b;
    public int c;

    private j() {
        this.c = 2;
    }

    public static j a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Object string = jSONObject.getString("deviceid");
            String string2 = jSONObject.getString("imei");
            int i = jSONObject.getInt(DeviceInfo.TAG_VERSION);
            if (TextUtils.isEmpty(string) || string2 == null) {
                return null;
            }
            j jVar = new j();
            jVar.a = string;
            jVar.b = string2;
            jVar.c = i;
            return jVar;
        } catch (Throwable e) {
            g.b(e);
            return null;
        }
    }

    public String a() {
        try {
            return new JSONObject().put("deviceid", this.a).put("imei", this.b).put(DeviceInfo.TAG_VERSION, this.c).toString();
        } catch (Throwable e) {
            g.b(e);
            return null;
        }
    }

    public String b() {
        String str = this.b;
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        return this.a + "|" + new StringBuffer(str).reverse().toString();
    }
}
