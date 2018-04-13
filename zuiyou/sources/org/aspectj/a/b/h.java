package org.aspectj.a.b;

import java.lang.reflect.Modifier;

class h {
    static h j = new h();
    static h k = new h();
    static h l = new h();
    boolean a = true;
    boolean b = true;
    boolean c = false;
    boolean d = false;
    boolean e = false;
    boolean f = true;
    boolean g = true;
    boolean h = true;
    int i;

    h() {
    }

    static {
        j.a = true;
        j.b = false;
        j.c = false;
        j.d = false;
        j.e = true;
        j.f = false;
        j.g = false;
        j.i = 0;
        k.a = true;
        k.b = true;
        k.c = false;
        k.d = false;
        k.e = false;
        j.i = 1;
        l.a = false;
        l.b = true;
        l.c = false;
        l.d = true;
        l.e = false;
        l.h = false;
        l.i = 2;
    }

    String a(String str) {
        int lastIndexOf = str.lastIndexOf(45);
        return lastIndexOf == -1 ? str : str.substring(lastIndexOf + 1);
    }

    String a(int i) {
        if (!this.d) {
            return "";
        }
        String modifier = Modifier.toString(i);
        if (modifier.length() == 0) {
            return "";
        }
        return new StringBuffer().append(modifier).append(" ").toString();
    }

    String b(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf == -1 ? str : str.substring(lastIndexOf + 1);
    }

    String a(Class cls, String str, boolean z) {
        if (cls == null) {
            return "ANONYMOUS";
        }
        if (cls.isArray()) {
            Class componentType = cls.getComponentType();
            return new StringBuffer().append(a(componentType, componentType.getName(), z)).append("[]").toString();
        } else if (z) {
            return b(str).replace('$', '.');
        } else {
            return str.replace('$', '.');
        }
    }

    public String a(Class cls) {
        return a(cls, cls.getName(), this.a);
    }

    public String a(Class cls, String str) {
        return a(cls, str, this.e);
    }

    public void a(StringBuffer stringBuffer, Class[] clsArr) {
        for (int i = 0; i < clsArr.length; i++) {
            if (i > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(a(clsArr[i]));
        }
    }

    public void b(StringBuffer stringBuffer, Class[] clsArr) {
        if (clsArr != null) {
            if (this.b) {
                stringBuffer.append("(");
                a(stringBuffer, clsArr);
                stringBuffer.append(")");
            } else if (clsArr.length == 0) {
                stringBuffer.append("()");
            } else {
                stringBuffer.append("(..)");
            }
        }
    }

    public void c(StringBuffer stringBuffer, Class[] clsArr) {
        if (this.c && clsArr != null && clsArr.length != 0) {
            stringBuffer.append(" throws ");
            a(stringBuffer, clsArr);
        }
    }
}
