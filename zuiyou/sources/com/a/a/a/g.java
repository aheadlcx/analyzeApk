package com.a.a.a;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class g {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;

    public String a() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public String b() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public String c() {
        return this.a;
    }

    public void c(String str) {
        this.a = str;
    }

    public String d() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public String e() {
        return this.e;
    }

    public void e(String str) {
        this.e = str;
    }

    public String f() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("callbackId", c());
        jSONObject.put("data", d());
        jSONObject.put("handlerName", e());
        Object b = b();
        if (TextUtils.isEmpty(b)) {
            jSONObject.put("responseData", b);
        } else {
            try {
                jSONObject.put("responseData", JSON.parseObject(b));
            } catch (Exception e) {
                try {
                    jSONObject.put("responseData", JSON.parseArray(b));
                } catch (Exception e2) {
                    jSONObject.put("responseData", b);
                }
            }
        }
        jSONObject.put("responseId", a());
        return jSONObject.toString();
    }

    public static List<g> f(String str) {
        List<g> arrayList = new ArrayList();
        JSONArray parseArray = JSON.parseArray(str);
        for (int i = 0; i < parseArray.size(); i++) {
            String string;
            g gVar = new g();
            JSONObject jSONObject = parseArray.getJSONObject(i);
            if (jSONObject.containsKey("handlerName")) {
                string = jSONObject.getString("handlerName");
            } else {
                string = null;
            }
            gVar.e(string);
            if (jSONObject.containsKey("callbackId")) {
                string = jSONObject.getString("callbackId");
            } else {
                string = null;
            }
            gVar.c(string);
            if (jSONObject.containsKey("responseData")) {
                string = jSONObject.getString("responseData");
            } else {
                string = null;
            }
            gVar.b(string);
            if (jSONObject.containsKey("responseId")) {
                string = jSONObject.getString("responseId");
            } else {
                string = null;
            }
            gVar.a(string);
            if (jSONObject.containsKey("data")) {
                string = jSONObject.getString("data");
            } else {
                string = null;
            }
            gVar.d(string);
            arrayList.add(gVar);
        }
        return arrayList;
    }
}
