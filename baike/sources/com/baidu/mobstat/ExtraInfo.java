package com.baidu.mobstat;

import android.text.TextUtils;
import org.json.JSONObject;

public class ExtraInfo {
    String a = "";
    String b = "";
    String c = "";
    String d = "";
    String e = "";
    String f = "";
    String g = "";
    String h = "";
    String i = "";
    String j = "";

    private static boolean a(String str, int i) {
        if (str == null) {
            return false;
        }
        int length;
        try {
            length = str.getBytes().length;
        } catch (Exception e) {
            length = 0;
        }
        if (length > i) {
            return true;
        }
        return false;
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if (a(str, 1024)) {
            return "";
        }
        return str;
    }

    public String getV1() {
        return this.a;
    }

    public void setV1(String str) {
        this.a = a(str);
    }

    public String getV2() {
        return this.b;
    }

    public void setV2(String str) {
        this.b = a(str);
    }

    public String getV3() {
        return this.c;
    }

    public void setV3(String str) {
        this.c = a(str);
    }

    public String getV4() {
        return this.d;
    }

    public void setV4(String str) {
        this.d = a(str);
    }

    public String getV5() {
        return this.e;
    }

    public void setV5(String str) {
        this.e = a(str);
    }

    public String getV6() {
        return this.f;
    }

    public void setV6(String str) {
        this.f = a(str);
    }

    public String getV7() {
        return this.g;
    }

    public void setV7(String str) {
        this.g = a(str);
    }

    public String getV8() {
        return this.h;
    }

    public void setV8(String str) {
        this.h = a(str);
    }

    public String getV9() {
        return this.i;
    }

    public void setV9(String str) {
        this.i = a(str);
    }

    public String getV10() {
        return this.j;
    }

    public void setV10(String str) {
        this.j = a(str);
    }

    public JSONObject dumpToJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.a)) {
                jSONObject.put("v1", this.a);
            }
            if (!TextUtils.isEmpty(this.b)) {
                jSONObject.put("v2", this.b);
            }
            if (!TextUtils.isEmpty(this.c)) {
                jSONObject.put("v3", this.c);
            }
            if (!TextUtils.isEmpty(this.d)) {
                jSONObject.put("v4", this.d);
            }
            if (!TextUtils.isEmpty(this.e)) {
                jSONObject.put("v5", this.e);
            }
            if (!TextUtils.isEmpty(this.f)) {
                jSONObject.put("v6", this.f);
            }
            if (!TextUtils.isEmpty(this.g)) {
                jSONObject.put("v7", this.g);
            }
            if (!TextUtils.isEmpty(this.h)) {
                jSONObject.put("v8", this.h);
            }
            if (!TextUtils.isEmpty(this.i)) {
                jSONObject.put("v9", this.i);
            }
            if (!TextUtils.isEmpty(this.j)) {
                jSONObject.put("v10", this.j);
            }
        } catch (Throwable e) {
            db.c(e);
        }
        return jSONObject;
    }
}
