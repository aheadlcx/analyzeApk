package com.loc;

import java.util.HashMap;
import java.util.Map;

@ag(a = "file")
public class bc {
    @ah(a = "fname", b = 6)
    private String a;
    @ah(a = "md", b = 6)
    private String b;
    @ah(a = "sname", b = 6)
    private String c;
    @ah(a = "version", b = 6)
    private String d;
    @ah(a = "dversion", b = 6)
    private String e;
    @ah(a = "status", b = 6)
    private String f;

    private bc() {
    }

    public bc(bc$a bc_a) {
        this.a = bc$a.a(bc_a);
        this.b = bc$a.b(bc_a);
        this.c = bc$a.c(bc_a);
        this.d = bc$a.d(bc_a);
        this.e = bc$a.e(bc_a);
        this.f = bc$a.f(bc_a);
    }

    public static String a(String str) {
        Map hashMap = new HashMap();
        hashMap.put("sname", str);
        return af.a(hashMap);
    }

    public static String a(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("sname", str);
        hashMap.put("dversion", str2);
        return af.a(hashMap);
    }

    public static String a(String str, String str2, String str3, String str4) {
        Map hashMap = new HashMap();
        hashMap.put("fname", str);
        hashMap.put("sname", str2);
        hashMap.put("dversion", str4);
        hashMap.put("version", str3);
        return af.a(hashMap);
    }

    public static String b(String str) {
        Map hashMap = new HashMap();
        hashMap.put("fname", str);
        return af.a(hashMap);
    }

    public static String b(String str, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("sname", str);
        hashMap.put("status", str2);
        return af.a(hashMap);
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final String c() {
        return this.c;
    }

    public final void c(String str) {
        this.f = str;
    }

    public final String d() {
        return this.d;
    }

    public final String e() {
        return this.e;
    }

    public final String f() {
        return this.f;
    }
}
