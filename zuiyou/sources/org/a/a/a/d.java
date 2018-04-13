package org.a.a.a;

public class d {
    private String a;
    private String b;
    private String c;
    private int d;
    private int e;
    private int f;
    private a g = new a();
    private d h;
    private k i;

    public d(String str, int i, int i2, int i3, k kVar) {
        this.a = str;
        this.d = i;
        this.e = i2;
        this.f = i3;
        this.i = kVar;
        this.b = a(str, false);
        this.c = a(str);
    }

    public String a(String str, boolean z) {
        int indexOf = str.indexOf(58);
        if (indexOf == -1) {
            return z ? "" : this.i.b();
        } else {
            String substring = str.substring(0, indexOf);
            if (substring.equals("xml")) {
                return "http://www.w3.org/XML/1998/namespace";
            }
            return new StringBuffer().append("urn:x-prefix:").append(substring).toString().intern();
        }
    }

    public String a(String str) {
        int indexOf = str.indexOf(58);
        return indexOf == -1 ? str : str.substring(indexOf + 1).intern();
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.f;
    }

    public a f() {
        return this.g;
    }

    public d g() {
        return this.h;
    }

    public boolean a(d dVar) {
        return (this.d & dVar.e) != 0;
    }

    public void a(a aVar, String str, String str2, String str3) {
        if (!str.equals("xmlns") && !str.startsWith("xmlns:")) {
            String a = a(str, true);
            String a2 = a(str);
            int index = aVar.getIndex(str);
            if (index == -1) {
                String str4;
                String str5;
                String intern = str.intern();
                if (str2 == null) {
                    str4 = "CDATA";
                } else {
                    str4 = str2;
                }
                if (str4.equals("CDATA")) {
                    str5 = str3;
                } else {
                    str5 = b(str3);
                }
                aVar.a(a, a2, intern, str4, str5);
                return;
            }
            String type;
            String str6;
            if (str2 == null) {
                type = aVar.getType(index);
            } else {
                type = str2;
            }
            if (type.equals("CDATA")) {
                str6 = str3;
            } else {
                str6 = b(str3);
            }
            aVar.a(index, a, a2, str, type, str6);
        }
    }

    public static String b(String str) {
        if (str == null) {
            return str;
        }
        str = str.trim();
        if (str.indexOf("  ") == -1) {
            return str;
        }
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length);
        Object obj = null;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == ' ') {
                if (obj == null) {
                    stringBuffer.append(charAt);
                }
                obj = 1;
            } else {
                stringBuffer.append(charAt);
                obj = null;
            }
        }
        return stringBuffer.toString();
    }

    public void a(String str, String str2, String str3) {
        a(this.g, str, str2, str3);
    }

    public void b(d dVar) {
        this.h = dVar;
    }
}
