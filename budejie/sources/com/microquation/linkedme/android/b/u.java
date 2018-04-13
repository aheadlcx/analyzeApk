package com.microquation.linkedme.android.b;

import com.baidu.mobads.openad.c.b;
import com.umeng.analytics.pro.x;
import org.json.JSONObject;

public class u {
    public final byte[] a;
    private int b;
    private String c;
    private Object d;

    public u(String str, int i) {
        this.c = str;
        this.b = i;
        this.a = new byte[0];
    }

    public u(String str, int i, byte[] bArr) {
        this.c = str;
        this.b = i;
        this.a = bArr;
    }

    public int a() {
        return this.b;
    }

    public void a(Object obj) {
        this.d = obj;
    }

    public JSONObject b() {
        return this.d instanceof JSONObject ? (JSONObject) this.d : null;
    }

    public String c() {
        String str = "";
        try {
            JSONObject b = b();
            if (b != null && b.has(x.aF) && b.getJSONObject(x.aF).has(b.EVENT_MESSAGE)) {
                str = b.getJSONObject(x.aF).getString(b.EVENT_MESSAGE);
                if (str != null && str.trim().length() > 0) {
                    str = str + ".";
                }
            }
        } catch (Exception e) {
        }
        return str;
    }
}
