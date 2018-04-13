package com.sina.weibo.sdk.web;

import android.text.TextUtils;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    private int a = -2;
    private String b;

    private a() {
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public static a a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        a aVar = new a();
        try {
            JSONObject jSONObject = new JSONObject(str);
            aVar.a = jSONObject.optInt(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, -2);
            aVar.b = jSONObject.optString("data", "");
            return aVar;
        } catch (JSONException e) {
            return aVar;
        }
    }
}
