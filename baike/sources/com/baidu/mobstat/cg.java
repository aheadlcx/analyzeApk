package com.baidu.mobstat;

import org.json.JSONObject;

class cg {
    private String a;
    private String b;
    private String c;
    private long d;
    private long e;
    private boolean f;
    private JSONObject g;

    public cg(String str, String str2, String str3, long j, long j2, boolean z, ExtraInfo extraInfo) {
        this.b = str;
        this.c = str2;
        this.a = str3;
        this.d = j;
        this.e = j2;
        this.f = z;
        JSONObject jSONObject = new JSONObject();
        if (extraInfo != null) {
            jSONObject = extraInfo.dumpToJson();
        }
        this.g = jSONObject;
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public long c() {
        return this.d;
    }

    public long d() {
        return this.e;
    }

    public JSONObject e() {
        return this.g;
    }

    public boolean f() {
        return this.f;
    }

    public void a(cg cgVar) {
        this.a = cgVar.a;
        this.b = cgVar.b;
        this.c = cgVar.c;
        this.d = cgVar.d;
        this.e = cgVar.e;
        this.f = cgVar.f;
        this.g = cgVar.g;
    }
}
