package com.coloros.mcssdk.e;

import android.text.TextUtils;
import com.coloros.mcssdk.c.c;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends c {
    public static final String a = null;
    private String b;
    private String c;
    private String d;
    private String e;
    private int f;
    private String g;
    private int h = -2;

    public static List<e> a(String str, String str2, String str3, String str4) {
        JSONException e;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        List<e> arrayList;
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray(str2);
            arrayList = new ArrayList();
            int i = 0;
            while (i < jSONArray.length()) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    e eVar = new e();
                    eVar.b(jSONObject.getString(str4));
                    eVar.a(jSONObject.getString(str3));
                    arrayList.add(eVar);
                    i++;
                } catch (JSONException e2) {
                    e = e2;
                }
            }
        } catch (JSONException e3) {
            JSONException jSONException = e3;
            arrayList = null;
            e = jSONException;
            e.printStackTrace();
            c.a("parseToSubscribeResultList--" + arrayList);
            return arrayList;
        }
        c.a("parseToSubscribeResultList--" + arrayList);
        return arrayList;
    }

    public int a() {
        return 4105;
    }

    public void a(int i) {
        this.f = i;
    }

    public void a(String str) {
        this.b = str;
    }

    public int b() {
        return this.f;
    }

    public void b(int i) {
        this.h = i;
    }

    public void b(String str) {
        this.c = str;
    }

    public String c() {
        return this.g;
    }

    public void c(String str) {
        this.g = str;
    }

    public int d() {
        return this.h;
    }

    public String toString() {
        return "type:4105,messageID:" + this.j + ",taskID:" + this.l + ",appPackage:" + this.k + ",registerID:" + this.d + ",sdkVersion:" + this.e + ",command:" + this.f + ",responseCode:" + this.h + ",content:" + this.g;
    }
}
